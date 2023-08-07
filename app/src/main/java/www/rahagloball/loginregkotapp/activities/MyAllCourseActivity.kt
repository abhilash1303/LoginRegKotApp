package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
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
import www.rahagloball.loginregkotapp.adapters.MyCourseeAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.myciursesalll.MyCourseAll
import www.rahagloball.loginregkotapp.models.myciursesalll.MyCoursePojo

class MyAllCourseActivity : AppCompatActivity() {
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    var context: Context? = null

    //    SwipeRefreshLayout swipe_refresh;
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var manager: SessionManager? = null
    var token: String? = null
    var nodata_mycrs: RelativeLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycourses)
        context = MyAllCourseActivity()
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        manager = SessionManager()
        token = manager?.getPreferences(this@MyAllCourseActivity, Constants.USER_TOKEN_LRN)
        rv_learn_imagess = findViewById<RecyclerView>(R.id.rv_learn_imagess)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        //        swipe_refresh = findViewById(R.id.swipe_refresh);
        nodata_mycrs = findViewById<RelativeLayout>(R.id.nodata_mycrs)
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess!!.layoutManager = layoutManager_imgs
        lEarnImgs
        //        swipe_refresh.setOnRefreshListener(() -> {
//            getLEarnImgs();
//            swipe_refresh.setRefreshing(false);
//        });
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
                    override fun onResponse(call: Call<MyCoursePojo?>?, response: Response<MyCoursePojo?>) {
                        try {
                            if (response.body() != null) {
                                val svdvidres: String ? = response.body()?.status
                                if (svdvidres == "404") {
                                    nodata_mycrs?.visibility = View.VISIBLE
                                } else if (svdvidres == "200") {
                                    val catggryList: List<MyCourseAll>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            shimmerFrameLayout?.visibility = View.GONE
                                            rv_learn_imagess!!.visibility = View.GONE
                                            nodata_mycrs?.visibility = View.VISIBLE
                                        } else {
                                            val uniqueCats: MutableList<MyCourseAll> =
                                                ArrayList<MyCourseAll>()
                                            val seenIds = HashSet<Int>()
                                            for (cat in catggryList) {
                                                if (cat.courseId?.let { seenIds.add(it) } == true) {
                                                    uniqueCats.add(cat)
                                                }
                                            }
                                            shimmerFrameLayout?.stopShimmer()
                                            shimmerFrameLayout?.visibility = View.GONE
                                            nodata_mycrs?.visibility = View.GONE
                                            rv_learn_imagess!!.visibility = View.VISIBLE
                                            adapter_imgs =
                                                MyCourseeAdapter(uniqueCats, this@MyAllCourseActivity)
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