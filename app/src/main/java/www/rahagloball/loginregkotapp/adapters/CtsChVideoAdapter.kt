package www.rahagloball.loginregkotapp.adapters



import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.addcmntres.CommentsAddpojo
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo
import www.rahagloball.loginregkotapp.models.singlecutiesch.SinCtsChVote
import www.rahagloball.loginregkotapp.models.singlecutiesch.SinCtsChnlNew
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleChPojo
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleCutiesCh
import java.util.Timer
import java.util.TimerTask

class CtsChVideoAdapter(
    videoItemmList: List<SingleCutiesCh>,
    context: Context,
    viewPager2: ViewPager2
) : RecyclerView.Adapter<CtsChVideoAdapter.Videoviewholder>() {
    private var videoItemmList: List<SingleCutiesCh>
    var context: Context
    var viewPager2: ViewPager2
    var intent1: Intent? = null
    var vote_typee: String? = null
    var videoo_idd: String? = null
    var token: String? = null
    var checImgSt: String? = null
    var edit_commnt_str: String? = null
    var manager: SessionManager? = null
    var toggle_like: ToggleButton? = null
    var toggle_dislike: ToggleButton? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    var bottomSheetLayout: View? = null
    var channel_pic_sub_btm: CircleImageView? = null
    var channel_pic_sub_cmnt: CircleImageView? = null
    var edit_commnt: EditText? = null
    var videoTitle_btm: TextView? = null
    var choice_data: TextView? = null
    var channel_name_btm: TextView? = null
    var send_commnett: FloatingActionButton? = null
    var chid_cts: String? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var mMediaPlayer: MediaPlayer? = null
    var lisescntstr = 0
    var ttt = 1
    var totelike = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Videoviewholder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contnr_videoalter, parent, false)
        val myviewholder: Videoviewholder = Videoviewholder(view)
        manager = SessionManager()
        token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
        toggle_like = view.findViewById<ToggleButton>(R.id.toggle_like)
        toggle_dislike = view.findViewById<ToggleButton>(R.id.toggle_dislike)
        return myviewholder


//        return new Videoviewholder(LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_contnr_video, parent, false)
//        );
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: Videoviewholder, position: Int) {
        holder.setVidoData(videoItemmList[position], position)
        val cutie_pause = holder.cutie_pause
        val cutie_play = holder.cutie_play
        val videoView: VideoView = holder.exoPlayerView
        val exoPlayer: ExoPlayer? = holder.exoPlayer
        val comnts_cnt: TextView = holder.comnts_cnt
        val likecount: TextView = holder.likecount
        val subscr_cts1: TextView = holder.subscr_cts
        val ll_shr_cutis: LinearLayout = holder.ll_shr_cutis
        val ll_cmnts_cutis: LinearLayout = holder.ll_cmnts_cutis
        val ll_ch_cts: LinearLayout = holder.ll_ch_cts
        val toggle_like: ToggleButton = holder.toggle_like
        val toggle_dislike: ToggleButton = holder.toggle_dislike
        val mPlayerContainer: ConstraintLayout? = holder.playerContainer
        subscr_cts1.setOnClickListener(View.OnClickListener { v: View? -> subscr_cts1.setText("Stop Support") })
        try {

//            videoView.setOnClickListener(view -> {
//
//
//                if (videoView.isPlaying()) {
////
//                    videoView.pause();
//                    cutie_play.visibility = View.VISIBLE;
//                    cutie_pause.visibility = View.GONE;
//
//                } else {
//                    videoView.start();
//                    cutie_play.visibility = View.GONE;
//                    cutie_pause.visibility = View.VISIBLE;
//                }
//
//
//            });
//
//            cutie_play.setOnClickListener(v -> {
//
//                holder.setVidoData(videoItemmList.get(position));
//                cutie_play.visibility = View.GONE;
//                cutie_pause.visibility = View.VISIBLE;
//
//
//            });
//
//
//            cutie_pause.setOnClickListener(v -> {
//
//                cutie_play.visibility = View.GONE;
//                cutie_pause.visibility = View.GONE;
//
//
//            });
//
            val votesItems: List<SinCtsChVote>? = videoItemmList[position].votes
            if (votesItems != null) {
                for (j in votesItems.indices) {
                    vote_typee = votesItems[j].type
                    videoo_idd = videoItemmList[position].id
                    if (vote_typee!!.contains("0")) {
                        postdislik(videoo_idd)
                    } else if (vote_typee!!.contains("1")) {
                        postliked(videoo_idd)
                    }
                }
            }
            videoo_idd = videoItemmList[position].id
            lisescntstr = videoItemmList[position].votesCount!!.toInt()
            if (lisescntstr > 0) {
                likecount.setText(lisescntstr.toString())
            } else {
                likecount.setText("Like")
            }
            toggle_like.setOnClickListener(View.OnClickListener { view: View? ->
                videoo_idd = videoItemmList[position].id
                postliked(videoo_idd)
                if (toggle_dislike.isChecked) {
                    toggle_dislike.isChecked = false
                }
                if (!toggle_like.isChecked) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                } else {
                    totelike = 1.let { lisescntstr += it; lisescntstr }.toString().toInt()
                }
                if (lisescntstr.toString().toInt() == 0) {
                    likecount.text = "Like"
                } else {
                    likecount.text = totelike.toString()
                }
            })
            toggle_dislike.setOnClickListener(View.OnClickListener { view: View? ->
                videoo_idd = videoItemmList[position].id
                postdislik(videoo_idd)
                if (toggle_like.isChecked()) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                    toggle_like.setChecked(false)
                }
                if (lisescntstr > 0) {
                    likecount.setText(lisescntstr.toString())
                } else {
                    likecount.setText("Like")
                }
            })
            val cmts_cnt_str: String? = videoItemmList[position].commentsCount
            comnts_cnt.setText(cmts_cnt_str)
            ll_shr_cutis.setOnClickListener(View.OnClickListener { view: View? ->
                val vidddurlll: String? = videoItemmList[position].video
                videoo_idd = videoItemmList[position].id

//                if (vidddurlll.contains(" ")) {
                val title_str1: String? = videoItemmList[position].title
                val newString = vidddurlll?.replace(" ", "%20")
                val vidd_urll: String = Configs.BASE_URL21 + "video/" + videoo_idd + "/" + newString
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.setType("text/plain")
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch $title_str1")
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT, "Watch now on NationLearns, " +
                            "India's first social e-learning platform " + vidd_urll
                )
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
            })
            val channell: SinCtsChnlNew? = videoItemmList[position].channel
            ll_ch_cts.setOnClickListener(View.OnClickListener { view: View? ->
                val ch_id_ctsss: String? = channell?.id
                val intent = Intent(context, ChannelProfileActivity::class.java)
                intent.putExtra("ch_iddCuties", ch_id_ctsss)
                context.startActivity(intent)
            })
            checImgSt = Configs.BASE_URL21 + "images/channel/" + channell?.image
            channel_pic_sub_cmnt?.let { Glide.with(context).load(checImgSt).into(it) }
            chid_cts = channell?.id
            ll_cmnts_cutis.setOnClickListener(View.OnClickListener { v: View? ->
                bottomSheetLayout =
                    LayoutInflater.from(context).inflate(R.layout.bottom_comment, null)
                channel_pic_sub_cmnt =
                    bottomSheetLayout?.findViewById<CircleImageView>(R.id.channel_pic_sub_cmnt)
                edit_commnt = bottomSheetLayout?.findViewById<EditText>(R.id.edit_commnt)
                send_commnett =
                    bottomSheetLayout?.findViewById<FloatingActionButton>(R.id.send_commnett)
                rv_commnetss = bottomSheetLayout!!.findViewById<RecyclerView>(R.id.rv_commnetss)
                edit_commnt?.post(Runnable {
                    edit_commnt!!.requestFocus()
                    var imm: InputMethodManager? = null
                    imm = edit_commnt!!.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm!!.showSoftInput(edit_commnt, InputMethodManager.SHOW_IMPLICIT)
                })
                edit_commnt?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable) {
                        send_commnett?.visibility = if (s.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    }
                })
                send_commnett?.setOnClickListener(View.OnClickListener { v1: View? ->
                    videoo_idd = videoItemmList[position].id
                    edit_commnt_str = edit_commnt?.text.toString()
                    RetrofitClient.getClient().add_comments(
                        videoo_idd, edit_commnt_str,
                        "application/json",
                        "Bearer $token"
                    )?.enqueue(object : GlobalCallback<CommentsAddpojo?>(send_commnett) {
                        override  fun onResponse(
                            call: Call<CommentsAddpojo?>,
                            response: Response<CommentsAddpojo?>
                        ) {
                            val add_comments_res: String ? = response.body()?.comments
                            //                            Log.e("add_comments_res", add_comments_res);
                            if (add_comments_res != null) {
                                if (add_comments_res.contains("1")) {
                                    edit_commnt?.setText("")
                                    send_commnett!!.visibility = View.GONE
                                    //                                hideKeyboardFrom(context, v1);
                                }
                            }
                        }
                    })
                })
                //                rv_commnetss.setHasFixedSize(true);
                layoutManager = LinearLayoutManager(context)
                rv_commnetss?.layoutManager = layoutManager
                rv_commnetss?.itemAnimator = DefaultItemAnimator()
                chid_cts = channell?.id
                getLEarnCommnts(chid_cts)
                mBottomSheetDialog = BottomSheetDialog(context)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            })
            mPlayerContainer?.setOnClickListener(View.OnClickListener { v: View? ->
                if (mMediaPlayer?.isPlaying == true) {
                    cutie_play.visibility = View.GONE
                    cutie_pause.visibility = View.VISIBLE
                    mMediaPlayer?.pause()
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            (context as Activity).runOnUiThread(
                                Runnable { cutie_pause.visibility = View.GONE }
                            )
                        }
                    }, 1500)
                } else {
                    cutie_pause.visibility = View.GONE
                    cutie_play.visibility = View.VISIBLE
                    mMediaPlayer?.start()
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            (context as Activity).runOnUiThread(
                                Runnable { cutie_play.visibility = View.GONE }
                            )
                        }
                    }, 1500)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLEarnCommnts(ch_id_single: String?) {
//        blur_reg1.visibility = View.VISIBLE;
        val url: String = Configs.BASE_URL2 + "cuties-videos/" + ch_id_single
        RetrofitClient.getClient().gtctssinglelist(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<SingleChPojo?>(send_commnett) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<SingleChPojo?>,
                    response: Response<SingleChPojo?>
                ) {
//                        blur_reg1.visibility = View.GONE;
                    try {
                        videoItemmList= response.body()?.data!!
                        if (videoItemmList.isEmpty()) {
                        } else {
                            adapter = YtCommentsAdapterCutCh(videoItemmList, context)
                            rv_commnetss!!.adapter = adapter
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun getItemCount(): Int {
        return videoItemmList.size
    }

    inner class Videoviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttitle: TextView
        var txtDesc: TextView? = null
        var likecount: TextView
        var subscr_cts: TextView
        var channel_name_cts: TextView
        var time_line: TextView? = null
        var views_vid: TextView
        var comnts_cnt: TextView
        var progressBar: ProgressBar
        var exoPlayerView //StyledPlayerView exoPlayerView;
                : VideoView
        var cutie_pause: ImageView
        var cutie_play: ImageView
        var ll_shr_cutis: LinearLayout
        var ll_cmnts_cutis: LinearLayout
        var ll_ch_cts: LinearLayout
        var toggle_like: ToggleButton
        var toggle_dislike: ToggleButton
        var exoPlayer: ExoPlayer? = null
        var channel_pic_cts: CircleImageView
        var playerContainer: ConstraintLayout? = null
        private val vid_home: String? = null

        init {
            exoPlayerView = itemView.findViewById<VideoView>(R.id.cut_vidview)
            txttitle = itemView.findViewById<TextView>(R.id.txtvidtitle)
            //            txtDesc = itemView.findViewById(R.id.txtviddesc);
            views_vid = itemView.findViewById<TextView>(R.id.views_vid)
            comnts_cnt = itemView.findViewById<TextView>(R.id.comnts_cnt)
            progressBar = itemView.findViewById<ProgressBar>(R.id.vidPrgresbar)
            cutie_pause = itemView.findViewById<ImageView>(R.id.cutie_pause)
            cutie_play = itemView.findViewById<ImageView>(R.id.cutie_play)
            subscr_cts = itemView.findViewById<TextView>(R.id.subscr_cts)
            ll_shr_cutis = itemView.findViewById<LinearLayout>(R.id.shareCuties)
            ll_cmnts_cutis = itemView.findViewById<LinearLayout>(R.id.ll_cmnts_cutis)
            toggle_like = itemView.findViewById<ToggleButton>(R.id.toggle_like)
            toggle_dislike = itemView.findViewById<ToggleButton>(R.id.toggle_dislike)
            likecount = itemView.findViewById<TextView>(R.id.likecount)
            channel_name_cts = itemView.findViewById<TextView>(R.id.channel_name_cts)
            channel_pic_cts = itemView.findViewById<CircleImageView>(R.id.channel_pic_cts)
            ll_ch_cts = itemView.findViewById<LinearLayout>(R.id.ll_ch_cts)
        }

        fun setVidoData(v_home: SingleCutiesCh, position: Int) {
            try {
                val cacheDir = context.cacheDir
                val cacheFiles = cacheDir.listFiles()
                val title_str: String? = v_home.title
                txttitle.setText(title_str)
                val cutChannel: SinCtsChnlNew? = v_home.channel
                val ch_cts_name: String =
                    Configs.BASE_URL21 + "images/channel/" + cutChannel?.image
                Glide.with(context).load(ch_cts_name).into(channel_pic_cts)
                val chnl_nm_cts: String? = cutChannel?.name
                channel_name_cts.text = chnl_nm_cts
                assert(cacheFiles != null)
                exoPlayerView.setVideoPath(context.cacheDir.path + "/" + v_home.video)
                if (v_home.video == null) {
                    playerContainer?.visibility   = View.GONE
                }
                exoPlayerView.setOnPreparedListener { mediaPlayer: MediaPlayer ->
                    mMediaPlayer = mediaPlayer
                    mediaPlayer.start()
                    progressBar.visibility = View.GONE
                }
                exoPlayerView.setOnCompletionListener { mediaPlayer: MediaPlayer ->
                    mMediaPlayer = mediaPlayer
                    mediaPlayer.start()
                }
                exoPlayerView.setOnErrorListener { mediaPlayer: MediaPlayer?, what: Int, extra: Int ->
                    mMediaPlayer = mediaPlayer
                    true // returning true indicates that you have handled the error
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    init {
        this.videoItemmList = videoItemmList
        this.context = context
        this.viewPager2 = viewPager2
    }

    private fun postliked(videoo_idd: String?) {
        manager = SessionManager()
        token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient().getlikislik(
            videoo_idd, 1.toString(),
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_like) {
            override fun onResponse(
                call: Call<LikeDislikPojo?>,
                response: Response<LikeDislikPojo?>
            ) {
                val likedislikeres: String ? = response.body()?.vote
                if (likedislikeres != null) {
                    manager?.setPreferences(context, Constants.LIKE_HIT, likedislikeres)
                }
                if (likedislikeres != null) {
                    if (likedislikeres.contains("1")) {

            //                    toggle_dislike.setChecked(false);
            //                    toggle_like.setChecked(true);
                    } else if (likedislikeres.contains("2")) {
                    } else if (likedislikeres.contains("3")) {

            //                    toggle_dislike.setChecked(false);
            //                    toggle_like.setChecked(true);
                    }
                }
            }
        })
    }

    private fun postdislik(videoo_idd: String?) {
        manager = SessionManager()
        token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient().getlikislik(
            videoo_idd, 0.toString(),
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_dislike) {
            override  fun onResponse(
                call: Call<LikeDislikPojo?>,
                response: Response<LikeDislikPojo?>
            ) {
                val likedislikeres: String ? = response.body()?.vote
                if (likedislikeres != null) {
                    if (likedislikeres.contains("1")) {
                    } else if (likedislikeres.contains("2")) {

            //                    toggle_dislike.setChecked(false);
            //                    toggle_like.setChecked(false);
                    } else if (likedislikeres.contains("3")) {

            //                    toggle_like.setChecked(false);
            //                    toggle_dislike.setChecked(true);
                    }
                }
            }
        })
    }

    companion object {
        var handler: Handler? = null
        private lateinit var exoPlayer: ExoPlayer
        private var adapter: RecyclerView.Adapter<*>? = null
        private var rv_commnetss: RecyclerView? = null
        var myOnClickListener1: View.OnClickListener? = null
        private fun onProgress() {
//        ExoPlayer player = exoPlayer;
            val position: Long = exoPlayer?.currentPosition ?: 0
            handler!!.removeCallbacks(updateProgressAction)
            val playbackState: Int =
                exoPlayer?.getPlaybackState() ?: Player.STATE_IDLE
            if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
                var delayMs: Long
                if (exoPlayer?.playWhenReady == true && playbackState == Player.STATE_READY) {
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

        private val updateProgressAction = Runnable { onProgress() }
        fun getExoPlayer(): ExoPlayer? {
            return exoPlayer
        }
    }
}