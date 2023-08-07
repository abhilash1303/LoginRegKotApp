package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ManageVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVid
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVidPojo

class MangeVidActivity : AppCompatActivity() {
    var blur_reg_sprt: RelativeLayout? = null
    var nodata: RelativeLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var mng_viddd: TextView? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var chanel_id_str: String? = null
    var pool_id_str: String? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mng_vid)
        manager = SessionManager()
        token = manager?.getPreferences(this@MangeVidActivity, Constants.USER_TOKEN_LRN)
        rv_supporters = findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = findViewById<RelativeLayout>(R.id.blur_reg_sprt)
        nodata = findViewById<RelativeLayout>(R.id.nodata_sprt)
        mng_viddd = findViewById<TextView>(R.id.mng_viddd)


//        rv_supporters.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this@MangeVidActivity)
        rv_supporters!!.layoutManager = layoutManager
        rv_supporters!!.itemAnimator = DefaultItemAnimator()
        createchannel()
        mng_viddd?.setOnClickListener(View.OnClickListener { v: View? ->
            try {
                val intent = Intent(this@MangeVidActivity, TCreatChanelActvty::class.java)
                startActivity(intent)
                if (pool_id_str == "" || pool_id_str!!.isEmpty()) {
                    Toast.makeText(this, "Create a Channel first!! ", Toast.LENGTH_SHORT).show()
                    val intent1 = Intent(this@MangeVidActivity, TCreatChanelActvty::class.java)
                    startActivity(intent1)
                } else {
                    val intent11 = Intent(this@MangeVidActivity, TUploadVid::class.java)
                    startActivity(intent11)
                }
                //
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        supprtList
    }

    //
    private val supprtList: Unit
        get() {
            blur_reg_sprt?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "my-managevideos"
            RetrofitClient.getClient().getMngVidLsit(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<MyManageVidPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                  override  fun onResponse(
                        call: Call<MyManageVidPojo?>,
                        response: Response<MyManageVidPojo?>
                    ) {
                        blur_reg_sprt?.visibility = View.GONE
                        try {
                            val subscriptionList: List<MyManageVid>?= response.body()?.data
                            if (subscriptionList != null) {
                                if (subscriptionList.isEmpty()) {
                                    rv_supporters!!.visibility = View.GONE
                                    nodata?.visibility = View.VISIBLE
                                } else {
                                    adapter =
                                        ManageVidListAdapter(subscriptionList, this@MangeVidActivity)
                                    rv_supporters!!.adapter = adapter
                                    rv_supporters!!.visibility = View.VISIBLE
                                    nodata?.visibility = View.GONE
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //
                    }
                })
        }

    private fun createchannel() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(mng_viddd) {
             override   fun onResponse(
                 call: Call<GetChanlPojo?>,
                 response: Response<GetChanlPojo?>
             ) {
                    try {
                        val catggryList: List<DataItem>?= response.body()?.data
                        if (catggryList != null) {
                            for (i in catggryList.indices) {
                                val dataItem: DataItem = catggryList[i]
                                chanel_id_str = dataItem.id
                                pool_id_str = dataItem.poolId
                                //                                poole_iddnulcheck.setText(pool_id_str);
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