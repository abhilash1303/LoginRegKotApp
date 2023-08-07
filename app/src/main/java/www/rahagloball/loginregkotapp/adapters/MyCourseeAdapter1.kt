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
import org.json.JSONException
import org.json.JSONObject
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.MyCourseViewActivity
import www.rahagloball.loginregkotapp.models.mychanldtls.CourseMychDtls

class MyCourseeAdapter1(allFollowList: List<CourseMychDtls>, act: FragmentActivity) :
    RecyclerView.Adapter<MyCourseeAdapter1.ViewHolder>() {
    private val dataSet: List<CourseMychDtls>
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
            .inflate(R.layout.lrn_video1, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.title
        val checkImage = viewHolder.videoImage
        val qid: TextView = viewHolder.idd
        val coursesMine: CourseMychDtls = dataSet[i]
        val cst_dd: String? = coursesMine.id
        val title_cat: String? = coursesMine.title
        try {
            val jsonObject = JSONObject(title_cat)
            val title: String = jsonObject.getString("en")
            postreq.setText(title)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val course_img_str: String =
            Configs.BASE_URL21 + "images/course/" + coursesMine.previewImage
        Glide.with(mContext).load(course_img_str).into(checkImage)
        card_learn!!.setOnClickListener { v: View? ->
            val intent = Intent(mContext, MyCourseViewActivity::class.java)
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