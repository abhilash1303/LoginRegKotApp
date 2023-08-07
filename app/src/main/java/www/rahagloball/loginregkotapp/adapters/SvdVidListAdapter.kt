package www.rahagloball.loginregkotapp.adapters

//import okhttp3.MediaType

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdChannel
import www.rahagloball.loginregkotapp.models.mysavedvidss.SvdVideo
import www.rahagloball.loginregkotapp.playerss.PlayerHomeSvd


//import www.nationlearnsraha.com.Configuration.Config;
//import www.nationlearnsraha.com.ConstantAndSession.Constants;
//import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
//import www.nationlearnsraha.com.Model.GlobalCallback;
//import www.nationlearnsraha.com.Model.Pojo.mysavedvidss.SvdChannel;
//import www.nationlearnsraha.com.Model.Pojo.mysavedvidss.SvdVideo;
//import www.nationlearnsraha.com.Model.Pojo.newvidsinglelist.DataItem;
//import www.nationlearnsraha.com.Model.RetrofitClient;
//import www.nationlearnsraha.com.R;
//import www.nationlearnsraha.com.videosdemo.PlayerHomeSvd;
class SvdVidListAdapter(allFollowList: List<SvdVideo>, act: Activity) :
    RecyclerView.Adapter<SvdVidListAdapter.ViewHolder>() {
    private var dataSet: List<SvdVideo> = ArrayList<SvdVideo>()
    private val mContext: Activity
    var card_learn: CardView? = null
    var id: String? = null

    //    DataItem vid_di;
    //    List<VideosItem> video_items;
    //    public SvdVidListAdapter(List<DataItem> allFollowList, FragmentActivity act) {
    //        this.dataSet = allFollowList;
    //        this.mContext = act;
    //
    //    }
    var btm_report_vid: TextView? = null
    var btm_share_vid: TextView? = null
    var btnspinner: Spinner? = null
    var rankDialog: Dialog? = null
    var radio_report: RadioGroup? = null
    var radioButton: RadioButton? = null
    var edit_query_str: String? = null
    var radioButton_str: String? = null
    private var mBottomSheetDialog: BottomSheetDialog? = null
    var bottomSheetLayout: View? = null
    var submit_dialog: TextView? = null
    var cancel_dialog: TextView? = null
    var edit_query: EditText? = null
    var token: String? = null
    var manager: SessionManager? = null

    init {
        dataSet = allFollowList
        mContext = act
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.ern_vidlist_adptr, viewGroup, false)
        manager = SessionManager()
        token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
        //        manager = new SessionManager();
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        try {
            val postreq: TextView = viewHolder.videoTitle
            val channel_name: TextView = viewHolder.channel_name
            val checkImage = viewHolder.videoThumbnail
            val qid: TextView = viewHolder.idd
            val views_vid: TextView = viewHolder.views_vid
            val time_line: TextView = viewHolder.time_line
            val channel_pic: CircleImageView = viewHolder.channel_pic
            card_learn = viewHolder.card_learn
            val ll_more_optionss: LinearLayout = viewHolder.ll_more_optionss
            val cst_dd: String? = dataSet[i].id


//            svdVideoList
//            SvdVideo svdVideoList = dataSet.get(i);
//            List<SvdVideoList> videosvid = svdVideoList.getVideos();

//            videoii
            val videoii: SvdVideo = dataSet[i]
            val title_cat: String? = videoii.title
            //            Log.e("title_cat", title_cat);
            postreq.setText(title_cat)
            val prev_img: String =
                Configs.BASE_URL21 + "images/pool/preview/" + videoii.previewImage
            //            Log.e("prev_img", prev_img);


//            Glide.with(mContext).
            Glide.with(mContext).load(prev_img)
                .into(checkImage)
            val qid_str: String ?= videoii.id
            val time_line_str: String ?= videoii.createdAt
            time_line.setText(time_line_str)

//            String views_vid_str = videoii.getViewsCount();
//            views_vid.setText(views_vid_str + " Views");
            viewHolder.vv.setOnClickListener { v: View ->
//
                val b = Bundle()
                b.putSerializable("videoData", dataSet[i])
                val qid_str1: String? = videoii.id
                manager = SessionManager()
                token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
                RetrofitClient.getClient().add_viewss(
                    qid_str1,
                    "application/json",
                    "Bearer $token"
                )?.enqueue(object : GlobalCallback<String?>(edit_query) {
                   override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        if (response.isSuccessful) {
//                            Toast.makeText(mContext, "View Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                val i222 = Intent(mContext, PlayerHomeSvd::class.java)
                i222.putExtras(b)
                i222.putExtra("video_id", qid_str1)
                v.context.startActivity(i222)
            }
            ll_more_optionss.setOnClickListener(View.OnClickListener { view: View? ->
                bottomSheetLayout =
                    LayoutInflater.from(mContext).inflate(R.layout.vidoe_moreoptions, null)
                btm_report_vid = bottomSheetLayout?.findViewById<TextView>(R.id.btm_report_vid)
                btm_share_vid = bottomSheetLayout?.findViewById<TextView>(R.id.btm_share_vid)
                btm_share_vid?.setOnClickListener(View.OnClickListener { view1: View? ->
                    val vidddurlll: String ?= videoii.video
                    val videoo_idd: String ?= videoii.id
                    if (vidddurlll != null) {
                        if (vidddurlll.contains(" ")) {
                            val title_str1: String? = videoii.title
                            val newString = vidddurlll.replace(" ", "%20")
                            val vidd_urll: String =
                                Configs.BASE_URL21 + "video/" + videoo_idd + "/" + newString
                            val sharingIntent = Intent(Intent.ACTION_SEND)
                            sharingIntent.setType("text/plain")
                            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch $title_str1")
                            sharingIntent.putExtra(
                                Intent.EXTRA_TEXT, "Watch now on NationLearns, " +
                                        "India's first social e-learning platform " + vidd_urll
                            )
                            mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"))
                        }
                    }
                })
                btm_report_vid?.setOnClickListener(View.OnClickListener { view1: View? ->
                    rankDialog = Dialog(mContext, R.style.FullHeightDialog)
                    rankDialog?.setContentView(R.layout.dialog_vid_report)
                    rankDialog!!.setCancelable(true)
                    edit_query = rankDialog!!.findViewById<EditText>(R.id.edit_query)
                    submit_dialog = rankDialog!!.findViewById<TextView>(R.id.submit_dialog)
                    cancel_dialog = rankDialog!!.findViewById<TextView>(R.id.cancel_dialog)
                    radio_report = rankDialog!!.findViewById<RadioGroup>(R.id.radio_report)
                    //            servc_spinr = rankDialog?.findViewById(R.id.servc_spinr);
                    edit_query?.setOnTouchListener(View.OnTouchListener { view2: View, event: MotionEvent ->
                        // TODO Auto-generated method stub
                        if (view2.id == R.id.edit_query) {
                            view2.parent.requestDisallowInterceptTouchEvent(true)
                            if (event.action and MotionEvent.ACTION_MASK == MotionEvent.ACTION_UP) {
                                view2.parent.requestDisallowInterceptTouchEvent(false)
                            }
                        }
                        false
                    })
                    submit_dialog?.setOnClickListener(View.OnClickListener { view4: View? ->
                        radio_report = rankDialog!!.findViewById<RadioGroup>(R.id.radio_report)
                        edit_query_str = edit_query?.text.toString()
                        val selectedId: Int? = radio_report?.checkedRadioButtonId
                        radioButton = selectedId?.let { rankDialog!!.findViewById<RadioButton>(it) }
                        radioButton_str = radioButton?.text as String?

//                    Log.e("radioButton_str",radioButton_str);
                        if (selectedId == -1) {
//
                            Toast.makeText(mContext, "Report something!", Toast.LENGTH_SHORT).show()
                            //
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                            if (edit_query_str == "" || edit_query_str!!.isEmpty()) {

                                //                        videoo_idd = video.getId();
                                //                        report_video(videoo_idd);
                                Toast.makeText(
                                    mContext,
                                    "Please Enter Something",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                //                        Toast.makeText(this, "No api", Toast.LENGTH_SHORT).show();
                                val qid_str44: String? = videoii.id
                                manager = SessionManager()
                                token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
                                RetrofitClient.getClient().vid_reportt(
                                    qid_str44, radioButton_str, edit_query_str,
                                    "application/json",
                                    "Bearer $token"
                                )?.enqueue(object : GlobalCallback<String?>(edit_query) {
                                    override fun onResponse(
                                        call: Call<String?>,
                                        response: Response<String?>
                                    ) {
                                        val languagestr = response.body()?.toString()
                                        rankDialog!!.dismiss()
                                        if (languagestr != null) {
                                            if (languagestr.contains("1")) {
                                                Toast.makeText(
                                                    mContext,
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
                    })
                    cancel_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                    rankDialog!!.show()
                })
                mBottomSheetDialog = BottomSheetDialog(mContext)
                mBottomSheetDialog?.setContentView(bottomSheetLayout!!)
                mBottomSheetDialog?.show()
            })
            val channell: SvdChannel? = videoii.channel
            val ch_namee: String? = channell?.name
            val ch_id: String? = channell?.id
            //            Log.e("ch_id",ch_id);
            val chpool_id: String? = channell?.poolId
            //            Log.e("chpool_id",chpool_id);
            channel_name.setText(ch_namee)
            val checImgSt: String = Configs.BASE_URL21 + "images/channel/" + channell?.image
            Glide.with(mContext).load(checImgSt)
                .into(channel_pic)
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
        var channel_pic: CircleImageView
        var videoTitle: TextView
        var idd: TextView
        var channel_name: TextView
        var views_vid: TextView
        var time_line: TextView
        var ll_more_optionss: LinearLayout
        var card_learn: CardView

        init {
            videoThumbnail = itemView.findViewById<ImageView>(R.id.videoThumbnail)
            channel_pic = itemView.findViewById<CircleImageView>(R.id.channel_pic)
            idd = itemView.findViewById<TextView>(R.id.idd)
            time_line = itemView.findViewById<TextView>(R.id.time_line)
            channel_name = itemView.findViewById<TextView>(R.id.channel_name)
            views_vid = itemView.findViewById<TextView>(R.id.views_vid)
            videoTitle = itemView.findViewById<TextView>(R.id.videoTitle)
            card_learn = itemView.findViewById<CardView>(R.id.card_learn)
            ll_more_optionss = itemView.findViewById<LinearLayout>(R.id.ll_more_optionss)
        }
    }
}