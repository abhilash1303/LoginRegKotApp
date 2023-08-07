package www.rahagloball.loginregkotapp.tandc

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R

class NlPaidTerms : AppCompatActivity() {
    var policy: WebView? = null
    var spurt_tooltxttx: TextView? = null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutus)
        policy = findViewById(R.id.policy)
        spurt_tooltxttx = findViewById(R.id.spurt_tooltxttx)
        spurt_tooltxttx?.text = "Paid Service Terms"
        policy!!.settings.javaScriptEnabled = true
        policy!!.loadUrl("file:///android_asset/nlpt.html")
    }
}