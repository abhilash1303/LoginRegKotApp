package www.rahagloball.loginregkotapp.fragments.mychdtlsf

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapter
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapterLtst
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapterTrnd1
import www.rahagloball.loginregkotapp.adapters.SuprSuprtVidListAdapter
import www.rahagloball.loginregkotapp.adapters.SuprtVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crtrreanking.CrDatum
import www.rahagloball.loginregkotapp.models.crtrreanking.CrtrRnkingPojo
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidDatum
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidPojo
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidList
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidPojo
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySprSprtVidsPojo
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySuprSprtVidList
import www.rahagloball.loginregkotapp.models.newsindletest.OneDataItem
import www.rahagloball.loginregkotapp.models.newsindletest.OneVidListPojo
import java.util.Collections
import java.util.Random


class VideosFragment2 : Fragment() {
    var rv_all_vid_filter: RecyclerView? = null
    private var llm_all_videos_fliter: RecyclerView.LayoutManager? = null
    var manager: SessionManager? = null
    var token: String? = null
    var customDialog: CustomDialog? = null
    var all_vid_spinner: Spinner? = null
    var radioGroup: RadioGroup? = null
    var rb_misc_videos: RadioButton? = null
    var rb_action_supportt: RadioButton? = null
    var rb_trnd_videos: RadioButton? = null
    var rb_all_videos: RadioButton? = null
    var rb_latest_videos: RadioButton? = null
    var rb_action_ssupportt: RadioButton? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var nodata: FrameLayout? = null
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vid_vw: View = inflater.inflate(R.layout.frag_vid2, container, false)
        manager = SessionManager()
        customDialog = CustomDialog(activity)
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        all_vid_spinner = vid_vw.findViewById<Spinner>(R.id.all_vid_spinner)
        shimmerFrameLayout = vid_vw.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        rv_all_vid_filter = vid_vw.findViewById<RecyclerView>(R.id.rv_all_vid_filter)
        rb_latest_videos = vid_vw.findViewById<RadioButton>(R.id.rb_latest_videos)
        rb_trnd_videos = vid_vw.findViewById<RadioButton>(R.id.rb_trnd_videos)
        rb_action_supportt = vid_vw.findViewById<RadioButton>(R.id.rb_action_supportt)
        rb_misc_videos = vid_vw.findViewById<RadioButton>(R.id.rb_misc_videos)
        rb_all_videos = vid_vw.findViewById<RadioButton>(R.id.rb_all_videos)
        rb_action_ssupportt = vid_vw.findViewById<RadioButton>(R.id.rb_action_ssupportt)
        nodata = vid_vw.findViewById<FrameLayout>(R.id.nodata)
        llm_all_videos_fliter = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_all_vid_filter?.layoutManager = llm_all_videos_fliter
        radioGroup = vid_vw.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
            when (checkedId) {
                R.id.rb_all_videos, R.id.rb_misc_videos -> {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                    shimmerFrameLayout?.startShimmer()
                    rv_all_vid_filter?.visibility = View.GONE
                    lEarnVidsFilter
                }

                R.id.rb_latest_videos -> {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                    shimmerFrameLayout?.startShimmer()
                    rv_all_vid_filter?.visibility = View.GONE
                    ltstVidsFilter
                }

                R.id.rb_trnd_videos -> {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                    shimmerFrameLayout?.startShimmer()
                    rv_all_vid_filter?.visibility = View.GONE
                    trndngVidsFilter
                }

                R.id.rb_action_supportt -> {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                    shimmerFrameLayout?.startShimmer()
                    rv_all_vid_filter?.visibility = View.GONE
                    shimmerFrameLayout?.startShimmer()
                    sprtVidsFilter
                }

                R.id.rb_action_ssupportt -> {
                    shimmerFrameLayout?.visibility = View.VISIBLE
                    shimmerFrameLayout?.startShimmer()
                    rv_all_vid_filter?.visibility = View.GONE
                    shimmerFrameLayout?.startShimmer()
                    suprSprtVidsFilter
                }

                else -> lEarnVidsFilter
            }
        })
        lEarnVidsFilter1
        return vid_vw
    }

    private val sprtVidsFilter: Unit
        private get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-supportvideos"
            RetrofitClient.getClient().suprtvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<MySprtVidPojo?>(rv_all_vid_filter) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<MySprtVidPojo?>?,
                        response: Response<MySprtVidPojo?>
                    ) {
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            if (response.body() != null) {
                                val sprtvidsts: String ? = response.body()?.status
                                if (sprtvidsts != null) {
                                    if (sprtvidsts.contains("0")) {
                                        nodata?.visibility = View.VISIBLE
                                    } else if (sprtvidsts.contains("1")) {
                                        nodata?.visibility = View.GONE
                                        val mySprtVids: List<MySprtVidList>? =
                                            response.body()?.videos
                                        if (mySprtVids != null) {
                                            if (mySprtVids.isEmpty()) {
                                                nodata?.visibility = View.VISIBLE
                                            } else {
                                                shimmerFrameLayout?.visibility = View.GONE
                                                rv_all_vid_filter!!.visibility = View.VISIBLE
                                                adapter_all_videos_fliter =
                                                    mySprtVids?.let { activity?.let { it1 ->
                                                        SuprtVidListAdapter(it,
                                                            it1
                                                        )
                                                    } }
                                                rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
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

    //                                            ll_sprtr_vids?.visibility = View.VISIBLE;
    private val suprSprtVidsFilter: Unit
        private get() {
            try {
                shimmerFrameLayout?.startShimmer()
                val url: String = Configs.BASE_URL2 + "my-ssupportvideos"
                RetrofitClient.getClient().sprsuprtvidList(
                    url, "application/json",
                    "Bearer $token"
                )
                    ?.enqueue(object : GlobalCallback<MySprSprtVidsPojo?>(rv_all_vid_filter) {
                        @SuppressLint("SetTextI18n")
                       override fun onResponse(
                            call: Call<MySprSprtVidsPojo?>,
                            response: Response<MySprSprtVidsPojo?>
                        ) {
                            shimmerFrameLayout?.stopShimmer()
                            try {
                                if (response.body() != null) {
                                    val sprtvidsts: String ? = response.body()?.status
                                    if (sprtvidsts != null) {
                                        if (sprtvidsts.contains("0")) {
                                            nodata?.visibility = View.VISIBLE
                                        } else if (sprtvidsts.contains("1")) {
                                            nodata?.visibility = View.GONE
                                            val mySprtVids: List<MySuprSprtVidList>? =
                                                response.body()?.videos
                                            if (mySprtVids != null) {
                                                if (mySprtVids.isEmpty()) {
                                                    nodata?.visibility = View.VISIBLE
                                                    shimmerFrameLayout?.visibility = View.GONE
                                                } else {
                                                    //                                            ll_sprtr_vids?.visibility = View.VISIBLE;
                                                    shimmerFrameLayout?.visibility = View.GONE
                                                    adapter_all_videos_fliter =
                                                        mySprtVids?.let { activity?.let { it1 ->
                                                            SuprSuprtVidListAdapter(it,
                                                                it1
                                                            )
                                                        } }
                                                    rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }//                                linearLayout.visibility = View.GONE;

    //
//                        customDialog.dismiss();
//                        blur_reg1.visibility = View.GONE;
    //        customDialog.show();
    //        blur_reg1.visibility = View.VISIBLE;
    private val ltstVidsFilter: Unit
        get() {

//        customDialog.show();
            shimmerFrameLayout?.startShimmer()
            //        blur_reg1.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "latest-videos"
            RetrofitClient.getClient().allvidList1(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<LtstVidPojo?>(rv_all_vid_filter) {
                    @SuppressLint("SetTextI18n")
                  override  fun onResponse(
                        call: Call<LtstVidPojo?>,
                        response: Response<LtstVidPojo?>
                    ) {
//                        customDialog.dismiss();
//                        blur_reg1.visibility = View.GONE;
                        try {
                            val catggryList: List<LtstVidDatum>?=response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_all_vid_filter!!.visibility = View.GONE
                                } else {
                        //                                linearLayout.visibility = View.GONE;
                        //
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_all_vid_filter!!.visibility = View.VISIBLE
                                    rv_all_vid_filter!!.visibility = View.VISIBLE
                                    adapter_all_videos_fliter =
                                        AllVidListAdapterLtst(catggryList,requireActivity()!!)
                                    rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }//                                linearLayout.visibility = View.GONE;

    //                                Collections.shuffle(catggryList, new Random());
    //        customDialog.show();
    private val lEarnVidsFilter: Unit
        get() {

//        customDialog.show();
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "videos-list"
            RetrofitClient.getClient().allvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rv_all_vid_filter) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<OneVidListPojo?>,
                        response: Response<OneVidListPojo?>
                    ) {
                        customDialog?.dismiss()
                        try {
                            val catggryList: List<OneDataItem>?=response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_all_vid_filter!!.visibility = View.GONE
                                } else {
                        //                                linearLayout.visibility = View.GONE;
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_all_vid_filter!!.visibility = View.VISIBLE
                                    adapter_all_videos_fliter = AllVidListAdapter(catggryList, activity!!)
                                    //                                Collections.shuffle(catggryList, new Random());
                                    rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }//                                linearLayout.visibility = View.GONE;

    //                        customDialog.dismiss();
    private val lEarnVidsFilter1: Unit
        get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "videos-list"
            RetrofitClient.getClient().allvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rv_all_vid_filter) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<OneVidListPojo?>,
                        response: Response<OneVidListPojo?>
                    ) {


//                        customDialog.dismiss();
                        try {
                            val catggryList: List<OneDataItem?>?=response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_all_vid_filter!!.visibility = View.GONE
                                } else {
                        //                                linearLayout.visibility = View.GONE;
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_all_vid_filter!!.visibility = View.VISIBLE
                                    adapter_all_videos_fliter = activity?.let {
                                        AllVidListAdapter(catggryList as List<OneDataItem>?,
                                            it
                                        )
                                    }
                                    Collections.shuffle(catggryList, Random())
                                    rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }//                                    linearLayout.visibility = View.GONE;//                        customDialog.dismiss();

    //        customDialog.show();
    private val trndngVidsFilter: Unit
        get() {
            shimmerFrameLayout?.startShimmer()
            //        customDialog.show();
            val url: String = Configs.BASE_URL2 + "creater-ranking"
            RetrofitClient.getClient().crtrrnkingg(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CrtrRnkingPojo?>(rv_all_vid_filter) {
                    @SuppressLint("SetTextI18n")
                 override fun onResponse(
                        call: Call<CrtrRnkingPojo?>,
                        response: Response<CrtrRnkingPojo?>
                    ) {

//                        customDialog.dismiss();
                        try {
                            val crtrres: String? = response.body()?.status
                            if (crtrres != null) {
                                if (crtrres.contains("1")) {
                                    val catggryList: List<CrDatum>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            rv_all_vid_filter!!.visibility = View.GONE
                                        } else {
                                            //                                    linearLayout.visibility = View.GONE;
                                            shimmerFrameLayout?.stopShimmer()
                                            shimmerFrameLayout?.visibility = View.GONE
                                            rv_all_vid_filter!!.visibility = View.VISIBLE
                                            adapter_all_videos_fliter =
                                                activity?.let {
                                                    CrtrRnkngAdapterTrnd1(catggryList,
                                                        it
                                                    )
                                                }
                                            rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
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

    companion object {
        private var adapter_all_videos_fliter: RecyclerView.Adapter<*>? = null
    }
}