package www.rahagloball.loginregkotapp.multispinnerrr

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import www.rahagloball.loginregkotapp.R
import java.util.Locale
import java.util.Objects

class MultiSpinnerSearch : AppCompatSpinner, DialogInterface.OnCancelListener {
    private var highlightSelected = false
    private var highlightColor: Int = ContextCompat.getColor(context, R.color.list_selected)
    private var textColor = Color.GRAY
    private var limit = -1
    private var selected = 0
    private var defaultText = ""
    private var spinnerTitle = ""
    private var emptyTitle = "Not Found!"
    private var searchHint = "Type to search"
    private var clearText = "Clear All"
    var isColorSeparation = false
    var isShowSelectAllButton = false
    private var listener: MultiSpinnerListener? = null
    private var limitListener: LimitExceedListener? = null
    private var adapter: MyAdapter? = null
    private var items: List<KeyPairBoolData>? = null
    var isSearchEnabled = true

    constructor(context: Context?) : super(context!!) {}
    constructor(arg0: Context, arg1: AttributeSet?) : super(arg0, arg1) {
        val a: TypedArray = arg0.obtainStyledAttributes(arg1, R.styleable.MultiSpinnerSearch)
        for (i in 0 until a.getIndexCount()) {
            val attr: Int = a.getIndex(i)
            if (attr == R.styleable.MultiSpinnerSearch_hintText) {
                hintText = a.getString(attr).toString()
                spinnerTitle = hintText
                defaultText = spinnerTitle
                break
            } else if (attr == R.styleable.MultiSpinnerSearch_highlightSelected) {
                highlightSelected = a.getBoolean(attr, false)
            } else if (attr == R.styleable.MultiSpinnerSearch_highlightColor) {
                highlightColor =
                    a.getColor(attr, ContextCompat.getColor(getContext(), R.color.list_selected))
            } else if (attr == R.styleable.MultiSpinnerSearch_textColor) {
                textColor = a.getColor(attr, Color.GRAY)
            } else if (attr == R.styleable.MultiSpinnerSearch_clearText) {
                a.getString(attr)?.let { setClearText(it) }
            }
        }

        //Log.i(TAG, "spinnerTitle: " + spinnerTitle);
        a.recycle()
    }

    constructor(arg0: Context?, arg1: AttributeSet?, arg2: Int) : super(arg0!!, arg1, arg2) {}

    var hintText: String
        get() = spinnerTitle
        set(hintText) {
            spinnerTitle = hintText
            defaultText = spinnerTitle
        }

    fun setClearText(clearText: String) {
        this.clearText = clearText
    }

    fun setLimit(limit: Int, listener: LimitExceedListener?) {
        this.limit = limit
        limitListener = listener
        isShowSelectAllButton = false // if its limited, select all default false.
    }

    val selectedItems: List<KeyPairBoolData>
        get() {
            val selectedItems: MutableList<KeyPairBoolData> = ArrayList()
            for (item in items!!) {
                if (item.isSelectedValue) {
                    selectedItems.add(item)
                }
            }
            return selectedItems
        }
    val selectedIds: List<Long>
        get() {
            val selectedItemsIds: MutableList<Long> = ArrayList()
            for (item in items!!) {
                if (item.isSelectedValue) {
                    selectedItemsIds.add(item.idValue)
                }
            }
            return selectedItemsIds
        }

    override fun onCancel(dialog: DialogInterface) {
        // refresh text on spinner
        val spinnerBuffer = StringBuilder()
        val selectedData = ArrayList<KeyPairBoolData?>()
        for (i in items!!.indices) {
            val currentData = items!![i]
            if (currentData.isSelectedValue) {
                selectedData.add(currentData)
                spinnerBuffer.append(currentData.nameValue)
                spinnerBuffer.append(", ")
            }
        }
        var spinnerText = spinnerBuffer.toString()
        spinnerText = if (spinnerText.length > 2) spinnerText.substring(
            0,
            spinnerText.length - 2
        ) else hintText
        val adapterSpinner: ArrayAdapter<String> = ArrayAdapter<String>(
            getContext(),
            R.layout.custom_spiner_layout,
            arrayOf<String>(spinnerText)
        )
        setAdapter(adapterSpinner)
        adapter?.notifyDataSetChanged()
        listener!!.onItemsSelected(selectedData)
        onDetachedFromWindow()
    }

    override fun performClick(): Boolean {
        super.performClick()
        builder = AlertDialog.Builder(context)
        builder!!.setTitle(spinnerTitle)
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.alert_dialog_listview_search, null)
        builder!!.setView(view)
        val listView = view.findViewById<ListView>(R.id.alertSearchListView)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView.isFastScrollEnabled = false
        adapter = MyAdapter(context, items)
        listView.adapter = adapter
        val emptyText: TextView = view.findViewById<TextView>(R.id.empty)
        emptyText.text = emptyTitle
        listView.emptyView = emptyText
        val editText: EditText = view.findViewById<EditText>(R.id.alertSearchEditText)
        if (isSearchEnabled) {
            editText.visibility = View.VISIBLE
            editText.hint = searchHint
            editText.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    adapter!!.filter.filter(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {}
            })
        } else {
            editText.visibility = View.GONE
        }

        /*
         * For selected items
         */selected = 0
        for (i in items!!.indices) {
            if (items!![i].isSelectedValue) selected++
        }

        /*
        Added Select all Dialog Button.
         */if (isShowSelectAllButton && limit == -1) {
            builder!!.setNeutralButton(
                R.string.selectAll,
                DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                    for (i in adapter!!.arrayList!!.indices) {
                        adapter!!.arrayList!![i].isSelectedValue = true
                    }
                    adapter!!.notifyDataSetChanged()
                    // To call onCancel listner and set title of selected items.
                    dialog.cancel()
                })
        }
        builder!!.setPositiveButton(
            R.string.ok,
            DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                //Log.i(TAG, " ITEMS : " + items.size());
                dialog.cancel()
            })
        builder!!.setNegativeButton(
            clearText,
            DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                //Log.i(TAG, " ITEMS : " + items.size());
                for (i in adapter!!.arrayList!!.indices) {
                    adapter!!.arrayList!![i].isSelectedValue = false
                }
                adapter!!.notifyDataSetChanged()
                dialog.cancel()
            })
        builder!!.setOnCancelListener(this)
//        ad = builder!!.show()

        ad = builder!!.create()
        ad!!.show()
        Objects.requireNonNull(ad!!.window)
            ?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return true
    }

    fun setItems(items: List<KeyPairBoolData>, listener: MultiSpinnerListener?) {
        this.items = items
        this.listener = listener
        val spinnerBuffer = StringBuilder()
        for (i in items.indices) {
            if (items[i].isSelectedValue) {
                spinnerBuffer.append(items[i].nameValue)
                spinnerBuffer.append(", ")
            }
        }
        if (spinnerBuffer.length > 2) defaultText =
            spinnerBuffer.toString().substring(0, spinnerBuffer.toString().length - 2)
        val adapterSpinner: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            R.layout.custom_spiner_layout,
            arrayOf<String>(defaultText)
        )
        setAdapter(adapterSpinner)
    }




    fun setEmptyTitle(emptyTitle: String) {
        this.emptyTitle = emptyTitle
    }

    fun setSearchHint(searchHint: String) {
        this.searchHint = searchHint
    }

    interface LimitExceedListener {
        fun onLimitListener(data: KeyPairBoolData?)
    }


    inner class MyAdapter(
        context: Context,
        var arrayList: List<KeyPairBoolData>?
    ) : BaseAdapter(), Filterable {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var mOriginalValues: List<KeyPairBoolData>? = arrayList
        private val defaultBackground: Int = R.color.white
        override fun getCount(): Int {
            return arrayList?.size ?: 0
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val holder: ViewHolder
            if (convertView == null) {
                holder = ViewHolder()
                convertView = inflater.inflate(R.layout.item_listview_multiple, parent, false)
                holder.textView = convertView.findViewById<TextView>(R.id.alertTextView)
                holder.checkBox = convertView.findViewById<CheckBox>(R.id.alertCheckbox)
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }

            val data = arrayList?.get(position)

            holder.textView?.text = data?.nameValue
            holder.checkBox?.isChecked = data?.isSelectedValue ?: false

            convertView?.setOnClickListener { v: View ->
                data?.let {
                    if (it.isSelectedValue) {
                        selected--
                    } else {
                        selected++
                        if (selected > limit && limit > 0) {
                            --selected
                            limitListener?.onLimitListener(it)
                            return@setOnClickListener
                        }
                    }
                    val temp = v.tag as ViewHolder
                    temp.checkBox?.isChecked = !temp.checkBox?.isChecked!!
                    it.isSelectedValue = !it.isSelectedValue
                    notifyDataSetChanged()
                }
            }

            if (data?.isSelectedValue == true) {
                holder.textView?.setTextColor(textColor)
                if (highlightSelected) {
                    holder.textView?.setTypeface(null, Typeface.BOLD)
                    convertView?.setBackgroundColor(highlightColor)
                } else {
                    convertView?.setBackgroundColor(Color.WHITE)
                }
            } else {
                holder.textView?.setTypeface(null, Typeface.NORMAL)
                convertView?.setBackgroundColor(Color.WHITE)
            }

            holder.checkBox?.tag = holder

            return convertView!!
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val results = FilterResults()
                    val filteredList: MutableList<KeyPairBoolData> = ArrayList()

                    if (constraint.isNullOrEmpty()) {
                        results.count = mOriginalValues?.size ?: 0
                        results.values = mOriginalValues
                    } else {
                        val filterPattern = constraint.toString().lowercase(Locale.getDefault())
                        for (item in mOriginalValues.orEmpty()) {
                            if (item.nameValue?.lowercase(Locale.getDefault())?.contains(filterPattern) == true) {
                                filteredList.add(item)
                            }
                        }
                        results.count = filteredList.size
                        results.values = filteredList
                    }

                    return results
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                    @Suppress("UNCHECKED_CAST")
                    arrayList = results.values as List<KeyPairBoolData>?
                    notifyDataSetChanged()
                }
            }
        }

        private inner class ViewHolder {
            var textView: TextView? = null
            var checkBox: CheckBox? = null
        }
    }


    //Adapter Class
//    inner class MyAdapter internal constructor(
//        context: Context?,
//        var arrayList: List<KeyPairBoolData>?
//    ) : BaseAdapter(), Filterable {
//        val mOriginalValues: List<KeyPairBoolData>? = arrayList
//        private val inflater: LayoutInflater
//
//        init {
//            inflater = LayoutInflater.from(context)
//        }
//
//        val itemCount: Int
//            get() = try {
//                arrayList?.size ?: 0
//            } catch (e: Exception) {
//                e.printStackTrace()
//                0
//            }
//
//
//        override fun getItem(position: Int): Any {
//            return position
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//
//        override fun getView(position: Int, convertView: View, parent: ViewGroup): View? {
////            //Log.i(TAG, "getView() enter");
//            var convertView: View? = convertView
//            val holder: ViewHolder
//            if (convertView == null) {
//                holder = ViewHolder()
//                convertView = inflater.inflate(R.layout.item_listview_multiple, parent, false)
//                holder.textView = convertView?.findViewById<TextView>(R.id.alertTextView)
//                holder.checkBox = convertView?.findViewById<CheckBox>(R.id.alertCheckbox)
//                convertView.tag = holder
//            } else {
//                holder = convertView.tag as ViewHolder
//            }
//            var background: Int = R.color.white
//            if (isColorSeparation) {
//                val backgroundColor: Int =
//                    if (position % 2 == 0) R.color.list_even else R.color.list_odd
//                background = backgroundColor
//                convertView?.setBackgroundColor(
//                    ContextCompat.getColor(
//                        context,
//                        backgroundColor
//                    )
//                )
//            }
//            val data = arrayList!![position]
//            holder.textView?.text = data.nameValue
//            holder.checkBox?.isChecked = data.isSelectedValue
//            convertView?.setOnClickListener(View.OnClickListener setOnClickListener@{ v: View ->
//                if (data.isSelectedValue) { // deselect
//                    selected--
//                } else { // selected
//                    selected++
//                    if (selected > limit && limit > 0) {
//                        --selected // select with limit
//                        if (limitListener != null) limitListener!!.onLimitListener(data)
//                        return@setOnClickListener
//                    }
//                }
//                val temp = v.tag as ViewHolder
//                temp.checkBox?.isChecked = !temp.checkBox?.isChecked()!!
//                data.isSelectedValue = !data.isSelectedValue
//                //Log.i(TAG, "On Click Selected Item : " + data.getName() + " : " + data.isSelectedValue());
//                notifyDataSetChanged()
//            })
//            if (data.isSelectedValue) {
//                holder.textView?.setTextColor(textColor)
//                if (highlightSelected) {
//                    holder.textView?.setTypeface(null, Typeface.BOLD)
//                    convertView?.setBackgroundColor(highlightColor)
//                } else {
//                    convertView?.setBackgroundColor(Color.WHITE)
//                }
//            } else {
//                holder.textView?.setTypeface(null, Typeface.NORMAL)
//                convertView?.setBackgroundColor(ContextCompat.getColor(getContext(), background))
//            }
//            holder.checkBox?.setTag(holder)
//            return convertView
//        }
//
//        @SuppressLint("DefaultLocale")
//        override fun getFilter(): Filter {
//            return object : Filter() {
//                override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                    arrayList = results.values as List<KeyPairBoolData> // has the filtered values
//                    notifyDataSetChanged() // notifies the data with new filtered values
//                }
//
//                override fun performFiltering(constraint: CharSequence): FilterResults {
//                    var constraint: CharSequence? = constraint
//                    val results =
//                        FilterResults() // Holds the results of a filtering operation in values
//                    val FilteredArrList: MutableList<KeyPairBoolData> = ArrayList()
//
//
//                    /*
//                     *
//                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
//                     *  else does the Filtering and returns FilteredArrList(Filtered)
//                     *
//                     **/if (constraint.isNullOrEmpty()) {
//
//                        // set the Original result to return
//                        results.count = mOriginalValues!!.size
//                        results.values = mOriginalValues
//                    } else {
//                        constraint = constraint.toString().lowercase(Locale.getDefault())
//                        for (i in mOriginalValues!!.indices) {
//                            //Log.i(TAG, "Filter : " + mOriginalValues.get(i).getName() + " -> " + mOriginalValues.get(i).isSelected());
//                            val data = mOriginalValues[i].nameValue
//                            if (data!!.lowercase(Locale.getDefault())
//                                    .contains(constraint.toString())
//                            ) {
//                                FilteredArrList.add(mOriginalValues[i])
//                            }
//                        }
//                        // set the Filtered result to return
//                        results.count = FilteredArrList.size
//                        results.values = FilteredArrList
//                    }
//                    return results
//                }
//            }
//        }
//
//        private inner class ViewHolder {
//            var textView: TextView? = null
//            var checkBox: CheckBox? = null
//        }
//    }

    companion object {
        var builder: AlertDialog.Builder? = null
        var ad: AlertDialog? = null
    }
}