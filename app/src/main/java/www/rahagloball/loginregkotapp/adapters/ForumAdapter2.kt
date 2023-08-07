package www.rahagloball.loginregkotapp.adapters


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.alltranxx.AllTranxx
class ForumAdapter2(dataset: List<AllTranxx>, mContext: Activity) :
    RecyclerView.Adapter<ForumAdapter2.ViewHolder>() {
    var dataset: List<AllTranxx>
    var manager: SessionManager? = null
    var mContext: Activity
    var textView: TextView? = null
    var token: String? = null
    var pcknameee: String? = null
    var txtforum: TextView? = null

    init {
        this.dataset = dataset
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.activity_forum_adapter1, parent, false)
        manager = SessionManager()
        token = manager?.getPreferences(mContext, Constants.USER_TOKEN_LRN)
        txtforum = view.findViewById<TextView>(R.id.txtforum)
        //        dashbrddata_main(myviewholder);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        manager = SessionManager()
        txtforum = holder.forumdetail
        val amount_cutt: TextView = holder.amount_cutt
        val txtforum_date: TextView = holder.txtforum_date
        val img_wallet = holder.img_wallet
        val boddyyy: String? = dataset[position].body
        val amouny: String? = dataset[position].amount
        //        String pckg_desc = dataset.get(position).getDescription();
//        String pckg_date = dataset.get(position).getFinal_datee();
//        String pckg_amountt_gst = dataset.get(position).getAmountWthgst();
//        String pckg_amountt = dataset.get(position).getAmount();
//        String pckg_amountt_old = dataset.get(position).getOldBalance();
        val txtxtsds = "you have $boddyyy with the amount of $amouny"
        //        you have body with the amount of amount
        txtforum?.text = txtxtsds
        //        txtforum_date.setText(pckg_date);
        amount_cutt.text = "Rs. $amouny"
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var forumdetail: TextView
        var circle_tranx: CircleImageView
        var amount_cutt: TextView
        var txtforum_date: TextView
        var img_wallet: ImageView

        init {
            forumdetail = itemView.findViewById<TextView>(R.id.txtforum)
            circle_tranx = itemView.findViewById<CircleImageView>(R.id.circle_tranx)
            amount_cutt = itemView.findViewById<TextView>(R.id.amount_cutt)
            txtforum_date = itemView.findViewById<TextView>(R.id.txtforum_date)
            img_wallet = itemView.findViewById<ImageView>(R.id.img_wallet)
        }
    }
}