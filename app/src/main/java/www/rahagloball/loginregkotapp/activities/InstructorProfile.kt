package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentUris
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.courses.CrsAddSection
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getinstrctr.GetInstrctrPojo
import java.io.ByteArrayOutputStream
import java.io.File

class InstructorProfile : AppCompatActivity() {
    var asscit_gender: Spinner? = null
    var instrc_details: EditText? = null
    var instrctr_namee: EditText? = null
    var instrctr_email: EditText? = null
    var instrctr_lnamee: EditText? = null
    var instrctr_mobilee: EditText? = null
    var instrc_dobbb: EditText? = null
    var instrc_details_str: String? = null
    var instrctr_namee_str: String? = null
    var instrctr_email_str: String? = null
    var instrctr_lnamee_str: String? = null
    var instrctr_mobilee_str: String? = null
    var instrc_dobbb_str: String? = null
    var asscit_gender_str: String? = null
    var imgPath: String? = null
    var imgPath_file: String? = null
    var token: String? = null
    var sel_gender = arrayOf("Select", "Male", "Female")
    var dataAdapter_gendr: ArrayAdapter<String>? = null
    var context: Context? = null
    var file_ban_img: File? = null
    var file_img: File? = null
    var sel_imgg: MultipartBody.Part? = null
    var sel_vidd: MultipartBody.Part? = null
    var map: HashMap<String, RequestBody>? = null
    var manager: SessionManager? = null
    var instructtr_img: ImageView? = null
    var instrct_img_file: ImageView? = null
    var instrct_prfl_Save: Button? = null
    var fl_idlayout: FrameLayout? = null
    var instr_btnEdit: Button? = null
    var add_courseee: TextView? = null
    var spurt_tooltxtfddftx: TextView? = null
    var progressBar: RelativeLayout? = null
    var instrtr_id_str = true
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_profile)
        context = this@InstructorProfile
        manager = SessionManager()
        token = manager?.getPreferences(this@InstructorProfile, Constants.USER_TOKEN_LRN)
        instrc_details = findViewById<EditText>(R.id.instrc_details)
        add_courseee = findViewById<TextView>(R.id.add_courseee)
        instrctr_namee = findViewById<EditText>(R.id.instrctr_namee)
        instrctr_email = findViewById<EditText>(R.id.instrctr_email)
        instrctr_lnamee = findViewById<EditText>(R.id.instrctr_lnamee)
        instrctr_mobilee = findViewById<EditText>(R.id.instrctr_mobilee)
        asscit_gender = findViewById<Spinner>(R.id.asscit_gender)
        instrc_dobbb = findViewById<EditText>(R.id.instrc_dobbb)
        instructtr_img = findViewById<ImageView>(R.id.instructtr_img)
        instrct_img_file = findViewById<ImageView>(R.id.instrct_img_file)
        instrct_prfl_Save = findViewById<Button>(R.id.instrct_prfl_Save)
        fl_idlayout = findViewById<FrameLayout>(R.id.fl_idlayout)
        instr_btnEdit = findViewById<Button>(R.id.instr_btnEdit)
        progressBar = findViewById<RelativeLayout>(R.id.progressBar_inst)
        spurt_tooltxtfddftx = findViewById<TextView>(R.id.spurt_tooltxtfddftx)
        instrc_details?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.id == R.id.instrc_details) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })
        dataAdapter_gendr =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, sel_gender)
        dataAdapter_gendr?.setDropDownViewResource(R.layout.custom_spiner_layout)
        asscit_gender?.setAdapter(dataAdapter_gendr)
        checkAndRequestPermissions()
        instrct_prfl_Save!!.setOnClickListener { v: View? -> checkvalidation() }
        instr_btnEdit!!.setOnClickListener { v: View? -> showPictureDialogImg() }
        fl_idlayout?.setOnClickListener(View.OnClickListener { v: View? -> showPictureDialogImgId() })
        spurt_tooltxtfddftx?.setOnClickListener(View.OnClickListener { v: View? ->
            val uri = Uri.parse("https://www.nationlearns.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
        add_courseee?.setOnClickListener(View.OnClickListener { v: View? ->
            val `in` = Intent(this@InstructorProfile, CrsAddSection::class.java)
            startActivity(`in`)
        })
        getinstrtrid()
    }

    private fun checkvalidation() {
        instrc_details_str = instrc_details?.text.toString()
        instrctr_namee_str = instrctr_namee?.text.toString()
        instrctr_email_str = instrctr_email?.text.toString()
        instrctr_lnamee_str = instrctr_lnamee?.text.toString()
        instrctr_mobilee_str = instrctr_mobilee?.text.toString()
        instrc_dobbb_str = instrc_dobbb?.text.toString()
        asscit_gender_str = asscit_gender?.selectedItem.toString()
        if (instrc_details_str == "" || instrc_details_str!!.isEmpty() || instrctr_namee_str == "" || instrctr_namee_str!!.isEmpty() || instrctr_email_str == "" || instrctr_email_str!!.isEmpty() || instrctr_lnamee_str == "" || instrctr_lnamee_str!!.isEmpty() || instrctr_mobilee_str == "" || instrctr_mobilee_str!!.isEmpty() || instrc_dobbb_str == "" || instrc_dobbb_str!!.isEmpty()) {
            Toast.makeText(context, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if (asscit_gender_str == "" || asscit_gender_str!!.isEmpty() || asscit_gender_str == "Select") {
            Toast.makeText(context, "Kindly Select Gender!", Toast.LENGTH_SHORT).show()
        } else if (imgPath == null || imgPath!!.isEmpty()) {
            Toast.makeText(context, "Image is empty!!", Toast.LENGTH_SHORT).show()
        } else if (imgPath_file == null || imgPath_file!!.isEmpty()) {
            Toast.makeText(context, "Banner Image is empty!!", Toast.LENGTH_SHORT).show()
        } else {
            submit_chanel_info()
        }
    }

    fun submit_chanel_info() {
//        progressBar.visibility = View.VISIBLE;
        val loading: ProgressDialog =
            ProgressDialog.show(this, "Uploading Details...!!", "Please Wait..!", false, false)
        try {
            instrc_details_str = instrc_details?.text.toString()
            instrctr_namee_str = instrctr_namee?.text.toString()
            instrctr_email_str = instrctr_email?.text.toString()
            instrctr_lnamee_str = instrctr_lnamee?.text.toString()
            instrctr_mobilee_str = instrctr_mobilee?.text.toString()
            instrc_dobbb_str = instrc_dobbb?.text.toString()
            asscit_gender_str = asscit_gender?.selectedItem.toString()
            map = HashMap<String, RequestBody>()
            map!!["fname"] = RequestBody.create(MediaType.parse("text/plain"), instrctr_namee_str)
            map!!["lname"] = RequestBody.create(MediaType.parse("text/plain"), instrctr_lnamee_str)
            map!!["dob"] = RequestBody.create(MediaType.parse("text/plain"), instrc_dobbb_str)
            map!!["email"] = RequestBody.create(MediaType.parse("text/plain"), instrctr_email_str)
            map!!["gender"] = RequestBody.create(MediaType.parse("text/plain"), asscit_gender_str)
            map!!["mobile"] =
                RequestBody.create(MediaType.parse("text/plain"), instrctr_mobilee_str)
            map!!["detail"] = RequestBody.create(MediaType.parse("text/plain"), instrc_details_str)
            file_img = File(imgPath)
            file_ban_img = File(imgPath_file)
            val reqFile_img: RequestBody = RequestBody.create(MediaType.parse("image/*"), file_img)
            val reqFile_img_bn: RequestBody =
                RequestBody.create(MediaType.parse("image/*"), file_ban_img)
            sel_imgg = MultipartBody.Part.createFormData("image", imgPath, reqFile_img)
            sel_vidd = MultipartBody.Part.createFormData("file", imgPath_file, reqFile_img_bn)
            RetrofitClient.getClient()
                .bcm_an_instrctr(map, sel_imgg, sel_vidd, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<String?>(instructtr_img) {
                  override  fun onResponse(call: Call<String?>, response: Response<String?>) {
//                            progressBar.visibility = View.GONE;
                        loading.dismiss()
                        try {
                            val res_indt  = response.body()?.toString()
                            if (res_indt != null) {
                                if (res_indt.contains("Success")) {
                                    Toast.makeText(
                                        context,
                                        "Instructor profile is created successfully!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                }
                            }
                        } catch (e: Exception) {
                            Log.d("Exception", "|=>" + e.message)
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getinstrtrid() {
        val url: String = Configs.BASE_URL2 + "instructor-exist"
        RetrofitClient.getClient().instrcrchnlexists(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetInstrctrPojo?>(instrc_details) {
             override   fun onResponse(
                    call: Call<GetInstrctrPojo?>?,
                    response: Response<GetInstrctrPojo?>
                ) {
                    try {
                        if (response.body() != null) {
                            val rererer: String ? = response.body()?.status
                            if (rererer == "200") {
                                instrtr_id_str  = response.body()?.isExist == true
                                if (instrtr_id_str) {
                                    add_courseee?.visibility = View.VISIBLE
                                } else {
                                    add_courseee?.visibility = View.GONE
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this@InstructorProfile, Manifest.permission.CAMERA)
        //        int locationPermission = ContextCompat.checkSelfPermission(InstructorProfile.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(InstructorProfile.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@InstructorProfile,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@InstructorProfile,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
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
                this@InstructorProfile,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }

    private fun showPictureDialogImg() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems = arrayOf(
            "Select Image",
            "Capture Image"
        )
        pictureDialog.setItems(pictureDialogItems,
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        0 -> chooseImgFromGallary()
                        1 -> takeImgromCamera()
                    }
                }
            })
        pictureDialog.show()
    }

    private fun takeImgromCamera() {
        val capture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(capture, CAPTURE_REQUEST_CODE)
    }

    private fun chooseImgFromGallary() {
        val select = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(select, SELECT_REQUEST_CODE)
    }

    private fun showPictureDialogImgId() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems = arrayOf(
            "Select Id Image",
            "Capture Image for Id"
        )
        pictureDialog.setItems(pictureDialogItems,
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        0 -> chooseImgFromGallaryban()
                        1 -> takeImgromCameraban()
                    }
                }
            })
        pictureDialog.show()
    }

    private fun takeImgromCameraban() {
        val capture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(capture, CAPTURE_REQUEST_CODE_BAN)
    }

    private fun chooseImgFromGallaryban() {
        val select = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(select, SELECT_REQUEST_CODE_BAN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
//            Log.d("what", "cancle");
            return
        }
        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap = data.getExtras()?.get("data") as Bitmap
                    instructtr_img!!.setImageBitmap(bitmap)
                    try {
                        imgPath = getPath(context, getImageUri(this, bitmap))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            SELECT_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    try {
                        val selectedImage: Uri? = data.data
                        val filePathColumn = arrayOf<String>(MediaStore.Images.Media.DATA)
                        if (selectedImage != null) {
                            val cursor: Cursor? = contentResolver.query(
                                selectedImage,
                                filePathColumn,
                                null,
                                null,
                                null
                            )
                            if (cursor != null) {
                                cursor.moveToFirst()
                                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                                val picturePath = cursor.getString(columnIndex)
                                instructtr_img!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath = getPath(
                                        context,
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

            CAPTURE_REQUEST_CODE_BAN -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap = data.getExtras()?.get("data") as Bitmap
                    instrct_img_file!!.setImageBitmap(bitmap)
                    try {
                        imgPath_file = getPath(context, getImageUri(this, bitmap))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            SELECT_REQUEST_CODE_BAN -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    try {
                        val selectedImage: Uri? = data.data
                        val filePathColumn = arrayOf<String>(MediaStore.Images.Media.DATA)
                        if (selectedImage != null) {
                            val cursor: Cursor? = contentResolver.query(
                                selectedImage,
                                filePathColumn,
                                null,
                                null,
                                null
                            )
                            if (cursor != null) {
                                cursor.moveToFirst()
                                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                                val picturePath = cursor.getString(columnIndex)
                                instrct_img_file!!.setImageBitmap(
                                    BitmapFactory.decodeFile(
                                        picturePath
                                    )
                                )
                                try {
                                    imgPath_file = getPath(
                                        context,
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
        }
    }

    fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
        val baos = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, baos)
        val path1: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "NLInstructor",
            null
        )
        return Uri.parse(path1)
    }

    companion object {
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        private const val CAPTURE_REQUEST_CODE = 0
        private const val SELECT_REQUEST_CODE = 1
        private const val CAPTURE_REQUEST_CODE_BAN = 3
        private const val SELECT_REQUEST_CODE_BAN = 4
        fun getPath(context: Context?, uri: Uri): String? {
            val isKitKat = true

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
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                return getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                return uri.path
            }
            return null
        }

        fun getDataColumn(
            context: Context?, uri: Uri?, selection: String?,
            selectionArgs: Array<String>?
        ): String? {
            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(
                column
            )
            try {
                cursor = context!!.contentResolver.query(
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

        fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }
    }
}