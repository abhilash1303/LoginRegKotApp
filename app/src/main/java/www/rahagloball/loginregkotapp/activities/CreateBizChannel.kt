package www.rahagloball.loginregkotapp.activities

//import android.widget.Spinner
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.bizch.BizChanPojo
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData


class CreateBizChannel : AppCompatActivity(), View.OnClickListener {
    var manager: SessionManager? = null
    var token: String? = null
    var context: Context? = null
    var channell_name: EditText? = null
    var auth_name: EditText? = null
    var bizch_gst: EditText? = null
    var bizch_pan: EditText? = null
    var biz_email: EditText? = null
    var write_biz: EditText? = null
    var business_name: EditText? = null
    var associte_fulladdrs: EditText? = null
    var enter_pincode: EditText? = null
    var cmp_type_spinner: Spinner? = null
    var subcat_spinner: Spinner? = null
    var state: Spinner? = null
    var city: Spinner? = null
    var agentype: Spinner? = null
    var profileSave: TextView? = null
    var cmptype_edit_str = arrayOf(
        "Select",
        "Private Limited",
        "Public Limited",
        "LLP",
        "Proprietorship",
        "Unregistered"
    )
    var customDialog: CustomDialog? = null
    var result: JSONArray? = null
    var cresult: JSONArray? = null
    var pinresults: JSONArray? = null
    var statee = ArrayList<String>()
    var cityy = ArrayList<String>()
    var pincode = ArrayList<String>()
    var preferences: SharedPreferences? = null
    private var catresult: JSONArray? = null
    private var subcatresult: JSONArray? = null
    var categry: ArrayList<String>? = null
    var subcategry: ArrayList<String>? = null
    var cattiid: String? = null
    var subcattiid: String? = null
    var stateiid: String? = null
    var cityIdd: String? = null
    var channell_name_str: String? = null
    var auth_name_str: String? = null
    var bizch_gst_str: String? = null
    var bizch_pan_str: String? = null
    var biz_email_str: String? = null
    var write_biz_str: String? = null
    var business_name_str: String? = null
    var associte_fulladdrs_str: String? = null
    var enter_pincode_str: String? = null
    var cmp_type_spinner_str: String? = null
    var terms_promo: CheckBox? = null
    var map: HashMap<String, RequestBody>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biz_prfl)
        context = this@CreateBizChannel
        manager = SessionManager()
        token = manager?.getPreferences(this@CreateBizChannel, Constants.USER_TOKEN_LRN)
        customDialog = CustomDialog(this)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        channell_name = findViewById<EditText>(R.id.channell_name)
        business_name = findViewById<EditText>(R.id.business_name)
        cmp_type_spinner = findViewById<Spinner>(R.id.cmp_type_spinner)
        auth_name = findViewById<EditText>(R.id.auth_name)
        bizch_pan = findViewById<EditText>(R.id.bizch_pan)
        bizch_gst = findViewById<EditText>(R.id.bizch_gst)
        biz_email = findViewById<EditText>(R.id.associte_email2)
        agentype = findViewById<Spinner>(R.id.agenttype_spinner)
        subcat_spinner = findViewById<Spinner>(R.id.subcat_spinner)
        write_biz = findViewById<EditText>(R.id.write_biz)
        associte_fulladdrs = findViewById<EditText>(R.id.associte_fulladdrs)
        state = findViewById<Spinner>(R.id.state_spinner)
        city = findViewById<Spinner>(R.id.city_spinner)
        enter_pincode = findViewById<EditText>(R.id.enter_pincode)
        terms_promo = findViewById<CheckBox>(R.id.terms_promo)
        profileSave = findViewById<TextView>(R.id.profileSave)
        categry = ArrayList()
        subcategry = ArrayList()
        val dataAdapter1: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.custom_spiner_layout,
            cmptype_edit_str
        )
        dataAdapter1.setDropDownViewResource(R.layout.custom_spiner_layout)
        cmp_type_spinner?.adapter = dataAdapter1
        agentype?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor = preferences!!.edit()
                editor.putString("ctgrysel", agentype?.selectedItem.toString())
                editor.apply()
                //                subctgrysel
                cattiid = Getcatid(position)
                getSubCData(cattiid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        subcat_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor = preferences!!.edit()
                editor.putString("subctgrysel", subcat_spinner?.selectedItem.toString())
                editor.apply()
                subcattiid = Getsubcatid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
    //                            singleSpinnerSearch.setTitle("Select City");
            }
        }
        state?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor = preferences!!.edit()
                editor.putString("statesel", state?.getSelectedItem().toString())
                editor.apply()
                stateiid = GetSid(position)
                getCData(stateiid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        city?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val editor: SharedPreferences.Editor = preferences!!.edit()
                editor.putString("citysel", city?.getSelectedItem().toString())
                editor.apply()
                cityIdd = GetCid(position)
                //                getpincode(cityIdd);
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

//        else if (!terms_promo.isChecked()) {
//            Toast.makeText(context, "Kindly Accept the terms and conditions.", Toast.LENGTH_SHORT).show();
//        }
        profileSave?.setOnClickListener(this)
        statecityData
        ctgryData
    }//                        blur_reg_signup.visibility = View.GONE;

    //Creating a request queue

    //Adding request to the queue
//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array
    //                            blur_reg_signup.visibility = View.GONE;
//Creating a string request
    //        blur_reg_signup.visibility = View.VISIBLE;
    private val statecityData: Unit
        private get() {

//        blur_reg_signup.visibility = View.VISIBLE;
            try {
                customDialog?.show()

                //Creating a string request
                val stringRequest = StringRequest(Configs.LOCATION_URL_BL,
                    { response ->
                        customDialog?.dismiss()
                        var j: JSONObject? = null
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = JSONObject(response)

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Configs.JSON_SARRAY_BL)

                            //Calling method getStudents to get the students from the JSON Array
                            getState(result)
                            //                            blur_reg_signup.visibility = View.GONE;
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    {
                        //                        blur_reg_signup.visibility = View.GONE;
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun getState(j: JSONArray?) {
        //Traversing through all the items in the json array
        if (j != null) {
            for (i in 0 until j.length()) {
                try {
                    //Getting json object
                    val json: JSONObject = j.getJSONObject(i)

                    //Adding the name of the state to array list
                    statee.add(json.getString(Configs.KEY_SNAME_BL))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        //Setting adapter to show the items in the spinner
        val spinnerArrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout, statee)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout)
        state?.adapter = spinnerArrayAdapter
        val citysreeer: String? = preferences?.getString("statesel", "")
        if (!citysreeer.equals("", ignoreCase = true)) {
            val spinnerPositioncity: Int = spinnerArrayAdapter.getPosition(citysreeer)
            state?.setSelection(spinnerPositioncity)
        }
    }

    private fun GetSid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = result?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_SID_BL)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getCData(stateiid: String?) {

//        blur_reg_signup.visibility = View.VISIBLE;
        val url: String = Configs.CLOCATION_URL_BL + stateiid
        val stringRequest = StringRequest(url, { response ->
            var j: JSONObject? = null
            try {
                //Parsing the fetched Json String to JSON Object
                j = JSONObject(response)

                //Storing the Array of JSON String to our JSON Array
                cresult = j.getJSONArray(Configs.JSON_CARRAY_BL)

                //Calling method getStudents to get the students from the JSON Array
                getCity(cresult)
                //                    blur_reg_signup.visibility = View.GONE;
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        },
            {
                //                        blur_reg_signup.visibility = View.GONE;
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

    private fun getCity(j: JSONArray?) {
        //Traversing through all the items in the json array
        cityy = ArrayList()
        if (j != null) {
            for (i in 0 until j.length()) {
                try {
                    //Getting json object
                    val json: JSONObject = j.getJSONObject(i)

                    //Adding the name of the student to array list
                    cityy.add(json.getString(Configs.KEY_CNAME_BL))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        //Setting adapter to show the items in the spinner
        val spinnerArrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout, cityy)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout)
        city?.adapter = spinnerArrayAdapter
        val citysreeer: String? = preferences?.getString("citysel", "")
        if (!citysreeer.equals("", ignoreCase = true)) {
            val spinnerPositioncity: Int = spinnerArrayAdapter.getPosition(citysreeer)
            city?.setSelection(spinnerPositioncity)
        }

//        blur_reg_signup.visibility = View.GONE;
    }

    private fun GetCid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = cresult?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_CID_BL)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }

    private fun getpincode(citystr: String) {
        try {

//            Log.e("citystreditprofile", citystr);

            //Creating a string request
            val stringRequests = StringRequest(Configs.PIN_URL_BL + citystr,
                { response ->
                    var j: JSONObject? = null
                    try {
                        //Parsing the fetched Json String to JSON Object
                        j = JSONObject(response)

                        //Storing the Array of JSON String to our JSON Array
                        pinresults = j.getJSONArray(Configs.JSON_PINARRAY_BL)

                        //Calling method getStudents to get the students from the JSON Array
//                                getPin(pinresults);
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                {
                    //
                })
            stringRequests.retryPolicy = DefaultRetryPolicy(
                Configs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )

            //Creating a request queue
            val requestQueue: RequestQueue = Volley.newRequestQueue(this)

            //Adding request to the queue
            requestQueue.add<String>(stringRequests)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array

//                    blur_reg1.visibility = View.GONE;

    //Creating a request queue

    //Adding request to the queue
    //            blur_reg1.visibility = View.VISIBLE;
    private val ctgryData: Unit
        get() {
            try {

//            blur_reg1.visibility = View.VISIBLE;
                customDialog?.show()
                val url: String = Configs.CATGRY_URL
                val stringRequest =
                    StringRequest(url, { response: String? ->
                        customDialog?.dismiss()
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
                        //                    categry.add(json.getString(Configs.KEY_CTRGYICON));
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
                            val countryName = json.getString(Configs.KEY_COUNTRY_NAME)
                            categry?.add(countryName)
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
                ArrayAdapter<String>(this@CreateBizChannel, R.layout.custom_spiner_layout, categry!!)
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
            agentype?.adapter = dataAdapter5
            val citysreeer: String? = preferences?.getString("ctgrysel", "")
            if (!citysreeer.equals("", ignoreCase = true)) {
                val spinnerPositioncity: Int = dataAdapter5.getPosition(citysreeer)
                agentype?.setSelection(spinnerPositioncity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getSubCData(cattiid: String?) {
//        blur_reg1.visibility = View.VISIBLE;
        // Log.e("stateiid",stateiid);
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
        try {
            subcategry = ArrayList()
            if (j != null) {
                for (i in 0 until j.length()) {
                    try {
                        val json: JSONObject = j.getJSONObject(i)
                        subcategry!!.add(json.getString(Configs.KEY_SUBCTRGYNAME))
                        //                    subcategry.add(json.getString(Configs.KEY_SUBCTRGYICON));
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            //Setting adapter to show the items in the spinner
            val spinnerArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.custom_spiner_layout, subcategry!!)
            spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout)
            subcat_spinner?.adapter = spinnerArrayAdapter
            val citysreeer: String? = preferences?.getString("subctgrysel", "")
            if (!citysreeer.equals("", ignoreCase = true)) {
                val spinnerPositioncity: Int = spinnerArrayAdapter.getPosition(citysreeer)
                subcat_spinner?.setSelection(spinnerPositioncity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.profileSave) {
            checkvalidation()
        }
    }

    private fun checkvalidation() {
        channell_name_str = channell_name?.text.toString()
        business_name_str = business_name?.text.toString()
        cmp_type_spinner_str = cmp_type_spinner?.selectedItem.toString()
        auth_name_str = auth_name?.text.toString()
        bizch_pan_str = bizch_pan?.text.toString()
        bizch_gst_str = bizch_gst?.text.toString()
        biz_email_str = biz_email?.text.toString()
        write_biz_str = write_biz?.text.toString()
        associte_fulladdrs_str = associte_fulladdrs?.text.toString()
        enter_pincode_str = enter_pincode?.text.toString()
        if (channell_name_str == "" || channell_name_str!!.isEmpty() || business_name_str == "" || business_name_str!!.isEmpty() || auth_name_str == "" || auth_name_str!!.isEmpty() || bizch_pan_str == "" || bizch_pan_str!!.isEmpty() || bizch_gst_str == "" || bizch_gst_str!!.isEmpty() || biz_email_str == "" || biz_email_str!!.isEmpty() || associte_fulladdrs_str == "" || associte_fulladdrs_str!!.isEmpty() || enter_pincode_str == "" || enter_pincode_str!!.isEmpty() || write_biz_str == "" || write_biz_str!!.isEmpty()) {
            Toast.makeText(context, "Enter all details!", Toast.LENGTH_SHORT).show()
        } else if (cmp_type_spinner_str == "Select") {
            Toast.makeText(context, "Select Company type!!", Toast.LENGTH_SHORT).show()
        } else {
            submitprofile()
        }
    }

    private fun submitprofile() {
        try {
            channell_name_str = channell_name?.text.toString()
            biz_email_str = biz_email?.text.toString()
            auth_name_str = auth_name?.text.toString()
            bizch_pan_str = bizch_pan?.text.toString()
            bizch_gst_str = bizch_gst?.text.toString()
            write_biz_str = write_biz?.text.toString()
            business_name_str = business_name?.text.toString()
            associte_fulladdrs_str = associte_fulladdrs?.text.toString()
            enter_pincode_str = enter_pincode?.text.toString()
            cmp_type_spinner_str = cmp_type_spinner?.selectedItem.toString()

//            if (customDialog != null)
            customDialog?.show()
            map = HashMap<String, RequestBody>()
            map!!["channel_name"] =
                channell_name_str?.let { RequestBody.create(MediaType.parse("text/plain"), it) }!!
            map!!["biz_name"] = RequestBody.create(MediaType.parse("text/plain"), business_name_str)
            map!!["authorised_person"] =
                RequestBody.create(MediaType.parse("text/plain"), auth_name_str)
            map!!["official_email"] =
                RequestBody.create(MediaType.parse("text/plain"), biz_email_str)
            map!!["state_id"] = RequestBody.create(MediaType.parse("text/plain"), stateiid)
            map!!["city_id"] = RequestBody.create(MediaType.parse("text/plain"), cityIdd)
            map!!["pincode"] = RequestBody.create(MediaType.parse("text/plain"), enter_pincode_str)
            map!!["address"] =
                RequestBody.create(MediaType.parse("text/plain"), associte_fulladdrs_str)
            map!!["category_id"] = RequestBody.create(MediaType.parse("text/plain"), cattiid)
            map!!["subcategory_id"] = RequestBody.create(MediaType.parse("text/plain"), subcattiid)
            map!!["gstin_number"] = RequestBody.create(MediaType.parse("text/plain"), bizch_gst_str)
            map!!["pan_number"] = RequestBody.create(MediaType.parse("text/plain"), bizch_pan_str)
            map!!["about_biz"] = RequestBody.create(MediaType.parse("text/plain"), write_biz_str)
            map!!["country_id"] = RequestBody.create(MediaType.parse("text/plain"), "")
            map!!["company_type"] =
                RequestBody.create(MediaType.parse("text/plain"), cmp_type_spinner_str)
            RetrofitClient.getClient().getbizchannel(
                map,
                "application/json", "Bearer $token"
            )?.enqueue(object : GlobalCallback<BizChanPojo?>(channell_name) {
               override fun onResponse(
                   call: Call<BizChanPojo?>,
                   response: retrofit2.Response<BizChanPojo?>
               ) {
                    customDialog?.dismiss()
                    try {
                        val bizChanPojo: BizChanPojo? = response.body()
                        val suxs: String? = bizChanPojo?.msg
                        val suxs_status: String? = bizChanPojo?.status
                        if (suxs_status == "200") {
                            finish()
                            Toast.makeText(context, suxs, Toast.LENGTH_SHORT).show()
                        } else if (suxs_status == "409") {
                            finish()
                            Toast.makeText(context, suxs, Toast.LENGTH_SHORT).show()
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
}