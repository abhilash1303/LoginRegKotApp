package www.rahagloball.loginregkotapp.fragments.anlytcs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.ovralanlyts.OvralAnlytcs
import www.rahagloball.loginregkotapp.models.ovralanlyts.OvralAnlytcsPojo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TabDmgrfcsFragment1 : Fragment() {
    //    LineChart chart;
    var cnt_prcntg_spnr: Spinner? = null
    var filter_all_vids_str = arrayOf("Count", "Percentage")
    var token: String? = null
    var manager: SessionManager? = null
    var sprts_count_bnglr: TextView? = null
    var spr_sprts_count_bnglr: TextView? = null
    var sprts_prcnt_cni: TextView? = null
    var spr_sprts_prcnt_cni: TextView? = null
    var sprts_prcnt_mumb: TextView? = null
    var spr_sprts_prcn_mumb: TextView? = null
    var sprts_prcnt_hyd: TextView? = null
    var spr_sprts_prcnt_hyd: TextView? = null
    var sprts_cnt_surat: TextView? = null
    var spr_sprts_cnt_surat: TextView? = null
    var sprts_cnt_delhi: TextView? = null
    var spr_sprts_cnt_delhi: TextView? = null
    var sprts_cnt_ahmdbd: TextView? = null
    var spr_sprts_cnt_ahmdbd: TextView? = null
    var sprts_cnt_pune: TextView? = null
    var spr_sprts_cnt_pune: TextView? = null
    var sprts_cnt_klkta: TextView? = null
    var spr_sprts_cnt_klkta: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_dmgrfcs_layout1, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }

//        chart = viewexp.findViewById(R.id.chart_cts);
        cnt_prcntg_spnr = viewexp.findViewById<Spinner>(R.id.cnt_prcntg_spnr)
        sprts_count_bnglr = viewexp.findViewById<TextView>(R.id.sprts_count_bnglr)
        spr_sprts_count_bnglr = viewexp.findViewById<TextView>(R.id.spr_sprts_count_bnglr)
        sprts_prcnt_cni = viewexp.findViewById<TextView>(R.id.sprts_prcnt_cni)
        spr_sprts_prcnt_cni = viewexp.findViewById<TextView>(R.id.spr_sprts_prcnt_cni)
        sprts_prcnt_mumb = viewexp.findViewById<TextView>(R.id.sprts_prcnt_mumb)
        spr_sprts_prcn_mumb = viewexp.findViewById<TextView>(R.id.spr_sprts_prcn_mumb)
        sprts_prcnt_hyd = viewexp.findViewById<TextView>(R.id.sprts_prcnt_hyd)
        spr_sprts_prcnt_hyd = viewexp.findViewById<TextView>(R.id.spr_sprts_prcnt_hyd)
        sprts_cnt_surat = viewexp.findViewById<TextView>(R.id.sprts_cnt_surat)
        spr_sprts_cnt_surat = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt_surat)
        sprts_cnt_delhi = viewexp.findViewById<TextView>(R.id.sprts_cnt_delhi)
        spr_sprts_cnt_delhi = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt_delhi)
        sprts_cnt_ahmdbd = viewexp.findViewById<TextView>(R.id.sprts_cnt_ahmdbd)
        spr_sprts_cnt_ahmdbd = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt_ahmdbd)
        sprts_cnt_pune = viewexp.findViewById<TextView>(R.id.sprts_cnt_pune)
        spr_sprts_cnt_pune = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt_pune)
        sprts_cnt_klkta = viewexp.findViewById<TextView>(R.id.sprts_cnt_klkta)
        spr_sprts_cnt_klkta = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt_klkta)


        // Get the current date
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
//        xAxis?.text =Color(Color.WHITE); // Change the color to red
//
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis?.text =Color(Color.WHITE);
//
////        YAxis yAxis = chart.getYAxis();
////        yAxis?.text =Color(Color.WHITE); // Change the color to red
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
        return viewexp
    }

    private fun getcitycnt() {

//        progressBar_up.visibility = View.VISIBLE;
        val url: String = Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(cnt_prcntg_spnr) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
//                        progressBar_up.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>?=response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val citystr: String? = ovralAnlytcsone.city
                                        if (citystr == "Bangalore") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val tr_spr_sprts_count_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_count_bnglr?.text = tr_sprts_count_str
                                            spr_sprts_count_bnglr?.text =(tr_spr_sprts_count_str)
                                        }
                                        if (citystr == "Hyderabad") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_prcnt_hyd?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt_hyd?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Kolkata") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_cnt_klkta?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_klkta?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Pune") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_cnt_pune?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_pune?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Chennai") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_prcnt_cni?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt_cni?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Mumbai") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_prcnt_mumb?.text =(sprt_cnt25_str)
                                            spr_sprts_prcn_mumb?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Delhi") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_cnt_delhi?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_delhi?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Ahmedabad") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_cnt_ahmdbd?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_ahmdbd?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Surat") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterCount
                                            sprts_cnt_surat?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_surat?.text =(spr_sprt_cnt25_str)
                                        }
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

//        progressBar_up.visibility = View.VISIBLE;
        val url: String = Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(cnt_prcntg_spnr) {
               override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
//                        progressBar_up.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>?=response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val citystr: String? = ovralAnlytcsone?.city
                                        if (citystr == "Bangalore") {
                                            val tr_sprts_count_str: String? = ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_count_bnglr?.text =(tr_sprts_count_str)
                                            spr_sprts_count_bnglr?.text =(tr_spr_sprts_count_str)
                                        }
                                        if (citystr == "Hyderabad") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_prcnt_hyd?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt_hyd?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Kolkata") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_cnt_klkta?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_klkta?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Pune") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_cnt_pune?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_pune?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Chennai") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_prcnt_cni?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt_cni?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Mumbai") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_prcnt_mumb?.text =(sprt_cnt25_str)
                                            spr_sprts_prcn_mumb?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Delhi") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_cnt_delhi?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_delhi?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Ahmedabad") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_cnt_ahmdbd?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_ahmdbd?.text =(spr_sprt_cnt25_str)
                                        }
                                        if (citystr == "Surat") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            sprts_cnt_surat?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt_surat?.text =(spr_sprt_cnt25_str)
                                        }
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