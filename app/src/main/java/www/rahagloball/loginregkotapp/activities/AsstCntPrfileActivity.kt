package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.bizcnctprofile.BizCnctProfilePojo
import www.rahagloball.loginregkotapp.models.bizcnctprofile.CityItemm
import www.rahagloball.loginregkotapp.models.bizcnctprofile.RatingsItem
import www.rahagloball.loginregkotapp.models.bizcnctprofile.ViewProfileItem

class AsstCntPrfileActivity : AppCompatActivity() {
    var name: TextView? = null
    var state: TextView? = null
    var asscote_name: TextView? = null
    var agentty: TextView? = null
    var quali: TextView? = null
    var spltst_in: TextView? = null
    var city: TextView? = null
    var txpincode: TextView? = null
    var txServiceProvided: TextView? = null
    var txExperience: TextView? = null
    var txLanguage: TextView? = null
    var txAboutme: TextView? = null
    var txtRatingValue: TextView? = null
    var views: TextView? = null
    var profile_visits: TextView? = null
    var asso_id: TextView? = null
    var videoView1: ImageView? = null
    var ratingBar: RatingBar? = null
    var circleImageView: CircleImageView? = null
    var assoc_id: String? = null
    var user_id_str: String? = null
    var token: String? = null
    var agenlooking: String? = null
    var manager: SessionManager? = null
    var blur_reg1: RelativeLayout? = null
    var write_rvw: Button? = null
    var read_rvw: Button? = null
    var request_services: Button? = null
    var submit_dialog: Button? = null
    var cancel_dialog: Button? = null
    var vid_ll: LinearLayout? = null
    var materialAlertDialogBuilder: MaterialAlertDialogBuilder? = null
    var customAlertDialogView: View? = null
    var edit_query: EditText? = null
    var edit_reviewss: EditText? = null
    var edit_rev: EditText? = null
    var edit_query_str: String? = null
    var edit_reviewss_str: String? = null
    var agentSpinCnt_str: String? = null
    var name_str: String? = null
    var semail: String? = null
    var snumber: String? = null
    var rate_value: Float? = null
    var rel_cnt: RelativeLayout? = null
    var rel_rvw: LinearLayout? = null
    var agent_spin_cnt: Spinner? = null
    var rankDialog: Dialog? = null

    var dataAdapter1: ArrayAdapter<String>? = null
    var dataAdapter2: ArrayAdapter<String>? = null
    var dataAdapter3: ArrayAdapter<String>? = null
    var dataAdapter4: ArrayAdapter<String>? = null
    var dataAdapter5: ArrayAdapter<String>? = null
    var bundle: Bundle? = null
    @SuppressLint("SetTextI18n")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subcat_view_proile)
        manager = SessionManager()
        token = manager!!.getPreferences(this@AsstCntPrfileActivity, Constants.USER_TOKEN_LRN)
        name = findViewById<TextView>(R.id.name)
        views = findViewById<TextView>(R.id.views)
        profile_visits = findViewById<TextView>(R.id.profile_visits)
        circleImageView = findViewById<CircleImageView>(R.id.circleImageView)
        state = findViewById<TextView>(R.id.state)
        city = findViewById<TextView>(R.id.city)
        txpincode = findViewById<TextView>(R.id.txpincode)
        txServiceProvided = findViewById<TextView>(R.id.txServiceProvided)
        txExperience = findViewById<TextView>(R.id.txExperience)
        txLanguage = findViewById<TextView>(R.id.txLanguage)
        txAboutme = findViewById<TextView>(R.id.txAboutme)
        txtRatingValue = findViewById<TextView>(R.id.txtRatingValue)
        ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        videoView1 = findViewById<ImageView>(R.id.videoView1)
        request_services = findViewById<Button>(R.id.request_services)
        blur_reg1 = findViewById<RelativeLayout>(R.id.blur_reg1)
        asso_id = findViewById<TextView>(R.id.asso_id)
        write_rvw = findViewById<Button>(R.id.write_rvw)
        read_rvw = findViewById<Button>(R.id.read_rvw)
        agentty = findViewById<TextView>(R.id.agentty)
        quali = findViewById<TextView>(R.id.quali)
        spltst_in = findViewById<TextView>(R.id.spltst_in)
        vid_ll = findViewById<LinearLayout>(R.id.vid_ll)
        bundle = intent.extras
        assoc_id = bundle?.getString("ass_id")
        user_id_str = bundle?.getString("user_iddd")
        getAssoViewProfile(assoc_id)
        write_rvw!!.setOnClickListener { view: View? ->
            rankDialog = Dialog(this@AsstCntPrfileActivity, R.style.FullHeightDialog)
            rankDialog?.setContentView(R.layout.dialog_review)
            rankDialog!!.setCancelable(true)
            val updateButton: TextView? = rankDialog?.findViewById<TextView>(R.id.submit_dialog)
            val cancel_dialog: TextView? = rankDialog?.findViewById<TextView>(R.id.cancel_dialog)
            edit_rev = rankDialog?.findViewById<EditText>(R.id.edit_reviewss)
            ratingBar = rankDialog?.findViewById<RatingBar>(R.id.dialog_ratingbar)
            val rank_dialog_text12: TextView? =
                rankDialog?.findViewById<TextView>(R.id.rank_dialog_text1)
            ratingBar?.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { ratingBar: RatingBar, rating: Float, fromUser: Boolean ->
                    rate_value = ratingBar.getRating()
                    rank_dialog_text12?.text = "Your Rating : $rate_value/5."
                }
            updateButton?.setOnClickListener(View.OnClickListener { v: View? ->
                edit_reviewss_str = edit_rev?.text.toString()
                rate_value = ratingBar?.rating
                if (rate_value.toString() == "0.0") {
                    Toast.makeText(this, "Give Rating", Toast.LENGTH_SHORT).show()
                } else if (edit_reviewss_str == "") {
                    Toast.makeText(this, "Enter Comment", Toast.LENGTH_SHORT).show()
                } else {
                    reviewCmt()
                }
            })
            cancel_dialog?.setOnClickListener { v: View? -> rankDialog!!.dismiss() }
            rankDialog!!.show()
        }
        read_rvw!!.setOnClickListener { v: View? ->
            val id_intnt = Intent(this@AsstCntPrfileActivity, ReviewActivity::class.java)
            //            assoc_id = asso_id?.text.toString();
            id_intnt.putExtra("ass_id", assoc_id)
            startActivity(id_intnt)
        }
    }

    private fun getAssoViewProfile(id: String?) {
        blur_reg1?.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "business/view/" + id
        RetrofitClient.getClient().biz_view_profile(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<BizCnctProfilePojo?>(name) {
                @SuppressLint("SetTextI18n")
             override   fun onResponse(
                    call: Call<BizCnctProfilePojo?>,
                    response: Response<BizCnctProfilePojo?>
                ) {
                    blur_reg1?.visibility = View.GONE
                    try {
                        val businessItemList: List<ViewProfileItem>? = response.body()?.viewProfile
                        val getSingleAssocProfile: ViewProfileItem = (businessItemList?.get(0) ?: "") as ViewProfileItem
                        val namee: String =
                            java.lang.String.valueOf(getSingleAssocProfile.name)
                        val cityItemm: CityItemm? = getSingleAssocProfile.city
                        val cityr: String = java.lang.String.valueOf(cityItemm?.name)
                        val pinccode: String =
                            java.lang.String.valueOf(getSingleAssocProfile.pincode)

//                            String totlexperience = String.valueOf(getSingleAssocProfile.getEstablishDate());
                        val lngknown: String =
                            java.lang.String.valueOf(getSingleAssocProfile.businessDescription)

//                            About abouttt = getSingleAssocProfile.getAbout();
                        val abtme: String? = getSingleAssocProfile.businessDescription
                        //                            https://nationlearns.com/images/business/banner/16697077401669745522934.jpg
                        val profile_pic: String =
                            Configs.BASE_URL21 + "images/business/banner/" + getSingleAssocProfile.bannerImage
                        assoc_id = java.lang.String.valueOf(getSingleAssocProfile.id)
                        asso_id?.text = assoc_id
                        //                            String video_intro = String.valueOf(getSingleAssocProfile.getVideoIntro());
//                            String qulifctn = String.valueOf(getSingleAssocProfile.getQualifictn());
                        val expert_inf_str: String =
                            java.lang.String.valueOf(getSingleAssocProfile.businessName)
                        val service_provided: String =
                            java.lang.String.valueOf(getSingleAssocProfile.companyType)
                        //                            String agnt_type_str = String.valueOf(getSingleAssocProfile.getAgent_typ());
//                            String statee = String.valueOf(getSingleAssocProfile.getState());
                        if (businessItemList != null) {
                            if (businessItemList.isNotEmpty()) {
                                for (i2 in businessItemList.indices) {
                                            val subBranchList: ViewProfileItem = businessItemList[0]
                                            val ratingss: List<RatingsItem>? = subBranchList.ratings
                                    if (ratingss != null) {
                                        for (j in ratingss.indices) {
                                            //
                                            val ratingtxt: String =
                                                java.lang.String.valueOf(ratingss[0].review)
                                            ratingBar?.rating = ratingtxt.toFloat()
                                            val prorate = ratingtxt.toFloat().toString() + "/" + "5"
                                            txtRatingValue?.text = prorate
                                        }
                                    }
                                        }
                            }
                        }


                        spltst_in?.text = namee
                        //                            agentty.setText(agnt_type_str);
                        circleImageView?.let {
                            Glide.with(this@AsstCntPrfileActivity)
                                .load(profile_pic)
                                .into(it)
                        }

//                            state.setText(statee);
                        name?.text =(namee)
                        txAboutme?.text =(abtme)
                        city?.text =(cityr)
                        txpincode?.text =(pinccode)
                        txServiceProvided?.text = service_provided
                        txLanguage?.text =(lngknown)
                        //


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    private fun postConnect() {
        blur_reg1?.visibility = View.VISIBLE
        name_str = manager?.getPreferences(this, Constants.USER_NAME)
        semail = manager?.getPreferences(this, Constants.USER_EMAIL)
        snumber = manager?.getPreferences(this, Constants.USER_NUM)
        edit_query_str = edit_query?.text.toString()
        assoc_id = asso_id?.text.toString()
        agentSpinCnt_str = agentty?.text.toString()
        //        Log.e("agentSpinCnt_str456", agenlooking);
        RetrofitClient.getClient().connect_associate1(
            assoc_id, name_str, snumber, semail, edit_query_str,
            agenlooking, agentty?.text.toString(), "Connect", "application/json",
            "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(name) {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                blur_reg1?.visibility = View.GONE
                try {
                    val takeRes  = response.body()?.toString()
                    if (takeRes != null) {
                        if (takeRes.contains("1")) {
                            edit_query?.setText("")
                            //                                Intent intent = new Intent(AsstCntPrfileActivity.this, FormPage2.class);
                //                                intent.putExtra("agent_data", agentSpinCnt_str);
                //                                intent.putExtra("type", "Connect");
                //                                startActivity(intent);
                //                                Toast.makeText(AssociateViewProileActivity.this, "Successful !!! Your Requirement has been Submiited Successfully", Toast.LENGTH_SHORT).show();
                        } else if (takeRes.contains("0")) {
                            Toast.makeText(
                                this@AsstCntPrfileActivity,
                                "Try Again!!",
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

    private fun reviewCmt() {
        bundle = intent.extras
        assoc_id = bundle?.getString("ass_id")
        user_id_str = bundle?.getString("user_iddd")
        //        sendreview, review ,rating ,user_id ,business_id
        blur_reg1?.visibility = View.VISIBLE
        assoc_id = asso_id?.text.toString()
        rate_value = ratingBar?.rating
        edit_reviewss_str = edit_rev?.text.toString()

//        user_id_str,
        RetrofitClient.getClient().rvw_asocte_new(
            assoc_id, edit_reviewss_str, rate_value.toString(),
            "application/json", "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(name) {
           override fun onResponse(call: Call<String?>, response: Response<String?>) {
                blur_reg1?.visibility = View.GONE
                try {
                    val takeRes  = response.body()?.toString()
                    if (takeRes != null) {
                        if (takeRes.contains("2")) {
                            edit_rev?.setText("")
                            Toast.makeText(
                                this@AsstCntPrfileActivity,
                                "Rating has already done",
                                Toast.LENGTH_SHORT
                            ).show()
                            rankDialog!!.dismiss()
                        } else if (takeRes.contains("1")) {
                            edit_rev?.setText("")
                            Toast.makeText(
                                this@AsstCntPrfileActivity,
                                "Thank you for your rating.",
                                Toast.LENGTH_SHORT
                            ).show()
                            rankDialog!!.dismiss()
                        } else {
                            Toast.makeText(
                                this@AsstCntPrfileActivity,
                                "Try Again!!",
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
}



//
//                            String vieww = String.valueOf(getSingleAssocProfile.getProfileView());
//                            String pro_visits = String.valueOf(getSingleAssocProfile.getProfileVisit());

//                            quali.setText(qulifctn);

//                            if (namee != null && !namee.isEmpty() && !namee.equals("null")){
//                                name.setText("");
//
//                            }else {
//                            }

//                            if (namee.equals("null") || cityr.equals("null") || expert_inf_str.equals("null") ||
//                                    abtme.equals("null") || pinccode.equals("null")) {
//                                name.setText("");
//                                city.setText("");
//                                txpincode.setText("");
//                                txAboutme.setText("");
//                                spltst_in.setText("");
//                            }else
//                            {

//                                name.setText(namee);
////                            state.setText(statee);
//                                city.setText(cityr);
//                                txpincode.setText(pinccode);
//                                txServiceProvided.setText(service_provided);
//                                txLanguage.setText(lngknown);
//                                spltst_in.setText(expert_inf_str);
//                            }

//                            if (totlexperience.equals("")) {
//                                txExperience.setText("");
//                            } else {
//
//                                txExperience.setText(totlexperience + " Years");
//                            }


//                            views.setText(vieww);
//                            profile_visits.setText(pro_visits);
//

//
//
//                            if (video_intro.equals("")) {
//                                vid_ll.visibility = View.GONE;
//
//                            } else {
//                                videoView1.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        Intent des = new Intent(AsstCntPrfileActivity.this, VideoPlayerActivity.class);
//                                        des.putExtra("video_intro", video_intro);
//                                        startActivity(des);
//
//                                    }
//                                });

//                            }