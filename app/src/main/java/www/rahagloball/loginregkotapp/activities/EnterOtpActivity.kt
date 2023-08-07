package www.rahagloball.loginregkotapp.activities

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.MainActivity
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.loginmodel.Register.RregisterPojo
import www.rahagloball.loginregkotapp.models.loginmodel.lhome.LHomePojo


class EnterOtpActivity : AppCompatActivity() {
//    var ph_str: String? = null
//    var res1: String? = null
//    var res2: String? = null
//    var entered_phone: TextView? = null
//    var resend_otpnew: TextView? = null
//    var otp_maar: EditText? = null
//    var otp_maar_str: String? = null
//    var FireBaseToken: String? = null
//    var submit_optt: TextView? = null
//    var blur_reg_login: RelativeLayout? = null
//    var manager: SessionManager? = null
//    var context: Context? = null
//    var customDialog: CustomDialog? = null

    private lateinit var ph_str: String
    private lateinit var res1: String
    private lateinit var entered_phone: TextView
    private lateinit var resend_otpnew: TextView
    private lateinit var otp_maar: EditText
    private lateinit var otp_maar_str: String
    private lateinit var submit_optt: TextView
    private lateinit var blur_reg_login: RelativeLayout
    private lateinit var manager: SessionManager
    private lateinit var context: Context
    private lateinit var customDialog: CustomDialog


    //    CircularProgressIndicator progress_bar;
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tnew_enter_otp)
        context = this@EnterOtpActivity
        manager = SessionManager()
        customDialog = CustomDialog(this)
        val bundle = intent.extras
        ph_str = bundle?.getString("enter_ph") ?: ""
        res1 = bundle?.getString("enter_ph1") ?: ""

        entered_phone = findViewById<TextView>(R.id.entered_phone)
        //        progress_bar = findViewById(R.id.progress_bar);
        resend_otpnew = findViewById<TextView>(R.id.resend_otpnew)
        otp_maar = findViewById<EditText>(R.id.otp_maar)
        submit_optt = findViewById<TextView>(R.id.submit_optt)
        entered_phone?.text=(" +91 $ph_str")
        resend_otpnew.setOnClickListener { resendOtp }
        submit_optt.setOnClickListener {
//            hideKeyboardFrom(context, view);
            checotpvalidation()
        }
    }

    private fun checotpvalidation() {
        otp_maar_str = otp_maar.text.toString()
        if (otp_maar_str == "" || otp_maar_str.isEmpty()) {
            Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
        } else {
            if (res1.contains("1")) {
                postOtpData()
            } else if (res1.contains("2")) {
                postOtpDataRegist()
            } else if (res1.contains("3")) {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postOtpData() {
        try {
            customDialog.show()
            otp_maar_str = otp_maar?.text.toString()
            //            FireBaseToken = FirebaseInstanceId.getInstance().getToken();
            RetrofitClient.getClient().getverigyotp(otp_maar_str, ph_str, "l", "nl-app", "")
                ?.enqueue(object : GlobalCallback<LHomePojo?>(entered_phone) {
                    override fun onResponse(call: Call<LHomePojo?>, response: Response<LHomePojo?>) {
                        customDialog.dismiss()
                        try {
                            val otp_verify_res1= response.body()
                            val Lresult_str: String? = otp_verify_res1?.result
                            val token_str: String? = otp_verify_res1?.accessToken
                            val uNum: String? = otp_verify_res1?.mobile
                            if (Lresult_str != null) {
                                if (Lresult_str.contains("l")) {
                                    val phonei = Intent(this@EnterOtpActivity, HomeDemoActivityCtgry::class.java)
                                    if (uNum != null) {
                                        manager.setPreferences(this@EnterOtpActivity, Constants.USER_NUM1, uNum)
                                    }
                                    if (token_str != null) {
                                        manager.setPreferences(this@EnterOtpActivity, Constants.USER_TOKEN_LRN, token_str)
                                    }
                                    manager.setPreferences(this@EnterOtpActivity, Constants.LOGIN_STATUS, "1")
                                    startActivity(phonei)
                                }
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun postOtpDataRegist() {
        try {
            customDialog.show()
            otp_maar_str = otp_maar.text.toString()
            RetrofitClient.getClient().getverigyotpr(otp_maar_str, ph_str, "R", "nl-app", "")
                ?.enqueue(object : GlobalCallback<RregisterPojo?>(entered_phone) {
                       override fun onResponse(call: Call<RregisterPojo?>, response: Response<RregisterPojo?>) {
                        customDialog.dismiss()
                        try {
                            val otp_verify_res1 = response.body()
                            if (otp_verify_res1 != null) {

                                val Lresult_str: String? = otp_verify_res1?.result
//                                val token_str: String? = otp_verify_res1?.accessToken

                                val resultstr:String? = otp_verify_res1.result
//                                Log.e("result_str",result_str)
                                val tokenstrR = otp_verify_res1.accessToken
                                if (resultstr == "R") {
                                    val phonei = Intent(this@EnterOtpActivity, EditProfileNew::class.java)
                                    if (tokenstrR != null) {
                                        manager.setPreferences(this@EnterOtpActivity, Constants.USER_TOKEN_LRN, tokenstrR)
                                    }
                                    manager.setPreferences(this@EnterOtpActivity, Constants.LOGIN_STATUS, "0")
                                    startActivity(phonei)
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    override fun onFailure(call: Call<RregisterPojo?>, t: Throwable) {
                        customDialog.dismiss()
                        // Handle failure
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    private val resendOtp: Unit
        get() {
            try {
                customDialog.show()
                RetrofitClient.getClient().resendotpp(ph_str)
                    ?.enqueue(object : GlobalCallback<String?>(resend_otpnew) {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            customDialog.dismiss()
                            Toast.makeText(context, "OTP Sent Wait!!", Toast.LENGTH_SHORT).show()
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}