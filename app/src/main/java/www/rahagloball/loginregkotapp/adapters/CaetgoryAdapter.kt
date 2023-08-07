package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.SubCatActivity
import www.rahagloball.loginregkotapp.models.catgryy.DataItem

class CaetgoryAdapter : RecyclerView.Adapter<CaetgoryAdapter.ViewHolder> {
    private var dataSet: List<DataItem>

    //    private List<DataItem> catggryList;
    private var mContext: Activity

    //    CardView card_view;
    constructor(allFollowList: List<DataItem>, act: Activity) {
        dataSet = allFollowList
        mContext = act
    }

//    constructor(allFollowList: List<www.rahagloball.loginregkotapp.models.getchanldata.DataItem>?, act: Catgryctivity) {
//        dataSet = allFollowList
//        mContext = act
//    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.home_single_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.cat_txtt
        val checkImage = viewHolder.cat_imgg
        val qid: TextView = viewHolder.catt_Idd
        val main_cat_idd: TextView = viewHolder.main_cat_id
        val cardVieww: LinearLayout = viewHolder.cardView
        val maincatidstr: String? = dataSet[i].maincatId
        val cst_dd: String? = dataSet[i].id
        //
//        if (maincatidstr.contains("1")) {
        val title_cat: String? = dataSet[i].title
        postreq.setText(title_cat)
        val img_str: String = Configs.BASE_URL21 + "images/category/" + dataSet[i].catImage

//            Log.e("img_strStr", img_str);
        Glide.with(mContext).load(img_str)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(checkImage)
        cardVieww.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(mContext, SubCatActivity::class.java)
            val cst_dd1: String? = dataSet[i].id
            intent.putExtra("Qid", cst_dd1)
            mContext.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cat_txtt: TextView
        var catt_Idd: TextView
        var main_cat_id: TextView
        var cat_imgg: ImageView
        var cardView: LinearLayout

        init {
            cat_txtt = itemView.findViewById<TextView>(R.id.home_item)
            cat_imgg = itemView.findViewById<ImageView>(R.id.home_img)
            catt_Idd = itemView.findViewById<TextView>(R.id.catt_Idd)
            cardView = itemView.findViewById<LinearLayout>(R.id.card_view)
            main_cat_id = itemView.findViewById<TextView>(R.id.main_cat_id)
        }
    }
}