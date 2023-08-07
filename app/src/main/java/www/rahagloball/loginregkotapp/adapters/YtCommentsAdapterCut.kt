package www.rahagloball.loginregkotapp.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.models.cutyvids.CommentsItem
import www.rahagloball.loginregkotapp.models.cutyvids.CutChannel
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojoItem

class YtCommentsAdapterCut(data: List<CuteVidListPojoItem>, mContext: Context) :
    RecyclerView.Adapter<YtCommentsAdapterCut.MyViewHolder>() {
    private val dataSet: List<CuteVidListPojoItem>
    var mContext: Context
    var root: View? = null
    var manager: SessionManager? = null
    var assoc_id: String? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var article_idd: TextView? = null
        var like_outlined: ImageView? = null
        var dislike_outline: ImageView? = null
        var like_filled: ImageView? = null
        var dislike_filled: ImageView? = null
        var like_countt: TextView? = null
        var artDates: TextView? = null
        var artDetailss: TextView? = null
        var choice_data: TextView? = null
        var user_cmnt_name: TextView
        var user_cmnt_body: TextView
        var card_artcle: CardView? = null
        var user_cmnt_pic: CircleImageView

        init {


//            like_outlined = itemView.findViewById(R.id.like_outlined);
//            dislike_outline = itemView.findViewById(R.id.dislike_outline);
//
//            like_filled = itemView.findViewById(R.id.like_filled);
//            dislike_filled = itemView.findViewById(R.id.dislike_filled);
//            like_countt = itemView.findViewById(R.id.like_countt);
//            choice_data = itemView.findViewById(R.id.choice_data);
            user_cmnt_pic = itemView.findViewById<CircleImageView>(R.id.user_cmnt_pic)
            user_cmnt_name = itemView.findViewById<TextView>(R.id.user_cmnt_name)
            user_cmnt_body = itemView.findViewById<TextView>(R.id.user_cmnt_body)
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
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.addcomnt_adapter, parent, false)
        manager = SessionManager()

//        view.setOnClickListener(AssociateArticlesRv.myOnClickListener1);
        return MyViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        try {

//        TextView like_countt = holder.like_countt;
            val user_cmnt_name: TextView = holder.user_cmnt_name
            val user_cmnt_body: TextView = holder.user_cmnt_body
            val user_cmnt_pic: CircleImageView = holder.user_cmnt_pic

//        String commnnts_body_str =dataSet.get(listPosition).getBody();
//        Log.e("commnnts_body_str", commnnts_body_str);
//        user_cmnt_body.setText(commnnts_body_str);

//        String commnnts_body_str = dataSet.getBody();
            val vidlist: CuteVidListPojoItem = dataSet[listPosition]
            val channell: CutChannel? = vidlist.channel
            val checImgSt: String = Configs.BASE_URL21 + "images/channel/" + channell?.image
            val checNmae: String ?= channell?.name
            user_cmnt_name.text = checNmae


//            Picasso.get().load(checImgSt).into(user_cmnt_pic);
            val commentsItemList: List<CommentsItem>? = vidlist.comments
            if (commentsItemList != null) {
                for (i in commentsItemList.indices) {
                    val commentsItem: CommentsItem = commentsItemList[i]
                    val user_cmnt_body_str: String? = commentsItem.body
                    if (user_cmnt_body_str != null) user_cmnt_body.text = user_cmnt_body_str
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