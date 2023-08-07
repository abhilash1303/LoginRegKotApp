package www.rahagloball.loginregkotapp.activities.courses

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
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


class AddAnncntActivty : AppCompatActivity() {
    var manager: SessionManager? = null
    var token: String? = null
    var mContect: Context? = null
    var all_anncmnts: TextView? = null
    var submit_ques: TextView? = null
    var ques_status: Spinner? = null
    var edit_ques: EditText? = null
    var status_str = arrayOf("Select", "Active", "Deactive")
    var ataAdapter_sel_vh: ArrayAdapter<String>? = null
    var edit_ques_str: String? = null
    var prev_enbld_str1: String? = null
    var crs_idd: String? = null
    var bundle: Bundle? = null
    var customDialog: CustomDialog? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upoad_announce)
        mContect = this@AddAnncntActivty
        customDialog = CustomDialog(this)
        manager = SessionManager()
        token = manager!!.getPreferences(this@AddAnncntActivty, Constants.USER_TOKEN_LRN)
        bundle = intent.extras
        crs_idd = bundle?.getString("Course_id")
        ques_status = findViewById<Spinner>(R.id.ques_status)
        submit_ques = findViewById<TextView>(R.id.submit_annctmnts)
        all_anncmnts = findViewById<TextView>(R.id.all_anncmnts)
        edit_ques = findViewById<EditText>(R.id.edit_ques)
        ataAdapter_sel_vh =
            ArrayAdapter<String>(applicationContext, R.layout.custom_spiner_layout, status_str)
        ataAdapter_sel_vh?.setDropDownViewResource(R.layout.custom_spiner_layout)
        ques_status?.adapter = ataAdapter_sel_vh
        all_anncmnts?.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(mContect, AllAnnouncementActivty::class.java)
            startActivity(intent)
        })
        submit_ques?.setOnClickListener(View.OnClickListener { v: View? -> checkvalidation() })
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
            RetrofitClient.getClient().storeannounce(
                crs_idd,
                edit_ques_str,
                prev_enbld_str1,
                "application/json",
                "Bearer $token"
            )
                ?.enqueue(object : GlobalCallback<String?>(edit_ques) {
                    @SuppressLint("NotifyDataSetChanged")
                    override  fun onResponse(call: Call<String?>, response: Response<String?>) {
                        customDialog?.dismiss()
                        try {
                            if (response.body() != null) {
                                val ques_res  = response.body()?.toString()
                                if (ques_res != null) {
                                    if (ques_res.contains("200")) {
                                        Toast.makeText(
                                            mContect,
                                            "Announcement Added Successfully!",
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