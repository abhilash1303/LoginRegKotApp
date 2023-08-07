package www.rahagloball.loginregkotapp.tandc

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R

class AboutUs : AppCompatActivity() {
    var policy: WebView? = null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutus)
        policy = findViewById(R.id.policy)
        policy!!.settings.javaScriptEnabled = true
        policy!!.loadUrl("file:///android_asset/nltnc.html")
    }
}