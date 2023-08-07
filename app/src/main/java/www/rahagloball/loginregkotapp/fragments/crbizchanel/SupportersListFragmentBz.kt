package www.rahagloball.loginregkotapp.fragments.crbizchanel

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.CrtrZoneActivity
import www.rahagloball.loginregkotapp.adapters.SupportersListAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.suprtrlistt.Supporter
import www.rahagloball.loginregkotapp.models.suprtrlistt.SupportersListPojo

class SupportersListFragmentBz : Fragment() {
    var blur_reg_sprt: RelativeLayout? = null
    var nodata: RelativeLayout? = null
    var token: String? = null
    var chanl_idtosend: String? = null
    var manager: SessionManager? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.activity_supporters_list, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        val activity: CrtrZoneActivity? = activity as CrtrZoneActivity?
//        if (activity != null) {
            chanl_idtosend = activity?.getMyData()
//        }
        rv_supporters = viewexp.findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = viewexp.findViewById<RelativeLayout>(R.id.blur_reg_sprt)
        nodata = viewexp.findViewById<RelativeLayout>(R.id.nodata_sprt)
        layoutManager = LinearLayoutManager(getActivity())
        rv_supporters?.layoutManager = layoutManager
        rv_supporters?.itemAnimator = DefaultItemAnimator()

//        getSupprtList();
        nodata?.visibility = View.VISIBLE
        return viewexp
    }

    //
    private val supprtList: Unit
        get() {
            blur_reg_sprt?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "my-supporters"
            RetrofitClient.getClient().getSuprtrsLsit(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<SupportersListPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                   override fun onResponse(
                        call: Call<SupportersListPojo?>,
                        response: Response<SupportersListPojo?>
                    ) {
                        blur_reg_sprt?.visibility = View.GONE
                        try {
                            val subscriptionList: List<Supporter>? =
                                response.body()?.supporterList
                            if (subscriptionList != null) {
                                if (subscriptionList.isEmpty()) {
                                    rv_supporters!!.visibility = View.GONE
                                    nodata?.visibility = View.VISIBLE
                                } else {
                                    adapter = SupportersListAdapter(subscriptionList, requireActivity())
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

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        var myOnClickListener: View.OnClickListener? = null
    }
}