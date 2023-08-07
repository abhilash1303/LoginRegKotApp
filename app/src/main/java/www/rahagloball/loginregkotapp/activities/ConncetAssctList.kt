package www.rahagloball.loginregkotapp.activities

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

class ConncetAssctList : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var adapter_nocity_cnct: SubCatAdapterProfileCnct? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val layoutManager_city_biz: RecyclerView.LayoutManager? = null
    var blur_reg1: RelativeLayout? = null
    var img_not_foundd: ImageView? = null
    var ctgry_cnct_str: String? = null
    var subctgry_cnct_str: String? = null
    var pin_cnct_str: String? = null
    var cty_cnct_str: String? = null
    var state_ctgry_cnct_str: String? = null
    var bundle: Bundle? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cnct_ast_list_layout)
        manager = SessionManager()
        token = manager?.getPreferences(this@ConncetAssctList, Constants.USER_TOKEN_LRN)
        recyclerView = findViewById<RecyclerView>(R.id.rv_biz)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        img_not_foundd = findViewById<ImageView>(R.id.img_not_foundd)

//        recyclerView.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        bundle = intent.extras
        ctgry_cnct_str = bundle?.getString("cat_Idd_cnct")
        subctgry_cnct_str = bundle?.getString("subcattiid_cnct")
        state_ctgry_cnct_str = bundle?.getString("stateiid_cnct")
        cty_cnct_str = bundle?.getString("cityIdd_cnct")
        pin_cnct_str = bundle?.getString("pinIdd_cnct")
        getfilterall(
            ctgry_cnct_str,
            subctgry_cnct_str,
            state_ctgry_cnct_str,
            cty_cnct_str,
            pin_cnct_str
        )
    }

    private fun getfilterall(
        cat_Idd: String?,
        subcattiid: String?,
        stateiid: String?,
        cityIdd: String?,
        pinIdd: String?
    ) {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "connect-filter?category=" + cat_Idd +
                "&subcategory=" + subcattiid + "&state=" + stateiid + "&city=" + cityIdd + "&pincode=" + pinIdd
        RetrofitClient.getClient().filteallconect(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<AllCnctFltrPojo?>(recyclerView) {
               override fun onResponse(
                   call: Call<AllCnctFltrPojo?>,
                   response: Response<AllCnctFltrPojo?>
               ) {
                   blur_reg1?.visibility = View.GONE
                    try {
                        val catggryList: List<ConnctData>? = response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                recyclerView!!.visibility = View.GONE
                                img_not_foundd!!.visibility = View.VISIBLE
                            } else {
                                recyclerView!!.visibility = View.VISIBLE
                                img_not_foundd!!.visibility = View.GONE
                                adapter_nocity_cnct =
                                    SubCatAdapterProfileCnct(catggryList, this@ConncetAssctList)
                                recyclerView!!.adapter = adapter_nocity_cnct
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}