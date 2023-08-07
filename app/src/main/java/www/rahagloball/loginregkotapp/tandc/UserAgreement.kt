package www.rahagloball.loginregkotapp.tandc

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R

class UserAgreement : AppCompatActivity() {
    var privacy_policy: WebView? = null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.useragreement)
        privacy_policy = findViewById(R.id.privacy_policy)
        privacy_policy!!.settings.javaScriptEnabled = true
        privacy_policy?.loadUrl("file:///android_asset/nlpp.html")
    }
}