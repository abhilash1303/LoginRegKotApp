package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.CreateBizChannel
import www.rahagloball.loginregkotapp.activities.CrtrZoneActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.bzchexits.BizChExistsPojo
import www.rahagloball.loginregkotapp.models.milestone.Milestone
import www.rahagloball.loginregkotapp.models.milestone.MilestonePojo
import www.rahagloball.loginregkotapp.models.milestone.Milestone_Ss
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo
class MyChanelFragmnet : Fragment(), View.OnClickListener {
    var my_chanls: TextView? = null
    var mych_sprtr_cnt: TextView? = null
    var crt_biz_ch: TextView? = null
    var mychiddummy: TextView? = null
    var my_cutiess: TextView? = null
    var my_vidsss: TextView? = null
    var my_crses: TextView? = null
    var my_sprtrss: TextView? = null
    var my_sprsprtrss: TextView? = null
    var my_ttl_pyouts: TextView? = null
    var my_revnue: TextView? = null
    var my_ch_namee: TextView? = null
    var token: String? = null
    var chanl_idtosend: String? = null
    var manager: SessionManager? = null
    var bundle: Bundle? = null
    var vids_cardvw: CardView? = null
    var cts_cardvw: CardView? = null
    var supsprt_cv: CardView? = null
    var sprt_cv: CardView? = null
    var jumpactvity: CrtrZoneActivity? = null
    var biz_ch_exsts_str = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getmychdtlssss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_mntzn_layout, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        val activity: CrtrZoneActivity? = activity as CrtrZoneActivity?
        chanl_idtosend = activity?.getMyData()
        my_chanls = viewexp.findViewById<TextView>(R.id.my_chanls)
        my_cutiess = viewexp.findViewById<TextView>(R.id.my_cutiess)
        my_vidsss = viewexp.findViewById<TextView>(R.id.my_vidsss)
        my_crses = viewexp.findViewById<TextView>(R.id.my_crses)
        my_sprtrss = viewexp.findViewById<TextView>(R.id.my_sprtrss)
        my_sprsprtrss = viewexp.findViewById<TextView>(R.id.my_sprsprtrss)
        my_ttl_pyouts = viewexp.findViewById<TextView>(R.id.my_ttl_pyouts)
        my_revnue = viewexp.findViewById<TextView>(R.id.my_revnue)
        crt_biz_ch = viewexp.findViewById<TextView>(R.id.crt_biz_ch)
        vids_cardvw = viewexp.findViewById<CardView>(R.id.vids_cardvw)
        cts_cardvw = viewexp.findViewById<CardView>(R.id.cts_cardvw)
        supsprt_cv = viewexp.findViewById<CardView>(R.id.supsprt_cv)
        sprt_cv = viewexp.findViewById<CardView>(R.id.sprt_cv)
        crt_biz_ch?.setOnClickListener(this)
        vids_cardvw?.setOnClickListener(this)
        cts_cardvw?.setOnClickListener(this)
        supsprt_cv?.setOnClickListener(this)
        sprt_cv?.setOnClickListener(this)
        jumpactvity = getActivity() as CrtrZoneActivity?
        getmychdtlssss()
        getbizchexists()
        milestiones
        if (biz_ch_exsts_str) {
            crt_biz_ch?.visibility = View.GONE
        } else {
            crt_biz_ch?.visibility = View.VISIBLE
        }
        return viewexp
    }

    private fun getbizchexists() {
        val url: String = Configs.BASE_URL2 + "biz-channel-exist"
        RetrofitClient.getClient().bizchnlexists(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<BizChExistsPojo?>(crt_biz_ch) {
                override fun onResponse(
                    call: Call<BizChExistsPojo?>?,
                    response: Response<BizChExistsPojo?>
                ) {
                    try {
                        if (response.body() != null) {
                            val rererer: String ? = response.body()?.status
                            if (rererer == "200") {
                                biz_ch_exsts_str = response.body()?.isExist!!
                                if (biz_ch_exsts_str) {
                                    crt_biz_ch?.visibility = View.GONE
                                } else {
                                    crt_biz_ch?.visibility = View.VISIBLE
                                }
                                //                                    updateUIBasedOnPoolId(biz_ch_exsts_str);
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun getmychdtlssss() {
        val url: String = Configs.BASE_URL2 + "channels/" + chanl_idtosend
        RetrofitClient.getClient().getmychdtls(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<MyChnlDtlsPojo?>(my_chanls) {
               override fun onResponse(
                   call: Call<MyChnlDtlsPojo?>,
                   response: Response<MyChnlDtlsPojo?>
               ) {
                    try {
//                            String supprtcnttt ? = response.body()?.getSupportcount();
//                            mych_sprtr_cnt.setText(supprtcnttt + " Supporters");
//                            my_sprtrss.setText(supprtcnttt);
//                            List<SubscribedDatum> catggryList ? = response.body()?.getSubscribedData();
//                            List<MyChDtlsCuty> cutiesdd ? = response.body()?.getCuties();

//                            int cutirsizee = cutiesdd.size();
//                            my_cutiess.setText(String.valueOf(cutirsizee));

//                            MyChDtlsVideos myChDtlsVideos ? = response.body()?.getVideos();
//                            List<MyChDtlsVid> videosddf = myChDtlsVideos.getVideos();

//                            int vidsssrsizee = videosddf.size();
//                            Log.e("vidsssrsizee", String.valueOf(vidsssrsizee));
//                            my_vidsss.setText(String.valueOf(vidsssrsizee));
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }//                        blur_reg_sprt.visibility = View.GONE;

    //                        customDialog.dismiss();
    //        blur_reg_sprt.visibility = View.VISIBLE;
//        customDialog.show();
    val milestiones: Unit
        get() {
//        blur_reg_sprt.visibility = View.VISIBLE;
//        customDialog.show();
            val url: String = Configs.BASE_URL2 + "mycreater-prfl"
            RetrofitClient.getClient().getmilestn(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<MilestonePojo?>(my_sprtrss) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<MilestonePojo?>,
                        response: Response<MilestonePojo?>
                    ) {

//                        blur_reg_sprt.visibility = View.GONE;
//                        customDialog.dismiss();
                        try {
                            val milestnres: String ? = response.body()?.status
                            if (milestnres == "200") {
                                val milestnee: List<Milestone>?=response.body()?.data
                                if (milestnee != null) {
                                    for (i in milestnee.indices) {
                                        val milestone_vc: Milestone = milestnee[i]
                                        val milestone_ss: Milestone_Ss? = milestone_vc.milestone
                                        val sprts_count: Int? = milestone_ss?.totalSupporters
                                        val sup_sprts_count: Int? =
                                            milestone_ss?.totalSuperSupporters
                                        val totl_vids_uplded: Int? = milestone_ss?.totalVideosCount
                                        val totl_cts_uplded: Int? = milestone_ss?.totalCutiesCount
                                        my_sprtrss?.text = sprts_count.toString()
                                        my_vidsss?.text = totl_vids_uplded.toString()
                                        my_cutiess?.text = totl_cts_uplded.toString()
                                    }
                                }
                            } else {
                                Toast.makeText(activity, "No data", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.crt_biz_ch -> {
                val crtbiz_ch_intent = Intent(activity, CreateBizChannel::class.java)
                startActivity(crtbiz_ch_intent)
            }

            R.id.vids_cardvw -> jumpactvity?.jumpToTab(2)
            R.id.cts_cardvw -> jumpactvity?.jumpToTab(3)
            R.id.sprt_cv -> jumpactvity?.jumpToTab(4)
            R.id.supsprt_cv -> jumpactvity?.jumpToTab(5)
        }
    }
}