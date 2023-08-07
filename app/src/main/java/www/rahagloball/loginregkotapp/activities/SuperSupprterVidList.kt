package www.rahagloball.loginregkotapp.activities

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SuprSuprtVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySprSprtVidsPojo
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySuprSprtVidList

//import www.nationlearnsraha.com.Configuration.Config;
//import www.nationlearnsraha.com.ConstantAndSession.Constants;
//import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
//import www.nationlearnsraha.com.LoginSignupOtp.CustomDialog;
//import www.nationlearnsraha.com.Model.GlobalCallback;
//import www.nationlearnsraha.com.Model.Pojo.mysuprsprtsvids.MySprSprtVidsPojo;
//import www.nationlearnsraha.com.Model.Pojo.mysuprsprtsvids.MySuprSprtVidList;
//import www.nationlearnsraha.com.Model.RetrofitClient;
//import www.nationlearnsraha.com.R;
//import www.nationlearnsraha.com.mainpage.adapters.SuprSuprtVidListAdapter;
class SuperSupprterVidList constructor() : AppCompatActivity() {
    var rv_learn_imagess: RecyclerView? = null
    var rv_learn_cats: RecyclerView? = null
    var rv_learn_videos: RecyclerView? = null
    var rv_all_videos: RecyclerView? = null
    private val layoutManager_cats: RecyclerView.LayoutManager? = null
    private val layoutManager_imgs: RecyclerView.LayoutManager? = null
    private val layoutManager_vids: RecyclerView.LayoutManager? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var context1: Activity? = null
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null

    //    SwipeRefreshLayout swipe_refresh;
    var customDialog: CustomDialog? = null
    var nodata: RelativeLayout? = null
    var spurt_tooltxttx: TextView? = null
    var txt_all_vidssList: TextView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var ll_sprtr_vids: LinearLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supprter_vid_list)
        context = SuperSupprterVidList()
        context1 = SuperSupprterVidList()
        customDialog = CustomDialog(this)
        manager = SessionManager()
        token = manager?.getPreferences(this@SuperSupprterVidList, Constants.USER_TOKEN_LRN)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        spurt_tooltxttx = findViewById<TextView>(R.id.spurt_tooltxttx)
        ll_sprtr_vids = findViewById<LinearLayout>(R.id.ll_sprtr_vids)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        spurt_tooltxttx?.setText("Super Supported Videos")
        layoutManager_vidsList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.setLayoutManager(layoutManager_vidsList)
        //        rv_all_videos.setHasFixedSize(true);
        getLEarnVids()

//        swipe_refresh.setOnRefreshListener(() -> {

//            getLEarnCats();
//            getLEarnImgs();
        getLEarnVids()

//            swipe_refresh.setRefreshing(false);
//        });
    }

    private fun getLEarnVids() {
        try {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-ssupportvideos"
            RetrofitClient.getClient().sprsuprtvidList(
                url, "application/json",
                "Bearer " + token
            )
                ?.enqueue(object : GlobalCallback<MySprSprtVidsPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<MySprSprtVidsPojo?>,
                        response: Response<MySprSprtVidsPojo?>
                    ) {

//                        blur_reg1.visibility = View.GONE;
                        shimmerFrameLayout?.stopShimmer()

//                            customDialog.dismiss();
                        try {
                            val sprtvidsts: String ? = response.body()?.status
                            if (sprtvidsts != null) {
                                if (sprtvidsts.contains("0")) {
                                    nodata?.visibility = View.VISIBLE
                                } else if (sprtvidsts.contains("1")) {
                                    nodata?.visibility = View.GONE
                                    val mySprtVids: List<MySuprSprtVidList>? =
                                        response.body()?.videos
                                    if (mySprtVids != null) {
                                        if (mySprtVids.isEmpty()) {
                                            nodata?.visibility = View.VISIBLE
                                        } else {
                                            ll_sprtr_vids?.visibility = View.VISIBLE
                                            shimmerFrameLayout?.visibility = View.GONE
                                            adapter_vids_list = SuprSuprtVidListAdapter(
                                                mySprtVids,
                                                this@SuperSupprterVidList
                                            )
                                            rv_all_videos!!.adapter = adapter_vids_list
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

    companion object {
        private val adapter_cats: RecyclerView.Adapter<*>? = null
        private val adapter_imgs: RecyclerView.Adapter<*>? = null
        private val adapter_vids: RecyclerView.Adapter<*>? = null
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
        var myOnClickListener_cats: View.OnClickListener? = null
        var myOnClickListener_imgs: View.OnClickListener? = null
        var myOnClickListener_vids: View.OnClickListener? = null
    }
}