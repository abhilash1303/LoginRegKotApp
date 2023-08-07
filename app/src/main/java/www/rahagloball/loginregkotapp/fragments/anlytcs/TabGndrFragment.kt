package www.rahagloball.loginregkotapp.fragments.anlytcs

//import android.view.View
//import androidx.fragment.app.Fragment
//import retrofit2.Call
//import retrofit2.Response
//import www.natlrnsnew.nationlearns.Configuration.Configs
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Locale


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
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


class TabGndrFragment : Fragment() {
    //    LineChart chart;
    var tr_sprts_count: TextView? = null
    var tr_spr_sprts_count: TextView? = null
    var empsts_count: TextView? = null
    var mrtlsts_count: TextView? = null
    var cnt_prnctg_spnr: Spinner? = null
    var male_feml_spnr: Spinner? = null
    var mrdsts_spnr: Spinner? = null
    var empsts_spnr: Spinner? = null
    var male_feml_spnr_prcnt: Spinner? = null
    var empsts_spnr_prcnt: Spinner? = null
    var mrdsts_spnr_prcnt: Spinner? = null
    var filter_cnt_prcnt = arrayOf("Count", "Percentage")
    var filter_mf_str = arrayOf("Male", "Female")
    var filter_cnt_mrysts = arrayOf("Married", "Unmarried", "Widow")
    var filter_cnt_empsts = arrayOf("Employed", "Self Employed", "Student", "Others")
    var sprt_cnt25: TextView? = null
    var spr_sprt_cnt25: TextView? = null
    var empsts_cnt25: TextView? = null
    var mrtlsts_cnt25: TextView? = null
    var sprts_cnt35: TextView? = null
    var spr_sprts_cnt35: TextView? = null
    var mrtlsts_cnt35: TextView? = null
    var empsts_cnt35: TextView? = null
    var mrtlsts_cnt50: TextView? = null
    var sprts_cnt50: TextView? = null
    var spr_sprts_cnt50: TextView? = null
    var empsts_cnt50: TextView? = null
    var tr_sprts_prcent: TextView? = null
    var tr_spr_sprts_prcent: TextView? = null
    var empsts_prcent: TextView? = null
    var mrtlsts_prcent: TextView? = null
    var sprt_prcnt25: TextView? = null
    var spr_sprt_prcnt25: TextView? = null
    var empsts_prcnt25: TextView? = null
    var mrtlsts_prcnt25: TextView? = null
    var sprts_prcnt35: TextView? = null
    var spr_sprts_prcnt35: TextView? = null
    var mrtlsts_prcnt35: TextView? = null
    var empsts_prcnt35: TextView? = null
    var sprts_prcnt50: TextView? = null
    var spr_sprts_prcnt50: TextView? = null
    var empsts_prcnt50: TextView? = null
    var mrtlsts_prcnt50: TextView? = null
    var fl_sts_prcnt: FrameLayout? = null
    var fl_sts_cnt: FrameLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_gndr_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        //        chart = viewexp.findViewById(R.id.chart_cts);
        tr_sprts_count = viewexp.findViewById<TextView>(R.id.tr_sprts_count)
        tr_spr_sprts_count = viewexp.findViewById<TextView>(R.id.tr_spr_sprts_count)
        empsts_count = viewexp.findViewById<TextView>(R.id.empsts_count)
        mrtlsts_count = viewexp.findViewById<TextView>(R.id.mrtlsts_count)
        sprt_cnt25 = viewexp.findViewById<TextView>(R.id.sprt_cnt25)
        spr_sprt_cnt25 = viewexp.findViewById<TextView>(R.id.spr_sprt_cnt25)
        empsts_cnt25 = viewexp.findViewById<TextView>(R.id.empsts_cnt25)
        mrtlsts_cnt25 = viewexp.findViewById<TextView>(R.id.mrtlsts_cnt25)
        sprts_cnt35 = viewexp.findViewById<TextView>(R.id.sprts_cnt35)
        spr_sprts_cnt35 = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt35)
        empsts_cnt35 = viewexp.findViewById<TextView>(R.id.empsts_cnt35)
        mrtlsts_cnt35 = viewexp.findViewById<TextView>(R.id.mrtlsts_cnt35)
        sprts_cnt50 = viewexp.findViewById<TextView>(R.id.sprts_cnt50)
        spr_sprts_cnt50 = viewexp.findViewById<TextView>(R.id.spr_sprts_cnt50)
        empsts_cnt50 = viewexp.findViewById<TextView>(R.id.empsts_cnt50)
        mrtlsts_cnt50 = viewexp.findViewById<TextView>(R.id.mrtlsts_cnt50)
        cnt_prnctg_spnr = viewexp.findViewById<Spinner>(R.id.cnt_prnctg_spnr)
        male_feml_spnr = viewexp.findViewById<Spinner>(R.id.male_feml_spnr)
        male_feml_spnr_prcnt = viewexp.findViewById<Spinner>(R.id.male_feml_spnr_prcnt)
        empsts_spnr = viewexp.findViewById<Spinner>(R.id.empsts_spnr)
        empsts_spnr_prcnt = viewexp.findViewById<Spinner>(R.id.empsts_spnr_prcnt)
        mrdsts_spnr = viewexp.findViewById<Spinner>(R.id.mrdsts_spnr)
        mrdsts_spnr_prcnt = viewexp.findViewById<Spinner>(R.id.mrdsts_spnr_prcnt)
        tr_sprts_prcent = viewexp.findViewById<TextView>(R.id.tr_sprts_prcent)
        tr_spr_sprts_prcent = viewexp.findViewById<TextView>(R.id.tr_spr_sprts_prcent)
        empsts_prcent = viewexp.findViewById<TextView>(R.id.empsts_prcent)
        mrtlsts_prcent = viewexp.findViewById<TextView>(R.id.mrtlsts_prcent)
        sprt_prcnt25 = viewexp.findViewById<TextView>(R.id.sprt_prcnt25)
        spr_sprt_prcnt25 = viewexp.findViewById<TextView>(R.id.spr_sprt_prcnt25)
        empsts_prcnt25 = viewexp.findViewById<TextView>(R.id.empsts_prcnt25)
        mrtlsts_prcnt25 = viewexp.findViewById<TextView>(R.id.mrtlsts_prcnt25)
        sprts_prcnt35 = viewexp.findViewById<TextView>(R.id.sprts_prcnt35)
        spr_sprts_prcnt35 = viewexp.findViewById<TextView>(R.id.spr_sprts_prcnt35)
        mrtlsts_prcnt35 = viewexp.findViewById<TextView>(R.id.mrtlsts_prcnt35)
        empsts_prcnt35 = viewexp.findViewById<TextView>(R.id.empsts_prcnt35)
        sprts_prcnt50 = viewexp.findViewById<TextView>(R.id.sprts_prcnt50)
        spr_sprts_prcnt50 = viewexp.findViewById<TextView>(R.id.spr_sprts_prcnt50)
        empsts_prcnt50 = viewexp.findViewById<TextView>(R.id.empsts_prcnt50)
        mrtlsts_prcnt50 = viewexp.findViewById<TextView>(R.id.mrtlsts_prcnt50)
        fl_sts_prcnt = viewexp.findViewById<FrameLayout>(R.id.fl_sts_prcnt)
        fl_sts_cnt = viewexp.findViewById<FrameLayout>(R.id.fl_sts_cnt)


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
//        xAxis.setTextColor(Color.WHITE); // Change the color to red


//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTextColor(Color.WHITE);

//        YAxis yAxis = chart.getYAxis();
//        yAxis.setTextColor(Color.WHITE); // Change the color to red


        // Set up the chart
//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart.getAxisRight().setEnabled(false);
//        chart.getDescription().setEnabled(false);
//        chart.setTouchEnabled(false);

//        LineData data = new LineData();
//        data.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart.setData(data);
//
//        // Refresh the chart
//        chart.invalidate();
//
//        getoverviewcnt();
        val dataAdapter5: ArrayAdapter<String>? =
            activity?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, filter_mf_str) }
        if (dataAdapter5 != null) {
            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
        }
        male_feml_spnr?.adapter = (dataAdapter5)
        male_feml_spnr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getmalecnt()
                    1 -> getfemalecnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dataAdapter11: ArrayAdapter<String>? =
            activity?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, filter_mf_str) }
        if (dataAdapter11 != null) {
            dataAdapter11.setDropDownViewResource(R.layout.custom_spiner_layout)
        }
        male_feml_spnr_prcnt?.adapter = (dataAdapter11)
        male_feml_spnr_prcnt?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getmaleprcnt()
                    1 -> getfemaleprcnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dataAdapter6: ArrayAdapter<String>? =
            activity?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.custom_spiner_layout,
                    filter_cnt_mrysts
                )
            }
        dataAdapter6?.setDropDownViewResource(R.layout.custom_spiner_layout)
        mrdsts_spnr?.adapter = dataAdapter6
        mrdsts_spnr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getmarriedcnt()
                    1 -> getunmarriedcnt()
                    2 -> getwidowcnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dataAdapter33: ArrayAdapter<String>? =
            activity?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.custom_spiner_layout,
                    filter_cnt_mrysts
                )
            }
        dataAdapter33?.setDropDownViewResource(R.layout.custom_spiner_layout)
        mrdsts_spnr_prcnt?.adapter = dataAdapter33
        mrdsts_spnr_prcnt?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getmarriedprcnt()
                    1 -> getunmarriedprcnt()
                    2 -> getwidowprcnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dataAdapter: ArrayAdapter<String>? =
            activity?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.custom_spiner_layout,
                    filter_cnt_empsts
                )
            }
        dataAdapter?.setDropDownViewResource(R.layout.custom_spiner_layout)
        empsts_spnr?.adapter = dataAdapter
        empsts_spnr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getempldycnt()
                    1 -> getslefempldycnt()
                    2 -> getstudentcnt()
                    3 -> getothrscnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dataAdapter77: ArrayAdapter<String>? =
            activity?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.custom_spiner_layout,
                    filter_cnt_empsts
                )
            }
        dataAdapter77?.setDropDownViewResource(R.layout.custom_spiner_layout)
        empsts_spnr_prcnt?.adapter = dataAdapter77
        empsts_spnr_prcnt?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> getempldyprcnt()
                    1 -> getslefempldyprcnt()
                    2 -> getstudentprcnt()
                    3 -> getothrsprcnt()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        return viewexp
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
    //        LineDataSet dataSet = new LineDataSet(entries, "Male");
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
    //        LineDataSet dataSet = new LineDataSet(entries, "Female");
    //        dataSet.setColor(Color.RED);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.RED);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
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

    private fun getoverviewcnt() {

//        progressBar_up.visibility = View.VISIBLE;
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override  fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
//                        progressBar_up.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.empStatus
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.meritalStatus
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.empStatus
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.meritalStatus
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.empStatus
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.meritalStatus
                                            sprts_cnt35?.text = sprt_cnt25_str
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.empStatus
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.meritalStatus
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getoverviewprcnt() {
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
//                        progressBar_up.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.empStatus
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.meritalStatus
                                            tr_sprts_count?.text = tr_sprts_count_str
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    //gender count
    private fun getmalecnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
//                                                        val res_mv: String? = response.body()?.status
                            val res_mv: String? = response.body()?.status

                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.maleCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.maleCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.maleCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getfemalecnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femaleCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    //    emp status count
    private fun getempldycnt() {

//        progressBar_up.visibility = View.VISIBLE;
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
//                        progressBar_up.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.employedCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getslefempldycnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.selfEmployedCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getstudentcnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.studentCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.studentCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            sprts_cnt35?.text = sprt_cnt25_str
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getothrscnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.othersCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.othersCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    //mrtal status count
    private fun getmarriedcnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprts_cnt35?.setText(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getunmarriedcnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            sprts_cnt35?.text =(sprt_cnt25_str)
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    private fun getwidowcnt() {
        fl_sts_cnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_cnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.widowCount
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.widowCount
                                            tr_sprts_count?.text = (tr_sprts_count_str)
                                            tr_spr_sprts_count?.text = (tr_spr_sprts_count_str)
                                            empsts_count?.text = (empsts_count_str)
                                            mrtlsts_count?.text = (mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            sprt_cnt25?.text = (sprt_cnt25_str)
                                            spr_sprt_cnt25?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt25?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt25?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone?.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            sprts_cnt35?.text = sprt_cnt25_str
                                            spr_sprts_cnt35?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt35?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt35?.text = (mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterCount
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterCount
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowCount
                                            sprts_cnt50?.text = (sprt_cnt25_str)
                                            spr_sprts_cnt50?.text = (spr_sprt_cnt25_str)
                                            empsts_cnt50?.text = (empsts_cnt25_str)
                                            mrtlsts_cnt50?.text = (mrtlsts_cnt25_str)
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

    //gender %
    private fun getmaleprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.malePercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.malePercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.malePercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getfemaleprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.setText(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.setText(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.setText(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.femalePercent
                                            sprts_prcnt50?.setText(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.setText(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.setText(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.setText(mrtlsts_cnt25_str)
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

    //employment status %
    private fun getempldyprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.employedPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getslefempldyprcnt() {

//        progressBar_up.visibility = View.VISIBLE;
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getstudentprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.studentPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getothrsprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.othersPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    //marital status %
    private fun getmarriedprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.marriedPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getunmarriedprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone?.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            val mrtlsts_count_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.unmarriedPercent
                                            sprts_prcnt50?.text =(sprt_cnt25_str)
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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

    private fun getwidowprcnt() {
        fl_sts_prcnt?.visibility = View.VISIBLE
        val url: String= Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(mrtlsts_count) {
                override fun onResponse(
                    call: Call<OvralAnlytcsPojo?>?,
                    response: Response<OvralAnlytcsPojo?>
                ) {
                    fl_sts_prcnt?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                val ovralAnlytcs: List<OvralAnlytcs>? = response.body()?.data
                                if (ovralAnlytcs != null) {
                                    for (i in ovralAnlytcs.indices) {
                                        val ovralAnlytcsone: OvralAnlytcs = ovralAnlytcs[i]
                                        val agegrpstr: String? = ovralAnlytcsone.ageGroup
                                        if (agegrpstr == "15 - 25") {
                                            val tr_sprts_count_str: String? = ovralAnlytcsone.supporterPercent
                                            val tr_spr_sprts_count_str: String? = ovralAnlytcsone.superSupporterPercent
                                            val empsts_count_str: String? = ovralAnlytcsone.widowPercent
                                            val mrtlsts_count_str: String? = ovralAnlytcsone.widowPercent
                                            tr_sprts_prcent?.text =(tr_sprts_count_str)
                                            tr_spr_sprts_prcent?.text =(tr_spr_sprts_count_str)
                                            empsts_prcent?.text =(empsts_count_str)
                                            mrtlsts_prcent?.text =(mrtlsts_count_str)
                                        }
                                        if (agegrpstr == "25-35") {
                                            val sprt_cnt25_str: String? = ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? = ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            sprt_prcnt25?.text =(sprt_cnt25_str)
                                            spr_sprt_prcnt25?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt25?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt25?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "35-50") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            sprts_prcnt35?.text = (sprt_cnt25_str)
                                            spr_sprts_prcnt35?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt35?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt35?.text =(mrtlsts_cnt25_str)
                                        }
                                        if (agegrpstr == "50-75") {
                                            val sprt_cnt25_str: String? =
                                                ovralAnlytcsone.supporterPercent
                                            val spr_sprt_cnt25_str: String? =
                                                ovralAnlytcsone.superSupporterPercent
                                            val empsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            val mrtlsts_cnt25_str: String? =
                                                ovralAnlytcsone.widowPercent
                                            sprts_prcnt50?.text = sprt_cnt25_str
                                            spr_sprts_prcnt50?.text =(spr_sprt_cnt25_str)
                                            empsts_prcnt50?.text =(empsts_cnt25_str)
                                            mrtlsts_prcnt50?.text =(mrtlsts_cnt25_str)
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
}