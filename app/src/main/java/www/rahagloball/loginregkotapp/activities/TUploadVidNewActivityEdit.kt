package www.rahagloball.loginregkotapp.activities

//import okhttp3.MediaType
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.ContentUris
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
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
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
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
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSingle
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSinglePojo
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URISyntaxException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TUploadVidNewActivityEdit : AppCompatActivity() {
    var progrees_vid: RelativeLayout? = null
    var catgrySpinnerSearch: SearchableSpinner? = null
    var subcatgrySpinnerSearch: SearchableSpinner? = null
    var vid_prevw_imgg: ImageView? = null
    var vid_prevw_imgg_btn: Button? = null
    var upld_videoo: Button? = null
    var upld_videoo_pv: Button? = null
    var submit_videoo: Button? = null
    var vid_titlee: EditText? = null
    var vid_descc: EditText? = null
    var keywords_vid: EditText? = null
    var vid_upld_view: VideoView? = null
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
    var sel_ch: Spinner? = null
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
    var cutiorVid: Array<String> = arrayOf("Select", "cuties", "video")
    var sel_eightenperstr: Array<String> = arrayOf("Select", "Yes", "No")
    var sel_langstr: Array<String> = arrayOf(
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
    var sel_visiblity_str: Array<String> = arrayOf("Public", "Super Supported")

    //    String[] sel_vidd_catgry = {"Select", "Music", "Gaming", "Sports", "Films", "News", "Fashion", "Beauty", "Learning"};
    var dataAdapter1: ArrayAdapter<String>? = null
    var dataAdapter_sel_vc: ArrayAdapter<String>? = null
    var dataAdapter_vid_cat: ArrayAdapter<String>? = null
    var ataAdapter_sel_visblt: ArrayAdapter<String>? = null
    var ataAdapter_sel_eightenper: ArrayAdapter<String>? = null
    var ataAdapter_sel_lng: ArrayAdapter<String>? = null
    var ataAdapter_sel_vh: ArrayAdapter<String>? = null

    //    Bengali,Gujarati,Kashmiri,
    var ll_vid_pv: LinearLayout? = null
    var progressBar: ProgressDialog? = null
    private var progressBarStatus: Int = 10
    private val progressBarHandler: Handler = Handler()
    private var fileSize: Long = 1000
    var mVideo: String? = null

    //    int status = 0;
    //    Handler handler = new Handler();
    //    ProgressDialog progressDialog;
    //    private int progressStatus = 0;
    //    private Handler handler = new Handler();
    private val mMediaPlayer: MediaPlayer? = null
    var bundle: Bundle? = null
    var res1: String? = null
    private var executorService: ExecutorService? = null
    var sel_chnl_str: Array<String> = arrayOf("Select", "My Channel", "My Business Channel")

    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tupload_vid_new1)
        context = this@TUploadVidNewActivityEdit
        manager = SessionManager()
        token = manager?.getPreferences(this@TUploadVidNewActivityEdit, Constants.USER_TOKEN_LRN)
        progressBar = ProgressDialog(context)
        bundle = intent.extras
        res1 = bundle?.getString("vid_idddd")
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
        //        vid_upld_view_pv = findViewById(R.id.vid_upld_view_pv);
        keywords_vid = findViewById<EditText>(R.id.keywords_vid)
        sel_eightenper = findViewById<Spinner>(R.id.sel_eightenper)
        sel_language = findViewById<Spinner>(R.id.sel_language)
        sel_ch = findViewById<Spinner>(R.id.sel_ch)
        catgrySpinnerSearch = findViewById(R.id.catgrySpinnerSearch)
        subcatgrySpinnerSearch = findViewById(R.id.subcatgrySpinnerSearch)
        //        agentype = findViewById(R.id.agenttype_spinner);
//        subcat_spinner = findViewById(R.id.subcat_spinner);
        executorService = Executors.newSingleThreadExecutor()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel: NotificationChannel =
                NotificationChannel(CHANNEL_ID, "Uploads", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
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
        ataAdapter_sel_vh = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_chnl_str
        )
        ataAdapter_sel_vh?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_ch?.setAdapter(ataAdapter_sel_vh)
        categry = ArrayList()
        subcategry = ArrayList()
        vidcategry = ArrayList()
        video_catgryy?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                vidcatgryId = GetVidcatid(position)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        sel_visiblity?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    ll_vid_pv?.visibility = View.GONE
                } else {
                    ll_vid_pv?.visibility = View.GONE
                }
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {
//                            singleSpinnerSearch.setTitle("Select City");
            }
        })
        catgrySpinnerSearch?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                stateiid = GetCtgryid(position)
                getsubCtgryData(stateiid)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        subcatgrySpinnerSearch?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                subcattiid = GetsubCtgryid(position)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        ctgryData
        vidCtgryData
        vid_descc?.setOnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.getId() == R.id.vid_descc) {
                view.getParent().requestDisallowInterceptTouchEvent(true)
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.getParent()
                        .requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        }
        upld_videoo!!.setOnClickListener { v: View? -> showPictureDialog() }
        vid_prevw_imgg_btn!!.setOnClickListener { v: View? -> showPictureDialogImg() }
        submit_videoo!!.setOnClickListener { v: View? -> checkvalidation() }
        checkAndRequestPermissions()
        getmangavidedit()
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
        if ((vid_titlee_str == "") || (vid_titlee_str!!.isEmpty()) || (vid_descc_str == "") || (vid_descc_str!!.isEmpty()) || (keywords_vid_str == "") || (keywords_vid_str!!.isEmpty())) {
            Toast.makeText(context, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if ((sel_eightenperStr == "") || (sel_eightenperStr!!.isEmpty()) || (sel_eightenperStr == "Select")) {
            Toast.makeText(
                context,
                "Kindly Select if, video you are uploading is permissible under the age of 18!",
                Toast.LENGTH_SHORT
            ).show()
        } else if ((selLangStr == "") || (selLangStr!!.isEmpty()) || (selLangStr == "Select")) {
            Toast.makeText(context, "Kindly Select Language!", Toast.LENGTH_SHORT).show()
        } else if (imgPath == null || imgPath!!.isEmpty()) {
            Toast.makeText(context, "Image is empty!!", Toast.LENGTH_SHORT).show()
        } else if (selectedVideoPath == null || selectedVideoPath!!.isEmpty()) {
            Toast.makeText(context, "Video is empty!!", Toast.LENGTH_SHORT).show()
        } else {
            setSubmit_videoodemo()
        }
    }

    private fun showPictureDialog() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> =
            arrayOf("Select video from gallery", "Record video from camera")
        pictureDialog.setItems(pictureDialogItems, object : DialogInterface.OnClickListener {
            public override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    0 -> chooseVideoFromGallary()
                    1 -> takeVideoFromCamera()
                }
            }
        })
        pictureDialog.show()
    }

    private fun showPictureDialogImg() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> = arrayOf("Select Image", "Capture Image")
        pictureDialog.setItems(pictureDialogItems, object : DialogInterface.OnClickListener {
            public override fun onClick(dialog: DialogInterface, which: Int) {
                when (which) {
                    0 -> chooseImgFromGallary()
                    1 -> takeImgromCamera()
                }
            }
        })
        pictureDialog.show()
    }

    private fun takeImgromCamera() {
        val takePicture: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, CAPTURE_REQUEST_CODE)
    }

    private fun chooseImgFromGallary() {
        val pickPhoto: Intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, SELECT_REQUEST_CODE)
    }

    fun chooseVideoFromGallary() {
        val intent: Intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Video"), GALLERY)
    }

    private fun takeVideoFromCamera() {
//        Toast.makeText(context, "We are working on it!", Toast.LENGTH_SHORT).show();
        val intent: Intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap: Bitmap = data.extras?.get("data") as Bitmap
                    vid_prevw_imgg!!.setImageBitmap(bitmap)
                    try {
                        imgPath = getPath(getImageUri(this@TUploadVidNewActivityEdit, bitmap))
                        context?.let { Glide.with(it).load(imgPath).into(vid_prevw_imgg!!) }
                        file_img = File(imgPath)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            SELECT_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    try {
                        val selectedImage: Uri? = data.data
                        val filePathColumn: Array<String> =
                            arrayOf<String>(MediaStore.Images.Media.DATA)
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
                                val columnIndex: Int = cursor.getColumnIndex(filePathColumn.get(0))
                                val picturePath: String = cursor.getString(columnIndex)
                                vid_prevw_imgg!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath = getPath(
                                        getImageUri(
                                            this@TUploadVidNewActivityEdit,
                                            BitmapFactory.decodeFile(picturePath)
                                        )
                                    )
                                    context?.let {
                                        Glide.with(it).load(imgPath).into(vid_prevw_imgg!!)
                                    }
                                    file_img = File(imgPath)
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
                        selectedVideoPath = contentURI?.let { getPath(it) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    vid_upld_view?.setVideoURI(contentURI)
                    vid_upld_view?.requestFocus()
                    vid_upld_view?.start()
                }
            }

            CAMERA -> {

//                if (resultCode == RESULT_OK && data != null) {
                val contentURI: Uri? = data?.data
                try {
                    selectedVideoPath = contentURI?.let { getPath(it) }
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

    fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
        val baos: ByteArrayOutputStream = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, baos)
        val path1: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "NLUploadVideos",
            null
        )
        return Uri.parse(path1)
    }

    @Throws(URISyntaxException::class)
    fun getPath(uri: Uri): String? {
        var uri: Uri = uri
        val needToCheckUri: Boolean = Build.VERSION.SDK_INT >= 19
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(
                this@TUploadVidNewActivityEdit,
                uri
            )
        ) {
            if (isExternalStorageDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split: Array<String> =
                    docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split.get(1)
            } else if (isDownloadsDocument(uri)) {
                val id: String = DocumentsContract.getDocumentId(uri)
                uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), id.toLong()
                )
            } else if (isMediaDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split: Array<String> =
                    docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val type: String = split.get(0)
                if (("image" == type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if (("video" == type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if (("audio" == type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(
                    split.get(1)
                )
            }
        }
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection: Array<String> = arrayOf<String>(
                MediaStore.Images.Media.DATA
            )
            var cursor: Cursor? = null
            try {
                cursor = this@TUploadVidNewActivityEdit.contentResolver
                    .query(uri, projection, selection, selectionArgs, null)
                val column_index: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        return column_index?.let { cursor.getString(it) }
                    }
                }
            } catch (e: Exception) {
            }
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.getPath()
        }
        return null
    }

    public override fun onBackPressed() {
        super.onBackPressed()
        //        startActivity(new Intent(TUploadVidNewActivity1.this, HomeDemoActivityCtgry.class));
    }

    private fun getmangavidedit() {
        val url: String = Configs.BASE_URL2 + "my-managevideos/" + res1
        RetrofitClient.getClient().getMngVidLsit1(url, "application/json", "Bearer " + token)
            ?.enqueue(object : GlobalCallback<MngVidSinglePojo?>(vid_titlee) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<MngVidSinglePojo?>,
                    response: Response<MngVidSinglePojo?>
                ) {
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            //                                Log.e("res_mvdgfh", res_mv);
                            if ((res_mv == "200")) {
                                val myManageVid: MngVidSingle? = response.body()!!.data
                                if (myManageVid != null) {
                                    val vid_title_str: String? = myManageVid.title
                                    val vid_desc_str: String? = myManageVid.description
                                    val vid_kywrds_str: String? = myManageVid.keywords
                                    vid_titlee?.setText(vid_title_str)
                                    vid_descc?.setText(vid_desc_str)
                                    keywords_vid?.setText(vid_kywrds_str)
                                    val chnl_Imgstr: String =
                                        Configs.BASE_URL21 + "images/pool/preview/" + myManageVid.previewImage
                                    context?.let {
                                        vid_prevw_imgg?.let { it1 ->
                                            Glide.with(it).load(chnl_Imgstr).into(
                                                it1
                                            )
                                        }
                                    }
                                    val video_str: String =
                                        Configs.BASE_URL21 + "images/pool/video/" + myManageVid.video
                                    val video: Uri = Uri.parse(video_str)
                                    vid_upld_view?.setVideoURI(video)
                                    vid_upld_view?.setOnPreparedListener { mp: MediaPlayer ->
                                        mp.isLooping = true
                                        vid_upld_view?.start()
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun setSubmit_videoodemo() {
        val NOTIFICATION_ID: Int = 1234 // unique ID for the notification
        executorService!!.execute(Runnable {
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this@TUploadVidNewActivityEdit, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_last_updated) // set a small icon for the notification
                    .setContentTitle("Uploading video") // set the title of the notification
                    .setContentText("Uploading video " + "all videos" + "...") // set the text of the notification
                    .setProgress(
                        0,
                        0,
                        true
                    ) // show a progress bar to indicate that the upload is in progress
                    .setPriority(NotificationCompat.PRIORITY_LOW) // set the priority of the notification to low
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
            try {
                vid_titlee_str = vid_titlee?.text.toString()
                vid_descc_str = vid_descc?.text.toString()
                keywords_vid_str = keywords_vid?.text.toString()
                sel_cuti_vidStr = sel_cuti_vid?.selectedItem.toString()
                video_catgryyStr = video_catgryy?.selectedItem.toString()
                if ((sel_visiblityStr == "Public")) {
                    sel_visiblityStr = "N"
                } else if ((sel_visiblityStr == "Super Support")) {
                    sel_visiblityStr = "Y"
                }
                map = HashMap<String, RequestBody>()
                map!!["title"] = RequestBody.create(MediaType.parse("text/plain"), vid_titlee_str)
                map!!["description"] =
                    RequestBody.create(MediaType.parse("text/plain"), vid_descc_str)
                map!!["type"] = RequestBody.create(MediaType.parse("text/plain"), sel_cuti_vidStr)
                map!!["keywords"] =
                    RequestBody.create(MediaType.parse("text/plain"), keywords_vid_str)
                map!!["visible"] =
                    RequestBody.create(MediaType.parse("text/plain"), sel_visiblityStr)
                map!!["video_category"] =
                    RequestBody.create(MediaType.parse("text/plain"), vidcatgryId)
                map!!["category"] = RequestBody.create(MediaType.parse("text/plain"), stateiid)
                map!!["sub_category"] = RequestBody.create(MediaType.parse("text/plain"), subcattiid)
                if (file_img != null) sel_imgg = MultipartBody.Part.createFormData(
                    "preview_image", imgPath, RequestBody.create(
                        MediaType.parse("multipart/form-data"), file_img
                    )
                )
                file_vid = File(selectedVideoPath)
                val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), file_vid)
                sel_vidd = MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
                RetrofitClient.getClient()
                    .upload_viddnew(map, sel_imgg, sel_vidd, "application/json", "Bearer " + token)
                    ?.enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
                     override   fun onResponse(call: Call<String?>?, response: Response<String?>) {

//                        String res ? = response.body()?.toString();
//                        if (res.contains("1")) {
                            if (response.isSuccessful) {
                                notificationBuilder.setContentText("Video uploaded successfully")
                                    .setProgress(0, 0, false) // remove the progress bar
                                    .setAutoCancel(true) // automatically dismiss the notification when the user taps on it
                                notificationManager.notify(
                                    NOTIFICATION_ID,
                                    notificationBuilder.build()
                                )
                                Toast.makeText(
                                    this@TUploadVidNewActivityEdit,
                                    "Your video will be visible after approval!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@TUploadVidNewActivityEdit,
                                        HomeDemoActivityCtgry::class.java
                                    )
                                )
                                finish()
                            } else {
                                Toast.makeText(
                                    this@TUploadVidNewActivityEdit,
                                    "Your video will be visible after approval!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //                                    Toast.makeText(TUploadVidNewActivity1.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    
                    })
            } catch (e: Exception) {
                notificationBuilder.setContentText("Video upload failed")
                    .setProgress(0, 0, false) // remove the progress bar
                    .setAutoCancel(true) // automatically dismiss the notification when the user taps on it
                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
            }
        })
    }

    fun submit_videoodde() {


//        progrees_vid.visibility = View.VISIBLE;
        try {
            vid_titlee_str = vid_titlee?.text.toString()
            vid_descc_str = vid_descc?.text.toString()
            keywords_vid_str = keywords_vid?.text.toString()
            sel_cuti_vidStr = sel_cuti_vid?.selectedItem.toString()
            video_catgryyStr = video_catgryy?.selectedItem.toString()
            //            sel_visiblityStr = sel_visiblity?.selectedItem.toString();
//
            if ((sel_visiblityStr == "Public")) {
                sel_visiblityStr = "N"
            } else if ((sel_visiblityStr == "Super Support")) {
                sel_visiblityStr = "Y"
            }
            map = HashMap<String, RequestBody>()
            map!!["title"] = RequestBody.create(MediaType.parse("text/plain"), vid_titlee_str)
            map!!["description"] = RequestBody.create(MediaType.parse("text/plain"), vid_descc_str)
            map!!["type"] = RequestBody.create(MediaType.parse("text/plain"), sel_cuti_vidStr)
            map!!["keywords"] = RequestBody.create(MediaType.parse("text/plain"), keywords_vid_str)
            map!!["visible"] = RequestBody.create(MediaType.parse("text/plain"), sel_visiblityStr)
            map!!["video_category"] = RequestBody.create(MediaType.parse("text/plain"), vidcatgryId)
            map!!["category"] = RequestBody.create(MediaType.parse("text/plain"), stateiid)
            map!!["sub_category"] = RequestBody.create(MediaType.parse("text/plain"), subcattiid)
            file_img = File(imgPath)
            file_vid = File(selectedVideoPath)
            val reqFile_img: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file_img)
            val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), file_vid)

            sel_imgg = MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img)
            sel_vidd = MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
            progressBar = ProgressDialog(context)
            progressBar!!.setCancelable(true)
            progressBar!!.setMessage("Video is Uploading, Please Wait.")
            progressBar!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressBar!!.setProgress(10)
            progressBar!!.setCanceledOnTouchOutside(false)
            progressBar!!.setMax(100)
            progressBar!!.show()

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
            RetrofitClient.getClient()
                .upload_viddnew(map, sel_imgg, sel_vidd, "application/json", "Bearer " + token)
                ?.enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
                   override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        try {
                            val res: String? = response.body()?.toString()
                            if (res != null) {
                                if (res.contains("1")) {
                                    Toast.makeText(
                                        this@TUploadVidNewActivityEdit,
                                        "Your video will be visible after approval!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@TUploadVidNewActivityEdit,
                                            HomeDemoActivityCtgry::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@TUploadVidNewActivityEdit,
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
        val permissioncallcam: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivityEdit,
            Manifest.permission.CAMERA
        )
        //        int locationPermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivityEdit,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivityEdit,
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
                this@TUploadVidNewActivityEdit,
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
                val stringRequest: StringRequest = object : StringRequest(
                    url,
                    com.android.volley.Response.Listener<String> { response: String? ->
                        var j: JSONObject? = null
                        try {
                            // Parsing the fetched Json String to JSON Object
                            j = response?.let { JSONObject(it) }

                            // Storing the Array of JSON String to our JSON Array
                            if (j != null) {
                                vidcatresult = j.getJSONArray(Configs.JSON_VIDCTRGYARRAY)
                            }

                            // Calling method getStudents to get the students from the JSON Array
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

                // Creating a request queue
                val requestQueue: RequestQueue = Volley.newRequestQueue(this)

                // Adding request to the queue
                requestQueue.add<String>(stringRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


//    private val vidCtgryData:
//
//    //Adding request to the queue
//            Unit
//        private get() {
//            try {
//                progressBar?.show()
//                val url: String = Configs.VID_CATGRY_URL
//                val stringRequest: StringRequest = object : StringRequest(
//                    url,
//                    com.android.volley.Response.Listener<String> { response: String? ->
//                        var j: JSONObject? = null
//                        try {
//                            //Parsing the fetched Json String to JSON Object
//                            j = JSONObject(response)
//
//                            //Storing the Array of JSON String to our JSON Array
//                            vidcatresult = j.getJSONArray(Configs.JSON_VIDCTRGYARRAY)
//
//                            //Calling method getStudents to get the students from the JSON Array
//                            getVidCtgry(vidcatresult)
//                            progressBar?.cancel()
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    },
//                    com.android.volley.Response.ErrorListener { error: VolleyError? -> }
//                ) {
//                    val headers: Map<String, String>
//                        get() {
//                            val header: HashMap<String, String> = HashMap()
//                            header["Accept"] = "application/json"
//                            header["Authorization"] = "Bearer $token"
//                            return header
//                        }
//                }
//                stringRequest.retryPolicy = DefaultRetryPolicy(
//                    Configs.MY_SOCKET_TIMEOUT_MS,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//                )
//
//                //Creating a request queue
//                val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//                //Adding request to the queue
//                requestQueue.add<String>(stringRequest)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

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
//            video_catgryy.setAdapter(dataAdapter_vid_cat);
            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(
                this@TUploadVidNewActivityEdit,
                R.layout.custom_spiner_layout,
                vidcategry!!
            )
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            video_catgryy?.adapter = dataAdapter5
            //            int spinnerPosition = dataAdapter5.getPosition(stateiid);
//            agentype.setSelection(spinnerPosition);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }//Parsing the fetched Json String to JSON Object

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
                val stringRequest: StringRequest = StringRequest(url,
                    { response: String? ->
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)
                            //Storing the Array of JSON String to our JSON Array
                            catresult = j.getJSONArray(Configs.JSON_CTRGYARRAY)
                            //Calling method getStudents to get the students from the JSON Array
                            getCatgry(catresult)
                            //                    blur_reg1.visibility = View.GONE;
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    { error: VolleyError? -> }
                )
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
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject?= catresult?.getJSONObject(position)

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
            categry = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        categry!!.add(json.getString(Configs.KEY_CTRGYNAME))
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
            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(
                this@TUploadVidNewActivityEdit,
                R.layout.custom_spiner_layout,
                categry!!
            )
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
            val stringRequest: StringRequest = StringRequest(url,
                { response: String? ->
                    var j: JSONObject? = null
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = response?.let { JSONObject(it) }
                        //Storing the Array of JSON String to our JSON Array
                        if (j != null) {
                            subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
                        }
                        //Calling method getStudents to get the students from the JSON Array
                        getSubCatgry(subcatresult)
                        //                    blur_reg1.visibility = View.GONE;
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { error: VolleyError? -> }
            )
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
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject? = subcatresult?.getJSONObject(position)

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
            subcategry = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
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
            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(
                this@TUploadVidNewActivityEdit,
                R.layout.custom_spiner_layout,
                subcategry!!
            )
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            subcatgrySpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    companion object {
        private val GALLERY: Int = 3
        private val CAMERA: Int = 4
        private val GALLERY_pv: Int = 5
        private val CAMERA_pv: Int = 6
        val REQUEST_ID_MULTIPLE_PERMISSIONS: Int = 101
        private val CAPTURE_REQUEST_CODE: Int = 0
        private val SELECT_REQUEST_CODE: Int = 1
        val EXTRA_VIDEO: String = "video"
        private val CHANNEL_ID: String = "upload_channel"
        fun isExternalStorageDocument(uri: Uri): Boolean {
            return ("com.android.externalstorage.documents" == uri.authority)
        }

        fun isDownloadsDocument(uri: Uri): Boolean {
            return ("com.android.providers.downloads.documents" == uri.authority)
        }

        fun isMediaDocument(uri: Uri): Boolean {
            return ("com.android.providers.media.documents" == uri.authority)
        }
    }
}


//    private void getCtgryData() {
//
//        try {
//
////            blur_reg1.visibility = View.VISIBLE;
//
//            String url = Configs.CATGRY_URL;
//
//            StringRequest stringRequest = new StringRequest(url, response -> {
//                JSONObject j = null;
//                try {
//                    //Parsing the fetched Json String to JSON Object
//                    j = new JSONObject(response);
//
//                    //Storing the Array of JSON String to our JSON Array
//                    catresult = j.getJSONArray(Configs.JSON_CTRGYARRAY);
//
//                    //Calling method getStudents to get the students from the JSON Array
//                    getCtgry(catresult);
//
////                    blur_reg1.visibility = View.GONE;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }, error -> {
////                        if (error instanceof TimeoutError) {
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
//
////                        blur_reg1.visibility = View.GONE;
//            });
//
//            stringRequest.setRetryPolicy(new DefaultRetryPolicy(Configs.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//            //Creating a request queue
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//            //Adding request to the queue
//            requestQueue.add(stringRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String Getcatid(int position) {
//        String sid = "";
//        try {
//            //Getting object of given index
//            JSONObject json = catresult.getJSONObject(position);
//
//            //Fetching id from that object
//            sid = json.getString(Configs.KEY_CTRGYID);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //Returning the id
//        return sid;
//    }
//
//    private void getCtgry(JSONArray j) {
//
//        try {
//
//            //Traversing through all the items in the json array
//            categry = new ArrayList<String>();
//            for (int i = 0; i < j.length(); i++) {
//                try {
//                    //Getting json object
//                    JSONObject json = j.getJSONObject(i);
//
//                    //Adding the name of the student to array list
//
//                    categry.add(json.getString(Configs.KEY_CTRGYNAME));
//
//                    final List<KeyPairBoolData> listArray0 = new ArrayList<>();
//                    for (int k = 0; k < categry.size(); k++) {
//                        KeyPairBoolData h = new KeyPairBoolData();
//                        h.setId(k + 1);
//                        h.setName(categry.get(k));
//                        h.setSelected(false);
//                        listArray0.add(h);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(TUploadVidNewActivityEdit.this, R.layout.custom_spiner_layout, categry);
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout);
//            agentype.setAdapter(dataAdapter5);
//            int spinnerPosition = dataAdapter5.getPosition(stateiid);
//            agentype.setSelection(spinnerPosition);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getSubCData(String cattiid) {
////        blur_reg1.visibility = View.VISIBLE;
//        // Log.e("stateiid",stateiid);
//
//
//        String url = Configs.SUBCATGRY_URL + "/" + cattiid;
//        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject j = null;
//                try {
//                    //Parsing the fetched Json String to JSON Object
//                    j = new JSONObject(response);
//                    //Storing the Array of JSON String to our JSON Array
//                    subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY);
//                    //Calling method getStudents to get the students from the JSON Array
//                    getSubcatgry(subcatresult);
////                    blur_reg1.visibility = View.GONE;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                        if (error instanceof TimeoutError) {
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
//            }
//        });
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Configs.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //Creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        //Adding request to the queue
//        requestQueue.add(stringRequest);
//    }
//
//    private String Getsubcatid(int position) {
//        String sid = "";
//        try {
//            //Getting object of given index
//            JSONObject json = subcatresult.getJSONObject(position);
//
//            //Fetching id from that object
//            sid = json.getString(Configs.KEY_SUBCTRGYID);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //Returning the id
//        return sid;
//    }
//
//    private void getSubcatgry(JSONArray j) {
//        //Traversing through all the items in the json array
//        subcategry = new ArrayList<String>();
//        for (int i = 0; i < j.length(); i++) {
//            try {
//                //Getting json object
//                JSONObject json = j.getJSONObject(i);
//                //Adding the name of the student to array list
//                subcategry.add(json.getString(Configs.KEY_SUBCTRGYNAME));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        //Setting adapter to show the items in the spinner
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_spiner_layout, subcategry);
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout);
//        subcat_spinner.setAdapter(spinnerArrayAdapter);
//        int spinnerPosition = spinnerArrayAdapter.getPosition(subcattiid);
//        subcat_spinner.setSelection(spinnerPosition);
////        blur_reg1.visibility = View.GONE;
//
//    }