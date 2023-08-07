package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.newsindletest.OneChannel
import www.rahagloball.loginregkotapp.models.newsindletest.OneComment
import www.rahagloball.loginregkotapp.models.newsindletest.OneDataItem
import www.rahagloball.loginregkotapp.models.newsindletest.SubcommentVid1

class YtCommentsAdapter(data: List<OneDataItem>, mContext: Activity) :
    RecyclerView.Adapter<YtCommentsAdapter.MyViewHolder>() {
    private val dataSet: List<OneDataItem>
    var mContext: Activity
    var root: View? = null
    var manager: SessionManager? = null
    var assoc_id: String? = null
    var input: EditText? = null
    var token: String? = null
    var commnet_replyyy: TextView? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var article_idd: TextView? = null
        var like_outlined: ImageView? = null
        var dislike_outline: ImageView? = null
        var like_filled: ImageView? = null
        var dislike_filled: ImageView? = null
        var like_countt: TextView
        var artDates: TextView? = null
        var commnet_replyyy: TextView
        var artDetailss: TextView? = null
        var choice_data: TextView
        var user_cmnt_name: TextView
        var user_cmnt_body: TextView
        var card_artcle: CardView? = null
        var user_cmnt_pic: CircleImageView
        var more_optionss: ImageView

        init {

//            like_outlined = itemView.findViewById(R.id.like_outlined);
//            dislike_outline = itemView.findViewById(R.id.dislike_outline);
//
//            like_filled = itemView.findViewById(R.id.like_filled);
//            dislike_filled = itemView.findViewById(R.id.dislike_filled);
            like_countt = itemView.findViewById<TextView>(R.id.like_countt)
            choice_data = itemView.findViewById<TextView>(R.id.choice_data)
            user_cmnt_pic = itemView.findViewById<CircleImageView>(R.id.user_cmnt_pic)
            user_cmnt_name = itemView.findViewById<TextView>(R.id.user_cmnt_name)
            user_cmnt_body = itemView.findViewById<TextView>(R.id.user_cmnt_body)
            more_optionss = itemView.findViewById<ImageView>(R.id.more_optionss)
            commnet_replyyy = itemView.findViewById<TextView>(R.id.commnet_replyyy)
        }
    }

    init {
        dataSet = data
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.addcomnt_adapter, parent, false)
        manager = SessionManager()
        token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
        commnet_replyyy = view.findViewById<TextView>(R.id.commnet_replyyy)

//        view.setOnClickListener(AssociateArticlesRv.myOnClickListener1);
        return MyViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        try {

//        TextView like_countt = holder.like_countt;
            val user_cmnt_name: TextView = holder.user_cmnt_name
            val user_cmnt_body: TextView = holder.user_cmnt_body
            val commnet_replyyy: TextView = holder.commnet_replyyy
            val more_optionss = holder.more_optionss
            val user_cmnt_pic: CircleImageView = holder.user_cmnt_pic
            val vidlist: OneDataItem = dataSet[listPosition]
            //            Video video = vidlist.getVideo();
            val channell: OneChannel? = vidlist.channel
            val checImgSt: String = Configs.BASE_URL21 + "images/channel/" + channell?.image
            val checNmae: String? = channell?.name
            user_cmnt_name.setText(checNmae)


//            Picasso.get().load(checImgSt).into(user_cmnt_pic);
            val commentsItemList: List<OneComment>? = vidlist.comments
            if (commentsItemList != null) {
                val commentsItem1: OneComment = commentsItemList[0]
                for (i in commentsItemList.indices) {
                    val commentsItem: OneComment = commentsItemList[i]
                    val user_cmnt_body_str: String? = commentsItem.body
                    if (user_cmnt_body_str != null) user_cmnt_body.text = user_cmnt_body_str
                    val subcomment: List<SubcommentVid1>? = commentsItem.subcomment
                    if (subcomment!=null) {
                        val user_cmnt_reply_str: String? = subcomment[i].body
                        if (user_cmnt_reply_str != null) commnet_replyyy.text = user_cmnt_reply_str
                    }
                }
                more_optionss.setOnClickListener { view: View? ->
                    val vidd_idd: String? = vidlist.id
                    val user_idd: String? = commentsItem1.userId
                    val cmmntt_idd: String? = commentsItem1.id
                    val alert = AlertDialog.Builder(mContext)
                    alert.setTitle("Reply") //Set Alert dialog title here
                    alert.setMessage("Write Your Reply") //Message here

                    // Set an EditText view to get user input
                    input = EditText(mContext)
                    alert.setView(input)
                    alert.setPositiveButton(
                        "OK"
                    ) { dialog, whichButton ->
                        if (validate()) {
                            if (vidd_idd != null) {
                                if (cmmntt_idd != null) {
                                    if (user_idd != null) {
                                        postfbckData(
                                            input?.text.toString(),
                                            vidd_idd,
                                            cmmntt_idd,
                                            user_idd
                                        )
                                    }
                                }
                            }
                        }
                    } // End of onClick(DialogInterface dialog, int whichButton)
                    //End of alert.setPositiveButton
                    alert.setNegativeButton(
                        "CANCEL"
                    ) { dialog, whichButton -> // Canceled.
                        dialog.cancel()
                    } //End of alert.setNegativeButton
                    val alertDialog = alert.create()
                    alertDialog.show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


////
//        if (dataSet.size() > 0) {
//            for (int i = 0; i < dataSet.size(); i++) {
//                DataItem vidlist = dataSet.get(i);
//                Video video = vidlist.getVideo();
//                Channel channell=vidlist.getChannel();
//                List<CommentsItem> commentss = video.getComments();
//
//                for (int j = 0; j < commentss.size(); j++) {
//
//
//                    String user_cmnt_body_str = commentss.get(j).getBody();
//
//                    if (user_cmnt_body_str!=null)
//                    user_cmnt_body.setText(user_cmnt_body_str);
//
//                    String checImgSt = channell.getImage();
//                    String checNmae = channell.getName();
//                    user_cmnt_name.setText(checNmae);
//
//                    Picasso.get().
//                            load(checImgSt)
//                            .into(user_cmnt_pic);
////
//                }
//            }
//        }


//        String checImgSt = channell.getImage();
//
//        Picasso.get().
//                load(checImgSt)
//                .into(user_cmnt_pic);


//
//        card_artcle.setOnClickListener(v -> {
//
//            Intent intent = new Intent(mContext, AssociateSingleArticles.class);
//            intent.putExtra("ArtsHead", artHeads_str);
//            intent.putExtra("ArtsImage", artimages_str);
//            intent.putExtra("ArtsDate", artDetailss_str);
//            intent.putExtra("ArtDetails", artDates_str);
//            mContext.startActivity(intent);
//
//        });

//        close_artcl.setOnClickListener(v -> {
////
//            AlertDialog.Builder cancelAlert = new AlertDialog.Builder(mContext);
//
//            cancelAlert.setTitle(mContext.getResources().getString(R.string.commens));
//            cancelAlert.setMessage(mContext.getResources().getString(R.string.r_u_sr_del_comment));
//            cancelAlert.setPositiveButton(mContext.getResources().getString(R.string.okay), (dialog, whichButton) -> {
//
////                delImg(article_idd_str,listPosition,holder);
//
////
//
//            });


//            cancelAlert.setNegativeButton(mContext.getResources().getString(R.string.cancel),
//                    (dialog, whichButton) -> dialog.dismiss());
//            cancelAlert.show();
//
//        });
    }

    fun validate(): Boolean {
        var valid = true
        val feedtext: String = input?.editableText.toString()
        if (feedtext.isEmpty()) {
            Toast.makeText(mContext, "Write Your Reply", Toast.LENGTH_SHORT).show()
            valid = false
        } else {
            input?.setError(null)
        }
        return valid
    }

    private fun postfbckData(
        bodyy: String,
        viddd_iddd: String,
        cmmnntt_id: String,
        user_iddd: String
    ) {
        try {
            manager = SessionManager()
            token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
            RetrofitClient.getClient().getcmntreply(
                bodyy, viddd_iddd, cmmnntt_id,
                "application/json",
                "Bearer $token"
            )?.enqueue(object : GlobalCallback<String?>(input) {
                override fun onResponse(call: Call<String?>?, response: Response<String?>) {
                    try {
                        if (response.isSuccessful) {

//                            commnet_replyyy.setText(bodyy);
                            Toast.makeText(mContext, "Replied successfully!", Toast.LENGTH_SHORT)
                                .show()
                        } else if (cmmnntt_id == user_iddd) {
                            Toast.makeText(mContext, "Already Replied !", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun postfbckDataCheck(bodyy: String, viddd_iddd: String, cmmnntt_id: String) {
        try {
            RetrofitClient.getClient().getcmntreplycheck(
                cmmnntt_id,
                "application/json",
                "Bearer $token"
            )?.enqueue(object : GlobalCallback<String?>(input) {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    try {
                        val calres = response.body()?.toString()
                        //
                        if (calres != null) {
                            if (calres.contains("1")) {
                                Toast.makeText(mContext, "Already Replied!", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (calres.contains("2")) {

                                //                            postfbckData(bodyy, viddd_iddd, cmmnntt_id);
                                //                            Toast.makeText(mContext, "Replied successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //    private void allfeedback() {
    //
    //        try {
    //
    //            Bundle bundle = intent.extras;
    //            lead_id = bundle.getString("LeadsID");
    //            pro_name = bundle.getString("catgryID");
    //            subpro_name = bundle.getString("subcatgryID");
    //
    //            RetrofitClient.getClient().getallfedchat(lead_id, pro_name, subpro_name,
    //                    "application/json",
    //                    "Bearer " + token).enqueue(new GlobalCallback<FdbkPojo>(listView) {
    //                @Override
    //                public void onResponse(Call<FdbkPojo> call, retrofit2.Response<FdbkPojo> response) {
    //
    //                    try {
    //
    //                        List<FeedbackLd> feedbackLds ? = response.body()?.getFeedchat();
    //
    //                        if (feedbackLds.isEmpty()) {
    //                            bar.visibility = View.VISIBLE;
    //
    //                        } else {
    //
    //                            bar.visibility = View.GONE;
    //                            adapter = new FeedAdapterNew(FeedActivity.this, feedbackLds);
    //                            listView.setAdapter(adapter);
    //                            listView.scrollToPosition(feedbackLds.size() - 1);
    //
    //                        }
    //
    //                    } catch (Exception e) {
    //                        e.printStackTrace();
    //                    }
    //
    //                }
    //            });
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
//    private fun removeItem(id: String, position: Int, holder: MyViewHolder) {
//        val actualPosition = holder.adapterPosition
//        dataSet.removeAt(actualPosition)
//        notifyItemRemoved(actualPosition)
//        notifyItemRangeChanged(actualPosition, dataSet.size)
//    }

    override fun getItemCount(): Int {
        return dataSet.size
    } //
    //    public void delImg(String idi,int listPosition1,MyViewHolder holder) {
    //
    //        assoc_id = manager?.getPreferences(mContext, "assoc_id");
    //
    //        RetrofitClient.getClient().dele_articlee(idi, assoc_id)
    //                .enqueue(new GlobalCallback<String>(root) {
    //                    @Override
    //                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {
    //
    //                        String responsee ? = response.body()?.toString();
    //
    //                        if (responsee.contains("1")) {
    //                            Toast.makeText(mContext, "deleted", Toast.LENGTH_SHORT).show();
    //                            removeItem(idi,listPosition1,holder);
    //
    //                        }
    //
    //                    }
    //
    //                    @Override
    //                    public void onFailure(Call<String> call, Throwable t) {
    //                        super.onFailure(call, t);
    //                    }
    //                });
    //
    //
    //    }
}