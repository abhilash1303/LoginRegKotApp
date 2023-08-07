package www.rahagloball.loginregkotapp.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
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
import www.rahagloball.loginregkotapp.models.cutyvids.CutChannel
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojo
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojoItem
import www.rahagloball.loginregkotapp.models.cutyvids.VotesItem
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo

class CuteVidAdptrHome(
    videoItemmList: List<CuteVidListPojoItem>,
    context: Context,
    viewPager2: ViewPager2
) : RecyclerView.Adapter<CuteVidAdptrHome.Videoviewholder>() {
    private var videoItemmList: List<CuteVidListPojoItem>
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
    var lisescntstr = 0
    var totelike = 0
    private var layoutManager: RecyclerView.LayoutManager? = null

    // Current playback position (in milliseconds).
    private val mCurrentPosition = 0

    //    public CuteVideoAdapter(List<CuteVidListPojoItem> videoItemmList) {
    //        this.videoItemmList = videoItemmList;
    //    }
    init {
        this.videoItemmList = videoItemmList
        this.context = context
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Videoviewholder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contnr_video, parent, false)
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
        try {
            holder.setVidoData(videoItemmList[position])
            val cutie_pause = holder.cutie_pause
            val cutie_play = holder.cutie_play
            val videoView: VideoView = holder.videoView
            val comnts_cnt: TextView = holder.comnts_cnt
            val likecount: TextView = holder.likecount
            val subscr_cts1: TextView = holder.subscr_cts
            val ll_shr_cutis: LinearLayout = holder.ll_shr_cutis
            val ll_cmnts_cutis: LinearLayout = holder.ll_cmnts_cutis
            val ll_ch_cts: LinearLayout = holder.ll_ch_cts
            val gobacku = holder.gobacku
            gobacku.visibility = View.GONE
            val toggle_like: ToggleButton = holder.toggle_like
            val toggle_dislike: ToggleButton = holder.toggle_dislike


//        String gd = subscr_cts1?.text.toString();
//        holder.add.setTag(gd);
            subscr_cts1.setOnClickListener(View.OnClickListener { v: View? -> subscr_cts1.setText("Stop Support") })

//
            videoView.setOnTouchListener(View.OnTouchListener { v: View?, event: MotionEvent? ->
                if (videoView.isPlaying()) {

//                        new Handler().postDelayed(() -> {
                    videoView.pause()
                    cutie_play.visibility = View.VISIBLE
                    cutie_pause.visibility = View.GONE

//                        }, 1000);
                } else {
                    videoView.start()
                    cutie_play.visibility = View.GONE
                    cutie_pause.visibility = View.VISIBLE
                }
                false
            })
            cutie_play.setOnClickListener { v: View? ->
                holder.setVidoData(videoItemmList[position])
                cutie_play.visibility = View.GONE
                cutie_pause.visibility = View.VISIBLE
            }
            cutie_pause.setOnClickListener { v: View? ->
                cutie_play.visibility = View.GONE
                cutie_pause.visibility = View.GONE
            }
            val votesItems: List<VotesItem>? = videoItemmList[position].votes
            if (votesItems != null) {
                for (j in votesItems.indices) {
                    vote_typee = votesItems[j].type
                    //                Log.e("commnnts_body_str",vote_typee);
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
                videoo_idd = videoItemmList[position]?.id
                postliked(videoo_idd)
                if (toggle_dislike.isChecked()) {
                    toggle_dislike.setChecked(false)
                }
                if (!toggle_like.isChecked()) {
                    lisescntstr -= 1
                    totelike = lisescntstr
                } else {
                    totelike = 1.let { lisescntstr += it; lisescntstr }.toString().toInt()
                }
                if (lisescntstr.toString().toInt() == 0) {
                    likecount.setText("Like")
                } else {
                    likecount.setText(totelike.toString())
                }
            })
            toggle_dislike.setOnClickListener(View.OnClickListener { view: View? ->
                videoo_idd = videoItemmList[position]?.id
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
            val cmts_cnt_str: String ?= videoItemmList[position].commentsCount
            comnts_cnt.setText(cmts_cnt_str)
            ll_shr_cutis.setOnClickListener(View.OnClickListener { view: View? ->

//                Toast.makeText(context, "share cutes clicked not sharing", Toast.LENGTH_SHORT).show();
                val vidddurlll: String? = videoItemmList[position].video
                //                Log.e("vidddurlllcts", vidddurlll);
                videoo_idd = videoItemmList[position]?.id

//                Log.e("videoo_iddlllcts", videoo_idd);

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
                    bottomSheetLayout?.findViewById<CircleImageView>(R.id.channel_pic_sub_cmnt)
                edit_commnt = bottomSheetLayout?.findViewById<EditText>(R.id.edit_commnt)
                send_commnett =
                    bottomSheetLayout?.findViewById<FloatingActionButton>(R.id.send_commnett)
                rv_commnetss = bottomSheetLayout!!.findViewById<RecyclerView>(R.id.rv_commnetss)
                edit_commnt?.post(Runnable {
                    edit_commnt?.requestFocus()
                    var imm: InputMethodManager? = null
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        imm = edit_commnt?.context
                            ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        imm!!.showSoftInput(edit_commnt, InputMethodManager.SHOW_IMPLICIT)
                    }
                })
                edit_commnt?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
                    // TODO Auto-generated method stub
                    if (view.id == R.id.edit_commnt) {
                        view.parent.requestDisallowInterceptTouchEvent(true)
                        when (event.getAction() and MotionEvent.ACTION_MASK) {
                            MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(
                                false
                            )
                        }
                    }
                    false
                })
                channel_pic_sub_cmnt?.let { Glide.with(context).load(checImgSt).into(it) }

//                Picasso.get().
//                        load(checImgSt)
//                        .into(channel_pic_sub_cmnt);
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
            //
                            val add_comments_res: String ? = response.body()?.comments
                            //                            Log.e("add_comments_res", add_comments_res);
                            if (add_comments_res != null) {
                                if (add_comments_res.contains("1")) {
                                    edit_commnt?.setText("")
                                    send_commnett?.visibility = View.GONE

                                    //                                hideKeyboardFrom(context, v1);
                                }
                            }
                        }
                    })
                })

//                rv_commnetss.setHasFixedSize(true);
                layoutManager = LinearLayoutManager(context)
                rv_commnetss?.setLayoutManager(layoutManager)
                rv_commnetss?.setItemAnimator(DefaultItemAnimator())
                lEarnCommnts
                mBottomSheetDialog = BottomSheetDialog(context)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }//                                nodata_cuties.visibility = View.GONE;

    //                                videoItemmList.add(new CuteVidListPojoItem());
//                                nodata_cuties.visibility = View.VISIBLE;
//                                rv_cuties.visibility = View.GONE;
//                        blur_reg1.visibility = View.GONE;
    //        blur_reg1.visibility = View.VISIBLE;
    private val lEarnCommnts: Unit
        private get() {

//        blur_reg1.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "cuties"
            RetrofitClient.getClient().gtcutevidlist(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CuteVidListPojo?>(send_commnett) {
                    @SuppressLint("SetTextI18n")
                 override   fun onResponse(
                        call: Call<CuteVidListPojo?>,
                        response: Response<CuteVidListPojo?>
                    ) {

//                        blur_reg1.visibility = View.GONE;
                        try {
                            videoItemmList= response.body()?.data!!
                            if (videoItemmList.isEmpty()) {

//                                nodata_cuties.visibility = View.VISIBLE;
//                                rv_cuties.visibility = View.GONE;
                            } else {
//                                nodata_cuties.visibility = View.GONE;
//                                videoItemmList.add(new CuteVidListPojoItem());
                                adapter = YtCommentsAdapterCut(videoItemmList, context)
                                rv_commnetss!!.adapter = adapter
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    //
    //    public static void hideKeyboardFrom(Context context, View view) {
    //        InputMethodManager imm = null;
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
    //            imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    //        }
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
    //            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    //        }
    //    }
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
        var views_vid: TextView
        var comnts_cnt: TextView
        var progressBar: ProgressBar
        var videoView: VideoView
        var cutie_pause: ImageView
        var cutie_play: ImageView
        var gobacku: ImageView
        var ll_shr_cutis: LinearLayout
        var ll_cmnts_cutis: LinearLayout
        var ll_ch_cts: LinearLayout
        var toggle_like: ToggleButton
        var toggle_dislike: ToggleButton
        var channel_pic_cts: CircleImageView
        private val vid_home: String? = null

        init {
            videoView = itemView.findViewById<VideoView>(R.id.cut_vidview)
            txttitle = itemView.findViewById<TextView>(R.id.txtvidtitle)
            //            txtDesc = itemView.findViewById(R.id.txtviddesc);
            views_vid = itemView.findViewById<TextView>(R.id.views_vid)
            comnts_cnt = itemView.findViewById<TextView>(R.id.comnts_cnt)
            progressBar = itemView.findViewById<ProgressBar>(R.id.vidPrgresbar)
            cutie_pause = itemView.findViewById<ImageView>(R.id.cutie_pause)
            cutie_play = itemView.findViewById<ImageView>(R.id.cutie_play)
            channel_pic_cts = itemView.findViewById<CircleImageView>(R.id.channel_pic_cts)
            subscr_cts = itemView.findViewById<TextView>(R.id.subscr_cts)
            ll_shr_cutis = itemView.findViewById<LinearLayout>(R.id.ll_shr_cutis)
            ll_cmnts_cutis = itemView.findViewById<LinearLayout>(R.id.ll_cmnts_cutis)
            toggle_like = itemView.findViewById<ToggleButton>(R.id.toggle_like)
            toggle_dislike = itemView.findViewById<ToggleButton>(R.id.toggle_dislike)
            likecount = itemView.findViewById<TextView>(R.id.likecount)
            channel_name_cts = itemView.findViewById<TextView>(R.id.channel_name_cts)
            ll_ch_cts = itemView.findViewById<LinearLayout>(R.id.ll_ch_cts)
            gobacku = itemView.findViewById<ImageView>(R.id.gobacku)
        }

        fun setVidoData(v_home: CuteVidListPojoItem) {
            try {
                progressBar.visibility = View.VISIBLE
                val title_str: String? = v_home.title
                txttitle.setText(title_str)
                val cutChannel: CutChannel? = v_home.channel
                val ch_cts_name: String =
                    Configs.BASE_URL21 + "images/channel/" + cutChannel?.image
                Glide.with(context).load(ch_cts_name).into(channel_pic_cts)
                val chnl_nm_cts: String? = cutChannel?.name
                channel_name_cts.text = chnl_nm_cts
                val vid_cuttt: String =
                    Configs.BASE_URL21 + "images/pool/video/" + v_home.video
                val newString = vid_cuttt.replace(" ", "%20")
                Log.e("vid_cuttt", newString)
                val uri = Uri.parse(newString)
                if (uri != null) videoView.setVideoPath(uri.toString())
                videoView.requestFocus()
                //                videoView.setMediaController(new MediaController(context));
                videoView.setOnPreparedListener { mp: MediaPlayer ->
                    progressBar.visibility = View.GONE
                    if (mCurrentPosition > 0) {
                        videoView.seekTo(mCurrentPosition)
                    } else {
                        // Skipping to 1 shows the first frame of the video.
                        videoView.seekTo(1)
                        val vidRatio: Float = mp.videoWidth / mp.videoHeight.toFloat()
                        val scrnRatio: Float =
                            videoView.width / videoView.height.toFloat()
                        val scalee = vidRatio / scrnRatio
                        if (scalee >= 1f) {
                            videoView.scaleX = scalee
                        } else {
                            videoView.scaleY = 1f / scalee
                        }
                    }
                    videoView.start()
                }
                videoView.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp: MediaPlayer ->


//                    Toast.makeText(context, R.string.toast_message, Toast.LENGTH_SHORT).show();
//
                    mp.seekTo(0)
                    mp.start()
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
                try {
                    val likedislikeres: String ? = response.body()?.vote
                    if (likedislikeres != null) {
                        manager!!.setPreferences(context, Constants.LIKE_HIT, likedislikeres)
                    }
                    if (likedislikeres != null) {
                        if (likedislikeres.contains("1")) {

                //                        toggle_dislike.setChecked(false);
                //                        toggle_like.setChecked(true);
                        } else if (likedislikeres.contains("2")) {
                        } else if (likedislikeres.contains("3")) {

                //                        toggle_dislike.setChecked(false);
                //                        toggle_like.setChecked(true);
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
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
            override   fun onResponse(
                call: Call<LikeDislikPojo?>,
                response: Response<LikeDislikPojo?>
            ) {
                try {
                    val likedislikeres: String ? = response.body()?.vote
                    if (likedislikeres != null) {
                        if (likedislikeres.contains("1")) {
                        } else if (likedislikeres.contains("2")) {

                //                        toggle_dislike.setChecked(false);
                //                        toggle_like.setChecked(false);
                        } else if (likedislikeres.contains("3")) {

                //                        toggle_like.setChecked(false);
                //                        toggle_dislike.setChecked(true);
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    companion object {
        private var adapter: RecyclerView.Adapter<*>? = null
        private var rv_commnetss: RecyclerView? = null
        var myOnClickListener1: View.OnClickListener? = null
    }
}