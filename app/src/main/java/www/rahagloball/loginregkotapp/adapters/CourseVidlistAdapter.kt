package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLst

class CourseVidlistAdapter(allFollowList: List<CrseeVidLst>, act: Activity) :
    RecyclerView.Adapter<CourseVidlistAdapter.ViewHolder>() {
    private val dataSet: List<CrseeVidLst>
    private val context: Context
    var card_view: CardView? = null

    init {
        dataSet = allFollowList
        context = act
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.course_vid_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val vid_cntnt_title: TextView = viewHolder.vid_cntnt_title
        val vid_cntnt_time: TextView = viewHolder.vid_cntnt_time
        val vid_cntnt_number: TextView = viewHolder.vid_cntnt_number
        val vid_chapter: TextView = viewHolder.vid_chapter
        val rl_course_vid_play: RelativeLayout = viewHolder.rl_course_vid_play
        val vid_cntnt_title_str: String? = dataSet[i].title
        val vid_cntnt_time_str: String? = dataSet[i].duration
        val vid_cntnt_number_str: String? = dataSet[i].id
        //        String vid_chapter_str = dataSet.get(i).getChapterName();


//        vid_cntnt_title.setText(Html.fromHtml(vid_cntnt_title_str));
        vid_cntnt_title.setText(vid_cntnt_title_str)
        vid_cntnt_time.setText(vid_cntnt_time_str)
        vid_cntnt_number.setText(vid_cntnt_number_str)


//        vid_chapter.setText(vid_chapter_str);


//        viewHolder.crse_vieww.setOnClickListener(v -> {
//
//            Bundle b = new Bundle();
////            b.putSerializable("videoData",  allVideos.get(position));
////            String id = String.valueOf(allVideos.get(position).getmId());
//
//            Intent intnt = new Intent(context, CourseVidPlayActivity.class);
//            intnt.putExtras(b);
////            intnt.putExtra("video_id", id);
//            v.getContext().startActivity(intnt);
//
//        });
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(var crse_vieww: View) : RecyclerView.ViewHolder(
        crse_vieww
    ) {
        var vid_cntnt_title: TextView
        var vid_cntnt_time: TextView
        var vid_cntnt_number: TextView
        var vid_chapter: TextView
        var rl_course_vid_play: RelativeLayout

        //        TextView cat_txtt;
        //        TextView catt_Idd, main_cat_id;
        //        ImageView cat_imgg;
        //        CardView cardView;
        init {
            vid_cntnt_title = itemView.findViewById<TextView>(R.id.vid_cntnt_title)
            vid_cntnt_time = itemView.findViewById<TextView>(R.id.vid_cntnt_time)
            vid_cntnt_number = itemView.findViewById<TextView>(R.id.vid_cntnt_number)
            rl_course_vid_play = itemView.findViewById<RelativeLayout>(R.id.rl_course_vid_play)
            vid_chapter = itemView.findViewById<TextView>(R.id.vid_chapter)
        }
    }
}