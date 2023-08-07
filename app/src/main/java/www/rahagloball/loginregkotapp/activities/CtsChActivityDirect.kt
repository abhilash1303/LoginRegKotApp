package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CtsChVideoAdapter
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.configuration.DownloadVideoAsyncTask
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient.getClient
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleChPojo
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleCutiesCh
import java.io.File

class CtsChActivityDirect : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var videoItemmList: ArrayList<SingleCutiesCh>
    private lateinit var cuteVideoAdapter: CtsChVideoAdapter
    private lateinit var vid_home: String
    private lateinit var textView123: TextView
    private lateinit var context: Context
    private lateinit var v_home: SingleCutiesCh
    private lateinit var intentt: Intent
    private lateinit var token: String
    private lateinit var ch_id_single: String
    private lateinit var manager: SessionManager

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuties_single)
        context = this@CtsChActivityDirect
        manager = SessionManager()
        token = manager.getPreferences(this@CtsChActivityDirect, Constants.USER_TOKEN_LRN)
        videoItemmList = ArrayList()
        textView123 = findViewById(R.id.textView123)
        viewPager2 = findViewById(R.id.cut_vwpagr)

        //for full-screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.setFormat(PixelFormat.TRANSLUCENT)
        intentt = intent
        val data = intentt.extras
        vid_home = intent.getStringExtra("video_id").toString()
        ch_id_single = intent.getStringExtra("ch_idddddstr").toString()
        v_home = data?.getSerializable("videoData") as SingleCutiesCh

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in position until videoItemmList.size) {
                    val fileName = videoItemmList[i].video
                    val file = File(applicationContext.cacheDir.toString() + "/" + fileName)
                    if (file.exists()) {
                        return
                    } else {
                        val asyncTask = DownloadVideoAsyncTask(applicationContext, fileName!!)
                        asyncTask.execute(Configs.BASE_URL21 + "images/pool/video/" + fileName)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })


//        viewPager2?.registerO/**/ nPageChangeCallback (object : OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in position until (videoItemmList as ArrayList<SingleCutiesCh>).size) {
//                    val fileName = (videoItemmList as ArrayList<SingleCutiesCh>)[i].video
//                    val file = File(applicationContext.cacheDir.toString() + "/" + fileName)
//                    if (file.exists()) {
//                        return
//                    } else {
//                        val async = DownloadVideoAsyncTask(
//                            applicationContext, fileName!!
//                        )
//                        async.execute(Configs.BASE_URL21 + "images/pool/video/" + fileName)
//                    }
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
        cuteVids
    }


    private val cuteVids: Unit
        get() {
            Log.e("TAG", ch_id_single) // Error log

            val url = Configs.BASE_URL2 + "cuties-videos/" + ch_id_single
            getClient().gtctssinglelist(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<SingleChPojo?>(textView123) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<SingleChPojo?>,
                        response: Response<SingleChPojo?>
                    ) {
                        try {
                            videoItemmList = response.body()?.data as ArrayList<SingleCutiesCh>
                            if (videoItemmList.isEmpty()) {


                            } else {
                                videoItemmList.add(SingleCutiesCh())
                                cuteVideoAdapter =
                                    CtsChVideoAdapter(videoItemmList, context, viewPager2)
                                viewPager2.adapter = cuteVideoAdapter
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
}