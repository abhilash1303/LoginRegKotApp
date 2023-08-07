package www.rahagloball.loginregkotapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.adapters.MyCourseeAdapter1
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mychanldtls.CourseMychDtls
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo


class ChCourseFrgmnt : Fragment() {
    private lateinit  var rv_learn_imagess: RecyclerView
    private lateinit var layoutManager_imgs: RecyclerView.LayoutManager
    private lateinit var context: Context

    //    SwipeRefreshLayout swipe_refresh;
    private lateinit  var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var manager: SessionManager
    private lateinit var token: String
    private lateinit var chanl_idtosend: String
    private lateinit var nodata_mycrs: RelativeLayout
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.activity_mycourses, container, false)
        manager = SessionManager()
        token = activity?.let { manager.getPreferences(it, Constants.USER_TOKEN_LRN) }.toString()
        val activity: ChannelProfileActivity? = activity as ChannelProfileActivity?
        chanl_idtosend = activity?.getMyChData().toString()
        rv_learn_imagess = viewexp.findViewById<RecyclerView>(R.id.rv_learn_imagess)
        shimmerFrameLayout = viewexp.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        toolbar = viewexp.findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility = View.GONE
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        nodata_mycrs = viewexp.findViewById<RelativeLayout>(R.id.nodata_mycrs)
        layoutManager_imgs = LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess.layoutManager = layoutManager_imgs
        getmychdtlssss(chanl_idtosend)
        return viewexp
    }

    fun getmychdtlssss(chanl_idtosend: String?) {
        val url: String = Configs.BASE_URL2 + "channels/" + chanl_idtosend
        RetrofitClient.getClient().getmychdtls(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<MyChnlDtlsPojo?>(rv_learn_imagess) {
                override fun onResponse(call: Call<MyChnlDtlsPojo?>, response: Response<MyChnlDtlsPojo?>) {
                    try {
                        if (response.body() != null) {
                            val catggryList: List<CourseMychDtls>? = response.body()!!.course
                            if (catggryList?.isEmpty() == true) {
                                shimmerFrameLayout.visibility = View.GONE
                                rv_learn_imagess.visibility = View.GONE
                                nodata_mycrs.visibility = View.VISIBLE
                            } else {
                                val uniqueCats: MutableList<CourseMychDtls> =
                                    ArrayList<CourseMychDtls>()
                                val seenIds = HashSet<String>()
                                if (catggryList != null) {
                                    for (cat in catggryList) {
                                        if (cat.id?.let { seenIds.add(it) } == true) {
                                            uniqueCats.add(cat)
                                        }
                                    }
                                }
                                shimmerFrameLayout.stopShimmer()
                                shimmerFrameLayout.visibility = View.GONE
                                nodata_mycrs.visibility = View.GONE
                                rv_learn_imagess.visibility = View.VISIBLE
                                adapter_imgs = MyCourseeAdapter1(uniqueCats, activity!!)
                                rv_learn_imagess.adapter = adapter_imgs
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    companion object {
        private var adapter_imgs: RecyclerView.Adapter<*>? = null
    }
}