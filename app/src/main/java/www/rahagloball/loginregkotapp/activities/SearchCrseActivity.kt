package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
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
import www.rahagloball.loginregkotapp.adapters.CourseDataAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crse_srch.CourseSearchPojo
import www.rahagloball.loginregkotapp.models.crse_srch.SerachCrse

//import www.nationlearnsraha.com.Configuration.Config;
//import www.nationlearnsraha.com.ConstantAndSession.Constants;
//import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
//import www.nationlearnsraha.com.Model.GlobalCallback;
//import www.nationlearnsraha.com.Model.Pojo.crse_srch.CourseSearchPojo;
//import www.nationlearnsraha.com.Model.Pojo.crse_srch.SerachCrse;
//import www.nationlearnsraha.com.Model.RetrofitClient;
//import www.nationlearnsraha.com.R;
//import www.nationlearnsraha.com.fragments.CourseDataAdapter;
class SearchCrseActivity : AppCompatActivity() {
    var token: String? = null
    var manager: SessionManager? = null
    var blur_reg1: RelativeLayout? = null
    var videoList: RecyclerView? = null
    var rv_misc_vid: RecyclerView? = null
    var rv_all_videos: RecyclerView? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var rv_learn_imagess: RecyclerView? = null
    var searched_data_str: String? = null
    var bundle: Bundle? = null
    var mis_creses: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchh)
        manager = SessionManager()
        token = manager?.getPreferences(this@SearchCrseActivity, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        searched_data_str = getIntent().getStringExtra("searchdata")
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        rv_learn_imagess = findViewById<RecyclerView>(R.id.videoList)
        mis_creses = findViewById<TextView>(R.id.mis_creses)
        mis_creses?.text = "Searched Courses"
        layoutManager_vidsList =
            LinearLayoutManager(this@SearchCrseActivity, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.layoutManager = layoutManager_vidsList
        getLEarnVids(searched_data_str)
    }

    private fun getLEarnVids(edit_str: String?) {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "bundle/courses/filter?search=" + edit_str
        RetrofitClient.getClient().SrchCrse(
            url, "application/json",
            "Bearer $token"
        )

            ?.enqueue(object : GlobalCallback<CourseSearchPojo?>(rv_all_videos) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<CourseSearchPojo?>,
                    response: Response<CourseSearchPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val srchres: String ? = response.body()?.status
                        if (srchres != null) {
                            if (srchres.contains("1")) {
                                val catggryList: List<SerachCrse>?=response.body()?.data
                                if (catggryList != null) {
                                    if (catggryList.isEmpty()) {
                                        rv_all_videos!!.visibility = View.GONE
                                    } else {
                                        rv_all_videos!!.visibility = View.VISIBLE
                                        adapter_vids_list =
                                            CourseDataAdapter(catggryList, this@SearchCrseActivity)
                                        rv_all_videos!!.adapter = adapter_vids_list
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
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
        private val adapter_imgs: RecyclerView.Adapter<*>? = null
    }
}