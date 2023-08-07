package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CrsSctnTitleAdapter
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitle
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitlePojo

class CourseAddTtile : AppCompatActivity() {
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null
    var crs_idd: String? = null
    var edit_section_titlee_str: String? = null
    var rv_learn_imagess: RecyclerView? = null
    private var layoutManager_imgs: RecyclerView.LayoutManager? = null
    var nodata: RelativeLayout? = null
    var bundle: Bundle? = null
    var edit_section_titlee: EditText? = null
    var section_title_add: TextView? = null
    var catggryList: MutableList<SectionTitle>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crs_add_section_title)
        context = this@CourseAddTtile
        manager = SessionManager()
        token = manager?.getPreferences(this@CourseAddTtile, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        crs_idd = bundle?.getString("Course_id")
        rv_learn_imagess = findViewById(R.id.rv_learn_imagess)
        edit_section_titlee = findViewById(R.id.edit_section_titlee)
        section_title_add = findViewById(R.id.section_title_add)
        nodata = findViewById<RelativeLayout>(R.id.nodata)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer_title)
        catggryList = ArrayList<SectionTitle>()
        layoutManager_imgs = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_learn_imagess!!.layoutManager = layoutManager_imgs
        getsectiontitlee()
        section_title_add?.setOnClickListener(View.OnClickListener { v: View? ->
            edit_section_titlee_str = edit_section_titlee?.text.toString()
            if (edit_section_titlee_str == "" || edit_section_titlee_str!!.isEmpty()) {
                Toast.makeText(this@CourseAddTtile, "Enter something!", Toast.LENGTH_SHORT).show()
            } else {
                addscr_title()
            }
        })
    }

    private fun addscr_title() {
        shimmerFrameLayout?.startShimmer()
        section_title_add?.setEnabled(false)
        edit_section_titlee_str = edit_section_titlee?.text.toString()
        RetrofitClient.getClient()
            .add_sectiontitle(crs_idd, edit_section_titlee_str, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<String?>(edit_section_titlee) {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<String?>?, response: Response<String?>) {
                    shimmerFrameLayout?.stopShimmer()
                    try {
                        if (response.isSuccessful) {
                            edit_section_titlee?.setText("")
                            val takeRes = response.body()
                            if (takeRes != null && takeRes.contains("200")) {
                                val sectionTitle = SectionTitle()
                                sectionTitle.title = (edit_section_titlee_str)
                                catggryList!!.add(sectionTitle)
                                adapter_imgs!!.notifyDataSetChanged()
                                Toast.makeText(
                                    this@CourseAddTtile,
                                    "Added Successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Handle unsuccessful response
                            }
                        } else {
                            // Handle unsuccessful response
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        // Enable the button
                        section_title_add?.isEnabled = true
                    }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    super.onFailure(call, t)
                    section_title_add?.isEnabled = true
                    //                        Log.e("errorResp", "" + t);
                }
            })
    }

    private fun getsectiontitlee() {
        shimmerFrameLayout?.startShimmer()
        val url: String = Configs.BASE_URL2 + "course-sections/" + crs_idd
        RetrofitClient.getClient().learnImgstitle(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<SectionTitlePojo?>(rv_learn_imagess) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<SectionTitlePojo?>?,
                    response: Response<SectionTitlePojo?>
                ) {
                    shimmerFrameLayout?.stopShimmer()
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                catggryList = response.body()!!.data as MutableList<SectionTitle>?
                                if (catggryList!!.isEmpty()) {
                                    nodata?.visibility = View.VISIBLE
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_learn_imagess!!.visibility = View.GONE
                                } else {
                                    shimmerFrameLayout?.stopShimmer()
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_learn_imagess!!.visibility = View.VISIBLE
                                    adapter_imgs =
                                        CrsSctnTitleAdapter(catggryList!!, this@CourseAddTtile)
                                    rv_learn_imagess!!.adapter = adapter_imgs
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