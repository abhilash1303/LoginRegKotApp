package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.courses.TMyCoursePlayVideos
import www.rahagloball.loginregkotapp.models.crsscnvidlist.CrsSctnVidllist

class MyCrsSctnVidAdapter(allFollowList: List<CrsSctnVidllist>, act: FragmentActivity) :
    RecyclerView.Adapter<MyCrsSctnVidAdapter.ViewHolder>() {
    private val dataSet: List<CrsSctnVidllist>
    private val mContext: Activity
    var card_learn: CardView? = null
    var handler: Handler? = null
    var exoPlayer: ExoPlayer? = null
    var exoPlayerView: Player? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.my_section_vidlist, viewGroup, false)
        val myViewHolder: ViewHolder = ViewHolder(view)
        handler = Handler(Looper.getMainLooper())
        exoPlayer = ExoPlayer.Builder(mContext).build()

//        exoPlayerView.setPlayer(exoPlayer);
        return myViewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.title
        val qid: TextView = viewHolder.idd
        val prvw_onoff: TextView = viewHolder.prvw_onoff
        val exoPlayerView: PlayerView = viewHolder.exovidplay
        val csection_idd: String? = dataSet[i].id
        card_learn = viewHolder.card_learn
        val title_cat: String? = dataSet[i].title
        //        String prvw_onoffstr = dataSet.get(i).getPaidOrOverview();
//        if (prvw_onoffstr.equals("1")) {
//            prvw_onoff.setText("On");
//        } else {
//            prvw_onoff.setText("Off");
//
//        }
        postreq.setText(title_cat)
        exoPlayer = ExoPlayer.Builder(mContext).build()
        exoPlayerView.setPlayer(exoPlayer)

        //screen alway active
        exoPlayerView.keepScreenOn = true

        //track state
        exoPlayer?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                //when data video fetch stream from internet
                if (playbackState == Player.STATE_BUFFERING) {
//                    progressBar.visibility = View.VISIBLE;
                } else if (playbackState == Player.STATE_READY) {
                    //then if streamed is loaded we hide the progress bar
//                    progressBar.visibility = View.GONE;
                }
                if (!exoPlayer!!.getPlayWhenReady()) {
                    handler!!.removeCallbacks(updateProgressAction)
                } else {
                    onProgress()
                }
            }
        })
        val vid_data: String = Configs.BASE_URL21 + "images/pool/video/" + dataSet[i].video

//            if (vid_data != null) {
        //pass the video link and play
        val videoUrl = Uri.parse(vid_data)
        val media = MediaItem.fromUri(videoUrl)
        exoPlayer?.setMediaItem(media)
        exoPlayer?.prepare()
        exoPlayer?.play()
        card_learn!!.setOnClickListener { v: View? ->
            val title_cat1: String? = dataSet[i].title
            val vid_data1: String =
                Configs.BASE_URL21 + "images/pool/video/" + dataSet[i].video
            val intent = Intent(mContext, TMyCoursePlayVideos::class.java)
            intent.putExtra("title_cat_play", title_cat1)
            intent.putExtra("vid_data_play", vid_data1)
            //            intent.putExtra("coure_iddrrrr", coure_idd1);
            mContext.startActivity(intent)
        }
    }

    private val updateProgressAction = Runnable { onProgress() }

    //at 4 second
    var ad: Long = 4000
    var check = false

    init {
        dataSet = allFollowList
        mContext = act
    }

    private fun onProgress() {
        val player: ExoPlayer? = exoPlayer
        val position: Long = if (player == null) 0 else player.currentPosition
        handler!!.removeCallbacks(updateProgressAction)
        val playbackState: Int =
            player?.playbackState ?: Player.STATE_IDLE
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            var delayMs: Long
            if (player?.playWhenReady ==true && playbackState == Player.STATE_READY) {
                delayMs = 1000 - position % 1000
                if (delayMs < 200) {
                    delayMs += 1000
                }
            } else {
                delayMs = 1000
            }

//            //check to display ad
//            if ((ad - 3000 <= position && position <= ad) && !check) {
//                check = true;
//                initAd();
//            }
            handler!!.postDelayed(updateProgressAction, delayMs)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(var vv: View) : RecyclerView.ViewHolder(
        vv
    ) {
        var title: TextView
        var idd: TextView
        var vids_crs: TextView? = null
        var card_learn: CardView
        var exovidplay: PlayerView
        var prvw_onoff: TextView
        var csvideoTitle: TextView? = null

        init {
            idd = itemView.findViewById<TextView>(R.id.idd)
            title = itemView.findViewById<TextView>(R.id.csvideoTitle)
            card_learn = itemView.findViewById<CardView>(R.id.card_learn)
            prvw_onoff = itemView.findViewById<TextView>(R.id.prvw_onoff)
            exovidplay = itemView.findViewById<PlayerView>(R.id.exovidplay)
        }
    }
}