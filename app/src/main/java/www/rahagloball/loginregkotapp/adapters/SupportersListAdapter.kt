package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.ChannelProfileActivity
import www.rahagloball.loginregkotapp.models.suprtrlistt.Channel_Support
import www.rahagloball.loginregkotapp.models.suprtrlistt.Supporter

class SupportersListAdapter(dataSet: List<Supporter>, mContext: Activity) :
    RecyclerView.Adapter<SupportersListAdapter.ViewHolder>() {
    var dataSet: List<Supporter>
    var mContext: Activity

    init {
        this.dataSet = dataSet
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.sprt_list_adptr, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val sprt_name: TextView = viewHolder.sprt_name
        val sprt_img: CircleImageView = viewHolder.sprt_img
        val my_finance = viewHolder.my_finance
        val sts: String? = dataSet[i].status
        //        Log.e("sts",sts);
        val channel_support: Channel_Support? = dataSet[i].channel
        if (channel_support != null) {
            val chnl_namestr: String? = channel_support.name
            if (chnl_namestr != null) sprt_name.text = chnl_namestr
            val chnl_Imgstr: String =
                Configs.BASE_URL21 + "images/channel/" + channel_support.image
            Glide.with(mContext).load(chnl_Imgstr).into(sprt_img)
            val sprt_ch_id: String? = channel_support.id
            my_finance.setOnClickListener { v: View? ->
                val intent = Intent(mContext, ChannelProfileActivity::class.java)
                intent.putExtra("ch_iddCuties", sprt_ch_id)
                mContext.startActivity(intent)
            }
        }
        if (channel_support == null) {
            my_finance.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sprt_name: TextView
        var sprt_img: CircleImageView
        var my_finance: CardView

        init {
            sprt_name = itemView.findViewById<TextView>(R.id.sprt_name)
            sprt_img = itemView.findViewById<CircleImageView>(R.id.sprt_img)
            my_finance = itemView.findViewById<CardView>(R.id.my_finance)
        }
    }
}