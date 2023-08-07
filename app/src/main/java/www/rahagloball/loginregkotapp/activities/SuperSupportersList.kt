package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
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
import www.rahagloball.loginregkotapp.adapters.SupportersListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.suprtrlistt.Supporter
import www.rahagloball.loginregkotapp.models.suprtrlistt.SupportersListPojo

class SuperSupportersList constructor() : AppCompatActivity() {
    var blur_reg_sprt: RelativeLayout? = null
    var nodata: RelativeLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supporters_list)
        manager = SessionManager()
        token = manager?.getPreferences(this@SuperSupportersList, Constants.USER_TOKEN_LRN)
        rv_supporters = findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = findViewById<RelativeLayout>(R.id.blur_reg_sprt)
        nodata = findViewById<RelativeLayout>(R.id.nodata_sprt)
        //        rv_supporters.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this@SuperSupportersList)
        rv_supporters!!.layoutManager = layoutManager
        rv_supporters!!.itemAnimator = DefaultItemAnimator()
        supprtList
    }

    private val supprtList: Unit
        get() {
            blur_reg_sprt?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "my-supporters"
            RetrofitClient.getClient().getSuprtrsLsit(url, "application/json", "Bearer " + token)
                ?.enqueue(object : GlobalCallback<SupportersListPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<SupportersListPojo?>?,
                        response: Response<SupportersListPojo?>
                    ) {
                        blur_reg_sprt?.visibility = View.GONE
                        try {
                            if (response.body() != null) {
                                val subscriptionList: List<Supporter>? =
                                    response.body()?.supporterList
                                if (subscriptionList != null) {
                                    if (subscriptionList.isEmpty()) {
                                        rv_supporters!!.visibility = View.GONE
                                        nodata?.visibility = View.VISIBLE
                                    } else {
                                        adapter = SupportersListAdapter(
                                            subscriptionList,
                                            this@SuperSupportersList
                                        )
                                        rv_supporters!!.adapter = adapter
                                        rv_supporters!!.visibility = View.VISIBLE
                                        nodata?.visibility = View.GONE
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
        var myOnClickListener: View.OnClickListener? = null
    }
}