package www.rahagloball.loginregkotapp.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.activities.ExoPlayerItem1
import www.rahagloball.loginregkotapp.activities.TCreatChanelActvty
import www.rahagloball.loginregkotapp.activities.TUploadCts
import www.rahagloball.loginregkotapp.activities.camera.CameraActivity
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.addcmntres.CommentsAddpojo
import www.rahagloball.loginregkotapp.models.cutyvids.CutChannel
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojo
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojoItem
import www.rahagloball.loginregkotapp.models.cutyvids.VotesItem
import www.rahagloball.loginregkotapp.models.getchanldata.DataItem
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo
import java.util.Timer
import java.util.TimerTask

class CuteVidAdptrHomeAltered(
    videoItemmList: List<CuteVidListPojoItem>,
    context: Context, viewPager2: ViewPager2
) :
    RecyclerView.Adapter<CuteVidAdptrHomeAltered.Videoviewholder>() {
    private var videoItemmList: List<CuteVidListPojoItem>
    private lateinit var context: Context
    private lateinit var viewPager2: ViewPager2
    private lateinit var intent1: Intent
    private lateinit var votetypee: String
    private lateinit var videoo_idd: String
    private lateinit var token: String
    private lateinit var checImgSt: String
    private lateinit var editcommntstr: String
    private lateinit var manager: SessionManager
    private lateinit var toggle_like: ToggleButton
    private lateinit var toggle_dislike: ToggleButton
    private lateinit var mBottomSheetDialog: BottomSheetDialog
    private lateinit var bottomSheetLayout: View
    private lateinit var channel_pic_sub_btm: CircleImageView
    private lateinit var channel_pic_sub_cmnt: CircleImageView
    private lateinit var editcommnt: EditText
    private lateinit var videoTitle_btm: TextView
    private lateinit var choice_data: TextView
    private lateinit var channel_name_btm: TextView
    private lateinit var send_commnett: FloatingActionButton
    private var lisescntstr = 0
    private var totelike = 0
    private var playThis = -1
    private lateinit var pool_id_str: String
    private lateinit var oldPlayer: ExoPlayer // keep track of the old player instance
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val playerList: List<ExoPlayer> = ArrayList<ExoPlayer>()
    private val currentPosition = -1
    private val exoPlayerItems: ArrayList<ExoPlayerItem1>
    private lateinit var mMediaPlayer: MediaPlayer
    var count = 0
    private lateinit var uploadCuties: LinearLayout
    private var isPlaying = false
    private val currentPlayingPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Videoviewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contnr_videoalter, parent, false)
        val myviewholder: Videoviewholder = Videoviewholder(view)
        handler = Handler(Looper.getMainLooper())
        manager = SessionManager()
        token = manager.getPreferences(context, Constants.USER_TOKEN_LRN)
        toggle_like = view.findViewById<ToggleButton>(R.id.toggle_like)
        toggle_dislike = view.findViewById<ToggleButton>(R.id.toggle_dislike)
        uploadCuties = view.findViewById<LinearLayout>(R.id.uploadCuties)
        return myviewholder
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: Videoviewholder, position: Int) {
        try {
            holder.setVideoData(videoItemmList[position], position)
            val cutie_pause = holder.cutie_pause
            val cutie_play = holder.cutie_play
            val videoView: VideoView = holder.videoView
            val exoPlayer: ExoPlayer? = holder.exoPlayer
            val comnts_cnt: TextView = holder.comnts_cnt
            val likecount: TextView = holder.likecount
            val subscr_cts1: TextView = holder.subscr_cts
            val mShareCuties: LinearLayout = holder.mShareCuties
            val ll_cmnts_cutis: LinearLayout = holder.ll_cmnts_cutis
            val ll_ch_cts: LinearLayout = holder.ll_ch_cts
            val gobacku = holder.gobacku
            val mPlayerContainer: ConstraintLayout = holder.playerContainer
            uploadCuties = holder.uploadCuties
            val fab_uc: FloatingActionButton = holder.fab_uc
            gobacku.visibility = View.GONE
            val toggle_like: ToggleButton = holder.toggle_like
            val toggle_dislike: ToggleButton = holder.toggle_dislike
            subscr_cts1.setOnClickListener { v: View? -> subscr_cts1.text = "Stop Support" }
            mShareCuties.setOnClickListener { view: View? ->
                val url: String = videoItemmList[position].video
                videoo_idd = videoItemmList[position].id.toString()
                val title_str1: String = videoItemmList[position].title!!
                val newString = url.replace(" ", "%20")
                val newUrl: String = Configs.BASE_URL21 + "video/" + videoo_idd + "/" + newString
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch $title_str1")
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT, "Watch now on NationLearns, " +
                            "India's first social e-learning platform " + newUrl
                )
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
            }
            val votesItems: List<VotesItem> = videoItemmList[position].votes!!
            for (j in votesItems.indices) {
                votetypee = votesItems[j].type.toString()
                videoo_idd = videoItemmList[position].id.toString()
                if (votetypee.contains("0")) {
                    postdislik(videoo_idd)
                } else if (votetypee.contains("1")) {
                    postliked(videoo_idd)
                }
            }
            videoo_idd = videoItemmList[position].id.toString()
            lisescntstr = videoItemmList[position].votesCount?.toInt()!!
            if (lisescntstr > 0) {
                likecount.text = lisescntstr.toString()
            } else {
                likecount.text = "Like"
            }
            toggle_like.setOnClickListener(View.OnClickListener { view: View? ->
                videoo_idd = videoItemmList[position].id.toString()
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
                videoo_idd = videoItemmList[position].id.toString()
                postdislik(videoo_idd)
                if (toggle_like.isChecked) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                    toggle_like.isChecked = false
                }
                if (lisescntstr > 0) {
                    likecount.text = lisescntstr.toString()
                } else {
                    likecount.text = "Like"
                }
            })
            val cmts_cnt_str: String? = videoItemmList[position].commentsCount
            comnts_cnt.setText(cmts_cnt_str)
            val channell: CutChannel? = videoItemmList[position].channel
            ll_ch_cts.setOnClickListener(View.OnClickListener { view: View? ->
                val ch_id_ctsss: String? = channell?.id
                val intent = Intent(context, ChannelProfileActivity::class.java)
                intent.putExtra("ch_iddCuties", ch_id_ctsss)
                context.startActivity(intent)
            })
            checImgSt = Configs.BASE_URL21 + "images/channel/" + channell?.image
            ll_cmnts_cutis.setOnClickListener(View.OnClickListener { v: View? ->
                bottomSheetLayout =
                    LayoutInflater.from(context).inflate(R.layout.bottom_comment, null)
                channel_pic_sub_cmnt =
                    bottomSheetLayout.findViewById<CircleImageView>(R.id.channel_pic_sub_cmnt)
                editcommnt = bottomSheetLayout.findViewById<EditText>(R.id.edit_commnt)
                send_commnett =
                    bottomSheetLayout.findViewById<FloatingActionButton>(R.id.send_commnett)
                rv_commnetss = bottomSheetLayout.findViewById<RecyclerView>(R.id.rv_commnetss)
                editcommnt.post(Runnable {
                    editcommnt.requestFocus()
                    var imm: InputMethodManager? = null
                    imm =
                        editcommnt.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm!!.showSoftInput(editcommnt, InputMethodManager.SHOW_IMPLICIT)
                })
                editcommnt.setOnTouchListener { view: View, event: MotionEvent ->
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
                }
                channel_pic_sub_cmnt.let {
                    Glide.with(context).load(checImgSt).into(it)
                }
                editcommnt.addTextChangedListener(object : TextWatcher {
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
                        send_commnett.visibility =
                            if (s.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    }
                })
                send_commnett.setOnClickListener { v1: View? ->
                    videoo_idd = videoItemmList[position].id.toString()
                    editcommntstr = editcommnt.text.toString()
                    RetrofitClient.getClient().add_comments(
                        videoo_idd, editcommntstr, "application/json",
                        "Bearer $token"
                    )?.enqueue(object : GlobalCallback<CommentsAddpojo?>(send_commnett) {
                        override fun onResponse(
                            call: Call<CommentsAddpojo?>,
                            response: Response<CommentsAddpojo?>
                        ) {
                            val add_comments_res: String? = response.body()?.comments
                            if (add_comments_res?.contains("1") == true) {
                                editcommnt.setText("")
                                send_commnett.visibility = View.GONE
                            }
                        }
                    })
                }
                layoutManager = LinearLayoutManager(context)
                rv_commnetss?.layoutManager = layoutManager
                rv_commnetss?.itemAnimator = DefaultItemAnimator()
                lEarnCommnts
                mBottomSheetDialog = BottomSheetDialog(context)
                mBottomSheetDialog.setContentView(bottomSheetLayout)
                mBottomSheetDialog.show()
            })
            mPlayerContainer.setOnClickListener { v: View? ->
                if (mMediaPlayer.isPlaying == true) {
                    cutie_play.visibility = View.GONE
                    cutie_pause.visibility = View.VISIBLE
                    mMediaPlayer.pause()
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            (context as Activity).runOnUiThread {
                                cutie_pause.visibility = View.GONE
                            }
                        }
                    }, 1500)
                } else {
                    cutie_pause.visibility = View.GONE
                    cutie_play.visibility = View.VISIBLE
                    mMediaPlayer.start()
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            (context as Activity).runOnUiThread(
                                Runnable { cutie_play.visibility = View.GONE }
                            )
                        }
                    }, 1500)
                }
            }
            uploadCuties.setOnClickListener { uc: View? -> getbizcnttedlist() }
            fab_uc.setOnClickListener { uc: View? -> getbizcnttedlist() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getbizcnttedlist() {
        val url: String = Configs.BASE_URL2 + "channel"
        RetrofitClient.getClient().getchanInfo(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<GetChanlPojo?>(editcommnt) {
                override fun onResponse(
                    call: Call<GetChanlPojo?>,
                    response: Response<GetChanlPojo?>
                ) {
                    try {
                        val catggryList: List<DataItem>? = response.body()?.data
                        if (catggryList != null) {
                            for (i in catggryList.indices) {
                                val dataItem: DataItem = catggryList[i]
                                pool_id_str = dataItem.poolId.toString()
                            }
                        }
                        updateUIBasedOnPoolId()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun updateUIBasedOnPoolId() {
        if (pool_id_str.isEmpty()) {
            // Show a message indicating that the user needs to create a channel first
            Toast.makeText(
                context.applicationContext,
                "Create a Channel first!!",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(context.applicationContext, TCreatChanelActvty::class.java)
            context.startActivity(intent)
        } else {
            val intent = Intent(context.applicationContext, TUploadCts::class.java)
            context.startActivity(intent)
        }
    }

    private val lEarnCommnts: Unit
        get() {
            val url: String = Configs.BASE_URL2 + "cuties"
            RetrofitClient.getClient().gtcutevidlist(url, "application/json", "Bearer $token")
                ?.enqueue(object : GlobalCallback<CuteVidListPojo?>(send_commnett) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<CuteVidListPojo?>,
                        response: Response<CuteVidListPojo?>
                    ) {
                        try {
                            videoItemmList = response.body()?.data!!
                            if (videoItemmList.isEmpty()) {
                            } else {
                                adapter = YtCommentsAdapterCut(videoItemmList, context)
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
        var subscr_cts: TextView
        var channel_name_cts: TextView
        var likecount: TextView
        var time_line: TextView? = null
        var comnts_cnt: TextView
        var progressBar: ProgressBar
        var exoPlayer: ExoPlayer? = null
        var videoView: VideoView
        var cutie_pause: ImageView
        var cutie_play: ImageView
        var gobacku: ImageView
        var mShareCuties: LinearLayout
        var ll_cmnts_cutis: LinearLayout
        var ll_ch_cts: LinearLayout
        var uploadCuties: LinearLayout
        var toggle_like: ToggleButton
        var toggle_dislike: ToggleButton
        var channel_pic_cts: CircleImageView
        var playerContainer: ConstraintLayout
        private val vid_home: String? = null
        var fab_uc: FloatingActionButton

        init {
            txttitle = itemView.findViewById<TextView>(R.id.txtvidtitle)
            videoView = itemView.findViewById<VideoView>(R.id.cut_vidview)
            comnts_cnt = itemView.findViewById<TextView>(R.id.comnts_cnt)
            progressBar = itemView.findViewById<ProgressBar>(R.id.vidPrgresbar)
            cutie_pause = itemView.findViewById<ImageView>(R.id.cutie_pause)
            cutie_play = itemView.findViewById<ImageView>(R.id.cutie_play)
            channel_pic_cts = itemView.findViewById<CircleImageView>(R.id.channel_pic_cts)
            subscr_cts = itemView.findViewById<TextView>(R.id.subscr_cts)
            mShareCuties = itemView.findViewById<LinearLayout>(R.id.shareCuties)
            ll_cmnts_cutis = itemView.findViewById<LinearLayout>(R.id.ll_cmnts_cutis)
            toggle_like = itemView.findViewById<ToggleButton>(R.id.toggle_like)
            toggle_dislike = itemView.findViewById<ToggleButton>(R.id.toggle_dislike)
            likecount = itemView.findViewById<TextView>(R.id.likecount)
            channel_name_cts = itemView.findViewById<TextView>(R.id.channel_name_cts)
            ll_ch_cts = itemView.findViewById<LinearLayout>(R.id.ll_ch_cts)
            gobacku = itemView.findViewById<ImageView>(R.id.gobacku)
            playerContainer = itemView.findViewById<ConstraintLayout>(R.id.playerContainer)
            val camera = itemView.findViewById<ImageView>(R.id.ivCamera)
            uploadCuties = itemView.findViewById<LinearLayout>(R.id.uploadCuties)
            fab_uc = itemView.findViewById<FloatingActionButton>(R.id.fab_uc)
            camera.setOnClickListener { view: View ->
                val intent = Intent(view.context, CameraActivity::class.java)
                view.context.startActivity(intent)
            }
        }

        fun setVideoData(video: CuteVidListPojoItem, position: Int) {
            val cacheDir = context.cacheDir
            val cacheFiles = cacheDir.listFiles()
            txttitle.text = video.title
            val ch_cts_name: String = Configs.BASE_URL21 + "images/channel/" + video.channel?.image
            Glide.with(context).load(ch_cts_name).into(channel_pic_cts)
            channel_name_cts.text = video.channel?.name
            assert(cacheFiles != null)

            val cachePath = itemView.context.cacheDir.path + "/" + video.video
//            Log.e("cachePath", cachePath)
            videoView.setVideoPath(cachePath)
//

            videoView.setOnPreparedListener { mediaPlayer ->
                mMediaPlayer = mediaPlayer
                mediaPlayer.start()
                progressBar.visibility = View.GONE
            }
            videoView.setOnCompletionListener { mediaPlayer ->
                mMediaPlayer = mediaPlayer
                mediaPlayer.start()
            }
            videoView.setOnErrorListener { mediaPlayer, what, extra ->
                mMediaPlayer = mediaPlayer
                true
            }
        }
    }

    init {
        this.videoItemmList = videoItemmList
        this.context = context
        this.viewPager2 = viewPager2
        exoPlayerItems = ArrayList<ExoPlayerItem1>()
    }

    private fun postliked(videoo_idd: String?) {
        manager = SessionManager()
        token = manager.getPreferences(context, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient()
            .getlikislik(videoo_idd, 1.toString(), "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_like) {
                override fun onResponse(
                    call: Call<LikeDislikPojo?>,
                    response: Response<LikeDislikPojo?>
                ) {
                    try {
                        val likedislikeres: String? = response.body()?.vote
                        manager.setPreferences(context, Constants.LIKE_HIT, likedislikeres!!)
                        if (likedislikeres.contains("1")) {
                        } else if (likedislikeres.contains("2")) {
                        } else if (likedislikeres.contains("3")) {
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun postdislik(videoo_idd: String?) {
        manager = SessionManager()
        token = manager.getPreferences(context, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient()
            .getlikislik(videoo_idd, 0.toString(), "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_dislike) {
                override fun onResponse(
                    call: Call<LikeDislikPojo?>,
                    response: Response<LikeDislikPojo?>
                ) {
                    try {
                        val likedislikeres: String? = response.body()?.vote
                        if (likedislikeres != null) {
                            if (likedislikeres.contains("1")) {
                            } else if (likedislikeres.contains("2")) {
                            } else if (likedislikeres.contains("3")) {
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    companion object {
        var handler: Handler? = null
        private var adapter: RecyclerView.Adapter<*>? = null
        private var rv_commnetss: RecyclerView? = null
        var myOnClickListener1: View.OnClickListener? = null
    }
}
