package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CaetgoryAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.OnSwipeTouchListener
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.catgryy.CatgtyPojo
import www.rahagloball.loginregkotapp.models.catgryy.DataItem
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner


class Catgryctivity : AppCompatActivity() {
    var manager: SessionManager? = null
    var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var token: String? = null
    var blur_reg1: RelativeLayout? = null
    var emptybox: ImageView? = null
    var rl_filterrr: RelativeLayout? = null
    var state_spinner: SearchableSpinner? = null
    var city_spinner: SearchableSpinner? = null
    var agentype: SearchableSpinner? = null
    var subcat_spinner: SearchableSpinner? = null
    var pin_spinner: SearchableSpinner? = null
    var stateiid: String? = null
    var cityIdd: String? = null
    var cat_Idd: String? = null
    var subcattiid: String? = null
    var pinIdd: String? = null
    var result: JSONArray? = null
    var cresult: JSONArray? = null
    var statee = ArrayList<String>()
    var cityy = ArrayList<String>()
    var pincdee = ArrayList<String>()
    private var catresult: JSONArray? = null
    private var subcatresult: JSONArray? = null
    private var pinresult: JSONArray? = null
    var categry: ArrayList<String>? = null
    var subcategry: ArrayList<String>? = null
    var just_explore: LinearLayout? = null
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catgryctivity)
        manager = SessionManager()
        token = manager?.getPreferences(this@Catgryctivity, Constants.USER_TOKEN_LRN)
        emptybox = findViewById<ImageView>(R.id.emptyElement)
        rl_filterrr = findViewById<RelativeLayout>(R.id.rl_filterrr)
        state_spinner = findViewById(R.id.state_spinner)
        city_spinner = findViewById(R.id.city_spinner)
        pin_spinner = findViewById(R.id.pin_spinner)
        agentype = findViewById(R.id.agenttype_spinner)
        subcat_spinner = findViewById(R.id.subcat_spinner)
        just_explore = findViewById<LinearLayout>(R.id.just_explore)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = GridLayoutManager(this, 3)
        //        layoutManager = new LinearLayoutManager(Catgryctivity.this);
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        catgry
        agentype?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long) {
                cat_Idd = Getcatid(position)
                getSubCData(cat_Idd)
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
                id: Long) {
                subcattiid = Getsubcatid(position)
                //                getfilterall_sc(subcattiid);
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
    //                            singleSpinnerSearch.setTitle("Select City");
            }
        }
        state_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long) {
                stateiid = GetSid(position)
                getCData(stateiid)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        city_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long) {
                cityIdd = GetCid(position)
                getPinData(cityIdd)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        pin_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                pinIdd = GetPinid(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        statecityData
        ctgryData
        recyclerView!!.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun onSwipeDown() {
                rl_filterrr?.visibility = View.VISIBLE
            }

            override fun onSwipeLeft() {}
            override fun onSwipeUp() {
                rl_filterrr?.visibility = View.GONE
            }

            override fun onSwipeRight() {}
        })
        recyclerView!!.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun onSwipeDown() {
                rl_filterrr?.visibility = View.VISIBLE
            }

            override fun onSwipeLeft() {}
            override fun onSwipeUp() {
                rl_filterrr?.visibility = View.GONE
            }

            override fun onSwipeRight() {}
        })
        just_explore?.setOnClickListener {
            if (cat_Idd == null || cat_Idd!!.isEmpty()) {
                Toast.makeText(this@Catgryctivity, "Select Category", Toast.LENGTH_SHORT).show()
            } else if (subcattiid == null || subcattiid!!.isEmpty()) {
                Toast.makeText(this@Catgryctivity, "Select SubCategory", Toast.LENGTH_SHORT).show()
            } else if (stateiid == null || stateiid!!.isEmpty()) {
                Toast.makeText(this@Catgryctivity, "Select State", Toast.LENGTH_SHORT).show()
            } else if (cityIdd == null || cityIdd!!.isEmpty()) {
                Toast.makeText(this@Catgryctivity, "Select City", Toast.LENGTH_SHORT).show()
            } else if (pinIdd == null || pinIdd!!.isEmpty()) {
                Toast.makeText(this@Catgryctivity, "Select Pincode", Toast.LENGTH_SHORT).show()
            } else {
                val filterintent = Intent(this@Catgryctivity, ConncetAssctList::class.java)
                filterintent.putExtra("cat_Idd_cnct", cat_Idd)
                filterintent.putExtra("subcattiid_cnct", subcattiid)
                filterintent.putExtra("stateiid_cnct", stateiid)
                filterintent.putExtra("cityIdd_cnct", cityIdd)
                filterintent.putExtra("pinIdd_cnct", pinIdd)
                startActivity(filterintent)

//                getfilterall();
            }
        }
        statecityData
        ctgryData
    }

    private val catgry: Unit
        private get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "category"
            token = manager?.getPreferences(this@Catgryctivity, Constants.USER_TOKEN_LRN)
            RetrofitClient.getClient().catrgotyy_list(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<CatgtyPojo?>(recyclerView) {
                  override  fun onResponse(
                      call: Call<CatgtyPojo?>,
                      response: retrofit2.Response<CatgtyPojo?>
                  ) {
                      blur_reg1?.visibility = View.GONE
                        try {
                            val catggryList: List<DataItem>? = response.body()?.data
                            if (catggryList?.isEmpty() == true) {
                            } else {
                                adapter = catggryList?.let { CaetgoryAdapter(it, this@Catgryctivity) }
                                recyclerView!!.adapter = adapter
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }//                        blur_reg_signup.visibility = View.GONE;

    //Creating a request queue

    //Adding request to the queue
//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array
    //                            blur_reg_signup.visibility = View.GONE;
    //        blur_reg_signup.visibility = View.VISIBLE;

    //Creating a string request
    private val statecityData: Unit
        private get() {

//        blur_reg_signup.visibility = View.VISIBLE;

            //Creating a string request
            val stringRequest = StringRequest(Configs.LOCATION_URL_BL,
                { response ->
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
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, statee)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
        state_spinner?.setAdapter(spinnerArrayAdapter)
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
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, cityy)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
        city_spinner?.setAdapter(spinnerArrayAdapter)
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

    private fun getPinData(cityIdd: String?) {

//        blur_reg_signup.visibility = View.VISIBLE;
        val url: String = Configs.PIN_URL_BL + cityIdd
        val stringRequest = StringRequest(url, { response ->
            var j: JSONObject? = null
            try {
                //Parsing the fetched Json String to JSON Object
                j = JSONObject(response)

                //Storing the Array of JSON String to our JSON Array
                pinresult = j.getJSONArray(Configs.JSON_PINARRAY_BL)

                //Calling method getStudents to get the students from the JSON Array
                getPincode(pinresult)
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

    private fun getPincode(j: JSONArray?) {
        //Traversing through all the items in the json array
        pincdee = ArrayList()
        if (j != null) {
            for (i in 0 until j.length()) {
                try {
                    //Getting json object
                    val json: JSONObject = j.getJSONObject(i)

                    //Adding the name of the student to array list
                    pincdee.add(json.getString(Configs.KEY_PINNAME_BL))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }

        //Setting adapter to show the items in the spinner
        val spinnerArrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, pincdee)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
        pin_spinner?.setAdapter(spinnerArrayAdapter)

//        String citysreeer = preferences.getString("citysel", "");
//        if (!citysreeer.equalsIgnoreCase("")) {
//
//            int spinnerPositioncity = spinnerArrayAdapter.getPosition(citysreeer);
//            city_spinner.setSelection(spinnerPositioncity);
//
//        }

//        blur_reg_signup.visibility = View.GONE;
    }

    private fun GetPinid(position: Int): String {
        var sid = ""
        try {
            //Getting object of given index
            val json: JSONObject? = pinresult?.getJSONObject(position)

            //Fetching id from that object
            if (json != null) {
                sid = json.getString(Configs.KEY_PINID_BL)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //Returning the id
        return sid
    }//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array

//                    blur_reg1.visibility = View.GONE;

    //Creating a request queue

    //Adding request to the queue
    //            blur_reg1.visibility = View.VISIBLE;
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
            manager?.setPreferences(this@Catgryctivity, Constants.USER_CATGRY, sid)
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
                ArrayAdapter<String>(this@Catgryctivity, R.layout.custom_spiner_layout_cat,
                    categry!!
                )
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
            agentype?.setAdapter(dataAdapter5)
            val spinnerPosition: Int = dataAdapter5.getPosition(cat_Idd)
            agentype?.setSelection(spinnerPosition)

//            String language = preferences.getString("language", "");
//            if(!language.equalsIgnoreCase(""))
//            {
//                int spinnerPosition = arrayAdapter.getPosition(language);
//                spinner.setSelection(spinnerPosition);
//            }
//            String cat_prefs = manager?.getPreferences(Catgryctivity.this, Constants.USER_CATGRY);
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

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        var myOnClickListener: View.OnClickListener? = null
    }
}