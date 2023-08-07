package www.rahagloball.loginregkotapp.fragments.mychdtlsf
//import okhttp3.MediaType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R
class MyChHomeFrgmnt : Fragment() {
    var ll_profile_view: LinearLayout? = null
    var ss_txt: TextView? = null
    var tss_txt: TextView? = null
    var ass_txt: TextView? = null
    var ess_txt: TextView? = null
    var ss_pyouts: TextView? = null
    var ss_rvnue: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.my_ch_home_layout, container, false)
        ss_txt = viewexp.findViewById<TextView>(R.id.ss_txt)
        tss_txt = viewexp.findViewById<TextView>(R.id.tss_txt)
        ass_txt = viewexp.findViewById<TextView>(R.id.ass_txt)
        ess_txt = viewexp.findViewById<TextView>(R.id.ess_txt)
        ss_pyouts = viewexp.findViewById<TextView>(R.id.ss_pyouts)
        ss_rvnue = viewexp.findViewById<TextView>(R.id.ss_rvnue)
        return viewexp
    }
}