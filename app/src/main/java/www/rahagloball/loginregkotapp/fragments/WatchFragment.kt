package www.rahagloball.loginregkotapp.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.SearchhActivity
import www.rahagloball.loginregkotapp.activities.SupprterVidList
import www.rahagloball.loginregkotapp.activities.TLtstVidsActivity
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapter
import www.rahagloball.loginregkotapp.adapters.AllVidListAdapterLtst
import www.rahagloball.loginregkotapp.adapters.AutoSuggestAdapter
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapterTrnd
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapterTrnd1
import www.rahagloball.loginregkotapp.adapters.CuteListAdapter
import www.rahagloball.loginregkotapp.adapters.SuprtVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crtrreanking.CrDatum
import www.rahagloball.loginregkotapp.models.crtrreanking.CrtrRnkingPojo
import www.rahagloball.loginregkotapp.models.cutiesss.CutiesPojo
import www.rahagloball.loginregkotapp.models.cutiesss.DataItemCute
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidDatum
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidPojo
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidList
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidPojo
import www.rahagloball.loginregkotapp.models.newsindletest.OneDataItem
import www.rahagloball.loginregkotapp.models.newsindletest.OneVidListPojo
import www.rahagloball.loginregkotapp.networks.demosapi.ApiCall
import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner

class WatchFragment : Fragment(), View.OnTouchListener {
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
    var search_videos_str: String? = null
    var agent_spin: Spinner? = null
    var singleSpinnerSearch: SearchableSpinner? = null
    var cresult: JSONArray? = null
    var city: ArrayList<String>? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    private var layoutManager_cuties: RecyclerView.LayoutManager? = null
    var rv_sprtr_videos: RecyclerView? = null
    private var llm_rv_sprtr_videos: RecyclerView.LayoutManager? = null
    var rv_all_vid_filter: RecyclerView? = null
    private var llm_all_videos_fliter: RecyclerView.LayoutManager? = null
    var rv_learn_imagess: RecyclerView? = null
    var ll_onlinee: LinearLayout? = null

    //    EditText search_videos;
    private var autoSuggestAdapter: AutoSuggestAdapter? = null
    var txt_watchhh: TextView? = null
    var wtch_trndg: TextView? = null
    var sprtr_shw_more: TextView? = null
    var image_cts: ImageView? = null
    var ltst_shw_more: TextView? = null
    var ll_cutiesss: LinearLayout? = null
    var wtch_ltst_right: ImageView? = null
    var all_vid_spinner: Spinner? = null
    var nsv_all_vids: NestedScrollView? = null
    var filter_all_vids_str = arrayOf(
        "Filter Videos",
        "Latest Videos",
        "Trending Videos",
        "My Supported Channel Videos",
        "All Videos"
    )
    var customDialog: CustomDialog? = null
    var linearLayout: LinearLayout? = null
    private val llm_trndngvidsList: RecyclerView.LayoutManager? = null
    private var llm_ltstvidsList: RecyclerView.LayoutManager? = null
    private var recyclerView1IsScrolling = false
    private var recyclerView2IsScrolling = false
    private var recyclerView3IsScrolling = false
    private var recyclerView4IsScrolling = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vid_vw: View = inflater.inflate(R.layout.fragment_videos2, container, false)
        city = ArrayList()
        manager = SessionManager()
        customDialog = CustomDialog(activity)
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        ssProduct1 = vid_vw.findViewById<Spinner>(R.id.ssProduct1)
        submit = vid_vw.findViewById<CardView>(R.id.submitlang)
        language = vid_vw.findViewById<Spinner>(R.id.language)
        ask = vid_vw.findViewById<Button>(R.id.ask)
        txt_watchhh = vid_vw.findViewById<TextView>(R.id.txt_watchhh)
        image_cts = vid_vw.findViewById<ImageView>(R.id.image_cts)
        ll_onlinee = vid_vw.findViewById<LinearLayout>(R.id.ll_onlinee)
        wtch_trndg = vid_vw.findViewById<TextView>(R.id.wtch_trndg)
        rv_sprtr_videos = vid_vw.findViewById<RecyclerView>(R.id.rv_sprtr_videos)
        sprtr_shw_more = vid_vw.findViewById<TextView>(R.id.sprtr_shw_more)
        all_vid_spinner = vid_vw.findViewById<Spinner>(R.id.all_vid_spinner)
        rv_all_vid_filter = vid_vw.findViewById<RecyclerView>(R.id.rv_all_vid_filter)
        linearLayout = vid_vw.findViewById<LinearLayout>(R.id.linearLayout)
        //        search_videos = vid_vw.findViewById(R.id.search_videos);
        blur_reg1 = vid_vw.findViewById<RelativeLayout>(R.id.blur_reg1)
        image_cts?.visibility = View.GONE
        emptylayout = vid_vw.findViewById<LinearLayout>(R.id.emptylayout)
        apply_fsp = vid_vw.findViewById<CardView>(R.id.apply_fsp)
        agent_spin = vid_vw.findViewById<Spinner>(R.id.agent_spin)
        singleSpinnerSearch = vid_vw.findViewById(R.id.multipleItemSelectionSpinner)
        rv_cuties = vid_vw.findViewById<RecyclerView>(R.id.rv_cuties)
        rv_all_videos = vid_vw.findViewById<RecyclerView>(R.id.rv_all_videos)
        rv_learn_imagess = vid_vw.findViewById<RecyclerView>(R.id.videoList)
        nsv_all_vids = vid_vw.findViewById<NestedScrollView>(R.id.nsv_all_vids)

        //misc videos
        layoutManager_vidsList = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_all_videos?.layoutManager=layoutManager_vidsList
        //        rv_all_videos.setNestedScrollingEnabled(false);

        //my supporter videos
        llm_rv_sprtr_videos = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_sprtr_videos?.layoutManager=llm_rv_sprtr_videos
        //        rv_sprtr_videos.setNestedScrollingEnabled(false);

        //latest videos
        layoutManager_cuties = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_cuties?.layoutManager=layoutManager_cuties
        //        rv_cuties.setNestedScrollingEnabled(false);

        //trending videos
        llm_ltstvidsList = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_learn_imagess?.layoutManager=llm_ltstvidsList
        //        rv_learn_imagess.setNestedScrollingEnabled(false);
        llm_all_videos_fliter = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_all_vid_filter?.layoutManager=llm_all_videos_fliter


        //trending videos
        rv_learn_imagess?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView1IsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }
        })


        //latest videos
        rv_cuties?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView2IsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }
        })

        //my supporter videos
        rv_sprtr_videos?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView3IsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }
        })


        //misc videos
        rv_all_videos?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerView4IsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }
        })
        getLEarnVids()
        getTrndngVids()
        getLtstVids()
        getSprtVids()
        val dataAdapter4: ArrayAdapter<String>? =
            activity?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, filter_all_vids_str) }
        if (dataAdapter4 != null) {
            dataAdapter4.setDropDownViewResource(R.layout.custom_spiner_layout)
        }
        all_vid_spinner?.adapter = dataAdapter4
        all_vid_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        nsv_all_vids?.visibility = View.VISIBLE
                        //                        linearLayout.visibility = View.VISIBLE;
                        rv_all_vid_filter?.visibility = View.GONE
                        Toast.makeText(activity, "Kindly Filter ", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
                        nsv_all_vids?.visibility = View.GONE
                        //                        linearLayout.visibility = View.VISIBLE;
                        getLtstVidsFilter()
                    }

                    2 -> {
                        //                        nsv_all_vids.visibility = View.GONE;
                        linearLayout?.visibility = View.VISIBLE
                        getTrndngVidsFilter()
                    }

                    3 -> {
                        nsv_all_vids?.visibility = View.GONE
                        //                        linearLayout.visibility = View.VISIBLE;
                        getSprtVidsFilter()
                    }

                    4 -> {
                        nsv_all_vids?.visibility = View.GONE
                        //                        linearLayout.visibility = View.VISIBLE;
                        getLEarnVidsFilter()
                    }

                    else -> //                        linearLayout.visibility = View.VISIBLE;
    //
                        nsv_all_vids?.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val autoCompleteTextView: AutoCompleteTextView =
            vid_vw.findViewById<AutoCompleteTextView>(R.id.search_videos)
        //        final TextView selectedText = vid_vw.findViewById(R.id.selected_item);
        //Setting up the adapter for AutoSuggest
        autoSuggestAdapter = activity?.let {
            AutoSuggestAdapter(
                it,
                android.R.layout.simple_dropdown_item_1line
            )
        }
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(autoSuggestAdapter)
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
    //                    selectedText.setText(autoSuggestAdapter.getObject(position));
                autoCompleteTextView.setText("")
                val seleted_sggtn: String? = autoSuggestAdapter?.getObject(position)
                val intent = Intent(activity, SearchhActivity::class.java)
                intent.putExtra("searchdata", seleted_sggtn)
                startActivity(intent)
            }
        autoCompleteTextView.setOnEditorActionListener(TextView.OnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // TODO do something
//                hideKeyboardFrom(getActivity(), v);
                search_videos_str = autoCompleteTextView?.text.toString()
                if (search_videos_str == "" || search_videos_str!!.isEmpty()) {
                    Toast.makeText(activity, "Enter something!!", Toast.LENGTH_SHORT).show()
                } else {
                    autoCompleteTextView.setText("")
                    val intent = Intent(activity, SearchhActivity::class.java)
                    intent.putExtra("searchdata", search_videos_str)
                    startActivity(intent)
                }
                handled = true
            }
            handled
        })
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
//                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
//                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE, AUTO_COMPLETE_DELAY);
            }

            override fun afterTextChanged(s: Editable) {
//                hideKeyboardFrom(Objects.requireNonNull(getActivity()), autoCompleteTextView);
                makeApiCall(s.toString())
            }
        })
        wtch_ltst_right = vid_vw.findViewById<ImageView>(R.id.wtch_ltst_right)
        ltst_shw_more = vid_vw.findViewById<TextView>(R.id.ltst_shw_more)
        wtch_ltst_right?.visibility = View.VISIBLE
        wtch_ltst_right?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent_ltst = Intent(activity, TLtstVidsActivity::class.java)
            startActivity(intent_ltst)
        })
        ltst_shw_more?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent_ltst = Intent(activity, TLtstVidsActivity::class.java)
            startActivity(intent_ltst)
        })
        sprtr_shw_more?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent_ltst = Intent(activity, SupprterVidList::class.java)
            startActivity(intent_ltst)
        })

        // Set up smooth scrolling
        nsv_all_vids?.setOnTouchListener(View.OnTouchListener { v: View, event: MotionEvent ->
            if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                v.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                v.stopNestedScroll()
            }
            false
        })
        return vid_vw
    }

    fun makeApiCall(text: String?) {
        activity?.let {
            if (text != null) {
                ApiCall.make(it, text, { response ->
                    val stringList: MutableList<String> = ArrayList()
                    try {
                        val responseObject = JSONObject(response)
                        val array: JSONArray = responseObject.getJSONArray("data")
                        for (i in 0 until array.length()) {
                            val row: JSONObject = array.getJSONObject(i)
                            stringList.add(row.getString("title"))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    //IMPORTANT: set data here and notify
                    autoSuggestAdapter?.setData(stringList)
                    autoSuggestAdapter?.notifyDataSetChanged()
                }) { }
            }
        }
    }

    //    public static void hideKeyboardFrom(Context context, View view) {
    //        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    //        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    //    }
    //
    //    @Override
    //    public boolean onTouchEvent(MotionEvent event) {
    //        // Handle touch event here
    //        switch (event.getAction()) {
    //            case MotionEvent.ACTION_DOWN:
    //                // Handle finger down event
    //                break;
    //            case MotionEvent.ACTION_MOVE:
    //                // Handle finger move event
    //                break;
    //            case MotionEvent.ACTION_UP:
    //                // Handle finger up event
    //                break;
    //            default:
    //                // Handle other events
    //                break;
    //        }
    //        return super.onTouchEvent(event);
    //
    //    }
    private fun getSprtVids() {
        try {
//            customDialog.show();
//        blur_reg1.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "my-supportvideos"
            RetrofitClient.getClient().suprtvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<MySprtVidPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<MySprtVidPojo?>,
                        response: Response<MySprtVidPojo?>
                    ) {
//                        blur_reg1.visibility = View.GONE;
//                           customDialog.dismiss();
                        try {
                            val sprtvidsts: String ? = response.body()?.status
                            if (sprtvidsts != null) {
                                if (sprtvidsts.contains("0")) {
                                    sprtr_shw_more?.visibility = View.GONE
                                    //                                    nodata?.visibility = View.VISIBLE;
                                } else if (sprtvidsts.contains("1")) {
                        //                                    nodata?.visibility = View.GONE;
                                    val mySprtVids: List<MySprtVidList> ? = response.body()?.videos
                                    if (mySprtVids != null) {
                                        if (mySprtVids.isEmpty()) {
                                            sprtr_shw_more?.visibility = View.GONE
                                            //                                        nodata?.visibility = View.VISIBLE;
                                        } else {
                                            sprtr_shw_more?.visibility = View.VISIBLE
                                            adapter_rv_sprtr_videos =
                                                activity?.let {
                                                    SuprtVidListAdapter(mySprtVids,
                                                        it
                                                    )
                                                }
                                            rv_sprtr_videos!!.adapter = adapter_rv_sprtr_videos
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

    private fun getSprtVidsFilter() {
        try {
            customDialog?.show()
            val url: String = Configs.BASE_URL2 + "my-supportvideos"
            RetrofitClient.getClient().suprtvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<MySprtVidPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<MySprtVidPojo?>,
                        response: Response<MySprtVidPojo?>
                    ) {
                        customDialog?.dismiss()
                        try {
                            val sprtvidsts: String ? = response.body()?.status
                            if (sprtvidsts != null) {
                                if (sprtvidsts.contains("0")) {
                        //                                    nodata?.visibility = View.VISIBLE;
                                } else if (sprtvidsts.contains("1")) {
                        //                                    nodata?.visibility = View.GONE;
                                    val mySprtVids: List<MySprtVidList> ? = response.body()?.videos
                                    if (mySprtVids != null) {
                                        if (mySprtVids.isEmpty()) {
                                            //                                        nodata?.visibility = View.VISIBLE;
                                        } else {
                                            //                                        linearLayout.visibility = View.GONE;
                                            nsv_all_vids?.visibility = View.GONE
                                            adapter_all_videos_fliter =
                                                activity?.let { SuprtVidListAdapter(mySprtVids, it) }
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLEarnVids() {
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
                    blur_reg1?.visibility  = View.GONE
                    try {
                        val catggryList: List<OneDataItem>?= response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rv_all_videos!!.visibility = View.GONE
                            } else {
                                rv_all_videos!!.visibility = View.VISIBLE
                                adapter_vids_list = activity?.let { AllVidListAdapter(catggryList, it) }
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

    private fun getLEarnVidsFilter() {
        customDialog?.show()
        val url: String = Configs.BASE_URL2 + "videos-list"
        RetrofitClient.getClient().allvidList(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<OneVidListPojo?>(rv_all_videos) {
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
                                nsv_all_vids?.visibility = View.GONE
                                rv_all_vid_filter!!.visibility = View.VISIBLE
                                adapter_all_videos_fliter =
                                    activity?.let { AllVidListAdapter(catggryList, it) }
                                //                                Collections.shuffle(catggryList, new Random());
                                rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getLtstVids() {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "latest-videos"
        RetrofitClient.getClient().allvidList1(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<LtstVidPojo?>(rv_all_videos) {
                @SuppressLint("SetTextI18n")
               override fun onResponse(call: Call<LtstVidPojo?>, response: Response<LtstVidPojo?>) {
                    blur_reg1?.visibility  = View.GONE
                    try {
                        val catggryList: List<LtstVidDatum>?=response.body()?.data
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

    private fun getLtstVidsFilter() {
        customDialog?.show()
        //        blur_reg1.visibility = View.VISIBLE;
        val url: String = Configs.BASE_URL2 + "latest-videos"
        RetrofitClient.getClient().allvidList1(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<LtstVidPojo?>(rv_all_videos) {
                @SuppressLint("SetTextI18n")
               override fun onResponse(call: Call<LtstVidPojo?>, response: Response<LtstVidPojo?>) {
                    customDialog?.dismiss()
                    //                        blur_reg1.visibility = View.GONE;
                    try {
                        val catggryList: List<LtstVidDatum>?=response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rv_cuties!!.visibility = View.GONE
                            } else {
                    //                                linearLayout.visibility = View.GONE;
                    //
                                nsv_all_vids?.visibility  = View.GONE
                                rv_all_vid_filter!!.visibility = View.VISIBLE
                                adapter_all_videos_fliter =
                                    activity?.let { AllVidListAdapterLtst(catggryList, it) }
                                rv_all_vid_filter!!.adapter = adapter_all_videos_fliter
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getTrndngVids() {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "creater-ranking"
        RetrofitClient.getClient().crtrrnkingg(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CrtrRnkingPojo?>(rv_all_videos) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(
                    call: Call<CrtrRnkingPojo?>,
                    response: Response<CrtrRnkingPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val crtrres: String ? = response.body()?.status
                        if (crtrres != null) {
                            if (crtrres.contains("1")) {
                                val catggryList: List<CrDatum>?= response.body()?.data
                                if (catggryList != null) {
                                    if (catggryList.isEmpty()) {
                                        rv_learn_imagess!!.visibility = View.GONE
                                    } else {
                                        rv_learn_imagess!!.visibility = View.VISIBLE
                                        adapter_ltstvids =
                                            activity?.let { CrtrRnkngAdapterTrnd(catggryList, it) }
                                        rv_learn_imagess!!.adapter = adapter_ltstvids
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

    private fun getTrndngVidsFilter() {
        customDialog?.show()
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
                    customDialog?.dismiss()
                    try {
                        val crtrres: String ? = response.body()?.status
                        if (crtrres != null) {
                            if (crtrres.contains("1")) {
                                val catggryList: List<CrDatum>?= response.body()?.data
                                if (catggryList != null) {
                                    if (catggryList.isEmpty()) {
                                        rv_learn_imagess!!.visibility = View.GONE
                                    } else {
                                        nsv_all_vids?.visibility = View.GONE
                                        //                                    linearLayout.visibility = View.GONE;
                                        rv_all_vid_filter!!.visibility = View.VISIBLE
                                        adapter_all_videos_fliter =
                                            catggryList.let { activity?.let { it1 ->
                                                CrtrRnkngAdapterTrnd1(it,
                                                    it1
                                                )
                                            } }
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

    private fun getCuteVids() {
        blur_reg1?.visibility  = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "cuties"
        RetrofitClient.getClient().gtcutelist(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CutiesPojo?>(rv_cuties) {
                @SuppressLint("SetTextI18n")
              override  fun onResponse(call: Call<CutiesPojo?>, response: Response<CutiesPojo?>) {
                    blur_reg1?.visibility  = View.GONE
                    try {
                        val catggryList: List<DataItemCute>?=response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                rv_cuties!!.visibility = View.GONE
                            } else {
                                rv_cuties!!.visibility = View.VISIBLE
                                adapter_cuties_list = catggryList.let { activity?.let { it1 ->
                                    CuteListAdapter(it,
                                        it1
                                    )
                                } }
                                rv_cuties!!.adapter = adapter_cuties_list
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, ev: MotionEvent): Boolean {
        if (recyclerView1IsScrolling) {
            rv_learn_imagess!!.dispatchTouchEvent(ev)
        } else if (recyclerView2IsScrolling) {
            rv_cuties!!.dispatchTouchEvent(ev)
        } else if (recyclerView3IsScrolling) {
            rv_sprtr_videos!!.dispatchTouchEvent(ev)
        } else if (recyclerView4IsScrolling) {
            rv_all_videos!!.dispatchTouchEvent(ev)
        } else {
            return true
        }
        return false
    }

    companion object {
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
        private val adapter_imgs: RecyclerView.Adapter<*>? = null
        private var adapter_cuties_list: RecyclerView.Adapter<*>? = null
        private var adapter_rv_sprtr_videos: RecyclerView.Adapter<*>? = null
        private var adapter_all_videos_fliter: RecyclerView.Adapter<*>? = null
        private val adapter_trndngvids: RecyclerView.Adapter<*>? = null
        private var adapter_ltstvids: RecyclerView.Adapter<*>? = null
    }
}