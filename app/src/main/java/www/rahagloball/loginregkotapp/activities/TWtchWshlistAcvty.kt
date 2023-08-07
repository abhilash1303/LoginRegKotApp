package www.rahagloball.loginregkotapp.activities


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.WtchWishListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.watchwshlist.WtchWshLstVideo
import www.rahagloball.loginregkotapp.models.watchwshlist.WtchWshlstPojo

class TWtchWshlistAcvty : AppCompatActivity() {
    var blur_reg_sprt: RelativeLayout? = null
    var nodata: RelativeLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var tool_txtxxx: TextView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_layout_activty)
        manager = SessionManager()
        token = manager?.getPreferences(this@TWtchWshlistAcvty, Constants.USER_TOKEN_LRN)
        rv_supporters = findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = findViewById<RelativeLayout>(R.id.blur_reg_sprt)
        nodata = findViewById<RelativeLayout>(R.id.nodata_sprt)
        tool_txtxxx = findViewById<TextView>(R.id.tool_txtxxx)
        tool_txtxxx?.text = "Watch Wishlist"
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        layoutManager_vidsList =
            LinearLayoutManager(this@TWtchWshlistAcvty, LinearLayoutManager.VERTICAL, false)
        rv_supporters!!.layoutManager = layoutManager_vidsList
        svdVidList
    }


    private val svdVidList: Unit
        private get() {

//        blur_reg_sprt.visibility = View.VISIBLE;
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-watchwishlist"
            RetrofitClient.getClient().getwwVidLsit(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<WtchWshlstPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                  override  fun onResponse(
                        call: Call<WtchWshlstPojo?>,
                        response: Response<WtchWshlstPojo?>
                    ) {

                        shimmerFrameLayout?.stopShimmer()
                        try {
                            val wwvidres: String? = response.body()?.status
                            if (wwvidres != null) {
                                if (wwvidres.contains("1")) {
                                    val wwishlist: List<WtchWshLstVideo> ? = response.body()?.videos
                                    if (wwishlist != null) {
                                        if (wwishlist.isEmpty()) {
                                            rv_supporters!!.visibility = View.GONE
                                            nodata?.visibility = View.VISIBLE
                                        } else {
                                            shimmerFrameLayout?.visibility = View.GONE
                                            rv_supporters!!.visibility = View.VISIBLE
                                            adapter_vids_list =
                                                WtchWishListAdapter(wwishlist, this@TWtchWshlistAcvty)
                                            rv_supporters!!.adapter = adapter_vids_list
                                            rv_supporters!!.visibility = View.VISIBLE
                                            nodata?.visibility = View.GONE
                                        }
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //
                    }
                })
        }

    companion object {
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
    }
}