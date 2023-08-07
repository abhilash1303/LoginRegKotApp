package www.rahagloball.loginregkotapp.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.configuration.Configs
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
import www.rahagloball.loginregkotapp.models.updtprofl.ProfileItem
import www.rahagloball.loginregkotapp.models.updtprofl.UpdtproflPojo

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var assoxmarystsss: TextView
    private lateinit var assox_occupationn: TextView
    private lateinit var assox_genderr: TextView
    private lateinit var assox_name: TextView
    private lateinit var assox_email: TextView
    private lateinit var assox_pinnc: TextView
    private lateinit var assox_cityy: TextView
    private lateinit var assox_mob: TextView
    private lateinit var assox_agee: TextView
    private lateinit var assox_mary_stsss_str: String
    private lateinit var assox_occupationn_str: String
    private lateinit var assox_genderr_str: String
    private lateinit var assox_name_str: String
    private lateinit var assox_agee_str: String
    private lateinit var assox_email_str: String
    private lateinit var assox_mob_str: String
    private lateinit var imgProfilePic_str: String
    private lateinit var assox_cityy_str: String
    private lateinit var assox_pinnc_str: String
    private lateinit var imgProfilePic: CircleImageView
    private lateinit var updatButton: Button
    private lateinit var token: String
    private lateinit var mContext: Context
    private lateinit var manager: SessionManager
    private lateinit var tool_back_img: ImageView
    private lateinit var progressBar_up: ProgressBar
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile_new)
        mContext = this@UpdateProfileActivity
        manager = SessionManager()
        token = manager.getPreferences(this@UpdateProfileActivity, Constants.USER_TOKEN_LRN)
        assox_name = findViewById<TextView>(R.id.assox_name)
        assoxmarystsss = findViewById<TextView>(R.id.assox_mary_stsss)
        assox_occupationn = findViewById<TextView>(R.id.assox_occupationn)
        assox_genderr = findViewById<TextView>(R.id.assox_genderr)
        assox_email = findViewById<TextView>(R.id.assox_email)
        assox_mob = findViewById<TextView>(R.id.assox_mob)
        imgProfilePic = findViewById<CircleImageView>(R.id.imgProfilePic)
        updatButton = findViewById<Button>(R.id.updatButton)
        assox_pinnc = findViewById<TextView>(R.id.assox_pinnc)
        assox_cityy = findViewById<TextView>(R.id.assox_cityy)
        tool_back_img = findViewById<ImageView>(R.id.tool_back_img)
        progressBar_up = findViewById<ProgressBar>(R.id.progressBar_up)
        assox_agee = findViewById<TextView>(R.id.assox_agee)
        updatButton.setOnClickListener { v: View? ->
            val `in`: Intent = Intent(this@UpdateProfileActivity, EditProfileNew::class.java)
            startActivity(`in`)
        }
        tool_back_img.setOnClickListener { v: View? -> onBackPressed() }
        profileimage()
    }

    private fun profileimage() {
        progressBar_up.visibility = View.VISIBLE
        val url: String = Configs.BASE_URL2 + "profile"
        RetrofitClient.getClient().update_profilenw(url, "application/json", "Bearer $token")
            ?.enqueue(object : GlobalCallback<UpdtproflPojo?>(assox_name) {
                override fun onResponse(
                    call: Call<UpdtproflPojo?>,
                    response: Response<UpdtproflPojo?>
                ) {
                    progressBar_up.visibility = View.GONE
                    try {
                        val profileItems: List<ProfileItem>? = response.body()?.profile
                        if (profileItems != null) {
                            val profileItem: ProfileItem = profileItems[0]
                            assox_name_str = profileItem.name.toString()
                            assox_mob_str = profileItem.mobile.toString()
                            assox_email_str = profileItem.email.toString()
                            assox_genderr_str = profileItem.gnederr.toString()
                            assox_occupationn_str = profileItem.occupation.toString()
                            assox_cityy_str = profileItem.city.toString()
                            assox_pinnc_str = profileItem.pincode.toString()
                            assox_mary_stsss_str = profileItem.marriedStatus.toString()
                            assox_agee_str = profileItem.age.toString()
                            assox_genderr.text = (assox_genderr_str)
                            assox_occupationn.text = (assox_occupationn_str)
                            assox_occupationn.text = (assox_occupationn_str)
                            assox_cityy.text = (assox_cityy_str)
                            assoxmarystsss.text = (assox_mary_stsss_str)
                            assox_pinnc.text = (assox_pinnc_str)
                            assox_name.text = (assox_name_str)
                            assox_email.text = (assox_email_str)
                            assox_pinnc.text = (assox_pinnc_str)
                            assox_agee.text = (assox_agee_str)
                            assox_mob.text = (assox_mob_str)
                            imgProfilePic_str =
                                Configs.BASE_URL21 + "images/user/" + profileItem.userImage
                            mContext.let {
                                imgProfilePic.let { it1 ->
                                    Glide.with(it).load(imgProfilePic_str).into(
                                        it1
                                    )
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}