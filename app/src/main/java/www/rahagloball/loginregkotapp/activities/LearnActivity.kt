package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.courses.CrsAddSection
import www.rahagloball.loginregkotapp.adapters.AutoSuggestAdapter
import www.rahagloball.loginregkotapp.adapters.LearnCatsAdapter
import www.rahagloball.loginregkotapp.adapters.LearndataAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomTypefaceSpan
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.Learnn.DataItem
import www.rahagloball.loginregkotapp.models.Learnn.LCatPojo
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getinstrctr.GetInstrctrPojo
import www.rahagloball.loginregkotapp.models.lrndtls_new.CourseDtlsPojo
import www.rahagloball.loginregkotapp.models.lrndtls_new.DatumCd
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.networks.demosapi.ApiCall
import www.rahagloball.loginregkotapp.rate.RateThisApp
import www.rahagloball.loginregkotapp.tandc.AboutUs
import www.rahagloball.loginregkotapp.tandc.NlPaidTerms
import www.rahagloball.loginregkotapp.tandc.UserAgreement

class LearnActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var bottomNavigationView: BottomNavigationView? = null
    var blur_reg1: RelativeLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var rv_learn_imagess: RecyclerView? = null
    var rv_learn_cats: RecyclerView? = null
    var rv_learn_videos: RecyclerView? = null
    var rv_all_videos: RecyclerView? = null
    private var layoutManager_cats: RecyclerView.LayoutManager? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    private var layoutManager_vids: RecyclerView.LayoutManager? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var assoc_id: String? = null
    var txt_learn_cats: TextView? = null
    var txt_learn_imgs: TextView? = null
    var txt_learn_vidss: TextView? = null
    var fragment: Fragment? = null
    var context: Context? = null
    var nodata: RelativeLayout? = null
    var channel_pic: CircleImageView? = null
    var toolbar: Toolbar? = null
    var appBbar: AppBarLayout? = null
    var navigationView: NavigationView? = null
    var nav_user_img: CircleImageView? = null
    var FireBaseToken: String? = null
    var user_pic: String? = null
    var img_path: String? = null
    var gender: String? = null
    var name: String? = null
    var nav_user_pic: String? = null
    var nav_img_path: String? = null
    var nav_user_name_str: String? = null
    var nav_email_str: String? = null
    var quiz_result: String? = null
    var phonenumber: String? = null
    var namess: String? = null
    var nav_user_name: TextView? = null
    var nav_user_email: TextView? = null
    var toolbar_titlee: TextView? = null
    var fl_become_an_instrcr: FrameLayout? = null

    //    SwipeRefreshLayout swipe_refresh;
    var ns_touch: NestedScrollView? = null
    var gone = false
    var search_videos_str: String? = null
    var pool_id_str = true
    var search_videos_lrn: EditText? = null
    var context1: Activity? = null
    var tv_action1: TextView? = null
    var tv_action: TextView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var ll_filter: LinearLayout? = null
    var rankDialog: Dialog? = null
    var radioGroup_lrn: RadioGroup? = null
    var rb_srch_crse: RadioButton? = null
    var rb_srch_instrctr: RadioButton? = null
    var search_crses: AutoCompleteTextView? = null
    var search_instrctr: AutoCompleteTextView? = null
    var cancel_dialog: ImageView? = null
    var autoSuggestAdapter: AutoSuggestAdapter? = null
    var ll_filter_crses: LinearLayout? = null
    var ll_filter_instrctr: LinearLayout? = null

    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_nav)
        context = this@LearnActivity
        manager = SessionManager()
        token = manager?.getPreferences(this@LearnActivity, Constants.USER_TOKEN_LRN)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        search_videos_lrn = findViewById<EditText>(R.id.search_videos_lrn)
        ns_touch = findViewById<NestedScrollView>(R.id.ns_touch)
        fl_become_an_instrcr = findViewById<FrameLayout>(R.id.fl_become_an_instrcr)
        bottomNavigationView?.selectedItemId = R.id.btm_exprt
        navigationView = findViewById<NavigationView>(R.id.nav_view)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        ll_filter = findViewById<LinearLayout>(R.id.ll_filter)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        val header: View? = navigationView?.getHeaderView(0)
        nav_user_img = header?.findViewById<CircleImageView>(R.id.nav_user_img)
        nav_user_pic = manager?.getPreferences(this@LearnActivity, Constants.USER_PROFILE_PIC)
        nav_user_name = header?.findViewById<TextView>(R.id.nav_user_name)
        nav_user_name_str = manager?.getPreferences(this@LearnActivity, Constants.USER_NAME)
        nav_user_name?.text = (nav_user_name_str)
        nav_user_email = header?.findViewById<TextView>(R.id.nav_user_email)
        nav_email_str = manager?.getPreferences(this@LearnActivity, Constants.USER_EMAIL)
        nav_user_email?.setText(nav_email_str)
        val m: Menu? = navigationView?.getMenu()
        if (m != null) {
            for (i in 0 until m.size()) {
                val mi = m.getItem(i)

                //for aapplying a font to subMenu ...
                val subMenu: SubMenu? = mi.subMenu
                if (subMenu != null && subMenu.size() > 0) {
                    for (j in 0 until subMenu.size()) {
                        val subMenuItem: MenuItem = subMenu.getItem(j)
                        applyFontToMenuItem(subMenuItem)
                    }
                }

                //the method we have create in activity
                applyFontToMenuItem(mi)
            }
        }
        navigationView?.setNavigationItemSelectedListener(this)
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

//        navigation = findViewById(R.id.navigation);
        toolbar_titlee = findViewById<TextView>(R.id.toolbar_titlee)
        appBbar = findViewById<AppBarLayout>(R.id.appBbar)
        bottomNavigationView?.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener setOnItemSelectedListener@{ item: MenuItem ->
            val itemId = item.itemId
            if (itemId == R.id.btm_dashbrd) {
                startActivity(Intent(applicationContext, HomeDemoActivityCtgry::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_cuties) {
                startActivity(Intent(this@LearnActivity, CutiesActvtyAltered::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_home) {
                startActivity(Intent(this@LearnActivity, WatchActivty::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_exprt) { //                    startActivity(new Intent(LearnActivity.this, LearnActivity.class));
                //                    overridePendingTransition(0, 0);
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_profle) {
                startActivity(Intent(this@LearnActivity, SubCatListActivity::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            }
            false
        })


//        Bundle bundle = getActivity().intent.extras;
//        assoc_id = bundle.getString("ass_id");
        rv_learn_videos = findViewById<RecyclerView>(R.id.rv_learn_videos)
        rv_learn_cats = findViewById<RecyclerView>(R.id.rv_learn_cats)
        rv_learn_imagess = findViewById<RecyclerView>(R.id.rv_learn_imagess)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        txt_learn_cats = findViewById<TextView>(R.id.txt_learn_cats)
        txt_learn_imgs = findViewById<TextView>(R.id.txt_learn_imgs)
        txt_learn_vidss = findViewById<TextView>(R.id.txt_learn_vidss)
        tv_action1 = findViewById<TextView>(R.id.tv_action1)
        tv_action = findViewById<TextView>(R.id.tv_action)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        nodata = findViewById<RelativeLayout>(R.id.nodata)

//        rv_learn_videos.setHasFixedSize(true);
//        rv_learn_cats.setHasFixedSize(true);
//        rv_learn_imagess.setHasFixedSize(true);
        layoutManager_cats = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager_vids = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_learn_cats!!.layoutManager = layoutManager_cats
        rv_learn_imagess!!.layoutManager = layoutManager_imgs
        rv_learn_videos!!.layoutManager = layoutManager_vids
        layoutManager_vidsList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.layoutManager = layoutManager_vidsList


//        ns_touch.setOnTouchListener((v, motionEvent) -> {
//
//            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                if (!gone) {
//                    Animation animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move_animation);
//                    fl_become_an_instrcr.startAnimation(animation);
//                    fl_become_an_instrcr.visibility = View.GONE;
//                    gone = true;
//                } else {
//                    Animation animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move_animation_out);
//                    fl_become_an_instrcr.startAnimation(animation);
//                    fl_become_an_instrcr.visibility = View.VISIBLE;
//                    gone = false;
//
//                }
//
//            }
//
//            return true;
//        });


//        getLEarnCats();
        lEarnImgs
        //        getLEarnVids();

//        swipe_refresh.setOnRefreshListener(() -> {
        lEarnImgs
        //            swipe_refresh.setRefreshing(false);
//
//        });


//        getbizcnttedlist();

//        fl_become_an_instrcr.setOnClickListener(v -> {
//
//            getbizcnttedlist();
//
//
//        });
        search_videos_lrn?.setOnEditorActionListener(TextView.OnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_NEXT) {
//                hideKeyboardFrom(context, v);
                checkvalidation()
                handled = true
            }
            handled
        })
        profileimage()
        ll_filter?.setOnClickListener(View.OnClickListener { v: View? ->
            rankDialog = Dialog(context!!, R.style.AppThemee)
            rankDialog?.setContentView(R.layout.dlg_lrn_filter)
            rankDialog!!.setCancelable(true)
            cancel_dialog = rankDialog!!.findViewById<ImageView>(R.id.cancel_dialog)
            search_instrctr = rankDialog?.findViewById<AutoCompleteTextView>(R.id.search_instrctr)
            search_crses = rankDialog?.findViewById<AutoCompleteTextView>(R.id.search_crses)
            rb_srch_instrctr = rankDialog?.findViewById<RadioButton>(R.id.rb_srch_instrctr)
            rb_srch_crse = rankDialog?.findViewById<RadioButton>(R.id.rb_srch_crse)
            radioGroup_lrn = rankDialog?.findViewById<RadioGroup>(R.id.radioGroup_lrn)
            ll_filter_crses = rankDialog?.findViewById<LinearLayout>(R.id.ll_filter_crses)
            ll_filter_instrctr = rankDialog?.findViewById<LinearLayout>(R.id.ll_filter_instrctr)
            autoSuggestAdapter = AutoSuggestAdapter(
                context as LearnActivity,
                android.R.layout.simple_dropdown_item_1line
            )
            search_instrctr?.threshold = 1
            search_instrctr?.setAdapter(autoSuggestAdapter)
            autoSuggestAdapter = AutoSuggestAdapter(
                context as LearnActivity,
                android.R.layout.simple_dropdown_item_1line
            )
            search_crses?.threshold = 1
            search_crses?.setAdapter(autoSuggestAdapter)
            search_instrctr?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    ApiCall.instructor_srch(context as LearnActivity, s.toString(), { response ->
                        val stringList: MutableList<String> = ArrayList()
                        try {
                            val responseObject = JSONObject(response)
                            val array: JSONArray = responseObject.getJSONArray("name")
                            for (i in 0 until array.length()) {
                                val row: JSONObject = array.getJSONObject(i)
                                stringList.add(row.getString("fname"))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //IMPORTANT: set data here and notify
                        autoSuggestAdapter!!.setData(stringList)
                        autoSuggestAdapter!!.notifyDataSetChanged()
                    }) { error -> }
                }
            })
            search_crses?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    ApiCall.coursesearch(context as LearnActivity, s.toString(), { response ->
                        val stringList: MutableList<String> = ArrayList()
                        try {
                            val responseObject = JSONObject(response)
                            val array: JSONArray = responseObject.getJSONArray("title")
                            for (i in 0 until array.length()) {
                                val row: JSONObject = array.getJSONObject(i)
                                stringList.add(row.getString("title"))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //IMPORTANT: set data here and notify
                        autoSuggestAdapter!!.setData(stringList)
                        autoSuggestAdapter!!.notifyDataSetChanged()
                    }) { error -> }
                }
            })
            radioGroup_lrn?.setOnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
                when (checkedId) {
                    R.id.rb_srch_crse -> {
                        ll_filter_crses?.visibility = View.VISIBLE
                        ll_filter_instrctr?.visibility = View.GONE
                    }

                    R.id.rb_srch_instrctr -> {
                        ll_filter_crses?.visibility = View.GONE
                        ll_filter_instrctr?.visibility = View.VISIBLE
                    }
                }
            }
            cancel_dialog?.setOnClickListener({ ve: View? -> rankDialog!!.dismiss() })
            rankDialog!!.show()
        })
    }

    private fun checkvalidation() {
        search_videos_str = search_videos_lrn?.text.toString()
        if (search_videos_str == "" || search_videos_str!!.isEmpty()) {
            Toast.makeText(this@LearnActivity, "Enter something!!", Toast.LENGTH_SHORT).show()
        } else {
            search_videos_lrn?.setText("")
            val intent = Intent(this@LearnActivity, SearchCrseActivity::class.java)
            intent.putExtra("searchdata", search_videos_str)
            startActivity(intent)
        }
    }

    private fun getbizcnttedlist() {
        val url: String = Configs.BASE_URL2 + "instructor-exist"
        RetrofitClient.getClient().instrcrchnlexists(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetInstrctrPojo?>(navigationView) {
                override fun onResponse(
                    call: Call<GetInstrctrPojo?>,
                    response: Response<GetInstrctrPojo?>
                ) {
                    try {
                        if (response.body() != null) {
                            val rererer: String? = response.body()!!.status
                            if (rererer == "200") {
                                pool_id_str = response.body()!!.isExist == true
                                updateUIBasedOnPoolId(pool_id_str)
                                if (pool_id_str) {
                                    tv_action1?.visibility = View.VISIBLE
                                    tv_action?.visibility = View.GONE
                                } else {
                                    tv_action1?.visibility = View.GONE
                                    tv_action?.visibility = View.VISIBLE
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun updateUIBasedOnPoolId(pool_id_str: Boolean) {
        val intent: Intent
        if (pool_id_str) {
            intent = Intent(this@LearnActivity, CrsAddSection::class.java)
        } else {
            intent = Intent(this@LearnActivity, InstructorProfile::class.java)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        }
        startActivity(intent)
    }

    //    public static void hideKeyboardFrom(Context context, View view) {
    //        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    //        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    //    }
    private fun profileimage() {

//        progressBar.visibility = View.VISIBLE;
//        asscoId = manager?.getPreferences(MyProfile.this, "assoc_id");
//        Log.e("asscoIdStrr", asscoId);
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(navigationView) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
//                progressBar.visibility = View.GONE;
                    try {
                        if (response.body() != null) {
                            val profileItems: List<ProfileItem>? = response.body()!!.profile
                            if (profileItems != null) {
                                for (i in profileItems.indices) {
                                    val profileItem: ProfileItem = profileItems[i]
                                    val header: View? = navigationView?.getHeaderView(i)
                                    if (header != null) {
                                        nav_user_img =
                                            header.findViewById<CircleImageView>(R.id.nav_user_img)
                                        nav_user_name =
                                            header.findViewById<TextView>(R.id.nav_user_name)
                                        nav_user_email =
                                            header.findViewById<TextView>(R.id.nav_user_email)
                                        nav_user_name_str = profileItem.name
                                        nav_user_name?.text = nav_user_name_str
                                        nav_email_str = profileItem.email
                                        nav_user_email?.text = nav_email_str
                                        nav_img_path =
                                            Configs.BASE_URL21 + "images/user/" + profileItem.userImage

                                    }
                                    nav_user_img?.let {
                                        Glide.with(applicationContext).load(nav_img_path)
                                            .into(it)
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

    private fun applyFontToMenuItem(mi: MenuItem) {
        val font: Typeface = Typeface.createFromAsset(getAssets(), "fonts/ExoBold.otf")
        val mNewTitle = SpannableString(mi.title)
        mNewTitle.setSpan(
            CustomTypefaceSpan("", font),
            0,
            mNewTitle.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        mi.setTitle(mNewTitle)
    }

    //                        String learn_catsStr=response.body().toString();
//                        Log.e("learn_catsStr",learn_catsStr);
    private val lEarnCats: Unit
        get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "course-category"
            RetrofitClient.getClient().learnCats(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<LCatPojo?>(rv_learn_videos) {
                    @SuppressLint("SetTextI18n")
                    override  fun onResponse(call: Call<LCatPojo?>, response: Response<LCatPojo?>) {
                        blur_reg1?.visibility = View.GONE

//                        String learn_catsStr=response.body().toString();
//                        Log.e("learn_catsStr",learn_catsStr);
                        try {
                            val catggryList: List<DataItem>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_learn_cats!!.visibility = View.GONE
                                } else {
                                    rv_learn_cats!!.visibility = View.VISIBLE
                                    adapter_cats = LearnCatsAdapter(catggryList, this@LearnActivity)
                                    rv_learn_cats!!.adapter = adapter_cats
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
    private val lEarnImgs: Unit
        private get() {
            shimmerFrameLayout?.startShimmer()
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
                        try {
                            val catggryList: List<DatumCd>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_learn_imagess!!.visibility = View.GONE
                                } else {
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_learn_imagess!!.visibility = View.VISIBLE
                                    adapter_imgs = catggryList?.let {
                                        LearndataAdapter(
                                            it,
                                            this@LearnActivity
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

    override fun onBackPressed() {
        val selectedItemId: Int? = bottomNavigationView?.selectedItemId
        //        // If the current item is not the home item, go back to the home item
        if (selectedItemId != R.id.btm_dashbrd) {
            bottomNavigationView?.selectedItemId = R.id.btm_dashbrd
        } else {
            // Otherwise, call the default behavior (exit the app)
            super.onBackPressed()
        }

//        bottomNavigationView.selectedItemId(R.id.btm_dashbrd);
//        startActivity(new Intent(LearnActivity.this, HomeDemoActivityCtgry.class));
//        overridePendingTransition(0, 0);
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_profile_view) {
            val prof_view1 = Intent(this@LearnActivity, UpdateProfileActivity::class.java)
            startActivity(prof_view1)
        } else if (id == R.id.nav_subs) {
            val prof_view12 = Intent(this@LearnActivity, SupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_spr_sprtr) {
            val prof_view12 = Intent(this@LearnActivity, SuperSupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_rate) {
            RateThisApp.showRateDialog(this@LearnActivity)
        } else if (id == R.id.tracktask) {
            val intent = Intent(this@LearnActivity, ConnectedBizList::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_about) {
            val intent = Intent(this@LearnActivity, AboutUs::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_paid_terms) {
            val intent = Intent(this@LearnActivity, NlPaidTerms::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_cncted_bizlist) {
            val prof_view122 = Intent(this@LearnActivity, MyAllCourseActivity::class.java)
            startActivity(prof_view122)
        } else if (id == R.id.nav_terms) {
            val intent = Intent(this@LearnActivity, UserAgreement::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_referfrnd) {
            val intent = Intent(this@LearnActivity, ReferFrnd::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_logout) {
            logout()
        } else if (id == R.id.nav_share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                        "Click here to Download https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
            //            startActivity(sharingIntent);
        } else if (id == R.id.nav_invite) {

////            Uri imgUri = Uri.parse(nav_img_path);
//            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//            sharingIntent.setType("text/plain");
//            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns");
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, "I am on Nation Learns to watch cool videos, learn courses and connect with" +
//                    " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
//                    "  or visit website now, www.nationlearns.com");
////            sharingIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
////            sharingIntent.setType("image/jpeg");
////            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "I am on Nation Learns to watch cool videos, learn courses and connect with" +
                        " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                        "  or visit website now, www.nationlearns.com"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun logout() {
        val builder = AlertDialog.Builder(this@LearnActivity)
        builder.setIcon(R.drawable.logout)
            .setTitle("Log out")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog: DialogInterface?, id: Int ->
                    manager?.setPreferences(this@LearnActivity, Constants.LOGIN_STATUS, "0")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_NAME, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_EMAIL, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_NUM, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_STATE, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_CITY, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_GENDER, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_MARITAL, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_AINCOME, "")
                    manager?.setPreferences(this@LearnActivity, Constants.USER_PROFILE_PIC, "")
                    val intent = Intent(this@LearnActivity, LoginOtpActivityTimer::class.java)
                    //                    Intent intent = new Intent(EarnActivity.this, LoginSignupActivity.class);
                    startActivity(intent)
                    finish()
                })
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog: DialogInterface, id: Int -> dialog.cancel() })
        val alert = builder.create()
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.tmenu_lrn, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_crtrzzone -> {
                try {
                    getbizcnttedlist()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private var adapter_cats: RecyclerView.Adapter<*>? = null
        private var adapter_imgs: RecyclerView.Adapter<*>? = null
        private val adapter_vids: RecyclerView.Adapter<*>? = null
        private val adapter_vids_list: RecyclerView.Adapter<*>? = null
        var myOnClickListener_cats: View.OnClickListener? = null
        var myOnClickListener_imgs: View.OnClickListener? = null
        var myOnClickListener_vids: View.OnClickListener? = null
    }
}