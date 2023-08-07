package www.rahagloball.loginregkotapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import www.rahagloball.loginregkotapp.R

class MyCourseActivity : AppCompatActivity() {
    var spurt_tooltxttx: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_crse)
        spurt_tooltxttx = findViewById<TextView>(R.id.spurt_tooltxttx)
        spurt_tooltxttx?.text = "My Courses"
    }
}