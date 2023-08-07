package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.app.ProgressDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import java.io.File

class SectionVideoActivity : AppCompatActivity() {
    var bundle: Bundle? = null
    var crs_section_idd: String? = null
    var mContect: Context? = null
    var manager: SessionManager? = null
    var token: String? = null
    var edit_section_titlee: EditText? = null
    var sel_videoo: TextView? = null
    var upld_course: TextView? = null

    //    SwitchCompat prvw_switch;
    var prvw_switch: Spinner? = null
    var prev_enbld_str = arrayOf("Select", "On", "Off")
    var vid_upld_view: VideoView? = null
    private var videoPickerLauncher: ActivityResultLauncher<Intent>? = null
    private var selectedVideoUri: Uri? = null
    var selectedVideoPath: String? = null
    var edit_section_titleestr: String? = null
    var prev_enbld_str1: String? = null
    var map: HashMap<String, RequestBody>? = null
    var file_vid: File? = null
    var progressBar: ProgressDialog? = null
    private var progressBarStatus = 10
    private val progressBarHandler = Handler()
    private var fileSize: Long = 1000
    var value: String? = null
    var customDialog: CustomDialog? = null
    var ataAdapter_sel_vh: ArrayAdapter<String>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_video)
        mContect = this@SectionVideoActivity
        manager = SessionManager()
        token = manager?.getPreferences(this@SectionVideoActivity, Constants.USER_TOKEN_LRN)
        customDialog = CustomDialog(this)
        bundle = intent.extras
        crs_section_idd = bundle?.getString("section_title_idd")
        //        Log.e("crs_section_iddsceccc",crs_section_idd);
        progressBar = ProgressDialog(mContect)
        edit_section_titlee = findViewById<EditText>(R.id.edit_section_titlee)
        sel_videoo = findViewById<TextView>(R.id.sel_videoo)
        upld_course = findViewById<TextView>(R.id.upld_course)
        prvw_switch = findViewById<Spinner>(R.id.prvw_switch)
        vid_upld_view = findViewById<VideoView>(R.id.vid_upld_view)
        ataAdapter_sel_vh = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            prev_enbld_str
        )
        ataAdapter_sel_vh?.setDropDownViewResource(R.layout.custom_spiner_layout)
        prvw_switch?.adapter = ataAdapter_sel_vh
        videoPickerLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent = result.data!!
                    //                            selectedVideoUri = videoUri;
                    selectedVideoUri = data.data
                    try {
                        selectedVideoPath = getPath(mContect!!, selectedVideoUri)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    //                            Log.d(TAG, "Selected Video URI: " + selectedVideoUri);
                    vid_upld_view?.setVideoURI(selectedVideoUri)
                    vid_upld_view?.start()
                }
            }
        )
        sel_videoo?.setOnClickListener(View.OnClickListener { v: View? -> pickVideo() })
        upld_course?.setOnClickListener(View.OnClickListener { v: View? -> checkvalidation() })
    }

    private fun checkvalidation() {
        edit_section_titleestr = edit_section_titlee?.text.toString()
        prev_enbld_str1 = prvw_switch?.selectedItem.toString()
        if (edit_section_titleestr == "" || edit_section_titleestr!!.isEmpty()) {
            Toast.makeText(mContect, "Enter title!", Toast.LENGTH_SHORT).show()
        } else if (prev_enbld_str1 == "" || prev_enbld_str1!!.isEmpty() || prev_enbld_str1 == "Select") {
            Toast.makeText(mContect, "Kindly select preview enabled or not!", Toast.LENGTH_SHORT)
                .show()
        } else {
            upload_course_vid()
        }
    }

    private fun upload_course_vid() {
//        customDialog.show();
        try {
            if (selectedVideoUri == null) {
                Toast.makeText(this, "Please select video", Toast.LENGTH_SHORT).show()
                return
            }
            edit_section_titleestr = edit_section_titlee?.text.toString()
            prev_enbld_str1 = prvw_switch?.selectedItem.toString()
            bundle = intent.extras
            crs_section_idd = bundle?.getString("section_title_idd")
            if (prev_enbld_str1 == "On") {
                prev_enbld_str1 = "1"
            } else if (prev_enbld_str1 == "Off") {
                prev_enbld_str1 = "0"
            }
            map = HashMap<String, RequestBody>()
            map!!["title"] =
                RequestBody.create(MediaType.parse("text/plain"), edit_section_titleestr)
            map!!["section_id"] = RequestBody.create(MediaType.parse("text/plain"), crs_section_idd)
            map!!["paid_or_overview"] =
                RequestBody.create(MediaType.parse("text/plain"), prev_enbld_str1)
            selectedVideoPath = getRealPathFromUri(selectedVideoUri!!)
            file_vid = File(selectedVideoPath)
            val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), file_vid)
            val videoPart: MultipartBody.Part =
                MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
            progressBar = ProgressDialog(mContect)
            progressBar?.setCancelable(true)
            progressBar?.setMessage("Video is Uploading, Please Wait.")
            progressBar?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressBar?.setProgress(10)
            progressBar?.setCanceledOnTouchOutside(false)
            progressBar?.setMax(100)
            progressBar?.show()
            //            //reset progress bar and filesize status
            progressBarStatus = 10
            fileSize = 1000
            Thread {
                while (progressBarStatus < 100) {
                    // performing operation
                    progressBarStatus = doOperation()
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    // Updating the progress bar
                    progressBarHandler.post(Runnable { progressBar!!.setProgress(progressBarStatus) })
                }
            }.start()
            RetrofitClient.getClient().upload_section_video(
                map, videoPart,
                "application/json", "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<String?>(edit_section_titlee) {
                 override   fun onResponse(call: Call<String?>, response: Response<String?>) {
                        customDialog?.dismiss()
                        try {
                            if (response.body() != null) {
                                val tcrs_res  = response.body()?.toString()
                                if (tcrs_res != null) {
                                    if (tcrs_res.contains("1")) {
                                        Toast.makeText(
                                            this@SectionVideoActivity,
                                            "Course is uploaded successfully!!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@SectionVideoActivity,
                                                HomeDemoActivityCtgry::class.java
                                            )
                                        )
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this@SectionVideoActivity,
                                            "Failed",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            Log.d("Exception", "|=>" + e.message)
                            e.printStackTrace()
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //            prvw_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
    //                if (isChecked) {
    //                    map.put("paid_or_overview", RequestBody.create(MediaType.parse("text/plain"), "1"));
    //                } else {
    //                    map.put("paid_or_overview", RequestBody.create(MediaType.parse("text/plain"), "0"));
    //                }
    //            });
    //            prvw_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
    //                value = isChecked ? "1" : "0";
    //                map.put("paid_or_overview", RequestBody.create(MediaType.parse("text/plain"), value));
    //                Log.e("value", value);
    //            });
    //     Log.e("edit_section_titleestr",edit_section_titleestr);
    //            Log.e("crs_section_idd",crs_section_idd);
    //            Log.e("selectedVideoPath",selectedVideoPath);
    //            Log.e("value",value);
    private fun pickVideo() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        videoPickerLauncher?.launch(intent)
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val projection = arrayOf<String>(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            return filePath
        }
        return uri.path
    }

    fun doOperation(): Int {
        while (fileSize <= 10000) {
            fileSize++
            if (fileSize == 1000L) {
                return 10
            } else if (fileSize == 2000L) {
                return 20
            } else if (fileSize == 3000L) {
                return 30
            } else if (fileSize == 4000L) {
                return 40
            } else if (fileSize == 5000L) {
                return 50 // you can add more else if
            } else if (fileSize == 6000L) {
                return 60 // you can add more else if
            } else if (fileSize == 7000L) {
                return 70 // you can add more else if
            } else if (fileSize == 8000L) {
                return 80 // you can add more else if
            } else if (fileSize == 9000L) {
                return 90 // you can add more else if
            } else if (fileSize == 10000L) {
                return 100 // you can add more else if
            }
        } //end of while
        return 100
    }

    protected override fun onDestroy() {
        super.onDestroy()
        if (progressBar != null && progressBar!!.isShowing) {
            progressBar!!.dismiss()
        }
    } //        //The range of ProgressDialog starts from 0 to 10000

    //        while (fileSize <= 10000) {
    //            fileSize++;
    //            if (fileSize == 1000) {
    //                return 10;
    //            } else if (fileSize == 2000) {
    //                return 20;
    //            } else if (fileSize == 3000) {
    //                return 30;
    //            } else if (fileSize == 4000) {
    //                return 40;
    //            } else if (fileSize == 5000) {
    //                return 50;// you can add more else if
    //            } else if (fileSize == 6000) {
    //                return 60;// you can add more else if
    //            } else if (fileSize == 7000) {
    //                return 70;// you can add more else if
    //            } else if (fileSize == 8000) {
    //                return 80;// you can add more else if
    //            } else if (fileSize == 9000) {
    //                return 90;// you can add more else if
    //            } else if (fileSize == 10000) {
    //                return 100;// you can add more else if
    //            }
    //         /* else {
    //                return 100;
    //            }*/
    //        }//end of while
    //        return 100;
    //    }//end of doOperation
    companion object {
        fun getPath(context: Context, uri: Uri?): String? {
            val isKitKat: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    val docId: String = DocumentsContract.getDocumentId(uri)
                    val split =
                        docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) {
                    val id: String = DocumentsContract.getDocumentId(uri)
                    val contentUri: Uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloadss"),
                        java.lang.Long.valueOf(id)
                    )
                    return getDataColumn(context, contentUri, null, null)
                } else if (isMediaDocument(uri)) {
                    val docId: String = DocumentsContract.getDocumentId(uri)
                    val split =
                        docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(
                        split[1]
                    )
                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }
            } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
                return getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                return uri.path
            }
            return null
        }

        fun getDataColumn(
            context: Context, uri: Uri?, selection: String?,
            selectionArgs: Array<String>?
        ): String? {
            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(
                column
            )
            try {
                cursor = context.contentResolver.query(
                    uri!!, projection, selection, selectionArgs,
                    null
                )
                if (cursor != null && cursor.moveToFirst()) {
                    val column_index = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(column_index)
                }
            } finally {
                cursor?.close()
            }
            return null
        }

        fun isExternalStorageDocument(uri: Uri?): Boolean {
            return "com.android.externalstorage.documents" == uri!!.authority
        }

        fun isDownloadsDocument(uri: Uri?): Boolean {
            return "com.android.providers.downloads.documents" == uri!!.authority
        }

        fun isMediaDocument(uri: Uri?): Boolean {
            return "com.android.providers.media.documents" == uri!!.authority
        }
    }
}