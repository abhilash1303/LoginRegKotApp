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
import www.rahagloball.loginregkotapp.activities.CtsChActivityDirect
import www.rahagloball.loginregkotapp.models.cutiesss.DataItemCute
import www.rahagloball.loginregkotapp.models.singlecutiesch.SinCtsChnlNew
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleCutiesCh


class CuteListChAdapter(allFollowList: List<SingleCutiesCh>, act: FragmentActivity) :
    RecyclerView.Adapter<CuteListChAdapter.ViewHolder>() {
    private var dataSet: List<SingleCutiesCh> = ArrayList<SingleCutiesCh>()
    private val mContext: Activity
    var card_learn: LinearLayout? = null
    var id: String? = null
    var ch_iddddd: String? = null
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
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.vid_upld_layout_cts, viewGroup, false)
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
            val sinCtsChnlNew: SinCtsChnlNew? = dataSet[i].channel
            ch_iddddd = sinCtsChnlNew?.id
            vid_ttl.setText(title_cat)
            val prev_img: String =
                Configs.BASE_URL21 + "images/pool/preview/" + dataSet[i].previewImage
            //            Log.e("prev_imgcutie", prev_img);
            Glide.with(mContext).load(prev_img).into(checkImage)
            val views_vid_str: String? = dataSet[i].viewsCount
            val shortenedViews: String? = views_vid_str?.let { Utils.getShortenedViewsString(it.toInt()) }
            views_vid.setText("$shortenedViews Views")
            viewHolder.vv.setOnClickListener { v: View ->
                ch_iddddd = sinCtsChnlNew?.id
                val b = Bundle()
                b.putSerializable("videoData", dataSet[i])
                val qid_str1: String? = dataSet[i].id
                val i222 = Intent(mContext, CtsChActivityDirect::class.java)
                i222.putExtras(b)
                i222.putExtra("video_id", qid_str1)
                i222.putExtra("ch_idddddstr", ch_iddddd)
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