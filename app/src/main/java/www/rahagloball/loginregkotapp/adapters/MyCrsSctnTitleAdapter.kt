package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.courses.TMyCourseVideos
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitle

class MyCrsSctnTitleAdapter(allFollowList: List<SectionTitle>, act: FragmentActivity) :
    RecyclerView.Adapter<MyCrsSctnTitleAdapter.ViewHolder>() {
    private val dataSet: List<SectionTitle>
    private val mContext: Activity
    var card_learn: CardView? = null

    init {
        dataSet = allFollowList
        mContext = act
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.lrn_section_title, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.title
        val qid: TextView = viewHolder.idd
        val vids_crs: TextView = viewHolder.vids_crs
        val csection_idd: String? = dataSet[i].id
        //        Log.e("csection_idd",csection_idd);
        val title_cat: String? = dataSet[i].title
        postreq.setText(title_cat)
        vids_crs.setOnClickListener(View.OnClickListener { v: View? ->
            val csection_idd1: String? = dataSet[i].id
            val coure_idd1: String ?= dataSet[i].courseId
            val intent = Intent(mContext, TMyCourseVideos::class.java)
            intent.putExtra("section_title_id", csection_idd1)
            intent.putExtra("coure_iddrrrr", coure_idd1)
            mContext.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(var vv: View) : RecyclerView.ViewHolder(
        vv
    ) {
        var title: TextView
        var idd: TextView
        var vids_crs: TextView

        init {
            idd = itemView.findViewById<TextView>(R.id.idd)
            title = itemView.findViewById<TextView>(R.id.videoTitle)
            card_learn = itemView.findViewById<CardView>(R.id.card_learn)
            vids_crs = itemView.findViewById<TextView>(R.id.vids_crs)
            //            section_crss = itemView.findViewById(R.id.section_crss);
        }
    }
}