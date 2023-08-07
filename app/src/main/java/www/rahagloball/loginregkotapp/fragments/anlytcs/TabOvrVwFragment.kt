package www.rahagloball.loginregkotapp.fragments.anlytcs



import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class TabOvrVwFragment : Fragment() {
    //    LineChart chart;
    var no_of_views_vids_got: TextView? = null
    var no_of_views_cts_got: TextView? = null
    var tr_sprts_count: TextView? = null
    var tr_spr_sprts_count: TextView? = null
    var tr_vds_count: TextView? = null
    var tr_cts_count: TextView? = null
    var tr_sprts_prcnt: TextView? = null
    var tr_spr_sprts_prcnt: TextView? = null
    var tr_vds_prcnt: TextView? = null
    var tr_cts_prcnt: TextView? = null
    var token: String? = null
    var manager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_ovrvw_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        //        chart = viewexp.findViewById(R.id.chart);
        no_of_views_vids_got = viewexp.findViewById<TextView>(R.id.no_of_views_ch_got)
        no_of_views_cts_got = viewexp.findViewById<TextView>(R.id.no_of_views_cts_got)
        tr_sprts_count = viewexp.findViewById<TextView>(R.id.tr_sprts_count)
        tr_spr_sprts_count = viewexp.findViewById<TextView>(R.id.tr_spr_sprts_count)
        tr_vds_count = viewexp.findViewById<TextView>(R.id.tr_vds_count)
        tr_cts_count = viewexp.findViewById<TextView>(R.id.tr_cts_count)
        tr_sprts_prcnt = viewexp.findViewById<TextView>(R.id.tr_sprts_prcnt)
        tr_spr_sprts_prcnt = viewexp.findViewById<TextView>(R.id.tr_spr_sprts_prcnt)
        tr_vds_prcnt = viewexp.findViewById<TextView>(R.id.tr_vds_prcnt)
        tr_cts_prcnt = viewexp.findViewById<TextView>(R.id.tr_cts_prcnt)
        no_of_views_vids_got?.text = "Your channel got no views from last 28 days"
        //        no_of_views_cts_got?.text=("Your channel got no views from last 28 days");
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
//        xAxis?.text=Color(Color.WHITE); // Change the color to red
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis?.text=Color(Color.WHITE);
////        YAxis yAxis = chart.getYAxis();
////        yAxis?.text=Color(Color.WHITE); // Change the color to red
//        // Set up the chart
//        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart.getAxisRight().setEnabled(false);
//        chart.getDescription().setEnabled(false);
//        chart.setTouchEnabled(false);
//        LineData data = new LineData();
//        data.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart.setData(data);
//        // Refresh the chart
//        chart.invalidate();
        getoverview()
        return viewexp
    }

    //    private LineDataSet getMoneyDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneyData = getMoneyData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneyData));
    //        }
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
    //        return dataSet;
    //    }
    //
    //    private LineDataSet getMoneySpentDataSet(int currentDayOfMonth) {
    //        ArrayList<Entry> entries = new ArrayList<>();
    //        for (int i = 0; i < 7; i++) {
    //            float moneySpentData = getMoneySpentData(currentDayOfMonth + i);
    //            entries.add(new Entry(i, moneySpentData));
    //        }
    //        LineDataSet dataSet = new LineDataSet(entries, "Support");
    //        dataSet.setColor(Color.RED);
    //        dataSet.setValueTextColor(Color.WHITE);
    //        dataSet.setCircleColor(Color.RED);
    //        dataSet.setLineWidth(2f);
    //        dataSet.setCircleRadius(4f);
    //        dataSet.setDrawValues(false);
    //        dataSet.setDrawCircleHole(false);
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

    private fun getoverview() {
//        progressBar_up.visibility = View.VISIBLE;
        val url: String? = Configs.BASE_URL2 + "overall-analytics"
        RetrofitClient.getClient().getovralanlytcs(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OvralAnlytcsPojo?>(no_of_views_vids_got) {
             override   fun onResponse(
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
                                        val tr_sprts_count_str: String? =
                                            ovralAnlytcsone.supporterCount
                                        val tr_spr_sprts_count_str: String? =
                                            ovralAnlytcsone.superSupporterCount
                                        val tr_vds_count_str: String? =
                                            ovralAnlytcsone.videoViewsCount
                                        val tr_cts_count_str: String? =
                                            ovralAnlytcsone.cutieViewsCount
                                        val tr_sprts_prcnt_str: String? =
                                            ovralAnlytcsone.supporterPercent
                                        val tr_spr_sprts_prcnt_str: String? =
                                            ovralAnlytcsone.superSupporterPercent
                                        val tr_vds_prcnt_str: String? =
                                            ovralAnlytcsone.videoViewsPercant
                                        val tr_cts_prcnt_str: String? =
                                            ovralAnlytcsone.cutieViewsPercant
                                        tr_sprts_count?.text=(tr_sprts_count_str)
                                        tr_spr_sprts_count?.text=(tr_spr_sprts_count_str)
                                        tr_vds_count?.text=(tr_vds_count_str)
                                        tr_cts_count?.text=(tr_cts_count_str)
                                        tr_sprts_prcnt?.text=(tr_sprts_prcnt_str)
                                        tr_spr_sprts_prcnt?.text=(tr_spr_sprts_prcnt_str)
                                        tr_vds_prcnt?.text=(tr_vds_prcnt_str)
                                        tr_cts_prcnt?.text=(tr_cts_prcnt_str)
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