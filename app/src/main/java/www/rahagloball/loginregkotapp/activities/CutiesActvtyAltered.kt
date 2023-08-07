package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CuteVidAdptrHomeAltered
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.configuration.DownloadVideoAsyncTask
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojo
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojoItem
import java.io.File
import java.util.Random


class CutiesActvtyAltered : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var videoItemmList: List<CuteVidListPojoItem?>
    private lateinit var cuteVideoAdapter: CuteVidAdptrHomeAltered
    private lateinit var exoPlayerItems: ArrayList<ExoPlayerItem1>
    private lateinit var textView123: TextView
    private lateinit var context: Context
    private lateinit var token: String
    private lateinit var manager: SessionManager
    private lateinit var nodatacuties: RelativeLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var player: ExoPlayer
    private var currentPlayingPosition = 0
    private var isDialogShown = false
    private val language_str =
        arrayOf("Select", "Kannada", "Hindi", "Telugu", "Tamil", "Malayalam", "English")
    private lateinit var ivCspinnerlang: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuties)

        context = this
        manager = SessionManager()

        player = ExoPlayer.Builder(this).build()

        token = manager.getPreferences(this, Constants.USER_TOKEN_LRN)
        videoItemmList = ArrayList<CuteVidListPojoItem?>()

        textView123 = findViewById(R.id.textView123)
        viewPager2 = findViewById(R.id.cut_vwpagr)
        nodatacuties = findViewById(R.id.nodata_cuties)
        ivCspinnerlang = findViewById(R.id.ivCspinnerlang)

        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.btm_cuties

        val dataAdapter1 = ArrayAdapter(this, R.layout.custom_spiner_layout, language_str)
        dataAdapter1.setDropDownViewResource(R.layout.custom_spiner_layout)
        ivCspinnerlang.adapter = dataAdapter1

        ivCspinnerlang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                getCuteVids()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in position until videoItemmList.size) {
                    val fileName = videoItemmList[i]?.video
                    val file = File(applicationContext.cacheDir.toString() + "/" + fileName)
                    if (file.exists()) {
                        return
                    } else {
                        if (fileName != null) {
                            val asyncTask = DownloadVideoAsyncTask(applicationContext, fileName)
                            asyncTask.execute(Configs.BASE_URL21 + "images/pool/video/" + fileName)
                        }
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btm_dashbrd -> {
                    startActivity(Intent(applicationContext, HomeDemoActivityCtgry::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.btm_cuties -> true
                R.id.btm_home -> {
                    startActivity(Intent(this, WatchActivty::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.btm_exprt -> {
                    startActivity(Intent(this, LearnActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                R.id.btm_profle -> {
                    startActivity(Intent(this, SubCatListActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }

                else -> false
            }
        }

        // for full-screen
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        getCuteVids()
    }

//    private fun dpToPx(dp: Int): Int {
//        val density = resources.displayMetrics.density
//        return Math.round(dp * density)
//    }

//    override fun onRestart() {
//        super.onRestart()
//        isDialogShown = false
//    }

    private fun getCuteVids() {
        val url = Configs.BASE_URL2 + "cuties"
        RetrofitClient.getClient().gtcutevidlist(
            url, "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<CuteVidListPojo?>(textView123) {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<CuteVidListPojo?>,
                response: Response<CuteVidListPojo?>
            ) {
                try {
                    if (response.body() != null) {
                        val videoItemmList: ArrayList<CuteVidListPojoItem> =
                            response.body()!!.data
                        if (videoItemmList.isEmpty()) {
                                nodatacuties.visibility = View.VISIBLE
                        } else {
                                nodatacuties.visibility = View.GONE
                            videoItemmList.add(CuteVidListPojoItem())
                            cuteVideoAdapter = CuteVidAdptrHomeAltered(videoItemmList, context, viewPager2)
                            videoItemmList.shuffle(Random())
                            viewPager2.adapter = cuteVideoAdapter
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}


//
//class CutiesActvtyAltered : AppCompatActivity() {
//    private lateinit var viewPager2: ViewPager2
//    private lateinit var videoItemmList: ArrayList<CuteVidListPojoItem?>
//    private lateinit var cuteVideoAdapter: CuteVidAdptrHomeAltered
//    private var exoPlayerItems: ArrayList<ExoPlayerItem1> = ArrayList<ExoPlayerItem1>()
//    private lateinit var textView123: TextView
//    private lateinit var context: Context
//    private lateinit var token: String
//    private lateinit var manager: SessionManager
//    private lateinit var nodatacuties: RelativeLayout
//    private lateinit var bottomNavigationView: BottomNavigationView
//    private val currentPlayingPosition = 0
//    private lateinit var player: ExoPlayer
//    private var isDialogShown = false
//    private var language_str =
//        arrayOf("Select", "Kannada", "Hindi", "Telugu", "Tamil", "Malayalam", "English")
//    private lateinit var ivCspinnerlang: Spinner
//
//    @OptIn(DelicateCoroutinesApi::class)
//    protected override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_cuties)
//        context = this@CutiesActvtyAltered
//        manager = SessionManager()
//        player = ExoPlayer.Builder(this).build()
//        token = manager.getPreferences(this@CutiesActvtyAltered, Constants.USER_TOKEN_LRN)
//        videoItemmList = ArrayList<CuteVidListPojoItem?>()
//        textView123 = findViewById<TextView>(R.id.textView123)
//        viewPager2 = findViewById<ViewPager2>(R.id.cut_vwpagr)
//        nodatacuties = findViewById<RelativeLayout>(R.id.nodata_cuties)
//        ivCspinnerlang = findViewById<Spinner>(R.id.ivCspinnerlang)
//        bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
//        bottomNavigationView.selectedItemId = R.id.btm_cuties
//        val dataAdapter1: ArrayAdapter<String> = ArrayAdapter<String>(
//            applicationContext,
//            R.layout.custom_spiner_layout,
//            language_str
//        )
//        dataAdapter1.setDropDownViewResource(R.layout.custom_spiner_layout)
//        ivCspinnerlang.adapter = dataAdapter1
//        ivCspinnerlang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int, id: Long
//            ) {
//
//                cuteVids
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                for (i in position until (videoItemmList.size)) {
//                    val fileName = videoItemmList[i]?.video
//                    val file = File(applicationContext.cacheDir.toString() + "/" + fileName)
//                    if (file.exists()) {
//                        return
//                    } else {
//                        val asyncTask = DownloadVideoAsyncTask(applicationContext, fileName!!)
//                        asyncTask.downloadVideo(
//                            Configs.BASE_URL21 + "images/pool/video/" + fileName,
//                            object : DownloadVideoAsyncTask.DownloadCallback {
//                                override fun onDownloadComplete(success: Boolean) {
//                                    if (success) {
//                                        // Video downloaded successfully, perform any necessary tasks
//                                    }
//                                }
//                            }
//                        )
//                    }
//                }
//            }
//        })
//
//        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
//            when (item.itemId) {
//                R.id.btm_dashbrd -> {
//                    startActivity(
//                        Intent(applicationContext, HomeDemoActivityCtgry::class.java)
//                    )
//                    overridePendingTransition(0, 0)
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.btm_cuties ->
//                    return@setOnItemSelectedListener true
//
//                R.id.btm_home -> {
//                    startActivity(Intent(this@CutiesActvtyAltered, WatchActivty::class.java))
//                    overridePendingTransition(0, 0)
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.btm_exprt -> {
//                    startActivity(Intent(this@CutiesActvtyAltered, LearnActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    return@setOnItemSelectedListener true
//                }
//
//                R.id.btm_profle -> {
//                    startActivity(Intent(this@CutiesActvtyAltered, SubCatListActivity::class.java))
//                    overridePendingTransition(0, 0)
//                    return@setOnItemSelectedListener true
//                }
//            }
//            false
//        }
//        cuteVids
//    }
//
//    protected override fun onRestart() {
//        super.onRestart()
//        // Reset the flag to false when the activity is restarted
//        isDialogShown = false
//    }
//
//    private val cuteVids: Unit
//        get() {
//            val url: String = Configs.BASE_URL2 + "cuties"
//            RetrofitClient.getClient().gtcutevidlist(
//                url, "application/json",
//                "Bearer $token"
//            )?.enqueue(object : GlobalCallback<CuteVidListPojo?>(textView123) {
//                @SuppressLint("SetTextI18n")
//                override fun onResponse(
//                    call: Call<CuteVidListPojo?>,
//                    response: Response<CuteVidListPojo?>
//                ) {
//                    try {
//                        if (response.body() != null) {
//                            val videoItemmList: ArrayList<CuteVidListPojoItem>? =
//                                response.body()!!.data
//                            if (videoItemmList!!.isEmpty()) {
//                                nodatacuties.visibility = View.VISIBLE
//                            } else {
//                                nodatacuties.visibility = View.GONE
//                                videoItemmList.add(CuteVidListPojoItem())
//                                cuteVideoAdapter =
//                                    CuteVidAdptrHomeAltered(videoItemmList, context, viewPager2)
//                                videoItemmList.shuffle(Random())
//                                viewPager2.adapter = cuteVideoAdapter
//                            }
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//            })
//        }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        finish()
//    }
//}


//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                GlobalScope.launch(Dispatchers.IO) {
//                    for (i in position until videoItemmList.size) {
//                        val fileName = videoItemmList[i]?.video
//                        val file = File(applicationContext.cacheDir, fileName!!)
//                        if (file.exists()) {
//                            return@launch
//                        } else {
//                            val async = DownloadVideoAsyncTask(applicationContext, fileName)
//                            async.doInBackground(Configs.BASE_URL21 + "images/pool/video/" + fileName)
//                        }
//                    }
//                }
//            }
//        })


//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                val executor: ExecutorService = Executors.newFixedThreadPool(5)
//
//                val future: Future<Unit> = executor.submit<Unit> {
//                    for (i in position until videoItemmList.size) {
//                        val fileName = videoItemmList[i]?.video
//                        val file = File(applicationContext.cacheDir, fileName!!)
//                        if (file.exists()) {
//                            return@submit
//                        } else {
//                            val async = DownloadVideoAsyncTask(applicationContext, fileName)
//                            async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Configs.BASE_URL21 + "images/pool/video/" + fileName)
//                        }
//                    }
//                }
//
//                // You can handle the result or cancel the task if needed
//                // For example:
//                // val result = future.get()
//                // future.cancel(true) // Cancel the task if necessary
//
//                // Don't forget to shutdown the executor when you're done
//                executor.shutdown()
//            }
//        })