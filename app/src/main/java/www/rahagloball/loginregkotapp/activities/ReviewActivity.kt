package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ReviewwAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.reviewpoj.GetAssocReview
import www.rahagloball.loginregkotapp.models.reviewpoj.ReviewPojo

class ReviewActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var blur_reg1: RelativeLayout? = null
    var token: String? = null
    var assoc_id: String? = null
    var manager: SessionManager? = null
    var rvw_count: TextView? = null
    var bundle: Bundle? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        manager = SessionManager()
        token = manager?.getPreferences(this@ReviewActivity, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        assoc_id = bundle?.getString("ass_id")
        recyclerView = findViewById<RecyclerView>(R.id.recycler_associate)
        rvw_count = findViewById<TextView>(R.id.rvw_count)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)

//        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this@ReviewActivity)
        recyclerView!!.layoutManager = layoutManager
        getServiceprovidedData(assoc_id)
    }

    private fun getServiceprovidedData(ass_sid: String?) {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "get-associate-reviews/" + ass_sid
        RetrofitClient.getClient().getReviews(url, "application/json", "Bearer $token")
            .enqueue(object : GlobalCallback<ReviewPojo?>(recyclerView) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(call: Call<ReviewPojo?>, response: Response<ReviewPojo?>) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val rev_res: String ? = response.body()?.status
                        if (rev_res != null) {
                            if (rev_res.contains("1")) {
                                val mGetAssocReview: List<GetAssocReview>?=response.body()?.data
                                if (mGetAssocReview != null) {
                                    if (mGetAssocReview.isEmpty()) {
                                        recyclerView!!.visibility = View.GONE
                                    } else {
                                        adapter = ReviewwAdapter(mGetAssocReview, this@ReviewActivity)
                                        val i = adapter!!.itemCount
                                        rvw_count?.text = "($i)"
                                        recyclerView!!.adapter = adapter
                                        recyclerView!!.visibility = View.VISIBLE
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
        private var adapter: RecyclerView.Adapter<*>? = null
        var myOnClickListener: View.OnClickListener? = null
    }
}