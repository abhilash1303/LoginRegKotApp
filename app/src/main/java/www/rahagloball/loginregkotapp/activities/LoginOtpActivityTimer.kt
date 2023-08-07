package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import java.util.*

class LoginOtpActivityTimer : AppCompatActivity() {
    private lateinit var login_submit_otp: TextView
    private lateinit var context: Context
    private lateinit var login_phone: EditText
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 0
    private lateinit var otp_timerr_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tnew_login_new)
        context = this@LoginOtpActivityTimer
        login_phone = findViewById(R.id.login_phonee)
        login_submit_otp = findViewById(R.id.login_submit_otp)
        otp_timerr_txt = findViewById(R.id.otp_timerr)

        login_submit_otp.setOnClickListener { checkValidation() }
    }

    private fun startOtpTimer() {
        timeLeftInMillis = 30000 // Set the timer to 30 seconds
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                otp_timerr_txt.visibility = View.GONE
                login_submit_otp.visibility = View.VISIBLE
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTimerText() {
        val seconds = (timeLeftInMillis / 1000).toInt()
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds)
        otp_timerr_txt.text = "Getting OTP in $timeLeftFormatted s"
    }

    private fun checkValidation() {
        val mobileNumber = login_phone.text.toString()
        if (mobileNumber.isEmpty() || mobileNumber.length != 10 || !mobileNumber.matches("^[6789]\\d{9}$".toRegex())) {
            Toast.makeText(this@LoginOtpActivityTimer, "Kindly enter a 10-digit mobile number!", Toast.LENGTH_SHORT).show()
        } else {
            postLoginData()
        }
    }

    private fun postLoginData() {
        try {
            startOtpTimer()
            showOtpInputView()
            RetrofitClient.getClient().otploginn(login_phone.text.toString(), "nl-app")
                ?.enqueue(object : GlobalCallback<String>(login_phone) {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        try {
                            val login_res  = response.body()?.toString()
                            if (login_res != null) {
                                if (login_res.contains("1")) {
                                    login_submit_otp.visibility = View.VISIBLE
                                    otp_timerr_txt.visibility = View.GONE
                                    val phonei = Intent(this@LoginOtpActivityTimer, EnterOtpActivity::class.java)
                                    phonei.putExtra("enter_ph", login_phone.text.toString())
                                    phonei.putExtra("enter_ph1", login_res)
                                    startActivity(phonei)
                                } else if (login_res.contains("2")) {
                                    login_submit_otp.visibility = View.VISIBLE
                                    otp_timerr_txt.visibility = View.GONE
                                    val phonei = Intent(this@LoginOtpActivityTimer, EnterOtpActivity::class.java)
                                    phonei.putExtra("enter_ph", login_phone.text.toString())
                                    phonei.putExtra("enter_ph1", login_res)
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

    private fun showOtpInputView() {
        login_submit_otp.visibility = View.GONE
        otp_timerr_txt.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
