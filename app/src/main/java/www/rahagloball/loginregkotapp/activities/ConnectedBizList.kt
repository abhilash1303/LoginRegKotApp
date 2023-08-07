package www.rahagloball.loginregkotapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ConnectedBizAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.conectedbiz.ConctedListPojo
import www.rahagloball.loginregkotapp.models.conectedbiz.ConnectlistItem

class ConnectedBizList : AppCompatActivity() {
    var rl_cbl: RelativeLayout? = null
    var rv_cbl: RecyclerView? = null
    var img_not_foundd_cbl: ImageView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var ass_iddd: String? = null
    var catggryList_biz_id: String? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connected_biz_list)
        manager = SessionManager()
        token = manager?.getPreferences(this@ConnectedBizList, Constants.USER_TOKEN_LRN)
        rl_cbl = findViewById<RelativeLayout>(R.id.rl_cbl)
        rv_cbl = findViewById<RecyclerView>(R.id.rv_cbl)
        img_not_foundd_cbl = findViewById<ImageView>(R.id.img_not_foundd_cbl)

//        rv_cbl.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_cbl!!.layoutManager = layoutManager
        rv_cbl!!.itemAnimator = DefaultItemAnimator()

//        Log.e("newbiszziddoncreate",catggryList_biz_id);
        getbizcnttedlist()
    }

    private fun getbizcnttedlist() {
        rl_cbl?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "connect"
        RetrofitClient.getClient().getcntbizz(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<ConctedListPojo?>(rv_cbl) {
             override   fun onResponse(
                 call: Call<ConctedListPojo?>,
                 response: Response<ConctedListPojo?>
             ) {
                 rl_cbl?.visibility = View.GONE

                    try {
                        val catggryList: List<ConnectlistItem>? = response.body()?.connectlist
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                img_not_foundd_cbl!!.visibility = View.VISIBLE
                            } else {
                                rv_cbl!!.visibility = View.VISIBLE
                                adapter = catggryList?.let { ConnectedBizAdapter(it, this@ConnectedBizList) }
                                rv_cbl!!.adapter = adapter
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
        startActivity(Intent(this@ConnectedBizList, HomeDemoActivityCtgry::class.java))
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
    }
}