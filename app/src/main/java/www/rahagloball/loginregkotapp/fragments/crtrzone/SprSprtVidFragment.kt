package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.SuperSupprterVidList
import www.rahagloball.loginregkotapp.adapters.SuprSuprtVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySprSprtVidsPojo
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySuprSprtVidList

class SprSprtVidFragment : Fragment() {
    var rv_all_videos: RecyclerView? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var context1: Activity? = null
    private lateinit var context: Context
    var manager: SessionManager? = null
    var token: String? = null

    //    SwipeRefreshLayout swipe_refresh;
    var customDialog: CustomDialog? = null
    var nodata: RelativeLayout? = null
    var spurt_tooltxttx: TextView? = null
    var txt_all_vidssList: TextView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var ll_sprtr_vids: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.activity_supprter_vid_list, container, false)
        context = SuperSupprterVidList()
        context1 = SuperSupprterVidList()
        customDialog = CustomDialog(activity)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        rv_all_videos = viewexp.findViewById<RecyclerView>(R.id.rv_all_videos)
        //        swipe_refresh = viewexp.findViewById(R.id.swipe_refresh);
        nodata = viewexp.findViewById<RelativeLayout>(R.id.nodata)
        spurt_tooltxttx = viewexp.findViewById<TextView>(R.id.spurt_tooltxttx)
        ll_sprtr_vids = viewexp.findViewById<LinearLayout>(R.id.ll_sprtr_vids)
        shimmerFrameLayout = viewexp.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        //        spurt_tooltxttx.setText("Super Supported Videos");
        spurt_tooltxttx?.visibility = View.GONE
        layoutManager_vidsList = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_all_videos?.layoutManager = layoutManager_vidsList
        //        rv_all_videos.setHasFixedSize(true);
        lEarnVids
        //        swipe_refresh.setOnRefreshListener(() -> {
        lEarnVids
        //            swipe_refresh.setRefreshing(false);
//        });
        return viewexp
    }

    private val lEarnVids: Unit
        get() {
            try {
                shimmerFrameLayout?.startShimmer()
                val url: String = Configs.BASE_URL2 + "my-ssupportvideos"
                RetrofitClient.getClient().sprsuprtvidList(
                    url, "application/json",
                    "Bearer $token"
                )
                    ?.enqueue(object : GlobalCallback<MySprSprtVidsPojo?>(rv_all_videos) {
                        @SuppressLint("SetTextI18n")
                       override fun onResponse(
                            call: Call<MySprSprtVidsPojo?>,
                            response: Response<MySprSprtVidsPojo?>
                        ) {
                            shimmerFrameLayout?.stopShimmer()
                            try {
                                if (response.body() != null) {
                                    val sprtvidsts: String ? = response.body()?.status
                                    if (sprtvidsts != null) {
                                        if (sprtvidsts.contains("0")) {
                                            nodata?.visibility = View.VISIBLE
                                        } else if (sprtvidsts.contains("1")) {
                                            nodata?.visibility = View.GONE
                                            val mySprtVids: List<MySuprSprtVidList> ?=
                                                response.body()?.videos
                                            if (mySprtVids != null) {
                                                if (mySprtVids.isEmpty()) {
                                                    nodata?.visibility = View.VISIBLE
                                                    shimmerFrameLayout?.visibility = View.GONE
                                                } else {
                                                    ll_sprtr_vids?.visibility = View.VISIBLE
                                                    shimmerFrameLayout?.visibility = View.GONE
                                                    adapter_vids_list =
                                                        SuprSuprtVidListAdapter(mySprtVids, activity!!)
                                                    rv_all_videos!!.adapter = adapter_vids_list
                                                }
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