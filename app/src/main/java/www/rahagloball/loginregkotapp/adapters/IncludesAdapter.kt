package www.rahagloball.loginregkotapp.adapters


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.crseinfonew.IncludeItemCi

class IncludesAdapter(allFollowList: List<IncludeItemCi>, act: Activity) :
    RecyclerView.Adapter<IncludesAdapter.ViewHolder>() {
    private val dataSet: List<IncludeItemCi>
    private val mContext: Activity
    var card_view: CardView? = null

    init {
        dataSet = allFollowList
        mContext = act
    }

    //    public IncludesAdapter(List<DataItem> allFollowList, Catgryctivity act) {
    //        this.dataSet = allFollowList;
    //        this.mContext = act;
    //
    //    }
    //
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.include_items, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val postreq: TextView = viewHolder.cat_txtt
        val checkImage = viewHolder.cat_imgg
        val qid: TextView = viewHolder.catt_Idd
        val main_cat_idd: TextView = viewHolder.main_cat_id
        val cardVieww = viewHolder.cardView

//        String maincatidstr = dataSet.get(i).getMaincatId();
        val cst_dd: String? = dataSet[i].id
        //
//        if (maincatidstr.contains("1")) {
        val title_cat: String? = dataSet[i].detail
        postreq.setText(title_cat)
        val img_str: String? = Configs.BASE_URL21 + "images/category/" + dataSet[i].icon
        //            Log.e("img_strStr", img_str);
        Glide.with(mContext).load(img_str)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(checkImage)


//        cardVieww.setOnClickListener(v -> {
//
//            Intent intent = new Intent(mContext, SubCatActivity.class);
//            intent.putExtra("Qid", cst_dd);
//            mContext.startActivity(intent);
//        });
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cat_txtt: TextView
        var catt_Idd: TextView
        var main_cat_id: TextView
        var cat_imgg: ImageView
        var cardView: CardView

        init {
            cat_txtt = itemView.findViewById<TextView>(R.id.home_item)
            cat_imgg = itemView.findViewById<ImageView>(R.id.home_img)
            catt_Idd = itemView.findViewById<TextView>(R.id.catt_Idd)
            cardView = itemView.findViewById<CardView>(R.id.card_view)
            main_cat_id = itemView.findViewById<TextView>(R.id.main_cat_id)
        }
    }
}