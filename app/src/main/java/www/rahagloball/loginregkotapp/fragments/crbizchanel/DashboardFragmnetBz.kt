package www.rahagloball.loginregkotapp.fragments.crbizchanel

//import android.content.Context


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.CrtrZoneActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.milestonebz.CutieDetailBz
import www.rahagloball.loginregkotapp.models.milestonebz.MilestoneBz
import www.rahagloball.loginregkotapp.models.milestonebz.MilestoneBzPojo
import www.rahagloball.loginregkotapp.models.milestonebz.Milestone_Ss_Bz
import www.rahagloball.loginregkotapp.models.milestonebz.SingleCutieDataBz
import www.rahagloball.loginregkotapp.models.milestonebz.SingleVideoDataBz
import www.rahagloball.loginregkotapp.models.milestonebz.VideoDetailBz
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DashboardFragmnetBz : Fragment(), View.OnClickListener {
    var dfgdb: TextView? = null
    var token: String? = null
    var chanl_idtosend: String? = null
    var manager: SessionManager? = null
    var highest_vid_title: TextView? = null
    var highest_vid_vws: TextView? = null
    var total_vids_vws: TextView? = null
    var vds_count: TextView? = null
    var cts_vids_count: TextView? = null
    var cts_vws: TextView? = null
    var cts_higt_vws: TextView? = null
    var highest_cts_title: TextView? = null
    var bundle: Bundle? = null
    var milestone_info: ImageView? = null
    var exl_sr_close: ImageView? = null
    var card_view_milestone: CardView? = null
    var rankDialog: Dialog? = null
    var cts_view_graph: TextView? = null
    var vides_view_graph: TextView? = null
    var milestone_details_sup_sprt: TextView? = null
    var milestone_details_sprt: TextView? = null

    //    LineChart chart, chart_cts;
    var all_vid_spinner: Spinner? = null
    var filer_ch_bizch = arrayOf("My Channel", "My Business Channel")
    var customDialog: CustomDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.cz_dashbrd_layoutbz, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        customDialog = CustomDialog(activity)
        val activity: CrtrZoneActivity? = activity as CrtrZoneActivity?
        chanl_idtosend = activity?.getMyBusinessChannelData()
        //        chart = viewexp.findViewById(R.id.chart);
//        chart_cts = viewexp.findViewById(R.id.chart_cts);
        highest_vid_title = viewexp.findViewById<TextView>(R.id.highest_vid_title)
        highest_vid_vws = viewexp.findViewById<TextView>(R.id.highest_vid_vws)
        total_vids_vws = viewexp.findViewById<TextView>(R.id.total_vids_vws)
        vds_count = viewexp.findViewById<TextView>(R.id.vds_count)
        milestone_info = viewexp.findViewById<ImageView>(R.id.milestone_info)
        cts_vids_count = viewexp.findViewById<TextView>(R.id.cts_vids_count)
        cts_vws = viewexp.findViewById<TextView>(R.id.cts_vws)
        cts_higt_vws = viewexp.findViewById<TextView>(R.id.cts_higt_vws)
        highest_cts_title = viewexp.findViewById<TextView>(R.id.highest_cts_title)
        cts_view_graph = viewexp.findViewById<TextView>(R.id.cts_view_graph)
        milestone_details_sprt = viewexp.findViewById<TextView>(R.id.milestone_details_sprt)
        milestone_details_sup_sprt = viewexp.findViewById<TextView>(R.id.milestone_details_sup_sprt)
        all_vid_spinner = viewexp.findViewById<Spinner>(R.id.all_vid_spinner)
        card_view_milestone = viewexp.findViewById<CardView>(R.id.card_view_milestone)
        vides_view_graph = viewexp.findViewById<TextView>(R.id.vides_view_graph)
        milestone_info?.setOnClickListener(this)
        vides_view_graph?.setOnClickListener(this)


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
//
//        chart_cts.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        chart_cts.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart_cts.getAxisRight().setEnabled(false);
//        chart_cts.getDescription().setEnabled(false);
//        chart_cts.setTouchEnabled(false);
//
//        // Get the data for the chart
//        LineData data = new LineData();
//        data.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart.setData(data);
//
//        // Refresh the chart
//        chart.invalidate();
//
//        // Get the data for the chart
//        LineData data_cts = new LineData();
//        data_cts.addDataSet(getMoneyDataSet(currentDayOfMonth));
//        data_cts.addDataSet(getMoneySpentDataSet(currentDayOfMonth));
//        chart_cts.setData(data_cts);
//
//        // Refresh the chart
//        chart_cts.invalidate();
        val dataAdapter4: ArrayAdapter<String>? =
            getActivity()?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, filer_ch_bizch) }
        dataAdapter4?.setDropDownViewResource(R.layout.custom_spiner_layout)
        all_vid_spinner?.setAdapter(dataAdapter4)
        all_vid_spinner?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    1 -> {}
                    else -> milestiones
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        milestiones
        return viewexp
    }

    //
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
    ////        dataSet.setSpaceTop(20f);
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
    }//                                    if (sprts_count == 1000){

    //                                        milestone_details_sprt.setText("1 Milestone Achieved");
//                                    }
    //                            response.body().getStatus()
//                            List<Milestone> milestoneList=response.body().getData();
//                        blur_reg_sprt.visibility = View.GONE;
//                        customDialog.dismiss();
    //        blur_reg_sprt.visibility = View.VISIBLE;
//        customDialog.show();
    val milestiones: Unit
        get() {
//        blur_reg_sprt.visibility = View.VISIBLE;
//        customDialog.show();
            val url: String = Configs.BASE_URL2 + "mycreater-prfl/bizchannel"
            RetrofitClient.getClient().getmilestnbz(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<MilestoneBzPojo?>(vides_view_graph) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<MilestoneBzPojo?>,
                        response: Response<MilestoneBzPojo?>
                    ) {

//                        blur_reg_sprt.visibility = View.GONE;
//                        customDialog.dismiss();
                        try {
                            val milestnres: String ? = response.body()?.status
                            if (milestnres == "200") {
                                val milestnee: List<MilestoneBz>?=response.body()?.data
                                if (milestnee != null) {
                                    for (i in milestnee.indices) {
                                        val vid_all_views: Int? =
                                            milestnee[i].viewsCountOnTotalVideos
                                        total_vids_vws?.setText(vid_all_views.toString())
                                        val cutie_all_views: Int? =
                                            milestnee[i].viewsCountOnTotalCuties
                                        cts_vws?.setText(cutie_all_views.toString())
                                        val milestone_vc: MilestoneBz = milestnee[i]
                                        val singleVideoData: SingleVideoDataBz? =
                                            milestone_vc.singleVideoData
                                        if (singleVideoData != null) {
                                            val single_vd_cnt: Int? =
                                                singleVideoData.highestViewsCount
                                            highest_vid_vws?.setText(single_vd_cnt.toString())
                                            val videoDetail: VideoDetailBz? =
                                                singleVideoData.videoDetail
                                            val single_vd_title: String? = videoDetail?.title
                                            highest_vid_title?.setText(single_vd_title)
                                        }
                                        val singleCutieData: SingleCutieDataBz? =
                                            milestone_vc.singleCutieData
                                        if (singleCutieData != null) {
                                            val single_cts_cnt: Int? =
                                                singleCutieData?.highestViewsCount
                                            cts_higt_vws?.setText(single_cts_cnt.toString())
                                            val ctsDetail: CutieDetailBz? =
                                                singleCutieData?.cutieDetail
                                            val single_cts_title: String? = ctsDetail?.title
                                            highest_cts_title?.setText(single_cts_title)
                                        }
                                        val milestone_ss: Milestone_Ss_Bz? = milestone_vc?.milestone
                                        val sprts_count: Int? = milestone_ss?.totalSupporters
                                        val sup_sprts_count: Int? =
                                            milestone_ss?.totalSuperSupporters
                                        val totl_vids_uplded: Int? = milestone_ss?.totalVideosCount
                                        val totl_cts_uplded: Int? = milestone_ss?.totalCutiesCount
                                        vds_count?.setText(totl_vids_uplded.toString())
                                        cts_vids_count?.setText(totl_cts_uplded.toString())
                                        milestone_details_sprt?.setText("$sprts_count/1000 Supporters")
                                        //                                    if (sprts_count == 1000){
                            //                                        milestone_details_sprt.setText("1 Milestone Achieved");
                            //                                    }
                                        milestone_details_sup_sprt?.setText("$sup_sprts_count/1000 Super Supporters")
                                    }
                                }
                            } else {
                                Toast.makeText(activity, "No data", Toast.LENGTH_SHORT).show()
                            }
                            //                            response.body().getStatus()
//                            List<Milestone> milestoneList=response.body().getData();
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.milestone_info -> {
                rankDialog = Dialog(requireActivity(), R.style.FullWidthDialogTheme)
                rankDialog?.setContentView(R.layout.dlg_filter_buylds)
                rankDialog!!.setCancelable(true)
                val window = rankDialog!!.window
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                exl_sr_close = rankDialog!!.findViewById<ImageView>(R.id.exl_sr_close)
                exl_sr_close?.setOnClickListener(View.OnClickListener { v3: View? -> rankDialog!!.dismiss() })
                rankDialog!!.setCanceledOnTouchOutside(false)
                rankDialog!!.show()
            }
        }
    }
}