package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.CrtrZoneActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.ovrlstcks.LtstVidsStackFrgmnt
import www.rahagloball.loginregkotapp.fragments.ovrlstcks.TrndVidsStackFrgmnt
import www.rahagloball.loginregkotapp.fragments.ovrlstcks.TtlVidsStackFrgmnt
import www.rahagloball.loginregkotapp.fragments.ovrlstcks.VidsVwsStackFrgmnt

class CrtrVidStatsFragmnet : Fragment() {
    var token: String? = null
    var chanl_idtosend: String? = null
    var manager: SessionManager? = null
    var tabLayout: RelativeLayout? = null
    var tab_1_ovrvw: TextView? = null
    var tab_2_gnder: TextView? = null
    var tab_3_demogrfics: TextView? = null
    var tab_4_earn: TextView? = null
    var tab_5_age: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.ovrallstats_layout, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        tabLayout = viewexp.findViewById<RelativeLayout>(R.id.tab_layout)
        tab_1_ovrvw = viewexp.findViewById<TextView>(R.id.tab_1_ovrvw)
        tab_2_gnder = viewexp.findViewById<TextView>(R.id.tab_2_gnder)
        tab_3_demogrfics = viewexp.findViewById<TextView>(R.id.tab_3_demogrfics)
        tab_4_earn = viewexp.findViewById<TextView>(R.id.tab_4_earn)
        val activity: CrtrZoneActivity? = activity as CrtrZoneActivity?
        chanl_idtosend = activity?.getMyData()
        tab_1_ovrvw?.setTextColor(Color.WHITE)
        tab_2_gnder?.setTextColor(Color.BLACK)
        tab_3_demogrfics?.setTextColor(Color.BLACK)
        tab_4_earn?.setTextColor(Color.BLACK)
        tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_selected_bg)
        tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
        updateTabContent(TtlVidsStackFrgmnt())
        tab_1_ovrvw?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(TtlVidsStackFrgmnt())
            tab_1_ovrvw?.setTextColor(Color.WHITE)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_2_gnder?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(VidsVwsStackFrgmnt())
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.WHITE)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_3_demogrfics?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(TrndVidsStackFrgmnt())
            tab_3_demogrfics?.setTextColor(Color.WHITE)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_4_earn?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(LtstVidsStackFrgmnt())
            tab_4_earn?.setTextColor(Color.WHITE)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_selected_bg)
        })
        return viewexp
    }

    private fun updateTabContent(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.tab_content_container, fragment)
        fragmentTransaction.commit()
    }

//    private fun updateTabStyle(selectedTab: TextView) {
//        for (i in 0 until tabLayout?.childCount!!) {
//            val tab: View = tabLayout?.getChildAt(i)!!
//            if (tab is TextView) {
//                val tabView: TextView = tab as TextView
//                if (tabView === selectedTab) {
//                    tabView.setTextColor(resources.getColor(R.color.black))
//                    tabView.setBackgroundResource(R.drawable.tab_selected_bg)
//                } else {
//                    tabView.setTextColor(resources.getColor(R.color.darker_gray))
//                    tabView.setBackgroundResource(R.drawable.tab_unselected_bg)
//                }
//            }
//        }
//    }
}