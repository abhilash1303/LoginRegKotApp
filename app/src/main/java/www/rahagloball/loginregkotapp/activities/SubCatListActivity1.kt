package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType
import android.annotation.SuppressLint
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
import www.rahagloball.loginregkotapp.adapters.SubCatAdapterProfileCnct
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.allconect.AllCnctFltrPojo
import www.rahagloball.loginregkotapp.models.allconect.ConnctData

class SubCatListActivity1 constructor() : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var blur_reg1: RelativeLayout? = null
    var img_not_foundd: ImageView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var Qid: String? = null
    var Catid: String? = null
    var adapter_nocity_cnct: SubCatAdapterProfileCnct? = null
    var bundle: Bundle? = null
    var token: String? = null
    var manager: SessionManager? = null
    @SuppressLint("ClickableViewAccessibility")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_cat_connect)
        manager = SessionManager()
        token = manager?.getPreferences(this@SubCatListActivity1, Constants.USER_TOKEN_LRN)
        recyclerView = findViewById<RecyclerView>(R.id.rv_biz)
        img_not_foundd = findViewById<ImageView>(R.id.img_not_foundd)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)

//        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        bundle = intent.extras
        Qid = bundle?.getString("Qid_Connect")
        Catid = bundle?.getString("Catid_Connect")
        getfilterall(Qid, Catid)
    }

    private fun getfilterall(subcattiid: String?, cat_Idd: String?) {
        blur_reg1?.visibility  = View.VISIBLE
        val url: String = (Configs.BASE_URL2 + "connect-filter?category=" + cat_Idd +
                "&subcategory=" + subcattiid + "&state=" + "" + "&city=" + "")
        RetrofitClient.getClient().filteallconect(
            url, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<AllCnctFltrPojo?>(recyclerView) {
             override   fun onResponse(call: Call<AllCnctFltrPojo?>, response: Response<AllCnctFltrPojo?>) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val catggryList: List<ConnctData>?=response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    recyclerView!!.visibility = View.GONE
                                    img_not_foundd!!.visibility = View.VISIBLE
                                } else {
                                    recyclerView!!.visibility = View.VISIBLE
                                    img_not_foundd!!.visibility = View.GONE
                                    adapter_nocity_cnct =
                                        SubCatAdapterProfileCnct(catggryList, this@SubCatListActivity1)
                                    recyclerView!!.adapter = adapter_nocity_cnct
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}