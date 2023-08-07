package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.MyCrsSctnVidAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crsscnvidlist.CrsSctnVidllist
import www.rahagloball.loginregkotapp.models.crsscnvidlist.CrsSctnVidllistPojo

class TMyCourseVideos constructor() : AppCompatActivity() {
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null
    var crs_section_idd: String? = null
    var crs_idd: String? = null
    var edit_section_titlee_str: String? = null
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    var nodata: RelativeLayout? = null
    var bundle: Bundle? = null
    var edit_section_titlee: EditText? = null
    var section_title_add: TextView? = null
    var catggryList: List<CrsSctnVidllist>? = null
    var add_video_section: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crs_add_section_video)
        context = this@TMyCourseVideos
        manager = SessionManager()
        token = manager?.getPreferences(this@TMyCourseVideos, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        crs_section_idd = bundle?.getString("section_title_id")
        crs_idd = bundle?.getString("coure_iddrrrr")
        //        Log.e("crs_section_idd",crs_section_idd);
        rv_learn_imagess = findViewById<RecyclerView>(R.id.rv_learn_imagess)
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        add_video_section = findViewById<TextView>(R.id.add_video_section)
        add_video_section?.visibility = View.GONE
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer_title)
        catggryList = ArrayList<CrsSctnVidllist>()
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        rv_learn_imagess!!.setLayoutManager(layoutManager_imgs)
        getAllSctnVids(crs_idd, crs_section_idd)
    }

    private fun getAllSctnVids(crs_idd: String?, crs_section_idd: String?) {
        shimmerFrameLayout?.startShimmer()

//        crs_section_idd = bundle.getString("section_title_id");
//        crs_idd = bundle.getString("coure_iddrrrr");
        RetrofitClient.getClient().getallcrsvids(
            crs_idd, crs_section_idd, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<CrsSctnVidllistPojo?>(rv_learn_imagess) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(
                    call: Call<CrsSctnVidllistPojo?>,
                    response: Response<CrsSctnVidllistPojo?>
                ) {
                    shimmerFrameLayout?.stopShimmer()
                    try {
                        catggryList=response.body()?.data
                        if (catggryList!!.isEmpty()) {
                            nodata?.visibility = View.VISIBLE
                            shimmerFrameLayout?.visibility = View.GONE
                            rv_learn_imagess!!.visibility = View.GONE
                        } else {
                            shimmerFrameLayout?.stopShimmer()
                            shimmerFrameLayout?.visibility = View.GONE
                            rv_learn_imagess!!.visibility = View.VISIBLE
                            adapter_imgs = MyCrsSctnVidAdapter(catggryList!!, this@TMyCourseVideos)
                            rv_learn_imagess!!.adapter = adapter_imgs
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