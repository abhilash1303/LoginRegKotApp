package www.rahagloball.loginregkotapp.fragments


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
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsCtView
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsCuty
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo

class OpAboutFrgmnt : Fragment() {
    var desc_abt: TextView? = null
    var mails_abt: TextView? = null
    var stats_abt: TextView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var chanl_idtosend: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.chdtls_about, container, false)
        manager = SessionManager()
        val activity: ChannelProfileActivity? = activity as ChannelProfileActivity?
        if (activity != null) {
            chanl_idtosend = activity.getMyChData()
        }
        token = getActivity()?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        desc_abt = viewexp.findViewById<TextView>(R.id.desc_abt)
        mails_abt = viewexp.findViewById<TextView>(R.id.mails_abt)
        stats_abt = viewexp.findViewById<TextView>(R.id.stats_abt)
        getmychdtlssss(chanl_idtosend)
        //        getviewsdtls();
        return viewexp
    }

    fun getmychdtlssss(chanl_idtosend: String?) {
        val url: String = Configs.BASE_URL2 + "channels/" + chanl_idtosend
        RetrofitClient.getClient().getmychdtls(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<MyChnlDtlsPojo?>(desc_abt) {
                @SuppressLint("SetTextI18n")
               override fun onResponse(call: Call<MyChnlDtlsPojo?>, response: Response<MyChnlDtlsPojo?>) {
                    try {
                        if (response.body() != null) {
                            val ch_desc: String ? = response.body()?.channelDescription
                            desc_abt?.text = ch_desc
                            val cutiesdd: List<MyChDtlsCuty> ? = response.body()?.cuties
                            if (cutiesdd != null) {
                                for (i in cutiesdd.indices) {
                                    val myChDtlsCtVws: MyChDtlsCuty = cutiesdd[i]
                                    val views_cts: List<MyChDtlsCtView>? = myChDtlsCtVws.views
                                    val cutirsizee = views_cts?.size
                                    stats_abt?.text = "$cutirsizee Views"
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    } //
    //    public void getviewsdtls() {
    //        String url = Configs.BASE_URL2 + "mycreater-prfl";
    //        RetrofitClient.getClient().getmilestn(url, "application/json", "Bearer " + token)
    //                .enqueue(new GlobalCallback<MilestonePojo>(desc_abt) {
    //                    @SuppressLint("SetTextI18n")
    //                    @Override
    //                    public void onResponse(@NonNull Call<MilestonePojo> call, @NonNull Response<MilestonePojo> response) {
    //
    //                        try {
    //                            if (response.body() != null) {
    //                                String milestnres ? = response.body()?.getStatus();
    //                                if (milestnres.equals("200")) {
    //                                    List<Milestone> milestnee?=response.body().data;
    //                                    for (int i = 0; i < milestnee.size(); i++) {
    //                                        Milestone milestone_vc = milestnee.get(i);
    //                                        int getcts_vws = milestone_vc.getViewsCountOnTotalCuties();
    //                                        int getvds_vws = milestone_vc.getViewsCountOnTotalVideos();
    //                                        int ttl_vws = getcts_vws + getvds_vws;
    //                                        Log.e("ttl_vws_mycrtr", String.valueOf(ttl_vws));
    //                                        stats_abt.setText(String.valueOf(ttl_vws) + " Views");
    //
    //                                    }
    //                                }
    //                            }
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //                    }
    //                });
    //    }
    //
}