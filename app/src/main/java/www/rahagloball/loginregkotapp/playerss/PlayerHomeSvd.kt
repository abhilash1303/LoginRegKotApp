package www.rahagloball.loginregkotapp.playerss


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView
import org.apache.commons.lang3.RandomStringUtils
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.configuration.Utils
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.adapters.RelVidListAdapter
import www.rahagloball.loginregkotapp.adapters.YtCommentsAdapter
import www.rahagloball.loginregkotapp.constsnsesion.ActivitySwipeDetector
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.GestureDetection
import www.rahagloball.loginregkotapp.constsnsesion.SwipeInterface
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.addcmntres.CommentsAddpojo
import www.rahagloball.loginregkotapp.models.cffwalt.CfDataWalt
import www.rahagloball.loginregkotapp.models.cffwalt.WalBalncePojo
import www.rahagloball.loginregkotapp.models.issprtcheck.SspCheck
import www.rahagloball.loginregkotapp.models.issprtcheck.SspCheckPojo
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdChannel
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdComment
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdSupport
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdVideo
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdVote
import www.rahagloball.loginregkotapp.models.newsindletest.OneDataItem
import www.rahagloball.loginregkotapp.models.newsindletest.OneVidListPojo
import www.rahagloball.loginregkotapp.models.newvidsinglelist.DataItem
import www.rahagloball.loginregkotapp.models.relvidsubcat.RelVid
import www.rahagloball.loginregkotapp.models.relvidsubcat.RelatedVidPojo
import www.rahagloball.loginregkotapp.models.sprsprt.DataSs
import www.rahagloball.loginregkotapp.models.sprsprt.SupprtSprtPojo
import www.rahagloball.loginregkotapp.models.sprtt.SupporrtPojo
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import java.util.Arrays


class PlayerHomeSvd : AppCompatActivity(), SwipeInterface {
    var spiiner: ProgressBar? = null
    var fullScreenOp: ImageView? = null
    var frameLayout: RelativeLayout? = null

    //    VideoView videoPlayer;
    var vid_getid: TextView? = null
    var vid_home: String? = null
    var choice_data_str: String? = null
    var vote_typee: String? = null
    var title: TextView? = null
    var desc: TextView? = null

    // creating a variable for exoplayerview.
    //    SimpleExoPlayerView exoPlayerView;
    // creating a variable for exoplayer
    //    SimpleExoPlayer exoPlayer;
    //
    // creating a variable for exoplayer
    var exoPlayer: ExoPlayer? = null
    var exoPlayerView: PlayerView? = null
    var channel_pic_sub: CircleImageView? = null
    var suporters_count: TextView? = null
    var like_countt: TextView? = null
    var channel_name11: TextView? = null
    var support_btn: TextView? = null
    var supported_btn: TextView? = null
    var desccc: TextView? = null
    var comnts_cnt: TextView? = null
    var time_line: TextView? = null
    var views_vid: TextView? = null
    private val dataSet: List<DataItem> = ArrayList<DataItem>()
    var ll_descc: LinearLayout? = null
    var like_outlined: ImageView? = null
    var dislike_outline: ImageView? = null
    var like_filled: ImageView? = null
    var dislike_filled: ImageView? = null
    var contextt: Context? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    var bottomSheetLayout: View? = null
    var channel_pic_sub_btm: CircleImageView? = null
    var channel_pic_sub_cmnt: CircleImageView? = null
    var videoTitle_btm: TextView? = null
    var choice_data: TextView? = null
    var channel_name_btm: TextView? = null
    var token: String? = null
    var suptrs_cntt: String? = null
    var ch_id: String? = null
    var suprt_hit_str: String? = null
    var manager: SessionManager? = null
    var videoo_idd: String? = null
    var like_hit_str: String? = null
    var checImgSt: String? = null
    var edit_commnt_str: String? = null
    var comment_here: TextView? = null
    var edit_commnt: EditText? = null
    var support_cnt = 0
    var send_commnett: FloatingActionButton? = null
    private val lastClickTime: Long = 0

    //    int myNum = 0;;
    //    RecyclerView rv_commnetss;
    var blur_reg1: RelativeLayout? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var layoutManager_vidsList: RecyclerView.LayoutManager? = null
    var ll_sharee: LinearLayout? = null
    var ll_report_vid: LinearLayout? = null
    var ll_super_spprt: LinearLayout? = null
    var rankDialog: Dialog? = null
    var ss_btn: Button? = null
    var submit_dialog: TextView? = null
    var cancel_dialog: TextView? = null
    var edit_query: EditText? = null
    var radio_report: RadioGroup? = null
    var radioButton: RadioButton? = null
    var edit_query_str: String? = null
    var radioButton_str: String? = null
    var ch_pic_mmbr: CircleImageView? = null
    var ch_ban_img_mmbr: ImageView? = null
    var chanel_name_mmbr: TextView? = null
    var monthly_chrge: TextView? = null
    var qtrly_chrge: TextView? = null
    var yrly_chrge: TextView? = null
    var qtrl_ben_mmbr: TextView? = null
    var month_ben_mmbr: TextView? = null
    var yrly_ben_mmbr: TextView? = null
    var monthly_mmbr: Button? = null
    var qrtrlt_mmbr: Button? = null
    var yrly_mmbr: Button? = null
    var submit_dialog_cf: TextView? = null
    var cancel_dialog_cf: TextView? = null
    var bt_fullscreen: ImageView? = null
    var isFullScreen = false
    var isLock = false
    var handler: Handler? = null
    private var isShowingTrackSelectionDialog = false
    private var trackSelector: DefaultTrackSelector? = null
    var speed = arrayOf("0.25x", "0.5x", "Normal", "1.5x", "2x")

    //    String url111 = "https://5b44cf20b0388.streamlock.net:8443/vod/smil:bbb.smil/playlist.m3u8";
    var vid_speedddd: TextView? = null
    var vid_qualty: ImageView? = null
    var validity_txt: TextView? = null
    var customamount: String? = null
    var lisescntstr = 0
    var partialpayment: EditText? = null
    var walt_str: String? = null
    var phone_str: String? = null
    var nav_user_name_str: String? = null
    var nav_email_str: String? = null

    //    phone_str=profileItem.getMobile();
    //    nav_user_name_str = profileItem.getName();
    //    nav_email_str = profileItem.getEmail();
    var bt_lockscreen: ImageView? = null
    var progressBar: ProgressBar? = null
    var toggle_dslke: ToggleButton? = null
    var toggle_like: ToggleButton? = null
    var myLyk = 0
    var myLykStr: String? = null
    var detector: GestureDetection? = null
    var currentPosition = 0
    var audioManager: AudioManager? = null
    var swipe: ActivitySwipeDetector? = null
    var saved_vid_res: String? = null
    var saved_ivd_strrr: String? = null
    var ll_save_vid: LinearLayout? = null
    var ll_savedd_vid: LinearLayout? = null
    var ll_ch_viewww: LinearLayout? = null
    var totelike = 0

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.yt_view_demo)
        handler = Handler(Looper.getMainLooper())
        contextt = this@PlayerHomeSvd
        manager = SessionManager()
        token = manager?.getPreferences(this@PlayerHomeSvd, Constants.USER_TOKEN_LRN)
        suprt_hit_str = manager?.getPreferences(this@PlayerHomeSvd, Constants.SUPPORT_HIT)
        //        Log.e("suprt_hit_str", suprt_hit_str);
        swipe = ActivitySwipeDetector(this as SwipeInterface)
        //        detector = new GestureDetection(this, this);
//        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        exoPlayerView = findViewById<PlayerView>(R.id.player)
        support_btn = findViewById<TextView>(R.id.support_btn)
        supported_btn = findViewById<TextView>(R.id.supported_btn)
        ll_report_vid = findViewById<LinearLayout>(R.id.ll_report_vid)
        ll_super_spprt = findViewById<LinearLayout>(R.id.ll_super_spprt)
        ll_ch_viewww = findViewById<LinearLayout>(R.id.ll_ch_viewww)
        ss_btn = findViewById<Button>(R.id.ss_btn)
        bt_fullscreen = exoPlayerView?.findViewById<ImageView>(R.id.bt_fullscreen)
        bt_lockscreen = exoPlayerView?.findViewById<ImageView>(R.id.exo_lock)
        vid_speedddd = exoPlayerView?.findViewById<TextView>(R.id.vid_speedddd)
        vid_qualty = exoPlayerView?.findViewById<ImageView>(R.id.vid_qualty)
        exoPlayerView?.setOnTouchListener(swipe)

        ll_savedd_vid = findViewById<LinearLayout>(R.id.ll_savedd_vid)
        ll_save_vid = findViewById<LinearLayout>(R.id.ll_save_vid)
        saved_ivd_strrr = manager?.getPreferences(this@PlayerHomeSvd, Constants.VIDEO_SAVEEE)

        if (suprt_hit_str!!.contains("2")) {
            supported_btn?.visibility = (View.VISIBLE)
            support_btn?.visibility = (View.GONE)
        } else if (suprt_hit_str!!.contains("1")) {
            supported_btn?.visibility = (View.GONE)
            support_btn?.visibility = (View.VISIBLE)
        }


        if (saved_ivd_strrr!!.contains("1")) {
            ll_save_vid?.visibility = (View.GONE)
            ll_savedd_vid?.visibility = (View.VISIBLE)
        } else if (saved_ivd_strrr!!.contains("2")) {
            ll_save_vid?.visibility = (View.VISIBLE)
            ll_savedd_vid?.visibility = (View.GONE)
        }
        trackSelector = DefaultTrackSelector(this)
        vid_speedddd?.setOnClickListener(View.OnClickListener { view: View? ->
            val builder = AlertDialog.Builder(this@PlayerHomeSvd)
            builder.setTitle("Set Spped")
            builder.setItems(speed
            ) { dialogInterface, i ->
                if (i == 0) {
                    vid_speedddd?.visibility = (View.VISIBLE)
                    vid_speedddd?.text = "0.25x"
                    val playbackParameters = PlaybackParameters(0.5f)
                    exoPlayer?.playbackParameters = (playbackParameters)
                }
                if (i == 1) {
                    vid_speedddd?.visibility = View.VISIBLE
                    vid_speedddd?.text = ("0.5x")
                    val playbackParameters = PlaybackParameters(0.5f)
                    exoPlayer!!.playbackParameters = (playbackParameters)
                }
                if (i == 2) {
                    vid_speedddd?.visibility = (View.VISIBLE)
                    vid_speedddd?.text = ("Normal")
                    val playbackParameters = PlaybackParameters(1f)
                    exoPlayer?.playbackParameters = (playbackParameters)
                }
                if (i == 3) {
                    vid_speedddd!!.visibility = (View.VISIBLE)
                    vid_speedddd!!.text = ("1.5x")
                    val playbackParameters = PlaybackParameters(1.5f)
                    exoPlayer?.playbackParameters = (playbackParameters)
                }
                if (i == 4) {
                    vid_speedddd!!.visibility = (View.VISIBLE)
                    vid_speedddd!!.text = ("2x")
                    val playbackParameters = PlaybackParameters(2f)
                    exoPlayer?.playbackParameters = playbackParameters
                }
            }
            builder.show()
        })

//        vid_qualty!!.setOnClickListener { view: View? ->
//            if (!isShowingTrackSelectionDialog
//                && TrackSelectionDialog.willHaveContent(exoPlayer)
//            ) {
//                isShowingTrackSelectionDialog = true
//                val trackSelectionDialog: TrackSelectionDialog =
//                    TrackSelectionDialog.createForPlayer(
//                        exoPlayer
//                    )  /* onDismissListener= */
//                    { isShowingTrackSelectionDialog = false }
//                trackSelectionDialog.show(supportFragmentManager,  /* tag= */null)
//            }
//        }

        exoPlayerView = findViewById<PlayerView>(R.id.player)
        spiiner = findViewById<ProgressBar>(R.id.progressBar)
        channel_name11 = findViewById<TextView>(R.id.channel_name)
        support_btn = findViewById<TextView>(R.id.support_btn)
        supported_btn = findViewById<TextView>(R.id.supported_btn)
        comnts_cnt = findViewById<TextView>(R.id.comnts_cnt)
        //        exoPlayerView = findViewById(R.id.videoView);
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        bt_fullscreen = exoPlayerView?.findViewById<ImageView>(R.id.bt_fullscreen)
        bt_lockscreen = exoPlayerView?.findViewById<ImageView>(R.id.exo_lock)
        suporters_count = findViewById<TextView>(R.id.suporters_count)
        channel_pic_sub = findViewById<CircleImageView>(R.id.channel_pic_sub)
        time_line = findViewById<TextView>(R.id.time_line)
        views_vid = findViewById<TextView>(R.id.views_vid)
        ll_descc = findViewById<LinearLayout>(R.id.ll_descc)
        ll_sharee = findViewById<LinearLayout>(R.id.ll_sharee)

//        like_outlined = findViewById(R.id.like_outlined);
//        dislike_outline = findViewById(R.id.dislike_outline);
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        //        like_filled = findViewById(R.id.like_filled);
//        dislike_filled = findViewById(R.id.dislike_filled);
        like_countt = findViewById<TextView>(R.id.like_countt)
        choice_data = findViewById<TextView>(R.id.choice_data)
        comment_here = findViewById<TextView>(R.id.comment_here)
        toggle_dslke = findViewById<ToggleButton>(R.id.toggle_dslke)
        toggle_like = findViewById<ToggleButton>(R.id.toggle_like)
        rv_all_videos = findViewById<RecyclerView>(R.id.rv_all_videos)
        layoutManager_vidsList = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_all_videos!!.layoutManager = layoutManager_vidsList
        try {
            title = findViewById<TextView>(R.id.videoTitle)
            val i: Intent = intent
            val data: Bundle? = i.extras
            vid_home = intent.getStringExtra("video_id")
            val video: SvdVideo = data?.getSerializable("videoData") as SvdVideo
            val votesItems: List<SvdVote>?= video.votes

            if (votesItems != null) {
                for (j in votesItems.indices) {
                    vote_typee = votesItems[j].type
                    videoo_idd = video.id
                    if (vote_typee!!.contains("0")) {
                        postdislik(videoo_idd)
                    } else if (vote_typee!!.contains("1")) {
                        postliked(videoo_idd)
                    }
                }
            }
            
            val title_str: String? = video.title
            videoo_idd = video.id

            ll_save_vid?.setOnClickListener {
                videoo_idd = video.id
                getsavedvidid(videoo_idd)
            }
            ll_savedd_vid?.setOnClickListener {
                videoo_idd = video.id
                getsavedvidid(videoo_idd)
            }
            
            val lisescntstr1: List<SvdVote>? = video.votes
            if (lisescntstr1 != null) {
                lisescntstr = lisescntstr1.size.toString().toInt()
            }
            if (lisescntstr > 0) {
                like_countt?.text = lisescntstr.toString()
            } else {
                like_countt?.text = "Like"
            }


            toggle_like?.setOnClickListener {
                videoo_idd = video.id
                postliked(videoo_idd)
                if (toggle_dslke?.isChecked == true) {
                    toggle_dslke?.isChecked = (false)
                }
                if (!toggle_like!!.isChecked) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                } else {
                    totelike = 1.let { lisescntstr += it; lisescntstr }.toString().toInt()
                }
                if (lisescntstr.toString().toInt() == 0) {
                    like_countt?.text = ("Like")
                } else {
                    like_countt?.text = (totelike.toString())
                }
            }

            toggle_dslke?.setOnClickListener {
                videoo_idd = video.id
                postdislik(videoo_idd)
                if (toggle_like?.isChecked == true) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                    toggle_like?.isChecked = false
                }
                if (lisescntstr > 0) {
                    like_countt?.text = lisescntstr.toString()
                } else {
                    like_countt?.text = "Like"
                }
            }

            ll_sharee?.setOnClickListener(View.OnClickListener { v: View? ->
                val vidddurlll: String? = video.video
                videoo_idd = video.id
                if (vidddurlll != null) {
                    if (vidddurlll.contains(" ")) {
                        val title_str1: String? = video.title
                        val newString = vidddurlll.replace(" ", "%20")
                        val vidd_urll: String =
                            Configs.BASE_URL21 + "video/" + videoo_idd + "/" + newString
                        val sharingIntent = Intent(Intent.ACTION_SEND)
                        sharingIntent.type = "text/plain"
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch $title_str1")
                        sharingIntent.putExtra(
                            Intent.EXTRA_TEXT, "Watch now on NationLearns, " +
                                    "India's first social e-learning platform " + vidd_urll
                        )
                        startActivity(Intent.createChooser(sharingIntent, "Share via"))
                    }
                }
            })


            val time_line_str: String? = video.createdAt
            time_line?.text = time_line_str

//            String views_vid_str = video.getViewsCount();
//            views_vid.setText(views_vid_str + " Views");
            val svdvidsViews: List<SvdVote>? = video.votes
            val itemCount = svdvidsViews?.size
            val shortenedViews: String? = itemCount?.let { Utils.getShortenedViewsString(it) }
            views_vid?.text = "$shortenedViews Views"
            val svdComments: List<SvdComment>? = video.comments
            val cmts_cnt_str = svdComments?.size
            //            String cmts_cnt_str = video.getCommentsCount();
            comnts_cnt?.setText(cmts_cnt_str.toString())
            title?.text = title_str
            bt_fullscreen!!.setOnClickListener { view: View? ->
                if (isFullScreen) {
                    bt_fullscreen!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@PlayerHomeSvd,
                            R.drawable.ic_baseline_fullscreen
                        )
                    )
                    window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
                    if (supportActionBar != null) {
                        supportActionBar?.hide()
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    val params: LinearLayout.LayoutParams =
                        exoPlayerView?.layoutParams as LinearLayout.LayoutParams
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = (200 * applicationContext.resources
                        .displayMetrics.density).toInt()
                    exoPlayerView?.setLayoutParams(params)
                    isFullScreen = false
                } else {
                    bt_fullscreen!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@PlayerHomeSvd,
                            R.drawable.ic_baseline_fullscreen_exit
                        )
                    )
                    window.decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    )
                    if (getSupportActionBar() != null) {
                        getSupportActionBar()?.hide()
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    val params: LinearLayout.LayoutParams =
                        exoPlayerView?.getLayoutParams() as LinearLayout.LayoutParams
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT
                    exoPlayerView?.setLayoutParams(params)
                    isFullScreen = true
                }
            }
            bt_lockscreen!!.setOnClickListener { view: View? ->
                //change icon base on toggle lock screen or unlock screen
                if (!isLock) {
                    bt_lockscreen!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.ic_baseline_lock
                        )
                    )
                } else {
                    bt_lockscreen!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.ic_outline_lock_open
                        )
                    )
                }
                isLock = !isLock
                //method for toggle will do next
                lockScreen(isLock)
            }
            exoPlayer = ExoPlayer.Builder(this).build()
            exoPlayerView?.player = exoPlayer

            //screen alway active
            exoPlayerView?.keepScreenOn = true

            //track state
            exoPlayer?.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    //when data video fetch stream from internet
                    if (playbackState == Player.STATE_BUFFERING) {
                        progressBar?.visibility = View.VISIBLE
                    } else if (playbackState == Player.STATE_READY) {
                        //then if streamed is loaded we hide the progress bar
                        progressBar?.visibility = View.GONE
                    }
                    if (!exoPlayer?.playWhenReady!!) {
                        handler!!.removeCallbacks(updateProgressAction)
                    } else {
                        onProgress()
                    }
                }
            })
            val vid_data: String = Configs.BASE_URL21 + "images/pool/video/" + video.video
            //pass the video link and play
            val videoUrl = Uri.parse(vid_data)
            val media = MediaItem.fromUri(videoUrl)
            exoPlayer?.setMediaItem(media)
            exoPlayer?.prepare()
            exoPlayer?.play()


            val channell: SvdChannel? = video.channel
            val ch_namee: String? = channell?.name
            val svdSupportList: List<SvdSupport>? = video.channel?.supports
            //            int sprt_cnt_str = svdComments.size();
            suptrs_cntt = svdSupportList?.size.toString()
            channel_name11?.text = ch_namee
            suporters_count?.text = "$suptrs_cntt Supporters"
            checImgSt = Configs.BASE_URL21 + "images/channel/" + channell?.image
            val ch_idd_strtr: String? = channell?.id
            
            ll_ch_viewww?.setOnClickListener { view: View? ->
                val intent = Intent(this@PlayerHomeSvd, ChannelProfileActivity::class.java)
                intent.putExtra("ch_iddCuties", ch_idd_strtr)
                startActivity(intent)
            }

            comment_here?.setOnClickListener { v: View? ->
                bottomSheetLayout = LayoutInflater.from(this).inflate(R.layout.bottom_comment, null)
                channel_pic_sub_cmnt =
                    bottomSheetLayout?.findViewById<CircleImageView>(R.id.channel_pic_sub_cmnt)
                edit_commnt = bottomSheetLayout?.findViewById<EditText>(R.id.edit_commnt)
                send_commnett =
                    bottomSheetLayout?.findViewById<FloatingActionButton>(R.id.send_commnett)
                rv_commnetss = bottomSheetLayout!!.findViewById<RecyclerView>(R.id.rv_commnetss)
                edit_commnt?.post(Runnable {
                    edit_commnt?.requestFocus()
                    val imm = edit_commnt?.context
                        ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(edit_commnt, InputMethodManager.SHOW_IMPLICIT)
                })
                edit_commnt?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
                    // TODO Auto-generated method stub
                    if (view.id == R.id.edit_commnt) {
                        view.parent.requestDisallowInterceptTouchEvent(true)
                        when (event.action and MotionEvent.ACTION_MASK) {
                            MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(
                                false
                            )
                        }
                    }
                    false
                })
                channel_pic_sub_cmnt?.let {
                    Glide.with(contextt as PlayerHomeSvd).load(checImgSt)
                        .into(it)
                }
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
                        send_commnett?.visibility =
                            if (s.length > 0) View.VISIBLE else View.INVISIBLE
                    }
                })
                send_commnett?.setOnClickListener(View.OnClickListener { v1: View? ->

//
                    videoo_idd = video.id
                    edit_commnt_str = edit_commnt?.text.toString()
                    RetrofitClient.getClient().add_comments(
                        videoo_idd, edit_commnt_str,
                        "application/json",
                        "Bearer $token"
                    )?.enqueue(object : GlobalCallback<CommentsAddpojo?>(like_outlined) {
                        override fun onResponse(
                            call: Call<CommentsAddpojo?>,
                            response: Response<CommentsAddpojo?>
                        ) {
//
                            val add_comments_res: String? = response.body()?.comments
                            //                            Log.e("add_comments_res", add_comments_res);
                            if (add_comments_res?.contains("1") == true) {
                                edit_commnt?.setText("")
                                send_commnett?.visibility = View.GONE

//                                hideKeyboardFrom(contextt, v1);
                            }
                        }
                    })
                })

//                rv_commnetss.setHasFixedSize(true);
                layoutManager = LinearLayoutManager(this@PlayerHomeSvd)
                rv_commnetss?.layoutManager = layoutManager
                rv_commnetss?.itemAnimator = DefaultItemAnimator()
                lEarnCommnts
                mBottomSheetDialog = BottomSheetDialog(this)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            }

            support_btn?.setOnClickListener(View.OnClickListener { v: View? ->

//                ll_super_spprt.visibility = View.VISIBLE;
                supported_btn?.visibility = View.VISIBLE
                support_btn?.visibility = View.GONE
                ch_id = channell?.id
                hitsupport(ch_id)
            })
            //
            supported_btn?.setOnClickListener(View.OnClickListener { v: View? ->

//                ll_super_spprt.visibility = View.GONE;
                supported_btn?.visibility = View.GONE
                support_btn?.visibility = View.VISIBLE
                ch_id = channell?.id
                hitsupport(ch_id)
            })



            contextt?.let {
                channel_pic_sub?.let { it1 ->
                    Glide.with(it).load(checImgSt).into(
                        it1
                    )
                }
            }
//            Glide.with(contextt as PlayerHomeSvd).load(checImgSt)
//                .into(channel_pic_sub)
            
            ll_descc?.setOnClickListener(View.OnClickListener { v: View? ->

//            String id_orderlist = String.valueOf(dataset.get(position).getId());
                bottomSheetLayout =
                    LayoutInflater.from(this).inflate(R.layout.frame_layout_example, null)
                desccc = bottomSheetLayout?.findViewById<TextView>(R.id.desccc)
                videoTitle_btm = bottomSheetLayout?.findViewById<TextView>(R.id.videoTitle_btm)
                channel_name_btm = bottomSheetLayout?.findViewById<TextView>(R.id.channel_name_btm)
                channel_pic_sub_btm =
                    bottomSheetLayout?.findViewById<CircleImageView>(R.id.channel_pic_sub_btm)
                val desccc_str: String? = video.description
                desccc?.text = desccc_str

//                String videoTitle_btm_str = video.getTitle();
                videoTitle_btm?.text = title_str
                channel_name_btm?.text = ch_namee
                val channel_pic_sub_btm_str: String =
                    Configs.BASE_URL21 + "images/channel/" + channell?.image
                channel_pic_sub_btm?.let {
                    Glide.with(contextt as PlayerHomeSvd).load(channel_pic_sub_btm_str)
                        .into(it)
                }
                mBottomSheetDialog = BottomSheetDialog(this)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            })


            ss_btn!!.setOnClickListener { v: View? ->
                val ch_iddddd: String? = channell?.id
                RetrofitClient.getClient().ssp_pckg(
                    ch_iddddd, "application/json",
                    "Bearer $token"
                )?.enqueue(object : GlobalCallback<SupprtSprtPojo?>(videoTitle_btm) {
                    override fun onResponse(
                        call: Call<SupprtSprtPojo?>,
                        response: Response<SupprtSprtPojo?>
                    ) {
                        try {
                            val ss_stus: String? = response.body()?.status
                            if (ss_stus != null) {
                                if (ss_stus.contains("1")) {
                                    bottomSheetLayout = LayoutInflater.from(contextt)
                                        .inflate(R.layout.membrship_btm, null)
                                    ch_pic_mmbr =
                                        bottomSheetLayout!!.findViewById<CircleImageView>(R.id.ch_pic_mmbr)
                                    ch_ban_img_mmbr =
                                        bottomSheetLayout!!!!.findViewById<ImageView>(R.id.ch_ban_img_mmbr)
                                    chanel_name_mmbr =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.chanel_name_mmbr)
                                    monthly_chrge =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.monthly_chrge)
                                    qtrly_chrge =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.qtrly_chrge)
                                    yrly_chrge =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.yrly_chrge)
                                    qtrl_ben_mmbr =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.qtrl_ben_mmbr)
                                    month_ben_mmbr =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.month_ben_mmbr)
                                    yrly_ben_mmbr =
                                        bottomSheetLayout!!.findViewById<TextView>(R.id.yrly_ben_mmbr)
                                    monthly_mmbr =
                                        bottomSheetLayout!!.findViewById<Button>(R.id.monthly_mmbr)
                                    qrtrlt_mmbr =
                                        bottomSheetLayout!!.findViewById<Button>(R.id.qrtrlt_mmbr)
                                    yrly_mmbr =
                                        bottomSheetLayout!!.findViewById<Button>(R.id.yrly_mmbr)
                                    chanel_name_mmbr?.text = (ch_namee)

                                    val channel_pic_sub_btm_str1: String =
                                        Configs.BASE_URL21 + "images/channel/" + channell?.image
                                    val dataSs: DataSs? = response.body()?.data
                                    val monthle_bal: String? = dataSs?.monthlyPrice
                                    monthly_chrge?.text = (monthle_bal)
                                    val halfyr_bal: String? = dataSs?.halfyPrice
                                    qtrly_chrge?.text = (halfyr_bal)
                                    val yrly_bal: String? = dataSs?.yearlyPrice
                                    yrly_chrge?.text = (yrly_bal)
                                    val monthle_ben: String? = dataSs?.monthlyBenefit
                                    //                                        month_ben_mmbr.text=(monthle_ben);
                                    val halfyr_ben: String? = dataSs?.halfyBenefit
                                    //                                        qtrl_ben_mmbr.text=(halfyr_ben);
                                    val yrly_ben: String? = dataSs?.yearlyBenefit
                                    //                                        yrly_ben_mmbr.setText(yrly_ben);
                                    val jsonArray = JSONArray(monthle_ben)
                                    val list: MutableList<String> = ArrayList()
                                    for (i in 0 until jsonArray.length()) {
                                        list.add(jsonArray.getString(i))
                                    }
                                    //
                                    val stringArray = list.toTypedArray()
                                    val ssnew = Arrays.toString(stringArray)
                                    val stryryryr =
                                        ssnew.replace("\\[".toRegex(), "")
                                            .replace("\\]".toRegex(), "")
                                    month_ben_mmbr?.text = (stryryryr)
                                    //
                                    val jsonArray2 = JSONArray(monthle_ben)
                                    val list2: MutableList<String> = ArrayList()
                                    for (i in 0 until jsonArray2.length()) {
                                        list2.add(jsonArray2.getString(i))
                                    }
                                    //
                                    val stringArray2 = list.toTypedArray()
                                    val ssnew2 = Arrays.toString(stringArray2)
                                    val stryryryr2 =
                                        ssnew2.replace("\\[".toRegex(), "")
                                            .replace("\\]".toRegex(), "")
                                    qtrl_ben_mmbr?.text = (stryryryr2)
                                    //
                                    val jsonArray3 = JSONArray(monthle_ben)
                                    val list3: MutableList<String> = ArrayList()
                                    for (i in 0 until jsonArray3.length()) {
                                        list3.add(jsonArray3.getString(i))
                                    }
                                    //
                                    val stringArray3 = list.toTypedArray()
                                    val ssnew3 = Arrays.toString(stringArray3)
                                    val stryryryr3 =
                                        ssnew3.replace("\\[".toRegex(), "")
                                            .replace("\\]".toRegex(), "")
                                    yrly_ben_mmbr?.text = (stryryryr3)

                                    monthly_mmbr?.setOnClickListener(View.OnClickListener { v1: View? ->
                                        if (monthle_bal != null) {
                                            if (walt_str!!.toFloat() < monthle_bal.toFloat()) {
                                                Toast.makeText(
                                                    contextt,
                                                    "No wallet Balance Kindly recharge",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                walletcheck()
                                            } else {
                                                val ch_iddddd: String? = channell?.id
                                                RetrofitClient.getClient().ssp_pckg_check(
                                                    ch_iddddd, "application/json",
                                                    "Bearer $token"
                                                )?.enqueue(object :
                                                    GlobalCallback<SspCheckPojo?>(videoTitle_btm) {
                                                    @SuppressLint("SetTextI18n")
                                                    override fun onResponse(
                                                        call: Call<SspCheckPojo?>,
                                                        response: Response<SspCheckPojo?>
                                                    ) {
                                                        try {
                                                            val issprtcheck: String? =
                                                                response.body()?.status
                                                            if (issprtcheck != null) {
                                                                if (issprtcheck.contains("1")) {
                                                                    val sspCheck: SspCheck? =
                                                                        response.body()?.data
                                                                    val validitystr: String? =
                                                                        sspCheck?.planExpiresAt
                                                                    if (validitystr != null) //                                                                        Log.e("validitystr", validitystr);
                                                                        rankDialog = Dialog(
                                                                            this@PlayerHomeSvd,
                                                                            R.style.FullHeightDialog
                                                                        )
                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text =
                                                                        ("You have an active super support valid till $validitystr would you like to coutinue")
                                                                    submit_dialog?.setOnClickListener { vv: View? ->
                                                                        val orderidd: String =
                                                                            RandomStringUtils.randomAlphanumeric(
                                                                                10
                                                                            )
                                                                        val transIDd: String =
                                                                            RandomStringUtils.randomAlphanumeric(
                                                                                10
                                                                            )

                                                                        val ch_iddddd: String? =
                                                                            channell?.id
                                                                        postcftodb(
                                                                            "add-supersupport",
                                                                            ch_iddddd,
                                                                            monthle_bal,
                                                                            "monthly"
                                                                        )
                                                                    }
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()

                                                                } else if (issprtcheck.contains("0")) {

                                                                    //
                                                                    rankDialog = Dialog(
                                                                        this@PlayerHomeSvd,
                                                                        R.style.FullHeightDialog
                                                                    )


                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text = (
                                                                            monthle_bal
                                                                            )

                                                                    submit_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? ->
                                                                            val orderidd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )
                                                                            val transIDd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )

                                                                            //                                                                            plan_type=> monthly, half_yearly, yearly
                                                                            val ch_iddddd: String? =
                                                                                channell?.id
                                                                            postcftodb(
                                                                                "add-supersupport",
                                                                                ch_iddddd,
                                                                                monthle_bal,
                                                                                "monthly"
                                                                            )
                                                                        })
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()
                                                                }
                                                            }
                                                        } catch (e: Exception) {
                                                            e.printStackTrace()
                                                        }
                                                    }
                                                })
                                            }
                                        }
                                    })

                                    qrtrlt_mmbr?.setOnClickListener(View.OnClickListener { v1: View? ->
                                        if (halfyr_bal != null) {
                                            if (walt_str!!.toFloat() < halfyr_bal.toFloat()) {
                                                Toast.makeText(
                                                    contextt,
                                                    "No wallet Balance Kindly recharge",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                walletcheck()
                                            } else {
                                                val ch_iddddd: String? = channell?.id
                                                RetrofitClient.getClient().ssp_pckg_check(
                                                    ch_iddddd, "application/json",
                                                    "Bearer $token"
                                                )?.enqueue(object :
                                                    GlobalCallback<SspCheckPojo?>(videoTitle_btm) {
                                                    @SuppressLint("SetTextI18n")
                                                    override fun onResponse(
                                                        call: Call<SspCheckPojo?>,
                                                        response: Response<SspCheckPojo?>
                                                    ) {
                                                        try {
                                                            val issprtcheck: String? =
                                                                response.body()?.status
                                                            if (issprtcheck != null) {
                                                                if (issprtcheck.contains("1")) {
                                                                    val sspCheck: SspCheck? =
                                                                        response.body()!!.data
                                                                    val validitystr: String? =
                                                                        sspCheck?.planExpiresAt
                                                                    rankDialog = Dialog(
                                                                        this@PlayerHomeSvd,
                                                                        R.style.FullHeightDialog
                                                                    )
                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text =
                                                                        ("You have an active super support valid till $validitystr would you like to coutinue")
                                                                    submit_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? ->
                                                                            val orderidd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )
                                                                            val transIDd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )


                                                                            //                                                                            plan_type=> monthly, half_yearly, yearly
                                                                            val ch_iddddd: String? =
                                                                                channell?.id
                                                                            postcftodb(
                                                                                "add-supersupport",
                                                                                ch_iddddd,
                                                                                halfyr_bal,
                                                                                "half_yearly"
                                                                            )
                                                                        })
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()
                                                                } else if (issprtcheck.contains("0")) {

                                                                    //
                                                                    rankDialog = Dialog(
                                                                        this@PlayerHomeSvd,
                                                                        R.style.FullHeightDialog
                                                                    )
                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text =
                                                                        (halfyr_bal)
                                                                    submit_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? ->
                                                                            val orderidd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )
                                                                            val transIDd: String =
                                                                                RandomStringUtils.randomAlphanumeric(
                                                                                    10
                                                                                )
                                                                            val ch_iddddd: String? =
                                                                                channell?.id
                                                                            postcftodb(
                                                                                "add-supersupport",
                                                                                ch_iddddd,
                                                                                halfyr_bal,
                                                                                "half_yearly"
                                                                            )
                                                                        })
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()
                                                                }
                                                            }
                                                        } catch (e: Exception) {
                                                            e.printStackTrace()
                                                        }
                                                    }


                                                })
                                            }
                                        }
                                    })


                                    yrly_mmbr?.setOnClickListener(View.OnClickListener { v1: View? ->
                                        if (yrly_bal != null) {
                                            if (walt_str!!.toFloat() < yrly_bal.toFloat()) {
                                                Toast.makeText(
                                                    contextt,
                                                    "No wallet Balance Kindly recharge",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                walletcheck()
                                            } else {
                                                val ch_iddddd: String? = channell?.id
                                                RetrofitClient.getClient().ssp_pckg_check(
                                                    ch_iddddd, "application/json",
                                                    "Bearer $token"
                                                )?.enqueue(object :
                                                    GlobalCallback<SspCheckPojo?>(videoTitle_btm) {
                                                    @SuppressLint("SetTextI18n")
                                                    override fun onResponse(
                                                        call: Call<SspCheckPojo?>,
                                                        response: Response<SspCheckPojo?>
                                                    ) {
                                                        try {
                                                            val issprtcheck: String? =
                                                                response.body()?.status
                                                            if (issprtcheck != null) {
                                                                if (issprtcheck.contains("1")) {
                                                                    val sspCheck: SspCheck? =
                                                                        response.body()?.data
                                                                    val validitystr: String? =
                                                                        sspCheck?.planExpiresAt
                                                                    rankDialog = Dialog(
                                                                        this@PlayerHomeSvd,
                                                                        R.style.FullHeightDialog
                                                                    )
                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog!!.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog!!.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text =
                                                                        ("You have an active super support valid till $validitystr would you like to coutinue")
                                                                    submit_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? ->
                                                                            val ch_iddddd: String? =
                                                                                channell?.id
                                                                            postcftodb(
                                                                                "add-supersupport",
                                                                                ch_iddddd,
                                                                                yrly_bal,
                                                                                "yearly"
                                                                            )
                                                                        })
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()
                                                                } else if (issprtcheck.contains("0")) {

                                                                    //
                                                                    rankDialog = Dialog(
                                                                        this@PlayerHomeSvd,
                                                                        R.style.FullHeightDialog
                                                                    )
                                                                    rankDialog?.setContentView(R.layout.dialog_toshow_val)
                                                                    rankDialog!!.setCancelable(true)
                                                                    submit_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.submit_dialog
                                                                        )
                                                                    cancel_dialog =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.cancel_dialog
                                                                        )
                                                                    validity_txt =
                                                                        rankDialog?.findViewById<TextView>(
                                                                            R.id.validity_txt
                                                                        )
                                                                    validity_txt?.text = (yrly_bal)
                                                                    submit_dialog?.setOnClickListener { vv: View? ->
                                                                        val ch_iddddd: String? =
                                                                            channell?.id
                                                                        postcftodb(
                                                                            "add-supersupport",
                                                                            ch_iddddd,
                                                                            yrly_bal,
                                                                            "yearly"
                                                                        )
                                                                    }
                                                                    cancel_dialog?.setOnClickListener(
                                                                        View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                                                    rankDialog!!.show()
                                                                }
                                                            }
                                                        } catch (e: Exception) {
                                                            e.printStackTrace()
                                                        }
                                                    }


                                                })
                                            }
                                        }
                                    })


                                    //                                Glide.with(contextt).load(channel_pic_sub_btm_str1)
                                    //                                    .into(ch_pic_mmbr)

                                    contextt?.let {
                                        ch_pic_mmbr?.let { it1 ->
                                            Glide.with(it).load(channel_pic_sub_btm_str1).into(
                                                it1
                                            )
                                        }
                                    }

                                    mBottomSheetDialog = BottomSheetDialog(contextt as PlayerHomeSvd)
                                    mBottomSheetDialog!!.setContentView(bottomSheetLayout!!)
                                    mBottomSheetDialog!!.show()
                                } else if (ss_stus != null) {
                                    if (ss_stus.contains("0")) {
                                        rankDialog =
                                            Dialog(this@PlayerHomeSvd, R.style.FullHeightDialog)
                                        rankDialog!!.setContentView(R.layout.dialog_ssp_first)
                                        rankDialog!!.setCancelable(true)
                                        submit_dialog =
                                            rankDialog?.findViewById<TextView>(R.id.submit_dialog)
                                        cancel_dialog =
                                            rankDialog?.findViewById<TextView>(R.id.cancel_dialog)
                                        submit_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                        cancel_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                                        rankDialog!!.show()

                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }


                })
            }

            ll_report_vid?.setOnClickListener(View.OnClickListener { v: View? ->
                rankDialog = Dialog(this@PlayerHomeSvd, R.style.FullHeightDialog)
                rankDialog!!.setContentView(R.layout.dialog_vid_report)
                rankDialog!!.setCancelable(true)
                edit_query = rankDialog!!.findViewById<EditText>(R.id.edit_query)
                submit_dialog = rankDialog!!.findViewById<TextView>(R.id.submit_dialog)
                cancel_dialog = rankDialog!!.findViewById<TextView>(R.id.cancel_dialog)
                radio_report = rankDialog!!.findViewById<RadioGroup>(R.id.radio_report)
                //            servc_spinr = rankDialog?.findViewById(R.id.servc_spinr);
                edit_query?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
                    // TODO Auto-generated method stub
                    if (view.id == R.id.edit_query) {
                        view.parent.requestDisallowInterceptTouchEvent(true)
                        when (event.getAction() and MotionEvent.ACTION_MASK) {
                            MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(
                                false
                            )
                        }
                    }
                    false
                })
                submit_dialog?.setOnClickListener {
                    radio_report = rankDialog?.findViewById(R.id.radio_report)
                    edit_query_str = edit_query?.text.toString()
                    val selectedId: Int? = radio_report?.checkedRadioButtonId
                    radioButton = selectedId?.let { rankDialog!!.findViewById<RadioButton>(it) }
                    radioButton_str = radioButton?.text.toString()

//                    Log.e("radioButton_str",radioButton_str);
                    if (selectedId == -1) {
//
                        Toast.makeText(this, "Report something!", Toast.LENGTH_SHORT).show()
                        //
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                        if (edit_query_str == "" || edit_query_str!!.isEmpty()) {

                            //                        videoo_idd = video.getId();
                            //                        report_video(videoo_idd);
                            Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            //                        Toast.makeText(this, "No api", Toast.LENGTH_SHORT).show();
                            videoo_idd = video!!.id
                            RetrofitClient.getClient().vid_reportt(
                                videoo_idd, radioButton_str, edit_query_str,
                                "application/json",
                                "Bearer $token"
                            )?.enqueue(object : GlobalCallback<String?>(edit_query) {
                                override fun onResponse(
                                    call: Call<String?>,
                                    response: Response<String?>
                                ) {
                                    val languagestr  = response.body()?.toString()
                                    rankDialog!!.dismiss()
                                    if (languagestr != null) {
                                        if (languagestr.contains("1")) {
                                            Toast.makeText(
                                                this@PlayerHomeSvd,
                                                "Report Successful!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            rankDialog!!.dismiss()
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
                cancel_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                rankDialog!!.show()
            })

            val vid_subcatid_str: String? = video.subCategory

            if (vid_subcatid_str != null) {
                getLEarnVids(vid_subcatid_str)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        cfWaltbal
        profileimage()
    }

    private val cfWaltbal: Unit
        get() {
            val url: String = Configs.BASE_URL2 + "get-balance"
            RetrofitClient.getClient().getwaltinfoo(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<WalBalncePojo?>(submit_dialog) {
                    override fun onResponse(
                        call: Call<WalBalncePojo?>,
                        response: Response<WalBalncePojo?>
                    ) {
//                        rl_cbl.visibility = (View.GONE);
                        try {
                            val cf_walt_sts: String? = response.body()?.staus
                            if (cf_walt_sts != null) {
                                if (cf_walt_sts.contains("0")) {
                                    //                                Toast.makeText(PlayerHomeRel.this, "No data", Toast.LENGTH_SHORT).show();
                                } else if (cf_walt_sts.contains("1")) {
                                    val cfDataWalt: CfDataWalt? = response.body()?.data
                                    walt_str = cfDataWalt?.balance
                                    //                                walt1_submit1.text=(walt_str);
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }


    private fun getsavedvidid(videoo_iddd: String?) {
        RetrofitClient.getClient().saveviddd(videoo_iddd, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<String?>(channel_name11) {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    blur_reg1?.visibility = (View.GONE)
                    saved_vid_res  = response.body()?.toString()
                    manager?.setPreferences(
                        this@PlayerHomeSvd, Constants.VIDEO_SAVEEE,
                        saved_vid_res!!
                    )
                    if (saved_vid_res!!.contains("1")) {
                        // successful
                        Toast.makeText(this@PlayerHomeSvd, "Video is saved!!", Toast.LENGTH_SHORT)
                            .show()
                        ll_savedd_vid?.visibility = (View.VISIBLE)
                        ll_save_vid?.visibility = (View.GONE)
                    } else if (saved_vid_res!!.contains("2")) {
                        ll_savedd_vid?.visibility = (View.GONE)
                        ll_save_vid?.visibility = (View.VISIBLE)
                        Toast.makeText(
                            this@PlayerHomeSvd,
                            "Removed from savings!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }


    private val updateProgressAction = Runnable { onProgress() }

    //at 4 second
    var ad: Long = 4000
    var check = false
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

    fun lockScreen(lock: Boolean) {
        //just hide the control for lock screen and vise versa
        val sec_mid: LinearLayout = findViewById<LinearLayout>(R.id.sec_controlvid1)
        val sec_bottom: LinearLayout = findViewById<LinearLayout>(R.id.sec_controlvid2)
        if (lock) {
            sec_mid.visibility = (View.INVISIBLE)
            sec_bottom.visibility = (View.INVISIBLE)
        } else {
            sec_mid.visibility = (View.VISIBLE)
            sec_bottom.visibility = (View.VISIBLE)
        }
    }

    //when is in lock screen we not accept for backpress button
    override fun onBackPressed() {
        //on lock screen back press button not work
        if (isLock) return

        //if user is in landscape mode we turn to portriat mode first then we can exit the app.
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bt_fullscreen!!.performClick()
        } else super.onBackPressed()
    }

    // pause or release the player prevent memory leak
    override fun onStop() {
        super.onStop()
        exoPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.playWhenReady = false
        exoPlayer?.playbackState
        //        exoPlayer.pause();
    }

    private fun profileimage() {

//        progressBar.visibility = (View.VISIBLE);
//        asscoId = manager?.getPreferences(MyProfile.this, "assoc_id");
//        Log.e("asscoIdStrr", asscoId);
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(chanel_name_mmbr) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
//                progressBar.visibility = (View.GONE);
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        val profileItem: ProfileItem = (profileItems?.get(0) ?: "") as ProfileItem
                        phone_str = profileItem.mobile
                        nav_user_name_str = profileItem.name
                        nav_email_str = profileItem.email
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    @SuppressLint("ClickableViewAccessibility")
    fun walletcheck() {
        Toast.makeText(contextt, "No wallet Balance Kindly recharge", Toast.LENGTH_SHORT).show()
        rankDialog = Dialog(this@PlayerHomeSvd, R.style.FullHeightDialog)
        rankDialog!!.setContentView(R.layout.nl_walletnew)
        rankDialog!!.setCancelable(true)
        partialpayment = rankDialog!!.findViewById<EditText>(R.id.partialpayment_cf)
        submit_dialog_cf = rankDialog!!.findViewById<TextView>(R.id.submit_dialog_cf)
        cancel_dialog_cf = rankDialog!!.findViewById<TextView>(R.id.cancel_dialog_cf)
        partialpayment?.setOnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.id == R.id.partialpayment_cf) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        }
        submit_dialog_cf?.setOnClickListener(View.OnClickListener { view: View? ->

//            hideKeyboardFrom(PlayerSearch.this, view);
            customamount = partialpayment?.text.toString()
            try {
                customamount = partialpayment?.text.toString()
                val num = customamount!!.toInt()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (customamount!!.isEmpty() || num < 1) {
                        Toast.makeText(
                            this@PlayerHomeSvd,
                            "Kindly Enter a valid Amount!  ",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        gettkncfpay(customamount)
                        rankDialog!!.dismiss()
                    }
                }
            } catch (ex: NumberFormatException) { // handle your exception
            }
        })
        cancel_dialog_cf?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
        rankDialog!!.show()
    }

    fun gettkncfpay(cfamountpay: String?) {
        blur_reg1?.visibility = (View.VISIBLE)
        val orderidd: String = RandomStringUtils.randomAlphanumeric(10)
        //        Log.e("orderidd",orderidd);
//
//        RetrofitClient.getClient().gettokencf(orderidd, cfamountpay, "INR",
//                        "application/json", "Bearer " + token).
//                enqueue(new GlobalCallback<CashFripojo>(partialpayment) {
//                    @Override
//                    public void onResponse(Call<CashFripojo> call, Response<CashFripojo> response) {
//                        blur_reg1.visibility = View.GONE;
//
//                        try {
//
//                            if (response.isSuccessful()) {
//                                if (response.body().getMessage().equals("Token generated")) {
//                                    String token1 ? = response.body()?.getCftoken();
//                                    Map<String, String> params = new HashMap<>();
//                                    params.put(PARAM_APP_ID, "287591d80ab19b3f8bae5cb1ab195782");
//                                    params.put(PARAM_ORDER_ID, orderidd);
//                                    params.put(PARAM_ORDER_AMOUNT, cfamountpay);
//                                    params.put(PARAM_ORDER_NOTE, "NL Payment done");
//                                    if (nav_user_name_str != null)
//                                        params.put(PARAM_CUSTOMER_NAME, nav_user_name_str);
//                                    if (phone_str != null)
//                                        params.put(PARAM_CUSTOMER_PHONE, phone_str);
//                                    if (nav_email_str != null)
//                                        params.put(PARAM_CUSTOMER_EMAIL, nav_email_str);
//
//                                    params.put(PARAM_ORDER_CURRENCY, "INR");
//
////                                    for (Map.Entry entry : params.entrySet()) {
////                                        Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
////                                    }
//
//                                    CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
//                                    cfPaymentService.setOrientation(0);
//                                    cfPaymentService.doPayment(PlayerHomeSvd.this, params, token1, "PROD", "#174778", "#FFFFFF", false);
////                                    Intent intentpay = new Intent(PlayerHomeSvd.this, PlayerHomeSvd.class);
////                                    startActivity(intentpay);
//
//                                } else {
//                                    Toast.makeText(PlayerHomeSvd.this, "msg2" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(PlayerHomeSvd.this, "msg3" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                });
    }


    fun postcftodb(
        request_typee: String?,
        channel_idw: String?,
        amountt: String?,
        plan_typer: String?)
    {
        blur_reg1?.visibility=(View.VISIBLE)
        RetrofitClient.getClient().pay_success_wallet(
            request_typee, channel_idw, amountt, plan_typer,
            "application/json", "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(partialpayment) {
            override  fun onResponse(call: Call<String?>, response: Response<String?>) {
                blur_reg1?.visibility=(View.GONE)
                try {
                    val cf_res  = response.body()?.toString()
                    //                            Log.e("cf_responseee", cf_res);
                    if (response.isSuccessful) {
                        rankDialog = Dialog(this@PlayerHomeSvd, R.style.FullHeightDialog)
                        rankDialog!!.setContentView(R.layout.walet_sucs_tick)
                        rankDialog!!.setCancelable(true)
                        rankDialog!!.show()

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun getLEarnVids(subcatIdStr: String) {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "relatedVideo" + "/" + subcatIdStr
        RetrofitClient.getClient().rellvidList(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<RelatedVidPojo?>(PlayerHomeSvd.rv_all_videos) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<RelatedVidPojo?>,
                    response: Response<RelatedVidPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val catggryList: List<RelVid>? = response.body()?.data
                        if (catggryList != null) {
                            if (catggryList.isEmpty()) {
                                PlayerHomeSvd.rv_all_videos!!.visibility = View.GONE
                            } else {
                                PlayerHomeSvd.rv_all_videos!!.visibility = View.VISIBLE
                                PlayerHomeSvd.adapter_vids_list =
                                    RelVidListAdapter(catggryList, this@PlayerHomeSvd)
                                PlayerHomeSvd.rv_all_videos!!.adapter = PlayerHomeSvd.adapter_vids_list
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private val lEarnCommnts: Unit
        get() {

//        blur_reg1.visibility = (View.VISIBLE);
            val url: String = Configs.BASE_URL2 + "videos-list"
            RetrofitClient.getClient().allvidList(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<OneVidListPojo?>(PlayerHomeSvd.rv_commnetss) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<OneVidListPojo?>,
                        response: Response<OneVidListPojo?>
                    ) {

//                        blur_reg1.visibility = (View.GONE);
                        try {
                            val catggryList: List<OneDataItem>? = response.body()?.data
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {

                                } else {
                                    PlayerHomeSvd.rv_commnetss!!.visibility = View.VISIBLE
                                    PlayerHomeSvd.adapter = catggryList.let {
                                        YtCommentsAdapter(
                                            it,
                                            this@PlayerHomeSvd
                                        )
                                    }
                                    PlayerHomeSvd.rv_commnetss!!.adapter = PlayerHomeSvd.adapter
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    private fun hitsupport(chh_iddstr: String?) {

        token = manager?.getPreferences(this@PlayerHomeSvd, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient().hitsuppportt(chh_iddstr, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<SupporrtPojo?>(title) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<SupporrtPojo?>,
                    response: Response<SupporrtPojo?>
                ) {
                    val res: String? = response.body()?.support
                    if (res != null) {
                        manager?.setPreferences(this@PlayerHomeSvd, Constants.SUPPORT_HIT, res)
                    }
                    if (res != null) {
                        if (res.contains("2")) {
                            support_cnt = suptrs_cntt!!.toInt()
                            support_cnt++
                            suporters_count?.text = ("$support_cnt Supporters")
                            supported_btn?.visibility = (View.VISIBLE)
                            support_btn?.visibility = (View.GONE)
                        }
                    }
                    if (res != null) {
                        if (res.contains("1")) {
                            support_cnt = suptrs_cntt!!.toInt()
                            suporters_count?.text = ("$support_cnt Supporters")
                            supported_btn?.visibility = (View.GONE)
                            support_btn?.visibility = (View.VISIBLE)
                        }
                    }
                }
            })
    }

    private fun postliked(viid: String?) {
        choice_data_str = choice_data?.text.toString()
        RetrofitClient.getClient()
            .getlikislik(viid, 1.toString(), "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<LikeDislikPojo?>(like_outlined) {
                override fun onResponse(
                    call: Call<LikeDislikPojo?>,
                    response: Response<LikeDislikPojo?>
                ) {
                    val likedislikeres: String? = response.body()?.vote
                    if (likedislikeres != null) {
                        manager?.setPreferences(
                            this@PlayerHomeSvd,
                            Constants.LIKE_HIT,
                            likedislikeres
                        )
                    }
                    if (likedislikeres != null) {
                        if (likedislikeres.contains("1")) {
                            //                    toggle_dslke.setChecked(false);
                            //                    toggle_like.setChecked(true);
                        } else if (likedislikeres.contains("2")) {
                        } else if (likedislikeres.contains("3")) {
                            //                    toggle_dslke.setChecked(false);
                            //                    toggle_like.setChecked(true);
                        }
                    }
                }
            })
    }

    private fun postdislik(viid: String?) {
        choice_data_str = choice_data?.text.toString()
        RetrofitClient.getClient().getlikislik(
            viid, 0.toString(),
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<LikeDislikPojo?>(like_outlined) {
            override fun onResponse(
                call: Call<LikeDislikPojo?>,
                response: Response<LikeDislikPojo?>
            ) {
                val likedislikeres: String? = response.body()?.vote
                if (likedislikeres != null) {
                    if (likedislikeres.contains("1")) {
                    } else if (likedislikeres.contains("2")) {
                    } else if (likedislikeres.contains("3")) {
                    }
                }
            }
        })
    }

    protected override fun onResume() {
        super.onResume()
//        if (isPIPModeEnabled) {
//            exoPlayer?.playWhenReady = true
//        } else {
////            initializePlayer();
//        }

//        postliked(videoo_idd);
//        hitsupport(ch_id);
    }

    override fun bottom2top(v: View?) {
        if (isFullScreen) {
            bt_fullscreen!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this@PlayerHomeSvd,
                    R.drawable.ic_baseline_fullscreen
                )
            )
            window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
            supportActionBar?.hide()
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            val params: LinearLayout.LayoutParams =
                exoPlayerView?.layoutParams as LinearLayout.LayoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height =
                (200 * applicationContext.resources.displayMetrics.density).toInt()
            exoPlayerView?.layoutParams = params
            isFullScreen = false
        } else {
            bt_fullscreen!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this@PlayerHomeSvd,
                    R.drawable.ic_baseline_fullscreen_exit
                )
            )
            window.decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            )
            if (supportActionBar != null) {
                supportActionBar?.hide()
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            val params: LinearLayout.LayoutParams =
                exoPlayerView?.layoutParams as LinearLayout.LayoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            exoPlayerView?.setLayoutParams(params)
            isFullScreen = true
        }
    }

    override fun left2right(v: View?) {
        currentPosition = exoPlayer?.currentPosition?.toInt() ?: 0
        currentPosition = ((exoPlayer?.currentPosition ?: 0) + 10000).toInt()
        exoPlayer?.seekTo(currentPosition.toLong())
    }

    override fun right2left(v: View?) {
        currentPosition = exoPlayer?.currentPosition?.toInt() ?: 0
        currentPosition = ((exoPlayer?.currentPosition ?: 0) - 10000).toInt()
        exoPlayer?.seekTo(currentPosition.toLong())
    }

    override fun top2bottom(v: View?) {
        onBackPressed()
    }


    companion object {
        const val TAG = "TAG"
        private var adapter: RecyclerView.Adapter<*>? = null
        private var rv_commnetss: RecyclerView? = null
        private var rv_all_videos: RecyclerView? = null
        var myOnClickListener1: View.OnClickListener? = null
        private var adapter_vids_list: RecyclerView.Adapter<*>? = null
    }
}