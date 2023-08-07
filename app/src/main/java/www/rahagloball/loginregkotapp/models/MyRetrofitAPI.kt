package www.rahagloball.loginregkotapp.models

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Url
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.models.CrseVidlist.CrseeVidLstPojo
import www.rahagloball.loginregkotapp.models.Learnn.LCatPojo
import www.rahagloball.loginregkotapp.models.LrnSrchVid.LearnSearchPojo
import www.rahagloball.loginregkotapp.models.addcmntres.CommentsAddpojo
import www.rahagloball.loginregkotapp.models.allannce.AllAnnouncmntPojo
import www.rahagloball.loginregkotapp.models.allconect.AllCnctFltrPojo
import www.rahagloball.loginregkotapp.models.allques.InstrctrQuesPojo
import www.rahagloball.loginregkotapp.models.alltranxx.AllTranxPojo
import www.rahagloball.loginregkotapp.models.bizch.BizChanPojo
import www.rahagloball.loginregkotapp.models.bizcnctprofile.BizCnctProfilePojo
import www.rahagloball.loginregkotapp.models.bizovrlall.BizOvrallAnlytcsPojo
import www.rahagloball.loginregkotapp.models.bzchexits.BizChExistsPojo
import www.rahagloball.loginregkotapp.models.cashfrii.CashFripojo
import www.rahagloball.loginregkotapp.models.cashfrii.PaymnetSessionPojo
import www.rahagloball.loginregkotapp.models.catgryy.CatgtyPojo
import www.rahagloball.loginregkotapp.models.cffwalt.WalBalncePojo
import www.rahagloball.loginregkotapp.models.chctsfront.CtsSingleChPojo
import www.rahagloball.loginregkotapp.models.conectedbiz.ConctedListPojo
import www.rahagloball.loginregkotapp.models.crscatgry.CrsCatPojo
import www.rahagloball.loginregkotapp.models.crse_srch.CourseSearchPojo
import www.rahagloball.loginregkotapp.models.crseinfonew.CrsInfoNewPojo
import www.rahagloball.loginregkotapp.models.crsscnvidlist.CrsSctnVidllistPojo
import www.rahagloball.loginregkotapp.models.crssubvatt.CrsSubCatPojo
import www.rahagloball.loginregkotapp.models.crtrreanking.CrtrRnkingPojo
import www.rahagloball.loginregkotapp.models.ctgry.CategoryNl
import www.rahagloball.loginregkotapp.models.ctgry.SubCategoryNl
import www.rahagloball.loginregkotapp.models.cutiesss.CutiesPojo
import www.rahagloball.loginregkotapp.models.cutyvids.CuteVidListPojo
import www.rahagloball.loginregkotapp.models.getbizchnl.GetBizChnlPojo
import www.rahagloball.loginregkotapp.models.getblogs.GetBlogsPojo
import www.rahagloball.loginregkotapp.models.getchanldata.GetChanlPojo
import www.rahagloball.loginregkotapp.models.getinstrctr.GetInstrctrPojo
import www.rahagloball.loginregkotapp.models.getsingleblg.GetSingleBlogPojo
import www.rahagloball.loginregkotapp.models.issprtcheck.SspCheckPojo
import www.rahagloball.loginregkotapp.models.likedislik.LikeDislikPojo
import www.rahagloball.loginregkotapp.models.loginmodel.Register.RregisterPojo
import www.rahagloball.loginregkotapp.models.loginmodel.lhome.LHomePojo
import www.rahagloball.loginregkotapp.models.lrndtls_new.CourseDtlsPojo
import www.rahagloball.loginregkotapp.models.ltstvids.LtstVidPojo
import www.rahagloball.loginregkotapp.models.milestone.MilestonePojo
import www.rahagloball.loginregkotapp.models.milestonebz.MilestoneBzPojo
import www.rahagloball.loginregkotapp.models.mychanldtls.MyChnlDtlsPojo
import www.rahagloball.loginregkotapp.models.mychvids.MyChVidpojo
import www.rahagloball.loginregkotapp.models.myciursesalll.MyCoursePojo
import www.rahagloball.loginregkotapp.models.mymngbid.MyManageVidPojo
import www.rahagloball.loginregkotapp.models.mymngvidsingle.MngVidSinglePojo
import www.rahagloball.loginregkotapp.models.mysavedvidss.SavedVidPojo
import www.rahagloball.loginregkotapp.models.mysinglecrs.MySingleCoursePojo
import www.rahagloball.loginregkotapp.models.mysprtsvids.MySprtVidPojo
import www.rahagloball.loginregkotapp.models.mysuprsprtsvids.MySprSprtVidsPojo
import www.rahagloball.loginregkotapp.models.newsindletest.OneVidListPojo
import www.rahagloball.loginregkotapp.models.ovralanlyts.OvralAnlytcsPojo
import www.rahagloball.loginregkotapp.models.relvidsubcat.RelatedVidPojo
import www.rahagloball.loginregkotapp.models.reviewpoj.ReviewPojo
import www.rahagloball.loginregkotapp.models.sectiontile.SectionTitlePojo
import www.rahagloball.loginregkotapp.models.singlecutiesch.SingleChPojo
import www.rahagloball.loginregkotapp.models.sprsprt.SupprtSprtPojo
import www.rahagloball.loginregkotapp.models.sprtt.SupporrtPojo
import www.rahagloball.loginregkotapp.models.statecitymodels.City
import www.rahagloball.loginregkotapp.models.statecitymodels.CountryKotlin
import www.rahagloball.loginregkotapp.models.statecitymodels.Pincode
import www.rahagloball.loginregkotapp.models.statecitymodels.State
import www.rahagloball.loginregkotapp.models.statecityss.StCtySprtSuprPojo
import www.rahagloball.loginregkotapp.models.subcatgry.SubCatgtyPojo
import www.rahagloball.loginregkotapp.models.suprtrlistt.SupportersListPojo
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo
import www.rahagloball.loginregkotapp.models.usercrs.UserCrsPojo
import www.rahagloball.loginregkotapp.models.watchistry.WtchHistryPojo
import www.rahagloball.loginregkotapp.models.watchwshlist.WtchWshlstPojo

interface MyRetrofitAPI {

    @POST("${Configs.BASE_URL2}otp")
    @FormUrlEncoded
    fun otploginn(
        @Field("mobile") mobile: String,
        @Field("via") via: String
    ): Call<String>

    @GET
    fun getReviews(
        @Url url: String,
        @Header("Accept") accept: String,
        @Header("Authorization") authToken: String
    ): Call<ReviewPojo>

    @POST(Configs.BASE_URL2 + "verify-otp")
    @FormUrlEncoded
    fun getverigyotp(
        @Field("otp") otpp: String?,
        @Field("mobile") mobile: String?,
        @Field("type") type: String?,
        @Field("via") viaa: String?,
        @Field("ftoken") ftoken: String?
    ): Call<LHomePojo?>?

    @POST(Configs.BASE_URL2 + "verify-otp")
    @FormUrlEncoded
    fun getverigyotpr(
        @Field("otp") otpp: String?,
        @Field("mobile") mobile: String?,
        @Field("type") type: String?,
        @Field("via") viaa: String?,
        @Field("ftoken") ftoken: String?
    ): Call<RregisterPojo?>?


    @POST(Configs.BASE_URL2 + "resend-otp")
    @FormUrlEncoded
    fun resendotpp(@Field("mobile") num: String?): Call<String?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "login-profile")
    fun upload_files(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepart_img: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "interest")
    @FormUrlEncoded
    fun getlangaugee(
        @Field("language") language: String?,
        @Field("interest") intrst: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "video")
    fun upload_viddnew(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepart_img: MultipartBody.Part?,
        @Part filepartvid: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "report-video")
    @FormUrlEncoded
    fun vid_reportt(
        @Field("video_id") language: String?,
        @Field("report") reportt: String?,
        @Field("comment") commentt: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @POST(Configs.BASE_URL2 + "comment-reply")
    @FormUrlEncoded
    fun getcmntreply(
        @Field("body") fbbck: String?,
        @Field("video_id") lead_idd: String?,
        @Field("comment_id") ccategory_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @POST(Configs.BASE_URL2 + "check-reply ")
    @FormUrlEncoded
    fun getcmntreplycheck(
        @Field("comment_id") comment_idd: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "like-dislike")
    @FormUrlEncoded
    fun getlikislik(
        @Field("id") video_id: String?,
        @Field("choice") choice: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<LikeDislikPojo?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "storecourse")
    fun upload_course(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepart_img: MultipartBody.Part?,
        @Part filepartvid: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @Multipart
    @POST(Configs.BASE_URL2 + "storecoursevideo")
    fun upload_section_video(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepartvid: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "instructor/request")
    fun bcm_an_instrctr(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepart_img: MultipartBody.Part?,
        @Part filepartvid: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @GET
    fun update_profilenw(
        @Url url: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<UpdtproflPojo?>?

    @GET
    fun getovralanlytcs(
        @Url url: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<OvralAnlytcsPojo?>?

    @GET
    fun getbizovralanlytcs(
        @Url url: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<BizOvrallAnlytcsPojo?>?

    @GET
    fun getstctydemkss(
        @Url url: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<StCtySprtSuprPojo?>?


    @POST(Configs.BASE_URL2 + "supports/{id}")
    fun hitsuppportt(
        @Path("id") id: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SupporrtPojo?>?


    @POST(Configs.BASE_URL2 + "comments")
    @FormUrlEncoded
    fun add_comments(
        @Field("video_id") title: String?,
        @Field("body") description: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CommentsAddpojo?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "channel")
    fun tcreate_ch(
        @PartMap params: HashMap<String, RequestBody>?,
        @Part filepart_img: MultipartBody.Part?,
        @Part filepartvid: MultipartBody.Part?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @POST(Configs.BASE_URL2 + "sendreview")
    @FormUrlEncoded
    fun rvw_asocte_new(
        @Field("business_id") biz_idd: String?,
        @Field("review") rvw: String?,
        @Field("rating") rtngs: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "get-token")
    @FormUrlEncoded
    fun gettokencf(
        @Field("orderId") ordrIdd: String?,
        @Field("orderAmount") ordrAmnt: String?,
        @Field("orderCurrency") ordrCrncy: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CashFripojo?>?


    @POST(Configs.BASE_URL2 + "get-token-updated")
    @FormUrlEncoded
    fun gettokencf_new(
        @Field("order_id") order_iddd: String?,
        @Field("order_amount") order_amountt: Double,
        @Field("order_currency") order_currencyy: String?,
        @Field("customer_details") customer_detailss: String?,
        @Field("customer_id") customer_idr: String?,
        @Field("customer_name") customer_namee: String?,
        @Field("customer_email") customer_emaill: String?,
        @Field("customer_phone") customer_phonee: String?,
        @Field("environment") environmentt: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<PaymnetSessionPojo?>?


    @POST(Configs.BASE_URL2 + "transaction/nl")
    @FormUrlEncoded
    fun pay_success_cf(
        @Field("orderId") orderIddf: String?,
        @Field("orderAmount") orderAmountdd: String?,
        @Field("orderCurrency") orderCurrencyff: String?,
        @Field("transactionMode") transactionModedd: String?,
        @Field("status") statusff: String?,
        @Field("request_type") request_typegg: String?,
        @Field("transaction_id") transaction_iddd: String?,
        @Field("paymentMode") paymentModett: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @Multipart
    @POST(Configs.BASE_URL2 + "add-bizchannel")
    fun getbizchannel(
        @PartMap params: HashMap<String, RequestBody>?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<BizChanPojo?>?


    @POST(Configs.BASE_URL2 + "wallet-transaction")
    @FormUrlEncoded
    fun pay_success_wallet(
        @Field("request_type") request_typee: String?,
        @Field("channel_id") channel_id: String?,
        @Field("amount") amountt: String?,
        @Field("plan_type") plan_typee: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "add-views")
    @FormUrlEncoded
    fun add_viewss(
        @Field("video_id") video_idd: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "enroll-course")
    @FormUrlEncoded
    fun getcrs_enroll(
        @Field("course_id") course_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @POST(Configs.BASE_URL2 + "buy-course")
    @FormUrlEncoded
    fun getcrs_buy(
        @Field("course_id") course_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @GET
    fun getwaltinfoo(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<WalBalncePojo?>?


    @POST("connect-associate/")
    @FormUrlEncoded
    fun connect_associate1(
        @Field("assoc_id") assoc_id: String?,
        @Field("name") namee: String?,
        @Field("mobile") moble: String?,
        @Field("email") emiall: String?,
        @Field("search_query") serchqury: String?,
        @Field("looking_for") look_for: String?,
        @Field("agent_type") agent_type: String?,
        @Field("type") typpee: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @GET
    fun forum2(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<AllTranxPojo?>?

    @GET
    fun getSuprtrsLsit(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SupportersListPojo?>?


    @GET
    fun getqueslist(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<InstrctrQuesPojo?>?


    @GET
    fun getallance(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<AllAnnouncmntPojo?>?

    @GET
    fun getCrsCtgry(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrsCatPojo?>?

    @GET
    fun getCrsSubCtgry(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrsSubCatPojo?>?

    @GET
    fun getmilestn(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MilestonePojo?>?

    @GET
    fun getmilestnbz(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MilestoneBzPojo?>?

    @GET
    fun getMngVidLsit(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MyManageVidPojo?>?

    @GET
    fun getMngVidLsit1(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MngVidSinglePojo?>?


    @GET
    fun getSvdVidLsit(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SavedVidPojo?>?


    @GET
    fun getwwVidLsit(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<WtchWshlstPojo?>?


    @POST(Configs.BASE_URL2 + "savevideo")
    @FormUrlEncoded
    fun saveviddd(
        @Field("video_id") video_idddd: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @POST(Configs.BASE_URL2 + "ssprt-settings")
    @FormUrlEncoded
    fun postssprtstngs(
        @Field("monthly_price") monthly_price: String?,
        @Field("monthly_bnfts") monthly_bnfts: String?,
        @Field("sixmonthly_price") sixmonthly_price: String?,
        @Field("sixmonthly_bnfts") sixmonthly_bnfts: String?,
        @Field("yrlymonthly_price") yrlymonthly_price: String?,
        @Field("yrlymonthly_bnfts") yrlymonthly_bnfts: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?

    @GET
    fun catrgotyy_list(
        @Url url: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CatgtyPojo?>?

    //
    @GET
    fun subcatrgotyy_list(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SubCatgtyPojo?>?

    @GET
    fun filteallconect(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<AllCnctFltrPojo?>?


    @GET
    fun learnCats(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<LCatPojo?>?

    //
    @GET
    fun learnImgs1(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CourseDtlsPojo?>?

    @GET
    fun getallmycrs(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MyCoursePojo?>?

    @GET
    fun getsecvids(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<UserCrsPojo?>?

    @GET
    fun learnImgstitle(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SectionTitlePojo?>?

    @GET
    fun learnIBlgs(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetBlogsPojo?>?

    @GET
    fun learnsingleBlgs(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetSingleBlogPojo?>?


    @GET
    fun SrchVidCrse(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<LearnSearchPojo?>?

    @GET
    fun SrchCrse(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CourseSearchPojo?>?

    @GET
    fun course_viewget1(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrsInfoNewPojo?>?

    @GET
    fun getmycrsid(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MySingleCoursePojo?>?

    //
    @GET
    fun crse_vid_list(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrseeVidLstPojo?>?

    @GET
    fun crtrrnkingg(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrtrRnkingPojo?>?


    @GET
    fun allvidList(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<OneVidListPojo?>?

    @GET
    fun allvidList1(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<LtstVidPojo?>?

    @GET
    fun suprtvidList(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MySprtVidPojo?>?

    @GET
    fun sprsuprtvidList(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MySprSprtVidsPojo?>?

    @GET
    fun wtchhsitory(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<WtchHistryPojo?>?

    @GET
    fun rellvidList(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<RelatedVidPojo?>?

    @GET
    fun gtcutelist(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CutiesPojo?>?

    @GET
    fun gtctssinglelist(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SingleChPojo?>?

    @GET
    fun gtchvidlist(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MyChVidpojo?>?

    @GET
    fun gtcutevidlist(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CuteVidListPojo?>?

    @GET
    fun snglechcts22(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CtsSingleChPojo?>?

    @POST(Configs.BASE_URL2 + "connect")
    @FormUrlEncoded
    fun connect_asociat(
        @Field("id") assoc_id: String?,
        @Field("budget_comissn") bdgt_cmsn: String?,
        @Field("budget") bdgt: String?,
        @Field("service") servc: String?,
        @Field("requirement") requiremnt: String?,
        @Field("subcategory_id") subcatid: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "ssupport-package")
    @FormUrlEncoded
    fun ssp_pckg(
        @Field("channel_id") assoc_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SupprtSprtPojo?>?

    @POST(Configs.BASE_URL2 + "course-videos-bysection")
    @FormUrlEncoded
    fun getallcrsvids(
        @Field("course_id") crse_id: String?,
        @Field("section_id") section_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<CrsSctnVidllistPojo?>?

    @POST(Configs.BASE_URL2 + "check-issupersupport")
    @FormUrlEncoded
    fun ssp_pckg_check(
        @Field("channel_id") assoc_id: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<SspCheckPojo?>?


    @GET
    fun getcntbizz(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<ConctedListPojo?>?

    @GET
    fun biz_view_profile(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<BizCnctProfilePojo?>?

    @GET
    fun getchanInfo(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetChanlPojo?>?

    @GET
    fun getbizchnl(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetBizChnlPojo?>?

    @GET
    fun bizchnlexists(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<BizChExistsPojo?>?


    @GET
    fun instrcrchnlexists(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetInstrctrPojo?>?


    @GET
    fun getbizchanInfo(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<GetBizChnlPojo?>?

    @GET
    fun getmychdtls(
        @Url url: String?, @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<MyChnlDtlsPojo?>?


    @POST(Configs.BASE_URL2 + "createannouncement")
    @FormUrlEncoded
    fun storeannounce(
        @Field("course_id") course_id: String?,
        @Field("announsment") question: String?,
        @Field("status") status: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "storesection")
    @FormUrlEncoded
    fun add_sectiontitle(
        @Field("category_id") category_id: String?,
        @Field("title") title: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @POST(Configs.BASE_URL2 + "addquestion")
    @FormUrlEncoded
    fun storequest(
        @Field("instructor_id") instructor_id: String?,
        @Field("course_id") course_id: String?,
        @Field("question") question: String?,
        @Field("status") status: String?,
        @Header("Accept") accept: String?,
        @Header("Authorization") AuthToken: String?
    ): Call<String?>?


    @GET("countries")
    fun getCountry(): Call<CountryKotlin?>?

    @GET("states")
    fun getState(): Call<State?>?

    @GET("city/{id}")
    fun getCity(@Path("id") id: String?): Call<City?>?

    @GET("pincode/{id}")
    fun getPinCode(@Path("id") id: String?): Call<Pincode?>?

    @GET("category")
    fun getCtgryNl(): Call<CategoryNl?>?

    @GET("sub-category/{id}")
    fun getSubCtgryNl(@Path("id") id: String?): Call<SubCategoryNl?>?


}
