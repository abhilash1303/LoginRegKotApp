package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.CrtrZoneActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.MyChDtls

class CzMyChAbout : Fragment() {
    var token: String? = null
    var chanl_idtosend: String? = null
    var manager: SessionManager? = null

    //    LinearLayout tabLayout;
    var tab_10_prof: TextView? = null
    var tab_1_ovrvw: TextView? = null
    var tab_2_gnder: TextView? = null
    var tab_3_demogrfics: TextView? = null
    var tab_4_earn: TextView? = null
    var tab_5_plylsts: TextView? = null
    var tab_6_chnls: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.czmych_layout, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        //        tabLayout = viewexp.findViewById(R.id.tab_layout);
        tab_1_ovrvw = viewexp.findViewById<TextView>(R.id.tab_1_ovrvw)
        tab_2_gnder = viewexp.findViewById<TextView>(R.id.tab_2_gnder)
        tab_3_demogrfics = viewexp.findViewById<TextView>(R.id.tab_3_demogrfics)
        tab_4_earn = viewexp.findViewById<TextView>(R.id.tab_4_earn)
        tab_5_plylsts = viewexp.findViewById<TextView>(R.id.tab_5_plylsts)
        tab_6_chnls = viewexp.findViewById<TextView>(R.id.tab_6_chnls)
        tab_10_prof = viewexp.findViewById<TextView>(R.id.tab_10_prof)
        val activity: CrtrZoneActivity? = activity as CrtrZoneActivity?
        chanl_idtosend = activity?.getMyData()
        tab_10_prof?.setTextColor(Color.WHITE)
        tab_1_ovrvw?.setTextColor(Color.BLACK)
        tab_2_gnder?.setTextColor(Color.BLACK)
        tab_3_demogrfics?.setTextColor(Color.BLACK)
        tab_4_earn?.setTextColor(Color.BLACK)
        tab_5_plylsts?.setTextColor(Color.BLACK)
        tab_6_chnls?.setTextColor(Color.BLACK)
        tab_10_prof?.setBackgroundResource(R.drawable.tab_selected_bg)
        tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
        tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        updateTabContent(MyChDtls())
        tab_10_prof?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(MyChDtls())
            tab_10_prof?.setTextColor(Color.WHITE)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_1_ovrvw?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(CrtsVideoFrgmnt())
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.WHITE)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_2_gnder?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(CrtsVideoFrgmnt())
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.WHITE)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_3_demogrfics?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(CrtrCtsListFrgmnt())
            tab_3_demogrfics?.setTextColor(Color.WHITE)
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_4_earn?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(MyCourseFrgmnt())
            tab_4_earn?.setTextColor(Color.WHITE)
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_5_plylsts?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(CrtsVideoFrgmnt())
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.WHITE)
            tab_6_chnls?.setTextColor(Color.BLACK)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_selected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_unselected_bg)
        })
        tab_6_chnls?.setOnClickListener(View.OnClickListener { v: View? ->
            updateTabContent(AbtChnlFrgmnt())
            tab_10_prof?.setTextColor(Color.BLACK)
            tab_4_earn?.setTextColor(Color.BLACK)
            tab_3_demogrfics?.setTextColor(Color.BLACK)
            tab_1_ovrvw?.setTextColor(Color.BLACK)
            tab_2_gnder?.setTextColor(Color.BLACK)
            tab_5_plylsts?.setTextColor(Color.BLACK)
            tab_6_chnls?.setTextColor(Color.WHITE)
            tab_10_prof?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_1_ovrvw?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_2_gnder?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_3_demogrfics?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_4_earn?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_5_plylsts?.setBackgroundResource(R.drawable.tab_unselected_bg)
            tab_6_chnls?.setBackgroundResource(R.drawable.tab_selected_bg)
        })
        return viewexp
    }

    private fun updateTabContent(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.tab_content_container, fragment)
        fragmentTransaction.commit()
    } //    private void updateTabStyle(TextView selectedTab) {
    //        for (int i = 0; i < tabLayout.getChildCount(); i++) {
    //            View tab = tabLayout.getChildAt(i);
    //            if (tab instanceof TextView) {
    //                TextView tabView = (TextView) tab;
    //                if (tabView == selectedTab) {
    //                    tabView.setTextColor(getResources().getColor(android.R.color.black));
    //                    tabView.setBackgroundResource(R.drawable.tab_selected_bg);
    //                } else {
    //                    tabView.setTextColor(getResources().getColor(android.R.color.darker_gray));
    //                    tabView.setBackgroundResource(R.drawable.tab_unselected_bg);
    //                }
    //            }
    //        }
    //    }
}