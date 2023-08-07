package www.rahagloball.loginregkotapp.activities
//import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import org.apache.commons.lang3.RandomStringUtils
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CourseVidlistAdapter
import www.rahagloball.loginregkotapp.adapters.IncludesAdapter
import www.rahagloball.loginregkotapp.adapters.WhatLrnAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLst
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLstPojo
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.cffwalt.CfDataWalt
import www.rahagloball.loginregkotapp.models.cffwalt.WalBalncePojo
import www.rahagloball.loginregkotapp.models.crseinfonew.CrsInfoNewPojo
import www.rahagloball.loginregkotapp.models.crseinfonew.DatumCrsInfo
import www.rahagloball.loginregkotapp.models.crseinfonew.IncludeItemCi
import www.rahagloball.loginregkotapp.models.crseinfonew.WhatlearnsCi
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo

class CourseViewActivity : AppCompatActivity() {
    var rv_videoListt: RecyclerView? = null
    var rv_ratingss: RecyclerView? = null
    var rv_students_bot: RecyclerView? = null
    var rv_includess: RecyclerView? = null
    var rv_what_lrns: RecyclerView? = null
    var add_toCart_txt: TextView? = null
    var sectionss: TextView? = null
    var lecturess: TextView? = null
    var total_timee: TextView? = null
    var topic_descrptn: TextView? = null
    var reuirementsss: TextView? = null
    var more_sectionss: TextView? = null
    var c_all_corses: TextView? = null
    var add_toFav_txt: TextView? = null
    var instrctr_name: TextView? = null
    var instrctr_job: TextView? = null
    var instruc_ratinggs: TextView? = null
    var instrc_reivewss: TextView? = null
    var instr_courses: TextView? = null
    var instrc_studnts: TextView? = null
    var instrctr_descptn: TextView? = null
    var inscrt_vw_profl: TextView? = null
    var course_ratingss: TextView? = null
    var see_all_rvws: TextView? = null
    var instrctr_name1: TextView? = null
    var last_updatedd: TextView? = null
    var languageee: TextView? = null
    var subtitlesss: TextView? = null
    var buy_this_course: TextView? = null
    var rating_cnt: TextView? = null
    var studentss_cnt: TextView? = null
    var instrc_rating_value: TextView? = null
    var course_pprice1: TextView? = null
    var discnt_course_pprice1: TextView? = null
    var buy_this_course1: TextView? = null
    var asso_id: TextView? = null
    var videoTitle: TextView? = null
    var videodescc: TextView? = null
    var course_pricee: TextView? = null
    var what_lrn_txt: TextView? = null
    var totlTime_crse_inclds: TextView? = null
    var instructor_img: CircleImageView? = null
    var instrc_ratingBar: RatingBar? = null
    var ll_buy_this: LinearLayout? = null
    var Qid: String? = null
    var Qslug: String? = null
    var blur_reg1: RelativeLayout? = null
    var videoThumbnail: ImageView? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var layoutManager_lrns: RecyclerView.LayoutManager? = null
    private var llm_crse_vid_list: RecyclerView.LayoutManager? = null
    var course_pricee1_str: String? = null
    var course_IdStr: String? = null
    var discnt_course_pricee1_str: String? = null
    var nav_user_name_str: String? = null
    var phone_str: String? = null
    var nav_email_str: String? = null
    var manager: SessionManager? = null
    var token: String? = null
    var cbHeart: CheckBox? = null
    var bundle: Bundle? = null
    var checked = false
    var wish_res: String? = null
    var prices_str: String? = null
    var dscnt_prices_str: String? = null
    var rankDialog: Dialog? = null
    var customamount: String? = null
    var walt_str: String? = null
    var partialpayment: EditText? = null
    var submit_dialog_cf: TextView? = null
    var cancel_dialog_cf: TextView? = null
    var view_tranx: TextView? = null
    var cf_walt_sts: String? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_viewzzss)
        manager = SessionManager()
        token = manager?.getPreferences(this@CourseViewActivity, Constants.USER_TOKEN_LRN)

        //Recyclerview
        rv_videoListt = findViewById<RecyclerView>(R.id.rv_videoListt)
        rv_ratingss = findViewById<RecyclerView>(R.id.rv_ratingss)
        rv_students_bot = findViewById<RecyclerView>(R.id.rv_students_bot)
        rv_includess = findViewById<RecyclerView>(R.id.rv_includess)
        rv_what_lrns = findViewById<RecyclerView>(R.id.rv_what_lrns)
        totlTime_crse_inclds = findViewById<TextView>(R.id.totlTime_crse_inclds)
        cbHeart = findViewById<CheckBox>(R.id.cbHeart)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        asso_id = findViewById<TextView>(R.id.idd)
        videoTitle = findViewById<TextView>(R.id.videoTitle)
        videodescc = findViewById<TextView>(R.id.videodescc)
        videoThumbnail = findViewById<ImageView>(R.id.videoThumbnail)
        //        what_lrn_txt = findViewById(R.id.what_lrn_txt);

        //Textview
        add_toCart_txt = findViewById<TextView>(R.id.add_toCart_txt)
        sectionss = findViewById<TextView>(R.id.sectionss)
        lecturess = findViewById<TextView>(R.id.lecturess)
        total_timee = findViewById<TextView>(R.id.total_timee)
        topic_descrptn = findViewById<TextView>(R.id.topic_descrptn)
        reuirementsss = findViewById<TextView>(R.id.reuirementsss)
        more_sectionss = findViewById<TextView>(R.id.more_sectionss)
        c_all_corses = findViewById<TextView>(R.id.c_all_corses)
        add_toFav_txt = findViewById<TextView>(R.id.add_toFav_txt)
        instrctr_name = findViewById<TextView>(R.id.instrctr_name)
        instrctr_job = findViewById<TextView>(R.id.instrctr_job)
        instruc_ratinggs = findViewById<TextView>(R.id.instruc_ratinggs)
        instrc_reivewss = findViewById<TextView>(R.id.instrc_reivewss)
        instr_courses = findViewById<TextView>(R.id.instr_courses)
        instrc_studnts = findViewById<TextView>(R.id.instrc_studnts)
        instrctr_descptn = findViewById<TextView>(R.id.instrctr_descptn)
        inscrt_vw_profl = findViewById<TextView>(R.id.inscrt_vw_profl)
        course_ratingss = findViewById<TextView>(R.id.course_ratingss)
        see_all_rvws = findViewById<TextView>(R.id.see_all_rvws)
        instrctr_name1 = findViewById<TextView>(R.id.instrctr_name1)
        last_updatedd = findViewById<TextView>(R.id.last_updatedd)
        languageee = findViewById<TextView>(R.id.languageee)
        subtitlesss = findViewById<TextView>(R.id.subtitlesss)
        buy_this_course = findViewById<TextView>(R.id.buy_this_course)
        rating_cnt = findViewById<TextView>(R.id.rating_cnt)
        studentss_cnt = findViewById<TextView>(R.id.studentss_cnt)
        instrc_rating_value = findViewById<TextView>(R.id.instrc_rating_value)
        course_pprice1 = findViewById<TextView>(R.id.course_pprice1)
        discnt_course_pprice1 = findViewById<TextView>(R.id.discnt_course_pprice1)
        course_pricee = findViewById<TextView>(R.id.course_pricee)
        buy_this_course1 = findViewById<TextView>(R.id.buy_this_course1)

        //Circleimage
        instructor_img = findViewById<CircleImageView>(R.id.instructor_img)

        //Ratingbar
        instrc_ratingBar = findViewById<RatingBar>(R.id.instrc_ratingBar)
        ll_buy_this = findViewById<LinearLayout>(R.id.ll_buy_this)
        try {
            bundle = intent.extras
            if (bundle != null) {
                Qid = bundle!!.getString("Qid")
                Qslug = bundle!!.getString("QSlug")
                prices_str = bundle!!.getString("prices")
                dscnt_prices_str = bundle!!.getString("discnt_price")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //includes
//        rv_includess.setHasFixedSize(true);
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_includess!!.layoutManager = layoutManager
        rv_includess!!.itemAnimator = DefaultItemAnimator()


        //what learns
//        rv_what_lrns.setHasFixedSize(true);
        layoutManager_lrns = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_what_lrns!!.layoutManager = layoutManager_lrns
        rv_what_lrns!!.itemAnimator = DefaultItemAnimator()

        //course video list
//        rv_videoListt.setHasFixedSize(true);
        llm_crse_vid_list = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_videoListt!!.layoutManager = llm_crse_vid_list
        rv_videoListt!!.itemAnimator = DefaultItemAnimator()
        getincludes()
        getwhatlearn()
        assoViewProfile
        buy_this_course?.visibility = View.GONE

//        buy_this_course.setOnClickListener(v -> {
//            Toast.makeText(CourseViewActivity.this, "Coming Soon !!!", Toast.LENGTH_SHORT).show();
//        });
        cfWaltbal
        buy_this_course1?.setOnClickListener(View.OnClickListener { v: View? ->
            try {
                prices_str = bundle?.getString("prices")
                dscnt_prices_str = bundle?.getString("discnt_price")
                val builder = AlertDialog.Builder(this@CourseViewActivity)
                if (dscnt_prices_str == null || dscnt_prices_str == "0") {
                    builder.setIcon(R.drawable.buy_lead_new)
                        .setTitle("Enroll now")
                        .setMessage("Are you sure you want to enroll now?")
                        .setCancelable(false)
                        .setPositiveButton(
                            "Enroll",
                            DialogInterface.OnClickListener { dialog: DialogInterface?, id: Int ->
                                getcourseenroll(Qid)
                            })
                        .setNegativeButton(
                            "Cancel",
                            DialogInterface.OnClickListener { dialog: DialogInterface, id: Int -> dialog.cancel() })
                    val alert = builder.create()
                    alert.show()
                } else {
                    if (cf_walt_sts == "0") {
                        walletcheck()
                    } else {
                        builder.setIcon(R.drawable.buy_lead_new)
                            .setTitle("Buy Course")
                            .setMessage("Are you sure you want to Buy now?")
                            .setCancelable(false)
                            .setPositiveButton(
                                "Buy",
                                DialogInterface.OnClickListener { dialog: DialogInterface?, id: Int ->
                                    if (walt_str != null) {
                                        if (walt_str!!.toFloat() < dscnt_prices_str!!.toFloat()) {
                                            Toast.makeText(
                                                this@CourseViewActivity,
                                                "No wallet Balance Kindly recharge",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            walletcheck()
                                        } else {
                                            getcoursebuy(Qid)
                                        }
                                    }
                                })
                            .setNegativeButton(
                                "Cancel",
                                DialogInterface.OnClickListener { dialog: DialogInterface, id: Int -> dialog.cancel() })
                        val alert = builder.create()
                        alert.show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        get_crse_vidlist()
        profileimage()
    }

    //                                walt1_submit1.setText(walt_str);
    private val cfWaltbal: Unit
        get() {
            val url: String = Configs.BASE_URL2 + "get-balance"
            RetrofitClient.getClient().getwaltinfoo(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<WalBalncePojo?>(sectionss) {
                    override fun onResponse(
                        call: Call<WalBalncePojo?>,
                        response: Response<WalBalncePojo?>
                    ) {
                        try {
                            if (response.body() != null) {
                                cf_walt_sts = response.body()?.staus
                                if (cf_walt_sts == "0") {
                                    Toast.makeText(
                                        this@CourseViewActivity,
                                        "No data",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (cf_walt_sts == "1") {
                                    val cfDataWalt: CfDataWalt? = response.body()?.data
                                    walt_str = cfDataWalt?.balance
                                    Log.e("walt_str", walt_str!!)
                                    //                                walt1_submit1.setText(walt_str);
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    private fun getcourseenroll(crse_idd: String?) {
        blur_reg1?.visibility = View.VISIBLE
        RetrofitClient.getClient().getcrs_enroll(
            crse_idd,
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(buy_this_course) {
            override fun onResponse(call: Call<String?>?, response: Response<String?>) {
                blur_reg1?.visibility = View.GONE
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CourseViewActivity,
                        "Added to My courses!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        })
    }

    private fun getcoursebuy(crse_idd: String?) {
        blur_reg1?.visibility = View.VISIBLE
        RetrofitClient.getClient().getcrs_buy(
            crse_idd,
            "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(buy_this_course) {
            override fun onResponse(call: Call<String?>?, response: Response<String?>) {
                blur_reg1?.visibility = View.GONE
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CourseViewActivity,
                        "Course is Successfully Bought!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        })
    }

    private fun get_crse_vidlist() {
        blur_reg1?.visibility = View.VISIBLE
        //        course/create/1
//        String url = Configs.BASE_URL2 + "course-video-list/" + Qid;
        val url: String = Configs.BASE_URL2 + "course/" + Qslug
        RetrofitClient.getClient().crse_vid_list(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CrseeVidLstPojo?>(buy_this_course1) {
                override fun onResponse(
                    call: Call<CrseeVidLstPojo?>,
                    response: Response<CrseeVidLstPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val dataItemList: List<CrseeVidLst>? = response.body()?.data
                        if (dataItemList != null) {
                            if (dataItemList.isEmpty()) {
                    //                                emptyElement.visibility = View.VISIBLE;
                            } else {
                    //                                emptyElement.visibility = View.GONE;
                                adapter_crse_vid_list =
                                    dataItemList.let { CourseVidlistAdapter(it, this@CourseViewActivity) }
                                rv_videoListt!!.adapter = adapter_crse_vid_list
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getwhatlearn() {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "course-info/" + Qid
        RetrofitClient.getClient().course_viewget1(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CrsInfoNewPojo?>(buy_this_course1) {
                override fun onResponse(
                    call: Call<CrsInfoNewPojo?>,
                    response: Response<CrsInfoNewPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val dataItemList: List<DatumCrsInfo>? = response.body()?.data
                        if (!dataItemList.isNullOrEmpty()) {
                            val catggryList: List<WhatlearnsCi>? = dataItemList[0].whatlearns
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {

                                    //                                emptyElement.visibility = View.VISIBLE;
                                } else {
                                    //                                emptyElement.visibility = View.GONE;
                                    adapter_lrns =
                                        WhatLrnAdapter(catggryList, this@CourseViewActivity)
                                    rv_what_lrns!!.adapter = adapter_lrns
                                }
                            }
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getincludes() {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "course-info/" + Qid
        RetrofitClient.getClient().course_viewget1(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<CrsInfoNewPojo?>(buy_this_course1) {
                override fun onResponse(
                    call: Call<CrsInfoNewPojo?>,
                    response: Response<CrsInfoNewPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {

                        val dataItemList: List<DatumCrsInfo>? = response.body()?.data

                        if (!dataItemList.isNullOrEmpty()) {
                            val catggryList: List<IncludeItemCi>? = dataItemList[0].include
                            if (catggryList != null) {
                                if (catggryList.isEmpty()) {
                                    // emptyElement.visibility = View.VISIBLE;
                                } else {
                                    // emptyElement.visibility = View.GONE;
                                    adapter = IncludesAdapter(catggryList, this@CourseViewActivity)
                                    rv_includess!!.adapter = adapter
                                }
                            }
                        }

//                        val dataItemList: List<DatumCrsInfo>? = response.body()?.data
//
//                        val catggryList: List<IncludeItemCi>? = dataItemList[0].include
//                        if (catggryList != null) {
//                            if (catggryList.isEmpty()) {
//
//                    //                                emptyElement.visibility = View.VISIBLE;
//                            } else {
//                    //                                emptyElement.visibility = View.GONE;
//                                adapter = IncludesAdapter(catggryList, this@CourseViewActivity)
//                                rv_includess!!.adapter = adapter
//                            }
//                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private val assoViewProfile: Unit
        private get() {
            blur_reg1?.visibility = View.VISIBLE
            val url: String = Configs.BASE_URL2 + "course-info/" + Qid
            RetrofitClient.getClient().course_viewget1(
                url, "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<CrsInfoNewPojo?>(buy_this_course1) {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<CrsInfoNewPojo?>,
                        response: Response<CrsInfoNewPojo?>
                    ) {
                        blur_reg1?.visibility = View.GONE
                        try {
                            val dataItemList: List<DatumCrsInfo>? = response.body()?.data
                            for (i in dataItemList?.indices!!) {
                                val course_title: String? = dataItemList[i].title
                                videoTitle?.setText(course_title)
                                val course_desc: String? = dataItemList[i].shortDetail
                                videodescc?.setText(course_desc)
                                course_IdStr = dataItemList[i].id
                                val profile_pic: String =
                                    Configs.BASE_URL21 + "images/course/" + dataItemList[i].previewImage
                                videoThumbnail?.let {
                                    Glide.with(this@CourseViewActivity)
                                        .load(profile_pic)
                                        .into(it)
                                }
                                val video_intro: String? = dataItemList[i].url
                                val course_pricee_str: String? = dataItemList[i].price
                                if (course_pricee_str == "0") {
                                    course_pricee?.text = "Free"
                                } else {
                                    course_pprice1?.paintFlags = (course_pprice1?.paintFlags ?: 0) or Paint.STRIKE_THRU_TEXT_FLAG
                                    course_pricee?.text = "Rs. $course_pricee_str"
                                }
                                course_pricee1_str = dataItemList[i].price
                                course_pprice1?.paintFlags = (course_pprice1?.paintFlags
                                    ?.or(Paint.STRIKE_THRU_TEXT_FLAG) ?: "") as Int
                                course_pprice1?.setText("Rs. $course_pricee1_str")
                                if (course_pricee1_str == null || course_pricee1_str == "0") {
                                    course_pprice1?.setText("Free")
                                    buy_this_course1?.setText("Enroll Now")
                                } else {
                                    course_pprice1?.setPaintFlags(course_pprice1!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
                                    course_pprice1?.setText("Rs. $course_pricee1_str")
                                }
                                discnt_course_pricee1_str = dataItemList[i].discountPrice
                                if (discnt_course_pricee1_str == null || discnt_course_pricee1_str == "0") {
                                    discnt_course_pprice1?.setText("Free")
                                    buy_this_course1?.text = "Enroll Now"
                                } else {
                                    discnt_course_pprice1?.setText("Rs. $discnt_course_pricee1_str")
                                }
                                val course_drtn: String? = dataItemList[i].duration
                                if (course_drtn == null) {
                                    last_updatedd?.setText(" total hours of on-demand video")
                                } else {
                                    last_updatedd?.setText("$course_drtn total hours of on-demand video")
                                }
                                val reuirementsss_str: String? = dataItemList[i].requirement
                                reuirementsss?.setText(reuirementsss_str)
                                val details_str: String? = dataItemList[i].detail
                                topic_descrptn?.setText(Html.fromHtml(details_str))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        }

    private fun profileimage() {
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(rv_videoListt) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile

//                        val profileItems: List<ProfileItem> ? = response.body()?.profile
//                        val profileItem: ProfileItem = profileItems[0]
                        for (i in profileItems?.indices!!) {
                            val profileItem: ProfileItem = profileItems[i]

                            phone_str = profileItem.mobile
                            nav_user_name_str = profileItem.name
                            nav_email_str = profileItem.email
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    @SuppressLint("ClickableViewAccessibility")
    fun walletcheck() {
        Toast.makeText(
            this@CourseViewActivity,
            "No wallet Balance Kindly recharge",
            Toast.LENGTH_SHORT
        ).show()
        rankDialog = Dialog(this@CourseViewActivity, R.style.FullHeightDialog)
        rankDialog?.setContentView(R.layout.nl_walletnew)
        rankDialog!!.setCancelable(true)
        partialpayment = rankDialog?.findViewById<EditText>(R.id.partialpayment_cf)
        submit_dialog_cf = rankDialog?.findViewById<TextView>(R.id.submit_dialog_cf)
        cancel_dialog_cf = rankDialog?.findViewById<TextView>(R.id.cancel_dialog_cf)
        view_tranx = rankDialog?.findViewById<TextView>(R.id.view_tranx)
        view_tranx?.visibility = View.GONE
        partialpayment?.setOnTouchListener(View.OnTouchListener { view: View, event: MotionEvent ->
            // TODO Auto-generated method stub
            if (view.id == R.id.partialpayment_cf) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            false
        })
        submit_dialog_cf?.setOnClickListener(View.OnClickListener { view: View? ->
            customamount = partialpayment?.text.toString()
            try {
                customamount = partialpayment?.text.toString()
                val num = customamount!!.toInt()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (customamount!!.isEmpty() || num < 1) {
                        Toast.makeText(
                            this@CourseViewActivity,
                            "Kindly Enter a valid Amount!  ",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        gettkncfpay(customamount)
                        rankDialog!!.dismiss()
                    }
                }
            } catch (ex: NumberFormatException) { // handle your exception
            }
        })
        cancel_dialog_cf?.setOnClickListener(View.OnClickListener { vv: View? -> rankDialog!!.dismiss() })
        rankDialog!!.show()
    }

    fun gettkncfpay(cfamountpay: String?) {
        blur_reg1?.visibility = View.VISIBLE
        val orderidd: String = RandomStringUtils.randomAlphanumeric(10)
        //        Log.e("orderidd",orderidd);

//        RetrofitClient.getClient().gettokencf(orderidd, cfamountpay, "INR",
//                        "application/json", "Bearer " + token).
//                enqueue(new GlobalCallback<CashFripojo>(buy_this_course1) {
//                    @Override
//                    public void onResponse(Call<CashFripojo> call, Response<CashFripojo> response) {
//                        blur_reg1.visibility = View.GONE;
//
//                        try {
//                            if (response.isSuccessful()) {
//                                if (response.body().getMessage().equals("Token generated")) {
//                                    String token1 ? = response.body()?.getCftoken();
//                                    Map<String, String> params = new HashMap<>();
//                                    params.put(PARAM_APP_ID, "287591d80ab19b3f8bae5cb1ab195782");
//                                    params.put(PARAM_ORDER_ID, orderidd);
//                                    params.put(PARAM_ORDER_AMOUNT, cfamountpay);
//                                    params.put(PARAM_ORDER_NOTE, "NL Payment done");
//                                    if (nav_user_name_str != null)
//                                        params.put(PARAM_CUSTOMER_NAME, nav_user_name_str);
//                                    if (phone_str != null)
//                                        params.put(PARAM_CUSTOMER_PHONE, phone_str);
//                                    if (nav_email_str != null)
//                                        params.put(PARAM_CUSTOMER_EMAIL, nav_email_str);
//
//                                    params.put(PARAM_ORDER_CURRENCY, "INR");
//
////                                    for (Map.Entry entry : params.entrySet()) {
////                                        Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
////                                    }
//
//                                    CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
//                                    cfPaymentService.setOrientation(0);
//                                    cfPaymentService.doPayment(CourseViewActivity.this, params, token1, "PROD", "#174778", "#FFFFFF", false);
////                                    Intent intentpay = new Intent(PlayerHome.this, PlayerHome.class);
////                                    startActivity(intentpay);
//
//                                } else {
//                                    Toast.makeText(CourseViewActivity.this, "msg2" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(CourseViewActivity.this, "msg3" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                });
    }

    protected override fun onResume() {
        super.onResume()
        //        invalidateOptionsMenu();
    }

    companion object {
        var notificationCountCart = 0
        private var adapter: RecyclerView.Adapter<*>? = null
        private var adapter_lrns: RecyclerView.Adapter<*>? = null
        private var adapter_crse_vid_list: RecyclerView.Adapter<*>? = null
    }
}