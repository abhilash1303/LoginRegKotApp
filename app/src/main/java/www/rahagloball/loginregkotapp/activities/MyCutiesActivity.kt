package www.rahagloball.loginregkotapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R


class MyCutiesActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ctss)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@MyCutiesActivity, HomeDemoActivityCtgry::class.java))
        overridePendingTransition(0, 0)
    }
}