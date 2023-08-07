package www.rahagloball.loginregkotapp.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.BlogssAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getblogs.GetBlogs
import www.rahagloball.loginregkotapp.models.getblogs.GetBlogsPojo

class OpBlogsFrgmnt : Fragment() {
    var ll_profile_view: LinearLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    var nodata_blogs: RelativeLayout? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.ch_blogs_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        rv_learn_imagess = viewexp.findViewById<RecyclerView>(R.id.rv_learn_imagess)
        nodata_blogs = viewexp.findViewById<RelativeLayout>(R.id.nodata_blogs)
        shimmerFrameLayout = viewexp.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        layoutManager_imgs = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess?.layoutManager = layoutManager_imgs
        lEarnImgs
        return viewexp
    }

    private val lEarnImgs: Unit
        private get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "blogs"
            RetrofitClient.getClient().learnIBlgs(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<GetBlogsPojo?>(rv_learn_imagess) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<GetBlogsPojo?>,
                        response: Response<GetBlogsPojo?>
                    ) {
                        try {
                            val catggryList: List<GetBlogs>?= response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_learn_imagess!!.visibility = View.GONE
                                } else {
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_learn_imagess!!.visibility = View.VISIBLE
                                    adapter_imgs = activity?.let { BlogssAdapter(catggryList, it) }
                                    rv_learn_imagess!!.adapter = adapter_imgs
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