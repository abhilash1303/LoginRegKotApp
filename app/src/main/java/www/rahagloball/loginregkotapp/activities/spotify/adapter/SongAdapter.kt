package www.rahagloball.loginregkotapp.activities.spotify.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.activities.spotify.data.Plays

class SongAdapter(
    private val mList: List<Plays>,
    var listener : OnItemClickListener
                  ) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    // create new views

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_songs, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.title

        if (ItemsViewModel.isSelected) holder.imageView.visibility = View.VISIBLE else  holder.imageView.visibility = View.INVISIBLE

        Glide.with(holder.itemView)
            .load(ItemsViewModel.imageUri)
            .into(holder.albumCover)


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView8)
        val albumCover: ImageView = itemView.findViewById(R.id.imageView2)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val data = mList[position]
                    listener.onItemClick(data,position)
                }
                imageView.visibility = View.VISIBLE
            }
            imageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val data = mList[position]
                    listener.onCheckClick(data)
                }
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(plays: Plays , position: Int)
        fun onCheckClick(plays: Plays)
    }
}
