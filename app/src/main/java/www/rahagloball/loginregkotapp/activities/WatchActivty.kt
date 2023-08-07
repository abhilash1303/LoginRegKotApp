package www.rahagloball.loginregkotapp.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.SubMenu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomTypefaceSpan
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient

import www.rahagloball.loginregkotapp.fragments.mychdtlsf.VideosFragmentWatch
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.activities.camera.GoLiveActitivy
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.rate.RateThisApp
import www.rahagloball.loginregkotapp.tandc.AboutUs
import www.rahagloball.loginregkotapp.tandc.NlPaidTerms
import www.rahagloball.loginregkotapp.tandc.UserAgreement


class WatchActivty constructor() : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {
    var toolbar: Toolbar? = null
    var fragment: Fragment? = null
    var navigation: BottomNavigationView? = null
    var nav_user_img: CircleImageView? = null
    var token: String? = null
    var nav_user_pic: String? = null
    var nav_img_path: String? = null
    var nav_user_name_str: String? = null
    var nav_email_str: String? = null
    var nav_user_name: TextView? = null
    var nav_user_email: TextView? = null
    var toolbar_titlee: TextView? = null
    var manager: SessionManager? = null
    var layoutParams: CoordinatorLayout.LayoutParams? = null
    var blur_reg1: RelativeLayout? = null
    var collapsingToolbar: CollapsingToolbarLayout? = null
    var appBbar: AppBarLayout? = null
    var navigationView: NavigationView? = null
    var linearLayout: LinearLayout? = null
    var emptyElement: ImageView? = null
    var poole_iddnulcheck: TextView? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var context: Context? = null
    var vadapter: ViewPagerAdapter? = null
    var pool_id_str: String? = null
    var chanel_id_str: String? = null
    var pool_id_str_bz: String? = null
    var chanel_id_str_bz: String? = null
    var fab: FloatingActionButton? = null
    var fab1: FloatingActionButton? = null
    var fab2: FloatingActionButton? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        context = this@WatchActivty
        manager = SessionManager()
        token = manager?.getPreferences(this@WatchActivty, Constants.USER_TOKEN_LRN)
        poole_iddnulcheck = findViewById<TextView>(R.id.poole_iddnulcheck)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        emptyElement = findViewById<ImageView>(R.id.emptyElement)
        linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        navigationView = findViewById<NavigationView>(R.id.nav_view)

        val header: View? = navigationView?.getHeaderView(0)
        if (header != null) {
            nav_user_img = header.findViewById<CircleImageView>(R.id.nav_user_img)
            nav_user_name = header.findViewById<TextView>(R.id.nav_user_name)
            nav_user_email = header.findViewById<TextView>(R.id.nav_user_email)
        }

//        val header: View = navigationView.getHeaderView(0)
//        nav_user_img = header.findViewById<CircleImageView>(R.id.nav_user_img)
//        Glide.with(applicationContext).load(nav_img_path).into(nav_user_img)

        context?.let { nav_user_img?.let { it1 ->
            Glide.with(it).load(nav_img_path).into(
                it1
            )
        }

        }

//        nav_user_name = header.findViewById<TextView>(R.id.nav_user_name)
//        nav_user_email = header.findViewById<TextView>(R.id.nav_user_email)
        fab = findViewById<FloatingActionButton>(R.id.fab)
        val m: Menu? = navigationView?.menu
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
        navigationView?.setNavigationItemSelectedListener(this)
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigation = findViewById<BottomNavigationView>(R.id.navigation)
        toolbar_titlee = findViewById<TextView>(R.id.toolbar_titlee)
        appBbar = findViewById<AppBarLayout>(R.id.appBbar)
        collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        vadapter = ViewPagerAdapter(supportFragmentManager)
        vadapter?.addFragment(VideosFragmentWatch(), getString(R.string.video), "", "")
        viewPager?.setAdapter(vadapter)
        //set ViewPager
        tabLayout?.setupWithViewPager(viewPager)
        //        changeTabsFont();
        viewPager?.setOffscreenPageLimit(1)

//        navigationView?.setNavigationItemSelectedListener(this)

        navigation?.selectedItemId = R.id.btm_home

//        navigation?.selectedItemId(R.id.btm_home)
        navigation?.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener setOnItemSelectedListener@{ item: MenuItem ->
            when (item.getItemId()) {
                R.id.btm_dashbrd -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            HomeDemoActivityCtgry::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }

                R.id.btm_cuties -> {
                    startActivity(Intent(this@WatchActivty, CutiesActvtyAltered::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }

                R.id.btm_home -> //                    startActivity(new Intent(WatchActivty.this, WatchActivty.class));
                    //                    overridePendingTransition(0, 0);
                    return@setOnItemSelectedListener true

                R.id.btm_exprt -> {
                    startActivity(Intent(this@WatchActivty, LearnActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }

                R.id.btm_profle -> {
                    startActivity(Intent(this@WatchActivty, SubCatListActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            false
        })
        layoutParams = navigation?.layoutParams as CoordinatorLayout.LayoutParams?
        toolbar_titlee?.setText(R.string.b_home)
        checkAndRequestPermissions()
        val type: Typeface = Typeface.createFromAsset(assets, "fonts/ExoBold.otf")
        nav_user_name?.setTypeface(type)
        nav_user_email?.setTypeface(type)
        collapsingToolbar?.setCollapsedTitleTypeface(type)
        collapsingToolbar?.setCollapsedTitleTypeface(type)
        collapsingToolbar?.setExpandedTitleTypeface(type)
        initViews()
        profileimage()
        fab?.setOnClickListener(View.OnClickListener { view: View? -> getbizcnttedlist() })
    }

    private fun getbizcnttedlist() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(poole_iddnulcheck) {
                override fun onResponse(
                    call: Call<GetChanlPojo?>,
                    response: Response<GetChanlPojo?>
                ) {
                    try {
                        val catggryList: List<DataItem>? = response.body()?.data
                        if (catggryList != null) {
                            for (i in catggryList.indices) {
                                val dataItem: DataItem = catggryList[i]
                                chanel_id_str = dataItem.id
                                pool_id_str = dataItem.poolId
                                //                                Log.e("pool_id_str", pool_id_str);
                                //                                poole_iddnulcheck.setText(pool_id_str);
                            }
                        }
                        updateUIBasedOnPoolId()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun updateUIBasedOnPoolId() {
        if (pool_id_str == null || pool_id_str!!.isEmpty()) {
            // Show a message indicating that the user needs to create a channel first
            Toast.makeText(this, "Create a Channel first!!", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this@WatchActivty, TCreatChanelActvty::class.java)
            startActivity(intent)
        } else {
            // Start the appropriate activity based on pool_id_str
            val intent: Intent = Intent(this@WatchActivty, TUploadVid::class.java)
            startActivity(intent)
        }
    }

    private fun getbizcnttedlistcz() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(poole_iddnulcheck) {
                override fun onResponse(
                    call: Call<GetChanlPojo?>,
                    response: Response<GetChanlPojo?>
                ) {
                    try {
                        val catggryList: List<DataItem>? = response.body()?.data
                        if (catggryList != null) {
                            for (i in catggryList.indices) {
                                val dataItem: DataItem = catggryList[i]
                                chanel_id_str_bz = dataItem.id
                                pool_id_str_bz = dataItem.poolId
                                //                                poole_iddnulcheck.setText(pool_id_str);
                            }
                        }
                        updateUIBasedOnPoolIdcz()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun updateUIBasedOnPoolIdcz() {
        if (pool_id_str_bz == null || pool_id_str_bz!!.isEmpty()) {
            // Show a message indicating that the user needs to create a channel first
            Toast.makeText(this, "Create a Channel first!!", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this@WatchActivty, TCreatChanelActvty::class.java)
            startActivity(intent)
        } else {
            // Start the appropriate activity based on pool_id_str
            val intent: Intent = Intent(this@WatchActivty, CrtrZoneActivity::class.java)
            intent.putExtra("ch_id_prf", chanel_id_str_bz)
            startActivity(intent)
        }
    }

    private fun profileimage() {
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer " + token)
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(nav_user_email) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        if (profileItems != null) {
                            val profileItem: ProfileItem = profileItems[0]
                            val header: View? = navigationView?.getHeaderView(0)
                            if (header != null) {
                                nav_user_img =
                                    header.findViewById<CircleImageView>(R.id.nav_user_img)
                                nav_user_name = header.findViewById<TextView>(R.id.nav_user_name)
                                nav_user_email = header.findViewById<TextView>(R.id.nav_user_email)
                                nav_user_name_str = profileItem.name
                                nav_user_name?.text = nav_user_name_str
                                nav_email_str = profileItem.email
                                nav_user_email?.text = nav_email_str
                                nav_img_path =
                                    Configs.BASE_URL21 + "images/user/" + profileItem.userImage

                                context?.let { nav_user_img?.let { it1 ->
                                    Glide.with(it).load(nav_img_path).into(
                                        it1
                                    )
                                }

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

    private enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    private fun initViews() {
        val TAG: String = "AppBarTest"
        appBbar?.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            private var state: State? = null

            @SuppressLint("SetTextI18n")
            public override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (verticalOffset == 0) {
                    if (state != State.EXPANDED) {
                        navigationView?.setNavigationItemSelectedListener(
                            NavigationView.OnNavigationItemSelectedListener { item: MenuItem ->

                                //                            nav_supr_spprt,nav_past_srch,nav_saved_vids,nav_srch_ctgry,nav_watch_wishlist
                                val id: Int = item.getItemId()
                                if (id == R.id.nav_profile_view) {
                                    val prof_view1: Intent =
                                        Intent(
                                            this@WatchActivty,
                                            UpdateProfileActivity::class.java
                                        )
                                    startActivity(prof_view1)
                                    // Handle the camera action
                                } else if (id == R.id.nav_cncted_bizlist) {
                                    val prof_view122: Intent =
                                        Intent(
                                            this@WatchActivty,
                                            MyAllCourseActivity::class.java
                                        )
                                    startActivity(prof_view122)
                                } else if (id == R.id.nav_subs) {
                                    val prof_view12: Intent =
                                        Intent(this@WatchActivty, SupportersList::class.java)
                                    startActivity(prof_view12)
                                } else if (id == R.id.nav_rate) {
                                    RateThisApp.showRateDialog(this@WatchActivty)
                                } else if (id == R.id.tracktask) {
                                    val intent: Intent =
                                        Intent(this@WatchActivty, ConnectedBizList::class.java)
                                    startActivity(intent)
                                } else if (id == R.id.nav_paid_terms) {
                                    val intent: Intent =
                                        Intent(this@WatchActivty, NlPaidTerms::class.java)
                                    startActivity(intent)
                                } else if (id == R.id.nav_about) {
                                    val intent: Intent =
                                        Intent(this@WatchActivty, AboutUs::class.java)
                                    startActivity(intent)
                                } else if (id == R.id.nav_terms) {
                                    val intent: Intent =
                                        Intent(this@WatchActivty, UserAgreement::class.java)
                                    startActivity(intent)
                                } else if (id == R.id.nav_referfrnd) {
                                    val intent: Intent =
                                        Intent(this@WatchActivty, ReferFrnd::class.java)
                                    startActivity(intent)
                                } else if (id == R.id.nav_logout) {
                                    logout()
                                } else if (id == R.id.nav_share) {
                                    val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
                                    sharingIntent.setType("text/plain")
                                    sharingIntent.putExtra(
                                        Intent.EXTRA_SUBJECT,
                                        "Nation Learns"
                                    )
                                    startActivity(
                                        Intent.createChooser(
                                            sharingIntent,
                                            "Share via"
                                        )
                                    )
                                    sharingIntent.putExtra(
                                        Intent.EXTRA_TEXT,
                                        "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                                                "Click here to Download https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns"
                                    )
                                } else if (id == R.id.nav_invite) {

                                    //                                Uri imgUri = Uri.parse(nav_img_path);
                                    //                                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                    //                                sharingIntent.setType("text/plain");
                                    //                                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns");
                                    //                                sharingIntent.putExtra(Intent.EXTRA_TEXT, "I am on Nation Learns to watch cool videos, learn courses and connect with" +
                                    //                                        " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                                    //                                        "  or visit website now, www.nationlearns.com");
                                    //                                sharingIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                                    //                                sharingIntent.setType("image/jpeg");
                                    //                                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    //                                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                                    val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
                                    sharingIntent.setType("text/plain")
                                    sharingIntent.putExtra(
                                        Intent.EXTRA_SUBJECT,
                                        "Nation Learns"
                                    )
                                    sharingIntent.putExtra(
                                        Intent.EXTRA_TEXT,
                                        ("I am on Nation Learns to watch cool videos, learn courses and connect with" +
                                                " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                                                "  or visit website now, www.nationlearns.com")
                                    )
                                    startActivity(
                                        Intent.createChooser(
                                            sharingIntent,
                                            "Share via"
                                        )
                                    )
                                }
                                val drawer: DrawerLayout =
                                    findViewById<DrawerLayout>(R.id.drawer_layout)
                                val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
                                    this@WatchActivty,
                                    drawer,
                                    toolbar,
                                    R.string.navigation_drawer_open,
                                    R.string.navigation_drawer_close
                                )
                                drawer.addDrawerListener(toggle)
                                toggle.syncState()
                                drawer.closeDrawer(GravityCompat.START)
                                true
                            }
                        )


    //                        toolbar_titlee.setText("Home");

    //                        Log.d(TAG,"Expanded");
                    }
                    state = State.EXPANDED
                } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                    if (state != State.COLLAPSED) {
                        navigationView?.setNavigationItemSelectedListener { item ->
                            val id: Int = item.getItemId()
                            if (id == R.id.nav_profile_view) {
                                val prof_view1: Intent =
                                    Intent(this@WatchActivty, UpdateProfileActivity::class.java)
                                startActivity(prof_view1)
                                // Handle the camera action
                            } else if (id == R.id.nav_cncted_bizlist) {
                                val prof_view122: Intent =
                                    Intent(this@WatchActivty, MyAllCourseActivity::class.java)
                                startActivity(prof_view122)
                            } else if (id == R.id.nav_subs) {
                                val prof_view12: Intent =
                                    Intent(this@WatchActivty, SupportersList::class.java)
                                startActivity(prof_view12)
                            } else if (id == R.id.nav_spr_sprtr) {
                                val prof_view12: Intent =
                                    Intent(this@WatchActivty, SuperSupportersList::class.java)
                                startActivity(prof_view12)
                            } else if (id == R.id.nav_rate) {
                                RateThisApp.showRateDialog(this@WatchActivty)
                            } else if (id == R.id.tracktask) {
                                val intent: Intent =
                                    Intent(this@WatchActivty, ConnectedBizList::class.java)
                                startActivity(intent)
                            } else if (id == R.id.nav_paid_terms) {
                                val intent: Intent =
                                    Intent(this@WatchActivty, NlPaidTerms::class.java)
                                startActivity(intent)
                            } else if (id == R.id.nav_about) {
                                val intent: Intent =
                                    Intent(this@WatchActivty, AboutUs::class.java)
                                startActivity(intent)
                            } else if (id == R.id.nav_terms) {
                                val intent: Intent =
                                    Intent(this@WatchActivty, UserAgreement::class.java)
                                startActivity(intent)
                            } else if (id == R.id.nav_referfrnd) {
                                val intent: Intent =
                                    Intent(this@WatchActivty, ReferFrnd::class.java)
                                startActivity(intent)
                            } else if (id == R.id.nav_logout) {
                                logout()
                            } else if (id == R.id.nav_share) {
                                val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
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


                                //                                    Uri imgUri = Uri.parse(nav_img_path);
                                //                                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                //                                    sharingIntent.setType("text/plain");
                                //                                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns");
                                //                                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "I am on Nation Learns to watch cool videos, learn courses and connect with" +
                                //                                            " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                                //                                            "  or visit website now, www.nationlearns.com");
                                //                                    sharingIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                                //                                    sharingIntent.setType("image/jpeg");
                                //                                    sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                //                                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                                //
                                val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
                                sharingIntent.setType("text/plain")
                                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
                                sharingIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    ("I am on Nation Learns to watch cool videos, learn courses and connect with" +
                                            " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
                                            "  or visit website now, www.nationlearns.com")
                                )
                                startActivity(Intent.createChooser(sharingIntent, "Share via"))
                            }
                            val drawer: DrawerLayout =
                                findViewById<DrawerLayout>(R.id.drawer_layout)
                            val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
                                this@WatchActivty,
                                drawer,
                                toolbar,
                                R.string.navigation_drawer_open,
                                R.string.navigation_drawer_close
                            )
                            drawer.addDrawerListener(toggle)
                            toggle.syncState()
                            drawer.closeDrawer(GravityCompat.START)
                            true
                        }


                        //                        toolbar_titlee.setText("Home");
                        Log.d(TAG, "Collapsed")
                    }
                    state = State.COLLAPSED
                } else {
                    if (state != State.IDLE) {
                        Log.d(TAG, "Idle")
                    }
                    state = State.IDLE
                }
            }
        })
    }

    fun img_update1() {
        nav_img_path = nav_user_pic
        Thread(Runnable {
            this@WatchActivty.runOnUiThread(Runnable {
                if (nav_img_path != null && !nav_img_path!!.isEmpty()) {

                    context?.let { nav_user_img?.let { it1 ->
                        Glide.with(it).load(nav_img_path).into(
                            it1
                        )
                    }

                    }
                }
            })
        }).start()
    }

    public override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id: Int = item.getItemId()
        if (id == R.id.nav_profile_view) {
            val prof_view1: Intent = Intent(this@WatchActivty, UpdateProfileActivity::class.java)
            startActivity(prof_view1)
            // Handle the camera action
        } else if (id == R.id.nav_subs) {
            val prof_view12: Intent = Intent(this@WatchActivty, SupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_spr_sprtr) {
            val prof_view12: Intent = Intent(this@WatchActivty, SuperSupportersList::class.java)
            startActivity(prof_view12)
        } else if (id == R.id.nav_rate) {
            RateThisApp.showRateDialog(this@WatchActivty)
        } else if (id == R.id.tracktask) {
            val intent: Intent = Intent(this@WatchActivty, ConnectedBizList::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_about) {
            val intent: Intent = Intent(this@WatchActivty, AboutUs::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_paid_terms) {
            val intent: Intent = Intent(this@WatchActivty, NlPaidTerms::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_cncted_bizlist) {
            val prof_view122: Intent = Intent(this@WatchActivty, MyAllCourseActivity::class.java)
            startActivity(prof_view122)
        } else if (id == R.id.nav_terms) {
            val intent: Intent = Intent(this@WatchActivty, UserAgreement::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_referfrnd) {
            val intent: Intent = Intent(this@WatchActivty, ReferFrnd::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_logout) {
            logout()
        } else if (id == R.id.nav_share) {
            val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                        "Click here to Download https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        } else if (id == R.id.nav_invite) {

//            Uri imgUri = Uri.parse(nav_img_path);
//            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//            sharingIntent.setType("text/plain");
//            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns");
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, "I am on Nation Learns to watch cool videos, learn courses and connect with" +
//                    " service providers whenever required, come join me on Nation Learns, Download the app, https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns" +
//                    "  or visit website now, www.nationlearns.com");
//            sharingIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
//            sharingIntent.setType("image/jpeg");
//            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            val sharingIntent: Intent = Intent(Intent.ACTION_SEND)
            sharingIntent.setType("text/plain")
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Nation Learns")
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Download NationLearns App, India's first video watching, E-learning, B2C market place, watch videos, learn courses and connect with local service providers. " +
                        "Click here to Download https://play.google.com/store/apps/details?id=www.natlrnsnew.nationlearns"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        val drawer: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun logout() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@WatchActivty)
        builder.setIcon(R.drawable.logout)
            .setTitle("Log out")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog: DialogInterface?, id: Int ->
                    manager?.setPreferences(this@WatchActivty, Constants.LOGIN_STATUS, "0")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_NAME, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_EMAIL, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_NUM, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_STATE, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_CITY, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_GENDER, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_MARITAL, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_AINCOME, "")
                    manager?.setPreferences(this@WatchActivty, Constants.USER_PROFILE_PIC, "")
                    val intent: Intent =
                        Intent(this@WatchActivty, LoginOtpActivityTimer::class.java)
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

    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.tmenu_wtch, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId")
    public override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_supportt -> {
                val prof_view12: Intent = Intent(this@WatchActivty, SupprterVidList::class.java)
                startActivity(prof_view12)
                return true
            }

            R.id.action_ssupportt -> {
                val sprsprt: Intent = Intent(this@WatchActivty, SuperSupprterVidList::class.java)
                startActivity(sprsprt)
                return true
            }

            R.id.action_crtrzzone -> {
                try {
                    getbizcnttedlistcz()
                    //                    Intent prof_view123;
//                    if (pool_id_str != null) {
//                        prof_view123 = new Intent(WatchActivty.this, CrtrZoneActivity.class);
//                        prof_view123.putExtra("ch_id_prf", chanel_id_str);
//                    } else {
//                        prof_view123 = new Intent(WatchActivty.this, TCreatChanelActvty.class);
//                    }
//                    startActivity(prof_view123);
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return true
            }

            R.id.action_golive -> {
                try {
                    val intent: Intent = Intent(this, GoLiveActitivy::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return true
            }

            R.id.action_svdvds -> {
                val intntsaved: Intent = Intent(this@WatchActivty, TSavedVidsActivty::class.java)
                startActivity(intntsaved)
                return true
            }

            R.id.action_mypstsrch -> {
                val wtchintnt: Intent = Intent(this@WatchActivty, WatchHistryActivty::class.java)
                startActivity(wtchintnt)
                return true
            }

            R.id.action_wtchwshlst -> {
                val intntww: Intent = Intent(this@WatchActivty, TWtchWshlistAcvty::class.java)
                startActivity(intntww)
                return true
            }

            R.id.action_crtrrnking -> {
                val crtrrnkings: Intent = Intent(this@WatchActivty, CrtrRankActivity::class.java)
                startActivity(crtrrnkings)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed();
//        finish();
        val selectedItemId: Int? = navigation?.selectedItemId
        //        // If the current item is not the home item, go back to the home item
        if (selectedItemId != R.id.btm_dashbrd) {
            navigation?.selectedItemId = R.id.btm_dashbrd
            //            finish();
        } else {
            // Otherwise, call the default behavior (exit the app)
            super.onBackPressed()
        }
    }

    public override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        navigation?.visibility = View.VISIBLE
        return true
    }

    public override fun setFinishOnTouchOutside(finish: Boolean) {
        super.setFinishOnTouchOutside(finish)
    }

    @SuppressLint("ClickableViewAccessibility")
    public override fun onTouch(v: View, event: MotionEvent): Boolean {
        navigation?.visibility = View.VISIBLE
        return true
    }

    public override fun onResume() {
        super.onResume()
        profileimage()
        navigation?.visibility = View.VISIBLE
        img_update1()
    }

    private fun applyFontToMenuItem(mi: MenuItem) {
        val font: Typeface = Typeface.createFromAsset(getAssets(), "fonts/ExoBold.otf")
        val mNewTitle: SpannableString = SpannableString(mi.getTitle())
        mNewTitle.setSpan(
            CustomTypefaceSpan("", font),
            0,
            mNewTitle.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        mi.setTitle(mNewTitle)
    }

    private fun checkAndRequestPermissions() {
        val permissioncallcam: Int =
            ContextCompat.checkSelfPermission(this@WatchActivty, Manifest.permission.CAMERA)
        //        int locationPermission = ContextCompat.checkSelfPermission(WatchActivty.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionSendMessage = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_CONTACTS);
//        int locationcoarsePermission = ContextCompat.checkSelfPermission(WatchActivty.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int SmsPermission = ContextCompat.checkSelfPermission(HomeDemoActivityCtgry.this, Manifest.permission.READ_SMS);
        val storagePermission: Int = ContextCompat.checkSelfPermission(
            this@WatchActivty,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val storagewritePermission: Int = ContextCompat.checkSelfPermission(
            this@WatchActivty,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (permissioncallcam != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (storagewritePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        //        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
//        }
//        if (locationcoarsePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//        }
//        if (SmsPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
//        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this@WatchActivty,
                listPermissionsNeeded.toTypedArray<String>(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
        }
    }

    companion object {
        val REQUEST_ID_MULTIPLE_PERMISSIONS: Int = 1
        protected val TAG: String = "WatchActivty"
    }
}