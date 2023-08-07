package www.rahagloball.loginregkotapp.fragments.mychdtlsf

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.SearchhActivity
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapter
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapterLtst
import www.rahagloball.loginregkotapp.adapters.AutoSuggestAdapter
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapterTrnd1
import www.rahagloball.loginregkotapp.adapters.SuprSuprtVidListAdapter
import www.rahagloball.loginregkotapp.adapters.SuprtVidListAdapter
import www.rahagloball.loginregkotapp.configuration.Configs
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
import www.rahagloball.loginregkotapp.networks.demosapi.ApiCall
import java.util.Collections
import java.util.Random

class VideosFragmentWatch : Fragment() {
    private lateinit var rvallvidfilter: RecyclerView
    private lateinit var llmallvideosfliter: RecyclerView.LayoutManager
    private lateinit var manager: SessionManager
    private lateinit var token: String
    private lateinit var customDialog: CustomDialog
    private lateinit var allvidspinner: Spinner
    private lateinit var autoSuggestAdapter: AutoSuggestAdapter
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioGroupwatch: RadioGroup
    private lateinit var rbmiscvideos: RadioButton
    private lateinit var rbactionsupport: RadioButton
    private lateinit var rbtrndvideos: RadioButton
    private lateinit var rb_all_videos: RadioButton
    private lateinit var rblatestvideos: RadioButton
    private lateinit var searchvideosstr: String
    private lateinit var nodata: RelativeLayout
    private lateinit var llfilter: LinearLayout
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var rankDialog: Dialog
    private lateinit var searchlang: AutoCompleteTextView
    private lateinit var searchcrtr: AutoCompleteTextView
    private lateinit var searchchanl: AutoCompleteTextView
    private lateinit var llfiltercrtr: LinearLayout
    private lateinit var llfilterlang: LinearLayout
    private lateinit var llfilterchanl: LinearLayout
    private lateinit var rbsrchchnmae: RadioButton
    private lateinit var rbsrchcrtr: RadioButton
    private lateinit var rbsrchlnguge: RadioButton
    private lateinit var canceldialog: ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vid_vw: View = inflater.inflate(R.layout.frag_vid_watch, container, false)
        manager = SessionManager()
        customDialog = CustomDialog(requireActivity())
        token = manager.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        allvidspinner = vid_vw.findViewById<Spinner>(R.id.all_vid_spinner)
        shimmerFrameLayout = vid_vw.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        llfilter = vid_vw.findViewById<LinearLayout>(R.id.ll_filter)
        rvallvidfilter = vid_vw.findViewById<RecyclerView>(R.id.rv_all_vid_filter)
        llmallvideosfliter =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rvallvidfilter?.layoutManager = llmallvideosfliter
        rblatestvideos = vid_vw.findViewById<RadioButton>(R.id.rb_latest_videos)
        rbtrndvideos = vid_vw.findViewById<RadioButton>(R.id.rb_trnd_videos)
        rbactionsupport = vid_vw.findViewById<RadioButton>(R.id.rb_action_supportt)
        rbmiscvideos = vid_vw.findViewById<RadioButton>(R.id.rb_misc_videos)
        nodata = vid_vw.findViewById<RelativeLayout>(R.id.nodata)
        radioGroup = vid_vw.findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
            shimmerFrameLayout.visibility = View.VISIBLE
            shimmerFrameLayout.startShimmer()
            rvallvidfilter?.visibility = View.GONE
            when (checkedId) {
                R.id.rb_latest_videos -> ltstVidsFilter
                R.id.rb_trnd_videos -> getTrndngVidsFilter()
                R.id.rb_action_supportt -> sprtVidsFilter
                R.id.rb_action_ssupportt -> suprSprtVidsFilter
                R.id.rb_all_videos, R.id.rb_misc_videos -> getLEarnVidsFilter()
                else -> getLEarnVidsFilter()
            }
        })

        val autoCompleteTextView: AutoCompleteTextView = vid_vw.findViewById(R.id.search_videos)
        autoSuggestAdapter =
            AutoSuggestAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(autoSuggestAdapter)

        autoCompleteTextView.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            autoCompleteTextView.setText("")
            val seleted_sggtn: String = autoSuggestAdapter.getObject(position)
            val intent = Intent(requireActivity(), SearchhActivity::class.java)
            intent.putExtra("searchdata", seleted_sggtn)
            startActivity(intent)
        }
        autoCompleteTextView.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // TODO do something
//                hideKeyboardFrom(getActivity(), v);
                searchvideosstr = autoCompleteTextView.text.toString()
                if (searchvideosstr == "" || searchvideosstr.isEmpty()) {
                    Toast.makeText(requireActivity(), "Enter something!!", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    autoCompleteTextView.setText("")
                    searchvideosstr = autoCompleteTextView.text.toString()
                    val intent = Intent(requireActivity(), SearchhActivity::class.java)
                    intent.putExtra("searchdata", searchvideosstr)
                    startActivity(intent)
                }
                handled = true
            }
            handled
        }
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                makeApiCall(s.toString())
            }
        })
        getLEarnVidsFilter1()

        llfilter.setOnClickListener(View.OnClickListener { v: View? ->
            rankDialog = Dialog(requireActivity(), R.style.AppThemee)
            rankDialog.setContentView(R.layout.dlg_watch_filter)
            rankDialog.setCancelable(true)
            canceldialog = rankDialog.findViewById<ImageView>(R.id.cancel_dialog)
            searchlang = rankDialog.findViewById<AutoCompleteTextView>(R.id.search_lang)
            searchcrtr = rankDialog.findViewById<AutoCompleteTextView>(R.id.search_crtr)
            searchchanl = rankDialog.findViewById<AutoCompleteTextView>(R.id.search_chanl)
            llfiltercrtr = rankDialog.findViewById<LinearLayout>(R.id.ll_filter_crtr)
            llfilterlang = rankDialog.findViewById<LinearLayout>(R.id.ll_filter_lang)
            llfilterchanl = rankDialog.findViewById<LinearLayout>(R.id.ll_filter_chanl)
            rbsrchchnmae = rankDialog.findViewById<RadioButton>(R.id.rb_srch_chnmae)
            rbsrchcrtr = rankDialog.findViewById<RadioButton>(R.id.rb_srch_crtr)
            rbsrchlnguge = rankDialog.findViewById<RadioButton>(R.id.rb_srch_lnguge)
            radioGroupwatch = rankDialog.findViewById<RadioGroup>(R.id.radioGroup_watch)
            autoSuggestAdapter = AutoSuggestAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line
            )
            searchlang.threshold = 1
            searchlang.setAdapter(autoSuggestAdapter)
            autoSuggestAdapter = AutoSuggestAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line
            )
            searchcrtr.threshold = 1
            searchcrtr.setAdapter(autoSuggestAdapter)
            autoSuggestAdapter = AutoSuggestAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line
            )
            searchchanl.threshold = 1
            searchchanl.setAdapter(autoSuggestAdapter)
            searchchanl.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    ApiCall.chanel_srch(requireActivity(), s.toString(), { response ->
                        val stringList: MutableList<String> = ArrayList()
                        try {
                            val responseObject = JSONObject(response!!)
                            val array: JSONArray = responseObject.getJSONArray("name")
                            for (i in 0 until array.length()) {
                                val row: JSONObject = array.getJSONObject(i)
                                stringList.add(row.getString("name"))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        autoSuggestAdapter.setData(stringList)
                        autoSuggestAdapter.notifyDataSetChanged()
                    }) { error -> }
                }
            })
            searchcrtr.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    ApiCall.instructor_srch(requireActivity(), s.toString(), { response ->
                        val stringList: MutableList<String> = ArrayList()
                        try {
                            val responseObject = JSONObject(response!!)
                            val array: JSONArray = responseObject.getJSONArray("data")
                            for (i in 0 until array.length()) {
                                val row: JSONObject = array.getJSONObject(i)
                                stringList.add(row.getString("title"))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        autoSuggestAdapter.setData(stringList)
                        autoSuggestAdapter.notifyDataSetChanged()

                    }) { error -> }
                }
            })
            searchlang.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    makeApiCall(s.toString())
                }
            })
            //            LinearLayout llfiltercrtr, ll_filter_lang, ll_filter_chanl;
            radioGroupwatch.setOnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
                when (checkedId) {
                    R.id.rb_srch_chnmae -> {
                        llfilterchanl.visibility = View.VISIBLE
                        llfiltercrtr.visibility = View.GONE
                        llfilterlang.visibility = View.GONE
                    }

                    R.id.rb_srch_crtr -> {
                        llfilterchanl.visibility = View.GONE
                        llfiltercrtr.visibility = View.VISIBLE
                        llfilterlang.visibility = View.GONE
                    }

                    R.id.rb_srch_lnguge -> {
                        llfilterchanl.visibility = View.GONE
                        llfiltercrtr.visibility = View.GONE
                        llfilterlang.visibility = View.VISIBLE
                    }
                }
            }
            canceldialog.setOnClickListener({ ve: View? -> rankDialog.dismiss() })
            rankDialog.show()
        })
        return vid_vw
    }


    fun makeApiCallcrtr(text: String?) {
        if (text != null) {
            ApiCall.instructor_srch(requireActivity(), text, { response ->
                val stringList: MutableList<String> = ArrayList()
                try {
                    val responseObject = JSONObject(response!!)
                    val array: JSONArray = responseObject.getJSONArray("data")
                    for (i in 0 until array.length()) {
                        val row: JSONObject = array.getJSONObject(i)
                        stringList.add(row.getString("title"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                //IMPORTANT: set data here and notify
                autoSuggestAdapter.setData(stringList)
                autoSuggestAdapter.notifyDataSetChanged()
            }) { error -> }
        }
    }

    fun makeApiCall(text: String?) {
        if (text != null) {
            ApiCall.make(requireActivity(), text, { response ->
                val stringList: MutableList<String> = ArrayList()
                try {
                    val responseObject = JSONObject(response!!)
                    val array: JSONArray = responseObject.getJSONArray("data")
                    for (i in 0 until array.length()) {
                        val row: JSONObject = array.getJSONObject(i)
                        stringList.add(row.getString("title"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                //IMPORTANT: set data here and notify
                autoSuggestAdapter.setData(stringList)
                autoSuggestAdapter.notifyDataSetChanged()
            }) { error -> }
        }
    }

    //            customDialog.show();
    private val sprtVidsFilter: Unit
        get() {
            try {
//            customDialog.show();
                shimmerFrameLayout.startShimmer()
                val url: String = Configs.BASE_URL2 + "my-supportvideos"
                RetrofitClient.getClient().suprtvidList(url, "application/json", "Bearer $token")
                    ?.enqueue(object : GlobalCallback<MySprtVidPojo?>(rvallvidfilter) {
                        @SuppressLint("SetTextI18n")
                        override fun onResponse(
                            call: Call<MySprtVidPojo?>,
                            response: Response<MySprtVidPojo?>
                        ) {
                            shimmerFrameLayout.stopShimmer()
                            try {
                                val sprtvidsts: String? = response.body()?.status
                                if (sprtvidsts != null) {
                                    if (sprtvidsts.contains("0")) {
                                        nodata.visibility = View.VISIBLE
                                    } else {
                                        if (sprtvidsts.contains("1")) {
                                            nodata.visibility = View.GONE
                                            val mySprtVids: List<MySprtVidList>? =
                                                response.body()?.videos
                                            if (mySprtVids != null) {
                                                if (mySprtVids.isEmpty()) {
                                                    nodata.visibility = View.VISIBLE
                                                } else {
                                                    shimmerFrameLayout.visibility = View.GONE
                                                    rvallvidfilter!!.visibility = View.VISIBLE
                                                    adapter_all_videos_fliter =
                                                        SuprtVidListAdapter(
                                                            mySprtVids,
                                                            requireActivity()
                                                        )
                                                    rvallvidfilter!!.adapter =
                                                        adapter_all_videos_fliter
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
        }

    private val suprSprtVidsFilter: Unit
        get() {
            try {
                shimmerFrameLayout.startShimmer()
                val url: String = Configs.BASE_URL2 + "my-ssupportvideos"
                RetrofitClient.getClient().sprsuprtvidList(url, "application/json", "Bearer $token")
                    ?.enqueue(object : GlobalCallback<MySprSprtVidsPojo?>(rvallvidfilter) {
                        @SuppressLint("SetTextI18n")
                        override fun onResponse(
                            call: Call<MySprSprtVidsPojo?>,
                            response: Response<MySprSprtVidsPojo?>
                        ) {
                            shimmerFrameLayout.stopShimmer()
                            try {
                                if (response.body() != null) {
                                    val sprtvidsts: String? = response.body()?.status
                                    if (sprtvidsts != null) {
                                        if (sprtvidsts.contains("0")) {
                                            nodata.visibility = View.VISIBLE
                                        } else {
                                            if (sprtvidsts.contains("1")) {
                                                nodata.visibility = View.GONE
                                                val mySprtVids: List<MySuprSprtVidList>? =
                                                    response.body()?.videos
                                                if (mySprtVids != null) {
                                                    if (mySprtVids.isEmpty()) {
                                                        nodata.visibility = View.VISIBLE
                                                        shimmerFrameLayout.visibility = View.GONE
                                                    } else {
                                                        //                                            ll_sprtr_vids.visibility = View.VISIBLE;
                                                        shimmerFrameLayout.visibility = View.GONE
                                                        adapter_all_videos_fliter =
                                                            SuprSuprtVidListAdapter(
                                                                mySprtVids,
                                                                requireActivity()
                                                            )
                                                        rvallvidfilter!!.adapter =
                                                            adapter_all_videos_fliter
                                                    }
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
        }
    val ltstVidsFilter: Unit
        get() {
//        customDialog.show();
            shimmerFrameLayout.startShimmer()
            //        blur_reg1.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "latest-videos"
            RetrofitClient.getClient().allvidList1(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<LtstVidPojo?>(rvallvidfilter) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<LtstVidPojo?>,
                        response: Response<LtstVidPojo?>
                    ) {
//                        customDialog.dismiss();
//                        blur_reg1.visibility = View.GONE;
                        try {
                            val catggryList: List<LtstVidDatum>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rvallvidfilter!!.visibility = View.GONE
                                } else {
                                    //                                linearLayout.visibility = View.GONE;
                                    shimmerFrameLayout.stopShimmer()
                                    shimmerFrameLayout.visibility = View.GONE
                                    rvallvidfilter!!.visibility = View.VISIBLE
                                    rvallvidfilter!!.visibility = View.VISIBLE
                                    adapter_all_videos_fliter =
                                        AllVidListAdapterLtst(catggryList, requireActivity())
                                    rvallvidfilter!!.adapter = adapter_all_videos_fliter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    private fun getLEarnVidsFilter() {
//        customDialog.show();
        shimmerFrameLayout.startShimmer()
        val url: String = Configs.BASE_URL2 + "videos-list"
        RetrofitClient.getClient().allvidList(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rvallvidfilter) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<OneVidListPojo?>,
                    response: Response<OneVidListPojo?>
                ) {
                    customDialog.dismiss()
                    try {
                        val catggryList: List<OneDataItem>? = response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rvallvidfilter!!.visibility = View.GONE
                            } else {
                                //                                linearLayout.visibility = View.GONE;
                                shimmerFrameLayout.stopShimmer()
                                shimmerFrameLayout.visibility = View.GONE
                                rvallvidfilter!!.visibility = View.VISIBLE
                                adapter_all_videos_fliter =
                                    AllVidListAdapter(catggryList, requireActivity())
                                //                                Collections.shuffle(catggryList, new Random());
                                rvallvidfilter!!.adapter = adapter_all_videos_fliter
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getLEarnVidsFilter1() {
        shimmerFrameLayout.startShimmer()
        val url: String = Configs.BASE_URL2 + "videos-list"
        RetrofitClient.getClient().allvidList(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rvallvidfilter) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<OneVidListPojo?>,
                    response: Response<OneVidListPojo?>
                ) {
//                        customDialog.dismiss();
                    try {
                        val catggryList: List<OneDataItem?>? = response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rvallvidfilter!!.visibility = View.GONE
                            } else {
                                //                                linearLayout.visibility = View.GONE;
                                shimmerFrameLayout.stopShimmer()
                                shimmerFrameLayout.visibility = View.GONE
                                rvallvidfilter!!.visibility = View.VISIBLE
                                adapter_all_videos_fliter = AllVidListAdapter(
                                    catggryList as List<OneDataItem>?,
                                    requireActivity()
                                )
                                Collections.shuffle(catggryList, Random())
                                rvallvidfilter!!.adapter = adapter_all_videos_fliter
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getTrndngVidsFilter() {
        shimmerFrameLayout.startShimmer()
        //        customDialog.show();
        val url: String = Configs.BASE_URL2 + "creater-ranking"
        RetrofitClient.getClient().crtrrnkingg(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CrtrRnkingPojo?>(rvallvidfilter) {
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
                                val catggryList: List<CrDatum>? = response.body()?.data
                                if (catggryList != null) {
                                    if (catggryList.isEmpty()) {
                                        rvallvidfilter!!.visibility = View.GONE
                                    } else {
                                        //                                    linearLayout.visibility = View.GONE;
                                        shimmerFrameLayout.stopShimmer()
                                        shimmerFrameLayout.visibility = View.GONE
                                        rvallvidfilter!!.visibility = View.VISIBLE
                                        adapter_all_videos_fliter =
                                            CrtrRnkngAdapterTrnd1(catggryList, requireActivity())
                                        rvallvidfilter!!.adapter = adapter_all_videos_fliter
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
        private lateinit var adapter_all_videos_fliter: RecyclerView.Adapter<*>
    }
}


//                                linearLayout.visibility = View.GONE;//                        customDialog.dismiss();

//                        blur_reg1.visibility = View.GONE;
//        customDialog.show();
//        blur_reg1.visibility = View.VISIBLE;
//        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
//            switch (checkedId) {
//
//                case R.id.rb_all_videos:
//                case R.id.rb_misc_videos:
//                    shimmerFrameLayout.visibility = View.VISIBLE;
//                    shimmerFrameLayout.startShimmer();
//                    rvallvidfilter.visibility = View.GONE;
//                    getLEarnVidsFilter();
//                    break;
//                case R.id.rb_latest_videos:
//                    shimmerFrameLayout.visibility = View.VISIBLE;
//                    shimmerFrameLayout.startShimmer();
//                    rvallvidfilter.visibility = View.GONE;
//                    getLtstVidsFilter();
//                    break;
//                case R.id.rb_trnd_videos:
//                    shimmerFrameLayout.visibility = View.VISIBLE;
//                    shimmerFrameLayout.startShimmer();
//                    rvallvidfilter.visibility = View.GONE;
//                    getTrndngVidsFilter();
//                    break;
//                case R.id.rb_action_supportt:
//                    shimmerFrameLayout.visibility = View.VISIBLE;
//                    shimmerFrameLayout.startShimmer();
//                    rvallvidfilter.visibility = View.GONE;
//                    shimmerFrameLayout.startShimmer();
//                    getSprtVidsFilter();
//                    break;
//                case R.id.rb_action_ssupportt:
//                    shimmerFrameLayout.visibility = View.VISIBLE;
//                    shimmerFrameLayout.startShimmer();
//                    rvallvidfilter.visibility = View.GONE;
//                    shimmerFrameLayout.startShimmer();
//                    getSuprSprtVidsFilter();
//                    break;
//                default:
//                    getLEarnVidsFilter();
//            }
//        });
//    private void getSuprSprtVidsFilter() {
//
////        try {
//
////            customDialog.show();
//
//        shimmerFrameLayout.startShimmer();
//
//        String url = Configs.BASE_URL2 + "my-supportvideos";
//        RetrofitClient.getClient().suprtvidList(url, "application/json",
//                        "Bearer " + token)
//                ?.enqueue(new GlobalCallback<MySprtVidPojo>(rvallvidfilter) {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<MySprtVidPojo> call, Response<MySprtVidPojo> response) {
//
//                        shimmerFrameLayout.stopShimmer();
//                        try {
//
//                            String sprtvidsts ? = response.body().getStatus();
//
//                            if (sprtvidsts.contains("0")) {
////                                    nodata.visibility = View.VISIBLE;
//                            } else if (sprtvidsts.contains("1")) {
////                                    nodata.visibility = View.GONE;
//                                List<MySprtVidList> mySprtVids ? = response.body().getVideos();
//
//                                if (mySprtVids.isEmpty()) {
////                                        nodata.visibility = View.VISIBLE;
//
//                                } else {
//
//                                    shimmerFrameLayout.visibility = View.GONE;
//                                    rvallvidfilter.visibility = View.VISIBLE;
//                                    adapter_all_videos_fliter = new SuprtVidListAdapter(mySprtVids, getActivity());
//                                    Collections.shuffle(mySprtVids, new Random());
//                                    rvallvidfilter.setAdapter(adapter_all_videos_fliter);
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }


//    private TextWatcher createTextWatcher(AutoCompleteTextView editText, int apiType) {
//        return new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                makeApiCall(String.valueOf(s), apiType);
//            }
//        };
//    }
//    public void makeApiCall(String text, int apiType) {
//        String apiUrl = "";
//        // Set the apiUrl based on the apiType
//        switch (apiType) {
//            case 1:
//                apiUrl = Configs.BASE_URL2 + "channelsearch/" + text;
//                break;
//            case 2:
//                apiUrl = Configs.BASE_URL2 + "instructorsearch/" + text;
//                break;
//            case 3:
//                apiUrl = Configs.BASE_URL2 + "coursesearch/" + text;
//                break;
//        }
//
//        ApiCall.make(getActivity(), apiUrl, response -> {
//            List<String> stringList = new ArrayList<>();
//            try {
//                switch (apiType) {
//                    case 1:
//                        JSONObject responseObject1 = new JSONObject(response);
//                        JSONArray array = responseObject1.getJSONArray("data");
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject row = array.getJSONObject(i);
//                            stringList.add(row.getString("title"));
//                        }
//                        break;
//                    case 2:
//                        JSONObject responseObject2 = new JSONObject(response);
//                        JSONArray array2 = responseObject2.getJSONArray("name");
//
//                        for (int i = 0; i < array2.length(); i++) {
//                            JSONObject row = array2.getJSONObject(i);
//                            String fname = row.getString("fname");
//                            String lname = row.getString("lname");
//                            if (fname.contains(text) || lname.contains(text)) {
//                                stringList.add(fname + " " + lname);
//                            }
//                        }
//                        break;
//                    case 3:
//                        JSONObject responseObject3 = new JSONObject(response);
//                        JSONArray array3 = responseObject3.getJSONArray("title");
//                        for (int i = 0; i < array3.length(); i++) {
//                            JSONObject row = array3.getJSONObject(i);
//                            stringList.add(row.getString("title"));
//                        }
//                        break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            // IMPORTANT: set data here and notify
//            autoSuggestAdapter.setData(stringList);
//            autoSuggestAdapter.notifyDataSetChanged();
//        }, error -> {
//            // Handle error here
//        });
//    }


//        ll_filter.setOnClickListener(v -> {
//            rankDialog = new Dialog(getActivity(), R.style.FullHeightDialog);
//            rankDialog.setContentView(R.layout.dlg_watch_filter);
//            rankDialog.setCancelable(true);
//
//            search_chanl = rankDialog.findViewById(R.id.search_chanl);
//            search_crtr = rankDialog.findViewById(R.id.search_crtr);
//            searchlang = rankDialog.findViewById(R.id.searchlang);
//            radioGroupwatch = rankDialog.findViewById(R.id.radioGroupwatch);
//
//            search_chanl.addTextChangedListener(createTextWatcher(search_chanl, 1));
//            search_crtr.addTextChangedListener(createTextWatcher(search_crtr, 2));
//            searchlang.addTextChangedListener(createTextWatcher(searchlang, 3));
//
//            radioGroupwatch.setOnCheckedChangeListener((radioGroup, checkedId) -> {
//                search_chanl.setVisibility(checkedId == R.id.rb_srch_chnmae ? View.VISIBLE : View.GONE);
//                search_crtr.setVisibility(checkedId == R.id.rbsrchcrtr ? View.VISIBLE : View.GONE);
//                searchlang.setVisibility(checkedId == R.id.rb_srch_lnguge ? View.VISIBLE : View.GONE);
//            });
//
//            rankDialog.findViewById(R.id.cancel_dialog).setOnClickListener(ve -> rankDialog.dismiss());
//            rankDialog.show();
//        });