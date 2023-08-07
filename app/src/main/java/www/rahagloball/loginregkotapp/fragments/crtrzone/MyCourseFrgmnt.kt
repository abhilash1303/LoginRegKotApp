package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.annotation.SuppressLint
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
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.MyCourseeAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.myciursesalll.MyCourseAll
import www.rahagloball.loginregkotapp.models.myciursesalll.MyCoursePojo

class MyCourseFrgmnt : Fragment() {
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    private lateinit var context: Context

    //    SwipeRefreshLayout swipe_refresh;
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var manager: SessionManager? = null
    var nodata_mycrs: RelativeLayout? = null
    var token: String? = null
    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.activity_mycourses, container, false)
        context = activity!!
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        //        swipe_refresh = viewexp.findViewById(R.id.swipe_refresh);
        rv_learn_imagess = viewexp.findViewById<RecyclerView>(R.id.rv_learn_imagess)
        shimmerFrameLayout = viewexp.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        toolbar = viewexp.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.visibility = View.GONE
        //        swipe_refresh = viewexp.findViewById(R.id.swipe_refresh);
        nodata_mycrs = viewexp.findViewById<RelativeLayout>(R.id.nodata_mycrs)
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess?.layoutManager = layoutManager_imgs
        lEarnImgs
        return viewexp
    }

    private val lEarnImgs: Unit
        get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-courses"
            RetrofitClient.getClient().getallmycrs(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<MyCoursePojo?>(rv_learn_imagess) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(call: Call<MyCoursePojo?>, response: Response<MyCoursePojo?>) {
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            if (response.body() != null) {
                                val svdvidres: String ? = response.body()?.status
                                if (svdvidres == "404") {
                                    nodata_mycrs?.visibility   = View.VISIBLE
                                } else if (svdvidres == "200") {
                                    val catggryList: List<MyCourseAll>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            shimmerFrameLayout?.visibility = View.GONE
                                            nodata_mycrs?.visibility = View.VISIBLE
                                            rv_learn_imagess!!.visibility = View.GONE
                                        } else {
                                            nodata_mycrs?.visibility  = View.GONE
                                            shimmerFrameLayout?.stopShimmer()
                                            shimmerFrameLayout?.visibility = View.GONE
                                            val uniqueCats: MutableList<MyCourseAll> =
                                                ArrayList<MyCourseAll>()
                                            val seenIds = HashSet<Int>()
                                            for (cat in catggryList) {
                                                if (cat.courseId?.let { seenIds.add(it) } == true) {
                                                    uniqueCats.add(cat)
                                                }
                                            }
                                            rv_learn_imagess!!.visibility = View.VISIBLE
                                            adapter_imgs = MyCourseeAdapter(uniqueCats, activity!!)
                                            rv_learn_imagess!!.adapter = adapter_imgs
                                        }
                                    }
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