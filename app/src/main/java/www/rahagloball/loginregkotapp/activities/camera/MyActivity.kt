package www.rahagloball.loginregkotapp.activities.camera

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { view: View? -> }
    }
}