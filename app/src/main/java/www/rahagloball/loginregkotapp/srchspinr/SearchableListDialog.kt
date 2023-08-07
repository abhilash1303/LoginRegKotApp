package www.rahagloball.loginregkotapp.srchspinr

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import www.rahagloball.loginregkotapp.R
import java.io.Serializable


class SearchableListDialog : DialogFragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private lateinit var listAdapter: ArrayAdapter<String>
    private lateinit  var _listViewItems: ListView
    private lateinit var _searchableItem: SearchableItem<*>
    private lateinit var _onSearchTextChanged: OnSearchTextChanged
    private lateinit var _searchView: SearchView
    private lateinit var _strTitle: String
    private var _strPositiveButtonText: String = "CLOSE"
    private lateinit var _onClickListener: DialogInterface.OnClickListener

    interface SearchableItem<T> : Serializable {
        fun onSearchableItemClicked(item: String, position: Int)
    }

    interface OnSearchTextChanged {
        fun onSearchTextChanged(strText: String?)
    }

    companion object {
        private const val ITEMS = "items"

        fun newInstance(items: MutableList<Any>): SearchableListDialog {
            val searchableListDialog = SearchableListDialog()
            val args = Bundle()
            args.putSerializable(ITEMS, items as Serializable?)
            searchableListDialog.arguments = args
            return searchableListDialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val rootView = inflater.inflate(R.layout.searchable_list_dialog, null)
        setData(rootView)
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setView(rootView)
        val strPositiveButton = _strPositiveButtonText
        alertDialog.setPositiveButton(strPositiveButton, _onClickListener)
        val strTitle = _strTitle ?: "Select Item"
        alertDialog.setTitle(strTitle)
        val dialog = alertDialog.create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        return dialog
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("item", _searchableItem)
        super.onSaveInstanceState(outState)
    }

    fun setTitle(strTitle: String) {
        _strTitle = strTitle
    }

    fun setPositiveButton(strPositiveButtonText: String) {
        _strPositiveButtonText = strPositiveButtonText
    }
    fun setPositiveButton(strPositiveButtonText: String, onClickListener: DialogInterface.OnClickListener) {
        _strPositiveButtonText = strPositiveButtonText
        _onClickListener = onClickListener
    }

//
//    fun setPositiveButton(strPositiveButtonText: String, onClickListener: DialogInterface.OnClickListener) {
//        _strPositiveButtonText = strPositiveButtonText
//        _onClickListener = onClickListener
//    }

    fun setOnSearchableItemClickListener(searchableItem: SearchableItem<*>) {
        _searchableItem = searchableItem
    }

    fun setOnSearchTextChangedListener(onSearchTextChanged: OnSearchTextChanged) {
        _onSearchTextChanged = onSearchTextChanged
    }

    private fun setData(rootView: View) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        _searchView = rootView.findViewById(R.id.search)
        _searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        _searchView.isIconifiedByDefault = false
        _searchView.setOnQueryTextListener(this)
        _searchView.setOnCloseListener(this)
        _searchView.clearFocus()
        val mgr = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(_searchView.windowToken, 0)
        val items = arguments?.getSerializable(ITEMS) as List<*>
        _listViewItems = rootView.findViewById(R.id.listItems)
        listAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, items as List<String?>)
        _listViewItems.adapter = listAdapter
        _listViewItems.isTextFilterEnabled = true
        _listViewItems.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                listAdapter.getItem(position)
                    ?.let { _searchableItem.onSearchableItemClicked(it, position) }
                dialog?.dismiss()
            }
    }

    override fun onClose(): Boolean {
        return false
    }

    override fun onQueryTextSubmit(s: String): Boolean {
        _searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(s: String): Boolean {
        listAdapter.filter.filter(s)
        if (TextUtils.isEmpty(s)) {
            listAdapter.filter.filter(null)
        } else {
            listAdapter.filter.filter(s)
        }
        _onSearchTextChanged.onSearchTextChanged(s)
        return true
    }
}



//class SearchableListDialog : DialogFragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
//
//    private lateinit var listAdapter: ArrayAdapter<String>
//    private lateinit var listViewItems: ListView
//    private lateinit var searchableItem: SearchableItem<*>
//    private var onSearchTextChanged: OnSearchTextChanged? = null
//    private lateinit var searchView: SearchView
//    private var strTitle: String? = null
//    private var strPositiveButtonText: String? = null
//    private var onClickListener: DialogInterface.OnClickListener? = null
//    private var _searchableListDialog: SearchableListDialog? = null
//
//    interface SearchableItem<T> : Serializable {
//        fun onSearchableItemClicked(item: Any?, position: Int)
//        abstract fun scanForActivity(_context: T): Any
//    }
//
//    interface OnSearchTextChanged {
//        fun onSearchTextChanged(strText: String)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val inflater = LayoutInflater.from(activity)
//        if (savedInstanceState != null) {
//            searchableItem = savedInstanceState.getSerializable("item") as SearchableItem<*>
//        }
//        val rootView = inflater.inflate(R.layout.searchable_list_dialog, null)
//        setData(rootView)
//
//        val alertDialogBuilder = android.app.AlertDialog.Builder(activity)
//        alertDialogBuilder.setView(rootView)
//
//        val positiveButton = strPositiveButtonText ?: "CLOSE"
//        alertDialogBuilder.setPositiveButton(positiveButton, onClickListener)
//
//        val title = strTitle ?: "Select Item"
//        alertDialogBuilder.setTitle(title)
//
//        val dialog = alertDialogBuilder.create()
//        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//        return dialog
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putSerializable("item", searchableItem)
//        super.onSaveInstanceState(outState)
//    }
//
//    fun setTitle(title: String?) {
//        strTitle = title
//        dialog?.setTitle(title)
//    }
//
//    fun setPositiveButton(positiveButtonText: String?) {
//        strPositiveButtonText = positiveButtonText
//    }
//
//    fun setPositiveButton(positiveButtonText: String?, listener: DialogInterface.OnClickListener?) {
//        strPositiveButtonText = positiveButtonText
//        onClickListener = listener
//    }
//
//    fun setOnSearchableItemClickListener(searchableItem: SearchableItem<*>) {
//        this.searchableItem = searchableItem
//    }
//
//    fun setOnSearchTextChangedListener(onSearchTextChanged: OnSearchTextChanged?) {
//        this.onSearchTextChanged = onSearchTextChanged
//    }
//
//    private fun setData(rootView: View) {
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        searchView = rootView.findViewById(R.id.search)
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
//        searchView.isIconifiedByDefault = false
//        searchView.setOnQueryTextListener(this)
//        searchView.setOnCloseListener(this)
//        searchView.clearFocus()
//
//        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
//
////        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as WindowManager
////        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
////
//        val items = arguments?.getSerializable(ITEMS) as List<*>
//
//        listViewItems = rootView.findViewById(R.id.listItems)
//        listAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, items as List<String>)
//        listViewItems.adapter = listAdapter
//        listViewItems.isTextFilterEnabled = true
//
//        listViewItems.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                searchableItem.onSearchableItemClicked(listAdapter.getItem(position), position)
//                dialog?.dismiss()
//            }
//    }
//
//    override fun onClose(): Boolean {
//        return false
//    }
//
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        searchView.clearFocus()
//        return true
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        listAdapter.filter.filter(newText)
//        if (TextUtils.isEmpty(newText)) {
//            listAdapter.filter.filter(null)
//        } else {
//            listAdapter.filter.filter(newText)
//        }
//        onSearchTextChanged?.onSearchTextChanged(newText ?: "")
//        return true
//    }
//
//    override fun show(fragmentManager: FragmentManager, tag: String?) {
//        show(fragmentManager.beginTransaction(), tag)
//    }
//
//    fun show(fragmentManager: Activity, s: String) {
//        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
//        _searchableListDialog?.show(fragmentManager, tag)
//    }
//
//    companion object {
//        private const val ITEMS = "items"
//
//        fun newInstance(items: ArrayList<Any?>): SearchableListDialog {
//            val searchableListDialog = SearchableListDialog()
//            val args = Bundle()
//            args.putSerializable(ITEMS, items as Serializable)
//            searchableListDialog.arguments = args
//            return searchableListDialog
//        }
//    }
//}




