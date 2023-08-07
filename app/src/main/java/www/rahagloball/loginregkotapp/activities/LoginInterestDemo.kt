package www.rahagloball.loginregkotapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.activities.HomeDemoActivityCtgry
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient

class LoginInterestDemo : AppCompatActivity() {
    var manager: SessionManager? = null
    var chipGroup_listt: ChipGroup? = null
    var rv_chiplist: RecyclerView? = null
//    var adapter: MyRecyclerViewAdapter? = null
    private val layoutManager: RecyclerView.LayoutManager? = null
    var token: String? = null
    var blur_reg_intsrst: RelativeLayout? = null
    var img_not_foundd_intrst: ImageView? = null
    var chipfashion: Chip? = null
    var chipmulti: Chip? = null
    var chipgame: Chip? = null
    var chipsport: Chip? = null
    var chipcareer: Chip? = null
    var chipenter: Chip? = null
    var chiphnf: Chip? = null
    var chipfit: Chip? = null
    var chiptech: Chip? = null
    var chipoutdprs: Chip? = null
    var chipanim: Chip? = null
    var chiparts: Chip? = null
    var chibiz: Chip? = null
    var chifood: Chip? = null
    var chitevl: Chip? = null
    var chitmusic: Chip? = null
    var continuee: Button? = null
    private var selcted_chips: ArrayList<String>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_chipdemo)
        manager = SessionManager()
        token = manager!!.getPreferences(this@LoginInterestDemo, Constants.USER_TOKEN_LRN)
        selcted_chips = ArrayList()
        chipGroup_listt = findViewById<ChipGroup>(R.id.chipGroup_listt)
        continuee = findViewById<Button>(R.id.continuee)
        //        rv_chiplist = findViewById(R.id.rv_chiplist);
        chipfashion = findViewById<Chip>(R.id.chipfashion)
        chipmulti = findViewById<Chip>(R.id.chipmulti)
        chipgame = findViewById<Chip>(R.id.chipgame)
        chipsport = findViewById<Chip>(R.id.chipsport)
        chipcareer = findViewById<Chip>(R.id.chipcareer)
        chipenter = findViewById<Chip>(R.id.chipenter)
        chiptech = findViewById<Chip>(R.id.chiptech)
        chipoutdprs = findViewById<Chip>(R.id.chipoutdprs)
        chipanim = findViewById<Chip>(R.id.chipanim)
        chiparts = findViewById<Chip>(R.id.chiparts)
        chipfit = findViewById<Chip>(R.id.chipfit)
        chiphnf = findViewById<Chip>(R.id.chiphnf)
        chibiz = findViewById<Chip>(R.id.chibiz)
        chifood = findViewById<Chip>(R.id.chifood)
        chitevl = findViewById<Chip>(R.id.chitevl)
        chitmusic = findViewById<Chip>(R.id.chitmusic)
        val filterChipListener: CompoundButton.OnCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
//
                if (isChecked) {
                    selcted_chips!!.add(buttonView.getText().toString())
                } else {
                    selcted_chips!!.remove(buttonView.getText().toString())
                }
            }
        //
        chipoutdprs?.setOnCheckedChangeListener(filterChipListener)
        chipanim?.setOnCheckedChangeListener(filterChipListener)
        chiparts?.setOnCheckedChangeListener(filterChipListener)
        chipfit?.setOnCheckedChangeListener(filterChipListener)
        chiphnf?.setOnCheckedChangeListener(filterChipListener)
        chiptech?.setOnCheckedChangeListener(filterChipListener)
        chibiz?.setOnCheckedChangeListener(filterChipListener)
        chifood?.setOnCheckedChangeListener(filterChipListener)
        chitevl?.setOnCheckedChangeListener(filterChipListener)
        chitmusic?.setOnCheckedChangeListener(filterChipListener)
        chipenter?.setOnCheckedChangeListener(filterChipListener)
        chipcareer?.setOnCheckedChangeListener(filterChipListener)
        chipsport?.setOnCheckedChangeListener(filterChipListener)
        chipgame?.setOnCheckedChangeListener(filterChipListener)
        chipmulti?.setOnCheckedChangeListener(filterChipListener)
        chipfashion?.setOnCheckedChangeListener(filterChipListener)
        continuee!!.setOnClickListener { v: View? ->
            val output = selcted_chips.toString().replace("(^\\[|\\]$)".toRegex(), "")

//            Log.e("slelected chipss",output);
            RetrofitClient.getClient().getlangaugee(
                "", output,
                "application/json",
                "Bearer $token"
            )?.enqueue(object : GlobalCallback<String?>(continuee) {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {

                    //                    blur_reg_intsrst.visibility = View.GONE;
                    val languagestr  = response.body()?.toString()
                    //                Log.e("languagestr", languagestr);
                    if (languagestr != null) {
                        if (languagestr.contains("1")) {
                            manager!!.setPreferences(this@LoginInterestDemo, Constants.LOGIN_STATUS, "1")
                            val intent =
                                Intent(this@LoginInterestDemo, HomeDemoActivityCtgry::class.java)
                            startActivity(intent)
                        }
                    }
                }
            })
        }
    }
}