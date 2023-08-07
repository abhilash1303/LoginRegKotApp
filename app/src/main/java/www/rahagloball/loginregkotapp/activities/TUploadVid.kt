package www.rahagloball.loginregkotapp.activities



//import okhttp3.MediaType
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner
import java.io.ByteArrayOutputStream
import java.io.File

class TUploadVid : AppCompatActivity() {
    var catgrySpinnerSearch: SearchableSpinner? = null
    var subcatgrySpinnerSearch: SearchableSpinner? = null
    var cat_results: JSONArray? = null
    var subcat_results: JSONArray? = null
    var vidcatresult: JSONArray? = null
    var catgry_list = ArrayList<String>()
    var subcatgry_list = ArrayList<String>()
    var vidcategry = ArrayList<String>()
    var catgrt_id_str: String? = null
    var subcat_id_str: String? = null
    var sel_eightenperstr = arrayOf("Select", "Yes", "No")
    var sel_langstr = arrayOf(
        "Select",
        "Kannada",
        "Hindi",
        "Telugu",
        "Tamil",
        "Malayalam",
        "Assamese",
        "English",
        "Bengali",
        "Gujarati",
        "Kashmiri"
    )
    var cutiorVid = arrayOf("video")
    var sel_visiblity_str = arrayOf("Public", "Super Supported")
    var sel_chnl_str = arrayOf("Select", "My Channel", "My Business Channel")
    var dataAdapter1: ArrayAdapter<String>? = null
    var ataAdapter_sel_visblt: ArrayAdapter<String>? = null
    var ataAdapter_sel_vh: ArrayAdapter<String>? = null
    var ataAdapter_sel_eightenper: ArrayAdapter<String>? = null
    var ataAdapter_sel_lng: ArrayAdapter<String>? = null
    var context: Context? = null
    var token: String? = null
    var vidcatgryId: String? = null
    var manager: SessionManager? = null
    var sel_cuti_vid: Spinner? = null
    var progrees_vid: RelativeLayout? = null
    var vid_prevw_imgg: ImageView? = null
    var vid_prevw_imgg_btn: Button? = null
    var upld_videoo: Button? = null
    var upld_videoo_pv: Button? = null
    var submit_videoo: Button? = null
    var vid_titlee: EditText? = null
    var vid_descc: EditText? = null
    var keywords_vid: EditText? = null
    var vid_upld_view: VideoView? = null
    var sel_vid_cat: Spinner? = null
    var sel_eightenper: Spinner? = null
    var sel_language: Spinner? = null
    var progressBar: ProgressDialog? = null
    private var progressBarStatus = 10
    private val progressBarHandler = Handler()
    private var fileSize: Long = 1000
    var video_catgryy: Spinner? = null
    var sel_visiblity: Spinner? = null
    var sel_ch: Spinner? = null
    var ll_vid_pv: LinearLayout? = null
    var file_img: File? = null
    var file_vid: File? = null
    private var selectedImageUri: Uri? = null
    private var selectedVideoUri: Uri? = null
    var imgPath: String? = null
    var selectedVideoPath: String? = null
    private var imagePickerLauncher: ActivityResultLauncher<Intent>? = null
    private var videoPickerLauncher: ActivityResultLauncher<Intent>? = null
    var vid_titlee_str: String? = null
    var vid_descc_str: String? = null
    var selch_str: String? = null
    var sel_cuti_vidStr: String? = null
    var keywords_vid_str: String? = null
    var video_catgryyStr: String? = null
    var sel_visiblityStr: String? = null
    var sel_eightenperStr: String? = null
    var selLangStr: String? = null
    var map: HashMap<String, RequestBody>? = null
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tupload_vid_new1)
        context = this@TUploadVid
        manager = SessionManager()
        token = manager?.getPreferences(this@TUploadVid, Constants.USER_TOKEN_LRN)
        progressBar = ProgressDialog(context)
        //        progressDialog = new ProgressDialog(this);
        progrees_vid = findViewById<RelativeLayout>(R.id.progrees_vid)
        vid_prevw_imgg = findViewById<ImageView>(R.id.vid_prevw_imgg)
        vid_prevw_imgg_btn = findViewById<Button>(R.id.vid_prevw_imgg_btn)
        upld_videoo = findViewById<Button>(R.id.upld_videoo)
        upld_videoo_pv = findViewById<Button>(R.id.upld_videoo_pv)
        submit_videoo = findViewById<Button>(R.id.submit_videoo)
        vid_titlee = findViewById<EditText>(R.id.vid_titlee)
        vid_descc = findViewById<EditText>(R.id.vid_descc)
        vid_upld_view = findViewById<VideoView>(R.id.vid_upld_view)
        sel_cuti_vid = findViewById<Spinner>(R.id.sel_cuti_vid)
        sel_vid_cat = findViewById<Spinner>(R.id.sel_vid_cat)
        video_catgryy = findViewById<Spinner>(R.id.video_catgryy)
        sel_visiblity = findViewById<Spinner>(R.id.sel_visiblity)
        ll_vid_pv = findViewById<LinearLayout>(R.id.ll_vid_pv)
        sel_ch = findViewById<Spinner>(R.id.sel_ch)
        keywords_vid = findViewById<EditText>(R.id.keywords_vid)
        sel_eightenper = findViewById<Spinner>(R.id.sel_eightenper)
        sel_language = findViewById<Spinner>(R.id.sel_language)
        catgrySpinnerSearch = findViewById(R.id.catgrySpinnerSearch)
        subcatgrySpinnerSearch = findViewById(R.id.subcatgrySpinnerSearch)
        dataAdapter1 =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, cutiorVid)
        dataAdapter1?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_cuti_vid?.setAdapter(dataAdapter1)
        ataAdapter_sel_visblt = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_visiblity_str
        )
        ataAdapter_sel_visblt?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_visiblity?.setAdapter(ataAdapter_sel_visblt)
        ataAdapter_sel_vh = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_chnl_str
        )
        ataAdapter_sel_vh?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_ch?.setAdapter(ataAdapter_sel_vh)
        ataAdapter_sel_eightenper = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_eightenperstr
        )
        ataAdapter_sel_eightenper?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_eightenper?.setAdapter(ataAdapter_sel_eightenper)
        ataAdapter_sel_lng = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_langstr
        )
        ataAdapter_sel_lng?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_language?.setAdapter(ataAdapter_sel_lng)
        checkAndRequestPermissions()
        imagePickerLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    try {
                        val data: Intent? = result.data
                        if (data != null) {
                            selectedImageUri = data.data
                        }
                        val filePathColumn = arrayOf<String>(MediaStore.Images.Media.DATA)
                        if (selectedImageUri != null) {
                            val cursor: Cursor? = contentResolver.query(
                                selectedImageUri!!,
                                filePathColumn,
                                null,
                                null,
                                null
                            )
                            if (cursor != null) {
                                cursor.moveToFirst()
                                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                                val picturePath = cursor.getString(columnIndex)
                                vid_prevw_imgg!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath = getPath(
                                        context!!,
                                        getImageUri(this, BitmapFactory.decodeFile(picturePath))
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                cursor.close()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        )
        videoPickerLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    if (data != null) {
//                            selectedVideoUri = videoUri;
                        selectedVideoUri = data.data
                        try {
                            selectedVideoPath = getPath(context!!, selectedVideoUri)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        Log.d(TAG, "Selected Video URI: $selectedVideoUri")
                        vid_upld_view?.setVideoURI(selectedVideoUri)
                        vid_upld_view?.start()
                    }
                }
            }
        )
        vid_prevw_imgg_btn!!.setOnClickListener { v: View? -> pickImage() }
        upld_videoo!!.setOnClickListener { v: View? -> pickVideo() }
        catgrySpinnerSearch?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                catgrt_id_str = GetCtgryid(position)
                getsubCtgryData(catgrt_id_str)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        subcatgrySpinnerSearch?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                subcat_id_str = GetsubCtgryid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        video_catgryy?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                vidcatgryId = GetVidcatid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        ctgryData
        vidCtgryData
        submit_videoo!!.setOnClickListener { v: View? -> checkvalidation() }
    }

    private fun checkvalidation() {
        vid_titlee_str = vid_titlee?.text.toString()
        vid_descc_str = vid_descc?.text.toString()
        keywords_vid_str = keywords_vid?.text.toString()
        sel_cuti_vidStr = sel_cuti_vid?.selectedItem.toString()
        video_catgryyStr = video_catgryy?.selectedItem.toString()
        sel_visiblityStr = sel_visiblity?.selectedItem.toString()
        sel_eightenperStr = sel_eightenper?.selectedItem.toString()
        selLangStr = sel_language?.selectedItem.toString()
        selch_str = sel_ch?.selectedItem.toString()
        if (vid_titlee_str == "" || vid_titlee_str!!.isEmpty() || vid_descc_str == "" || vid_descc_str!!.isEmpty() || keywords_vid_str == "" || keywords_vid_str!!.isEmpty()) {
            Toast.makeText(context, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if (sel_eightenperStr == "" || sel_eightenperStr!!.isEmpty() || sel_eightenperStr == "Select") {
            Toast.makeText(
                context,
                "Kindly Select if, video you are uploading is permissible under the age of 18!",
                Toast.LENGTH_SHORT
            ).show()
        } else if (selLangStr == "" || selLangStr!!.isEmpty() || selLangStr == "Select") {
            Toast.makeText(context, "Kindly Select Language!", Toast.LENGTH_SHORT).show()
        } else if (imgPath == null || imgPath!!.isEmpty()) {
            Toast.makeText(context, "Image is empty!!", Toast.LENGTH_SHORT).show()
        } else if (selch_str == "" || selch_str!!.isEmpty() || selch_str == "Select") {
            Toast.makeText(context, "Kindly Select channel!", Toast.LENGTH_SHORT).show()
        } else if (selectedVideoPath == null || selectedVideoPath!!.isEmpty()) {
            Toast.makeText(context, "Video is empty!!", Toast.LENGTH_SHORT).show()
        } else {
            tuplodvideo()
            //            submit_videoodde();
        }
    }

    private fun tuplodvideo() {
        try {
            if (selectedImageUri == null || selectedVideoUri == null) {
                Toast.makeText(this, "Please select both image and video", Toast.LENGTH_SHORT)
                    .show()
                return
            }
            vid_titlee_str = vid_titlee?.text.toString()
            vid_descc_str = vid_descc?.text.toString()
            keywords_vid_str = keywords_vid?.text.toString()
            sel_cuti_vidStr = sel_cuti_vid?.selectedItem.toString()
            video_catgryyStr = video_catgryy?.selectedItem.toString()
            if (sel_visiblityStr == "Public") {
                sel_visiblityStr = "N"
            } else if (sel_visiblityStr == "Super Support") {
                sel_visiblityStr = "Y"
            }
            if (selch_str == "My Channel") {
                selch_str = "normal"
            } else if (selch_str == "My Business Channel") {
                selch_str = "business"
            }
            map = HashMap<String, RequestBody>()
            map!!["title"] = RequestBody.create(MediaType.parse("text/plain"), vid_titlee_str)
            map!!["description"] = RequestBody.create(MediaType.parse("text/plain"), vid_descc_str)
            map!!["type"] = RequestBody.create(MediaType.parse("text/plain"), sel_cuti_vidStr)
            map!!["keywords"] = RequestBody.create(MediaType.parse("text/plain"), keywords_vid_str)
            map!!["visible"] = RequestBody.create(MediaType.parse("text/plain"), sel_visiblityStr)
            map!!["video_category"] = RequestBody.create(MediaType.parse("text/plain"), vidcatgryId)
            map!!["category"] = RequestBody.create(MediaType.parse("text/plain"), catgrt_id_str)
            map!!["sub_category"] = RequestBody.create(MediaType.parse("text/plain"), subcat_id_str)
            map!!["channel_type"] = RequestBody.create(MediaType.parse("text/plain"), selch_str)
            imgPath = getRealPathFromUri(selectedImageUri!!)
            selectedVideoPath = getRealPathFromUri(selectedVideoUri!!)
            file_img = File(imgPath)
            file_vid = File(selectedVideoPath)
            val reqFile_img: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file_img)
            val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), file_vid)
            val imagePart: MultipartBody.Part =
                MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img)
            val videoPart: MultipartBody.Part =
                MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
            //            sel_imgg = MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img);
//            sel_vidd = MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid);
            progressBar = ProgressDialog(context)
            progressBar?.setCancelable(true)
            progressBar?.setMessage("Video is Uploading, Please Wait.")
            progressBar?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressBar?.progress=(10)
            progressBar?.setCanceledOnTouchOutside(false)
            progressBar?.max=(100)
            progressBar?.show()
            //            //reset progress bar and filesize status
            progressBarStatus = 10
            fileSize = 1000
            //
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
                    progressBarHandler.post(Runnable { progressBar?.setProgress(progressBarStatus) })
                }
            }.start()
            RetrofitClient.getClient().upload_viddnew(
                map, imagePart, videoPart,
                "application/json", "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
                 override   fun onResponse(call: Call<String?>, response: Response<String?>) {
                        try {
                            val tcrs_res = response.body()!!.toString()
                            if (tcrs_res.contains("1")) {
                                Toast.makeText(
                                    this@TUploadVid,
                                    "Your video will be visible after approval!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@TUploadVid,
                                        HomeDemoActivityCtgry::class.java
                                    )
                                )
                                finish()
                            } else {
                                Toast.makeText(this@TUploadVid, "Failed", Toast.LENGTH_SHORT).show()
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

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher?.launch(intent)
        vid_upld_view?.setVideoURI(selectedVideoUri)
        vid_upld_view?.start()
    }

    private fun pickVideo() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        videoPickerLauncher?.launch(intent)
        //        videoPickerLauncher.launch("video/*");
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

    fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
        val baos = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, baos)
        val path1: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "NLUploadVideos",
            null
        )
        return Uri.parse(path1)
    }

    fun doOperation(): Int {
        //The range of ProgressDialog starts from 0 to 10000
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
            /* else {
                return 100;
            }*/
        } //end of while
        return 100
    } //end of doOperation

    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        //        int locationPermission = ContextCompat.checkSelfPermission(TUploadVid.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(TUploadVid.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val storagewritePermission: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (permissioncallcam != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (storagewritePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        //        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
//        }
//        if (locationcoarsePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//        }
//        if (SmsPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
//        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }
    //Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array

    //Creating a request queue
    private val vidCtgryData: Unit
        get() {
            try {
                progressBar?.show()
                val url: String = Configs.VID_CATGRY_URL
                val stringRequest: StringRequest = object : StringRequest(url,
                    com.android.volley.Response.Listener<String> { response: String? ->
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = response?.let { JSONObject(it) }

                            //Storing the Array of JSON String to our JSON Array
                            if (j != null) {
                                vidcatresult = j.getJSONArray(Configs.JSON_VIDCTRGYARRAY)
                            }

                            //Calling method getStudents to get the students from the JSON Array
                            getVidCtgry(vidcatresult)
                            progressBar?.cancel()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    com.android.volley.Response.ErrorListener { error: VolleyError? -> }
                ) {
                    // Override the getHeaders() function with explicit return type
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers: MutableMap<String, String> = HashMap()
                        headers["Accept"] = "application/json"
                        headers["Authorization"] = "Bearer $token"
                        return headers
                    }
                }
                stringRequest.retryPolicy = DefaultRetryPolicy(
                    Configs.MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )

                //Creating a request queue
                val requestQueue: RequestQueue = Volley.newRequestQueue(this)

                //Adding request to the queue
                requestQueue.add<String>(stringRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun GetVidcatid(position: Int): String {
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject? = vidcatresult?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_VIDCTRGYID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getVidCtgry(j: JSONArray?) {
        try {

            //Traversing through all the items in the json array
            vidcategry = ArrayList()
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)

                        //Adding the name of the student to array list
                        vidcategry!!.add(json.getString(Configs.KEY_VIDCTRGYNAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until vidcategry!!.size) {
                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
                            vidcategry!!.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }

//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in vidcategry!!.indices) {
//                            val h: KeyPairBoolData = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(vidcategry!!.get(k))
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

//
//            dataAdapter_vid_cat = new ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, vidcategry);
//            dataAdapter_vid_cat.setDropDownViewResource(R.layout.custom_spiner_layout);
//            video_catgryy?.adapter =(dataAdapter_vid_cat);
            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(
                this@TUploadVid,
                R.layout.custom_spiner_layout,
                vidcategry!!
            )
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            video_catgryy?.adapter =(dataAdapter5)
            //            int spinnerPosition = dataAdapter5.getPosition(stateiid);
//            agentype.setSelection(spinnerPosition);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    //Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array
    //Calling method getStudents to get the students from the JSON Array
    //                    blur_reg1.visibility = View.GONE;
    //Creating a request queue
    //Adding request to the queue
    //Category
    private val ctgryData: Unit
        get() {
            try {
                val url: String = Configs.CATGRY_URL
                val stringRequest = StringRequest(url,
                    { response: String? ->
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)
                            //Storing the Array of JSON String to our JSON Array
                            cat_results = j.getJSONArray(Configs.JSON_CTRGYARRAY)
                            //Calling method getStudents to get the students from the JSON Array
                            getCatgry(cat_results)
                            //                    blur_reg1.visibility = View.GONE;
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    { error: VolleyError? -> })
                stringRequest.retryPolicy = DefaultRetryPolicy(
                    Configs.MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
                //Creating a request queue
                val requestQueue: RequestQueue = Volley.newRequestQueue(this)
                //Adding request to the queue
                requestQueue.add<String>(stringRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun GetCtgryid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = cat_results?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CTRGYID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getCatgry(j: JSONArray?) {
        try {
            //Traversing through all the items in the json array
            catgry_list = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        catgry_list.add(json.getString(Configs.KEY_CTRGYNAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until catgry_list.size) {
                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
                            catgry_list.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }

                        //                    val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
        //                    for (k in catgry_list.indices) {
        //                        val h = KeyPairBoolData()
        //                        h.setId(k + 1)
        //                        h.setName(catgry_list[k])
        //                        h.setSelected(false)
        //                        listArray0.add(h)
        //                    }
                        //
        //                    singleSpinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //                        @Override
        //                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //
        //                            stateiid = GetSid(position);
        //                            getfsp(stateiid);
        //
        //                        }
        //
        //                        @Override
        //                        public void onNothingSelected(AdapterView<?> parent) {
        ////                            singleSpinnerSearch.setTitle("Select City");
        //
        //                        }
        //                    });
        
        //
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@TUploadVid, R.layout.custom_spiner_layout, catgry_list)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            catgrySpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //sub-Category
    private fun getsubCtgryData(subcat_id_str: String?) {
        try {
            val url: String = Configs.SUBCATGRY_URL + "/" + subcat_id_str
            val stringRequest = StringRequest(url,
                { response: String? ->
                    var j: JSONObject? = null
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = response?.let { JSONObject(it) }
                        //Storing the Array of JSON String to our JSON Array
                        if (j != null) {
                            subcat_results = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
                        }
                        //Calling method getStudents to get the students from the JSON Array
                        getSubCatgry(subcat_results)
                        //                    blur_reg1.visibility = View.GONE;
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { error: VolleyError? -> })
            stringRequest.retryPolicy = DefaultRetryPolicy(
                Configs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            //Creating a request queue
            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
            //Adding request to the queue
            requestQueue.add<String>(stringRequest)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun GetsubCtgryid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = subcat_results?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_SUBCTRGYID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getSubCatgry(j: JSONArray?) {
        try {
            //Traversing through all the items in the json array
            subcatgry_list = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        subcatgry_list.add(json.getString(Configs.KEY_SUBCTRGYNAME))
                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until subcatgry_list!!.size) {
                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
                            subcatgry_list.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }
                        
//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in subcatgry_list.indices) {
//                            val h = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(subcatgry_list[k])
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }
                        //
        //                    singleSpinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //                        @Override
        //                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //
        //                            stateiid = GetSid(position);
        //                            getfsp(stateiid);
        //
        //                        }
        //
        //                        @Override
        //                        public void onNothingSelected(AdapterView<?> parent) {
        ////                            singleSpinnerSearch.setTitle("Select City");
        //
        //                        }
        //                    });
        
        //
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@TUploadVid, R.layout.custom_spiner_layout, subcatgry_list)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            subcatgrySpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TAG = "TUploadVid"
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 101
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