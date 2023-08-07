package www.rahagloball.loginregkotapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
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
import www.rahagloball.loginregkotapp.activities.SnglChCtsActvt
import www.rahagloball.loginregkotapp.adapters.SnglChCtsAdapter.Videoviewholder.Companion.toggle_dislike
import www.rahagloball.loginregkotapp.adapters.SnglChCtsAdapter.Videoviewholder.Companion.toggle_like
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.addcmntres.CommentsAddpojo
import www.rahagloball.loginregkotapp.models.chctsfront.CtsSingleChPojo
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsChId
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsChannel
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsVote
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo

class SnglChCtsAdapter(

    videoItemmList: List<FrntCtsChId>,
    context: Context,
    viewPager2: ViewPager2
) : RecyclerView.Adapter<SnglChCtsAdapter.Videoviewholder>() {
    private var context: Context
    private var videoItemmList: List<FrntCtsChId>
    var viewPager2: ViewPager2
    var intent1: Intent? = null
    var vote_typee: String? = null
    var videoo_idd: String? = null
    var checImgSt: String? = null
    var edit_commnt_str: String? = null
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
    var ttt = 1
    var totelike = 0
    private var layoutManager: RecyclerView.LayoutManager? = null

    init {
        this.videoItemmList = videoItemmList
        this.context = context
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Videoviewholder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contnr_video, parent, false)
        val myviewholder = Videoviewholder(view)
        manager = SessionManager()
        token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
        toggle_like = view.findViewById<ToggleButton>(R.id.toggle_like)
        toggle_dislike = view.findViewById<ToggleButton>(R.id.toggle_dislike)


//        videoItemmList = new ArrayList<>();
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
            val gobacku = holder.gobacku
            val ll_shr_cutis: LinearLayout = holder.ll_shr_cutis
            val ll_cmnts_cutis: LinearLayout = holder.ll_cmnts_cutis
            val ll_ch_cts: LinearLayout = holder.ll_ch_cts
            val toggle_like: ToggleButton = Videoviewholder.toggle_like
            val toggle_dislike: ToggleButton = Videoviewholder.toggle_dislike
            gobacku.setOnClickListener { view: View? -> (context as SnglChCtsActvt).finish() }
            subscr_cts1.setOnClickListener(View.OnClickListener { v: View? -> subscr_cts1.text = "Stop Support" })
            videoView.setOnClickListener(View.OnClickListener { view: View? ->
                cutie_pause.visibility = View.GONE
                if (videoView.isPlaying()) {
//
                    videoView.pause()
                    cutie_play.visibility = View.VISIBLE
                    cutie_pause.visibility = View.GONE
                } else {
                    videoView.start()
                    cutie_play.visibility = View.GONE
                    cutie_pause.visibility = View.VISIBLE
                }
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
            videoo_idd = videoItemmList[position].id
            val votesItems: List<FrntCtsVote>? = videoItemmList[position].votes
            if (votesItems != null) {
                for (j in votesItems.indices) {
                    vote_typee = votesItems[j].type
                    //                Log.e("commnnts_body_str",vote_typee);
                    if (vote_typee == "0") {
                        postdislik(videoo_idd)
                        //                    toggle_dislike.setChecked(true);
                    } else if (vote_typee == "1") {
                        postliked(videoo_idd)
                        //                    toggle_like.setChecked(true);
                    }
                }
            }
            videoo_idd = videoItemmList[position].id
            lisescntstr = videoItemmList[position].votesCount?.toInt()!!
            //            Log.e("lisescntstr", String.valueOf(lisescntstr));
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
                    /*Integer.parseInt(String.valueOf(*/lisescntstr += 1
                    totelike = lisescntstr /*))*/
                }
                if ( /*Integer.parseInt(String.valueOf(*/lisescntstr /*))*/ == 0) {
                    likecount.setText("Like")
                } else {
                    likecount.setText( /*totelike*/lisescntstr.toString())
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


//            lisescntstr = videoItemmList.get(position).getVotesCount();
//
//            if (lisescntstr.isEmpty() || lisescntstr.equals("0")) {
//                likecount.setText("Like");
//            }
//
//
//            toggle_like.setOnClickListener(v -> {
//
////            Toast.makeText(context, "liked", Toast.LENGTH_SHORT).show();
//                videoo_idd = videoItemmList.get(position).getId();
//                postliked(videoo_idd);
//
//                lisescntstr = String.valueOf(Integer.parseInt(videoItemmList.get(position).getVotesCount()) + ttt);
//                likecount.setText(lisescntstr);
//
//
//                if (toggle_dislike.isChecked()) {
//                    toggle_dislike.setChecked(false);
//                }
//
//
//            });
//
//
//            toggle_dislike.setOnClickListener(v -> {
////            Toast.makeText(context, "disliked", Toast.LENGTH_SHORT).show();
//                videoo_idd = videoItemmList.get(position).getId();
//                postdislik(videoo_idd);
//
//                lisescntstr = String.valueOf(Integer.parseInt(videoItemmList.get(position).getVotesCount()) - ttt);
//
//                if (Integer.parseInt(lisescntstr) <= zerooo) {
//                    likecount.setText("Like");
//                } else {
//                    likecount.setText(lisescntstr);
//                }
//
//                if (toggle_like.isChecked()) {
//
//                    toggle_like.setChecked(false);
//                }
//
//            });
//
            val cmts_cnt_str: String? = videoItemmList[position].commentsCount
            comnts_cnt.setText(cmts_cnt_str)
            ll_shr_cutis.setOnClickListener(View.OnClickListener { view: View? ->
                val vidddurlll: String? = videoItemmList[position].video
                videoo_idd = videoItemmList[position].id

//            if (vidddurlll.contains(" ")) {
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
            val channell: FrntCtsChannel? = videoItemmList[position].channel
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


//                Picasso.get().
                channel_pic_sub_cmnt?.let {
                    Glide.with(context).load(checImgSt)
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
                        send_commnett?.visibility = if (s.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                    }
                })
                send_commnett?.setOnClickListener(View.OnClickListener { v1: View? ->
                    videoo_idd = videoItemmList[position].id
                    edit_commnt_str = edit_commnt?.text.toString()
                    RetrofitClient.getClient().add_comments(
                        videoo_idd, edit_commnt_str,
                        "application/json",
                        "Bearer " + token
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
                rv_commnetss?.layoutManager = layoutManager
                rv_commnetss?.itemAnimator = DefaultItemAnimator()
                val sing_idd: String? = channell?.id
                if (sing_idd != null) {
                    getLEarnCommnts(sing_idd)
                }
                mBottomSheetDialog = BottomSheetDialog(context)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            })


//        });
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLEarnCommnts(ch_idddd: String) {
        val url: String = Configs.BASE_URL2 + "cuties-videos/" + ch_idddd
        RetrofitClient.getClient().snglechcts22(
            url, "application/json",
            "Bearer " + token
        )
            ?.enqueue(object : GlobalCallback<CtsSingleChPojo?>(send_commnett) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(
                    call: Call<CtsSingleChPojo?>,
                    response: Response<CtsSingleChPojo?>
                ) {
                    try {
                        videoItemmList=response.body()?.data!!
                        if (videoItemmList.isEmpty()) {

//                                nodata_cuties.visibility = View.VISIBLE;
//                                rv_cuties.visibility = View.GONE;
                        } else {
//                                nodata_cuties.visibility = View.GONE;
//                                videoItemmList.add(new CuteVidListPojoItem());
                            adapter = YtOneComentsAdptrCut(videoItemmList, context)
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

    class Videoviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttitle: TextView
        var txtDesc: TextView? = null
        var likecount: TextView
        var subscr_cts: TextView
        var channel_name_cts: TextView
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
        var channel_pic_cts: CircleImageView
        private val vid_home: String? = null

        init {
            videoView = itemView.findViewById<VideoView>(R.id.cut_vidview)
            txttitle = itemView.findViewById<TextView>(R.id.txtvidtitle)
            ll_ch_cts = itemView.findViewById<LinearLayout>(R.id.ll_ch_cts)
            views_vid = itemView.findViewById<TextView>(R.id.views_vid)
            comnts_cnt = itemView.findViewById<TextView>(R.id.comnts_cnt)
            progressBar = itemView.findViewById<ProgressBar>(R.id.vidPrgresbar)
            cutie_pause = itemView.findViewById<ImageView>(R.id.cutie_pause)
            cutie_play = itemView.findViewById<ImageView>(R.id.cutie_play)
            subscr_cts = itemView.findViewById<TextView>(R.id.subscr_cts)
            ll_shr_cutis = itemView.findViewById<LinearLayout>(R.id.ll_shr_cutis)
            ll_cmnts_cutis = itemView.findViewById<LinearLayout>(R.id.ll_cmnts_cutis)
            toggle_like = itemView.findViewById<ToggleButton>(R.id.toggle_like)
            toggle_dislike = itemView.findViewById<ToggleButton>(R.id.toggle_dislike)
            likecount = itemView.findViewById<TextView>(R.id.likecount)
            channel_name_cts = itemView.findViewById<TextView>(R.id.channel_name_cts)
            channel_pic_cts = itemView.findViewById<CircleImageView>(R.id.channel_pic_cts)
            gobacku = itemView.findViewById<ImageView>(R.id.gobacku)
        }

        fun setVidoData(v_home: FrntCtsChId) {
            try {
                progressBar.visibility = View.VISIBLE
                val title_str: String? = v_home.title
                txttitle.setText(title_str)
                val cutChannel: FrntCtsChannel ?= v_home.channel
                val ch_cts_name: String =
                    Configs.BASE_URL21 + "images/channel/" + cutChannel?.image
                Glide.with(context).load(ch_cts_name).into(channel_pic_cts)
                val chnl_nm_cts: String? = cutChannel?.name
                channel_name_cts.setText(chnl_nm_cts)
                val vid_cuttt: String =
                    Configs.BASE_URL21 + "images/pool/video/" + v_home.video
                videoView.setVideoPath(vid_cuttt)
                videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp: MediaPlayer ->
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
                })
                videoView.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp: MediaPlayer ->
                    Toast.makeText(
                        context,
                        R.string.toast_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    mp.seekTo(0)
                    mp.start()
                })


//                videoView.setOnPreparedListener(mp -> {
//
//                    progressBar.visibility = View.GONE;
//
////                    mp.seekTo(0);
////                    mp.prepareAsync();
////                    mp.start();
//
//                    float vidRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
//                    float scrnRatio = videoView.getWidth() / (float) videoView.getHeight();
//
//                    float scalee = vidRatio / scrnRatio;
//                    if (scalee >= 1f) {
//                        videoView.setScaleX(scalee);
//
//                    } else {
//                        videoView.setScaleY(1f / scalee);
//
//                    }
//
//
//                });
//
//                videoView.setOnCompletionListener(mp -> {
//
//                    mp.seekTo(0);
//                    mp.start();
//                });
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        companion object {
            lateinit var toggle_like: ToggleButton
            lateinit var toggle_dislike: ToggleButton
        }
    }

    fun postdislik(videoo_idd: String?) {
        manager = SessionManager()
        token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
        RetrofitClient.getClient().getlikislik(
            videoo_idd, 0.toString(),
            "application/json",
            "Bearer " + token
        )?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_dislike) {
            override  fun onResponse(
                call: Call<LikeDislikPojo?>,
                response: Response<LikeDislikPojo?>
            ) {
                val likedislikeres: String ? = response.body()?.vote
                if (likedislikeres != null) {
                    if (likedislikeres.contains("1")) {
                    } else if (likedislikeres.contains("2")) {


            //                    toggle_dislike.setChecked(true);
            //                    toggle_like.setChecked(false);
                    } else if (likedislikeres.contains("3")) {

            //                    Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT).show();
            //                    toggle_like.setChecked(false);
            //                    toggle_dislike.setChecked(true);
                    }
                }
            }
        })
    }

    companion object {
        lateinit var context: Context
        var manager: SessionManager? = null
        var token: String? = null
//        var toggle_like: ToggleButton? = null
//        var toggle_dislike: ToggleButton? = null
        private var adapter: RecyclerView.Adapter<*>? = null
        private var rv_commnetss: RecyclerView? = null
        var myOnClickListener1: View.OnClickListener? = null
        private const val mCurrentPosition = 0
        fun postliked(videoo_idd: String?) {
            manager = SessionManager()
            token = manager?.getPreferences(context, Constants.USER_TOKEN_LRN)
            RetrofitClient.getClient().getlikislik(
                videoo_idd, 1.toString(),
                "application/json",
                "Bearer " + token
            )?.enqueue(object : GlobalCallback<LikeDislikPojo?>(toggle_like) {
                override   fun onResponse(
                    call: Call<LikeDislikPojo?>,
                    response: Response<LikeDislikPojo?>
                ) {
                    val likedislikeres: String ? = response.body()?.vote
                    manager?.setPreferences(context, Constants.LIKE_HIT, likedislikeres!!)
                    if (likedislikeres != null) {
                        if (likedislikeres.contains("1")) {
                
                
                //                    toggle_dislike.setChecked(false);
                //                    toggle_like.setChecked(true);
                        } else if (likedislikeres.contains("2")) {
                        } else if (likedislikeres.contains("3")) {
                
                //                    Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                
                //                    toggle_dislike.setChecked(false);
                //                    toggle_like.setChecked(true);
                        }
                    }
                }
            })
        }
    }
}