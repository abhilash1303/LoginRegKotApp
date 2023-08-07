package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
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
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVid
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVidPojo

class CrtrMangVidFrgmnt : Fragment() {
    var blur_reg_sprt: RelativeLayout? = null
    var nodata: RelativeLayout? = null
    var token: String? = null
    var manager: SessionManager? = null
    var mng_viddd: TextView? = null
    var rv_supporters: RecyclerView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    //    Toolbar toolbar;
    var chanel_id_str: String? = null
    var pool_id_str: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.activity_mng_vid, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        rv_supporters = viewexp.findViewById<RecyclerView>(R.id.rv_supporters)
        blur_reg_sprt = viewexp.findViewById<RelativeLayout>(R.id.blur_reg_sprt)
        nodata = viewexp.findViewById<RelativeLayout>(R.id.nodata_sprt)
        mng_viddd = viewexp.findViewById<TextView>(R.id.mng_viddd)
        //        toolbar = viewexp.findViewById(R.id.toolbar);

//        toolbar.visibility = View.GONE;

//        rv_supporters.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(activity)
        rv_supporters?.layoutManager = layoutManager
        rv_supporters?.itemAnimator = DefaultItemAnimator()
        supprtList
        return viewexp
    }

    //
    private val supprtList: Unit
        private get() {
            blur_reg_sprt?.visibility  = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "my-managevideos"
            RetrofitClient.getClient().getMngVidLsit(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<MyManageVidPojo?>(rv_supporters) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
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
                                    adapter = ManageVidListAdapter(subscriptionList, activity!!)
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