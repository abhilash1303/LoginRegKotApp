package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SuprtVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidList
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidPojo

class SupprterVidList constructor() : AppCompatActivity() {
    var rv_all_videos: RecyclerView? = null

    //    public static View.OnClickListener myOnClickListener_cats, myOnClickListener_imgs, myOnClickListener_vids;
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var context1: Activity? = null
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null

    //    SwipeRefreshLayout swipe_refresh;
    var customDialog: CustomDialog? = null
    var nodata: RelativeLayout? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var ll_sprtr_vids: LinearLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supprter_vid_list)
        context = SupprterVidList()
        context1 = SupprterVidList()
        customDialog = CustomDialog(this)
        manager = SessionManager()
        token = manager?.getPreferences(this@SupprterVidList, Constants.USER_TOKEN_LRN)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        ll_sprtr_vids = findViewById<LinearLayout>(R.id.ll_sprtr_vids)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        layoutManager_vidsList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.setLayoutManager(layoutManager_vidsList)
        getLEarnVids()

//        swipe_refresh.setOnRefreshListener(() -> {
        getLEarnVids()

//            swipe_refresh.setRefreshing(false);
//        });
    }

    private fun getLEarnVids() {
        try {
            shimmerFrameLayout?.startShimmer()
            //            customDialog.show();
//        blur_reg1.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "my-supportvideos"
            RetrofitClient.getClient().suprtvidList(
                url, "application/json",
                "Bearer " + token
            )
                ?.enqueue(object : GlobalCallback<MySprtVidPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<MySprtVidPojo?>,
                        response: Response<MySprtVidPojo?>
                    ) {

//                        blur_reg1.visibility = View.GONE;

//                            customDialog.dismiss();
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            val sprtvidsts: String ? = response.body()?.status
                            if (sprtvidsts != null) {
                                if (sprtvidsts.contains("0")) {
                                    nodata?.visibility = View.VISIBLE
                                } else if (sprtvidsts.contains("1")) {
                                    nodata?.visibility = View.GONE
                                    val mySprtVids: List<MySprtVidList> ? = response.body()?.videos
                                    if (mySprtVids != null) {
                                        if (mySprtVids.isEmpty()) {
                                            nodata?.visibility = View.VISIBLE
                                        } else {
                                            ll_sprtr_vids?.visibility = View.VISIBLE
                                            shimmerFrameLayout?.visibility = View.GONE
                                            adapter_vids_list =
                                                SuprtVidListAdapter(
                                                    mySprtVids,
                                                    this@SupprterVidList
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
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
    }
}