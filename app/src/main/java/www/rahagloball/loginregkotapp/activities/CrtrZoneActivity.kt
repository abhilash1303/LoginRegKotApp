package www.rahagloball.loginregkotapp.activities

//import android.widget.Spinner
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.crbizchanel.CrtrAnalytcsFrgmntBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.CrtrCtsListFrgmntBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.CrtsVideoFrgmntBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.CzMyChAboutBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.DashboardFragmnetBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.MyChanelFragmnetBz
import www.rahagloball.loginregkotapp.fragments.crbizchanel.SupportersListFragmentBz
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrAnalytcsFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrChMntznFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrChRvnuFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrCtsListFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrMangVidFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrSuprSprtSettings
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtrVidStatsFragmnet
import www.rahagloball.loginregkotapp.fragments.crtrzone.CrtsVideoFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.CzMyChAbout
import www.rahagloball.loginregkotapp.fragments.crtrzone.DashboardFragmnet
import www.rahagloball.loginregkotapp.fragments.crtrzone.MyChanelFragmnet
import www.rahagloball.loginregkotapp.fragments.crtrzone.MyCourseFrgmnt
import www.rahagloball.loginregkotapp.fragments.crtrzone.SprSprtVidFragment
import www.rahagloball.loginregkotapp.fragments.crtrzone.SupportersListFragment
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getbizchnl.GetBizChnl
import www.rahagloball.loginregkotapp.models.getbizchnl.GetBizChnlPojo
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo

class CrtrZoneActivity : AppCompatActivity() {
    var bundle: Bundle? = null
    var upload_vid_cz: TextView? = null
    var token: String? = null
    var chanel_id_str: String? = null
    var pool_id_str: String? = null
    var myBizChData: String? = null
    var manager: SessionManager? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var vadapter: ViewPagerAdapter? = null
   private lateinit var myData: String
    var tab_1_ovrvw: TextView? = null
    var tab_2_gnder: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crtr_zone)
        manager = SessionManager()
        token = manager?.getPreferences(this@CrtrZoneActivity, Constants.USER_TOKEN_LRN)
        upload_vid_cz = findViewById<TextView>(R.id.upload_vid_cz)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        bundle = intent.extras
        myData = bundle?.getString("ch_id_prf").toString()
        tab_1_ovrvw = findViewById<TextView>(R.id.tab_1_ovrvw)
        tab_2_gnder = findViewById<TextView>(R.id.tab_2_gnder)
        tab_1_ovrvw?.setTextColor(Color.WHITE)
        tab_2_gnder?.setTextColor(Color.BLACK)
        tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_selected_bg)
        tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
        vadapter = ViewPagerAdapter(supportFragmentManager)
        vadapter?.addFragment(DashboardFragmnet(), getString(R.string.b_dashh), chanel_id_str, "")
        vadapter?.addFragment(MyChanelFragmnet(), getString(R.string.my_ch_me), chanel_id_str, "")
        vadapter?.addFragment(CrtsVideoFrgmnt(), getString(R.string.video11), chanel_id_str, "")
        vadapter?.addFragment(CrtrCtsListFrgmnt(), getString(R.string.b_cutiess), chanel_id_str, "")
        vadapter?.addFragment(MyCourseFrgmnt(), getString(R.string.my_courses), chanel_id_str, "")
        vadapter?.addFragment(
            SupportersListFragment(),
            getString(R.string.sprt_my),
            chanel_id_str,
            ""
        )
        vadapter?.addFragment(SprSprtVidFragment(), getString(R.string.ssprt_my), chanel_id_str, "")
        vadapter?.addFragment(CrtrMangVidFrgmnt(), getString(R.string.mange_vid), chanel_id_str, "")
        vadapter?.addFragment(CrtrAnalytcsFrgmnt(), getString(R.string.anlytvs), chanel_id_str, "")
        vadapter?.addFragment(
            CrtrVidStatsFragmnet(),
            getString(R.string.overall_stks),
            chanel_id_str,
            ""
        )
        vadapter?.addFragment(
            CrtrSuprSprtSettings(),
            getString(R.string.sssetings),
            chanel_id_str,
            ""
        )
        vadapter?.addFragment(CrtrChRvnuFrgmnt(), getString(R.string.revenueee), chanel_id_str, "")
        vadapter?.addFragment(CrtrChMntznFrgmnt(), getString(R.string.montzn), chanel_id_str, "")
        vadapter?.addFragment(CzMyChAbout(), getString(R.string.aboutt), chanel_id_str, "")
        viewPager?.adapter = (vadapter)
        //set ViewPager
        tabLayout?.setupWithViewPager(viewPager)
        changeTabsFont()
        viewPager?.offscreenPageLimit = (12)
        tab_1_ovrvw?.setOnClickListener(View.OnClickListener { v: View? ->
            tab_1_ovrvw?.setTextColor(Color.WHITE)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            vadapter = ViewPagerAdapter(supportFragmentManager)
            vadapter?.addFragment(
                DashboardFragmnet(),
                getString(R.string.b_dashh),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                MyChanelFragmnet(),
                getString(R.string.my_ch_me),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(CrtsVideoFrgmnt(), getString(R.string.video11), chanel_id_str, "")
            vadapter?.addFragment(
                CrtrCtsListFrgmnt(),
                getString(R.string.b_cutiess),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                MyCourseFrgmnt(),
                getString(R.string.my_courses),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                SupportersListFragment(),
                getString(R.string.sprt_my),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                SprSprtVidFragment(),
                getString(R.string.ssprt_my),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrMangVidFrgmnt(),
                getString(R.string.mange_vid),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrAnalytcsFrgmnt(),
                getString(R.string.anlytvs),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrVidStatsFragmnet(),
                getString(R.string.overall_stks),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrSuprSprtSettings(),
                getString(R.string.sssetings),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrChRvnuFrgmnt(),
                getString(R.string.revenueee),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(
                CrtrChMntznFrgmnt(),
                getString(R.string.montzn),
                chanel_id_str,
                ""
            )
            vadapter?.addFragment(CzMyChAbout(), getString(R.string.aboutt), chanel_id_str, "")
            viewPager?.adapter = (vadapter)
            //set ViewPager
            tabLayout?.setupWithViewPager(viewPager)
            changeTabsFont()
            viewPager?.offscreenPageLimit = (12)
        })
        tab_2_gnder?.setOnClickListener(View.OnClickListener { v: View? ->
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.WHITE)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_selected_bg)
            vadapter = ViewPagerAdapter(supportFragmentManager)
            vadapter?.addFragment(
                DashboardFragmnetBz(),
                getString(R.string.b_dashh),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                MyChanelFragmnetBz(),
                getString(R.string.my_ch_me),
                "",
                myBizChData
            )
            vadapter?.addFragment(CrtsVideoFrgmntBz(), getString(R.string.video11), "", myBizChData)
            vadapter?.addFragment(
                CrtrCtsListFrgmntBz(),
                getString(R.string.b_cutiess),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                SupportersListFragmentBz(),
                getString(R.string.sprt_my),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                SprSprtVidFragment(),
                getString(R.string.ssprt_my),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                CrtrMangVidFrgmnt(),
                getString(R.string.mange_vid),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                CrtrAnalytcsFrgmntBz(),
                getString(R.string.anlytvs),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                CrtrVidStatsFragmnet(),
                getString(R.string.overall_stks),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                CrtrSuprSprtSettings(),
                getString(R.string.sssetings),
                "",
                myBizChData
            )
            vadapter?.addFragment(
                CrtrChRvnuFrgmnt(),
                getString(R.string.revenueee),
                "",
                myBizChData
            )
            vadapter?.addFragment(CrtrChMntznFrgmnt(), getString(R.string.montzn), "", myBizChData)
            vadapter?.addFragment(CzMyChAboutBz(), getString(R.string.aboutt), "", myBizChData)
            viewPager?.adapter = (vadapter)
            //set ViewPager
            tabLayout?.setupWithViewPager(viewPager)
            changeTabsFont()
            viewPager?.offscreenPageLimit = 12
        })
        upload_vid_cz?.setOnClickListener(View.OnClickListener { v: View? ->
            try {
//                Intent intent = new Intent(CrtrZoneActivity.this, TCreatChanelActvty.class);
//                startActivity(intent);
//                if (pool_id_str.equals("") || pool_id_str.length() == 0) {
//                    Toast.makeText(this, "Create a Channel first!! ", Toast.LENGTH_SHORT).show();
//                    Intent intent1 = new Intent(CrtrZoneActivity.this, TCreatChanelActvty.class);
//                    startActivity(intent1);
//                } else {
                val intent11 = Intent(this@CrtrZoneActivity, TUploadVid::class.java)
                startActivity(intent11)

//                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        createchannel()
        createbizchannel()
    }

    fun jumpToTab(tabIndex: Int) {
        viewPager?.currentItem = tabIndex
        tabLayout?.getTabAt(tabIndex)?.select()
    }

    fun getMyData(): String {
        return myData
    }

    fun getMyBusinessChannelData(): String? {
        return myBizChData
    }

    private fun createchannel() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(upload_vid_cz) {
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
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun createbizchannel() {
        val url: String = Configs.BASE_URL2 + "getbiz-channel"
        RetrofitClient.getClient().getbizchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetBizChnlPojo?>(upload_vid_cz) {
                override fun onResponse(
                    call: Call<GetBizChnlPojo?>,
                    response: Response<GetBizChnlPojo?>
                ) {
                    try {
                        val biz_res: String? = response.body()?.status
                        if (biz_res == "200") {
                            val catggryList: GetBizChnl? = response.body()?.data
                            if (catggryList != null) {
                                myBizChData = catggryList.id
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun changeTabsFont() {
        val vg: ViewGroup = tabLayout?.getChildAt(0) as ViewGroup
        val tabsCount: Int = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab: ViewGroup = vg.getChildAt(j) as ViewGroup
            val tabChildsCount: Int = vgTab.childCount
            for (i in 0 until tabChildsCount) {
                val tabViewChild: View = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    val custom_font: Typeface? =
                        ResourcesCompat.getFont(this, R.font.poppins_regular)
                    (tabViewChild as TextView).setTypeface(custom_font)
                }
            }
        }
    }
}