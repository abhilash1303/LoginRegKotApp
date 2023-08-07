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
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import de.hdodenhof.circleimageview.CircleImageView
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
import java.net.URISyntaxException

//import www.nationlearnsraha.com.mainpage.TUploadVidNewActivity;
class TCreatChanelActvty constructor() : AppCompatActivity() {
    var manager: SessionManager? = null
    var ch_name: EditText? = null
    var ch_desc: EditText? = null
    var ch_name_str: String? = null
    var ch_desc_str: String? = null
    var ivImage: CircleImageView? = null
    var photo_upload: Button? = null
    var edit_profile_pic: TextView? = null
    var ch_baner_pic: TextView? = null
    private val REQUEST_CAMERA: Int = 0
    private val REQUEST_CAMERA1: Int = 3
    private val SELECT_FILE: Int = 1
    private val SELECT_FILE1: Int = 2
    var token: String? = null
    var imgPath: String? = null
    var sel_visiblityStr: String? = null
    var imgPath_ban: String? = null
    var ch_ban_img: ImageView? = null
    var context: Context? = null
    var file_ban_img: File? = null
    var file_img: File? = null
    var sel_imgg: MultipartBody.Part? = null
    var sel_vidd: MultipartBody.Part? = null
    var sel_visiblity_str: Array<String> = arrayOf("Select", "My Channel", "My Business Channel")
    var sel_chnl_str: Array<String> = arrayOf("Select", "My Channel", "My Business Channel")
    var map: HashMap<String, RequestBody>? = null
    var ataAdapter_sel_visblt: ArrayAdapter<String>? = null
    var sel_visiblity: Spinner? = null
    var terms_promo: CheckBox? = null
    var cntry_result: JSONArray? = null
    var state_result: JSONArray? = null
    var city_results: JSONArray? = null
    var cntry: ArrayList<String> = ArrayList()
    var statee: ArrayList<String> = ArrayList()
    var cityy: ArrayList<String> = ArrayList()
    var countrySpinnerSearch: SearchableSpinner? = null
    var stateSpinnerSearch: SearchableSpinner? = null
    var citySpinnerSearch: SearchableSpinner? = null
    var country_id_str: String? = null
    var state_id_str: String? = null
    var city_id_str: String? = null
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_chanel_actvty)
        context = this@TCreatChanelActvty
        manager = SessionManager()
        token = manager?.getPreferences(this@TCreatChanelActvty, Constants.USER_TOKEN_LRN)
        ch_desc = findViewById<EditText>(R.id.ch_desc)
        ivImage = findViewById<CircleImageView>(R.id.ch_post_profile)
        edit_profile_pic = findViewById<TextView>(R.id.ch_edit_profile_pic)
        photo_upload = findViewById<Button>(R.id.ch_photo_upload)
        ch_name = findViewById<EditText>(R.id.ch_name)
        ch_baner_pic = findViewById<TextView>(R.id.ch_baner_pic)
        ch_ban_img = findViewById<ImageView>(R.id.ch_ban_img)
        terms_promo = findViewById<CheckBox>(R.id.terms_promo)
        sel_visiblity = findViewById<Spinner>(R.id.sel_visiblity)
        countrySpinnerSearch = findViewById(R.id.countrySpinnerSearch)
        stateSpinnerSearch = findViewById(R.id.stateSpinnerSearch)
        citySpinnerSearch = findViewById(R.id.citySpinnerSearch)
        ataAdapter_sel_visblt = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_visiblity_str
        )
        ataAdapter_sel_visblt!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        sel_visiblity!!.adapter = ataAdapter_sel_visblt
        ch_desc?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.id == R.id.ch_desc) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.parent
                        .requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })
        ch_baner_pic?.setOnClickListener(View.OnClickListener({ v: View? -> showPictureDialogImgBan() }))
        edit_profile_pic?.setOnClickListener(View.OnClickListener({ v: View? -> showPictureDialogImg() }))
        photo_upload!!.setOnClickListener(View.OnClickListener({ v: View? -> checkvalidation() }))
        countrySpinnerSearch?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                country_id_str = GetCntryid(position)
                getStatedata(country_id_str)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        stateSpinnerSearch?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                state_id_str = GetStateid(position)
                getCitydata(state_id_str)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        citySpinnerSearch?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                city_id_str = GetCityid(position)
            }

            public override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        cntrtyData
        checkAndRequestPermissions()
    }

    private fun checkvalidation() {
        ch_name_str = ch_name?.text.toString()
        ch_desc_str = ch_desc?.text.toString()
        sel_visiblityStr = sel_visiblity?.selectedItem.toString()
        if ((ch_name_str == "") || (ch_name_str!!.isEmpty()
                    ) || (ch_desc_str == "") || (ch_desc_str!!.isEmpty())
        ) {
            Toast.makeText(context, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if (imgPath == null || imgPath!!.isEmpty()) {
            Toast.makeText(context, "Image is empty!!", Toast.LENGTH_SHORT).show()
        } else if (imgPath_ban == null || imgPath_ban!!.isEmpty()) {
            Toast.makeText(context, "Banner Image is empty!!", Toast.LENGTH_SHORT).show()
        } else if (!terms_promo?.isChecked!!) {
            Toast.makeText(context, "Kindly Accept the terms and conditions.", Toast.LENGTH_SHORT)
                .show()
        } else if ((sel_visiblityStr == "") || (sel_visiblityStr!!.isEmpty()) || (sel_visiblityStr == "Select")) {
            Toast.makeText(context, "Kindly Select channel!", Toast.LENGTH_SHORT).show()
        } else {
            submit_chanel_info()
        }
    }

    fun submit_chanel_info() {
        val loading: ProgressDialog =
            ProgressDialog.show(this, "Uploading Channel Info...!!", "Please Wait..!", false, false)
        try {
            ch_name_str = ch_name?.text.toString()
            ch_desc_str = ch_desc?.text.toString()
            sel_visiblityStr = sel_visiblity?.selectedItem.toString()
            //            if (sel_visiblityStr.equals("My Channel")) {
//                sel_visiblityStr = "normal";
//            } else if (sel_visiblityStr.equals("My Business Channel")) {
//                sel_visiblityStr = "business";
//            }
            map = HashMap<String, RequestBody>()
            map!!.put("name", RequestBody.create(MediaType.parse("text/plain"), ch_name_str))
            map!!.put("description", RequestBody.create(MediaType.parse("text/plain"), ch_desc_str))
            map!!.put("City_name", RequestBody.create(MediaType.parse("text/plain"), city_id_str))
            map!!.put(
                "channel_type",
                RequestBody.create(MediaType.parse("text/plain"), sel_visiblityStr)
            )
            file_img = imgPath?.let { File(it) }
            file_ban_img = imgPath_ban?.let { File(it) }
            val reqFile_img: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file_img)
            val reqFile_img_bn: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file_ban_img)
            sel_imgg = MultipartBody.Part.createFormData("image", imgPath, reqFile_img)
            sel_vidd = MultipartBody.Part.createFormData("ban_image", imgPath_ban, reqFile_img_bn)
            RetrofitClient.getClient().tcreate_ch(
                map,
                sel_imgg,
                sel_vidd,
                "application/json", (
                        "Bearer "
                                + token)
            )
                ?.enqueue(object : GlobalCallback<String?>(ivImage) {
                 override   fun onResponse(call: Call<String?>, response: Response<String?>) {
                        loading.dismiss()
                        try {


//                                Intent intent11 = new Intent(TCreatChanelActvty.this, TUploadVidNewActivity.class);
//                                startActivity(intent11);
//
                            Toast.makeText(
                                context,
                                "Channel is created successfully!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()


//
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
            ContextCompat.checkSelfPermission(this@TCreatChanelActvty, Manifest.permission.CAMERA)
        //        int locationPermission = ContextCompat.checkSelfPermission(TCreatChanelActvty.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(TCreatChanelActvty.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@TCreatChanelActvty,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@TCreatChanelActvty,
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
                this@TCreatChanelActvty,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }

    private fun showPictureDialogImg() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> = arrayOf(
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
        val capture: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(capture, CAPTURE_REQUEST_CODE)
    }

    private fun chooseImgFromGallary() {
        val select: Intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(select, SELECT_REQUEST_CODE)
    }

    private fun showPictureDialogImgBan() {
        val pictureDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        pictureDialog.setTitle("NationLearns")
        val pictureDialogItems: Array<String> = arrayOf(
            "Select Banner Image",
            "Capture Image for Banner"
        )
        pictureDialog.setItems(pictureDialogItems,
            object : DialogInterface.OnClickListener {
                public override fun onClick(dialog: DialogInterface, which: Int) {
                    when (which) {
                        0 -> chooseImgFromGallaryban()
                        1 -> takeImgromCameraban()
                    }
                }
            })
        pictureDialog.show()
    }

    private fun takeImgromCameraban() {
        val capture: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(capture, CAPTURE_REQUEST_CODE_BAN)
    }

    private fun chooseImgFromGallaryban() {
        val select: Intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(select, SELECT_REQUEST_CODE_BAN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                    ivImage?.setImageBitmap(bitmap)
                    try {
                        imgPath = getPath(getImageUri(this@TCreatChanelActvty, bitmap))
                    } catch (e: URISyntaxException) {
                        e.printStackTrace()
                    }
                    //                    progressDialog.show();
//                    ImageUpload(bitmap);


//                    imgPath = FileUtils.getPath(TCreatChanelActvty.this, getImageUri(TCreatChanelActvty.this, bitmap));
                }
            }

            SELECT_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        val selectedImage: Uri? = data?.data
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
                                ivImage?.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath = getPath(
                                        getImageUri(
                                            this@TCreatChanelActvty,
                                            BitmapFactory.decodeFile(picturePath)
                                        )
                                    )
                                } catch (e: URISyntaxException) {
                                    e.printStackTrace()
                                }
                                cursor.close()
                            }
                        }


//                        Uri ImageUri = data.getData();
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
//                        ivImage.setImageBitmap(bitmap);
//                        imgPath = FileUtils.getPath(TCreatChanelActvty.this, getImageUri(TCreatChanelActvty.this, bitmap));
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            CAPTURE_REQUEST_CODE_BAN -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                    ch_ban_img!!.setImageBitmap(bitmap)
                    try {
                        imgPath_ban = getPath(getImageUri(this@TCreatChanelActvty, bitmap))
                    } catch (e: URISyntaxException) {
                        e.printStackTrace()
                    }


//                    progressDialog.show();
//                    ImageUpload(bitmap);


//                    imgPath_ban = FileUtils.getPath(TCreatChanelActvty.this, getImageUri(TCreatChanelActvty.this, bitmap));
                }
            }

            SELECT_REQUEST_CODE_BAN -> {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        val selectedImage: Uri? = data?.data
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
                                ch_ban_img!!.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath_ban = getPath(
                                        getImageUri(
                                            this@TCreatChanelActvty,
                                            BitmapFactory.decodeFile(picturePath)
                                        )
                                    )
                                } catch (e: URISyntaxException) {
                                    e.printStackTrace()
                                }
                                cursor.close()
                            }
                        }

//                        Uri ImageUri = data.getData();
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
//                        ch_ban_img.setImageBitmap(bitmap);
//                        imgPath_ban = FileUtils.getPath(TCreatChanelActvty.this, getImageUri(TCreatChanelActvty.this, bitmap));
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    @SuppressLint("Recycle")
    @Throws(URISyntaxException::class)
    fun getPath(uri: Uri): String? {
        var uri: Uri = uri
        val needToCheckUri: Boolean = Build.VERSION.SDK_INT >= 19
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(this@TCreatChanelActvty, uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split: Array<String> =
                    docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split.get(1)
            } else if (isDownloadsDocument(uri)) {
                val id: String = DocumentsContract.getDocumentId(uri)
                uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), id.toLong()
                )
            } else if (isMediaDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split: Array<String> =
                    docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type: String = split[0]
                if (("image" == type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if (("video" == type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if (("audio" == type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(
                    split[1]
                )
            }
        }
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection: Array<String> = arrayOf<String>(
                MediaStore.Images.Media.DATA
            )
            var cursor: Cursor? = null
            try {
                cursor = this@TCreatChanelActvty.contentResolver
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
            return uri.path
        }
        return null
    }

    fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
        val baos: ByteArrayOutputStream = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, baos)
        val path1: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "NLChannel",
            null
        )
        return Uri.parse(path1)
    }//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array
    //Calling method getStudents to get the students from the JSON Array
    //                    blur_reg1.visibility = View.GONE;
    //Creating a request queue
    //Adding request to the queue
    //country
    private val cntrtyData: Unit
        get() {
            try {
                val url: String = Configs.COUNTRY_URL_BL
                val stringRequest: StringRequest = StringRequest(url,
                    { response: String? ->
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)
                            //Storing the Array of JSON String to our JSON Array
                            cntry_result = j.getJSONArray(Configs.JSON_COUNTRY_ARRAY)
                            //Calling method getStudents to get the students from the JSON Array
                            getCntry(cntry_result)
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

    private fun GetCntryid(position: Int): String {
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject? = cntry_result?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_COUNTRY_ID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getCntry(j: JSONArray?) {
        try {
            //Traversing through all the items in the json array
            cntry = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        cntry.add(json.getString(Configs.KEY_COUNTRY_NAME))
//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in cntry.indices) {
//                            val h: KeyPairBoolData = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(cntry.get(k))
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until cntry.size) {
                            val countryName = json.getString(Configs.KEY_COUNTRY_NAME)
                            cntry.add(countryName)
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
                ArrayAdapter<String>(this@TCreatChanelActvty, R.layout.custom_spiner_layout, cntry)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            countrySpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //state
    private fun getStatedata(country_id_str: String?) {
        try {
            val url: String = Configs.STATE_URL_BL + country_id_str
            val stringRequest: StringRequest = StringRequest(url,
                { response: String? ->
                    var j: JSONObject? = null
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = JSONObject(response)
                        //Storing the Array of JSON String to our JSON Array
                        state_result = j.getJSONArray(Configs.JSON_STATE_ARRAY)
                        //Calling method getStudents to get the students from the JSON Array
                        getState(state_result)
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

    private fun GetStateid(position: Int): String {
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject ?= state_result?.getJSONObject(position)
            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_STATE_ID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getState(j: JSONArray?) {
        try {
            //Traversing through all the items in the json array
            statee = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        statee.add(json.getString(Configs.KEY_STATE_NAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until statee.size) {
                            val countryName = json.getString(Configs.KEY_STATE_NAME)
                            statee.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }

//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in statee.indices) {
//                            val h: KeyPairBoolData = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(statee.get(k))
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@TCreatChanelActvty, R.layout.custom_spiner_layout, statee)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            stateSpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //city
    private fun getCitydata(state_id_str: String?) {
        try {
            val url: String = Configs.CITY_URL_BL + state_id_str
            val stringRequest: StringRequest = StringRequest(url,
                { response: String? ->
                    var j: JSONObject? = null
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = JSONObject(response)
                        //Storing the Array of JSON String to our JSON Array
                        city_results = j.getJSONArray(Configs.JSON_CITY_ARRAY)
                        //Calling method getStudents to get the students from the JSON Array
                        getCity(city_results)
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

    private fun GetCityid(position: Int): String {
        var sid: String = ""
        try {
            //Getting object of given index
            val json: JSONObject ?= city_results?.getJSONObject(position)
            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CITY_ID)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getCity(j: JSONArray?) {
        try {
            //Traversing through all the items in the json array
            cityy = ArrayList()
            //            city.add("Search Category");
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        //Getting json object
                        val json: JSONObject = j.getJSONObject(i)
                        //Adding the name of the student to array list
                        cityy.add(json.getString(Configs.KEY_CITY_NAME))



                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until cityy.size) {
                            val countryName = json.getString(Configs.KEY_CITY_NAME)
                            cityy.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }

//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in cityy.indices) {
//                            val h: KeyPairBoolData = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(cityy.get(k))
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@TCreatChanelActvty, R.layout.custom_spiner_layout, cityy)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            citySpinnerSearch?.setAdapter(dataAdapter5)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected override fun onResume() {
        super.onResume()
    }

    companion object {
        val REQUEST_ID_MULTIPLE_PERMISSIONS: Int = 1
        private val CAPTURE_REQUEST_CODE: Int = 0
        private val SELECT_REQUEST_CODE: Int = 1
        private val CAPTURE_REQUEST_CODE_BAN: Int = 3
        private val SELECT_REQUEST_CODE_BAN: Int = 4

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        fun isExternalStorageDocument(uri: Uri): Boolean {
            return ("com.android.externalstorage.documents" == uri.getAuthority())
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        fun isDownloadsDocument(uri: Uri): Boolean {
            return ("com.android.providers.downloads.documents" == uri.getAuthority())
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        fun isMediaDocument(uri: Uri): Boolean {
            return ("com.android.providers.media.documents" == uri.getAuthority())
        }
    }
}