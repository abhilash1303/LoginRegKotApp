package www.rahagloball.loginregkotapp.srchspinr

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.FragmentActivity
import www.rahagloball.loginregkotapp.R


class SearchableSpinner : AppCompatSpinner, View.OnTouchListener {
    companion object {
        const val NO_ITEM_SELECTED = -1
    }

    private var _context: Context
    private var _items: MutableList<Any> = mutableListOf()
    private lateinit var _searchableListDialog: SearchableListDialog
    private var _isDirty: Boolean = false
    private var _arrayAdapter: ArrayAdapter<*>? = null
    private var _strHintText: String? = null
    private var _isFromInit: Boolean = false




   constructor(context: Context) : super(context) {
        _context = context
        _searchableListDialog = SearchableListDialog.newInstance(_items)
//        _searchableListDialog.setOnSearchableItemClickListener(this)
        setOnTouchListener(this)
        _arrayAdapter = adapter as? ArrayAdapter<*>
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        _context = context
        val a = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinner)
        val N = a.indexCount
        for (i in 0 until N) {
            val attr = a.getIndex(i)
            if (attr == R.styleable.SearchableSpinner_hintTextt) {
                _strHintText = a.getString(attr)
            }
        }
        a.recycle()
        _searchableListDialog = SearchableListDialog.newInstance(_items)
//        _searchableListDialog.setOnSearchableItemClickListener(this)
        setOnTouchListener(this)
        _arrayAdapter = adapter as? ArrayAdapter<*>
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        _context = context
        _searchableListDialog = SearchableListDialog.newInstance(_items)
//        _searchableListDialog.setOnSearchableItemClickListener(this)
        setOnTouchListener(this)
        _arrayAdapter = adapter as? ArrayAdapter<*>
        init()
    }

    private fun init() {
        if (!TextUtils.isEmpty(_strHintText)) {
            val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_list_item_1, arrayOf(_strHintText))
            _isFromInit = true
            adapter = arrayAdapter
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            _arrayAdapter?.let {
                _items.clear()
                for (i in 0 until _arrayAdapter!!.count) {
                    _items.add(_arrayAdapter!!.getItem(i)!!)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    (context as? FragmentActivity)?.supportFragmentManager?.let { it1 ->
                        _searchableListDialog.show(
                            it1, "TAG")
                    }

               }
            }
        }
        return true
    }

    override fun setAdapter(adapter: SpinnerAdapter?) {
        if (!_isFromInit) {
            _arrayAdapter = adapter as? ArrayAdapter<*>
            if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
                val arrayAdapter = ArrayAdapter(_context, android.R.layout.simple_list_item_1, arrayOf(_strHintText))
                super.setAdapter(arrayAdapter)
            } else {
                super.setAdapter(adapter)
            }
        } else {
            _isFromInit = false
            super.setAdapter(adapter)
        }
    }

    fun onSearchableItemClicked(item: Any?, position: Int) {
        setSelection(_items.indexOf(item))
        if (!_isDirty) {
            _isDirty = true
            adapter = _arrayAdapter
            setSelection(_items.indexOf(item))
        }
    }

    fun setTitle(strTitle: String) {
        _searchableListDialog.setTitle(strTitle)
    }

    fun setPositiveButton(strPositiveButtonText: String) {
        _searchableListDialog.setPositiveButton(strPositiveButtonText)
    }

    fun setPositiveButton(strPositiveButtonText: String, onClickListener: DialogInterface.OnClickListener) {
        _searchableListDialog.setPositiveButton(strPositiveButtonText, onClickListener)
    }

    fun setOnSearchTextChangedListener(onSearchTextChanged: SearchableListDialog.OnSearchTextChanged) {
        _searchableListDialog.setOnSearchTextChangedListener(onSearchTextChanged)
    }

    private fun scanForActivity(cont: Context?): Activity? {
        if (cont == null) return null
        if (cont is Activity) return cont
        if (cont is ContextWrapper) return scanForActivity(cont.baseContext)
        return null
    }

    override fun getSelectedItemPosition(): Int {
        return if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
            NO_ITEM_SELECTED
        } else {
            super.getSelectedItemPosition()
        }
    }

    override fun getSelectedItem(): Any? {
        return if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
            null
        } else {
            super.getSelectedItem()
        }
    }
}


//class SearchableSpinner : AppCompatSpinner, View.OnTouchListener, SearchableListDialog.SearchableItem<Any?> {
//    private var _context: Context
//    private var _items: MutableList<*>? = null
//    private var _searchableListDialog: SearchableListDialog? = null
//    private var _isDirty = false
//    private var _arrayAdapter: ArrayAdapter<*>? = null
//    private var _strHintText: String? = null
//    private var _isFromInit = false
//
//    constructor(context: Context) : super(context) {
//        _context = context
//        init()
//    }
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
//        _context = context
//        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinner)
//        val N: Int = a.indexCount
//        for (i in 0 until N) {
//            val attr: Int = a.getIndex(i)
//            if (attr == R.styleable.SearchableSpinner_hintTextt) {
//                _strHintText = a.getString(attr)
//            }
//        }
//        a.recycle()
//        init()
//    }
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    ) {
//        _context = context
//        init()
//    }
//    private fun init() {
//        _items = ArrayList<Any?>()
//        _searchableListDialog = SearchableListDialog.newInstance(_items as ArrayList<Any?>)
//        _searchableListDialog!!.setOnSearchableItemClickListener(this)
//        setOnTouchListener(this)
//        _arrayAdapter = adapter as ArrayAdapter<*>?
//        if (!TextUtils.isEmpty(_strHintText)) {
//            val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
//                _context,
//                android.R.layout.simple_list_item_1,
//                arrayOf<String?>(_strHintText)
//            )
//            _isFromInit = true
//            setAdapter(arrayAdapter)
//        }
//    }
//
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        if (event?.action == MotionEvent.ACTION_UP) {
//            if (_arrayAdapter != null) {
//                _items?.clear()
//                for (i in 0 until _arrayAdapter!!.count) {
//                    _items?.add(_arrayAdapter!!.getItem(i) as Nothing)
//                }
//                // Change end.
//                scanForActivity(_context)?.let { fragmentManager ->
//                    _searchableListDialog?.show(fragmentManager, "TAG")
//                }
//            }
//        }
//        return true
//    }
//
//    override fun setAdapter(adapter: SpinnerAdapter) {
//        if (!_isFromInit) {
//            _arrayAdapter = adapter as ArrayAdapter<*>
//            if (!TextUtils.isEmpty(_strHintText) && !_isDirty) {
//                val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
//                    _context,
//                    android.R.layout.simple_list_item_1,
//                    arrayOf<String?>(_strHintText)
//                )
//                super.setAdapter(arrayAdapter)
//            } else {
//                super.setAdapter(adapter)
//            }
//        } else {
//            _isFromInit = false
//            super.setAdapter(adapter)
//        }
//    }
//
//    override fun onSearchableItemClicked(item: Any?, position: Int) {
//        setSelection(_items!!.indexOf(item))
//        if (!_isDirty) {
//            _isDirty = true
//            _arrayAdapter?.let { setAdapter(it) }
//            setSelection(_items!!.indexOf(item))
//        }
//    }
//
//    override fun scanForActivity(_context: Any?): Any {
//        TODO("Not yet implemented")
//    }
//
//    fun setTitle(strTitle: String?) {
//        _searchableListDialog!!.setTitle(strTitle)
//    }
//    fun setPositiveButton(strPositiveButtonText: String?) {
//        _searchableListDialog!!.setPositiveButton(strPositiveButtonText)
//    }
//
//    fun setPositiveButton(
//        strPositiveButtonText: String?,
//        onClickListener: DialogInterface.OnClickListener?) {
//        _searchableListDialog!!.setPositiveButton(strPositiveButtonText, onClickListener)
//    }
//    fun setOnSearchTextChangedListener(onSearchTextChanged: SearchableListDialog.OnSearchTextChanged?) {
//        _searchableListDialog!!.setOnSearchTextChangedListener(onSearchTextChanged)
//    }
//        private fun scanForActivity(cont: Context?): Activity? {
//        if (cont == null) return null else if (cont is Activity) return cont else if (cont is ContextWrapper) return scanForActivity(
//            cont.baseContext
//        )
//        return null
//    }
//
//   companion object {
//        const val NO_ITEM_SELECTED = -1
//    }
//}