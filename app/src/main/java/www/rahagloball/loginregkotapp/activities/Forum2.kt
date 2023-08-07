package www.rahagloball.loginregkotapp.activities

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
import www.rahagloball.loginregkotapp.adapters.ForumAdapter2
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.alltranxx.AllTranxPojo
import www.rahagloball.loginregkotapp.models.alltranxx.AllTranxx

class Forum2 : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var layoutManager: LinearLayoutManager? = null
    var bar: RelativeLayout? = null
    var nodata_tranxxx: RelativeLayout? = null
    var manager: SessionManager? = null
    var token: String? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum1)
        manager = SessionManager()
        token = manager?.getPreferences(this@Forum2, Constants.USER_TOKEN_LRN)
        recyclerView = findViewById<View>(R.id.listView) as RecyclerView?
        bar = findViewById<RelativeLayout>(R.id.progressBar)
        nodata_tranxxx = findViewById<RelativeLayout>(R.id.nodata_tranxxx)
        //        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.stackFromEnd = true
        layoutManager!!.reverseLayout = true
        recyclerView!!.layoutManager = layoutManager
        forumlist()
    }

    fun forumlist() {
        bar?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "all-transactions"
        RetrofitClient.getClient().forum2(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<AllTranxPojo?>(recyclerView) {
              override  fun onResponse(
                  call: Call<AllTranxPojo?>,
                  response: Response<AllTranxPojo?>
              ) {
                  bar?.visibility =(View.GONE)
                    try {
                        val res: String? = response.body()?.status
                        if (res != null) {
                            if (res.contains("0")) {
                                nodata_tranxxx?.visibility =(View.VISIBLE)
                            } else if (res.contains("1")) {
                                nodata_tranxxx?.visibility =(View.GONE)
                                val allProducts: List<AllTranxx>? = response.body()?.data
                                if (allProducts != null) {
                                    if (allProducts.isEmpty()) {
                                        nodata_tranxxx?.visibility =(View.VISIBLE)
                                    } else {
                                        nodata_tranxxx?.visibility =(View.GONE)
                                        adapter = ForumAdapter2(allProducts, this@Forum2)
                                        recyclerView!!.adapter = adapter
                                        recyclerView!!.visibility = View.VISIBLE
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
        private var adapter: RecyclerView.Adapter<*>? = null
    }
}