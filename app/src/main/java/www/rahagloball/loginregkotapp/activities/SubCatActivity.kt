package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SubCaetgoryAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.subcatgry.DataItem
import www.rahagloball.loginregkotapp.models.subcatgry.SubCatgtyPojo

class SubCatActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    //    RelativeLayout blur_reg1;
    var manager: SessionManager? = null
    var token: String? = null
    var Qid: String? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_subcatgry_acticity)
        manager = SessionManager()
        token = manager?.getPreferences(this@SubCatActivity, Constants.USER_TOKEN_LRN)
        recyclerView = findViewById<RecyclerView>(R.id.rv_subcatgories)
        //        blur_reg1 = findViewById(R.id.blur_reg1);
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)

//        recyclerView.setHasFixedSize(true);
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        val bundle: Bundle? = intent.extras
        Qid = bundle?.getString("Qid")
        getsubCatgrylst(Qid)
    }

    private fun getsubCatgrylst(id: String?) {

//        blur_reg1.visibility = View.VISIBLE;
        shimmerFrameLayout?.startShimmer()
        val url: String = Configs.BASE_URL2 + "sub-category/" + id
        token = manager?.getPreferences(this@SubCatActivity, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient().subcatrgotyy_list(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<SubCatgtyPojo?>(recyclerView) {
             override   fun onResponse(
                 call: Call<SubCatgtyPojo?>,
                 response: Response<SubCatgtyPojo?>
             ) {

//                        blur_reg1.visibility = View.GONE;

//                        String subcat_list ? = response.body()?.toString();
//                        Log.e("subcat_list", subcat_list);
                    shimmerFrameLayout?.stopShimmer()
                    try {
                        val catggryList: List<DataItem>?= response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                            } else {

                    //                                shimmerFrameLayout?.stopShimmer();
                                shimmerFrameLayout?.visibility = View.GONE
                                recyclerView!!.visibility = View.VISIBLE
                                adapter = SubCaetgoryAdapter(catggryList, this@SubCatActivity)
                                recyclerView!!.adapter = adapter
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        var myOnClickListener: View.OnClickListener? = null
    }
}