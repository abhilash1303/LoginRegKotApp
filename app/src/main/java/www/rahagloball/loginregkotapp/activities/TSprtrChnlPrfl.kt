package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChAboutFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChCpyrightFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChHomeFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChMntznFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChRvnuFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChSttngFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChVidsFrgmnt
import www.rahagloball.loginregkotapp.fragments.mychdtlsf.MyChWwbFrgmnt
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsCuty
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsVid
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsVideos
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo
import www.rahagloball.loginregkotapp.models.mychanldtls.SubscribedDatum

class TSprtrChnlPrfl constructor() : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var bundle: Bundle? = null
    var my_chanls: TextView? = null
    var mych_sprtr_cnt: TextView? = null
    var mychiddummy: TextView? = null
    var my_cutiess: TextView? = null
    var my_vidsss: TextView? = null
    var my_crses: TextView? = null
    var my_sprtrss: TextView? = null
    var my_sprsprtrss: TextView? = null
    var my_ttl_pyouts: TextView? = null
    var my_revnue: TextView? = null
    var my_ch_namee: TextView? = null
    var chanl_idtosend: String? = null
    var sprtr_imgeee: CircleImageView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var chnl_id_str: String? = null
    var mychiddummy_str: String? = null
    var horizontalScrollView: HorizontalScrollView? = null
    var context: Context? = null
    var collapsingToolbar: CollapsingToolbarLayout? = null
    var catggryList: List<DataItem> = ArrayList<DataItem>()
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main_mych)
        context = this@TSprtrChnlPrfl
        manager = SessionManager()
        token = manager?.getPreferences(this@TSprtrChnlPrfl, Constants.USER_TOKEN_LRN)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout_ch)
        horizontalScrollView = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
        collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        viewPager = findViewById<ViewPager>(R.id.view_pager_ch)
        sprtr_imgeee = findViewById<CircleImageView>(R.id.sprtr_imgeee)
        mychiddummy = findViewById<TextView>(R.id.mychiddummy)
        my_chanls = findViewById<TextView>(R.id.my_chanls)
        my_cutiess = findViewById<TextView>(R.id.my_cutiess)
        my_vidsss = findViewById<TextView>(R.id.my_vidsss)
        my_crses = findViewById<TextView>(R.id.my_crses)
        my_sprtrss = findViewById<TextView>(R.id.my_sprtrss)
        my_sprsprtrss = findViewById<TextView>(R.id.my_sprsprtrss)
        my_ttl_pyouts = findViewById<TextView>(R.id.my_ttl_pyouts)
        my_revnue = findViewById<TextView>(R.id.my_revnue)
        my_ch_namee = findViewById<TextView>(R.id.my_ch_namee)
        mych_sprtr_cnt = findViewById<TextView>(R.id.mych_sprtr_cnt)
        bundle = intent.extras
        chanl_idtosend = bundle?.getString("ch_id_prf")
        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyChHomeFrgmnt(), getString(R.string.home), chnl_id_str, "")
        adapter.addFragment(MyChVidsFrgmnt(), getString(R.string.video11), chnl_id_str, "")
        adapter.addFragment(MyChAboutFrgmnt(), getString(R.string.aboutt), chnl_id_str, "")
        adapter.addFragment(MyChMntznFrgmnt(), getString(R.string.montzn), chnl_id_str, "")
        adapter.addFragment(MyChWwbFrgmnt(), getString(R.string.wwbbb), chnl_id_str, "")
        adapter.addFragment(MyChCpyrightFrgmnt(), getString(R.string.cpyrgt), chnl_id_str, "")
        adapter.addFragment(MyChRvnuFrgmnt(), getString(R.string.revenueee), chnl_id_str, "")
        adapter.addFragment(MyChSttngFrgmnt(), getString(R.string.aboutt), chnl_id_str, "")
        viewPager?.setAdapter(adapter)
        //set ViewPager
        tabLayout?.setupWithViewPager(viewPager)
        changeTabsFont()
        viewPager?.setOffscreenPageLimit(8)


//        horizontalScrollView.postDelayed(new Runnable() {
//            public void run() {
//                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
//            }
//        }, 100L);
        getchdtlssss()
        try {
            getmychdtlssss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMyChDtls(): String? {
        return chanl_idtosend
    }

    fun getchdtlssss() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(viewPager) {
                override fun onResponse(
                    call: Call<GetChanlPojo?>,
                    response: Response<GetChanlPojo?>
                ) {
                    try {
//                        catggryList = response.body()?.data!!
//
                        catggryList = response.body()?.data!!
                        for (i in catggryList.indices) {
                            val dataItem: DataItem = catggryList[i]
                            chnl_id_str = dataItem.id
                            val ch_namee: String? = dataItem.name
                            my_ch_namee?.text = ch_namee
                            val ch_imgg: String =
                                Configs.BASE_URL21 + "images/user/" + dataItem.image
                            //                                Log.e("ch_imgg", ch_imgg);
                            sprtr_imgeee?.let {
                                context?.let { it1 ->
                                    Glide.with(it1)
                                        .load(ch_imgg)
                                        .into(it)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    fun getmychdtlssss() {
        bundle = intent.extras
        chanl_idtosend = bundle?.getString("ch_id_prf")
        val url: String = Configs.BASE_URL2 + "channels/" + chanl_idtosend
        RetrofitClient.getClient().getmychdtls(
            url, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<MyChnlDtlsPojo?>(viewPager) {
                override fun onResponse(
                    call: Call<MyChnlDtlsPojo?>,
                    response: Response<MyChnlDtlsPojo?>
                ) {

//                        rl_cbl.visibility = View.GONE;
                    try {
                        val supprtcnttt: String? = response.body()?.supportcount
                        mych_sprtr_cnt?.text = (supprtcnttt + " Supporters")
                        my_sprtrss?.text = (supprtcnttt)
                        val catggryList: List<SubscribedDatum>? = response.body()?.subscribedData
                        val cutiesdd: List<MyChDtlsCuty>? = response.body()?.cuties
                        val cutirsizee: Int? = cutiesdd?.size
                        my_cutiess?.text = (cutirsizee.toString())
                        val myChDtlsVideos: MyChDtlsVideos? = response.body()?.videos
                        val videosddf: List<MyChDtlsVid>? = myChDtlsVideos?.videos
                        val vidsssrsizee: Int? = videosddf?.size
                        my_vidsss?.text = (vidsssrsizee.toString())
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