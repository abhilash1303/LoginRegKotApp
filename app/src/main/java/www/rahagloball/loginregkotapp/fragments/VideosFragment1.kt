package www.rahagloball.loginregkotapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CuteListAdapter
import www.rahagloball.loginregkotapp.adapters.LearndataAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.cutiesss.CutiesPojo
import www.rahagloball.loginregkotapp.models.cutiesss.DataItemCute
import www.rahagloball.loginregkotapp.models.lrndtls_new.CourseDtlsPojo
import www.rahagloball.loginregkotapp.models.lrndtls_new.DatumCd
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner
import java.util.Collections

class VideosFragment1 : Fragment() {
    //    VideoAdapter1 vidadapter;
    //    List<FinancialVideo> all_videos;
    var ssProduct1: Spinner? = null
    var language: Spinner? = null

    //    Button submit;
    var submit: CardView? = null
    var blur_reg1: RelativeLayout? = null
    var emptylayout: LinearLayout? = null
    var videoList: RecyclerView? = null
    var rv_misc_vid: RecyclerView? = null
    var rv_all_videos: RecyclerView? = null
    var rv_cuties: RecyclerView? = null
    var token: String? = null
    var manager: SessionManager? = null
    var ask: Button? = null
    var productss = ArrayList<String>()
    var presults: JSONArray? = null
    var presults1: JSONArray? = null
    var apply_fsp: CardView? = null
    var agent_spin_str: String? = null
    var city_spiner_str: String? = null
    var search_videos_str: String? = null

    //    String[] lang = {"English", "Hindi", "Kannada", "Tamil", "Telugu", "Malayalam"};
    //
    //    String[] agentstr = {"Select Agent", "Certified Financial Planner", "Insurance Advisor/Seller(Individual)",
    //            "Insurance Seller(Broker)", "Chartered Accountant", "Loan Agent(Individual)", "Loan Agent(DSA)"};
    var agent_spin: Spinner? = null
    var city_spiner: Spinner? = null
    var singleSpinnerSearch: SearchableSpinner? = null

    //    Button apply_fsp;
    var cresult: JSONArray? = null
    var city: ArrayList<String>? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    private var layoutManager_cuties: RecyclerView.LayoutManager? = null
    var rv_learn_imagess: RecyclerView? = null
    var search_videos: EditText? = null
    var buy_card: CardView? = null
    var txt_leaddrn_catsd: TextView? = null
    var ll_cutiesss: LinearLayout? = null
    var ll_onlinee: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        //        all_videos = new ArrayList<>();
        emptylayout = vid_vw.findViewById<LinearLayout>(R.id.emptylayout)
        apply_fsp = vid_vw.findViewById<CardView>(R.id.apply_fsp)
        agent_spin = vid_vw.findViewById<Spinner>(R.id.agent_spin)
        singleSpinnerSearch = vid_vw.findViewById(R.id.multipleItemSelectionSpinner)
        rv_all_videos = vid_vw.findViewById<RecyclerView>(R.id.rv_all_videos)
        rv_cuties = vid_vw.findViewById<RecyclerView>(R.id.rv_cuties)
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


//        getCuteVids();
        lEarnVids
        //        getLEarnImgs();
        return vid_vw
    }

    //                        String learn_ImgVidStr ? = response.body()?.toString();
//                        Log.e("learn_ImgVidStr", learn_ImgVidStr);
//
    private val lEarnImgs: Unit
        get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "course-details"
            RetrofitClient.getClient().learnImgs1(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CourseDtlsPojo?>(rv_learn_imagess) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<CourseDtlsPojo?>,
                        response: Response<CourseDtlsPojo?>
                    ) {
                        blur_reg1?.visibility = View.GONE

//                        String learn_ImgVidStr ? = response.body()?.toString();
//                        Log.e("learn_ImgVidStr", learn_ImgVidStr);
//
                        try {
                            val catggryList: List<DatumCd>?= response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_learn_imagess!!.visibility = View.GONE
                                } else {
                                    rv_learn_imagess!!.visibility = View.VISIBLE
                                    adapter_imgs = activity?.let {
                                        LearndataAdapter(
                                            catggryList,
                                            it
                                        )
                                    }
                                    rv_learn_imagess!!.adapter = adapter_imgs
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    //        blur_reg1.visibility = View.VISIBLE;
//
//        String url = Configs.BASE_URL2 + "videos-list";
//
//        RetrofitClient.getClient().allvidList(url, "application/json",
//                        "Bearer " + token)
//                .enqueue(new GlobalCallback<OneVidListtPojo>(rv_all_videos) {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<OneVidListtPojo> call, Response<OneVidListtPojo> response) {
//
//                        blur_reg1.visibility = View.GONE;
//
//                        try {
//
//                            List<DataItem> catggryList?=response.body().data;
//
//                            if (catggryList.isEmpty()) {
//
//                                rv_all_videos.visibility = View.GONE;
//
//                            } else {
//                                rv_all_videos.visibility = View.VISIBLE;
//                                adapter_vids_list = new AllVidListAdapter(catggryList, getActivity());
////                                Collections.shuffle(catggryList, new Random());
//                                rv_all_videos.setAdapter(adapter_vids_list);
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
    val lEarnVids: Unit
        get() {

//        blur_reg1.visibility = View.VISIBLE;
//
//        String url = Configs.BASE_URL2 + "videos-list";
//
//        RetrofitClient.getClient().allvidList(url, "application/json",
//                        "Bearer " + token)
//                .enqueue(new GlobalCallback<OneVidListtPojo>(rv_all_videos) {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<OneVidListtPojo> call, Response<OneVidListtPojo> response) {
//
//                        blur_reg1.visibility = View.GONE;
//
//                        try {
//
//                            List<DataItem> catggryList?=response.body().data;
//
//                            if (catggryList.isEmpty()) {
//
//                                rv_all_videos.visibility = View.GONE;
//
//                            } else {
//                                rv_all_videos.visibility = View.VISIBLE;
//                                adapter_vids_list = new AllVidListAdapter(catggryList, getActivity());
////                                Collections.shuffle(catggryList, new Random());
//                                rv_all_videos.setAdapter(adapter_vids_list);
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
        }

    private fun getCuteVids() {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "cuties"
        RetrofitClient.getClient().gtcutelist(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CutiesPojo?>(rv_cuties) {
                @SuppressLint("SetTextI18n")
              override  fun onResponse(call: Call<CutiesPojo?>, response: Response<CutiesPojo?>) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val catggryList: List<DataItemCute?>?= response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rv_cuties!!.visibility = View.GONE
                            } else {
                                rv_cuties!!.visibility = View.VISIBLE
                                adapter_cuties_list = activity?.let {
                                    CuteListAdapter(
                                        catggryList as List<DataItemCute>,
                                        it
                                    )
                                }
                                if (catggryList != null) {
                                    Collections.reverse(catggryList)
                                }
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
        private val adapter_vids_list: RecyclerView.Adapter<*>? = null
        private var adapter_imgs: RecyclerView.Adapter<*>? = null
        private var adapter_cuties_list: RecyclerView.Adapter<*>? = null
    }
}