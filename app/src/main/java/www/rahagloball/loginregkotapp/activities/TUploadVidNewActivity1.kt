package www.rahagloball.loginregkotapp.activities

//import android.Manifest
//import android.content.Context
//import android.database.Cursor
//import android.graphics.Bitmap
//import android.net.Uri
//import android.os.Environment
//import android.os.Handler
//import android.util.Log
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import androidx.appcompat.app.AlertDialog
//import androidx.core.app.NotificationCompat
//import okhttp3.MediaType
//import retrofit2.Call
//import retrofit2.Response
//import java.io.ByteArrayOutputStream
//import java.io.File
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors


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
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
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
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class TUploadVidNewActivity1 constructor() : AppCompatActivity() {
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
    var selch_str: String? = null
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

    //    String[] cutiorVid = {"Select", "cuties", "video"};
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
    var cutiorVid: Array<String> = arrayOf("video")
    var sel_visiblity_str: Array<String> = arrayOf("Public", "Super Supported")
    var sel_chnl_str: Array<String> = arrayOf("Select", "My Channel", "My Business Channel")

    //    String[] sel_vidd_catgry = {"Select", "Music", "Gaming", "Sports", "Films", "News", "Fashion", "Beauty", "Learning"};
    var dataAdapter1: ArrayAdapter<String>? = null
    var ataAdapter_sel_visblt: ArrayAdapter<String>? = null
    var ataAdapter_sel_vh: ArrayAdapter<String>? = null
    var ataAdapter_sel_eightenper: ArrayAdapter<String>? = null
    var ataAdapter_sel_lng: ArrayAdapter<String>? = null
    var dataAdapter_sel_vc: ArrayAdapter<String>? = null
    var dataAdapter_vid_cat: ArrayAdapter<String>? = null

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
    var sel_ch: Spinner? = null
    private var executorService: ExecutorService? = null
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tupload_vid_new1)
        context = this@TUploadVidNewActivity1
        manager = SessionManager()
        token = manager?.getPreferences(this@TUploadVidNewActivity1, Constants.USER_TOKEN_LRN)
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
        dataAdapter1!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_cuti_vid!!.adapter = dataAdapter1

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
        ataAdapter_sel_visblt!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_visiblity!!.adapter =(ataAdapter_sel_visblt)
        ataAdapter_sel_vh = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_chnl_str
        )
        ataAdapter_sel_vh!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_ch?.adapter = ataAdapter_sel_vh
        ataAdapter_sel_eightenper = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_eightenperstr
        )
        ataAdapter_sel_eightenper?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_eightenper?.adapter =(ataAdapter_sel_eightenper)
        ataAdapter_sel_lng = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_langstr
        )
        ataAdapter_sel_lng?.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_language?.adapter =(ataAdapter_sel_lng)
        categry = ArrayList()
        subcategry = ArrayList()
        vidcategry = ArrayList()

//        mVideo = getIntent().getStringExtra(EXTRA_VIDEO);
        video_catgryy?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                vidcatgryId = GetVidcatid(position)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        sel_visiblity!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        }
        agentype?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                stateiid = Getcatid(position)
                getSubCData(stateiid)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {
                //                            singleSpinnerSearch.setTitle("Select City");
            }
        }
        subcat_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                subcattiid = Getsubcatid(position)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {
                //                            singleSpinnerSearch.setTitle("Select City");
            }
        }

//
//        add_bizcat.setOnClickListener(v -> {
//
//            Intent in = new Intent(AddbizCatActivity.this, BusinessRegister.class);
//            in.putExtra("catIdd", stateiid);
//            in.putExtra("subbcatIdd", subcattiid);
//            startActivity(in);
//
//        });
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
        upld_videoo!!.setOnClickListener(View.OnClickListener { v: View? -> showPictureDialog() })

//        upld_videoo_pv.setOnClickListener(v -> {
//
//            showPictureDialog_pv();
//
//        });
        vid_prevw_imgg_btn!!.setOnClickListener(View.OnClickListener { v: View? -> showPictureDialogImg() })
        submit_videoo!!.setOnClickListener(View.OnClickListener { v: View? -> checkvalidation() })
        checkAndRequestPermissions()
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
        if ((vid_titlee_str == "") || (vid_titlee_str!!.isEmpty()
                    ) || (vid_descc_str == "") || (vid_descc_str!!.isEmpty()
                    ) || (keywords_vid_str == "") || (keywords_vid_str!!.isEmpty())
        ) {
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
        } else if ((selch_str == "") || (selch_str!!.isEmpty()) || (selch_str == "Select")) {
            Toast.makeText(context, "Kindly Select channel!", Toast.LENGTH_SHORT).show()
        } else if (selectedVideoPath == null || selectedVideoPath!!.isEmpty()) {
            Toast.makeText(context, "Video is empty!!", Toast.LENGTH_SHORT).show()
        } else {
            setSubmit_videoodemo()
            //            submit_videoodde();
        }
    }

    private fun showPictureDialog() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> = arrayOf(
            "Select video from gallery",
            "Record video from camera"
        )
        pictureDialog.setItems(pictureDialogItems,
            object : DialogInterface.OnClickListener {
                public override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        0 -> chooseVideoFromGallary()
                        1 -> takeVideoFromCamera()
                    }
                }
            })
        pictureDialog.show()
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
    private fun showPictureDialogImg() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> = arrayOf(
            "Select Image",
            "Capture Image"
        )
        pictureDialog.setItems(pictureDialogItems,
            object : DialogInterface.OnClickListener {
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

//        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(capture, CAPTURE_REQUEST_CODE);
    }

    private fun chooseImgFromGallary() {
        val pickPhoto: Intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, SELECT_REQUEST_CODE)

//        Intent select = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(select, SELECT_REQUEST_CODE);
    }

    fun chooseVideoFromGallary() {

//        Intent intent;
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//        } else {
//            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
//        }
//        intent.setType("video/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra("return-data", true);
//        startActivityForResult(Intent.createChooser(intent, "Select Video"), GALLERY);
        val intent: Intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Video"), GALLERY)

//
    }

    private fun takeVideoFromCamera() {
//        Toast.makeText(context, "We are working on it!", Toast.LENGTH_SHORT).show();
        val intent: Intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA)
        }
    }

    //                    progressDialog.show();
    //                    ImageUpload(bitmap);
    //                    imgPath = FileUtils.getPath(TUploadVidNewActivity.this, getImageUri(TUploadVidNewActivity.this, bitmap));
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
                        imgPath = getPath(context, getImageUri(this@TUploadVidNewActivity1, bitmap))
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
                                        context,
                                        getImageUri(
                                            this@TUploadVidNewActivity1,
                                            BitmapFactory.decodeFile(picturePath)
                                        )
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                cursor.close()
                            }
                        }

//                        Uri ImageUri = data.getData();
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
//                        vid_prevw_imgg.setImageBitmap(bitmap);
//                        imgPath = FileUtils.getPath(TUploadVidNewActivity.this, getImageUri(TUploadVidNewActivity.this, bitmap));
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
                        //                        selectedVideoPath =mVideo;
//                        Toast.makeText(context, "tetsttt", Toast.LENGTH_LONG).show();
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

    //    =======================
    //for video important end
    public override fun onBackPressed() {
        try {
            // Call the method to upload the video
            setSubmit_videoodemo()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onBackPressed()

//        setSubmit_videoodemo();
//        startActivity(new Intent(TUploadVidNewActivity1.this, HomeDemoActivityCtgry.class));
    }

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
    fun setSubmit_videoodemo() {
        val NOTIFICATION_ID: Int = 1234 // unique ID for the notification
        executorService!!.execute(Runnable {
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this@TUploadVidNewActivity1, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_last_updated) // set a small icon for the notification
                    .setContentTitle("Uploading video") // set the title of the notification
                    .setContentText("Uploading video " + "all videos" + "...") // set the text of the notification
                    .setProgress(
                        100,
                        10,
                        true
                    ) // show a progress bar to indicate that the upload is in progress
                    .setPriority(NotificationCompat.PRIORITY_HIGH) // set the priority of the notification to low
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
                if ((selch_str == "My Channel")) {
                    selch_str = "normal"
                } else if ((selch_str == "My Business Channel")) {
                    selch_str = "business"
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
                map!!["channel_type"] = RequestBody.create(MediaType.parse("text/plain"), selch_str)
                file_img = File(imgPath)
                file_vid = File(selectedVideoPath)
                val reqFile_img: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file_img)
                val reqFile_vid: RequestBody =
                    RequestBody.create(MediaType.parse("video/*"), file_vid)
                sel_imgg = MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img)
                sel_vidd =
                    MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
                RetrofitClient.getClient().upload_viddnew(
                    map,
                    sel_imgg,
                    sel_vidd,
                    "application/json", (
                            "Bearer "
                                    + token)
                )
                    ?.enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
                      override  fun onResponse(call: Call<String?>?, response: Response<String?>) {
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
                                    this@TUploadVidNewActivity1,
                                    "Your video will be visible after approval!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@TUploadVidNewActivity1,
                                        HomeDemoActivityCtgry::class.java
                                    )
                                )
                                finish()
                            } else {
//                                    Toast.makeText(TUploadVidNewActivity1.this, "Your video will be visible after approval!!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(
                                    this@TUploadVidNewActivity1,
                                    "Failed",
                                    Toast.LENGTH_SHORT
                                ).show()
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

//    fun submit_videoodde() {
//
//
////        progrees_vid.visibility = View.VISIBLE;
//        try {
//            vid_titlee_str = vid_titlee?.text.toString()
//            vid_descc_str = vid_descc?.text.toString()
//            keywords_vid_str = keywords_vid?.text.toString()
//            sel_cuti_vidStr = sel_cuti_vid?.selectedItem.toString()
//            video_catgryyStr = video_catgryy?.selectedItem.toString()
//            //            sel_visiblityStr = sel_visiblity?.selectedItem.toString();
////
//            if ((sel_visiblityStr == "Public")) {
//                sel_visiblityStr = "N"
//            } else if ((sel_visiblityStr == "Super Support")) {
//                sel_visiblityStr = "Y"
//            }
//            map = HashMap<String, RequestBody>()
//            map!!.put("title", RequestBody.create(MediaType.parse("text/plain"), vid_titlee_str))
//            map!!.put(
//                "description",
//                RequestBody.create(MediaType.parse("text/plain"), vid_descc_str)
//            )
//            map!!.put("type", RequestBody.create(MediaType.parse("text/plain"), sel_cuti_vidStr))
//            map!!.put(
//                "keywords",
//                RequestBody.create(MediaType.parse("text/plain"), keywords_vid_str)
//            )
//            map!!.put(
//                "visible",
//                RequestBody.create(MediaType.parse("text/plain"), sel_visiblityStr)
//            )
//            map!!.put(
//                "video_category",
//                RequestBody.create(MediaType.parse("text/plain"), vidcatgryId)
//            )
//            map!!.put("category", RequestBody.create(MediaType.parse("text/plain"), stateiid))
//            map!!.put("sub_category", RequestBody.create(MediaType.parse("text/plain"), subcattiid))
//            file_img = File(imgPath)
//            //            file_vid = new File(selectedVideoPath);
//            file_vid = File(selectedVideoPath)
//            val reqFile_img: RequestBody =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file_img)
//            val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), file_vid)
//            //            ProgressRequestBody fileBody = new ProgressRequestBody(file_vid, "video/*", TUploadVidNewActivity.this);
//            sel_imgg = MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img)
//            //            if (mVideo != null) {
////                sel_vidd = MultipartBody.Part.createFormData("video", mVideo, reqFile_vid);
////                upld_videoo.visibility = View.GONE;
////            } else {
//            sel_vidd = MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
//
////            }
//            progressBar = ProgressDialog(context)
//            progressBar.setCancelable(true)
//            progressBar.setMessage("Video is Uploading, Please Wait.")
//            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
//            progressBar.setProgress(10)
//            progressBar.setCanceledOnTouchOutside(false)
//            progressBar.setMax(100)
//            progressBar.show()
//            //            //reset progress bar and filesize status
//            progressBarStatus = 10
//            fileSize = 1000
//            Thread(object : Runnable {
//                public override fun run() {
//                    while (progressBarStatus < 100) {
//                        // performing operation
//                        progressBarStatus = doOperation()
//                        try {
//                            Thread.sleep(1000)
//                        } catch (e: InterruptedException) {
//                            e.printStackTrace()
//                        }
//                        // Updating the progress bar
//                        progressBarHandler.post(object : Runnable {
//                            public override fun run() {
//                                progressBar.setProgress(progressBarStatus)
//                            }
//                        })
//                    }
//                }
//            }).start()
//
////            showProgressDialogWithTitle("Video is Uploading..","Please wait!");
//            RetrofitClient.getClient().upload_viddnew(
//                map,
//                sel_imgg,
//                sel_vidd,
//                "application/json", (
//                        "Bearer "
//                                + token)
//            )
//                .enqueue(object : GlobalCallback<String?>(vid_prevw_imgg) {
//                    fun onResponse(call: Call<String?>, response: Response<String>) {
//
////                            progrees_vid.visibility = View.GONE;
//
//
////                            hideProgressDialogWithTitle();
//                        try {
//                            val res: String ? = response.body()?.toString()
//
////                                String resscoe= String.valueOf(response.code());
//// & progressBarStatus == 100
//                            if (res.contains("1")) {
////                                if (res.contains("1")) {
//
////                                    showDialog(TUploadVidNewActivity.this,"Video is Uploading, Please wait..");
//                                Toast.makeText(
//                                    this@TUploadVidNewActivity1,
//                                    "Your video will be visible after approval!!",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                startActivity(
//                                    Intent(
//                                        this@TUploadVidNewActivity1,
//                                        HomeDemoActivityCtgry::class.java
//                                    )
//                                )
//                                finish()
//                            } else {
//                                Toast.makeText(
//                                    this@TUploadVidNewActivity1,
//                                    "Your video will be visible after approval!!",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                //                                    Toast.makeText(TUploadVidNewActivity1.this, "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (e: Exception) {
//                            Log.d("Exception", "|=>" + e.message)
//                        }
//                    }
//                })
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
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
    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivity1,
            Manifest.permission.CAMERA
        )
        //        int locationPermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(TUploadVidNewActivity1.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivity1,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@TUploadVidNewActivity1,
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
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this@TUploadVidNewActivity1,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array

    //Creating a request queue

    //Adding request to the queue
    //    =================================================
    //    vid category start
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
                    com.android.volley.Response.ErrorListener { }
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
                this@TUploadVidNewActivity1,
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
//            blur_reg1.visibility = View.VISIBLE;
    //    vid category end
    //    =================================================
    private val ctgryData: Unit
        private get() {
            try {

//            blur_reg1.visibility = View.VISIBLE;
                val url: String = Configs.CATGRY_URL
                val stringRequest =
                    StringRequest(url, { response: String? ->
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)

                            //Storing the Array of JSON String to our JSON Array
                            catresult = j.getJSONArray(Configs.JSON_CTRGYARRAY)

                            //Calling method getStudents to get the students from the JSON Array
                            getCtgry(catresult)

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

    private fun Getcatid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = catresult?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CTRGYID)
            }
            manager?.setPreferences(this@TUploadVidNewActivity1, Constants.USER_CATGRY, sid)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getCtgry(j: JSONArray?) {
        try {

            //Traversing through all the items in the json array
            categry = ArrayList()
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)

                        //Adding the name of the student to array list
                        categry!!.add(json.getString(Configs.KEY_CTRGYNAME))

//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in categry!!.indices) {
//                            val h = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(categry!![k])
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }

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
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@TUploadVidNewActivity1, R.layout.custom_spiner_layout_cat,
                    categry!!
                )
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
            agentype?.adapter = dataAdapter5
//            val spinnerPosition: Int = dataAdapter5.getPosition(cat_Idd)
//            agentype?.setSelection(spinnerPosition)

//            String language = preferences.getString("language", "");
//            if(!language.equalsIgnoreCase(""))
//            {
//                int spinnerPosition = arrayAdapter.getPosition(language);
//                spinner.setSelection(spinnerPosition);
//            }
//            String cat_prefs = manager?.getPreferences(TUploadVidNewActivity1.this, Constants.USER_CATGRY);
//            if (!cat_prefs.equalsIgnoreCase("")) {
//            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun getSubCData(cattiid: String?) {
//        blur_reg1.visibility = View.VISIBLE;
        val url: String = Configs.SUBCATGRY_URL + "/" + cattiid
        val stringRequest = StringRequest(url, { response ->
            var j: JSONObject? = null
            try {
                //Parsing the fetched Json String to JSON Object
                j = JSONObject(response)
                //Storing the Array of JSON String to our JSON Array
                subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
                //Calling method getStudents to get the students from the JSON Array
                getSubcatgry(subcatresult)
                //                    blur_reg1.visibility = View.GONE;
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        },
            {
                //                        if (error instanceof TimeoutError) {
//                            Toast.makeText(SignupActivity.this,"Server Busy",Toast.LENGTH_LONG).show();
//                        } else if (error instanceof NoConnectionError) {
//                            Toast.makeText(SignupActivity.this, "No Connection", Toast.LENGTH_LONG).show();
//                        } else if (error instanceof AuthFailureError) {
//                            Toast.makeText(SignupActivity.this,"Auth Failure",Toast.LENGTH_LONG).show();
//                        } else if (error instanceof ServerError) {
//                            Toast.makeText(SignupActivity.this,"Server Error",Toast.LENGTH_LONG).show();
//                        } else if (error instanceof NetworkError) {
//                            Toast.makeText(SignupActivity.this,"Network Error",Toast.LENGTH_LONG).show();
//                        } else if (error instanceof ParseError) {
//                            Toast.makeText(SignupActivity.this,"Parse Error",Toast.LENGTH_LONG).show();
//                        }
//                        blur_reg1.visibility = View.GONE;
            })
        stringRequest.retryPolicy = DefaultRetryPolicy(
            Configs.MY_SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        //Creating a request queue
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        //Adding request to the queue
        requestQueue.add<String>(stringRequest)
    }

    private fun Getsubcatid(position: Int): String {
        var sid = ""
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

    private fun getSubcatgry(j: JSONArray?) {
        //Traversing through all the items in the json array
        subcategry = ArrayList()
        if (j != null) {
            for (i in 0 until j.length()) {
                try {
                    //Getting json object
                    val json: JSONObject = j.getJSONObject(i)
                    //Adding the name of the student to array list
                    subcategry!!.add(json.getString(Configs.KEY_SUBCTRGYNAME))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        //Setting adapter to show the items in the spinner
        val spinnerArrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, subcategry!!)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
        subcat_spinner?.setAdapter(spinnerArrayAdapter)
        val spinnerPosition: Int = spinnerArrayAdapter.getPosition(subcattiid)
        subcat_spinner?.setSelection(spinnerPosition)
        //        blur_reg1.visibility = View.GONE;
    }
  

    //    @Override
    //    public void onProgressUpdate(int percentage) {
    //        progressBar.setProgress(percentage);
    //    }
    //
    //    @Override
    //    public void onError() {
    //
    //    }
    //
    //    @Override
    //    public void onFinish() {
    //        progressBar.setProgress(100);
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
        private val GALLERY: Int = 3
        private val CAMERA: Int = 4
        private val GALLERY_pv: Int = 5
        private val CAMERA_pv: Int = 6
        val REQUEST_ID_MULTIPLE_PERMISSIONS: Int = 101
        private val CAPTURE_REQUEST_CODE: Int = 0
        private val SELECT_REQUEST_CODE: Int = 1
        val EXTRA_VIDEO: String = "video"
        private val CHANNEL_ID: String = "upload_channel"

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
                    val split: Array<String> =
                        docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    val type: String = split.get(0)
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory()
                            .toString() + "/" + split.get(1)
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
                    val split: Array<String> =
                        docId.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    val type: String = split.get(0)
                    var contentUri: Uri? = null
                    if (("image" == type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if (("video" == type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if (("audio" == type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection: String = "_id=?"
                    val selectionArgs: Array<String> = arrayOf(
                        split.get(1)
                    )
                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }
            } else if ("content".equals(uri.getScheme(), ignoreCase = true)) {
                return getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.getScheme(), ignoreCase = true)) {
                return uri.getPath()
            }
            return null
        }

        fun getDataColumn(
            context: Context?, uri: Uri?, selection: String?,
            selectionArgs: Array<String>?
        ): String? {
            var cursor: Cursor? = null
            val column: String = "_data"
            val projection: Array<String> = arrayOf(
                column
            )
            try {
                cursor = context!!.getContentResolver().query(
                    (uri)!!, projection, selection, selectionArgs,
                    null
                )
                if (cursor != null && cursor.moveToFirst()) {
                    val column_index: Int = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(column_index)
                }
            } finally {
                if (cursor != null) cursor.close()
            }
            return null
        }

        fun isExternalStorageDocument(uri: Uri): Boolean {
            return ("com.android.externalstorage.documents" == uri.getAuthority())
        }

        fun isDownloadsDocument(uri: Uri): Boolean {
            return ("com.android.providers.downloads.documents" == uri.getAuthority())
        }

        fun isMediaDocument(uri: Uri): Boolean {
            return ("com.android.providers.media.documents" == uri.getAuthority())
        }
    }
}