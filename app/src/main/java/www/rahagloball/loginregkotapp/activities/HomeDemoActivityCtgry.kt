package www.rahagloball.loginregkotapp.activities

//import www.rahagloball.loginregkotapp.srchspinr.SearchableSpinner
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.SubMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.cashfree.pg.api.CFPaymentGatewayService
import com.cashfree.pg.core.api.CFSession
import com.cashfree.pg.core.api.CFTheme
import com.cashfree.pg.core.api.callback.CFCheckoutResponseCallback
import com.cashfree.pg.core.api.utils.CFErrorResponse
import com.cashfree.pg.ui.api.CFDropCheckoutPayment
import com.cashfree.pg.ui.api.CFPaymentComponent
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.apache.commons.lang3.RandomStringUtils
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.camera.GoLiveActitivy
import www.rahagloball.loginregkotapp.adapters.CatgryAdptr_Hscrol
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomTypefaceSpan
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.VideosFragment2
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.cashfrii.PaymentSessionId
import www.rahagloball.loginregkotapp.models.cashfrii.PaymnetSessionPojo
import www.rahagloball.loginregkotapp.models.catgryy.CatgtyPojo
import www.rahagloball.loginregkotapp.models.cffwalt.CfDataWalt
import www.rahagloball.loginregkotapp.models.cffwalt.WalBalncePojo
import www.rahagloball.loginregkotapp.models.ctgry.CategiesNl
import www.rahagloball.loginregkotapp.models.ctgry.CategoryNl
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.rate.RateThisApp
import www.rahagloball.loginregkotapp.tandc.AboutUs
import www.rahagloball.loginregkotapp.tandc.NlPaidTerms
import www.rahagloball.loginregkotapp.tandc.UserAgreement
import kotlin.math.roundToInt

class HomeDemoActivityCtgry : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    CFCheckoutResponseCallback {
    private lateinit var manager: SessionManager
    private lateinit var token: String
    private lateinit var rv_city_biz: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var layoutManager_city_biz: RecyclerView.LayoutManager
    private lateinit var toolbar: Toolbar
    private lateinit var layoutParams: CoordinatorLayout.LayoutParams
    private lateinit var navigation: BottomNavigationView
//    private lateinit var singleSpinnerSearch: com.toptoche.searchablespinnerlibrary.SearchableSpinner
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var appbar: AppBarLayout
    private lateinit var navigationView: NavigationView
    private lateinit var vadapter: ViewPagerAdapter
    private lateinit var navuserimg: CircleImageView
    private lateinit var FireBaseToken: String
    private lateinit var phonestr: String
    private lateinit var user_pic: String
    private lateinit var img_path: String
    private lateinit var gender: String
    private lateinit var name: String
    private lateinit var nav_user_pic: String
    private lateinit var navimgpath: String
    private lateinit var navusernamestr: String
    private lateinit var navemailstr: String
    private lateinit var quiz_result: String
    private lateinit var phonenumber: String
    private lateinit var namess: String
    private lateinit var navusername: TextView
    private lateinit var navuseremail: TextView
    private lateinit var toolbartitlee: TextView
    private lateinit var poolidstr: String
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var viewmore: LinearLayout
    private lateinit var applyfsp: CardView
    private lateinit var rankDialog: Dialog
    private lateinit var submitdialog: TextView
    private lateinit var canceldialog: TextView
    private lateinit var customamount: String
    private lateinit var stateiid: String
    private lateinit var partialpayment: EditText
    private lateinit var blurreg1: RelativeLayout
    private lateinit var walt1submit1: TextView
    private lateinit var viewtranx: TextView
    lateinit var cresult: JSONArray
    lateinit var category_list: ArrayList<String>
    private lateinit var misclnum: TextView
    private lateinit var fab: FloatingActionButton
    private lateinit var fab1: FloatingActionButton
    private lateinit var fab2: FloatingActionButton
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private var cfEnvironment: CFSession.Environment = CFSession.Environment.PRODUCTION
    var ctgryies_list: List<CategiesNl> = ArrayList()

    val ctgryies: com.toptoche.searchablespinnerlibrary.SearchableSpinner
        get() = findViewById(R.id.multipleItemSelectionSpinner)


    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        manager = SessionManager()
        token = manager.getPreferences(this@HomeDemoActivityCtgry, Constants.USER_TOKEN_LRN)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        navigationView = findViewById(R.id.nav_view)
//        singleSpinnerSearch = findViewById(R.id.multipleItemSelectionSpinner)
        val header: View? = navigationView.getHeaderView(0)
        if (header != null) {
            navuserimg = header.findViewById(R.id.nav_user_img)
            navusername = header.findViewById(R.id.nav_user_name)
            navuseremail = header.findViewById(R.id.nav_user_email)
        }
//        navuserimg.let { Glide.with(applicationContext).load(navimgpath).into(it) }
        misclnum = findViewById(R.id.mis_cl_num)
        category_list = ArrayList()
        val m: Menu = navigationView.menu
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
            applyFontToMenuItem(mi)
        }
        navigationView.setNavigationItemSelectedListener(this)
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        fab = findViewById(R.id.fab)
        fab1 = findViewById(R.id.fab1)
        fab2 = findViewById(R.id.fab2)
        navigation = findViewById(R.id.navigation)
        toolbartitlee = toolbar.findViewById(R.id.toolbar_titlee)!!
        appbar = findViewById(R.id.appBbar)
        viewmore = findViewById(R.id.view_morreee)
        walt1submit1 = findViewById(R.id.walt1_submit1)
        applyfsp = findViewById(R.id.apply_fsp_cf)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.view_pager)
        blurreg1 = findViewById(R.id.blur_reg1)
        shimmerFrameLayout = findViewById(R.id.shimmer)
        shimmerFrameLayout.startShimmer()
        vadapter = ViewPagerAdapter(supportFragmentManager)
        vadapter.addFragment(VideosFragment2(), getString(R.string.video), "", "")
        viewPager.adapter = vadapter
        //set ViewPager
        tabLayout.setupWithViewPager(viewPager)
        //        changeTabsFont();
        viewPager.offscreenPageLimit = 1
        recyclerView = findViewById<RecyclerView>(R.id.rv_catgories)
        //        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView!!.layoutManager = layoutManager
        navigation.selectedItemId = R.id.btm_dashbrd
        navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener setOnItemSelectedListener@{ item: MenuItem ->
            val itemId = item.itemId
            when (itemId) {
                R.id.btm_dashbrd -> {
                    //  startActivity(new Intent(applicationContext, HomeDemoActivityCtgry.class));
                    //                    overridePendingTransition(0, 0);
                    return@setOnItemSelectedListener true
                }
                R.id.btm_cuties -> {
                    startActivity(Intent(this@HomeDemoActivityCtgry, CutiesActvtyAltered::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.btm_home -> {
                    startActivity(Intent(this@HomeDemoActivityCtgry, WatchActivty::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.btm_exprt -> {
                    startActivity(Intent(this@HomeDemoActivityCtgry, LearnActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.btm_profle -> {
                    startActivity(Intent(this@HomeDemoActivityCtgry, SubCatListActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        })

        viewmore.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@HomeDemoActivityCtgry, SubCatListActivity::class.java)
            startActivity(intent)
        })
        applyfsp.setOnClickListener { v: View? ->
//            , R.style.FullHeightDialog
            rankDialog = Dialog(this@HomeDemoActivityCtgry, R.style.FullHeightDialog)
            rankDialog.setContentView(R.layout.nl_walletnew)
            rankDialog.setCancelable(true)
            partialpayment = rankDialog.findViewById<EditText>(R.id.partialpayment_cf)
            viewtranx = rankDialog.findViewById<TextView>(R.id.view_tranx)
            submitdialog = rankDialog.findViewById<TextView>(R.id.submit_dialog_cf)
            canceldialog = rankDialog.findViewById<TextView>(R.id.cancel_dialog_cf)
            viewtranx.setOnClickListener(View.OnClickListener { v23: View? ->
                val viwetranxintent = Intent(this@HomeDemoActivityCtgry, Forum2::class.java)
                startActivity(viwetranxintent)
            })
            partialpayment.setOnTouchListener { view: View, event: MotionEvent ->
                // TODO Auto-generated method stub
                if (view.id == R.id.partialpayment_cf) {
                    view.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(
                            false
                        )
                    }
                }
                false
            }
            submitdialog.setOnClickListener {
//                hideKeyboardFrom(HomeDemoActivityCtgry.this, view);
                customamount = partialpayment.text.toString()
                try {
                    customamount = partialpayment.text.toString()
                    val num = customamount.toFloat()
                    if (customamount.isEmpty() || num < 1) {
                        Toast.makeText(this, "Kindly Enter a valid Amount!  ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        gettkncfpay(customamount)
                        rankDialog.dismiss()
                    }
                } catch (ex: NumberFormatException) { // handle your exception
                }
            }
            canceldialog.setOnClickListener { vv: View? -> rankDialog.dismiss() }
            rankDialog.show()
        }
        profileimage()
//        ctgryies.setTitle("Select Category");
        getCatgry()
        getCtgryData()



        fab.setOnClickListener { getbizcnttedlist() }
        misclnum.setOnClickListener {
            val tel_numberStr1 = "011–66562838"
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$tel_numberStr1")
            startActivity(callIntent)
        }
        cashWallet

//        singleSpinnerSearch.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                stateiid = GetSid(position)
//                getfsp(stateiid)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }

    }


    private val cashWallet: Unit
        get() {
            val url: String = Configs.BASE_URL2 + "get-balance"
            RetrofitClient.getClient().getwaltinfoo(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<WalBalncePojo?>(viewmore) {
                    override fun onResponse(
                        call: Call<WalBalncePojo?>,
                        response: Response<WalBalncePojo?>
                    ) {
                        try {
                            val cf_walt_sts: String? = response.body()?.staus
                            if (cf_walt_sts?.contains("0") == true) {
//                                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                            } else if (cf_walt_sts?.contains("1") == true) {
                                val cfDataWalt: CfDataWalt? = response.body()!!.data
                                val walt_str: String? = cfDataWalt?.balance
                                walt1submit1.text = walt_str
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    fun postcftodb(
        orderIdd: String?,
        orderAmountt: String?,
        orderCurrencyy: String?,
        transactionModee: String?,
        staturs: String?,
        request_typer: String?,
        transaction_idd: String?,
        paymentMode: String?
    ) {
        blurreg1.visibility = View.VISIBLE
        //        String orderidd = RandomStringUtils.randomAlphanumeric(8);
        RetrofitClient.getClient().pay_success_cf(
            orderIdd, orderAmountt, orderCurrencyy, transactionModee,
            staturs, request_typer, transaction_idd, paymentMode,
            "application/json", "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(partialpayment) {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                blurreg1.visibility = View.GONE
                try {
                    val cf_res = response.body()?.toString()

//                    val cf_res = response.body()?.toString()
                    if (response.isSuccessful) {
                        rankDialog = Dialog(this@HomeDemoActivityCtgry, R.style.FullHeightDialog)
                        rankDialog.setContentView(R.layout.walet_sucs_tick)
                        rankDialog.setCancelable(true)
                        rankDialog.show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    fun gettkncfpay(cfamountpay: String?) {
//        customDialogl.show();
        val result = cfamountpay!!.toDouble()
        //        Log.e("resultpackage", String.valueOf(result));
        val roundOff = (result * 100).roundToInt().toDouble() / 100
        val orderidd: String = RandomStringUtils.randomAlphanumeric(10)
        val customer1234: String = RandomStringUtils.randomAlphanumeric(10)
        RetrofitClient.getClient().gettokencf_new(
            orderidd, roundOff, "INR", "Payment Done",
            customer1234, navusernamestr, navemailstr, phonestr, "live",
            "application/json", "Bearer $token"
        )?.enqueue(object : GlobalCallback<PaymnetSessionPojo?>(misclnum) {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<PaymnetSessionPojo?>,
                response: Response<PaymnetSessionPojo?>
            ) {
//                        customDialogl.dismiss();
                try {
                    if (response.isSuccessful) {
                        val paymentSessionIdpj: PaymentSessionId? =
                            response.body()?.paymentSessionId
                        val paymentSessionID: String? = paymentSessionIdpj?.paymentSessionId
                        //                                    Log.e("paymentSessionID", paymentSessionID);
                        val cfSession: CFSession? = paymentSessionID?.let {
                            CFSession.CFSessionBuilder()
                                .setEnvironment(cfEnvironment)
                                .setPaymentSessionID(it)
                                .setOrderId(orderidd)
                                .build()
                        }
                        val cfPaymentComponent: CFPaymentComponent =
                            CFPaymentComponent.CFPaymentComponentBuilder() // Shows only Card and UPI modes
                                //                                        .add(CFPaymentComponent.CFPaymentModes.CARD)
                                //                                        .add(CFPaymentComponent.CFPaymentModes.UPI)
                                .build()
                        // Replace with your application's theme colors
                        val cfTheme: CFTheme = CFTheme.CFThemeBuilder()
                            .setNavigationBarBackgroundColor("#fc2678")
                            .setNavigationBarTextColor("#ffffff")
                            .setButtonBackgroundColor("#fc2678")
                            .setButtonTextColor("#ffffff")
                            .setPrimaryTextColor("#000000")
                            .setSecondaryTextColor("#000000")
                            .build()
                        val cfDropCheckoutPayment: CFDropCheckoutPayment? =
                            cfSession?.let {
                                CFDropCheckoutPayment.CFDropCheckoutPaymentBuilder()
                                    .setSession(it)
                                    .setCFUIPaymentModes(cfPaymentComponent)
                                    .setCFNativeCheckoutUITheme(cfTheme)
                                    .build()
                            }
                        val gatewayService: CFPaymentGatewayService =
                            CFPaymentGatewayService.getInstance()
                        if (cfDropCheckoutPayment != null) {
                            gatewayService.doPayment(
                                this@HomeDemoActivityCtgry,
                                cfDropCheckoutPayment
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun getbizcnttedlist() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(navigationView) {
                override fun onResponse(
                    call: Call<GetChanlPojo?>,
                    response: Response<GetChanlPojo?>
                ) {
                    try {
                        val catggryList: List<DataItem>? = response.body()?.data
                        for (i in catggryList?.indices!!) {
                            val dataItem = catggryList[i]
                            poolidstr = dataItem.poolId.toString()
                        }
                        updateUIBasedOnPoolId()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun updateUIBasedOnPoolId() {
        if (poolidstr.isEmpty()) {
            // Show a message indicating that the user needs to create a channel first
            Toast.makeText(this, "Create a Channel first!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@HomeDemoActivityCtgry, TCreatChanelActvty::class.java)
            startActivity(intent)
        } else {
            // Start the appropriate activity based on poolidstr
            val intent = Intent(this@HomeDemoActivityCtgry, TUploadVid::class.java)
            startActivity(intent)
        }
    }

    protected override fun onResume() {
        super.onResume()
    }

    private fun profileimage() {
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(navigationView) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        for (i in profileItems?.indices!!) {
                            val profileItem: ProfileItem = profileItems[i]
                            val header: View? = navigationView.getHeaderView(i)
                            if (header != null) {
                                navuserimg = header.findViewById(R.id.nav_user_img)
                                navusername = header.findViewById(R.id.nav_user_name)
                                navuseremail = header.findViewById(R.id.nav_user_email)
                            }
                            phonestr = profileItem.mobile.toString()
                            navusernamestr = profileItem.name.toString()
                            navemailstr = profileItem.email.toString()
                            navusername.text = navusernamestr
                            navuseremail.text = navemailstr
                            navimgpath = Configs.BASE_URL21 + "images/user/" + profileItem.userImage
                            navuserimg.let {
                                Glide.with(applicationContext).load(navimgpath)
                                    .into(it)
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getCatgry() {
        shimmerFrameLayout.startShimmer()
        val url: String = Configs.BASE_URL2 + "category"
        RetrofitClient.getClient().catrgotyy_list(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<CatgtyPojo?>(navigationView) {
                override fun onResponse(call: Call<CatgtyPojo?>, response: Response<CatgtyPojo?>) {

//                        blurreg1.visibility = View.GONE;
                    shimmerFrameLayout.stopShimmer()
                    try {
                        if (response.body() != null) {
                            val catggryList: List<www.rahagloball.loginregkotapp.models.catgryy.DataItem>? =
                                response.body()?.data
                            if (catggryList?.isEmpty() == true) {
//                                emptyElement.visibility = View.VISIBLE;
                            } else {
                                shimmerFrameLayout.stopShimmer()
                                shimmerFrameLayout.visibility = View.GONE
                                recyclerView!!.visibility = View.VISIBLE
                                //                                emptyElement.visibility = View.GONE;
                                adapter =
                                    catggryList?.let {
                                        CatgryAdptr_Hscrol(
                                            it,
                                            this@HomeDemoActivityCtgry
                                        )
                                    }
                                recyclerView!!.adapter = adapter
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_profile_view) {
            val prof_view1 = Intent(this@HomeDemoActivityCtgry, UpdateProfileActivity::class.java)
            startActivity(prof_view1)
        } else if (id == R.id.nav_subs) {
            val prof_view12 = Intent(this@HomeDemoActivityCtgry, SupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_spr_sprtr) {
            val prof_view12 = Intent(this@HomeDemoActivityCtgry, SuperSupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_cncted_bizlist) {
            val prof_view122 = Intent(this@HomeDemoActivityCtgry, MyAllCourseActivity::class.java)
            startActivity(prof_view122)
        } else if (id == R.id.nav_mang_vids) {
            val prof_view122 = Intent(this@HomeDemoActivityCtgry, MangeVidActivity::class.java)
            startActivity(prof_view122)
        } else if (id == R.id.nav_rate) {
            RateThisApp.showRateDialog(this@HomeDemoActivityCtgry)
        } else if (id == R.id.tracktask) {
            val intent = Intent(this@HomeDemoActivityCtgry, ConnectedBizList::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_about) {
            val intent = Intent(this@HomeDemoActivityCtgry, AboutUs::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_paid_terms) {
            val intent = Intent(this@HomeDemoActivityCtgry, NlPaidTerms::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_terms) {
            val intent = Intent(this@HomeDemoActivityCtgry, UserAgreement::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_referfrnd) {
            val intent = Intent(this@HomeDemoActivityCtgry, ReferFrnd::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_logout) {
            logout()
        } else if (id == R.id.nav_share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                        "Click here to Download https://play.google.com/store/apps/details?id=www.rahagloball.loginregkotapp"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        } else if (id == R.id.nav_invite) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                        "Click here to Download https://play.google.com/store/apps/details?id=www.rahagloball.loginregkotapp"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

//    private fun getCtgryData() {
//        try {
//            val url: String = Configs.CATGRY_URL
//            val stringRequest = StringRequest(url,
//                { response: String? ->
//                    var j: JSONObject? = null
//                    try {
//                        j = response?.let { JSONObject(it) }
//                        cresult = j?.getJSONArray(Configs.JSON_CTRGYARRAY)!!
//
//                        getCtgry(cresult)
//
//                        blurreg1.visibility = View.GONE
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                },
//                { error: VolleyError? -> })
//            stringRequest.retryPolicy = DefaultRetryPolicy(
//                Configs.MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//            )
//
//            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//            requestQueue.add<String>(stringRequest)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun GetSid(position: Int): String {
//        var sid = ""
//        try {
//            val json: JSONObject? = cresult.getJSONObject(position)
//            sid = json?.getString(Configs.KEY_CTRGYID)!!
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return sid
//    }
//
//    private fun getCtgry(j: JSONArray?) {
//        try {
//            category_list = ArrayList()
//            if (j != null) {
//                for (i in 0 until j.length()) {
//                    try {
//                        val json: JSONObject = j.getJSONObject(i)
//                        val countryName = json.getString(Configs.KEY_CTRGYNAME)
//                        category_list.add(countryName)
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(this@HomeDemoActivityCtgry, R.layout.custom_spiner_layout1, category_list)
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
//            singleSpinnerSearch.adapter = dataAdapter5
//
//            singleSpinnerSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    stateiid = GetSid(position)
//                    getfsp(stateiid)
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


    private fun getCtgryData() {
        RetrofitClient.getClient()
            .getCtgryNl()!!
            .enqueue(object : Callback<CategoryNl?> {
                override fun onResponse(call: Call<CategoryNl?>, response: Response<CategoryNl?>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            ctgryies_list = response.body()!!.categories!!
                            val stateData = arrayOfNulls<String>(ctgryies_list.size)

                            for (i in ctgryies_list.indices) {
                                stateData[i] = ctgryies_list[i].name
                            }
                            val arrayAdapter = ArrayAdapter<String?>(
                                this@HomeDemoActivityCtgry,
                                R.layout.spinner_layout,
                                R.id.textViewSpinner,
                                stateData
                            )
                            ctgryies.adapter = arrayAdapter

                            var isUserInteraction = false
                            ctgryies.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View,
                                        position: Int,
                                        id: Long) {
                                        if (isUserInteraction) {
                                            stateiid = ctgryies_list[position].id.toString()
                                            getfsp(stateiid)
                                        } else {
                                            isUserInteraction = true
                                        }
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

    private fun getfsp(cst_dd: String?) {
        val intent = Intent(this@HomeDemoActivityCtgry, SubCatActivity::class.java)
        intent.putExtra("Qid", cst_dd)
        startActivity(intent)
    }

//    private fun getCtgryData() {
//        try {
//            val url: String = Configs.CATGRY_URL
//            val stringRequest = StringRequest(url,
//                { response: String? ->
//                    var j: JSONObject? = null
//                    try {
//                        j = response?.let { JSONObject(it) }
//                            cresult = j?.getJSONArray(Configs.JSON_CTRGYARRAY)!!
//                        getCtgry(cresult)
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                },
//                { error: VolleyError? -> })
//            stringRequest.retryPolicy = DefaultRetryPolicy(
//                Configs.MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//            requestQueue.add<String>(stringRequest)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun GetSid(position: Int): String {
//        var sid = ""
//        try {
//            val json: JSONObject? = cresult.getJSONObject(position)
//                sid = json?.getString(Configs.KEY_CTRGYID)!!
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return sid
//    }
//
//    private fun getCtgry(j: JSONArray?) {
//        try {
//            category_list = ArrayList()
//            if (j != null) {
//                for (i in 0 until j.length()) {
//                    try {
//                        val json: JSONObject = j.getJSONObject(i)
//                       val listArray0 = ArrayList<KeyPairBoolData>()
//                        for (k in 0 until category_list.size) {
//                            val countryName = json.getString(Configs.KEY_CTRGYNAME)
//                            category_list.add(countryName)
//                            val h = KeyPairBoolData(
//                                idValue = (k + 1).toLong(),
//                                nameValue = countryName,
//                                isSelectedValue = false
//                            )
//                            listArray0.add(h)
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//            val dataAdapter5: ArrayAdapter<String> = ArrayAdapter<String>(
//                this@HomeDemoActivityCtgry,
//                R.layout.custom_spiner_layout1,
//                category_list
//            )
//            dataAdapter5.setDropDownViewResource(R.layout.custom_spiner_layout)
//            singleSpinnerSearch.adapter = dataAdapter5
//            singleSpinnerSearch.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    stateiid = GetSid(position)
//                    getfsp(stateiid)
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tmenu_home, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_golive -> {
                try {
                    val intent = Intent(this, GoLiveActitivy::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val builder = AlertDialog.Builder(this@HomeDemoActivityCtgry)
        builder.setIcon(R.drawable.logout)
            .setTitle("Log out")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { dialog: DialogInterface?, id: Int ->
                manager.setPreferences(
                    this@HomeDemoActivityCtgry,
                    Constants.LOGIN_STATUS,
                    "0"
                )
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_NAME, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_EMAIL, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_NUM, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_STATE, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_CITY, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_GENDER, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_MARITAL, "")
                manager.setPreferences(this@HomeDemoActivityCtgry, Constants.USER_AINCOME, "")
                manager.setPreferences(
                    this@HomeDemoActivityCtgry,
                    Constants.USER_PROFILE_PIC,
                    ""
                )
                val intent =
                    Intent(this@HomeDemoActivityCtgry, LoginOtpActivityTimer::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton(
                "No"
            ) { dialog: DialogInterface, id: Int -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun onBackPressed() {
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (navigation.selectedItemId == R.id.btm_dashbrd) {
            super.onBackPressed()
            finishAffinity()
        } else if (supportFragmentManager.backStackEntryCount > -1) supportFragmentManager.popBackStack() else {
            finishAffinity()
        }
        super.onBackPressed()
        navigation.selectedItemId = R.id.btm_dashbrd
        navigation.visibility = View.VISIBLE
    }

    override fun onPaymentVerify(orderID: String) {
//        Log.e("orderIDNew", orderID);
//        Log.e("onPaymentVerify", "verifyPayment triggered");

//        postcftodb(orderID, orderAmountcf, "INR", transactionModeecf, staturscf, "add-money", transaction_iddcf, paymentModecf);
    }

    override fun onPaymentFailure(cfErrorResponse: CFErrorResponse, orderID: String) {
        Log.e("onPaymentFailure $orderID", cfErrorResponse.message)
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        private var recyclerView: RecyclerView? = null
    }
}


//    public void gettkncfpay(String cfamountpay) {
//
//        blurreg1.visibility = View.VISIBLE;
//        String orderidd = RandomStringUtils.randomAlphanumeric(10);
//        Log.e("orderidd",orderidd);
//        RetrofitClient.getClient().gettokencf(orderidd, cfamountpay, "INR",
//                        "application/json", "Bearer " + token).
//                enqueue(new GlobalCallback<CashFripojo>(partialpayment) {
//                    @SuppressLint("SuspiciousIndentation")
//                    @Override
//                    public void onResponse(Call<CashFripojo> call, Response<CashFripojo> response) {
//                        blurreg1.visibility = View.GONE;
//
//                        try {
//
//                            if (response.isSuccessful()) {
//                                if (response.body().getMessage().equals("Token generated")) {
//                                    String token ? = response.body()?.getCftoken();
//                                    String mshhhsssage ? = response.body()?.getMessage();
////                                    Log.e("mshhhsssage", mshhhsssage);
//                                    Map<String, String> params = new HashMap<>();
//                                    params.put(PARAM_APP_ID, "287591d80ab19b3f8bae5cb1ab195782");
//                                    params.put(PARAM_ORDER_ID, orderidd);
//                                    params.put(PARAM_ORDER_AMOUNT, cfamountpay);
//                                    params.put(PARAM_ORDER_NOTE, "NL Payment done");
//
//                                    if (navusernamestr != null)
//                                        params.put(PARAM_CUSTOMER_NAME, navusernamestr);
//
//                                    if (phonestr != null)
//                                        params.put(PARAM_CUSTOMER_PHONE, phonestr);
//
//                                    if (navemailstr != null)
//                                        params.put(PARAM_CUSTOMER_EMAIL, navemailstr);
//
//                                    params.put(PARAM_ORDER_CURRENCY, "INR");
//
////                                    for (Map.Entry entry : params.entrySet()) {
////                                        Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
////                                    }
//
//                                    CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
//                                    cfPaymentService.setOrientation(0);
//                                    cfPaymentService.doPayment(HomeDemoActivityCtgry.this, params, token, "PROD", "#174778", "#FFFFFF", false);
//                                } else {
//                                    Toast.makeText(HomeDemoActivityCtgry.this, "msg2" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
////                                Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(HomeDemoActivityCtgry.this, "msg3" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                });
//    }


//                                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            Bundle bundle = data.getExtras();
//            if (bundle != null) {
//                String odercidcf = bundle.getString("orderId");
//                String orderAmountcf = bundle.getString("orderAmount");
//                String orderCurrencyycf = bundle.getString("INR");
//                String transactionModeecf = bundle.getString("cashfree");
//                String staturscf = bundle.getString("txStatus");
////                String request_typercf=bundle.getString("add-money");
//                String transaction_iddcf = bundle.getString("referenceId");
//                String paymentModecf = bundle.getString("paymentMode");
//                String orderidd = RandomStringUtils.randomAlphanumeric(10);
//                postcftodb(orderidd, orderAmountcf, "INR", transactionModeecf, staturscf, "add-money", transaction_iddcf, paymentModecf);
//            }
//        }
//    }
