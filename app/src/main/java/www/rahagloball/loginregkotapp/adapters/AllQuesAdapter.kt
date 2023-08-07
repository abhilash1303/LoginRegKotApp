package www.rahagloball.loginregkotapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.allques.QuestionAll

class AllQuesAdapter(dataSet: List<QuestionAll>, mContext: Activity) :
    RecyclerView.Adapter<AllQuesAdapter.ViewHolder>() {
    var dataSet: List<QuestionAll>
    var mContext: Activity
    var counter = 0

    init {
        this.dataSet = dataSet
        this.mContext = mContext
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.sprt_list_adptr, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val sprt_name: TextView = viewHolder.sprt_name
        val sprt_img: CircleImageView = viewHolder.sprt_img
        val my_finance = viewHolder.my_finance
        sprt_img.visibility = View.GONE
        ++counter
        val ques_txt_str = counter.toString() + ") " + dataSet[i].question
        sprt_name.setText(ques_txt_str)
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