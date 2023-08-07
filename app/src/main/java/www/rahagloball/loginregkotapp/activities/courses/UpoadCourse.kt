package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.Manifest
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
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResult
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
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import java.io.ByteArrayOutputStream
import java.io.File

class UpoadCourse : AppCompatActivity() {
    private lateinit var crssellanguage: Spinner
    private lateinit var selcoursecat: Spinner
    private lateinit var selcoursesubcat: Spinner
    private lateinit var selcoursecatstr: String
    private lateinit var selcoursesubcatstr: String
    private lateinit var llfreepaid: LinearLayout
    private lateinit var crsdscntprice: EditText
    private lateinit var crsdiscprcnt: EditText
    private lateinit var crsprice: EditText
    private lateinit var crsdscntpricestr: String
    private lateinit var crspricestr: String
    private lateinit var selLangStr: String
    private lateinit var crsdurtndays: EditText
    private lateinit var crsdurtnmonths: EditText
    private lateinit var courselvltags: EditText
    private lateinit var courserp: EditText
    private lateinit var coursetags: EditText
    private lateinit var coursereqqw: EditText
    private lateinit var coursedescshrt: EditText
    private lateinit var coursedesc: EditText
    private lateinit var coursetitle: EditText
    private lateinit var crsdurtndaysstr: String
    private lateinit var crsdurtnmonthsstr: String
    private lateinit var courselvltagsstr: String
    private lateinit var courserpstr: String
    private lateinit var coursetagsstr: String
    private lateinit var coursereqqwstr: String
    private lateinit var coursedescshrtstr: String
    private lateinit var coursedescstr: String
    private lateinit var coursetitlestr: String
    private lateinit var vidupldview: VideoView
    private lateinit var selvideoo: TextView
    private lateinit var upldcourse: TextView
    private lateinit var vidprevwimggbtn: Button
    private lateinit var vidprevwimgg: ImageView
    private lateinit var mContect: Context
    private lateinit var selectedImageUri: Uri
    private lateinit var selectedVideoUri: Uri
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var videoPickerLauncher: ActivityResultLauncher<Intent>
    lateinit var token: String
    lateinit var manager: SessionManager
    var sel_langstr: Array<String> = arrayOf("Select", "Kannada", "Hindi", "English", "Spanish", "French")
    private lateinit var catresult: JSONArray
    private lateinit var subcatresult: JSONArray
    lateinit var categry: ArrayList<String>
    lateinit var subcategry: ArrayList<String>
    lateinit var stateiid: String
    lateinit var subcattiid: String
    lateinit var map: HashMap<String, RequestBody>
    lateinit var progressBar: ProgressDialog
    private var progressBarStatus: Int = 10
    private val progressBarHandler: Handler = Handler()
    private var fileSize: Long = 1000
    lateinit var fileimg: File
    lateinit var filevid: File
    lateinit var imgPath: String
    lateinit var selectedVideoPath: String
//    lateinit var progresss: ProgressBar
    lateinit var ataAdaptersellng: ArrayAdapter<String>
    lateinit var freeRadioButton: RadioButton
    lateinit var paidRadioButton: RadioButton
    lateinit var daysRadioButton: RadioButton
    lateinit var monthRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upoad_course)
        mContect = this@UpoadCourse
        manager = SessionManager()
        token = manager.getPreferences(this@UpoadCourse, Constants.USER_TOKEN_LRN)
        progressBar = ProgressDialog(mContect)
        selcoursecat = findViewById(R.id.sel_course_cat)
        selcoursesubcat = findViewById(R.id.sel_course_subcat)
        crsdscntprice = findViewById(R.id.crs_dscnt_price)
        crsdiscprcnt = findViewById(R.id.crs_disc_prcnt)
        crsprice = findViewById(R.id.crs_price)
        crsdurtndays = findViewById(R.id.crs_durtn_days)
        crsdurtnmonths = findViewById(R.id.crs_durtn_months)
        llfreepaid = findViewById(R.id.ll_free_paid)
        courselvltags = findViewById(R.id.course_lvl_tags)
        courserp = findViewById(R.id.course_rp)
        coursetags = findViewById(R.id.course_tags)
        coursereqqw = findViewById(R.id.course_reqqw)
        coursedescshrt = findViewById(R.id.course_desc_shrt)
        coursedesc = findViewById(R.id.course_desc)
        coursetitle = findViewById(R.id.course_title)
        crssellanguage = findViewById(R.id.crs_sel_language)
        vidupldview = findViewById(R.id.vid_upld_view)
        selvideoo = findViewById(R.id.sel_videoo)
        upldcourse = findViewById(R.id.upld_course)
        vidprevwimggbtn = findViewById(R.id.vid_prevw_imgg_btn)
        vidprevwimgg = findViewById(R.id.vid_prevw_imgg)
//        progresss = findViewById(R.id.progresss)

        freeRadioButton = findViewById(R.id.freeRadioButton)
        paidRadioButton = findViewById(R.id.paidRadioButton)
        daysRadioButton = findViewById(R.id.daysRadioButton)
        monthRadioButton = findViewById(R.id.monthRadioButton)


        categry = ArrayList()
        subcategry = ArrayList()
        ataAdaptersellng = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_langstr
        )
        ataAdaptersellng.setDropDownViewResource(R.layout.custom_spiner_layout)
        crssellanguage.adapter = ataAdaptersellng
        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val data: Intent? = result.data
//                        if (data != null) {
                    selectedImageUri = data?.data!!
//                        }
                    val filePathColumn: Array<String> =
                        arrayOf<String>(MediaStore.Images.Media.DATA)
//                        if (selectedImageUri != null) {
                    val cursor: Cursor? = contentResolver.query(
                        selectedImageUri,
                        filePathColumn,
                        null,
                        null,
                        null
                    )
                    if (cursor != null) {
                        cursor.moveToFirst()
                        val columnIndex: Int = cursor.getColumnIndex(filePathColumn.get(0))
                        val picturePath: String = cursor.getString(columnIndex)
                        vidprevwimgg.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                        try {
                            imgPath = getPath(
                                mContect,
                                getImageUri(this, BitmapFactory.decodeFile(picturePath))
                            ).toString()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        cursor.close()
                    }
//                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        videoPickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
//                            selectedVideoUri = videoUri;
                    selectedVideoUri = data.data!!
                    try {
                        selectedVideoPath = getPath(mContect, selectedVideoUri)!!
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    Log.d(TAG, "Selected Video URI: $selectedVideoUri")
                    vidupldview.setVideoURI(selectedVideoUri)
                    vidupldview.start()
                }
            }
        }
        vidprevwimggbtn.setOnClickListener { v: View? -> pickImage() }
        selvideoo.setOnClickListener { v: View? -> pickVideo() }

        selcoursecat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                stateiid = Getcatid(position)
                getSubCData(stateiid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        selcoursesubcat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                subcattiid = Getsubcatid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        ctgryData
        paidRadioButton.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                llfreepaid.visibility = View.VISIBLE
            } else {
                llfreepaid.visibility = View.GONE
            }
        }
        daysRadioButton.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                crsdurtndays.visibility = View.VISIBLE
                crsdurtnmonths.visibility = View.GONE
            } else {
                crsdurtndays.visibility = View.GONE
                crsdurtnmonths.visibility = View.VISIBLE
            }
        }
        crsprice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                calculateDiscountPrice()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        crsdiscprcnt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                calculateDiscountPrice()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        upldcourse.setOnClickListener{ v: View? -> checkvalidation() }
        checkAndRequestPermissions()
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

    private fun calculateDiscountPrice() {
        val originalPriceStr: String = crsprice.text.toString()
        val discountPercentStr: String = crsdiscprcnt.text.toString()
        if (!originalPriceStr.isEmpty() && !discountPercentStr.isEmpty()) {
            val originalPrice: Double = originalPriceStr.toDouble()
            val discountPercent: Double = discountPercentStr.toDouble()
            val discountAmount: Double = originalPrice * (discountPercent / 100)
            val discountPrice: Double = originalPrice - discountAmount
            crsdscntprice.setText(discountPrice.toString())
        } else {
            crsdscntprice.setText("")
        }
    }

    private fun checkvalidation() {
        coursetitlestr = coursetitle.text.toString()
        coursedescstr = coursedesc.text.toString()
        coursedescshrtstr = coursedescshrt.text.toString()
        coursereqqwstr = coursereqqw.text.toString()
        coursetagsstr = coursetags.text.toString()
        courserpstr = courserp.text.toString()
        courselvltagsstr = courselvltags.text.toString()
        crsdurtndaysstr = crsdurtndays.text.toString()
        crsdurtnmonthsstr = crsdurtnmonths.text.toString()
        selcoursecatstr = selcoursecat.selectedItem.toString()
        selcoursesubcatstr = selcoursesubcat.selectedItem.toString()
        selLangStr = crssellanguage.selectedItem.toString()
        if ((coursetitlestr == "") || (coursetitlestr.isEmpty()
                    ) || (coursedescstr == "") || (coursedescstr.isEmpty()
                    ) || (coursedescshrtstr == "") || (coursedescshrtstr.isEmpty()
                    ) || (coursereqqwstr == "") || (coursereqqwstr.isEmpty()
                    ) || (coursetagsstr == "") || (coursetagsstr.isEmpty()
                    ) || (courserpstr == "") || (courserpstr.isEmpty()
                    ) || (courselvltagsstr == "") || (courselvltagsstr.isEmpty())
        ) {
            Toast.makeText(mContect, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if ((selLangStr == "") || (selLangStr.isEmpty()) || (selLangStr == "Select")) {
            Toast.makeText(mContect, "Kindly Select Language!", Toast.LENGTH_SHORT).show()
        } else {
            upload_coursee()
        }
    }

    private fun upload_coursee() {
//        progresss.checkedRadioButtonId;
        try {
            if (selectedImageUri == null || selectedVideoUri == null) {
                Toast.makeText(this, "Please select both image and video", Toast.LENGTH_SHORT)
                    .show()
                return
            }
            coursetitlestr = coursetitle.text.toString()
            coursedescstr = coursedesc.text.toString()
            coursedescshrtstr = coursedescshrt.text.toString()
            coursereqqwstr = coursereqqw.text.toString()
            coursetagsstr = coursetags.text.toString()
            courserpstr = courserp.text.toString()
            courselvltagsstr = courselvltags.text.toString()
            crsdurtndaysstr = crsdurtndays.text.toString()
            crsdurtnmonthsstr = crsdurtnmonths.text.toString()
            crsdscntpricestr = crsdscntprice.text.toString()
            crspricestr = crsprice.text.toString()
            selLangStr = crssellanguage.selectedItem.toString()
            map = HashMap<String, RequestBody>()
            map["title"] = RequestBody.create(MediaType.parse("text/plain"), coursetitlestr)
            map["detail"] = RequestBody.create(MediaType.parse("text/plain"), coursedescstr)
            map["short_detail"] =
                RequestBody.create(MediaType.parse("text/plain"), coursedescshrtstr)
            map["requirement"] =
                RequestBody.create(MediaType.parse("text/plain"), coursereqqwstr)
            map["course_tags"] =
                RequestBody.create(MediaType.parse("text/plain"), coursetagsstr)
            map["refund_policy_id"] =
                RequestBody.create(MediaType.parse("text/plain"), courserpstr)
            map["level_tags"] =
                RequestBody.create(MediaType.parse("text/plain"), courselvltagsstr)
            map["category_id"] = RequestBody.create(MediaType.parse("text/plain"), stateiid)
            map["subcategory_id"] = RequestBody.create(MediaType.parse("text/plain"), subcattiid)
            map["language_id"] = RequestBody.create(MediaType.parse("text/plain"), selLangStr)


            imgPath = getRealPathFromUri(selectedImageUri)!!
            selectedVideoPath = getRealPathFromUri(selectedVideoUri)!!
            fileimg = File(imgPath)
            filevid = File(selectedVideoPath)
            val reqFile_img: RequestBody = RequestBody.create(MediaType.parse("image/*"), fileimg)
            val reqFile_vid: RequestBody = RequestBody.create(MediaType.parse("video/*"), filevid)
            val imagePart: MultipartBody.Part =
                MultipartBody.Part.createFormData("preview_image", imgPath, reqFile_img)
            val videoPart: MultipartBody.Part =
                MultipartBody.Part.createFormData("video", selectedVideoPath, reqFile_vid)
            var type = 0
            if (paidRadioButton.isChecked) {
                llfreepaid.visibility = View.VISIBLE
                freeRadioButton.isChecked = false // Ensure that the freeRadioButton is unchecked
                type = 1
                crspricestr = crsprice.text.toString()
                crsdscntpricestr = crsdscntprice.text.toString()
            } else {
                llfreepaid.visibility = View.GONE
            }
            map["type"] = RequestBody.create(MediaType.parse("text/plain"), type.toString())
            if (type == 1) {
                crspricestr = crsprice.text.toString()
                crsdscntpricestr = crsdscntprice.text.toString()
                map["price"] = RequestBody.create(MediaType.parse("text/plain"), crspricestr)
                map["discount_price"] =
                    RequestBody.create(MediaType.parse("text/plain"), crsdscntpricestr)
            } else {
                // If type is 0, set price and discount_price to "0"
                crspricestr = "0"
                crsdscntpricestr = "0"
                map["price"] = RequestBody.create(MediaType.parse("text/plain"), crspricestr)
                map["discount_price"] =
                    RequestBody.create(MediaType.parse("text/plain"), crsdscntpricestr)
            }
            if (daysRadioButton.isChecked) {
                crsdurtndays.visibility = View.VISIBLE
                crsdurtnmonths.visibility = View.GONE
                crsdurtndaysstr = crsdurtndays.text.toString()
                map["day"] = RequestBody.create(MediaType.parse("text/plain"), crsdurtndaysstr)
            } else if (monthRadioButton.isChecked) {
                crsdurtndays.visibility = View.GONE
                crsdurtnmonths.visibility = View.VISIBLE
                crsdurtnmonthsstr = crsdurtnmonths.text.toString()
                map.put(
                    "day",
                    RequestBody.create(MediaType.parse("text/plain"), crsdurtnmonthsstr)
                )
            }
            progressBar = ProgressDialog(mContect)
            progressBar.setCancelable(true)
            progressBar.setMessage("Course is Uploading, Please Wait.")
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressBar.progress = 10
            progressBar.setCanceledOnTouchOutside(false)
            progressBar.max = 100
            progressBar.show()
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
                    progressBarHandler.post { progressBar.progress = progressBarStatus }
                }
            }.start()
            RetrofitClient.getClient()
                .upload_course(map, imagePart, videoPart, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<String?>(vidprevwimgg) {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        try {
                            val tcrs_res: String? = response.body()
                            if (tcrs_res != null) {
                                if (tcrs_res.contains("1")) {
                                    Toast.makeText(
                                        this@UpoadCourse,
                                        "Your Course will be visible after approval!!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@UpoadCourse,
                                            HomeDemoActivityCtgry::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(this@UpoadCourse, "Failed", Toast.LENGTH_SHORT)
                                        .show()
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

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
        vidupldview.setVideoURI(selectedVideoUri)
        vidupldview.start()
    }

    private fun pickVideo() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        videoPickerLauncher.launch(intent)
        //        videoPickerLauncher.launch("video/*");
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val projection: Array<String> = arrayOf<String>(MediaStore.Images.Media.DATA)
        val cursor: Cursor = contentResolver.query(uri, projection, null, null, null)!!
        if (cursor.moveToFirst()) {
            val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val filePath: String = cursor.getString(columnIndex)
            cursor.close()
            return filePath
        }
        return uri.path
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

        } //end of while
        return 100
    } //end of doOperation

    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

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

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }
    private val ctgryData: Unit
        get() {
            try {

//            blur_reg1.visibility = View.VISIBLE;
                val url: String = Configs.CRS_CATGRY_URL
                val stringRequest = StringRequest(url,
                    { response: String? ->
                        val j: JSONObject
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)

                            //Storing the Array of JSON String to our JSON Array
                            catresult = j.getJSONArray(Configs.JSON_CRS_CTRGYARRAY)

                            //Calling method getStudents to get the students from the JSON Array
                            getCtgry(catresult)

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

    private fun Getcatid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = catresult.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CRS_CTRGYID)
            }
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
                        categry.add(json.getString(Configs.KEY_CRS_CTRGYNAME))

                        val listArray0 = ArrayList<KeyPairBoolData>()
                        for (k in 0 until categry.size) {
                            val countryName = json.getString(Configs.KEY_STATE_NAME)
                            categry.add(countryName)
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = countryName,
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }

//                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                        for (k in categry!!.indices) {
//                            val h: KeyPairBoolData = KeyPairBoolData()
//                            h.setId(k + 1)
//                            h.setName(categry!!.get(k))
//                            h.setSelected(false)
//                            listArray0.add(h)
//                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5: ArrayAdapter<String> =
                ArrayAdapter<String>(this@UpoadCourse, R.layout.custom_spiner_layout, categry)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            selcoursecat.adapter = dataAdapter5
            //            int spinnerPosition = dataAdapter5.getPosition(stateiid);
//            agentype.setSelection(spinnerPosition);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getSubCData(cattiid: String?) {
//        blur_reg1.visibility = View.VISIBLE;
        // Log.e("stateiid",stateiid);
        val url: String = Configs.CRS_SUBCATGRY_URL + "/" + cattiid
        val stringRequest =
            StringRequest(url, { response ->
                val j: JSONObject
                try {
                    //Parsing the fetched Json String to JSON Object
                    j = JSONObject(response)
                    //Storing the Array of JSON String to our JSON Array
                    subcatresult = j.getJSONArray(Configs.JSON_CRS_SUBCTRGYARRAY)
                    //Calling method getStudents to get the students from the JSON Array
                    getSubcatgry(subcatresult)
                    //                    blur_reg1.visibility = View.GONE;
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            ) {
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
    }

    private fun Getsubcatid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = subcatresult.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CRS_SUBCTRGYID)
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
                    subcategry.add(json.getString(Configs.KEY_CRS_SUBCTRGYNAME))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        //Setting adapter to show the items in the spinner
        val spinnerArrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout, subcategry)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout)
        selcoursesubcat.adapter = spinnerArrayAdapter
    }

    companion object {
        private val TAG: String = "UpoadCourse"
        const val REQUEST_ID_MULTIPLE_PERMISSIONS: Int = 101

        fun getPath(context: Context, uri: Uri?): String? {
            val isKitKat = true

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    val docId: String = DocumentsContract.getDocumentId(uri)
                    val split: Array<String> =
                        docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type: String = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory()
                            .toString() + "/" + split[1]
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
                        docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type: String = split[0]
                    var contentUri: Uri?=null
                    if (("image" == type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if (("video" == type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if (("audio" == type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection = "_id=?"
                    val selectionArgs: Array<String> = arrayOf(
                        split.get(1)
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
            var cursor: Cursor?  =null
            val column = "_data"
            val projection: Array<String> = arrayOf(
                column
            )
            try {
                cursor = context.contentResolver.query(
                    (uri)!!, projection, selection, selectionArgs,
                    null
                )
                if (cursor != null && cursor.moveToFirst()) {
                    val column_index: Int = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(column_index)
                }
            } finally {
                cursor?.close()
            }
            return null
        }

        fun isExternalStorageDocument(uri: Uri?): Boolean {
            return ("com.android.externalstorage.documents" == uri!!.authority)
        }

        fun isDownloadsDocument(uri: Uri?): Boolean {
            return ("com.android.providers.downloads.documents" == uri!!.authority)
        }

        fun isMediaDocument(uri: Uri?): Boolean {
            return ("com.android.providers.media.documents" == uri!!.authority)
        }
    }
}


//        private val REQUEST_IMAGE_PICK: Int = 1
//        private val REQUEST_VIDEO_PICK: Int = 2

//    private lateinit var videoPath: String

//        radioGroupfp = findViewById(R.id.radioGroupfp)
//    radioGroupdm = findViewById(R.id.radioGroup_dm)
//        ce_on = findViewById(R.id.ce_on)
//        ceoff = findViewById(R.id.ce_off)
//        ae_on = findViewById(R.id.ae_on)
//        ae_off = findViewById(R.id.ae_off)
//    private lateinit var crs_sel_language_str: String

//    private lateinit var free_paid_switch: Spinner
//    private lateinit var days_mnth_switch: Spinner
//    private lateinit var assignmnt_switch: Spinner
//    private lateinit var cert_switch: Spinner
//    private var cert_enbld_str: Array<String> = arrayOf("Select", "On", "Off")
//    private var assgn_enbld_str: Array<String> = arrayOf("Select", "On", "Off")
//    lateinit var crs_catId: String
//    lateinit var crs_subcatId: String

//    lateinit var radioGroupfp: RadioGroup
//    lateinit var radioGroupdm: RadioGroup
//    lateinit var ceoff: RadioButton
//    lateinit var ce_on: RadioButton
//    lateinit var ae_on: RadioButton
//    lateinit var ae_off: RadioButton