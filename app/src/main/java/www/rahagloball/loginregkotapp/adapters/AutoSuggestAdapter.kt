package www.rahagloball.loginregkotapp.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import java.util.Locale

class AutoSuggestAdapter(context: Context, resource: Int) :
    ArrayAdapter<String?>(context, resource), Filterable {
    private val mlistData: ArrayList<String> = ArrayList()
    private val mOriginalData: ArrayList<String> = ArrayList()

    fun setData(list: List<String>?) {
        mlistData.clear()
        mOriginalData.clear()
        if (list != null) {
            mlistData.addAll(list)
            mOriginalData.addAll(list)
        }
    }

    override fun getCount(): Int {
        return mlistData.size
    }

    override fun getItem(position: Int): String {
        return mlistData[position]
    }

    fun getObject(position: Int): String {
        return mlistData[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val filteredList: MutableList<String> = ArrayList()

                if (!constraint.isNullOrEmpty()) {
                    val constraintString = constraint.toString().lowercase(Locale.ROOT)

                    // Apply your filtering logic here
                    for (item in mlistData) {
                        if (item.lowercase(Locale.ROOT).contains(constraintString)) {
                            filteredList.add(item)
                        }
                    }
                }

                filterResults.values = filteredList
                filterResults.count = filteredList.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                if (!constraint.isNullOrEmpty()) {
                    if (results.count > 0) {
                        notifyDataSetChanged()
                    } else {
                        notifyDataSetInvalidated()
                    }
                }
            }

//            override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                mlistData.clear()
//                mlistData.addAll(results.values as List<String>)
//                notifyDataSetChanged()
//            }
        }
    }
}



//
//class AutoSuggestAdapter(context: Context, resource: Int) :
//    ArrayAdapter<String?>(context, resource), Filterable {
//    private val mlistData: MutableList<String>
//
//    init {
//        mlistData = ArrayList()
//    }
//
//    fun setData(list: List<String>?) {
//        mlistData.clear()
//        mlistData.addAll(list!!)
//    }
//
//    override fun getCount(): Int {
//        return mlistData.size
//    }
//
//    override fun getItem(position: Int): String? {
//        return mlistData[position]
//    }
//
//    fun getObject(position: Int): String {
//        return mlistData[position]
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence): FilterResults {
//                val filterResults = FilterResults()
//                filterResults.values = mlistData
//                filterResults.count = mlistData.size
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                if (results.count > 0) {
//                    notifyDataSetChanged()
//                } else {
//                    notifyDataSetInvalidated()
//                }
//            }
//        }
//    }
//}