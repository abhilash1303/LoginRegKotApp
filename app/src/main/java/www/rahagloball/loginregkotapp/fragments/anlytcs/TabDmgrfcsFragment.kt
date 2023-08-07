package www.rahagloball.loginregkotapp.fragments.anlytcs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.TabDmgrfcntAdapter
import www.rahagloball.loginregkotapp.adapters.TabDmgrfpercntAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.statecityss.StCtySprtSupr
import www.rahagloball.loginregkotapp.models.statecityss.StCtySprtSuprPojo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TabDmgrfcsFragment : Fragment() {
    //    LineChart chart;
    var cnt_prcntg_spnr: Spinner? = null
    var filter_all_vids_str = arrayOf("Count", "Percentage")
    var token: String? = null
    var manager: SessionManager? = null
    var recyclerView: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var tabDmgrfAdapter_count: TabDmgrfcntAdapter? = null
    var tabDmgrfAdapter_percnt: TabDmgrfpercntAdapter? = null
    var dmgrfy_pb: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_dmgrfcs_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }

//        chart = viewexp.findViewById(R.id.chart_cts);
        cnt_prcntg_spnr = viewexp.findViewById<Spinner>(R.id.cnt_prcntg_spnr)
        recyclerView = viewexp.findViewById<RecyclerView>(R.id.rv_st_cty_dmgrfs)
        dmgrfy_pb = viewexp.findViewById<ProgressBar>(R.id.dmgrfy_pb)
        layoutManager = LinearLayoutManager(activity)
        recyclerView?.setLayoutManager(layoutManager)
        recyclerView?.setItemAnimator(DefaultItemAnimator())
        val calendar = Calendar.getInstance()
        val currentDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

        // Generate the labels for the X-axis (7 days starting from today)
        val labels = ArrayList<String>()
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        for (i in 0..6) {
            calendar[Calendar.DAY_OF_MONTH] = currentDayOfMonth + i
            val label = sdf.format(calendar.time)
            labels.add(label)
        }

//        XAxis xAxis = chart.getXAxis();
//        xAxis.setTextColor(Color.WHITE); // Change the color to red

//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTextColor(Color.WHITE);
//
////        YAxis yAxis = chart.getYAxis();
////        yAxis.setTextColor(Color.WHITE); // Change the color to red
//
//        // Set up the chart
//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart.getAxisRight().setEnabled(false);
//        chart.getDescription().setEnabled(false);
//        chart.setTouchEnabled(false);
//
//        LineData data = new LineData();
//        data.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart.setData(data);
//
//        // Refresh the chart
//        chart.invalidate();
        val dataAdapter4: ArrayAdapter<String>? =
            activity?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, filter_all_vids_str) }
        dataAdapter4?.setDropDownViewResource(R.layout.custom_spiner_layout)
        cnt_prcntg_spnr?.adapter = dataAdapter4
        cnt_prcntg_spnr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getcitycnt()
                    1 -> getcityprcnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        getcitycnt()
        return viewexp
    }

    //
    private fun getcitycnt() {
        dmgrfy_pb?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "top-cities/bysupporters"
        RetrofitClient.getClient().getstctydemkss(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<StCtySprtSuprPojo?>(cnt_prcntg_spnr) {
                override fun onResponse(
                    call: Call<StCtySprtSuprPojo?>,
                    response: Response<StCtySprtSuprPojo?>
                ) {
                    dmgrfy_pb?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val stCtySprtSuprs: List<StCtySprtSupr>?= response.body()!!.data
                                if (stCtySprtSuprs != null) {
                                    if (stCtySprtSuprs.isEmpty()) {
                                        recyclerView!!.visibility = View.GONE
                                        //                                        img_not_foundd.visibility = View.VISIBLE;
                                    } else {
                                        recyclerView!!.visibility = View.VISIBLE
                                        //                                        img_not_foundd.visibility = View.GONE;
                                        tabDmgrfAdapter_count =
                                            TabDmgrfcntAdapter(stCtySprtSuprs, activity!!)
                                        recyclerView!!.adapter = tabDmgrfAdapter_count
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

    private fun getcityprcnt() {
        dmgrfy_pb?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "top-cities/bysupporters"
        RetrofitClient.getClient().getstctydemkss(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<StCtySprtSuprPojo?>(cnt_prcntg_spnr) {
               override fun onResponse(
                    call: Call<StCtySprtSuprPojo?>?,
                    response: Response<StCtySprtSuprPojo?>
                ) {
                   dmgrfy_pb?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()!!.status
                            if (res_mv == "200") {
                                val stCtySprtSuprs: List<StCtySprtSupr>?= response.body()!!.data
                                if (stCtySprtSuprs != null) {
                                    if (stCtySprtSuprs.isEmpty()) {
                                        recyclerView!!.visibility = View.GONE
                                        //                                        img_not_foundd.visibility = View.VISIBLE;
                                    } else {
                                        recyclerView!!.visibility = View.VISIBLE
                                        //                                        img_not_foundd.visibility = View.GONE;
                                        tabDmgrfAdapter_percnt =
                                            activity?.let {
                                                TabDmgrfpercntAdapter(stCtySprtSuprs,
                                                    it
                                                )
                                            }
                                        recyclerView!!.adapter = tabDmgrfAdapter_percnt
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

    //    private LineDataSet getMoneyDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneyData = getMoneyData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneyData));
    //        }
    //
    //
    ////        chart.setExtraTopOffset(20f);
    //        LineDataSet dataSet = new LineDataSet(entries, "Views");
    //        dataSet.setColor(Color.BLUE);
    //        dataSet.setDrawValues(false);
    //        chart.setExtraBottomOffset(10f);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.BLUE);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
    //
    //        return dataSet;
    //    }
    //
    //    private LineDataSet getMoneySpentDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneySpentData = getMoneySpentData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneySpentData));
    //        }
    //
    //        LineDataSet dataSet = new LineDataSet(entries, "Support");
    //        dataSet.setColor(Color.RED);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.RED);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
    //
    //
    //        return dataSet;
    //    }
    private fun getMoneyData(dayOfMonth: Int): Float {
        // Here, you would retrieve the money data for the given day from your data source
        // For the sake of example, let's just return a random value between 0 and 100
        return (Math.random() * 100).toFloat()
    }

    private fun getMoneySpentData(dayOfMonth: Int): Float {
        // Here, you would retrieve the money spent data for the given day from your data source
        // For the sake of example, let's just return a random value between 0 and 100
        return (Math.random() * 100).toFloat()
    }
}