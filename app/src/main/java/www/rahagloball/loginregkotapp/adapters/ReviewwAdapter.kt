package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.reviewpoj.GetAssocReview
import www.rahagloball.loginregkotapp.models.reviewpoj.RvwUser

class ReviewwAdapter(dataSet: List<GetAssocReview>, mContext: Activity) :
    RecyclerView.Adapter<ReviewwAdapter.ViewHolder>() {
    var dataSet: List<GetAssocReview>
    var mContext: Activity

    init {
        this.dataSet = dataSet
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.review_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        try {
            val assoname1: TextView = viewHolder.rvw_namee
            val rev_desc1: TextView = viewHolder.rev_desc
            val txtRatingValue_rvw1: TextView = viewHolder.txtRatingValue_rvw
            val ratingBar1: RatingBar = viewHolder.ratings
            ratingBar1.rating = dataSet[i].rating?.toFloat()!!
            val prorate: String = dataSet[i].rating?.toFloat().toString() + "/" + "5"
            txtRatingValue_rvw1.text = prorate
            val rvwUser: RvwUser? = dataSet[i].user
            assoname1.setText(rvwUser?.name)
            val rev_des_str: String? = dataSet[i].review
            if (rev_des_str == "") {
                rev_desc1.visibility = View.GONE
            } else {
                rev_desc1.visibility = View.VISIBLE
                rev_desc1.text = rev_des_str
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ratings: RatingBar
        var blur_reg1: RelativeLayout
        var asso_id: TextView
        var rvw_namee: TextView
        var txtRatingValue_rvw: TextView
        var rev_desc: TextView

        init {
            ratings = itemView.findViewById<RatingBar>(R.id.ratingBar_rvw1)
            rvw_namee = itemView.findViewById<TextView>(R.id.rvw_namee)
            asso_id = itemView.findViewById<TextView>(R.id.asso_id)
            txtRatingValue_rvw = itemView.findViewById<TextView>(R.id.txtRatingValue_rvw)
            rev_desc = itemView.findViewById<TextView>(R.id.rev_desc)
            rev_desc = itemView.findViewById<TextView>(R.id.rev_desc)
            blur_reg1 = itemView.findViewById<RelativeLayout>(R.id.blur_reg1)
        }
    }
}