package www.rahagloball.loginregkotapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.statecityss.StCtySprtSupr

class TabDmgrfpercntAdapter(dataSet: List<StCtySprtSupr>, mContext: FragmentActivity) :
    RecyclerView.Adapter<TabDmgrfpercntAdapter.ViewHolder>() {
    var dataSet: List<StCtySprtSupr>
    var mContext: FragmentActivity

    init {
        this.dataSet = dataSet
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.dmgrfy_st_cty, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val city_dmgrfy: TextView = viewHolder.city_dmgrfy
        val sprts_count_bnglr: TextView = viewHolder.sprts_count_bnglr
        val spr_sprts_count_bnglr: TextView = viewHolder.spr_sprts_count_bnglr
        val stCtySprtSupr: StCtySprtSupr = dataSet[i]
        city_dmgrfy.text =(stCtySprtSupr.city)
        sprts_count_bnglr.text = stCtySprtSupr.supportPercentage
        spr_sprts_count_bnglr.text = stCtySprtSupr.superSupportPercentage
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city_dmgrfy: TextView
        var sprts_count_bnglr: TextView
        var spr_sprts_count_bnglr: TextView

        init {
            city_dmgrfy = itemView.findViewById<TextView>(R.id.city_dmgrfy)
            sprts_count_bnglr = itemView.findViewById<TextView>(R.id.sprts_count_bnglr)
            spr_sprts_count_bnglr = itemView.findViewById<TextView>(R.id.spr_sprts_count_bnglr)
        }
    }
}