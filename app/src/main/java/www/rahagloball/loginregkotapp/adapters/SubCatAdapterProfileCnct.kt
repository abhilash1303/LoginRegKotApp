package www.rahagloball.loginregkotapp.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.AsstCntPrfileActivity
import www.rahagloball.loginregkotapp.activities.ConnectedBizList
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.TriangleLabelView
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.allconect.CnctCity
import www.rahagloball.loginregkotapp.models.allconect.ConnctData
import www.rahagloball.loginregkotapp.models.allconect.MyPackageCnct
import www.rahagloball.loginregkotapp.models.conectedbiz.ConctedListPojo
import www.rahagloball.loginregkotapp.models.conectedbiz.ConnectlistItem
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo

class SubCatAdapterProfileCnct : RecyclerView.Adapter<SubCatAdapterProfileCnct.ViewHolder> {
    //    List<BusinessItem> dataSet;
    var dataSet: List<ConnctData>? = null
    var mContext: Activity? = null
    var view_profile: Button? = null
    var request_services: Button? = null
    var videoView1: ImageView? = null
    var ass_id: String? = null
    var token: String? = null
    var catggryList_biz_id: String? = null
    var user_id_str: String? = null
    var manager: SessionManager? = null
    var blur_reg1: RelativeLayout? = null
    var connectt: Button? = null
    var edit_query: EditText? = null
    var catggryList1: List<ConnectlistItem>? = null
    var takeResCnct: String? = null
    var connectt_scc: TextView? = null
    var connectted_scc: TextView? = null
    var ll_alrdy_cnctdd: LinearLayout? = null
    var ll_alrdy_cnctd: LinearLayout? = null
    var submit_dialog: Button? = null
    var cancel_dialog: Button? = null
    var name: TextView? = null
    var asscote_name: TextView? = null
    var cnt_assoc_name: TextView? = null
    var cnt_assoc_num: TextView? = null
    var cnt_assoc_email: TextView? = null
    var agentty: TextView? = null
    var selct_bdgt_srvc: TextView? = null
    var select_budgt_spinr: Spinner? = null
    var servc_spinr: Spinner? = null
    var edit_query_str: String? = null
    var connect_sts: String? = null
    var connect_sts1: String? = null
    var bdget_spnr_str: String? = null
    var srvc_spnr_str: String? = null
    var edit_amnt_budget_str: String? = null
    var agentSpinCnt_str: String? = null
    var materialAlertDialogBuilder: MaterialAlertDialogBuilder? = null
    var customAlertDialogView: View? = null
    var assox_name_str: String? = null
    var assox_mob_str: String? = null
    var assox_email_str: String? = null
    var rankDialog: Dialog? = null
    var budget_str = arrayOf(
        "Select Budget",
        "Less than 500",
        "Between 500 to 5,000",
        "Between 5,000 to 10,000",
        "Between 10,000 to 20,000"
    )
    var service_str = arrayOf("Select Service", "Immediately", "Within 1 week", "Within 2 weeks")
    var edit_amnt_budget: EditText? = null
    var dataAdapter_bdgt: ArrayAdapter<String>? = null
    var dataAdapter_srvc: ArrayAdapter<String>? = null

    constructor(catggryList: List<ConnctData>?, activity: Activity?) {
        dataSet = catggryList
        mContext = activity
    }

    constructor(catggryList1: List<ConnectlistItem>?) {
        this.catggryList1 = catggryList1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_subcatgry_listdemo, parent, false)
        val myViewHolder: ViewHolder = ViewHolder(view)
        manager = SessionManager()
        connectt_scc = view.findViewById<TextView>(R.id.connectt_sc)
        blur_reg1 = view.findViewById<RelativeLayout>(R.id.blur_reg1)
        connectted_scc = view.findViewById<TextView>(R.id.connectted)
        ll_alrdy_cnctd = view.findViewById<LinearLayout>(R.id.ll_alrdy_cnctd)
        return myViewHolder
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") i: Int) {
        try {
            val assoname1: TextView = viewHolder.assoname_sc
            val city1: TextView = viewHolder.city_sc
            val asso_id1: TextView = viewHolder.asso_id_sc
            val pincodee: TextView = viewHolder.pincode_sc
            ll_alrdy_cnctdd = viewHolder.ll_alrdy_cnctd
            val img = viewHolder.circleImageview_sc
            val view_profile_scc: TextView = viewHolder.view_profile_sc
            connectt_scc = viewHolder.connectt_sc
            connectted_scc = viewHolder.connectted
            val txtRatingValue1: TextView = viewHolder.txtRatingValue_sc
            val ratingBar1: RatingBar = viewHolder.ratingBar_sc
            val verified_tickk = viewHolder.verified_tickk
            val verified_tickk_red = viewHolder.verified_tickk_red
            val blog_icon1: TriangleLabelView = viewHolder.blog_icon1
            assoname1.setText(dataSet!![i].name)
            val cnctCityyy: CnctCity? = dataSet!![i].city
            val cnctCityyy_str: String? = cnctCityyy?.name
            city1.setText(cnctCityyy_str)
            pincodee.setText(dataSet!![i].pincode)
            val cont_strrr: MyPackageCnct? = dataSet!![i].myPackage
            val cont_strrrstr: String? = cont_strrr?.packageName
            if (cont_strrrstr != null) {
                if (cont_strrrstr.contains("First Priority Listing") || cont_strrrstr.contains("Second Priority Listing")
                    || cont_strrrstr.contains("Trial Launch Package")
                ) {
                    connectt_scc?.visibility = View.VISIBLE
                } else {
                    connectt_scc?.visibility = View.GONE
                }
            }
            ass_id = dataSet!![i].id
            //        Log.e("ass_idBizIoutside", ass_id);
            val status_nocitystr: String? = dataSet!![i].status
            //            Log.e("statusoutside1", status_nocitystr);
            if (status_nocitystr != null) {
                if (status_nocitystr.contains("1")) {
                    connectt_scc?.setEnabled(true)
                } else {
                    connectt_scc?.setEnabled(false)
                }
            }
            val verify_str: String? = dataSet!![i].verify
            if (verify_str != null) {

//                Log.e("verify_str", verify_str);
                if (verify_str.contains("1")) {
                    verified_tickk.visibility = View.VISIBLE
                    blog_icon1.visibility = View.VISIBLE
                    verified_tickk_red.visibility = View.GONE
                } else {
                    verified_tickk.visibility = View.GONE
                    verified_tickk_red.visibility = View.VISIBLE
                    blog_icon1.visibility = View.GONE
                }
            }
            connectted_scc?.isEnabled = false
            connectted_scc?.setOnClickListener { view: View? ->
                Toast.makeText(
                    mContext,
                    "Already Connected!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

//        private void getbizcnttedlist() {


//            rl_cbl?.visibility = View.VISIBLE;
            val url: String = Configs.BASE_URL2 + "connect/"
            manager = SessionManager()
            token = mContext?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
            RetrofitClient.getClient().getcntbizz(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<ConctedListPojo?>(assoname1) {
                 override   fun onResponse(
                     call: Call<ConctedListPojo?>,
                     response: Response<ConctedListPojo?>
                 ) {

//                            rl_cbl?.visibility = View.GONE;
                        try {
                            val catggryList: List<ConnectlistItem>? =
                                response.body()?.connectlist
                            if (catggryList != null) {
                                if (catggryList.isNotEmpty()) {
                                    for (i2 in catggryList.indices) {
                                        val subBranchList: ConnectlistItem = catggryList[i2]
                                        catggryList_biz_id = subBranchList.businessId
                                        ass_id = dataSet!![i].id
                                        if (catggryList_biz_id == ass_id) {
                                            ll_alrdy_cnctdd?.visibility = View.VISIBLE
                                            connectt_scc?.visibility = View.GONE
                                            connectted_scc?.visibility = View.VISIBLE
                                            connectt_scc?.isEnabled = false
                                        } else {
                                            ll_alrdy_cnctdd?.visibility = View.GONE
                                            connectt_scc?.visibility = View.VISIBLE
                                            connectted_scc?.visibility = View.GONE
                                            connectt_scc?.isEnabled = true
                                        }
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
            asso_id1.setText(ass_id)
            mContext?.let {
                Glide.with(it)
                    .load(Configs.BASE_URL21 + "images/business/banner/" + dataSet!![i].bannerImage)
                    .into(img)
            }
            view_profile_scc.setOnClickListener(View.OnClickListener { view: View? ->
                ass_id = dataSet!![i].id
                user_id_str = dataSet!![i].userId
                val id = Intent(mContext, AsstCntPrfileActivity::class.java)
                id.putExtra("ass_id", ass_id)
                id.putExtra("user_iddd", user_id_str)
                mContext?.startActivity(id)
            })


//        connectted_scc?.visibility = View.VISIBLE;
//        connectted_scc.setEnabled(false);
            connectt_scc?.setOnClickListener(View.OnClickListener { v: View? ->
                rankDialog = mContext?.let { Dialog(it, R.style.FullHeightDialog) }
                rankDialog?.setContentView(R.layout.dialog_cat_connect)
                rankDialog!!.setCancelable(true)
                edit_query = rankDialog?.findViewById<EditText>(R.id.edit_query)
                asscote_name = rankDialog?.findViewById<TextView>(R.id.asscote_name)
                submit_dialog = rankDialog!!.findViewById<Button>(R.id.submit_dialog)
                cancel_dialog = rankDialog!!.findViewById<Button>(R.id.cancel_dialog)
                select_budgt_spinr = rankDialog?.findViewById<Spinner>(R.id.select_budgt_spinr)
                servc_spinr = rankDialog?.findViewById<Spinner>(R.id.servc_spinr)
                edit_amnt_budget = rankDialog?.findViewById<EditText>(R.id.edit_amnt_budget)
                cnt_assoc_name = rankDialog?.findViewById<TextView>(R.id.cnt_assoc_name)
                cnt_assoc_email = rankDialog?.findViewById<TextView>(R.id.cnt_assoc_email)
                cnt_assoc_num = rankDialog?.findViewById<TextView>(R.id.cnt_assoc_num)
                edit_query?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
                    // TODO Auto-generated method stub
                    if (view.id == R.id.edit_query) {
                        view.parent.requestDisallowInterceptTouchEvent(true)
                        if (event.action and MotionEvent.ACTION_MASK == MotionEvent.ACTION_UP) {
                            view.parent.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                    false
                })
                dataAdapter_srvc =
                    mContext?.let { ArrayAdapter<String>(it, R.layout.custom_spiner_layout, service_str) }
                dataAdapter_srvc?.setDropDownViewResource(R.layout.custom_spiner_layout)
                servc_spinr?.adapter = dataAdapter_srvc
                manager = SessionManager()
                token = mContext?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
                val url1: String = Configs.BASE_URL2 + "profile"
                RetrofitClient.getClient()
                    .update_profilenw(url1, "application/json", "Bearer $token")
                    ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(cnt_assoc_name) {
                     override   fun onResponse(
                            call: Call<UpdtproflPojo?>,
                            response: Response<UpdtproflPojo?>
                        ) {
                            try {
                                if (response.body() != null) {
                                    val profileItems: List<ProfileItem>? =
                                        response.body()?.profile
                                    if (profileItems != null) {
                                        for (i in profileItems.indices) {
                                            val profileItem: ProfileItem = profileItems[i]
                                            assox_name_str = profileItem.name
                                            assox_mob_str = profileItem.mobile
                                            assox_email_str = profileItem.email
                                            cnt_assoc_name?.text = assox_name_str
                                            cnt_assoc_email?.text = assox_email_str
                                            cnt_assoc_num?.text = assox_mob_str
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    })
                submit_dialog?.setOnClickListener(View.OnClickListener { view: View? ->
                    edit_query_str = edit_query?.text.toString()
                    edit_amnt_budget_str = edit_amnt_budget?.text.toString()
                    srvc_spnr_str = servc_spinr?.selectedItem.toString()
                    if (edit_amnt_budget_str == "" || edit_amnt_budget_str!!.isEmpty()) {
                        Toast.makeText(mContext, "Enter Budget", Toast.LENGTH_SHORT).show()
                    } else if (srvc_spnr_str!!.contains("Select Service")) {
                        Toast.makeText(mContext, "Select any Service", Toast.LENGTH_SHORT).show()
                    } else if (edit_query_str == "" || edit_query_str!!.isEmpty()) {
                        Toast.makeText(mContext, "Please Enter Something", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (edit_amnt_budget_str != null && edit_amnt_budget_str!!.length > 0) {
                        val percent = 5.0f
                        val answer = edit_amnt_budget_str!!.toFloat() * (percent / 100.0f)
                        ass_id = dataSet!![i].id
                        val subcatid_str: String? = dataSet!![i].subcategoryId
                        if (subcatid_str != null) {
                            conect_submit(
                                ass_id, answer.toString(), edit_amnt_budget_str,
                                srvc_spnr_str, edit_query_str, subcatid_str, i
                            )
                        }
                    }
                })
                cancel_dialog?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
                rankDialog!!.show()
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun conect_submit(
        ass_id: String?,
        answer: String,
        edit_amnt_budget_str: String?,
        srvc_spnr_str: String?,
        edit_query_str: String?,
        subcatid_str: String,
        position: Int
    ) {
        blur_reg1?.visibility = View.VISIBLE
        manager = SessionManager()
        token = mContext?.let { manager?.getPreferences(it, Constants.USER_TOKEN_LRN) }
        RetrofitClient.getClient().connect_asociat(
            ass_id, answer, edit_amnt_budget_str,
            srvc_spnr_str, edit_query_str, subcatid_str,
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(videoView1) {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                blur_reg1?.visibility = View.GONE
                try {
                    val takeResCnct  = response.body()?.toString()
                    if (takeResCnct != null) {
                        if (takeResCnct.contains("1")) {
                            val assdataename: String ?= dataSet!![position].name
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                                val channel = NotificationChannel(
//                                    Constants.CHANNEL_ID,
//                                    Constants.NOTICHANNEL_NAME,
//                                    NotificationManager.IMPORTANCE_DEFAULT
//                                )
//                                channel.description = Constants.CHANNEL_DESC
//                                val notificationManager: NotificationManager? =
//                                    mContext?.getSystemService(
//                                        NotificationManager::class.java
//                                    )
//                                notificationManager?.createNotificationChannel(channel)
//                            }
//                            val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(
//                                mContext?.applicationContext!!,
//                                Constants.CHANNEL_ID
//                            )
//                                .setSmallIcon(R.drawable.nllogo)
//                                .setAutoCancel(true)
//                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//                                .setContentTitle("NationLearns")
//                                .setContentText("You have successfully Connected to $assdataename")
//                                .setStyle(
//                                    NotificationCompat.BigTextStyle()
//                                        .bigText("You have successfully Connected to $assdataename")
//                                )
//                                .setContentIntent(
//                                    PendingIntent.getActivity(
//                                        mContext?.applicationContext,
//                                        0,
//                                        Intent(),
//                                        PendingIntent.FLAG_IMMUTABLE
//                                    )
//                                )
//                            val notificationManagerCompat: Unit =
//                                mContext?.applicationContext.let {
//                                    if (it != null) {
//                                        NotificationManagerCompat.from(
//                                            it
//                                        )
//                                    }
//                                }
//                            if (mContext?.let {
//                                    ActivityCompat.checkSelfPermission(it, Manifest.permission.POST_NOTIFICATIONS) } != PackageManager.PERMISSION_GRANTED) {
//
//                                return
//                            }
//                            notificationManagerCompat.notify(1, mBuilder.build())
                            val success_nsgg =
                                "Connected Successfully. Kindly check the associate details on My Business List"
                            AlertDialog.Builder(mContext!!)
                                .setMessage(success_nsgg)
                                .setCancelable(false)
                                .setPositiveButton("Yes"
                                ) { dialog, id ->
                                    //                                                String ass_idd_slc_str = dataSet.get(i).getUser_idd();
                                    //                                                accpet_slc(ass_idd_slc_str);
                                }
                                .setNegativeButton("No", null)
                                .show()
                            val intent = Intent(mContext, ConnectedBizList::class.java)
                            mContext!!.startActivity(intent)
                            rankDialog!!.dismiss()
                        } else if (takeResCnct.contains("2")) {
                            Toast.makeText(mContext, "Connection Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if (takeResCnct != null) {
                        if (takeResCnct.contains("3")) {
                            Toast.makeText(mContext, "Already Connected", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if (takeResCnct != null) {
                        if (takeResCnct.contains("4")) {
                            Toast.makeText(
                                mContext,
                                "Cannot Connect to own business!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return dataSet!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var assoname_sc: TextView
        var pincode_sc: TextView
        var city_sc: TextView
        var txtRatingValue_sc: TextView
        var asso_id_sc: TextView
        var ratingBar_sc: RatingBar
        var view_profile_sc: TextView
        var connectt_sc: TextView
        var connectted: TextView
        var circleImageview_sc: ImageView
        var verified_tickk: ImageView
        var verified_tickk_red: ImageView
        var ll_alrdy_cnctd: LinearLayout
        var blog_icon1: TriangleLabelView

        init {
            assoname_sc = itemView.findViewById<TextView>(R.id.assoname_sc)
            pincode_sc = itemView.findViewById<TextView>(R.id.pincode_sc)
            city_sc = itemView.findViewById<TextView>(R.id.city_sc)
            txtRatingValue_sc = itemView.findViewById<TextView>(R.id.txtRatingValue_sc)
            asso_id_sc = itemView.findViewById<TextView>(R.id.asso_id_sc)
            ratingBar_sc = itemView.findViewById<RatingBar>(R.id.ratingBar_sc)
            view_profile_sc = itemView.findViewById<TextView>(R.id.view_profile_sc)
            connectt_sc = itemView.findViewById<TextView>(R.id.connectt_sc)
            circleImageview_sc = itemView.findViewById<ImageView>(R.id.circleImageview_sc)
            connectted = itemView.findViewById<TextView>(R.id.connectted)
            ll_alrdy_cnctd = itemView.findViewById<LinearLayout>(R.id.ll_alrdy_cnctd)
            verified_tickk = itemView.findViewById<ImageView>(R.id.verified_tickk)
            blog_icon1 = itemView.findViewById(R.id.blog_icon1)
            verified_tickk_red = itemView.findViewById<ImageView>(R.id.verified_tickk_red)
        }
    }
}