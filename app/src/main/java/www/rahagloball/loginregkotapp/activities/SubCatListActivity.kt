package www.rahagloball.loginregkotapp.activities


import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.preference.PreferenceManager
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CaetgoryAdapter
import www.rahagloball.loginregkotapp.adapters.SubCatAdapterProfileCnct
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomTypefaceSpan
import www.rahagloball.loginregkotapp.constsnsesion.OnSwipeTouchListener
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.allconect.AllCnctFltrPojo
import www.rahagloball.loginregkotapp.models.allconect.ConnctData
import www.rahagloball.loginregkotapp.models.catgryy.CatgtyPojo
import www.rahagloball.loginregkotapp.models.catgryy.DataItem
import www.rahagloball.loginregkotapp.models.ctgry.CategiesNl
import www.rahagloball.loginregkotapp.models.ctgry.CategoryNl
import www.rahagloball.loginregkotapp.models.ctgry.SubCategiesNl
import www.rahagloball.loginregkotapp.models.ctgry.SubCategoryNl
import www.rahagloball.loginregkotapp.models.statecitymodels.Cities
import www.rahagloball.loginregkotapp.models.statecitymodels.City
import www.rahagloball.loginregkotapp.models.statecitymodels.Pincode
import www.rahagloball.loginregkotapp.models.statecitymodels.Pincodes
import www.rahagloball.loginregkotapp.models.statecitymodels.State
import www.rahagloball.loginregkotapp.models.statecitymodels.States
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.rate.RateThisApp
import www.rahagloball.loginregkotapp.tandc.AboutUs
import www.rahagloball.loginregkotapp.tandc.NlPaidTerms
import www.rahagloball.loginregkotapp.tandc.UserAgreement

class SubCatListActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private val KEY_RECYCLER_STATE: String = "recycler_state"
    private lateinit var recyclerView: RecyclerView
    private lateinit var rv_city_biz: RecyclerView
    private lateinit var rv_cbl: RecyclerView
    private lateinit var rv_cty_list: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var layoutManager_city_biz: RecyclerView.LayoutManager
    private lateinit var blur_reg1: RelativeLayout
    private lateinit var manager: SessionManager
    private lateinit var token: String
    private lateinit var adapter_nocity_cnct: SubCatAdapterProfileCnct
    private lateinit var img_not_foundd: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var appBbar: AppBarLayout
    private lateinit var navigationView: NavigationView
    private lateinit var nav_user_img: CircleImageView
    private lateinit var nav_user_pic: String
    private lateinit var nav_img_path: String
    private lateinit var nav_user_name_str: String
    private lateinit var nav_email_str: String
    private lateinit var nav_user_name: TextView
    private lateinit var nav_user_email: TextView
//    private lateinit var toolbar_titlee: TextView
//    private lateinit var connectt_sc: TextView
//    private lateinit var ll_alrdy_cnctd: LinearLayout
    private lateinit var layoutManager_cl: RecyclerView.LayoutManager
    private lateinit var ll_multisrch: LinearLayout
    private lateinit var stateiid: String
    private lateinit var cityIdd: String
    private lateinit var cat_Idd: String
    private lateinit var subcattiid: String
    private lateinit var pinIdd: String
//    private lateinit var result: JSONArray
//    private lateinit var cresult: JSONArray
//    private lateinit var pinresult: JSONArray

//    private lateinit var catresult: JSONArray
//    private lateinit var subcatresult: JSONArray
//    private lateinit var categry: ArrayList<String>
//    private lateinit var subcategry: ArrayList<String>

    //  private lateinit var state_spinner: SearchableSpinner
//  private lateinit var city_spinner: SearchableSpinner
//    private lateinit var agentype: SearchableSpinner
//    private lateinit var subcat_spinner: SearchableSpinner
//    private lateinit var pin_spinner: SearchableSpinner

//    private lateinit var clr_st_ct: LinearLayout
    private lateinit var rl_filterrr: RelativeLayout
    private lateinit var rl_updown: RelativeLayout
    private lateinit var just_explore: LinearLayout
    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences
//    private lateinit var mis_cl_num: TextView
    private lateinit var img_top: ImageView
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var dwld_nlplisting: ImageView

    var statee: List<States> = ArrayList()
    var cityy: List<Cities> = ArrayList()
    var pincdee: List<Pincodes> = ArrayList()

    var categry: List<CategiesNl> = ArrayList()
    var subcategry: List<SubCategiesNl> = ArrayList()


    val state_spinner: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.state_spinner)

    val city_spinner: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.city_spinner)

    val pin_spinner: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.pin_spinner)


    val agentype: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.agenttype_spinner)

    val subcat_spinner: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.subcat_spinner)



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_cat_list_nav)
        manager = SessionManager()
        token = manager.getPreferences(this@SubCatListActivity, Constants.USER_TOKEN_LRN)
        context = this@SubCatListActivity
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.btm_profle
        recyclerView = findViewById(R.id.rv_biz)
//        clr_st_ct = findViewById(R.id.clr_st_ct)
        rl_filterrr = findViewById(R.id.rl_filterrr)
        rl_updown = findViewById(R.id.rl_updown)
        just_explore = findViewById(R.id.just_explore)
        shimmerFrameLayout = findViewById(R.id.shimmer)
        dwld_nlplisting = findViewById(R.id.dwld_nlplisting)


        blur_reg1 = findViewById(R.id.blur_reg1)
        img_not_foundd = findViewById(R.id.img_not_foundd)
        ll_multisrch = findViewById(R.id.ll_multisrch)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        img_top = findViewById<ImageView>(R.id.img_top)
        rv_city_biz = findViewById<RecyclerView>(R.id.rv_city_biz)
        rv_city_biz.visibility = View.VISIBLE
        layoutManager_city_biz = GridLayoutManager(this, 3)
        rv_city_biz.layoutManager = layoutManager_city_biz
        rv_city_biz.itemAnimator = DefaultItemAnimator()
        rv_cbl = findViewById<RecyclerView>(R.id.rv_cbl)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_cbl.layoutManager = layoutManager
        rv_cbl.itemAnimator = DefaultItemAnimator()
        rv_cty_list = findViewById(R.id.rv_cty_list)
        layoutManager_cl = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_cty_list.layoutManager = layoutManager_cl

        navigationView = findViewById<NavigationView>(R.id.nav_view)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        val header: View? = navigationView.getHeaderView(0)
        nav_user_img = header?.findViewById<CircleImageView>(R.id.nav_user_img)!!
        nav_user_pic = manager.getPreferences(this@SubCatListActivity, Constants.USER_PROFILE_PIC)
        nav_user_name = header.findViewById<TextView>(R.id.nav_user_name)!!
        nav_user_name_str = manager.getPreferences(this@SubCatListActivity, Constants.USER_NAME)
        nav_user_name.text = nav_user_name_str
        nav_user_email = header.findViewById<TextView>(R.id.nav_user_email)!!
        nav_email_str = manager.getPreferences(this@SubCatListActivity, Constants.USER_EMAIL)
        nav_user_email.text = nav_email_str
        val m: Menu? = navigationView.menu
        if (m != null) {
            for (i in 0 until m.size()) {
                val mi: MenuItem = m.getItem(i)

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
        navigationView.setNavigationItemSelectedListener(this)
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        appBbar = findViewById<AppBarLayout>(R.id.appBbar)
        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener setOnItemSelectedListener@{ item: MenuItem ->
            val itemId: Int = item.itemId
            if (itemId == R.id.btm_dashbrd) {
                startActivity(Intent(applicationContext, HomeDemoActivityCtgry::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_cuties) {
                startActivity(Intent(this@SubCatListActivity, CutiesActvtyAltered::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_home) {
                startActivity(Intent(this@SubCatListActivity, WatchActivty::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_exprt) {
                startActivity(Intent(this@SubCatListActivity, LearnActivity::class.java))
                overridePendingTransition(0, 0)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.btm_profle) {
                //    startActivity(new Intent(SubCatListActivity.this, SubCatListActivity.class));
                //                    overridePendingTransition(0, 0);
                return@setOnItemSelectedListener true
            }
            false
        })
        profileimage()
//
//        state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                stateiid = GetSid(position)
//                getCData(stateiid)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//        city_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                cityIdd = GetCid(position)
//                getPinData(cityIdd)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//        pin_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                pinIdd = GetPinid(position)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//        agentype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                cat_Idd = Getcatid(position)
//                getSubCData(cat_Idd)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//        subcat_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                subcattiid = Getsubcatid(position)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }


        statecityData()
        getCtgryData()

        rl_updown.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeDown() {
                rl_filterrr.visibility = View.VISIBLE
            }

            override fun onSwipeLeft() {}
            override fun onSwipeUp() {
                rl_filterrr.visibility = View.GONE
            }

            override fun onSwipeRight() {}
        })
        rv_city_biz.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeDown() {
                rl_filterrr.visibility = View.VISIBLE
            }

            override fun onSwipeLeft() {}
            override fun onSwipeUp() {
                rl_filterrr.visibility = View.GONE
            }

            override fun onSwipeRight() {}
        })
        recyclerView.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeDown() {
                rl_filterrr.visibility = View.VISIBLE
            }

            override fun onSwipeLeft() {}
            override fun onSwipeUp() {
                rl_filterrr.visibility = View.GONE
            }

            override fun onSwipeRight() {}
        })

        just_explore.setOnClickListener {

            //            hideKeyboardFrom(context, view);
            img_not_foundd.visibility = View.GONE
            if (cat_Idd.isEmpty()) {
                Toast.makeText(this@SubCatListActivity, "Select Category", Toast.LENGTH_SHORT)
                    .show()
            } else if (subcattiid.isEmpty()) {
                Toast.makeText(this@SubCatListActivity, "Select SubCategory", Toast.LENGTH_SHORT)
                    .show()
            } else if (stateiid.isEmpty()) {
                Toast.makeText(this@SubCatListActivity, "Select State", Toast.LENGTH_SHORT).show()
            } else if (cityIdd.isEmpty()) {
                Toast.makeText(this@SubCatListActivity, "Select City", Toast.LENGTH_SHORT).show()
            } else {
                rv_city_biz.visibility = View.GONE
                getfilterall()
            }
        }
        catgry
        img_top.setOnClickListener {
            val tel_numberStr1 = "011–66562838"
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$tel_numberStr1")
            startActivity(callIntent)
        }
        dwld_nlplisting.setOnClickListener { v: View? ->
            val url: String = "https://play.google.com/store/apps/details?id=www.rahagloball.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private val catgry: Unit
        get() {
            shimmerFrameLayout.startShimmer()
            val url: String = Configs.BASE_URL2 + "category"
            token = manager.getPreferences(this@SubCatListActivity, Constants.USER_TOKEN_LRN)
            RetrofitClient.getClient().catrgotyy_list(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CatgtyPojo?>(recyclerView) {
                    override fun onResponse(
                        call: Call<CatgtyPojo?>,
                        response: Response<CatgtyPojo?>
                    ) {
                        try {
                            val catggryList: List<DataItem>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                } else {
                                    shimmerFrameLayout.stopShimmer()
                                    shimmerFrameLayout.visibility = View.GONE
                                    rv_city_biz.visibility = (View.VISIBLE)
                                    adapter = CaetgoryAdapter(catggryList, this@SubCatListActivity)
                                    rv_city_biz.adapter = adapter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    override fun onPause() {
        super.onPause()

        // save RecyclerView state
        mBundleRecyclerViewState = Bundle()
        val listState: Parcelable? = rv_cty_list.layoutManager!!.onSaveInstanceState()
        mBundleRecyclerViewState!!.putParcelable(KEY_RECYCLER_STATE, listState)
    }

    override fun onResume() {
        super.onResume()
        rv_cty_list.visibility = (View.GONE)
        ll_multisrch.visibility = (View.VISIBLE)
        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            val listState: Parcelable? =
                mBundleRecyclerViewState!!.getParcelable(KEY_RECYCLER_STATE)
            rv_cty_list.layoutManager!!.onRestoreInstanceState(listState)
        }
        //        adapter.notifyDataSetChanged();
    }

    private fun profileimage() {

//        progressBar.visibility =(View.VISIBLE);
//        asscoId = manager?.getPreferences(MyProfile.this, "assoc_id");
//        Log.e("asscoIdStrr", asscoId);
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(navigationView) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
//                progressBar.visibility =(View.GONE);
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        if (profileItems != null) {
                            val profileItem: ProfileItem = profileItems[0]
                            val header: View? = navigationView.getHeaderView(0)
                            if (header != null) {
                                nav_user_img =
                                    header.findViewById(R.id.nav_user_img)
                                nav_user_name = header.findViewById(R.id.nav_user_name)
                                nav_user_email = header.findViewById(R.id.nav_user_email)
                                //                    Glide.with(applicationContext).load(nav_img_path).into(nav_user_img);
                                nav_user_name_str = profileItem.name.toString()
                                nav_user_name.text = nav_user_name_str
                                nav_email_str = profileItem.email.toString()
                                nav_user_email.text = nav_email_str
                                nav_img_path =
                                    Configs.BASE_URL21 + "images/user/" + profileItem.userImage

                                nav_user_img.let {
                                    Glide.with(applicationContext).load(nav_img_path)
                                        .into(it)
                                }
//                                Glide.with(applicationContext).load(nav_img_path).into(nav_user_img)
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun applyFontToMenuItem(mi: MenuItem) {
        val font: Typeface = Typeface.createFromAsset(assets, "fonts/ExoBold.otf")
        val mNewTitle = SpannableString(mi.title)
        mNewTitle.setSpan(
            CustomTypefaceSpan("", font),
            0,
            mNewTitle.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        mi.title = mNewTitle
    }

    private fun getfilterall() {
        blur_reg1.visibility = (View.VISIBLE)
        val url: String = (Configs.BASE_URL2 + "connect-filter?category=" + cat_Idd +
                "&subcategory=" + subcattiid + "&state=" + stateiid + "&city=" + cityIdd + "&pincode=" + pinIdd)
        RetrofitClient.getClient().filteallconect(
            url, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<AllCnctFltrPojo?>(recyclerView) {
                override fun onResponse(
                    call: Call<AllCnctFltrPojo?>?,
                    response: Response<AllCnctFltrPojo?>
                ) {
                    blur_reg1.visibility = (View.GONE)
                    try {
                        if (response.body() != null) {
                            val catggryList: List<ConnctData>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    recyclerView.visibility = View.GONE
                                    img_not_foundd.visibility = (View.VISIBLE)
                                } else {
                                    recyclerView.visibility = (View.VISIBLE)
                                    img_not_foundd.visibility = (View.GONE)
                                    adapter_nocity_cnct =
                                        SubCatAdapterProfileCnct(
                                            catggryList,
                                            this@SubCatListActivity
                                        )
                                    recyclerView.adapter = adapter_nocity_cnct
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun onBackPressed() {
        val selectedItemId: Int? = bottomNavigationView.selectedItemId
        //        // If the current item is not the home item, go back to the home item
        if (selectedItemId != R.id.btm_dashbrd) {
            bottomNavigationView.selectedItemId = R.id.btm_dashbrd
        } else {
            // Otherwise, call the default behavior (exit the app)
            super.onBackPressed()
        }

//        bottomNavigationView.selectedItemId(R.id.btm_dashbrd);
//        startActivity(new Intent(SubCatListActivity.this, HomeDemoActivityCtgry.class));
//        overridePendingTransition(0, 0);
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item1: MenuItem ->
            val id: Int = item1.itemId
            if (id == R.id.nav_profile_view) {
                val prof_view1: Intent =
                    Intent(this@SubCatListActivity, UpdateProfileActivity::class.java)
                startActivity(prof_view1)
                // Handle the camera action
            } else if (id == R.id.nav_cncted_bizlist) {
                val prof_view122: Intent =
                    Intent(this@SubCatListActivity, MyAllCourseActivity::class.java)
                startActivity(prof_view122)
            } else if (id == R.id.nav_subs) {
                val prof_view12: Intent =
                    Intent(this@SubCatListActivity, SupportersList::class.java)
                startActivity(prof_view12)
            } else if (id == R.id.nav_spr_sprtr) {
                val prof_view12: Intent =
                    Intent(this@SubCatListActivity, SuperSupportersList::class.java)
                startActivity(prof_view12)
            } else if (id == R.id.nav_paid_terms) {
                val intent: Intent = Intent(this@SubCatListActivity, NlPaidTerms::class.java)
                startActivity(intent)
            } else if (id == R.id.nav_rate) {
                RateThisApp.showRateDialog(this@SubCatListActivity)
            } else if (id == R.id.tracktask) {
                val intent: Intent =
                    Intent(this@SubCatListActivity, ConnectedBizList::class.java)
                startActivity(intent)
            } else if (id == R.id.nav_about) {
                val intent: Intent = Intent(this@SubCatListActivity, AboutUs::class.java)
                startActivity(intent)
            } else if (id == R.id.nav_terms) {
                val intent: Intent = Intent(this@SubCatListActivity, UserAgreement::class.java)
                startActivity(intent)
            } else if (id == R.id.nav_referfrnd) {
                val intent: Intent = Intent(this@SubCatListActivity, ReferFrnd::class.java)
                startActivity(intent)
            } else if (id == R.id.nav_logout) {
                logout()
            } else if (id == R.id.nav_share) {
                val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                            "Click here to Download https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns"
                )
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            } else if (id == R.id.nav_invite) {
                val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    ("I am on Nation Learns to watch cool videos, learn courses and connect with" +
                            " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                            "  or visit website now, www.nationlearns.com")
                )
                startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
            val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
            val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
                this@SubCatListActivity,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawer.addDrawerListener(toggle)
            toggle.syncState()
            drawer.closeDrawer(GravityCompat.START)
            true
        })
        return false
    }

    fun logout() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@SubCatListActivity)
        builder.setIcon(R.drawable.logout)
            .setTitle("Log out")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog: DialogInterface?, id: Int ->
                    manager.setPreferences(this@SubCatListActivity, Constants.LOGIN_STATUS, "0")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_NAME, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_EMAIL, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_NUM, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_STATE, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_CITY, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_GENDER, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_MARITAL, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_AINCOME, "")
                    manager.setPreferences(this@SubCatListActivity, Constants.USER_PROFILE_PIC, "")
                    val intent: Intent =
                        Intent(this@SubCatListActivity, LoginOtpActivityTimer::class.java)
                    //                    Intent intent = new Intent(EarnActivity.this, LoginSignupActivity.class);
                    startActivity(intent)
                    finish()
                }
            )
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener({ dialog: DialogInterface, id: Int -> dialog.cancel() })
            )
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    //     blur_reg_signup.visibility = View.GONE;

    //Creating a request queue

    //Adding request to the queue
//Parsing the fetched Json String to JSON Object

    //Storing the Array of JSON String to our JSON Array

    //Calling method getStudents to get the students from the JSON Array
    //                            blur_reg_signup.visibility = View.GONE;
//        blur_reg_signup.visibility = View.VISIBLE;

    //Creating a string request
    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        getMenuInflater().inflate(R.menu.tmenu_conect, menu);
    //        return true;
    //    }
    //
    //
    //    @SuppressLint("NonConstantResourceId")
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        if (item.getItemId() == R.id.action_plstr) {
    //
    //            String url = "https://play.google.com/store/apps/details?id=www.rahagloball.com";
    //            Intent i = new Intent(Intent.ACTION_VIEW);
    //            i.setData(Uri.parse(url));
    //            startActivity(i);
    //
    //            return true;
    //        }
    //        return super.onOptionsItemSelected(item);
    //
    //
    //    }


    //state and city data
    private fun statecityData() {
        RetrofitClient.getClient()
            .getState()!!
            .enqueue(object : Callback<State?> {
                override fun onResponse(call: Call<State?>, response: Response<State?>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            statee = response.body()!!.states!!
                            val stateData = arrayOfNulls<String>(statee.size)
                            for (i in statee.indices) {
                                stateData[i] = statee[i].name
                            }
                            val arrayAdapter = ArrayAdapter<String?>(
                                this@SubCatListActivity,
                                R.layout.spinner_layout,
                                R.id.textViewSpinner,
                                stateData)
                            state_spinner.adapter = arrayAdapter
                            state_spinner.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long
                                    ) {
                                        stateiid = statee[position].id.toString()
                                        getCity(stateiid)
                                    }
                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                }
                        } else {
                            Log.d("TAG", "onResponse: " + response.code())
                        }
                    } else {
                        Log.d("TAG", "onResponse: " + response.code())
                    }
                }

                override fun onFailure(call: Call<State?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
    }

    private fun getCity(stateId: String?) {
        RetrofitClient.getClient()
            .getCity(stateId)!!
            .enqueue(object : Callback<City?> {
                override fun onResponse(call: Call<City?>, response: Response<City?>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            cityy = response.body()!!.cities
                            val cityData = arrayOfNulls<String>(cityy.size)
                            for (i in cityy.indices) {
                                cityData[i] = cityy[i].name
                            }
                            val arrayAdapter = ArrayAdapter<String?>(
                                this@SubCatListActivity,
                                R.layout.spinner_layout,
                                R.id.textViewSpinner,
                                cityData)
                            city_spinner.adapter = arrayAdapter
                            city_spinner.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long
                                    ) {

                                        cityIdd = cityy[position].id.toString()
                                        getPincode(cityIdd)
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                }
                        }
                    }
                }
                override fun onFailure(call: Call<City?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })

    }

    private fun getPincode(stateId: String?) {
        RetrofitClient.getClient()
            .getPinCode(stateId)
            ?.enqueue(object : Callback<Pincode?> {
                override fun onResponse(call: Call<Pincode?>, response: Response<Pincode?>) {
                    if (response.isSuccessful) {
                        val cityData = if (response.body() != null) {
                            pincdee = response.body()!!.pincodes!!
                            Array(pincdee.size) { i -> pincdee[i].pincode }
                        } else {
                            arrayOf("No Pincode Data Available")
                        }
                        val arrayAdapter = ArrayAdapter<String?>(
                            this@SubCatListActivity,
                            R.layout.spinner_layout,
                            R.id.textViewSpinner,
                            cityData
                        )
                        pin_spinner.adapter = arrayAdapter
                        pin_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View,
                                position: Int,
                                id: Long
                            ) {
                                if (pincdee.isNotEmpty()) {
                                    pinIdd = pincdee[position].id.toString()
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {}
                        }
                    }
                }

                override fun onFailure(call: Call<Pincode?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }


//    private fun getPincode(stateId: String?) {
//        RetrofitClient.getClient()
//            .getPinCode(stateId)!!
//            .enqueue(object : Callback<Pincode?> {
//                override fun onResponse(call: Call<Pincode?>, response: Response<Pincode?>) {
//                    if (response.isSuccessful) {
//                        if (response.body() != null) {
//                            pincdee = response.body()!!.pincodes!!
//                            val cityData = arrayOfNulls<String>(pincdee.size)
//                            for (i in pincdee.indices) {
//                                cityData[i] = pincdee[i].pincode
//                            }
//                            val arrayAdapter = ArrayAdapter<String?>(
//                                this@SubCatListActivity,
//                                R.layout.spinner_layout,
//                                R.id.textViewSpinner,
//                                cityData)
//                            pin_spinner.adapter = arrayAdapter
//                            pin_spinner.onItemSelectedListener =
//                                object : AdapterView.OnItemSelectedListener {
//                                    override fun onItemSelected(
//                                        parent: AdapterView<*>?,
//                                        view: View,
//                                        position: Int,
//                                        id: Long
//                                    ) {
//
//                                        pinIdd = pincdee[position].id.toString()
////                                        Log.d("TAG", "onResponseOnItemSelected: $cityId")
////                                        getPincode(cityId)
//                                    }
//
//                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
//                                }
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<Pincode?>, t: Throwable) {
//                    Log.d("TAG", "onFailure: ${t.message}")
//                }
//
//            })
//
//    }


    private fun getCtgryData() {
        RetrofitClient.getClient()
            .getCtgryNl()!!
            .enqueue(object : Callback<CategoryNl?> {
                override fun onResponse(call: Call<CategoryNl?>, response: Response<CategoryNl?>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            categry = response.body()!!.categories!!
                            val stateData = arrayOfNulls<String>(categry.size)
                            for (i in categry.indices) {
                                stateData[i] = categry[i].name
                            }
                            val arrayAdapter = ArrayAdapter<String?>(
                                this@SubCatListActivity,
                                R.layout.spinner_layout,
                                R.id.textViewSpinner,
                                stateData
                            )
                            agentype.adapter = arrayAdapter
                            agentype.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long
                                    ) {
                                        cat_Idd = categry[position].id.toString()
                                        getSubCtgry(cat_Idd)
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                }
                        } else {
                            Log.d("TAG", "onResponse: " + response.code())
                        }
                    } else {
                        Log.d("TAG", "onResponse: " + response.code())
                    }
                }

                override fun onFailure(call: Call<CategoryNl?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
    }

    private fun getSubCtgry(stateId: String?) {
        RetrofitClient.getClient()
            .getSubCtgryNl(stateId)!!
            .enqueue(object : Callback<SubCategoryNl?> {
                override fun onResponse(call: Call<SubCategoryNl?>, response: Response<SubCategoryNl?>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            subcategry = response.body()!!.subcategories!!
                            val cityData = arrayOfNulls<String>(subcategry.size)
                            for (i in subcategry.indices) {
                                cityData[i] = subcategry[i].name
                            }
                            val arrayAdapter = ArrayAdapter<String?>(
                                this@SubCatListActivity,
                                R.layout.spinner_layout,
                                R.id.textViewSpinner,
                                cityData
                            )
                            subcat_spinner.adapter = arrayAdapter
                            subcat_spinner.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long
                                    ) {
                                        subcattiid = subcategry[position].id.toString()
//                                        Log.d("TAG", "onResponseOnItemSelected: $cityId")
//                                        getPincode(cityId)
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                }
                        }
                    }
                }
                override fun onFailure(call: Call<SubCategoryNl?>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }

//    val statecityData: Unit
//        get() {
//
////        blur_reg_signup.visibility = View.VISIBLE;
//
//            //Creating a string request
//            val stringRequest: StringRequest = StringRequest(Configs.LOCATION_URL_BL,
//                { response ->
//                    var j: JSONObject
//                    try {
//                        //Parsing the fetched Json String to JSON Object
//                        j = JSONObject(response)
//
//                        //Storing the Array of JSON String to our JSON Array
//                        result = j.getJSONArray(Configs.JSON_SARRAY_BL)
//
//                        //Calling method getStudents to get the students from the JSON Array
//                        getState(result)
//                        //                            blur_reg_signup.visibility = View.GONE;
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            ) {
//                //                        blur_reg_signup.visibility = View.GONE;
//            }
//            stringRequest.retryPolicy = DefaultRetryPolicy(
//                Configs.MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//
//            //Creating a request queue
//            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//            //Adding request to the queue
//            requestQueue.add<String>(stringRequest)
//        }
//
//    private fun getState(j: JSONArray?) {
//        //Traversing through all the items in the json array
//        if (j != null) {
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//
//                    //Adding the name of the state to array list
//                    statee.add(json.getString(Configs.KEY_SNAME_BL))
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        //Setting adapter to show the items in the spinner
//        val spinnerArrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, statee)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
//        state_spinner?.adapter = spinnerArrayAdapter
//
////        String citysreeer = preferences.getString("statesel", "");
////        if (!citysreeer.equalsIgnoreCase("")) {
////
////            int spinnerPositioncity = spinnerArrayAdapter.getPosition(citysreeer);
////            state_spinner.setSelection(spinnerPositioncity);
////
////        }
//    }
//
//    private fun GetSid(position: Int): String {
//        var sid: String = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject? = result.getJSONObject(position)
//
//            //Fetching id from that object
//            if (json != null) {
//                sid = json.getString(Configs.KEY_SID_BL)
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }
//
//    private fun getCData(stateiid: String?) {
//
////        blur_reg_signup.visibility = View.VISIBLE;
//        val url: String = Configs.CLOCATION_URL_BL + stateiid
//        val stringRequest: StringRequest =
//            StringRequest(url, { response ->
//                var j: JSONObject
//                try {
//                    //Parsing the fetched Json String to JSON Object
//                    j = JSONObject(response)
//
//                    //Storing the Array of JSON String to our JSON Array
//                    cresult = j.getJSONArray(Configs.JSON_CARRAY_BL)
//
//                    //Calling method getStudents to get the students from the JSON Array
//                    getCity(cresult)
//                    //                    blur_reg_signup.visibility = View.GONE;
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//            ) {
//                //                        blur_reg_signup.visibility = View.GONE;
//            }
//        stringRequest.retryPolicy = DefaultRetryPolicy(
//            Configs.MY_SOCKET_TIMEOUT_MS,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        )
//
//        //Creating a request queue
//        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//        //Adding request to the queue
//        requestQueue.add<String>(stringRequest)
//    }
//
//    private fun getCity(j: JSONArray?) {
//        //Traversing through all the items in the json array
//        cityy = ArrayList()
//        if (j != null) {
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//
//                    //Adding the name of the student to array list
//                    cityy.add(json.getString(Configs.KEY_CNAME_BL))
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        //Setting adapter to show the items in the spinner
//        val spinnerArrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, cityy)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
//        city_spinner?.adapter = spinnerArrayAdapter
//
////        String citysreeer = preferences.getString("citysel", "");
////        if (!citysreeer.equalsIgnoreCase("")) {
////
////            int spinnerPositioncity = spinnerArrayAdapter.getPosition(citysreeer);
////            city_spinner.setSelection(spinnerPositioncity);
////
////        }
//
////        blur_reg_signup.visibility = View.GONE;
//    }
//
//    private fun GetCid(position: Int): String {
//        var sid: String = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject? = cresult.getJSONObject(position)
//
//            //Fetching id from that object
//            if (json != null) {
//                sid = json.getString(Configs.KEY_CID_BL)
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }

//    private fun getPinData(cityIdd: String?) {
//
////        blur_reg_signup.visibility = View.VISIBLE;
//        val url: String = Configs.PIN_URL_BL + cityIdd
//        val stringRequest: StringRequest =
//            StringRequest(url, { response ->
//                var j: JSONObject
//                try {
//                    //Parsing the fetched Json String to JSON Object
//                    j = JSONObject(response)
//
//                    //Storing the Array of JSON String to our JSON Array
//                    pinresult = j.getJSONArray(Configs.JSON_PINARRAY_BL)
//
//                    //Calling method getStudents to get the students from the JSON Array
//                    getPincode(pinresult)
//                    //                    blur_reg_signup.visibility = View.GONE;
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//            ) {
//                //                        blur_reg_signup.visibility = View.GONE;
//            }
//        stringRequest.retryPolicy = DefaultRetryPolicy(
//            Configs.MY_SOCKET_TIMEOUT_MS,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        )
//
//        //Creating a request queue
//        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//        //Adding request to the queue
//        requestQueue.add<String>(stringRequest)
//    }
//
//    private fun getPincode(j: JSONArray?) {
//        //Traversing through all the items in the json array
//        pincdee = ArrayList()
//        if (j != null) {
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//
//                    //Adding the name of the student to array list
//                    pincdee.add(json.getString(Configs.KEY_PINNAME_BL))
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        //Setting adapter to show the items in the spinner
//        val spinnerArrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, pincdee)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
//        pin_spinner?.adapter = spinnerArrayAdapter
//
////        String citysreeer = preferences.getString("citysel", "");
////        if (!citysreeer.equalsIgnoreCase("")) {
////
////            int spinnerPositioncity = spinnerArrayAdapter.getPosition(citysreeer);
////            city_spinner.setSelection(spinnerPositioncity);
////
////        }
//
////        blur_reg_signup.visibility = View.GONE;
//    }
//
//    private fun GetPinid(position: Int): String {
//        var sid: String = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject? = pinresult.getJSONObject(position)
//
//            //Fetching id from that object
//            if (json != null) {
//                sid = json.getString(Configs.KEY_PINID_BL)
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }

//    private fun getCtgryData() {
//        try {
//
////            blur_reg1.visibility = View.VISIBLE;
//            val url: String = Configs.CATGRY_URL
//            val stringRequest: StringRequest = StringRequest(url,
//                { response: String? ->
//                    var j: JSONObject
//                    try {
//                        //Parsing the fetched Json String to JSON Object
//                        j = JSONObject(response)
//
//                        //Storing the Array of JSON String to our JSON Array
//                        catresult = j.getJSONArray(Configs.JSON_CTRGYARRAY)
//
//                        //Calling method getStudents to get the students from the JSON Array
//                        getCtgry(catresult)
//
////                    blur_reg1.visibility = View.GONE;
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                },
//                { error: VolleyError? -> }
//            )
//            stringRequest.retryPolicy = DefaultRetryPolicy(
//                Configs.MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//
//            //Creating a request queue
//            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//
//            //Adding request to the queue
//            requestQueue.add<String>(stringRequest)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun Getcatid(position: Int): String {
//        var sid: String = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject? = catresult.getJSONObject(position)
//
//            //Fetching id from that object
//            if (json != null) {
//                sid = json.getString(Configs.KEY_CTRGYID)
//            }
//            manager.setPreferences(this@SubCatListActivity, Constants.USER_CATGRY, sid)
//            //            Log.e("catpreffset", sid);
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }
//
//    private fun getCtgry(j: JSONArray?) {
//        try {
//
//            //Traversing through all the items in the json array
//            categry = ArrayList()
//            if (j != null) {
//                for (i in 0 until j.length()) {
//                    try {
//                        //Getting json object
//                        val json: JSONObject = j.getJSONObject(i)
//
//                        //Adding the name of the student to array list
//                        categry.add(json.getString(Configs.KEY_CTRGYNAME))
////                        val listArray0: MutableList<KeyPairBoolData> = ArrayList<KeyPairBoolData>()
////                        for (k in categry!!.indices) {
////                            val h = KeyPairBoolData()
////                            h.setId(k + 1)
////                            h.setName(categry!![k])
////                            h.setSelected(false)
////                            listArray0.add(h)
////                        }
//                        val listArray0 = ArrayList<KeyPairBoolData>()
//                        for (k in 0 until categry.size) {
//                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
//                            categry.add(countryName)
//                            val h = KeyPairBoolData(
//                                idValue = (i + 1).toLong(),
//                                nameValue = countryName,
//                                isSelectedValue = false
//                            )
//                            listArray0.add(h)
//                        }
//
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//            val dataAdapter5: ArrayAdapter<String> =
//                ArrayAdapter<String>(
//                    this@SubCatListActivity, R.layout.custom_spiner_layout_cat,
//                    categry
//                )
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
//            agentype?.adapter = dataAdapter5
//            val spinnerPosition: Int = dataAdapter5.getPosition(cat_Idd)
//            agentype.setSelection(spinnerPosition)
//
////            String language = preferences.getString("language", "");
////            if(!language.equalsIgnoreCase(""))
////            {
////                int spinnerPosition = arrayAdapter.getPosition(language);
////                spinner.setSelection(spinnerPosition);
////
////            }
////            String cat_prefs = manager?.getPreferences(Catgryctivity.this, Constants.USER_CATGRY);
////            if (!cat_prefs.equalsIgnoreCase("")) {
//
//
////            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun getSubCData(cattiid: String?) {
////        blur_reg1.visibility = View.VISIBLE;
//        val url: String = Configs.SUBCATGRY_URL + "/" + cattiid
//        val stringRequest: StringRequest =
//            StringRequest(url, { response ->
//                var j: JSONObject
//                try {
//                    //Parsing the fetched Json String to JSON Object
//                    j = JSONObject(response)
//                    //Storing the Array of JSON String to our JSON Array
//                    subcatresult = j.getJSONArray(Configs.JSON_SUBCTRGYARRAY)
//                    //Calling method getStudents to get the students from the JSON Array
//                    getSubcatgry(subcatresult)
//                    //                    blur_reg1.visibility = View.GONE;
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//            ) {
//                //                        if (error instanceof TimeoutError) {
//                //                            Toast.makeText(SignupActivity.this,"Server Busy",Toast.LENGTH_LONG).show();
//                //                        } else if (error instanceof NoConnectionError) {
//                //                            Toast.makeText(SignupActivity.this, "No Connection", Toast.LENGTH_LONG).show();
//                //                        } else if (error instanceof AuthFailureError) {
//                //                            Toast.makeText(SignupActivity.this,"Auth Failure",Toast.LENGTH_LONG).show();
//                //                        } else if (error instanceof ServerError) {
//                //                            Toast.makeText(SignupActivity.this,"Server Error",Toast.LENGTH_LONG).show();
//                //                        } else if (error instanceof NetworkError) {
//                //                            Toast.makeText(SignupActivity.this,"Network Error",Toast.LENGTH_LONG).show();
//                //                        } else if (error instanceof ParseError) {
//                //                            Toast.makeText(SignupActivity.this,"Parse Error",Toast.LENGTH_LONG).show();
//                //                        }
//                //                        blur_reg1.visibility = View.GONE;
//            }
//        stringRequest.retryPolicy = DefaultRetryPolicy(
//            Configs.MY_SOCKET_TIMEOUT_MS,
//            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        )
//        //Creating a request queue
//        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//        //Adding request to the queue
//        requestQueue.add<String>(stringRequest)
//    }
//
//    private fun Getsubcatid(position: Int): String {
//        var sid = ""
//        try {
//            //Getting object of given index
//            val json: JSONObject? = subcatresult.getJSONObject(position)
//
//            //Fetching id from that object
//            if (json != null) {
//                sid = json.getString(Configs.KEY_SUBCTRGYID)
//            }
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        //Returning the id
//        return sid
//    }
//
//    private fun getSubcatgry(j: JSONArray?) {
//        //Traversing through all the items in the json array
//        subcategry = ArrayList()
//        if (j != null) {
//            for (i in 0 until j.length()) {
//                try {
//                    //Getting json object
//                    val json: JSONObject = j.getJSONObject(i)
//                    //Adding the name of the student to array list
//                    subcategry.add(json.getString(Configs.KEY_SUBCTRGYNAME))
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        //Setting adapter to show the items in the spinner
//        val spinnerArrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter<String>(this, R.layout.custom_spiner_layout_cat, subcategry)
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spiner_layout_cat)
//        subcat_spinner?.adapter = spinnerArrayAdapter
//
////        String citysreeer = preferences.getString("subctgrysel", "");
////        if (!citysreeer.equalsIgnoreCase("")) {
////
////            int spinnerPositioncity = spinnerArrayAdapter.getPosition(citysreeer);
////            subcat_spinner.setSelection(spinnerPositioncity);
////
////        }
//
////        int spinnerPosition = spinnerArrayAdapter.getPosition(subcattiid);
////        subcat_spinner.setSelection(spinnerPosition);
////        blur_reg1.visibility = View.GONE;
//    }


    //    savePrefsData();
    private fun restorePrefData(): Boolean {
        val pref: SharedPreferences =
            applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val isIntroActivityOpnendBefore: Boolean = pref.getBoolean("isIntroOpnend", false)
        return isIntroActivityOpnendBefore
    }

    private fun savePrefsData() {
        val pref: SharedPreferences =
            applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean("isIntroOpnend", true)
        editor.commit()
    } //                Uri imgUri = Uri.parse(nav_img_path);

    //                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    //                sharingIntent.setType("text/plain");
    //                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns");
    //                sharingIntent.putExtra(Intent.EXTRA_TEXT, "I am on Nation Learns to watch cool videos, learn courses and connect with" +
    //                        " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
    //                        "  or visit website now, www.nationlearns.com");
    ////                sharingIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
    ////                sharingIntent.setType("image/jpeg");
    ////                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    //                startActivity(Intent.createChooser(sharingIntent, "Share via"));
    //    public static void hideKeyboardFrom(Context context, View view) {
    //        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    //        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    //    }
    companion object {
        private var mBundleRecyclerViewState: Bundle? = null

        //    RecyclerView recyclerView;
        private var adapter: RecyclerView.Adapter<*>? = null
    }
}