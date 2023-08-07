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
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CrtrRnkngAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crtrreanking.CrDatum
import www.rahagloball.loginregkotapp.models.crtrreanking.CrtrRnkingPojo

//import www.nationlearnsraha.com.Configuration.Config;
//import www.nationlearnsraha.com.ConstantAndSession.Constants;
//import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
//import www.nationlearnsraha.com.Model.GlobalCallback;
//import www.nationlearnsraha.com.Model.Pojo.crtrreanking.CrDatum;
//import www.nationlearnsraha.com.Model.Pojo.crtrreanking.CrtrRnkingPojo;
//import www.nationlearnsraha.com.Model.RetrofitClient;
//import www.nationlearnsraha.com.R;
//import www.nationlearnsraha.com.mainpage.adapters.CrtrRnkngAdapter;
class TcrtrRnkingActivity constructor() : AppCompatActivity() {
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var rv_all_videos: RecyclerView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var blur_reg1: RelativeLayout? = null
    var cr_nodata: RelativeLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcrtr_rnking)
        manager = SessionManager()
        token = manager?.getPreferences(this, Constants.USER_TOKEN_LRN)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        cr_nodata = findViewById<RelativeLayout>(R.id.cr_nodata)
        layoutManager_vidsList = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.layoutManager = layoutManager_vidsList
        lCrtrRnkingVids
    }

    private val lCrtrRnkingVids: Unit
        get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "creater-ranking"
            RetrofitClient.getClient().crtrrnkingg(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CrtrRnkingPojo?>(rv_all_videos) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<CrtrRnkingPojo?>,
                        response: Response<CrtrRnkingPojo?>
                    ) {
                        blur_reg1?.visibility = View.GONE
                        try {
                            val crtrres: String ? = response.body()?.status
                            if (crtrres != null) {
                                if (crtrres.contains("1")) {
                                    val catggryList: List<CrDatum>?=response.body()?.data
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            cr_nodata?.visibility = View.VISIBLE
                                            rv_all_videos!!.visibility = View.GONE
                                        } else {
                                            cr_nodata?.visibility = View.GONE
                                            rv_all_videos!!.visibility = View.VISIBLE
                                            adapter_vids_list =
                                                CrtrRnkngAdapter(catggryList, this@TcrtrRnkingActivity)
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
    }
}