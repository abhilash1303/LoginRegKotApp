package www.rahagloball.loginregkotapp.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getbizchnl.GetBizChnl
import www.rahagloball.loginregkotapp.models.getbizchnl.GetBizChnlPojo
import www.rahagloball.loginregkotapp.models.milestonebz.MilestoneBz
import www.rahagloball.loginregkotapp.models.milestonebz.MilestoneBzPojo
import www.rahagloball.loginregkotapp.models.milestonebz.Milestone_Ss_Bz
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo

class MyChDtlsBz : Fragment() {
    var img_my_ch: CircleImageView? = null
    var my_ch_name: TextView? = null
    var my_bizch_name: TextView? = null
    var sprtr_count_mych: TextView? = null
    var spr_sprt_cnt_mych: TextView? = null
    var vid_mych: TextView? = null
    var cts_mych: TextView? = null
    var crs_mych: TextView? = null
    var sprtr_count_mychstr: String? = null
    var spr_sprt_cnt_mychstr: String? = null
    var vid_mychstr: String? = null
    var cts_mychstr: String? = null
    var crs_mychstr: String? = null
    var imgProfilePic_str: String? = null
    var token: String? = null
    var manager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.my_ch_about, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        img_my_ch = viewexp.findViewById<CircleImageView>(R.id.img_my_ch)
        my_ch_name = viewexp.findViewById<TextView>(R.id.my_ch_name)
        my_bizch_name = viewexp.findViewById<TextView>(R.id.my_bizch_name)
        sprtr_count_mych = viewexp.findViewById<TextView>(R.id.sprtr_count_mych)
        spr_sprt_cnt_mych = viewexp.findViewById<TextView>(R.id.spr_sprt_cnt_mych)
        vid_mych = viewexp.findViewById<TextView>(R.id.vid_mych)
        cts_mych = viewexp.findViewById<TextView>(R.id.cts_mych)
        crs_mych = viewexp.findViewById<TextView>(R.id.crs_mych)
        profileimage()
        //        getchannel();
        createbizchannel()
        milestionesbiz
        return viewexp
    }

    private fun createbizchannel() {
        val url: String = Configs.BASE_URL2 + "getbiz-channel"
        RetrofitClient.getClient().getbizchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetBizChnlPojo?>(img_my_ch) {
                override fun onResponse(
                    call: Call<GetBizChnlPojo?>,
                    response: Response<GetBizChnlPojo?>
                ) {
                    try {
                        val biz_res: String? = response.body()?.status
                        if (biz_res == "200") {
                            val getBizChnl: GetBizChnl? = response.body()?.data
                            //                                biz_chanel_id_str = catggryList.getId();
                            val my_ch_namestr: String? = getBizChnl?.channelName
                            my_ch_name?.text = my_ch_namestr
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun profileimage() {
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(img_my_ch) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        if (profileItems != null) {
                            val profileItem: ProfileItem = profileItems[0]
                            imgProfilePic_str =
                                Configs.BASE_URL21 + "images/user/" + profileItem.userImage
                            activity?.let {
                                img_my_ch?.let { it1 ->
                                    Glide.with(it).load(imgProfilePic_str).into(
                                        it1
                                    )
                                }
                            }
                        }


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    val milestionesbiz: Unit
        get() {
            val url: String = Configs.BASE_URL2 + "mycreater-prfl/bizchannel"
            RetrofitClient.getClient().getmilestnbz(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<MilestoneBzPojo?>(img_my_ch) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<MilestoneBzPojo?>?,
                        response: Response<MilestoneBzPojo?>
                    ) {
                        try {
                            if (response.body() != null) {
                                val milestnres: String? = response.body()?.status
                                if (milestnres == "200") {
                                    val milestnee: List<MilestoneBz>? = response.body()?.data
                                    if (milestnee != null) {
                                        for (i in milestnee.indices) {
                                            val milestone_vc: MilestoneBz = milestnee[i]
                                            val milestone_ss: Milestone_Ss_Bz? =
                                                milestone_vc.milestone
                                            val sprts_count: Int? = milestone_ss?.totalSupporters
                                            val sup_sprts_count: Int? =
                                                milestone_ss?.totalSuperSupporters
                                            val totl_vids_uplded: Int? =
                                                milestone_ss?.totalVideosCount
                                            val totl_cts_uplded: Int? =
                                                milestone_ss?.totalCutiesCount
                                            sprtr_count_mych?.text = sprts_count.toString()
                                            spr_sprt_cnt_mych?.text = sup_sprts_count.toString()
                                            vid_mych?.text = totl_vids_uplded.toString()
                                            cts_mych?.text = totl_cts_uplded.toString()
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