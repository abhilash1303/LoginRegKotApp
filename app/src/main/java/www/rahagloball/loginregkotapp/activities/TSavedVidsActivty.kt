package www.rahagloball.loginregkotapp.activities


//import okhttp3.MediaType

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.SvdVidListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mysavedvidss.SavedVidPojo
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdVideo


class TSavedVidsActivty constructor() : AppCompatActivity() {
    var blur_reg_sprt: ImageView? = null
    var token: String? = null
    var manager: SessionManager? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null

    //    public static View.OnClickListener myOnClickListener;
    //    private RecyclerView.LayoutManager layoutManager;
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_layout_activty)
        manager = SessionManager()
        token = manager?.getPreferences(this@TSavedVidsActivty, Constants.USER_TOKEN_LRN)
        rv_supporters = findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = findViewById<ImageView>(R.id.nodata_sprt)
        //        nodata = findViewById(R.id.nodata_sprt);
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmer)
        layoutManager_vidsList =
            LinearLayoutManager(this@TSavedVidsActivty, LinearLayoutManager.VERTICAL, false)
        rv_supporters!!.layoutManager = layoutManager_vidsList
        svdVidList
    }

    //                                }
//                            }
    //
    private val svdVidList: Unit
        get() {
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "my-savedvideos"
            RetrofitClient.getClient().getSvdVidLsit(url, "application/json", "Bearer " + token)
                ?.enqueue(object : GlobalCallback<SavedVidPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<SavedVidPojo?>,
                        response: Response<SavedVidPojo?>
                    ) {
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            val svdvidres: String ? = response.body()?.status
                            if (svdvidres != null) {
                                if (svdvidres.contains("0")) {
                                    blur_reg_sprt!!.visibility =(View.VISIBLE)
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_supporters!!.visibility =(View.GONE)
                                } else {
                                    if (svdvidres.contains("1")) {
                                        val subscriptionList: List<SvdVideo> ? = response.body()?.videos
                                        if (subscriptionList != null) {
                                            if (subscriptionList.isEmpty()) {
                                                shimmerFrameLayout?.visibility = View.GONE
                                                blur_reg_sprt!!.visibility =(View.VISIBLE)
                                                rv_supporters!!.visibility =(View.GONE)
                                            } else {
                                                shimmerFrameLayout?.stopShimmer()
                                                shimmerFrameLayout?.visibility = View.GONE
                                                rv_supporters!!.visibility =(View.VISIBLE)
                                                adapter_vids_list =
                                                    SvdVidListAdapter(subscriptionList, this@TSavedVidsActivty)
                                                rv_supporters!!.adapter = adapter_vids_list
                                            }
                                        }
                                    }
                                }
                            }
                            //                                }
//                            }
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