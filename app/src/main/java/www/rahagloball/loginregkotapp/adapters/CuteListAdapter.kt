package www.rahagloball.loginregkotapp.adapters


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.configuration.Utils
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.SnglChCtsActvt
import www.rahagloball.loginregkotapp.models.cutiesss.DataItemCute

//import www.nationlearnsraha.com.CutiesActivityDirect;
//import www.nationlearnsraha.com.Model.Pojo.cutiesss.DataItemCute;
//import www.nationlearnsraha.com.R;
//import www.nationlearnsraha.com.SnglChCtsActvt;
//import www.nationlearnsraha.com.homeDemo.cutesss.CutiesActivity;
class CuteListAdapter(allFollowList: List<DataItemCute>, act: FragmentActivity) :
    RecyclerView.Adapter<CuteListAdapter.ViewHolder>() {
    private var dataSet: List<DataItemCute> = ArrayList<DataItemCute>()
    private val mContext: Activity
    var card_learn: LinearLayout? = null
    var id: String? = null
    var vid_di: DataItemCute? = null

    //    List<VideosItem> video_items;
    init {
        dataSet = allFollowList
        mContext = act
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vid_upld_layout, viewGroup, false)
        //        manager = new SessionManager();
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val vid_ttl: TextView = viewHolder.videoTitle
        val checkImage = viewHolder.videoThumbnail
        val qid: TextView = viewHolder.idd
        val views_vid: TextView = viewHolder.views_vid
        //        final CircleImageView chan_img = viewHolder.channel_pic;
        card_learn = viewHolder.ll_cutee
        try {
            val title_cat: String? = dataSet[i].title
            //            Log.e("title_cat", title_cat);
            vid_ttl.setText(title_cat)
            val prev_img: String =
                Configs.BASE_URL21 + "images/pool/preview/" + dataSet[i].previewImage
            //            Log.e("prev_imgcutie", prev_img);
            Glide.with(mContext).load(prev_img).into(checkImage)

//            Picasso.get().
//                    load(prev_img)
//                    .into(checkImage);
            val views_vid_str: String? = dataSet[i].viewsCount
            val shortenedViews: String? = views_vid_str?.let { Utils.getShortenedViewsString(it.toInt()) }
            views_vid.setText("$shortenedViews Views")

//            views_vid.setText(views_vid_str + " Views");
            viewHolder.vv.setOnClickListener { v: View ->
//
                val b = Bundle()
                b.putSerializable("videoData", dataSet[i])
                val qid_str1: String? = dataSet[i].channelId
                val i222 = Intent(mContext, SnglChCtsActvt::class.java)
                i222.putExtras(b)
                i222.putExtra("video_id", qid_str1)
                v.context.startActivity(i222)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(var vv: View) : RecyclerView.ViewHolder(
        vv
    ) {
        var videoThumbnail: ImageView

        //        CircleImageView channel_pic;
        var videoTitle: TextView
        var idd: TextView
        var views_vid: TextView
        var ll_cutee: LinearLayout

        init {
            videoThumbnail = itemView.findViewById<ImageView>(R.id.videoThumbnaic)
            idd = itemView.findViewById<TextView>(R.id.idd)
            views_vid = itemView.findViewById<TextView>(R.id.cuteviews)
            videoTitle = itemView.findViewById<TextView>(R.id.cuteTitle)
            ll_cutee = itemView.findViewById<LinearLayout>(R.id.ll_cutee)
        }
    }
}