package www.rahagloball.loginregkotapp.activities.courses

//import okhttp3.MediaType
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.constsnsesion.CustomDialog
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient

class AddQuestionActivty : AppCompatActivity() {
    var manager: SessionManager? = null
    var token: String? = null
    var mContect: Context? = null
    var active_rb: RadioButton? = null
    var deactive_rb: RadioButton? = null
    var all_questns: TextView? = null
    var submit_ques: TextView? = null
    var spnr_sel_course: Spinner? = null
    var ques_status: Spinner? = null
    var edit_ques: EditText? = null
    var status_str = arrayOf("Select", "Active", "Deactive")
   private lateinit var ataAdapter_sel_vh: ArrayAdapter<String>
    var edit_ques_str: String? = null
    var prev_enbld_str1: String? = null
    var bundle: Bundle? = null
    var crs_idd: String? = null
    var customDialog: CustomDialog? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upoad_ques)
        mContect = this@AddQuestionActivty
        customDialog = CustomDialog(this)
        manager = SessionManager()
        token = manager?.getPreferences(this@AddQuestionActivty, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        crs_idd = bundle?.getString("Course_id")
        ques_status = findViewById(R.id.ques_status)
        submit_ques = findViewById(R.id.submit_ques)
        spnr_sel_course = findViewById(R.id.spnr_sel_course)
        edit_ques = findViewById(R.id.edit_ques)
        all_questns = findViewById(R.id.all_questns)
        ataAdapter_sel_vh =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, status_str)
        ataAdapter_sel_vh.setDropDownViewResource(R.layout.custom_spiner_layout)
        ques_status?.setAdapter(ataAdapter_sel_vh)
        submit_ques?.setOnClickListener { v: View? -> checkvalidation() }
        all_questns?.setOnClickListener { v: View? ->
            val intent = Intent(mContect, AllQuestionActivty::class.java)
            startActivity(intent)
        }
    }

    private fun checkvalidation() {
        edit_ques_str = edit_ques?.text.toString()
        prev_enbld_str1 = ques_status?.selectedItem.toString()
        if (edit_ques_str == "" || edit_ques_str!!.isEmpty()) {
            Toast.makeText(mContect, "Enter title!", Toast.LENGTH_SHORT).show()
        } else if (prev_enbld_str1 == "" || prev_enbld_str1!!.isEmpty() || prev_enbld_str1 == "Select") {
            Toast.makeText(mContect, "Kindly select status!", Toast.LENGTH_SHORT).show()
        } else {
            upload_ques()
        }
    }

    private fun upload_ques() {
        customDialog?.show()
        try {
            bundle = intent.extras
            crs_idd = bundle?.getString("Course_id")
            edit_ques_str = edit_ques?.text.toString()
            prev_enbld_str1 = ques_status?.selectedItem.toString()
            if (prev_enbld_str1 == "Active") {
                prev_enbld_str1 = "1"
            } else if (prev_enbld_str1 == "Deactive") {
                prev_enbld_str1 = "0"
            }
            RetrofitClient.getClient().storequest(
                "",
                crs_idd,
                edit_ques_str,
                prev_enbld_str1,
                "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<String?>(edit_ques) {
                    @SuppressLint("NotifyDataSetChanged")
                  override  fun onResponse(call: Call<String?>?, response: Response<String?>) {
                        customDialog?.dismiss()
                        try {
                            if (response.body() != null) {
                                val ques_res  = response.body()?.toString()
                                if (ques_res != null) {
                                    if (ques_res.contains("200")) {
                                        Toast.makeText(
                                            mContect,
                                            "Question Added Successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        finish()
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}