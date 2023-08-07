package www.rahagloball.loginregkotapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.constsnsesion.Constants

class InternetRetry : AppCompatActivity(), View.OnClickListener {

    private lateinit var retry_btn: Button
    private lateinit var bar: ProgressBar
    private lateinit var manager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.internet_retry_layout)

        manager = SessionManager()

        retry_btn = findViewById(R.id.button7)
        bar = findViewById(R.id.progressBar)

        retry_btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        bar.visibility = View.VISIBLE
        if (Constants.isWorkingInternetPresent(this)) {
            val status = manager.getPreferences(this@InternetRetry, Constants.LOGIN_STATUS)
            if (status == "1") {
                val i = Intent(this@InternetRetry, HomeDemoActivityCtgry::class.java)
                startActivity(i)
                finish()
            } else {
                val i = Intent(this@InternetRetry, LoginOtpActivityTimer::class.java)
                startActivity(i)
                finish()
            }
            finish()
        } else {
            bar.visibility = View.GONE
        }
    }


}
