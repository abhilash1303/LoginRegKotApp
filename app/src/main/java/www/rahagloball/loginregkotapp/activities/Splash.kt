package www.rahagloball.loginregkotapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.constsnsesion.Constants

class Splash : AppCompatActivity() {

    private lateinit var manager: SessionManager
    private lateinit var bt: TextView
    private lateinit var bt1: TextView
    private lateinit var textmsg1: TextView
    private lateinit var uptodown: Animation
    private lateinit var downtotop: Animation
    private lateinit var blink: Animation
    private lateinit var logooooooo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        manager = SessionManager()
        uptodown = AnimationUtils.loadAnimation(this, R.anim.splashtransition)
        downtotop = AnimationUtils.loadAnimation(this, R.anim.splashtransition1)
        blink = AnimationUtils.loadAnimation(this, R.anim.bounce)

        logooooooo = findViewById(R.id.logooooooo)
        textmsg1 = findViewById(R.id.textmsg1)
        bt = findViewById(R.id.bt)
        bt1 = findViewById(R.id.bt1)
        logooooooo.animation = uptodown
        bt.animation = downtotop
        bt1.animation = downtotop
        textmsg1.animation = blink


        val handlerThread = HandlerThread("SplashHandlerThread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        handler.postDelayed({
            runOnUiThread {
                handleSplashDelay()
            }
        }, 3000)

    }


    private fun handleSplashDelay() {
        if (Constants.isWorkingInternetPresent(this)) {
            val status1 = manager.getPreferences(this@Splash, Constants.LOGIN_STATUS)
            val targetActivity = if (status1 == "1") {
                HomeDemoActivityCtgry::class.java
            } else {
                LoginOtpActivityTimer::class.java
            }
            val intent = Intent(this@Splash, targetActivity)
            startActivity(intent)
        } else {
            val intent = Intent(this@Splash, InternetRetry::class.java)
            startActivity(intent)
        }
        finish()
    }


}
