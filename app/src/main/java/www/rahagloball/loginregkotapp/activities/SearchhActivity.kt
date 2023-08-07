package www.rahagloball.loginregkotapp.activities

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SrchVidListAdapter
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.LrnSrchVid.LearnSearchPojo
import www.rahagloball.loginregkotapp.models.LrnSrchVid.SearchVideo
import www.rahagloball.loginregkotapp.models.RetrofitClient

class SearchhActivity : AppCompatActivity() {
   private lateinit var token: String
   private lateinit var manager: SessionManager
   private lateinit var blurreg1: RelativeLayout
   private lateinit var videoList: RecyclerView
   private lateinit var rv_misc_vid: RecyclerView
   private lateinit var rvallvideos: RecyclerView
   private lateinit  var layoutManagervidsList: RecyclerView.LayoutManager
   private lateinit var rvlearnimagess: RecyclerView
   private lateinit var searcheddatastr: String
   private lateinit var bundle: Bundle
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchh)
        manager = SessionManager()
        token = manager.getPreferences(this@SearchhActivity, Constants.USER_TOKEN_LRN)
        bundle = intent.extras!!
        searcheddatastr = intent.getStringExtra("searchdata").toString()
        blurreg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        rvallvideos = findViewById<RecyclerView>(R.id.rv_all_videos)
        rvlearnimagess = findViewById<RecyclerView>(R.id.videoList)
        layoutManagervidsList =
            LinearLayoutManager(this@SearchhActivity, LinearLayoutManager.VERTICAL, false)
        rvallvideos.layoutManager = layoutManagervidsList
        getLEarnVids(searcheddatastr)
    }

    private fun getLEarnVids(edit_str: String?) {
        blurreg1.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "nationvideo/filter?search=" + edit_str
        RetrofitClient.getClient().SrchVidCrse(
            url, "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<LearnSearchPojo?>(rvallvideos) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(
                    call: Call<LearnSearchPojo?>,
                    response: Response<LearnSearchPojo?>
                ) {
                    blurreg1.visibility = View.GONE
                    try {
                        val srchres: String ? = response.body()?.status
                        if (srchres != null) {
                            if (srchres.contains("1")) {
                                val catggryList: List<SearchVideo>?=response.body()?.data
                                if (catggryList != null) {
                                    if (catggryList.isEmpty()) {
                                        rvallvideos.visibility = View.GONE
                                    } else {
                                        rvallvideos.visibility = View.VISIBLE
                                        adaptervidslist =
                                            SrchVidListAdapter(catggryList, this@SearchhActivity)
                                        rvallvideos.adapter = adaptervidslist
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
        private lateinit var adaptervidslist: RecyclerView.Adapter<*>
        private lateinit var adapterimgs: RecyclerView.Adapter<*>
    }
}