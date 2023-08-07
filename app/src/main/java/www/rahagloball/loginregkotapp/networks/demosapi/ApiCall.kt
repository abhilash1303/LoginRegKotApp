package www.rahagloball.loginregkotapp.networks.demosapi


import android.annotation.SuppressLint
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import www.rahagloball.loginregkotapp.configuration.Configs

class ApiCall private constructor(private val mCtx: Context) {
    private var mRequestQueue: RequestQueue? = null

    init {
        mRequestQueue = requestQueue
    }

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(mCtx.applicationContext)
            }
            return mRequestQueue!!
        }

    fun <T> addToRequestQueue(req: Request<T>?) {
        requestQueue.add(req)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mInstance: ApiCall? = null

        @Synchronized
        fun getInstance(context: Context): ApiCall {
            return mInstance ?: synchronized(this) {
                val instance = ApiCall(context)
                mInstance = instance
                instance
            }
        }

        fun make(
            ctx: Context,
            query: String,
            listener: Response.Listener<String?>?,
            errorListener: Response.ErrorListener?
        ) {
            val url = Configs.BASE_URL2 + "suggestion-search?query=" + query
            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
            getInstance(ctx).addToRequestQueue(stringRequest)
        }


        fun chanel_srch(
            ctx: Context,
            query: String,
            listener: Response.Listener<String?>?,
            errorListener: Response.ErrorListener?
        ) {
            val url = Configs.BASE_URL2 + "channelsearch/" + query
            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
            getInstance(ctx).addToRequestQueue(stringRequest)
        }

        fun instructor_srch(
            ctx: Context,
            query: String,
            listener: Response.Listener<String?>?,
            errorListener: Response.ErrorListener?
        ) {
            val url = Configs.BASE_URL2 + "instructorsearch/" + query
            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
            getInstance(ctx).addToRequestQueue(stringRequest)
        }

        fun coursesearch(
            ctx: Context,
            query: String,
            listener: Response.Listener<String?>?,
            errorListener: Response.ErrorListener?
        ) {
            val url = Configs.BASE_URL2 + "coursesearch/" + query
            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
            getInstance(ctx).addToRequestQueue(stringRequest)
        }
    }
}


//
//class ApiCall() {
//    private var mRequestQueue: RequestQueue?
//    private lateinit var mCtx: Context
//    init {
//        mRequestQueue = requestQueue
//    }
//
//    val requestQueue: RequestQueue
//        get() {
//            if (mRequestQueue == null) {
//                mRequestQueue = Volley.newRequestQueue(mCtx.applicationContext)
//            }
//            return mRequestQueue!!
//        }
//
//    fun <T> addToRequestQueue(req: Request<T>?) {
//        requestQueue.add(req)
//    }
//
//    companion object {
//         lateinit  var mInstance: ApiCall
//        @Synchronized
//        fun getInstance(context: Context): ApiCall {
//            if (!::mInstance.isInitialized) {
//                mInstance = ApiCall() // Initialize the property here
//            }
//            return mInstance
//        }
//
//        fun make(
//            ctx: Context,
//            query: String,
//            listener: Response.Listener<String?>?,
//            errorListener: Response.ErrorListener?
//        ) {
//            val url = Configs.BASE_URL2 + "suggestion-search?query=" + query
//            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
//            getInstance(ctx).addToRequestQueue(stringRequest)
//        }
//
//        fun chanel_srch(
//            ctx: Context,
//            query: String,
//            listener: Response.Listener<String?>?,
//            errorListener: Response.ErrorListener?
//        ) {
//            val url = Configs.BASE_URL2 + "channelsearch/" + query
//            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
//            getInstance(ctx).addToRequestQueue(stringRequest)
//        }
//
//        fun instructor_srch(
//            ctx: Context,
//            query: String,
//            listener: Response.Listener<String?>?,
//            errorListener: Response.ErrorListener?
//        ) {
//            val url = Configs.BASE_URL2 + "instructorsearch/" + query
//            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
//            getInstance(ctx).addToRequestQueue(stringRequest)
//        }
//
//        fun coursesearch(
//            ctx: Context,
//            query: String,
//            listener: Response.Listener<String?>?,
//            errorListener: Response.ErrorListener?
//        ) {
//            val url = Configs.BASE_URL2 + "coursesearch/" + query
//            val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
//            getInstance(ctx).addToRequestQueue(stringRequest)
//        }
//    }
//}