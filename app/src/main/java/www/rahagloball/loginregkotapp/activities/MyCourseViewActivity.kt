package www.rahagloball.loginregkotapp.activities
//import static com.cashfree.pg.CFPaymentService.PARAM_APP_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_EMAIL;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_NAME;
//import static com.cashfree.pg.CFPaymentService.PARAM_CUSTOMER_PHONE;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_AMOUNT;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_CURRENCY;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_ID;
//import static com.cashfree.pg.CFPaymentService.PARAM_ORDER_NOTE;

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.adapters.CourseVidlistAdapter
import www.rahagloball.loginregkotapp.adapters.IncludesAdapter
import www.rahagloball.loginregkotapp.adapters.MyCrsSctnTitleAdapter
import www.rahagloball.loginregkotapp.adapters.WhatLrnAdapter
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLst
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLstPojo
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.crseinfonew.CrsInfoNewPojo
import www.rahagloball.loginregkotapp.models.crseinfonew.DatumCrsInfo
import www.rahagloball.loginregkotapp.models.crseinfonew.IncludeItemCi
import www.rahagloball.loginregkotapp.models.crseinfonew.WhatlearnsCi
import www.rahagloball.loginregkotapp.models.mysinglecrs.MySingleCourse
import www.rahagloball.loginregkotapp.models.mysinglecrs.MySingleCoursePojo
import www.rahagloball.loginregkotapp.models.mysinglecrs.MyoneCourses
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitle
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitlePojo

class MyCourseViewActivity : AppCompatActivity() {
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
    var partialpayment: EditText? = null
    var submit_dialog_cf: TextView? = null
    var cancel_dialog_cf: TextView? = null
    var catggryList: List<SectionTitle>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_course_viewzzss)
        manager = SessionManager()
        token = manager?.getPreferences(this@MyCourseViewActivity, Constants.USER_TOKEN_LRN)

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
        catggryList = ArrayList<SectionTitle>()

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
        //        course_pprice1 = findViewById(R.id.course_pprice1);
//        discnt_course_pprice1 = findViewById(R.id.discnt_course_pprice1);
        course_pricee = findViewById<TextView>(R.id.course_pricee)

        //Circleimage
        instructor_img = findViewById<CircleImageView>(R.id.instructor_img)

        //Ratingbar
        instrc_ratingBar = findViewById<RatingBar>(R.id.instrc_ratingBar)
        try {
            bundle = intent.extras
            if (bundle != null) {
                Qid = bundle!!.getString("Qid")
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
        getAssoViewProfile(Qid)
        buy_this_course?.visibility = View.GONE
        getsectiontitlee(Qid)
    }

    private fun getsectiontitlee(crs_idd: String?) {

//        shimmerFrameLayout?.startShimmer();
        val url: String = Configs.BASE_URL2 + "course-sections/" + crs_idd
        RetrofitClient.getClient().learnImgstitle(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<SectionTitlePojo?>(rv_videoListt) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<SectionTitlePojo?>?,
                    response: Response<SectionTitlePojo?>
                ) {
//                shimmerFrameLayout?.stopShimmer();
                    try {
                        if (response.body() != null) {
                            val res_mv: String? = response.body()?.status
                            if (res_mv == "200") {
                                catggryList = response.body()?.data
                                if (catggryList!!.isEmpty()) {
//                                nodata?.visibility = View.VISIBLE;
//                                shimmerFrameLayout?.visibility = View.GONE;
                                    rv_videoListt!!.visibility = View.GONE
                                } else {
//                                shimmerFrameLayout?.stopShimmer();
//                                shimmerFrameLayout?.visibility = View.GONE;
                                    rv_videoListt!!.visibility = View.VISIBLE
                                    adapter_imgs = MyCrsSctnTitleAdapter(
                                        catggryList!!,
                                        this@MyCourseViewActivity
                                    )
                                    rv_videoListt!!.adapter = adapter_imgs
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
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
            ?.enqueue(object : GlobalCallback<CrseeVidLstPojo?>(course_pricee) {
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
                                    CourseVidlistAdapter(dataItemList, this@MyCourseViewActivity)
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
            ?.enqueue(object : GlobalCallback<CrsInfoNewPojo?>(course_pricee) {
                override fun onResponse(
                    call: Call<CrsInfoNewPojo?>?,
                    response: Response<CrsInfoNewPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val dataItemList: List<DatumCrsInfo>? = response.body()?.data
                            if (dataItemList != null) {
                                for (i in dataItemList.indices) {
                                    val catggryList: List<WhatlearnsCi>? =
                                        dataItemList[i].whatlearns
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {

                                            //                                emptyElement.visibility = View.VISIBLE;
                                        } else {
                                            //                                emptyElement.visibility = View.GONE;
                                            adapter_lrns =
                                                WhatLrnAdapter(
                                                    catggryList,
                                                    this@MyCourseViewActivity
                                                )
                                            rv_what_lrns!!.adapter = adapter_lrns
                                        }
                                    }
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
            ?.enqueue(object : GlobalCallback<CrsInfoNewPojo?>(course_pricee) {
                override fun onResponse(
                    call: Call<CrsInfoNewPojo?>?,
                    response: Response<CrsInfoNewPojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val dataItemList: List<DatumCrsInfo>? = response.body()?.data
                            if (dataItemList != null) {
                                for (i in dataItemList.indices) {
                                    val catggryList: List<IncludeItemCi>? = dataItemList[i].include
                                    if (catggryList != null) {
                                        if (catggryList.isEmpty()) {
                                            //                                emptyElement.visibility = View.VISIBLE;
                                        } else {
                                            //                                emptyElement.visibility = View.GONE;
                                            adapter =
                                                IncludesAdapter(
                                                    catggryList,
                                                    this@MyCourseViewActivity
                                                )
                                            rv_includess!!.adapter = adapter
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun getAssoViewProfile(Qid: String?) {
        blur_reg1?.visibility = View.VISIBLE
        //        Qid = bundle.getString("Qid");
        val url: String = Configs.BASE_URL2 + "my-courses/" + Qid
        RetrofitClient.getClient().getmycrsid(
            url, "application/json",
            "Bearer $token"
        )
            ?.enqueue(object : GlobalCallback<MySingleCoursePojo?>(course_pricee) {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<MySingleCoursePojo?>?,
                    response: Response<MySingleCoursePojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        if (response.body() != null) {
                            val dataItemList: MySingleCourse? = response.body()?.data
                            val myoneCourses: MyoneCourses? = dataItemList?.courses
                            val course_title: String? = myoneCourses?.title
                            videoTitle?.text = course_title
                            val course_desc: String? = myoneCourses?.shortDetail
                            videodescc?.text = course_desc
                            course_IdStr = dataItemList?.id
                            val profile_pic: String =
                                Configs.BASE_URL21 + "images/course/" + myoneCourses?.previewImage
                            videoThumbnail?.let {
                                Glide.with(this@MyCourseViewActivity)
                                    .load(profile_pic)
                                    .into(it)
                            }
                            val reuirementsss_str: String? = myoneCourses?.requirement
                            reuirementsss?.text = reuirementsss_str
                            val details_str: String? = myoneCourses?.detail
                            topic_descrptn?.text = Html.fromHtml(details_str)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
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
        private var adapter_imgs: RecyclerView.Adapter<*>? = null
    }
}