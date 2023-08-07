package www.rahagloball.loginregkotapp.activities.camera

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
import android.view.MotionEvent
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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import java.io.ByteArrayOutputStream
import java.io.File

class UploadVideoActivity : AppCompatActivity() {
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
    var path = ""
    var sel_cuti_vid: Spinner? = null
    var sel_vid_cat: Spinner? = null
    var sel_eightenper: Spinner? = null
    var sel_language: Spinner? = null
    var selectedVideoPath: String? = null
    var selectedVideoPath_pv: String? = null
    var token: String? = null
    var context: Context? = null
    var map: HashMap<String, RequestBody>? = null
    var vid_titlee_str: String? = null
    var vid_descc_str: String? = null
    var imgPath: String? = null
    var sel_cuti_vidStr: String? = null
    var keywords_vid_str: String? = null
    var video_catgryyStr: String? = null
    var sel_visiblityStr: String? = null
    var sel_eightenperStr: String? = null
    var selLangStr: String? = null
    var agentype: Spinner? = null
    var subcat_spinner: Spinner? = null
    var video_catgryy: Spinner? = null
    var sel_visiblity: Spinner? = null
    private var catresult: JSONArray? = null
    private var vidcatresult: JSONArray? = null
    private var subcatresult: JSONArray? = null
    var categry: ArrayList<String>? = null
    var vidcategry: ArrayList<String>? = null
    var subcategry: ArrayList<String>? = null
    var stateiid: String? = null
    var vidcatgryId: String? = null
    var subcattiid: String? = null
    var file_img: File? = null
    var file_vid: File? = null
    var file_img_thmb: File? = null
    var file_vid_pv: File? = null
    var bitmap: Bitmap? = null
    var sel_imgg: MultipartBody.Part? = null
    var sel_vidd: MultipartBody.Part? = null
    var sel_imgg_thmb: MultipartBody.Part? = null
    var sel_vidd_pv: MultipartBody.Part? = null
    var manager: SessionManager? = null
    var cutiorVid = arrayOf("Select", "cuties", "video")
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

    //    String[] cutiorVid = {"video"};
    var sel_visiblity_str = arrayOf("Public", "Super Supported")

    //    String[] sel_vidd_catgry = {"Select", "Music", "Gaming", "Sports", "Films", "News", "Fashion", "Beauty", "Learning"};
    var dataAdapter1: ArrayAdapter<String>? = null
    var dataAdapter_sel_vc: ArrayAdapter<String>? = null
    var dataAdapter_vid_cat: ArrayAdapter<String>? = null
    var ataAdapter_sel_visblt: ArrayAdapter<String>? = null
    var ataAdapter_sel_eightenper: ArrayAdapter<String>? = null
    var ataAdapter_sel_lng: ArrayAdapter<String>? = null

    //    Bengali,Gujarati,Kashmiri,
    var ll_vid_pv: LinearLayout? = null
    var progressBar: ProgressDialog? = null
    private var progressBarStatus = 10
    private val progressBarHandler = Handler()
    private var fileSize: Long = 1000
    var mVideo: String? = null

    //    int status = 0;
    //    Handler handler = new Handler();
    //    ProgressDialog progressDialog;
    //    private int progressStatus = 0;
    //    private Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)
        context = this
        manager = SessionManager()
        val intent: Intent = intent
        path = intent.getStringExtra("key").toString()
        Log.d("TAG", "path:$path")
        token = manager!!.getPreferences(this, Constants.USER_TOKEN_LRN)
        progressBar = ProgressDialog(context)
        //        progressDialog = new ProgressDialog(this);
        progrees_vid = findViewById(R.id.progrees_vid)
        vid_prevw_imgg = findViewById(R.id.vid_prevw_imgg)
        vid_prevw_imgg_btn = findViewById(R.id.vid_prevw_imgg_btn)
        upld_videoo = findViewById(R.id.upld_videoo)
        upld_videoo_pv = findViewById(R.id.upld_videoo_pv)
        submit_videoo = findViewById<Button>(R.id.submit_videoo)
        vid_titlee = findViewById<EditText>(R.id.vid_titlee)
        vid_descc = findViewById<EditText>(R.id.vid_descc)
        vid_upld_view = findViewById<VideoView>(R.id.vid_upld_view)
        sel_cuti_vid = findViewById<Spinner>(R.id.sel_cuti_vid)
        sel_vid_cat = findViewById<Spinner>(R.id.sel_vid_cat)
        video_catgryy = findViewById<Spinner>(R.id.video_catgryy)
        sel_visiblity = findViewById<Spinner>(R.id.sel_visiblity)
        ll_vid_pv = findViewById<LinearLayout>(R.id.ll_vid_pv)
        //        vid_upld_view_pv = findViewById(R.id.vid_upld_view_pv);
        keywords_vid = findViewById<EditText>(R.id.keywords_vid)
        sel_eightenper = findViewById<Spinner>(R.id.sel_eightenper)
        sel_language = findViewById<Spinner>(R.id.sel_language)
        agentype = findViewById<Spinner>(R.id.agenttype_spinner)
        subcat_spinner = findViewById<Spinner>(R.id.subcat_spinner)
        dataAdapter1 =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, cutiorVid)
        dataAdapter1?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_cuti_vid?.adapter = dataAdapter1
        //
//        dataAdapter_sel_vc = new ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, sel_cid_catt);
//        dataAdapter_sel_vc.setDropDownViewResource(R.layout.custom_spiner_layout);
//        sel_vid_cat.setAdapter(dataAdapter_sel_vc);

//        dataAdapter_vid_cat = new ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, sel_vidd_catgry);
//        dataAdapter_vid_cat.setDropDownViewResource(R.layout.custom_spiner_layout);
//        video_catgryy.setAdapter(dataAdapter_vid_cat);

        ataAdapter_sel_visblt = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_visiblity_str
        )
        ataAdapter_sel_visblt?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_visiblity?.adapter = ataAdapter_sel_visblt
        ataAdapter_sel_eightenper = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_eightenperstr
        )
        ataAdapter_sel_eightenper?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_eightenper?.adapter = ataAdapter_sel_eightenper
        ataAdapter_sel_lng = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_langstr
        )
        ataAdapter_sel_lng?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_language?.adapter = ataAdapter_sel_lng
        categry = ArrayList()
        subcategry = ArrayList()
        vidcategry = ArrayList()

//        mVideo = getIntent().getStringExtra(EXTRA_VIDEO);
        video_catgryy?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                vidcatgryId = GetVidcatid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        sel_visiblity?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    ll_vid_pv?.visibility = View.GONE
                } else {
                    ll_vid_pv?.visibility=(View.GONE)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
    //                            singleSpinnerSearch.setTitle("Select City");
            }
        }
        agentype?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                stateiid = Getcatid(position)
                getSubCData(stateiid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
    //                            singleSpinnerSearch.setTitle("Select City");
            }
        }
        subcat_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                subcattiid = Getsubcatid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
    //                            singleSpinnerSearch.setTitle("Select City");
            }
        }



        ctgryData()
        vidCtgryData()
        vid_descc?.setOnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.id == R.id.vid_descc) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        }
        upld_videoo!!.setOnClickListener { showPictureDialog() }

        vid_prevw_imgg_btn!!.setOnClickListener { showPictureDialogImg() }
        submit_videoo!!.setOnClickListener { checkvalidation() }
        checkAndRequestPermissions()
    }

    private fun checkvalidation() {
        vid_titlee_str = vid_titlee?.text?.toString()
        vid_descc_str = vid_descc?.text?.toString()
        keywords_vid_str = keywords_vid?.text?.toString()
        sel_cuti_vidStr = sel_cuti_vid?.selectedItem?.toString()
        video_catgryyStr = video_catgryy?.selectedItem?.toString()
        sel_visiblityStr = sel_visiblity?.selectedItem?.toString()
        sel_eightenperStr = sel_eightenper?.selectedItem?.toString()
        selLangStr = sel_language?.selectedItem?.toString()

        selectedVideoPath = path
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
        } else if (selectedVideoPath == null || selectedVideoPath!!.isEmpty()) {
            Toast.makeText(context, "Video is empty!!", Toast.LENGTH_SHORT).show()
        } else {
            submit_videoodde()
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems = arrayOf(
            "Select video from gallery",
            "Record video from camera"
        )
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseVideoFromGallary()
                1 -> takeVideoFromCamera()
            }
        }
        pictureDialog.show()
    }


    private fun showPictureDialogImg() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems = arrayOf(
            "Select Image",
            "Capture Image"
        )
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseImgFromGallary()
                1 -> takeImgromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takeImgromCamera() {
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, CAPTURE_REQUEST_CODE)
    }

    private fun chooseImgFromGallary() {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, SELECT_REQUEST_CODE)

    }

    fun chooseVideoFromGallary() {

        val intent: Intent
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        } else {
            intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI)
        }
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("return-data", true)
        startActivityForResult(Intent.createChooser(intent, "Select Video"), GALLERY)
        //
    }

    private fun takeVideoFromCamera() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    vid_prevw_imgg!!.setImageBitmap(bitmap)
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
                                vid_prevw_imgg!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
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

            GALLERY -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val contentURI: Uri? = data.data
                    //
                    try {
                        selectedVideoPath = contentURI?.let { getPath(context, it) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    vid_upld_view?.setVideoURI(Uri.parse(path))
                    vid_upld_view?.requestFocus()
                    vid_upld_view?.start()
                }
            }

            CAMERA -> {

//                if (resultCode == RESULT_OK && data != null) {
                val contentURI: Uri? = data?.data
                try {
                    selectedVideoPath = contentURI?.let { getPath(context, it) }
                    //                    selectedVideoPath =mVideo;
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                vid_upld_view?.setVideoURI(contentURI)
                vid_upld_view?.requestFocus()
                vid_upld_view?.start()
            }
        }
    }

    private fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
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

    //    =======================
    //for video important end
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeDemoActivityCtgry::class.java))
    }

    fun submit_videoodde() {

       try {
            vid_titlee_str = vid_titlee?.text?.toString()
            vid_descc_str = vid_descc?.text?.toString()
            keywords_vid_str = keywords_vid?.text?.toString()
            sel_cuti_vidStr = sel_cuti_vid?.selectedItem?.toString()
            video_catgryyStr = video_catgryy?.selectedItem?.toString()

            if (sel_visiblityStr == "Public") {
                sel_visiblityStr = "N"
            } else if (sel_visiblityStr == "Super Support") {
                sel_visiblityStr = "Y"
            }
            map = HashMap<String, RequestBody>()
            map!!["title"] = vid_titlee_str?.let { RequestBody.create(MediaType.parse("text/plain"), it) }!!
            map!!["description"] = vid_descc_str?.let { RequestBody.create(MediaType.parse("text/plain"), it) }!!
            map!!["type"] = sel_cuti_vidStr?.let { RequestBody.create(MediaType.parse("text/plain"), it) }!!
            map!!["keywords"] =keywords_vid_str?.let{ RequestBody.create(MediaType.parse("text/plain"), it)}!!
            map!!["visible"] =sel_visiblityStr?.let{ RequestBody.create(MediaType.parse("text/plain"), it)}!!
            map!!["video_category"] =vidcatgryId?.let{ RequestBody.create(MediaType.parse("text/plain"), it)}!!
            map!!["category"] =stateiid?.let{ RequestBody.create(MediaType.parse("text/plain"), it)}!!
            map!!["sub_category"] =subcattiid?.let{ RequestBody.create(MediaType.parse("text/plain"), it)}!!

            file_img = imgPath?.let { File(it) }
            file_vid = selectedVideoPath?.let { File(it) }
            val reqFile_img: RequestBody? =
                file_img?.let { RequestBody.create(MediaType.parse("multipart/form-data"), it) }
            val reqFile_vid: RequestBody? =
                file_vid?.let { RequestBody.create(MediaType.parse("video/*"), it) }
            sel_imgg =
                reqFile_img?.let { MultipartBody.Part.createFormData("preview_image", imgPath, it) }
            sel_vidd =
                reqFile_vid?.let {
                    MultipartBody.Part.createFormData("video", selectedVideoPath,
                        it
                    )
                }

            progressBar = ProgressDialog(context)
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
                    progressBarHandler.post { progressBar!!.setProgress(progressBarStatus) }
                }
            }.start()
           RetrofitClient.getClient().upload_viddnew(
                map,
                sel_imgg,
                sel_vidd,
                "application/json", "Bearer "
                        + token
            )
                ?.enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
                                       override fun onResponse(
                        call: Call<String?>,
                        response: retrofit2.Response<String?>
                    ) {

                        try {
                            val res  = response.body()?.toString()
                            Log.d("TAG", "onResponse: $res")

                            if (res != null) {
                                if (res.contains("1")) {
                                    Toast.makeText(
                                        context,
                                        "Your video will be visible after approval!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(Intent(context, HomeDemoActivityCtgry::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Your video will be visible after approval!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
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


    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        //        int locationPermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_COARSE_LOCATION);
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
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }

    //    =================================================
    //    vid category start

    class CustomStringRequest(
        url: String,
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener,
        private val token: String
    ) : StringRequest(url, listener, errorListener) {
        override fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            headers["Accept"] = "application/json"
            headers["Authorization"] = "Bearer $token"
            return headers
        }
    }

    // Usage example:
    private fun vidCtgryData() {
        val url: String = Configs.VID_CATGRY_URL
        val stringRequest = token?.let {
            CustomStringRequest(
                url,
                { response: String? ->
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

    //                blur_reg1.visibility = View.GONE;
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { },
                it
            )
        }
        if (stringRequest != null) {
            stringRequest.retryPolicy = DefaultRetryPolicy(
                Configs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        }

        //Creating a request queue
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        //Adding request to the queue
        requestQueue.add(stringRequest)
    }


    private fun GetVidcatid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = vidcatresult?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_VIDCTRGYID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getVidCtgry(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            vidcategry = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
                        vidcategry!!.add(json.getString(Configs.KEY_COUNTRY_NAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until vidcategry!!.size) {
                            val countryName = json.getString(Configs.KEY_COUNTRY_NAME)
                            vidcategry!!.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, vidcategry!!)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            video_catgryy?.adapter = dataAdapter5

//            val citysreeer = preferences?.getString("countrysel", "")
//            if (!citysreeer.equals("", ignoreCase = true)) {
//                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
//                video_catgryy?.setSelection(spinnerPositioncity)
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun ctgryData() {
        val url = Configs.CATGRY_URL
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    // Parsing the fetched JSON String to a JSON Object
                    val j = JSONObject(response)
                    // Storing the array of JSON String to our JSON Array
                    catresult = j.getJSONArray(Configs.JSON_CTRGYARRAY)
                    // Calling the method to process the country data
                    getCtgry(catresult)
//                blur_reg1.visibility = View.GONE;
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> // Error occurred
                error.printStackTrace()
            })

        // Setting up retry policy for the request
        stringRequest.retryPolicy = DefaultRetryPolicy(
            Configs.MY_SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Creating a request queue
        val requestQueue = Volley.newRequestQueue(this)
        // Adding the request to the queue
        requestQueue.add(stringRequest)
    }

    private fun Getcatid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = catresult?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_CTRGYID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getCtgry(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            categry = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
                        categry?.add(json.getString(Configs.KEY_CTRGYNAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until categry!!.size) {
                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
                            categry!!.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, categry!!)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            agentype?.adapter = dataAdapter5

//            val citysreeer = preferences?.getString("countrysel", "")
//            if (!citysreeer.equals("", ignoreCase = true)) {
//                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
//                agentype?.setSelection(spinnerPositioncity)
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun getSubCData(cattiid: String?) {
        val url = Configs.SUBCATGRY_URL + "/" +  cattiid
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    // Parsing the fetched JSON String to a JSON Object
                    val j = JSONObject(response)
                    // Storing the array of JSON String to our JSON Array
                    subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
                    // Calling the method to process the country data
                    getSubcatgry(subcatresult)
//                blur_reg1.visibility = View.GONE;
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> // Error occurred
                error.printStackTrace()
            })

        // Setting up retry policy for the request
        stringRequest.retryPolicy = DefaultRetryPolicy(
            Configs.MY_SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Creating a request queue
        val requestQueue = Volley.newRequestQueue(this)
        // Adding the request to the queue
        requestQueue.add(stringRequest)
    }

    private fun Getsubcatid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = subcatresult?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_SUBCTRGYID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getSubcatgry(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            subcategry = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
                        subcategry!!.add(json.getString(Configs.KEY_SUBCTRGYNAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until subcategry!!.size) {
                            val countryName = json.getString(Configs.KEY_SUBCTRGYNAME)
                            subcategry!!.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, subcategry!!)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            subcat_spinner?.adapter = dataAdapter5

//            val citysreeer = preferences?.getString("statesel", "")
//            if (!citysreeer.equals("", ignoreCase = true)) {
//                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
//                stateSpinnerSearch?.setSelection(spinnerPositioncity)
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //    vid category end
    //    =================================================

//    private fun getSubCData(cattiid: String?) {
////        blur_reg1.visibility = View.VISIBLE;
//        // Log.e("stateiid",stateiid);
//        val url: String = Configs.SUBCATGRY_URL + "/" + cattiid
//        val stringRequest = StringRequest(url, Response.Listener<String?> { response ->
//            var j: JSONObject? = null
//            try {
//                //Parsing the fetched Json String to JSON Object
//                j = JSONObject(response)
//                //Storing the Array of JSON String to our JSON Array
//                subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
//                //Calling method getStudents to get the students from the JSON Array
//                getSubcatgry(subcatresult)
//                //                    blur_reg1.visibility = View.GONE;
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//        },
//            Response.ErrorListener {
//                //                        if (error instanceof TimeoutError) {
////                            Toast.makeText(SignupActivity.this,"Server Busy",Toast.LENGTH_LONG).show();
////                        } else if (error instanceof NoConnectionError) {
////                            Toast.makeText(SignupActivity.this, "No Connection", Toast.LENGTH_LONG).show();
////                        } else if (error instanceof AuthFailureError) {
////                            Toast.makeText(SignupActivity.this,"Auth Failure",Toast.LENGTH_LONG).show();
////                        } else if (error instanceof ServerError) {
////                            Toast.makeText(SignupActivity.this,"Server Error",Toast.LENGTH_LONG).show();
////                        } else if (error instanceof NetworkError) {
////                            Toast.makeText(SignupActivity.this,"Network Error",Toast.LENGTH_LONG).show();
////                        } else if (error instanceof ParseError) {
////                            Toast.makeText(SignupActivity.this,"Parse Error",Toast.LENGTH_LONG).show();
////                        }
////                        blur_reg1.visibility = View.GONE;
//            })
//        stringRequest.setRetryPolicy(
//            DefaultRetryPolicy(
//                Configs.MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//        )
//        //Creating a request queue
//        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//        //Adding request to the queue
//        requestQueue.add<String>(stringRequest)
//    }
//
//    private fun Getsubcatid(position: Int): String {
//        var sid = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject = subcatresult.getJSONObject(position)
//
//            //Fetching id from that object
//            sid = json.getString(Configs.KEY_SUBCTRGYID)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }
//
//    private fun getSubcatgry(j: JSONArray?) {
//        //Traversing through all the items in the json array
//        subcategry = ArrayList()
//        if (j != null) {
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//                    //Adding the name of the student to array list
//                    subcategry!!.add(json.getString(Configs.KEY_SUBCTRGYNAME))
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        //Setting adapter to show the items in the spinner
//        val spinnerArrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, R.layout.custom_spiner_layout, subcategry!!)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout)
//        subcat_spinner?.adapter = spinnerArrayAdapter
//        val spinnerPosition: Int = spinnerArrayAdapter.getPosition(subcattiid)
//        subcat_spinner?.setSelection(spinnerPosition)
//        //        blur_reg1.visibility = View.GONE;
//    }




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

    companion object {
        //    vid_upld_view_pv
        //    private static final String VIDEO_DIRECTORY = "/abhifilesss";
        private const val GALLERY = 3
        private const val CAMERA = 4
        private const val GALLERY_pv = 5
        private const val CAMERA_pv = 6
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 101
        private const val CAPTURE_REQUEST_CODE = 0
        private const val SELECT_REQUEST_CODE = 1
        const val EXTRA_VIDEO = "video"

        //    =======================
        //for video important start
        //
        fun getPath(context: Context?, uri: Uri): String? {
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

//    private void showPictureDialog_pv() {
//        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
//        pictureDialog.setTitle("NationLearns");
//        String[] pictureDialogItems = {
//                "Select video from gallery",
//                "Record video from camera"};
//        pictureDialog.setItems(pictureDialogItems,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                chooseVideoFromGallary_pv();
//                                break;
//                            case 1:
//                                takeVideoFromCamera_pv();
//                                break;
//                        }
//                    }
//                });
//        pictureDialog.show();
//    }

//    private void showProgressDialogWithTitle(String title,String substring) {
//        progressDialog.setTitle(title);
//        progressDialog.setMessage(substring);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setCancelable(false);
//        progressDialog.setMax(100);
//        progressDialog.show();
//
//        // Start Process Operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (progressStatus < 100) {
//                    try{
//                        // This is mock thread using sleep to show progress
//                        Thread.sleep(200);
//                        progressStatus += 5;
//                    } catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    // Change percentage in the progress bar
//                    handler.post(new Runnable() {
//                        public void run() {
//                            progressDialog.setProgress(progressStatus);
//                        }
//                    });
//                }
//                //hide Progressbar after finishing process
//                hideProgressDialogWithTitle();
//            }
//        }).start();
//
//    }
// Method to hide/ dismiss Progress bar
//    private void hideProgressDialogWithTitle() {
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.dismiss();
//    }
//    public void showDialog(Activity activity, String msg) {
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dialog_show_progress);
//
//        final ProgressBar text = (ProgressBar) dialog.findViewById(R.id.progress_horizontal);
//        final TextView text2 = dialog.findViewById(R.id.value123);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (status < 100) {
//
//                    status += 1;
//
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            text.setProgress(status);
//                            text2.setText(String.valueOf(status));
//
//                            if (status == 100) {
//                                dialog.dismiss();
//                            }
//                        }
//                    });
//                }
//            }
//        }).start();
//
//
//        dialog.show();
//
//        Window window = dialog.getWindow();
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        try {
//            submit_videoodde();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }