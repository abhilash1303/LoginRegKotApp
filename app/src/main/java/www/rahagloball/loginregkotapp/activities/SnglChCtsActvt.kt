package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SnglChCtsAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.chctsfront.CtsSingleChPojo
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsChId
import www.rahagloball.loginregkotapp.models.cutiesss.DataItemCute

class SnglChCtsActvt : AppCompatActivity() {
    var viewPager2: ViewPager2? = null
    var videoItemmList: MutableList<FrntCtsChId>? = null
    var cuteVideoAdapter: SnglChCtsAdapter? = null
    var vid_home: String? = null
    var textView123: TextView? = null
    var context: Context? = null
    var v_home: DataItemCute? = null
    var intentt: Intent? = null
    var token: String? = null

    //    ImageView gobacku;
    var manager: SessionManager? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuties)
        context = this@SnglChCtsActvt
        manager = SessionManager()
        token = manager?.getPreferences(this@SnglChCtsActvt, Constants.USER_TOKEN_LRN)
        videoItemmList = ArrayList<FrntCtsChId>()
        textView123 = findViewById<TextView>(R.id.textView123)
        viewPager2 = findViewById<ViewPager2>(R.id.cut_vwpagr)
        //        gobacku = findViewById(R.id.gobacku);

        //for full-screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.setFormat(PixelFormat.TRANSLUCENT)
        intentt = intent
        val data: Bundle? = intentt?.extras
        vid_home = intent.getStringExtra("video_id")
        //        Log.e("vid_homeSinglects", vid_home);

//        v_home = (DataItemCute) data.getSerializable("videoData");

//        String vid_cuttt = Config.BASE_URL21 + "images/pool/video/" + v_home.getVideo();
//        Log.e("vid_cuttt", vid_cuttt);

//        videoView.setVideoPath(vid_cuttt);
        cuteVids

//        gobacku.setOnClickListener(view -> {
//
//            finish();
//        });
    }//                                rv_cuties.visibility = View.GONE;//                        blur_reg1.visibility = View.GONE;

    //        blur_reg1.visibility = View.VISIBLE;
    private val cuteVids: Unit
        private get() {

//        blur_reg1.visibility = View.VISIBLE;
            vid_home = intent.getStringExtra("video_id")
            val url: String = Configs.BASE_URL2 + "cuties-videos/" + vid_home
            RetrofitClient.getClient().snglechcts22(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CtsSingleChPojo?>(textView123) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<CtsSingleChPojo?>,
                        response: Response<CtsSingleChPojo?>
                    ) {

//                        blur_reg1.visibility = View.GONE;
                        try {
                            videoItemmList = (response.body()?.data as MutableList<FrntCtsChId>?)!!
                            if (videoItemmList!!.isEmpty()) {

//                                rv_cuties.visibility = View.GONE;
                            } else {
                                videoItemmList!!.add(FrntCtsChId())
                                cuteVideoAdapter =
                                    context?.let {
                                        viewPager2?.let { it1 ->
                                            SnglChCtsAdapter(videoItemmList!!,
                                                it, it1
                                            )
                                        }
                                    }
                                viewPager2?.adapter = cuteVideoAdapter
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }
}