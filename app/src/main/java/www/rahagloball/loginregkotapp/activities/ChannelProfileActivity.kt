package www.rahagloball.loginregkotapp.activities

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.ChCourseFrgmnt
import www.rahagloball.loginregkotapp.fragments.ChCtsListFrgmnt
import www.rahagloball.loginregkotapp.fragments.ChVideoFrgmnt
import www.rahagloball.loginregkotapp.fragments.OpAboutFrgmnt
import www.rahagloball.loginregkotapp.fragments.OpBlogsFrgmnt
import www.rahagloball.loginregkotapp.fragments.OpSuprSprtFrgmnt
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsCuty
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsVid
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChDtlsVideos
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo

class ChannelProfileActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var bundle: Bundle? = null
    var myData: String? = null
    var manager: SessionManager? = null
    var token: String? = null
    var chanl_idtosend: String? = null
    var context: Context? = null
    var mych_sprtr_cnt: TextView? = null
    var my_cutiess: TextView? = null
    var my_vidsss: TextView? = null
    var my_sprtrss: TextView? = null
    var my_ch_namee: TextView? = null
    var sprtr_imgeee: CircleImageView? = null
    var appBbar: LinearLayout? = null

    //    ShimmerFrameLayout shimmerFrameLayout;
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_profile)
        bundle = intent.extras
        myData = bundle?.getString("ch_iddCuties")
        context = this@ChannelProfileActivity
        manager = SessionManager()
        token = manager?.getPreferences(this@ChannelProfileActivity, Constants.USER_TOKEN_LRN)
        my_ch_namee = findViewById<TextView>(R.id.my_ch_namee)
        mych_sprtr_cnt = findViewById<TextView>(R.id.mych_sprtr_cnt)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout_ch)
        viewPager = findViewById<ViewPager>(R.id.view_pager_ch)
        sprtr_imgeee = findViewById<CircleImageView>(R.id.sprtr_imgeee)
        my_cutiess = findViewById<TextView>(R.id.my_cutiess)
        my_vidsss = findViewById<TextView>(R.id.my_vidsss)
        my_sprtrss = findViewById<TextView>(R.id.my_sprtrss)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ChVideoFrgmnt(), getString(R.string.video11), myData, "")
        adapter.addFragment(ChCourseFrgmnt(), getString(R.string.coursess), myData, "")
        adapter.addFragment(ChCtsListFrgmnt(), getString(R.string.b_cutiess), myData, "")
        adapter.addFragment(OpSuprSprtFrgmnt(), getString(R.string.supr_sprt_contnt), myData, "")
        adapter.addFragment(OpBlogsFrgmnt(), getString(R.string.blogss), myData, "")
        adapter.addFragment(OpAboutFrgmnt(), getString(R.string.aboutt), myData, "")
        viewPager?.adapter = adapter
        //set ViewPager
        tabLayout?.setupWithViewPager(viewPager)
        changeTabsFont()
        viewPager?.offscreenPageLimit = 5
        getmychdtlssss(myData)
    }

    fun getMyChData(): String? {
        return chanl_idtosend
    }


    fun getmychdtlssss(chanl_idtosend: String?) {
        val url: String = Configs.BASE_URL2 + "channels/" + chanl_idtosend
        RetrofitClient.getClient().getmychdtls(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<MyChnlDtlsPojo?>(viewPager) {
                override fun onResponse(call: Call<MyChnlDtlsPojo?>, response: Response<MyChnlDtlsPojo?>) {
                    try {
                        if (response.body() != null) {
                            val ch_namee: String? = response.body()!!.channelTitle
                            //                            Log.e("ch_namee", ch_namee);
                            if (ch_namee != null) my_ch_namee?.text = ch_namee
                            val cutiesdd: List<MyChDtlsCuty>? = response.body()!!.cuties
                            val cutirsizee = cutiesdd?.size
                            my_cutiess?.text = cutirsizee.toString()
                            val myChDtlsVideos: MyChDtlsVideos? = response.body()!!.videos
                            val ch_imgg: String =
                                Configs.BASE_URL21 + "images/channel/" + myChDtlsVideos?.image
                            //                                Log.e("ch_imggcp", ch_imgg);
                            context?.let {
                                sprtr_imgeee?.let { it1 ->
                                    Glide.with(it)
                                        .load(ch_imgg)
                                        .placeholder(R.drawable.nllogo)
                                        .into(it1)
                                }
                            }
                            val videosddf: List<MyChDtlsVid>? = myChDtlsVideos?.videos
                            val vidsssrsizee = videosddf?.size
                            my_vidsss?.text = vidsssrsizee.toString()
                            val supprtcnttt: String? = response.body()!!.supportcount
                            mych_sprtr_cnt?.text = "$supprtcnttt Supporters"
                            my_sprtrss?.text = supprtcnttt
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

//    private void loadFragment(Fragment fragment) {
    //        // load fragment
    //        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
    //        transaction.replace(R.id.frame_container, fragment);
    //        transaction.addToBackStack(null);
    //        transaction.commitAllowingStateLoss();
    //    }


//}