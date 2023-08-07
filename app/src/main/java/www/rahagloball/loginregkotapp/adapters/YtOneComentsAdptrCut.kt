package www.rahagloball.loginregkotapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsChId
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsChannel
import www.rahagloball.loginregkotapp.models.chctsfront.FrntCtsComment
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import www.nationlearnsraha.com.ConstantAndSession.SessionManager;
//import www.nationlearnsraha.com.Model.Pojo.chctsfront.FrntCtsChId;
//import www.nationlearnsraha.com.Model.Pojo.chctsfront.FrntCtsChannel;
//import www.nationlearnsraha.com.Model.Pojo.chctsfront.FrntCtsComment;
//import www.nationlearnsraha.com.R;
//
class YtOneComentsAdptrCut(data: List<FrntCtsChId>, mContext: Context) :
    RecyclerView.Adapter<YtOneComentsAdptrCut.MyViewHolder>() {
    private val dataSet: List<FrntCtsChId>
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
        var like_countt: TextView
        var artDates: TextView? = null
        var artDetailss: TextView? = null
        var choice_data: TextView
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
            like_countt = itemView.findViewById<TextView>(R.id.like_countt)
            choice_data = itemView.findViewById<TextView>(R.id.choice_data)
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

    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        try {

//        TextView like_countt = holder.like_countt;
            val user_cmnt_name: TextView = holder.user_cmnt_name
            val user_cmnt_body: TextView = holder.user_cmnt_body
            val user_cmnt_pic: CircleImageView = holder.user_cmnt_pic
            val vidlist: FrntCtsChId = dataSet[listPosition]
            val channell: FrntCtsChannel? = vidlist.channel
            val checImgSt: String = Configs.BASE_URL21 + "images/channel/" + channell?.image
            val checNmae: String? = channell?.name
            user_cmnt_name.setText(checNmae)


//            Picasso.get().
            Glide.with(mContext).load(checImgSt).into(user_cmnt_pic)
            val commentsItemList: List<FrntCtsComment>? = vidlist.comments
            if (commentsItemList != null) {
                for (i in commentsItemList.indices) {
                    val commentsItem: FrntCtsComment = commentsItemList[i]
                    val user_cmnt_body_str: String? = commentsItem.body
                    if (user_cmnt_body_str != null) user_cmnt_body.setText(user_cmnt_body_str)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}