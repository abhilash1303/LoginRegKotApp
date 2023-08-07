package www.rahagloball.loginregkotapp.fragments.crtrzone

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.ViewPagerAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.fragments.earnigs.BrandEarnFrgmnt
import www.rahagloball.loginregkotapp.fragments.earnigs.SuprSprtEarnFrgmnt

class CrtrChRvnuFrgmnt : Fragment() {
    var token: String? = null
    var chanel_id_str: String? = null
    var pool_id_str: String? = null
    var manager: SessionManager? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var vadapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewexp: View = inflater.inflate(R.layout.ch_rvnue_layout, container, false)
        manager = SessionManager()
        token = activity?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        tabLayout = viewexp.findViewById<TabLayout>(R.id.tabLayout)
        viewPager = viewexp.findViewById<ViewPager>(R.id.view_pager)
        vadapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        vadapter!!.addFragment(
            SuprSprtEarnFrgmnt(),
            getString(R.string.supr_sprt_earn),
            chanel_id_str,
            ""
        )
        vadapter!!.addFragment(BrandEarnFrgmnt(), getString(R.string.brand_earn), chanel_id_str, "")
        viewPager?.setAdapter(vadapter)
        //set ViewPager
        tabLayout?.setupWithViewPager(viewPager)
        changeTabsFont()
        viewPager?.offscreenPageLimit = 5
        return viewexp
    }

    private fun changeTabsFont() {
        val vg: ViewGroup = tabLayout?.getChildAt(0) as ViewGroup
        val tabsCount: Int = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab: ViewGroup = vg.getChildAt(j) as ViewGroup
            val tabChildsCount: Int = vgTab.childCount
            for (i in 0 until tabChildsCount) {
                val tabViewChild: View = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    val custom_font: Typeface? =
                        activity?.let { ResourcesCompat.getFont(it, R.font.poppins_regular) }
                    (tabViewChild as TextView).setTypeface(custom_font)
                }
            }
        }
    }
}