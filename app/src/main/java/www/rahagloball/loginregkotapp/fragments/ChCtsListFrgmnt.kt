package www.rahagloball.loginregkotapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.adapters.CuteListChAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleChPojo
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleCutiesCh

class ChCtsListFrgmnt : Fragment() {
    var ll_profile_view: LinearLayout? = null
    private var layoutManager_cuties: RecyclerView.LayoutManager? = null
    var emptyElement_cts: ImageView? = null
    var blur_reg1_cts: RelativeLayout? = null
    var rv_chcutiess: RecyclerView? = null
    var manager: SessionManager? = null
    var token: String? = null
    var ChCtsIdSStr: String? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_vid_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        val activity: ChannelProfileActivity? = activity as ChannelProfileActivity?
        if (activity != null) {
            ChCtsIdSStr = activity.getMyChData()
        }
        rv_chcutiess = viewexp.findViewById<RecyclerView>(R.id.rv_chcutiess)
        emptyElement_cts = viewexp.findViewById<ImageView>(R.id.emptyElement_cts)
        blur_reg1_cts = viewexp.findViewById<RelativeLayout>(R.id.blur_reg1_cts)
        shimmerFrameLayout = viewexp.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        layoutManager_cuties = GridLayoutManager(getActivity(), 3)
        rv_chcutiess?.setLayoutManager(layoutManager_cuties)
        cuteVids
        return viewexp
    }//                        blur_reg1_cts.visibility = View.GONE;

    //        blur_reg1_cts.visibility = View.VISIBLE;
    private val cuteVids: Unit
        private get() {

//        blur_reg1_cts.visibility = View.VISIBLE;
            shimmerFrameLayout?.startShimmer()
            val url: String = Configs.BASE_URL2 + "cuties-videos/" + ChCtsIdSStr
            RetrofitClient.getClient().gtctssinglelist(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<SingleChPojo?>(rv_chcutiess) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<SingleChPojo?>,
                        response: Response<SingleChPojo?>
                    ) {

//                        blur_reg1_cts.visibility = View.GONE;
                        shimmerFrameLayout?.stopShimmer()
                        try {
                            val catggryList: List<SingleCutiesCh>?=response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    rv_chcutiess!!.visibility = View.GONE
                                    shimmerFrameLayout?.visibility = View.GONE
                                    emptyElement_cts!!.visibility = View.VISIBLE
                                } else {
                                    shimmerFrameLayout?.visibility = View.GONE
                                    rv_chcutiess!!.visibility = View.VISIBLE
                                    adapter_cuties_list = CuteListChAdapter(catggryList, activity!!)
                                    rv_chcutiess!!.adapter = adapter_cuties_list
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    companion object {
        private var adapter_cuties_list: RecyclerView.Adapter<*>? = null
    }
}