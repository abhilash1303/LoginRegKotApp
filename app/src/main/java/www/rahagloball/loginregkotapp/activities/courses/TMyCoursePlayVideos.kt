package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants

class TMyCoursePlayVideos constructor() : AppCompatActivity() {
    var context: Context? = null
    var manager: SessionManager? = null
    var token: String? = null
    var crs_section_idd: String? = null
    var crs_idd: String? = null
    var edit_section_titlee_str: String? = null
    var bundle: Bundle? = null
    var title_cat_play_str: String? = null
    var vid_data_play_str: String? = null
    var handler: Handler? = null
    var videoTitle: TextView? = null
    var exoPlayer: ExoPlayer? = null
    var exoPlayerView: PlayerView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mycrs_play)
        handler = Handler(Looper.getMainLooper())
        context = this@TMyCoursePlayVideos
        manager = SessionManager()
        token = manager?.getPreferences(this@TMyCoursePlayVideos, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        title_cat_play_str = bundle?.getString("title_cat_play")
        vid_data_play_str = bundle?.getString("vid_data_play")
        exoPlayerView = findViewById<PlayerView>(R.id.exovidplay)
        videoTitle = findViewById<TextView>(R.id.videoTitle)
        videoTitle?.text = title_cat_play_str
        exoPlayer = ExoPlayer.Builder(context as TMyCoursePlayVideos).build()
        exoPlayerView?.player = exoPlayer

        //screen alway active
        exoPlayerView?.keepScreenOn = true

        //track state
        exoPlayer?.addListener(object : Player.Listener {
            public override fun onPlaybackStateChanged(playbackState: Int) {
                //when data video fetch stream from internet
                if (playbackState == Player.STATE_BUFFERING) {
//                    progressBar.checkedRadioButtonId;
                } else if (playbackState == Player.STATE_READY) {
                    //then if streamed is loaded we hide the progress bar
//                    progressBar.visibility = View.GONE;
                }
                if (!exoPlayer?.playWhenReady!!) {
                    handler!!.removeCallbacks(updateProgressAction)
                } else {
                    onProgress()
                }
            }
        })


//            if (vid_data != null) {
        //pass the video link and play
        val videoUrl: Uri = Uri.parse(vid_data_play_str)
        val media: MediaItem = MediaItem.fromUri(videoUrl)
        exoPlayer?.setMediaItem(media)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }

    private val updateProgressAction: Runnable = Runnable { onProgress() }

    //at 4 second
    var ad: Long = 4000
    var check: Boolean = false
    private fun onProgress() {
        val player: ExoPlayer? = exoPlayer
        val position: Long = player?.currentPosition ?: 0
        handler!!.removeCallbacks(updateProgressAction)
        val playbackState: Int =
            player?.playbackState ?: Player.STATE_IDLE
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            var delayMs: Long=1000
            if (player != null) {
                if (player.playWhenReady && playbackState == Player.STATE_READY) {
                    delayMs = 1000 - position % 1000
                    if (delayMs < 200) {
                        delayMs += 1000
                    }
                } else {
                    delayMs = 1000
                }
            }

//            //check to display ad
//            if ((ad - 3000 <= position && position <= ad) && !check) {
//                check = true;
//                initAd();
//            }
            handler!!.postDelayed(updateProgressAction, delayMs)
        }
    }
}