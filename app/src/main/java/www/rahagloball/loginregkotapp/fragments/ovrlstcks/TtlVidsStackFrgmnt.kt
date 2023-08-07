package www.rahagloball.loginregkotapp.fragments.ovrlstcks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.camera.UploadVideoActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.multispinnerrr.KeyPairBoolData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale



class TtlVidsStackFrgmnt : Fragment() {
    //    private BarChart barChart;
    var token: String? = null
    var manager: SessionManager? = null
    var video_catgryy: Spinner? = null
    private var vidcatresult: JSONArray? = null
    var vidcategry: ArrayList<String>? = null
    var vidcatgryId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ttlvidstck_layout, container, false)

//        barChart = viewexp.findViewById(R.id.bar_chart);
        video_catgryy = viewexp.findViewById<Spinner>(R.id.video_catgryy)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        // Create a list of 7 days starting from today
        val labels = ArrayList<String>()
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        for (i in 0..6) {
            val label = sdf.format(calendar.time)
            labels.add(label)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        // Create a list of random values for the y-axis
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 7; i++) {
//            float value = random.nextFloat() * 100;
//            entries.add(new BarEntry(i, value));
//        }
//        // Set the data for the bar chart
//        BarDataSet dataSet = new BarDataSet(entries, "All Videos");
//        BarData data = new BarData(dataSet);
//        barChart.setData(data);
//        // Set the labels for the x-axis
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1f);
//        xAxis.setDrawGridLines(false);
//        // Set the values for the y-axis
//        YAxis yAxis = barChart.getAxisLeft();
//        yAxis.setAxisMinimum(0f);
//        yAxis.setDrawGridLines(false);
//        // Disable the right y-axis
//        barChart.getAxisRight().setEnabled(false);
//        // Set the chart description
//        Description description = new Description();
//        description.setText("");
//        barChart.setDescription(description);
//
//        // Set other chart settings as needed
//        barChart.setTouchEnabled(true);
//        barChart.setDragEnabled(true);
//        barChart.setScaleEnabled(true);
//        barChart.animateY(1000);
//        barChart.invalidate();
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
        vidCtgryData()
        return viewexp
    }//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array

//                    progressBar.cancel();

    //Creating a request queue

    //Adding request to the queue
//            progressBar.show();
    //    =================================================
    //    vid category start

    private fun vidCtgryData() {
        val url: String = Configs.VID_CATGRY_URL
        val stringRequest = token?.let {
            UploadVideoActivity.CustomStringRequest(
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
        val requestQueue: RequestQueue = Volley.newRequestQueue(activity)

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


            val dataAdapter5 =
                activity?.let { ArrayAdapter(it, R.layout.custom_spiner_layout, vidcategry!!) }
            dataAdapter5?.setDropDownViewResource(R.layout.custom_spiner_layout)
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
//
//    private val vidCtgryData: Unit
//        private get() {
//            try {
//
////            progressBar.show();
//                val url: String = Configs.VID_CATGRY_URL
//                val stringRequest: StringRequest =
//                    object : StringRequest(url, Response.Listener<String> { response: String? ->
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
//
////                    progressBar.cancel();
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    },
//                        Response.ErrorListener { error: VolleyError? -> }) {
//                        val headers: Map<String, String>
//                            get() {
//                                val header = HashMap<String, String>()
//                                header["Accept"] = "application/json"
//                                header["Authorization"] = "Bearer $token"
//                                return header
//                            }
//                    }
//                stringRequest.retryPolicy = DefaultRetryPolicy(
//                    Configs.MY_SOCKET_TIMEOUT_MS,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//                )
//
//                //Creating a request queue
//                val requestQueue: RequestQueue = Volley.newRequestQueue(activity)
//
//                //Adding request to the queue
//                requestQueue.add<String>(stringRequest)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//    private fun GetVidcatid(position: Int): String {
//        var sid = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject = vidcatresult.getJSONObject(position)
//
//            //Fetching id from that object
//            sid = json.getString(Configs.KEY_VIDCTRGYID)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }
//
//    private fun getVidCtgry(j: JSONArray?) {
//        try {
//            //Traversing through all the items in the json array
//            vidcategry = ArrayList()
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//                    //Adding the name of the student to array list
//                    vidcategry!!.add(json.getString(Configs.KEY_VIDCTRGYNAME))
//                    val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
//                    for (k in vidcategry!!.indices) {
//                        val h = KeyPairBoolData()
//                        h.setId(k + 1)
//                        h.setName(vidcategry!![k])
//                        h.setSelected(false)
//                        listArray0.add(h)
//                    }
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//            val dataAdapter5: ArrayAdapter<String> =
//                ArrayAdapter<String>(activity, R.layout.custom_spiner_layout, vidcategry)
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
//            video_catgryy.setAdapter(dataAdapter5)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    } //


//  vid category end
    //    =================================================
}