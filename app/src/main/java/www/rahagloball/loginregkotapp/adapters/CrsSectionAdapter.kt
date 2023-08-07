package www.rahagloball.loginregkotapp.adapters


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.courses.AddAnncntActivty
import www.rahagloball.loginregkotapp.activities.courses.AddQuestionActivty
import www.rahagloball.loginregkotapp.activities.courses.CourseAddTtile
import www.rahagloball.loginregkotapp.models.usercrs.UserCrs

class CrsSectionAdapter(allFollowList: List<UserCrs>, act: FragmentActivity) :
    RecyclerView.Adapter<CrsSectionAdapter.ViewHolder>() {
    private val dataSet: List<UserCrs>
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
            .inflate(R.layout.lrn_video_section, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.title
        val checkImage = viewHolder.videoImage
        val qid: TextView = viewHolder.idd
        val section_crss: TextView = viewHolder.section_crss
        val vids_crs: TextView = viewHolder.vids_crs
        val add_ques: TextView = viewHolder.add_ques
        val add_anouncmnt: TextView = viewHolder.add_anouncmnt
        val cst_dd: String? = dataSet[i].id
        val cst_slug: String? = dataSet[i].slug
        val title_cat: String? = dataSet[i].title
        postreq.setText(title_cat)
        val course_img_str: String? =
            Configs.BASE_URL21 + "images/course/" + dataSet[i].previewImage
        Glide.with(mContext).load(course_img_str).into(checkImage)
        section_crss.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(mContext, CourseAddTtile::class.java)
            intent.putExtra("Course_id", cst_dd)
            mContext.startActivity(intent)
        })
        add_ques.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(mContext, AddQuestionActivty::class.java)
            intent.putExtra("Course_id", cst_dd)
            mContext.startActivity(intent)
        })
        add_anouncmnt.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(mContext, AddAnncntActivty::class.java)
            intent.putExtra("Course_id", cst_dd)
            mContext.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(var vv: View) : RecyclerView.ViewHolder(
        vv
    ) {
        var videoImage: ImageView
        var title: TextView
        var time_line: TextView? = null
        var views_vid: TextView? = null
        var idd: TextView
        var vids_crs: TextView
        var add_anouncmnt: TextView
        var add_ques: TextView
        var section_crss: TextView

        init {
            videoImage = itemView.findViewById<ImageView>(R.id.videoThumbnail)
            idd = itemView.findViewById<TextView>(R.id.idd)
            title = itemView.findViewById<TextView>(R.id.videoTitle)
            card_learn = itemView.findViewById<CardView>(R.id.card_learn)
            vids_crs = itemView.findViewById<TextView>(R.id.vids_crs)
            add_ques = itemView.findViewById<TextView>(R.id.add_ques)
            add_anouncmnt = itemView.findViewById<TextView>(R.id.add_anouncmnt)
            section_crss = itemView.findViewById<TextView>(R.id.section_crss)
        }
    }
}