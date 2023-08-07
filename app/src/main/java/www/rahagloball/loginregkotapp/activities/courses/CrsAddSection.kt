package www.rahagloball.loginregkotapp.activities.courses

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import www.rahagloball.loginregkotapp.adapters.CrsSectionAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.usercrs.UserCrs
import www.rahagloball.loginregkotapp.models.usercrs.UserCrsPojo

class CrsAddSection : AppCompatActivity() {
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    var nodata: RelativeLayout? = null
    var add_courseee: TextView? = null
    var add_ques: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crs_add_section)
        context = this@CrsAddSection
        manager = SessionManager()
        token = manager?.getPreferences(this@CrsAddSection, Constants.USER_TOKEN_LRN)
        rv_learn_imagess = findViewById<RecyclerView>(R.id.rv_learn_imagess)
        add_courseee = findViewById<TextView>(R.id.add_courseee)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        add_ques = findViewById<TextView>(R.id.add_ques)
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess!!.layoutManager = layoutManager_imgs
        lEarnImgs
        add_courseee?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(context, UpoadCourse::class.java)
            startActivity(intent)
        })
        add_ques?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(context, AddQuestionActivty::class.java)
            startActivity(intent)
        })
    }

    private val lEarnImgs: Unit
        private get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "myall-courses"
            RetrofitClient.getClient().getsecvids(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<UserCrsPojo?>(rv_learn_imagess) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(call: Call<UserCrsPojo?>?, response: Response<UserCrsPojo?>) {
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            if (response.body() != null) {
                                val resss: String ? = response.body()?.status
                                if (resss == "200") {
                                    val catggryList: List<UserCrs>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            nodata?.visibility = View.VISIBLE
                                            shimmerFrameLayout?.visibility = View.GONE
                                            rv_learn_imagess!!.visibility = View.GONE
                                        } else {
                                            shimmerFrameLayout?.stopShimmer()
                                            shimmerFrameLayout?.visibility = View.GONE
                                            rv_learn_imagess!!.visibility = View.VISIBLE
                                            adapter_imgs =
                                                catggryList?.let {
                                                    CrsSectionAdapter(
                                                        it,
                                                        this@CrsAddSection
                                                    )
                                                }
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