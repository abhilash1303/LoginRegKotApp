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
import www.rahagloball.loginregkotapp.activities.BlogsShowActivity
import www.rahagloball.loginregkotapp.models.getblogs.GetBlogs

class BlogssAdapter(allFollowList: List<GetBlogs>, act: FragmentActivity) :
    RecyclerView.Adapter<BlogssAdapter.ViewHolder>() {
    private val dataSet: List<GetBlogs>
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
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lrn_video1, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.title
        val checkImage = viewHolder.videoImage
        val qid: TextView = viewHolder.idd
        val cst_dd: String? = dataSet[i].id
        val title_cat: String? = dataSet[i].title
        postreq.setText(title_cat)
        val course_img_str: String =
            Configs.BASE_URL21 + "images/blogscontent/" + dataSet[i].image
        Glide.with(mContext).load(course_img_str).into(checkImage)
        card_learn!!.setOnClickListener { v: View? ->
            val intent = Intent(mContext, BlogsShowActivity::class.java)
            intent.putExtra("Qid", cst_dd)
            mContext.startActivity(intent)
        }
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

        init {
            videoImage = itemView.findViewById<ImageView>(R.id.videoThumbnail)
            idd = itemView.findViewById<TextView>(R.id.idd)
            title = itemView.findViewById<TextView>(R.id.videoTitle)
            card_learn = itemView.findViewById<CardView>(R.id.card_learn)
        }
    }
}