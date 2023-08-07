package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.SubCatActivity
import www.rahagloball.loginregkotapp.activities.SubCatListActivity1
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.models.subcatgry.DataItem

class SubCaetgoryAdapter(allFollowList: List<DataItem>, act: SubCatActivity) :
    RecyclerView.Adapter<SubCaetgoryAdapter.ViewHolder>() {
    private val dataSet: List<DataItem>
    private val mContext: Activity
    var card_view: CardView? = null

    init {
        dataSet = allFollowList
        mContext = act
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.child_layout2, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.sc_name
        val checkImage = viewHolder.sc_image
//        val sc_Idd: TextView viewHolder.sc_Idd
        val cardVieww = viewHolder.card_view_sc
        postreq.isSelected = true

        postreq.text=dataSet[i].title
        val cst_dd: String? = dataSet[i].id
        val ctgeree_id: String? = dataSet[i].categoryId

        val img_str: String = Configs.BASE_URL21 + "images/subcategory/" + dataSet[i].img
        Glide.with(mContext).load(img_str)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(checkImage)
        cardVieww.setOnClickListener { v: View? ->
//
            val intent = Intent(mContext, SubCatListActivity1::class.java)
            intent.putExtra("Qid_Connect", cst_dd)
            intent.putExtra("Catid_Connect", ctgeree_id)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sc_name: TextView
//        var sc_Idd: TextView
        var sc_image: ImageView
        var card_view_sc: CardView

        init {
            sc_name = itemView.findViewById<TextView>(R.id.sc_name)
            sc_image = itemView.findViewById<ImageView>(R.id.sc_image)
//            sc_Idd = itemView.findViewById<TextView>(R.id.sc_Idd)
            card_view_sc = itemView.findViewById<CardView>(R.id.card_view_sc)
        }
    }
}