@file:Suppress("UNNECESSARY_SAFE_CALL")

package www.rahagloball.loginregkotapp.activities

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Spinner
//import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import www.rahagloball.loginregkotapp.multispinnerrr.MultiSpinnerSearch
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URISyntaxException
import java.util.Objects

class EditProfileNew : AppCompatActivity() {
    private lateinit var imgProfilePic: CircleImageView
    private lateinit var associt_namee: EditText
    private lateinit var associte_email: EditText
    private lateinit var asscit_mary_stats: Spinner
    private lateinit var asscit_gender: Spinner
    private lateinit var state_spinner: Spinner
    private lateinit var associte_occuptn: Spinner
    private lateinit var city_spinner: Spinner

    //    EditText multipleItemSelectionSpinner;
    private lateinit var agentype: Spinner
    private lateinit var subcat_spinner: Spinner
    private lateinit var asscit_age_spnr: Spinner
    private lateinit var context: Context
    private lateinit var profileSave: Button
    private lateinit var btnEdit: Button
    private lateinit var token: String
    private lateinit var manager: SessionManager
    private var sel_marital = arrayOf("Select", "Married", "Unmarried", "Widow")
    private var sel_occupation = arrayOf("Select", "Employed", "Self Employed", "Student", "Others")
    private var sel_gender = arrayOf("Select", "Male", "Female", "Other")
    private var sel_agee = arrayOf("Select", "15-25", "25-35", "35-50", "50-75")
    private lateinit var customDialog: CustomDialog
    private lateinit var dataAdapter_gendr: ArrayAdapter<String>
    private lateinit var dataAdapter_marital: ArrayAdapter<String>
    private lateinit var dataAdapter_others: ArrayAdapter<String>
    private lateinit var dataAdapter_age: ArrayAdapter<String>
    private lateinit var associt_namee_str: String
    private lateinit var associte_email_str: String
    private lateinit var asscit_mary_statsStr: String
    private lateinit var asscit_genderStr: String
    private lateinit var associte_occuptnStr: String
    private lateinit var StspinnerStr: String
    private lateinit var CspinnerStr: String
    private lateinit var cat_spinnerStr: String
    private lateinit var asscit_age_spnr_str: String
    private lateinit var subcat_spinnerStr: String
    private lateinit var ppinIdd: String
    private lateinit var pincode_spinStr: String
    private lateinit var map: HashMap<String, RequestBody>
    private lateinit var vid_titlee_str: String
    private lateinit var vid_descc_str: String
    private lateinit var imgPath: String
    private lateinit var sel_cuti_vidStr: String
    private lateinit var file_img: File
    private lateinit var file_vid: File
    private lateinit var bitmap: Bitmap
    private lateinit var sel_imgg: MultipartBody.Part
    private lateinit var sel_vidd: MultipartBody.Part
    private lateinit var catresult: JSONArray
    private lateinit var subcatresult: JSONArray
    private lateinit var categry: ArrayList<String>
    private lateinit var subcategry: ArrayList<String>

    //    String stateiid, stcity, cityIdd, subcattiid, catiid, pin_iddd;
    private lateinit var pin_iddd: String
    private lateinit var cntry_result: JSONArray
    private lateinit var state_result: JSONArray
    private lateinit var city_results: JSONArray
    private var cntry = ArrayList<String>()
    private var statee = ArrayList<String>()
    private var cityy = ArrayList<String>()
    private lateinit var pinresults: JSONArray

    //    ArrayList<String> statee = new ArrayList<>();
    //    ArrayList<String> cityy = new ArrayList<>();
    private var pincode_list = ArrayList<String>()
    private lateinit var countrySpinnerSearch: SearchableSpinner
    private lateinit var stateSpinnerSearch: SearchableSpinner
    private lateinit var citySpinnerSearch: SearchableSpinner
    private lateinit var progressBar: RelativeLayout
    private lateinit var cnfrm_pwd: TextInputEditText
    private lateinit var pwd: TextInputEditText
    private lateinit var cnfrm_pwd_str: String
    private lateinit var pwd_str: String
    private var temp = true
    private lateinit var assox_mary_stsss_str: String
    private lateinit var assox_occupationn_str: String
    private lateinit var assox_genderr_str: String
    private lateinit var assox_name_str: String
    private lateinit var assox_email_str: String
    private lateinit var assox_mob_str: String
    private lateinit var imgProfilePic_str: String
    private lateinit var assox_cityy_str: String
    private lateinit var assox_pinnc_str: String
    private lateinit var preferences: SharedPreferences

    //    CustomDialog customDialog;
    private lateinit var country_id_str: String
    private lateinit var state_id_str: String
    private lateinit var city_id_str: String
    private lateinit var multipleItemSelectionSpinner: MultiSpinnerSearch
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_final)
        context = this@EditProfileNew
        manager = SessionManager()
        customDialog = CustomDialog(this)
        token = manager!!.getPreferences(this@EditProfileNew, Constants.USER_TOKEN_LRN)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        imgProfilePic = findViewById<CircleImageView>(R.id.imgProfilePic)
        btnEdit = findViewById<Button>(R.id.btnEdit)
        progressBar = findViewById<RelativeLayout>(R.id.progressBar)
        associt_namee = findViewById<EditText>(R.id.associt_namee)
        associte_email = findViewById<EditText>(R.id.associte_email)
        associte_occuptn = findViewById<Spinner>(R.id.associte_occuptn)
        asscit_mary_stats = findViewById<Spinner>(R.id.asscit_mary_stats)
        asscit_gender = findViewById<Spinner>(R.id.asscit_gender)
        cnfrm_pwd = findViewById<TextInputEditText>(R.id.cnfrm_pwd)
        pwd = findViewById<TextInputEditText>(R.id.pwd)
        multipleItemSelectionSpinner = findViewById(R.id.pincode)

//        state_spinner = findViewById(R.id.state_spinner);
//        city_spinner = findViewById(R.id.city_spinner);
        asscit_age_spnr = findViewById<Spinner>(R.id.asscit_age_spnr)
        countrySpinnerSearch = findViewById(R.id.countrySpinnerSearch)
        stateSpinnerSearch = findViewById(R.id.stateSpinnerSearch)
        citySpinnerSearch = findViewById(R.id.citySpinnerSearch)
        agentype = findViewById<Spinner>(R.id.agenttype_spinner)
        subcat_spinner = findViewById<Spinner>(R.id.subcat_spinner)
        profileSave = findViewById<Button>(R.id.profileSave)

        //============================================

        //gender
        dataAdapter_age =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, sel_agee)
        dataAdapter_age!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        asscit_age_spnr?.adapter = dataAdapter_age
        dataAdapter_gendr =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, sel_gender)
        dataAdapter_gendr?.setDropDownViewResource(R.layout.custom_spiner_layout)
        asscit_gender?.adapter = dataAdapter_gendr
        val sel_gennder: String? = preferences?.getString("gen_sel", "")
        if (!sel_gennder.equals("", ignoreCase = true)) {
            val spinnerPosition: Int = dataAdapter_gendr!!.getPosition(sel_gennder)
            asscit_gender?.setSelection(spinnerPosition)
        }
        asscit_gender?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences?.edit()
                editor?.putString("gen_sel", asscit_gender?.getSelectedItem().toString())
                editor?.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

//============================================

        //marital status
        dataAdapter_marital = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_marital
        )
        dataAdapter_marital!!.setDropDownViewResource(R.layout.custom_spiner_layout)
        asscit_mary_stats?.adapter = dataAdapter_marital
        val mary_sts: String? = preferences?.getString("marsts_sel", "")
        if (!mary_sts.equals("", ignoreCase = true)) {
            val spinnerPosition: Int = dataAdapter_marital!!.getPosition(mary_sts)
            asscit_mary_stats?.setSelection(spinnerPosition)
        }
        asscit_mary_stats?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences?.edit()
                editor?.putString("marsts_sel", asscit_mary_stats?.selectedItem.toString())
                editor?.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


//============================================
        //occupation
        dataAdapter_others = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            sel_occupation
        )
        dataAdapter_others?.setDropDownViewResource(R.layout.custom_spiner_layout)
        associte_occuptn?.adapter = dataAdapter_others
        val assc_occpn: String? = preferences?.getString("occptn_sel", "")
        //        Log.e("aos",language);
        if (!assc_occpn.equals("", ignoreCase = true)) {
            val spinnerPosition: Int = dataAdapter_others!!.getPosition(assc_occpn)
            associte_occuptn?.setSelection(spinnerPosition)
        }
        associte_occuptn?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences?.edit()
                editor?.putString("occptn_sel", associte_occuptn?.selectedItem.toString())
                editor?.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        btnEdit!!.setOnClickListener {
            chooseImage(this@EditProfileNew)
        }
        profileSave!!.setOnClickListener { checkvalidation() }

//
//        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("statesel", state_spinner.getSelectedItem().toString());
//                editor.apply();
//
//                stateiid = GetSid(position);
//                getCData(stateiid);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("citysel", city_spinner.getSelectedItem().toString());
//                editor.apply();
//
//                cityIdd = GetCid(position);
//                getpincode(cityIdd);
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        countrySpinnerSearch?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences?.edit()
                editor?.putString("countrysel", countrySpinnerSearch?.getSelectedItem().toString())
                editor?.apply()
                country_id_str = getCntryid(position)
                getStatedata(country_id_str!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        stateSpinnerSearch?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences?.edit()
                editor?.putString("statesel", stateSpinnerSearch?.getSelectedItem().toString())
                editor?.apply()
                state_id_str = GetStateid(position)
                getCitydata(state_id_str!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        citySpinnerSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor? = preferences.edit()
                editor?.putString("citysel", citySpinnerSearch.getSelectedItem().toString())
                editor?.apply()
                city_id_str = GetCityid(position)
                getPincode(city_id_str)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        multipleItemSelectionSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                pin_iddd = getPinid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        getCntrtyData()
        checkAndRequestPermissions()
        profileimage()
    }

    private fun checkvalidation() {
        associt_namee_str = associt_namee.text.toString()
        associte_email_str = associte_email.text.toString()
        asscit_mary_statsStr = asscit_mary_stats.selectedItem.toString()
        asscit_genderStr = asscit_gender.selectedItem.toString()
        associte_occuptnStr = associte_occuptn.selectedItem.toString()
        asscit_age_spnr_str = asscit_age_spnr.selectedItem.toString()
        pwd_str = Objects.requireNonNull<Editable>(pwd.text).toString()
        cnfrm_pwd_str = Objects.requireNonNull<Editable>(cnfrm_pwd.getText()).toString()
        if (multipleItemSelectionSpinner.selectedItem != null) {
            pincode_spinStr = (multipleItemSelectionSpinner.selectedItem as String?).toString()
        } else if (pincode_spinStr == "Choose Pincode") {
            Toast.makeText(context, "Choose Pincodes!!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "No Pincodes!!", Toast.LENGTH_SHORT).show()
        }
        if (associt_namee_str == "" || associt_namee_str.isEmpty() || associte_email_str == "" || associte_email_str.isEmpty()) {
            Toast.makeText(context, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else if (asscit_genderStr == "" || asscit_genderStr.isEmpty() || asscit_genderStr == "Select" || asscit_mary_statsStr == "" || asscit_mary_statsStr.isEmpty() || asscit_mary_statsStr == "Select" || asscit_age_spnr_str == "" || asscit_age_spnr_str.isEmpty() || asscit_age_spnr_str == "Select" || associte_occuptnStr == "" || associte_occuptnStr.isEmpty() || associte_occuptnStr == "Select") {
            Toast.makeText(context, "Kindly Select!", Toast.LENGTH_SHORT).show()
        } else if (pwd_str != cnfrm_pwd_str) {
            Toast.makeText(this@EditProfileNew, "Password Not matching", Toast.LENGTH_SHORT).show()
            temp = false
        } else {
            submitprofile()
        }
    }

    private fun profileimage() {

//        progressBar.visibility = View.VISIBLE;
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.run {
            getClient().update_profilenw(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(associt_namee) {
                    override fun onResponse(
                        call: Call<UpdtproflPojo?>,
                        response: retrofit2.Response<UpdtproflPojo?>
                    ) {
                        //                        progressBar.visibility = View.GONE;
                        try {
                            val profileItems: List<ProfileItem>? = response.body()?.profile
                            if (profileItems?.isEmpty() == true) {
                                Toast.makeText(context, "No data!!", Toast.LENGTH_SHORT).show()
                            } else {
                                for (i in profileItems?.indices!!) {
                                    val profileItem: ProfileItem = profileItems[i]
                                    assox_name_str = profileItem.name.toString()
                                    assox_mob_str = profileItem.mobile.toString()
                                    assox_email_str = profileItem.email.toString()
                                    assox_genderr_str = profileItem.gnederr.toString()
                                    assox_occupationn_str = profileItem.occupation.toString()
                                    assox_cityy_str = profileItem.city.toString()
                                    assox_pinnc_str = profileItem.pincode.toString()
                                    assox_pinnc_str = profileItem.pincode.toString()
                                    assox_mary_stsss_str = profileItem.marriedStatus.toString()
                                    associt_namee.setText(assox_name_str)
                                    associte_email.setText(assox_email_str)
                                    imgPath =
                                        Configs.BASE_URL21 + "images/user/" + profileItem.userImage
                                    context.let {
                                        imgProfilePic.let { it1 ->
                                            Glide.with(it).load(imgPath).into(
                                                it1
                                            )
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
    }

    private fun submitprofile() {
        try {
            progressBar.visibility = View.VISIBLE
            associt_namee_str = associt_namee.text.toString()
            associte_email_str = associte_email.text.toString()
            asscit_mary_statsStr = asscit_mary_stats.selectedItem.toString()
            asscit_genderStr = asscit_gender.selectedItem.toString()
            associte_occuptnStr = associte_occuptn.selectedItem.toString()
            asscit_age_spnr_str = asscit_age_spnr.selectedItem.toString()
            pwd_str = pwd.text.toString()
            cnfrm_pwd_str = cnfrm_pwd.text.toString()

            map = HashMap<String, RequestBody>()
            map["name"] = associt_namee_str.let { name ->
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    name
                )
            }
            map["email"] = associte_email_str.let { email ->
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    email
                )
            }
            map["gender"] = asscit_genderStr.let { gender ->
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    gender
                )
            }
            map["age"] = asscit_age_spnr.selectedItem.toString()
                .let { age -> RequestBody.create(MediaType.parse("text/plain"), age) }
            map["occupation"] = associte_occuptn.selectedItem.toString().let { occupation ->
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    occupation
                )
            }
            map["married_status"] = asscit_mary_statsStr.let { status ->
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    status
                )
            }
            map["state"] =
                state_id_str.let { RequestBody.create(MediaType.parse("text/plain"), it) }
            map["city"] = city_id_str.let {
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    it
                )
            }
            map["pin_code"] = pin_iddd.let {
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    it
                )
            }
            map["password"] = pwd_str.let {
                RequestBody.create(
                    MediaType.parse("text/plain"),
                    it
                )
            }


            map["confirm_password"] =
                cnfrm_pwd_str.let { RequestBody.create(MediaType.parse("text/plain"), it) }

            sel_imgg = file_img.let {
                RequestBody.create(
                    MediaType.parse("image/*"), it
                )
            }?.let {
                MultipartBody.Part.createFormData(
                    "user_img", imgPath, it
                )
            }!!
            RetrofitClient.getClient().upload_files(
                map, sel_imgg,
                "application/json", "Bearer $token"
            )?.enqueue(object : GlobalCallback<String?>(associt_namee) {
                override fun onResponse(
                    call: Call<String?>,
                    response: retrofit2.Response<String?>
                ) {
                    progressBar?.visibility = View.GONE
                    try {
                        val res = response.body()?.toString()
                        if (res?.contains("1") == true) {
                            manager?.setPreferences(
                                this@EditProfileNew,
                                Constants.LOGIN_STATUS,
                                "1"
                            )
                            val intent =
                                Intent(this@EditProfileNew, LanguageActivityDemo::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this@EditProfileNew,
                                "Profile Update successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(this@EditProfileNew, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Log.d("Exception", "|=>" + e.message)
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Exit"
        ) // create a menuOption Array

        // create a dialog for showing the optionsMenu
        val builder = AlertDialog.Builder(context)

        // set the items in builder
        builder.setItems(
            optionsMenu
        ) { dialogInterface, i ->
            if (optionsMenu[i] == "Take Photo") {

                // Open the camera and get the photo
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, 0)
            } else if (optionsMenu[i] == "Choose from Gallery") {

                // choose from  external storage
                val pickPhoto =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, 1)
            } else if (optionsMenu[i] == "Exit") {
                dialogInterface.dismiss()
            }
        }
        builder.show()
    }

    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this@EditProfileNew, Manifest.permission.CAMERA)
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@EditProfileNew,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@EditProfileNew,
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
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this@EditProfileNew,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

//        Log.d("result", "" + resultCode);
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
//            Log.d("what", "cancle");
            return
        }
        when (requestCode) {
            CAPTURE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    imgProfilePic?.setImageBitmap(bitmap)
                    try {
                        imgPath = getPath(getImageUri(this@EditProfileNew, bitmap))!!
                        imgProfilePic?.let {
                            context?.let { it1 ->
                                Glide.with(it1).load(imgPath).into(it)
                            }
                        }
                        file_img = File(imgPath)
                    } catch (e: URISyntaxException) {
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
                                imgProfilePic?.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                                try {
                                    imgPath = getPath(
                                        getImageUri(
                                            this@EditProfileNew,
                                            BitmapFactory.decodeFile(picturePath)
                                        )
                                    )!!
                                    imgProfilePic?.let {
                                        context?.let { it1 ->
                                            Glide.with(it1).load(imgPath).into(it)
                                        }
                                    }
                                    file_img = File(imgPath)
                                } catch (e: URISyntaxException) {
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

    private fun getImageUri(inContext: Activity, inImage: Bitmap): Uri {
        val baos = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, baos)
        val path1: String = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "NLProfile",
            null
        )
        return Uri.parse(path1)
    }

    @Throws(URISyntaxException::class)
    fun getPath(uri: Uri): String? {
        var uri = uri
        val needToCheckUri: Boolean = true
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(this@EditProfileNew, uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            } else if (isDownloadsDocument(uri)) {
                val id: String = DocumentsContract.getDocumentId(uri)
                uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), id.toLong()
                )
            } else if (isMediaDocument(uri)) {
                val docId: String = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                if ("image" == type) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(
                    split[1]
                )
            }
        }
        if ("content".equals(uri.scheme, ignoreCase = true)) {
            val projection = arrayOf<String>(
                MediaStore.Images.Media.DATA
            )
            var cursor: Cursor? = null
            try {
                cursor = this@EditProfileNew.contentResolver
                    .query(uri, projection, selection, selectionArgs, null)
                val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
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


    //add country, state, city and pincode here

    private fun getCntrtyData() {
        val url = Configs.COUNTRY_URL_BL
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    // Parsing the fetched JSON String to a JSON Object
                    val j = JSONObject(response)
                    // Storing the array of JSON String to our JSON Array
                    cntry_result = j.getJSONArray(Configs.JSON_COUNTRY_ARRAY)
                    // Calling the method to process the country data
                    getCntry(cntry_result)
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

    private fun getCntryid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = cntry_result?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_COUNTRY_ID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getCntry(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            cntry = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
                        cntry.add(json.getString(Configs.KEY_COUNTRY_NAME))

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
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, cntry)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            countrySpinnerSearch?.adapter = dataAdapter5

            val citysreeer = preferences?.getString("countrysel", "")
            if (!citysreeer.equals("", ignoreCase = true)) {
                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
                countrySpinnerSearch?.setSelection(spinnerPositioncity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun getStatedata(country_id_str: String) {
        val url = Configs.STATE_URL_BL + country_id_str
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    // Parsing the fetched JSON String to a JSON Object
                    val j = JSONObject(response)
                    // Storing the array of JSON String to our JSON Array
                    state_result = j.getJSONArray(Configs.JSON_STATE_ARRAY)
                    // Calling the method to process the country data
                    getState(state_result)
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

    private fun GetStateid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = state_result?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_STATE_ID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getState(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            statee = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
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
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, statee)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            stateSpinnerSearch?.adapter = dataAdapter5

            val citysreeer = preferences?.getString("statesel", "")
            if (!citysreeer.equals("", ignoreCase = true)) {
                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
                stateSpinnerSearch?.setSelection(spinnerPositioncity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun getCitydata(state_id_str: String) {
        val url = Configs.CITY_URL_BL + state_id_str
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    // Parsing the fetched JSON String to a JSON Object
                    val j = JSONObject(response)
                    // Storing the array of JSON String to our JSON Array
                    city_results = j.getJSONArray(Configs.JSON_CITY_ARRAY)
                    // Calling the method to process the country data
                    getCity(city_results)
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

    private fun GetCityid(position: Int): String {
        var sid = ""
        try {
            // Getting object at the given index
            val json = city_results?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_CITY_ID).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getCity(j: JSONArray?) {
        try {
            // Traversing through all the items in the JSON array
            cityy = ArrayList<String>()
            // city.add("Search Category")
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        // Getting JSON object
                        val json = j.getJSONObject(i)
                        // Adding the name of the student to the array list
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
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, cityy)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            citySpinnerSearch?.adapter = dataAdapter5

            val citysreeer = preferences?.getString("citysel", "")
            if (!citysreeer.equals("", ignoreCase = true)) {
                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
                citySpinnerSearch?.setSelection(spinnerPositioncity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun getPincode(citystr: String?) {
        val url = Configs.PIN_URL_BL + citystr
        val stringRequest = StringRequest(url, { response ->
            try {
                val j = JSONObject(response)
                pinresults = j.getJSONArray(Configs.JSON_PINARRAY_BL)
                getPin(pinresults)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> })
        stringRequest.retryPolicy = DefaultRetryPolicy(
            Configs.MY_SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun getPinid(position: Int): String {
        var sid = ""
        try {
            // Getting object of given index
            val json = pinresults?.getJSONObject(position)

            // Fetching id from that object
            sid = json?.getString(Configs.KEY_PINID_BL).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        // Returning the id
        return sid
    }

    private fun getPin(j: JSONArray?) {
        try {
            pincode_list = ArrayList<String>()
            val listArray0 = ArrayList<KeyPairBoolData>()

            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        val json = j.getJSONObject(i)
                        val pinName = json.getString(Configs.KEY_PINNAME_BL)
                        pincode_list.add(pinName)

                        for (k in 0 until pincode_list.size) {
                            val h = KeyPairBoolData(
                                idValue = (i + 1).toLong(),
                                nameValue = pincode_list[k],
                                isSelectedValue = false
                            )
                            listArray0.add(h)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

//            multipleItemSelectionSpinner?.setItems(listArray0) { items ->
//                for (g in items.indices) {
//                    Log.i("SelectPincodde", "$g : ${items[g].id} : ${items[g].isSelected}")
//                }
//            }

            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, pincode_list)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            multipleItemSelectionSpinner?.adapter = dataAdapter5
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


// private fun getpincode(citystr: String) {
//        val url = Configs.PIN_URL_BL + citystr
//        val stringRequest = StringRequest(url,
//            { response ->
//                try {
//                    // Parsing the fetched JSON String to a JSON Object
//                    val j = JSONObject(response)
//                    // Storing the array of JSON String to our JSON Array
//                    pinresults = j.getJSONArray(Configs.JSON_PINARRAY_BL)
//                    // Calling the method to process the country data
//                    getPin(pinresults)
////                blur_reg1.visibility = View.GONE;
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            },
//            { error -> // Error occurred
//                error.printStackTrace()
//            })
//
//        // Setting up retry policy for the request
//        stringRequest.retryPolicy = DefaultRetryPolicy(
//            Configs.MY_SOCKET_TIMEOUT_MS,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        )
//
//        // Creating a request queue
//        val requestQueue = Volley.newRequestQueue(this)
//        // Adding the request to the queue
//        requestQueue.add(stringRequest)
//    }
//
//    private fun GetPinid(position: Int): String {
//        var sid = ""
//        try {
//            // Getting object at the given index
//            val json = pinresults?.getJSONObject(position)
//
//            // Fetching id from that object
//            sid = json?.getString(Configs.KEY_PINID_BL).toString()
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        // Returning the id
//        return sid
//    }
//
//    private fun getPin(j: JSONArray?) {
//        try {
//            // Traversing through all the items in the JSON array
//            pincode_list = ArrayList<String>()
//            // city.add("Search Category")
//            if (j != null) {
//                for (i in 0 until j.length()) {
//                    try {
//                        // Getting JSON object
//                        val json = j.getJSONObject(i)
//                        // Adding the name of the student to the array list
//                        pincode_list.add(json.getString(Configs.KEY_CITY_NAME))
//
//                        val listArray0 = ArrayList<KeyPairBoolData>()
//                        for (k in 0 until pincode_list.size) {
//                            val countryName = json.getString(Configs.KEY_CITY_NAME)
//                            pincode_list.add(countryName)
//                            val h = KeyPairBoolData(
//                                id = (i + 1).toLong(),
//                                name = countryName,
//                                isSelected = false
//                            )
//                            listArray0.add(h)
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//            val dataAdapter5 = ArrayAdapter(this, R.layout.custom_spiner_layout, pincode_list)
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
//            multipleItemSelectionSpinner?.adapter = dataAdapter5
//
////            val citysreeer = preferences?.getString("citysel", "")
////            if (!citysreeer.equals("", ignoreCase = true)) {
////                val spinnerPositioncity = dataAdapter5.getPosition(citysreeer)
////                citySpinnerSearch?.setSelection(spinnerPositioncity)
////            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


    companion object {
        const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        private const val CAPTURE_REQUEST_CODE = 0
        private const val SELECT_REQUEST_CODE = 1
        fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

        fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }

        fun hideKeyboardFrom(context: Context, view: View) {
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}