package www.rahagloball.loginregkotapp.activities



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
import www.rahagloball.loginregkotapp.adapters.WtchHistryAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.watchistry.WtchHistryPojo
import www.rahagloball.loginregkotapp.models.watchistry.WtchHstryVideo


class WatchHistryActivty constructor() : AppCompatActivity() {
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
        context = WatchHistryActivty()
        context1 = WatchHistryActivty()
        customDialog = CustomDialog(this)
        manager = SessionManager()
        token = manager?.getPreferences(this@WatchHistryActivty, Constants.USER_TOKEN_LRN)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        spurt_tooltxttx = findViewById<TextView>(R.id.spurt_tooltxttx)
        txt_all_vidssList = findViewById<TextView>(R.id.txt_all_vidssList)
        ll_sprtr_vids = findViewById<LinearLayout>(R.id.ll_sprtr_vids)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        spurt_tooltxttx?.text = "Watch History"
        txt_all_vidssList?.text = "Watch History"
        layoutManager_vidsList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.layoutManager = layoutManager_vidsList
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


//            customDialog.show();
//        blur_reg1.visibility = View.VISIBLE;
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-watchhistory"
            RetrofitClient.getClient().wtchhsitory(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<WtchHistryPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                  override  fun onResponse(
                        call: Call<WtchHistryPojo?>,
                        response: Response<WtchHistryPojo?>
                    ) {

//                        blur_reg1.visibility = View.GONE;

//                            customDialog.dismiss();
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            val sprtvidsts: String? = response.body()?.status
                            if (sprtvidsts != null) {
                                if (sprtvidsts.contains("0")) {
                                    nodata?.visibility = View.VISIBLE
                                } else if (sprtvidsts.contains("1")) {
                                    nodata?.visibility = View.GONE
                                    val mySprtVids: List<WtchHstryVideo>? = response.body()?.videos
                                    if (mySprtVids != null) {
                                        if (mySprtVids.isEmpty()) {
                                            nodata?.visibility = View.VISIBLE
                                        } else {
                                            ll_sprtr_vids?.visibility = View.VISIBLE
                                            shimmerFrameLayout?.visibility = View.GONE
                                            adapter_vids_list =
                                                WtchHistryAdapter(mySprtVids, this@WatchHistryActivty)
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