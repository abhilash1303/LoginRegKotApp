package www.rahagloball.loginregkotapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.TLtstVidsActivity
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapter
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapterLtst
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapterTrnd
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crtrreanking.CrDatum
import www.rahagloball.loginregkotapp.models.crtrreanking.CrtrRnkingPojo
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidDatum
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidPojo
import www.rahagloball.loginregkotapp.models.newsindletest.OneDataItem
import www.rahagloball.loginregkotapp.models.newsindletest.OneVidListPojo
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner

class VideosFragment : Fragment() {
    var ssProduct1: Spinner? = null
    var language: Spinner? = null
    var submit: CardView? = null
    var blur_reg1: RelativeLayout? = null
    var emptylayout: LinearLayout? = null
    var rv_all_videos: RecyclerView? = null
    var rv_cuties: RecyclerView? = null
    var token: String? = null
    var manager: SessionManager? = null
    var ask: Button? = null
    var apply_fsp: CardView? = null
    var agent_spin: Spinner? = null
    var singleSpinnerSearch: SearchableSpinner? = null
    var city: ArrayList<String>? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var adapter_vids_list: RecyclerView.Adapter<*>? = null
    private var layoutManager_cuties: RecyclerView.LayoutManager? = null
    var rv_learn_imagess: RecyclerView? = null
    var search_videos: EditText? = null
    var buy_card: CardView? = null
    var txt_leaddrn_catsd: TextView? = null
    var wtch_ltst: TextView? = null
    var ltst_shw_more: TextView? = null
    var ll_cutiesss: LinearLayout? = null
    var ll_onlinee: LinearLayout? = null
    var wtch_ltst_right: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vid_vw: View = inflater.inflate(R.layout.fragment_videos1, container, false)
        city = ArrayList()
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        ssProduct1 = vid_vw.findViewById<Spinner>(R.id.ssProduct1)
        submit = vid_vw.findViewById<CardView>(R.id.submitlang)
        language = vid_vw.findViewById<Spinner>(R.id.language)
        ask = vid_vw.findViewById<Button>(R.id.ask)
        search_videos = vid_vw.findViewById<EditText>(R.id.search_videos)
        blur_reg1 = vid_vw.findViewById<RelativeLayout>(R.id.blur_reg1)
        buy_card = vid_vw.findViewById<CardView>(R.id.buy_card)
        ll_cutiesss = vid_vw.findViewById<LinearLayout>(R.id.ll_cutiesss)
        ll_cutiesss?.visibility = View.GONE
        ll_onlinee = vid_vw.findViewById<LinearLayout>(R.id.ll_onlinee)
        ll_onlinee?.visibility = View.GONE
        txt_leaddrn_catsd = vid_vw.findViewById<TextView>(R.id.txt_leaddrn_catsd)
        buy_card?.visibility = View.GONE
        txt_leaddrn_catsd?.visibility = View.GONE
        emptylayout = vid_vw.findViewById<LinearLayout>(R.id.emptylayout)
        apply_fsp = vid_vw.findViewById<CardView>(R.id.apply_fsp)
        agent_spin = vid_vw.findViewById<Spinner>(R.id.agent_spin)
        wtch_ltst_right = vid_vw.findViewById<ImageView>(R.id.wtch_ltst_right)
        ltst_shw_more = vid_vw.findViewById<TextView>(R.id.ltst_shw_more)
        wtch_ltst = vid_vw.findViewById<TextView>(R.id.wtch_ltst)
        singleSpinnerSearch = vid_vw.findViewById(R.id.multipleItemSelectionSpinner)
        rv_all_videos = vid_vw.findViewById<RecyclerView>(R.id.rv_all_videos)
        rv_cuties = vid_vw.findViewById<RecyclerView>(R.id.rv_cuties)

        //all videos
        layoutManager_vidsList = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
        rv_all_videos?.layoutManager = layoutManager_vidsList
        layoutManager_cuties = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_cuties?.layoutManager = layoutManager_cuties
        rv_learn_imagess = vid_vw.findViewById<RecyclerView>(R.id.videoList)

        rv_learn_imagess?.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        cuteVids
        lEarnVids
        lEarnImgs
        wtch_ltst_right = vid_vw.findViewById<ImageView>(R.id.wtch_ltst_right)
        ltst_shw_more = vid_vw.findViewById<TextView>(R.id.ltst_shw_more)
        wtch_ltst = vid_vw.findViewById<TextView>(R.id.wtch_ltst)
        wtch_ltst?.text = "Latest Videos"
        wtch_ltst_right?.visibility = View.VISIBLE
        wtch_ltst_right?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent_ltst = Intent(activity, TLtstVidsActivity::class.java)
            startActivity(intent_ltst)
        })
        ltst_shw_more?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent_ltst = Intent(activity, TLtstVidsActivity::class.java)
            startActivity(intent_ltst)
        })
        return vid_vw
    }

    private val lEarnImgs: Unit
        private get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "creater-ranking"
            RetrofitClient.getClient().crtrrnkingg(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CrtrRnkingPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                  override  fun onResponse(
                        call: Call<CrtrRnkingPojo?>,
                        response: Response<CrtrRnkingPojo?>
                    ) {
                         blur_reg1?.visibility = View.GONE
                        try {
                            val crtrres: String ? = response.body()?.status
                            if (crtrres != null) {
                                if (crtrres.contains("1")) {
                                    val catggryList: List<CrDatum>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            rv_learn_imagess!!.visibility = View.GONE
                                        } else {
                                            rv_learn_imagess!!.visibility = View.VISIBLE
                                            adapter_imgs =
                                                activity?.let {
                                                    CrtrRnkngAdapterTrnd(catggryList,
                                                        it
                                                    )
                                                }
                                            rv_learn_imagess!!.adapter = adapter_imgs
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

    //                                Collections.shuffle(catggryList, new Random());
    private val lEarnVids: Unit
        get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "videos-list"
            RetrofitClient.getClient().allvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<OneVidListPojo?>,
                        response: Response<OneVidListPojo?>
                    ) {
                         blur_reg1?.visibility = View.GONE
                        try {
                            val catggryList: List<OneDataItem>?= response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_all_videos!!.visibility = View.GONE
                                } else {
                                    rv_all_videos!!.visibility = View.VISIBLE
                                    adapter_vids_list =
                                        activity?.let { AllVidListAdapter(catggryList, it) }
                                    //                                Collections.shuffle(catggryList, new Random());
                                    rv_all_videos!!.adapter = adapter_vids_list
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
    private val cuteVids: Unit
        get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "latest-videos"
            RetrofitClient.getClient().allvidList1(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<LtstVidPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<LtstVidPojo?>,
                        response: Response<LtstVidPojo?>
                    ) {
                         blur_reg1?.visibility = View.GONE
                        try {
                            val catggryList: List<LtstVidDatum>?= response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_cuties!!.visibility = View.GONE
                                } else {
                                    rv_cuties!!.visibility = View.VISIBLE
                                    adapter_cuties_list =
                                        activity?.let { AllVidListAdapterLtst(catggryList, it) }
                                    rv_cuties!!.adapter = adapter_cuties_list
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    companion object {
        private var adapter_imgs: RecyclerView.Adapter<*>? = null
        private var adapter_cuties_list: RecyclerView.Adapter<*>? = null
    }
}