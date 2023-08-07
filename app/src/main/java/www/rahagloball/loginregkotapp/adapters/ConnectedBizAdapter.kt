package www.rahagloball.loginregkotapp.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.constsnsesion.TriangleLabelView
import www.rahagloball.loginregkotapp.models.conectedbiz.Business
import www.rahagloball.loginregkotapp.models.conectedbiz.ConnectlistItem

class ConnectedBizAdapter : RecyclerView.Adapter<ConnectedBizAdapter.ViewHolder> {
    var dataSet: List<ConnectlistItem>
    var mContext: Activity
    var ass_id: String? = null
    var token: String? = null

    constructor(catggryList: List<ConnectlistItem>, activity: Activity) {
        dataSet = catggryList
        mContext = activity
    }

//    constructor(catggryList: List<ConnectlistItem>, activity: SubCatListActivity) {
//        dataSet = catggryList
//        mContext = activity
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(catggryList: List<ConnectlistItem>) {
        dataSet = catggryList
        notifyDataSetChanged()
    }

    fun setActionModeReceiver(receiver: OnClickActionn?) {
        this.receiver = receiver
    }

    var receiver: OnClickActionn? = null

    interface OnClickActionn {
        fun onClickActionn(item: ConnectlistItem?, pos: Int, holder: ViewHolder?)
    }

    constructor(
        catggryList: List<ConnectlistItem>,
        activity: Activity,
        receiver: OnClickActionn?
    ) {
        dataSet = catggryList
        mContext = activity
        this.receiver = receiver
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.connected_biz_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") i: Int) {
        val assoname1: TextView = viewHolder.assoname_cbl
        val city1: TextView = viewHolder.city_cbl
        val prdcttt: TextView = viewHolder.prodct_cbl
        val emailll: TextView = viewHolder.email_cbl
        val number_cbll: TextView = viewHolder.number_cbl
        val asso_id1: TextView = viewHolder.asso_id_cbl
        val img = viewHolder.imageview_cbl
        val call_cnt = viewHolder.call_cbl
        val wtsap_cnt = viewHolder.whtsapp_cbl
        val ll_call_whatsapp: LinearLayout = viewHolder.ll_call_whatsapp
        val ll_waiting_cnct: LinearLayout = viewHolder.ll_waiting_cnct
        val ll_connected: LinearLayout = viewHolder.ll_connected
        val verified_tickk = viewHolder.verified_tickk
        val verified_tickk_red = viewHolder.verified_tickk_red
        val blog_icon1: TriangleLabelView = viewHolder.blog_icon1
        val dataItemSc: ConnectlistItem = dataSet[i]
        val business: Business? = dataItemSc.business
        assoname1.text = business?.name
        emailll.text = business?.email
        city1.text = business?.city
        ass_id = dataSet[i].id
        val tel_numberStr: String? = business?.mobile
        //        Log.e("tel_numberStr", tel_numberStr);
        number_cbll.text = tel_numberStr
        val statusss: String? = dataItemSc?.status
        //        Log.e("statusss_call_whatsap", statusss);
        if (statusss != null) {
            if (statusss.contains("1")) {
                ll_call_whatsapp.visibility=(View.VISIBLE)
                verified_tickk.visibility = View.VISIBLE
                blog_icon1.visibility=(View.VISIBLE)
                ll_connected.visibility=(View.VISIBLE)
                verified_tickk_red.visibility = View.GONE
                ll_waiting_cnct.visibility=(View.GONE)
            } else {
                ll_call_whatsapp.visibility=(View.GONE)
                verified_tickk_red.visibility = View.VISIBLE
                blog_icon1.visibility=(View.GONE)
                ll_connected.visibility=(View.GONE)
                ll_waiting_cnct.visibility=(View.VISIBLE)
            }
        }

        Glide.with(mContext)
            .load(Configs.BASE_URL21 + "images/business/banner/" + business?.bannerImage)
            .into(img)

        call_cnt.setOnClickListener { view: View? ->
            val tel_numberStr1: String? = business?.mobile
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.setData(Uri.parse("tel:$tel_numberStr1"))
            mContext.startActivity(callIntent)
        }
        wtsap_cnt.setOnClickListener { v: View ->
            val url = "https://api.whatsapp.com/send?phone=$tel_numberStr"
            try {
                val pm: PackageManager = mContext.getPackageManager()
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val whatsappintent = Intent(Intent.ACTION_VIEW)
                whatsappintent.setData(Uri.parse(url))
                mContext.startActivity(whatsappintent)
            } catch (e: PackageManager.NameNotFoundException) {
                v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                Toast.makeText(
                    mContext,
                    "Whatsapp app not installed in your phone",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var whtsapp_cbl: Button
        var call_cbl: Button
        var prodct_cbl: TextView
        var city_cbl: TextView
        var number_cbl: TextView
        var assoname_cbl: TextView
        var email_cbl: TextView
        var asso_id_cbl: TextView
        var imageview_cbl: ImageView
        var verified_tickk: ImageView
        var verified_tickk_red: ImageView
        var blog_icon1: TriangleLabelView
        var ll_call_whatsapp: LinearLayout
        var ll_waiting_cnct: LinearLayout
        var ll_connected: LinearLayout

        init {
            assoname_cbl = itemView.findViewById<TextView>(R.id.assoname_cbl)
            email_cbl = itemView.findViewById<TextView>(R.id.email_cbl)
            number_cbl = itemView.findViewById<TextView>(R.id.number_cbl)
            city_cbl = itemView.findViewById<TextView>(R.id.city_cbl)
            prodct_cbl = itemView.findViewById<TextView>(R.id.prodct_cbl)
            imageview_cbl = itemView.findViewById<ImageView>(R.id.imageview_cbl)
            whtsapp_cbl = itemView.findViewById<Button>(R.id.whtsapp_cbl)
            call_cbl = itemView.findViewById<Button>(R.id.call_cbl)
            asso_id_cbl = itemView.findViewById<TextView>(R.id.asso_id_cbl)
            ll_call_whatsapp = itemView.findViewById<LinearLayout>(R.id.ll_call_whatsapp)
            ll_waiting_cnct = itemView.findViewById<LinearLayout>(R.id.ll_waiting_cnct)
            verified_tickk = itemView.findViewById<ImageView>(R.id.verified_tickk)
            blog_icon1 = itemView.findViewById(R.id.blog_icon1)
            ll_connected = itemView.findViewById<LinearLayout>(R.id.ll_connected)
            verified_tickk_red = itemView.findViewById<ImageView>(R.id.verified_tickk_red)
        }
    }
}