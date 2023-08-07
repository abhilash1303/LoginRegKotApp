@file:Suppress("UNREACHABLE_CODE")

package www.rahagloball.loginregkotapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient

class LanguageActivityDemo : AppCompatActivity() {
    var rb_languge: RadioGroup? = null
    var rb_kannada: RadioButton? = null
    var rb_eng: RadioButton? = null
    var rb_hindi: RadioButton? = null
    var rb_telgu: RadioButton? = null
    var rb_idd: RadioButton? = null
    var continuee: Button? = null
    var manager: SessionManager? = null
    var token: String? = null
    var blur_reg_lang: RelativeLayout? = null
    var chk1: CheckBox? = null
    var chk2: CheckBox? = null
    var chk3: CheckBox? = null
    var chk4: CheckBox? = null
    var chk5: CheckBox? = null
    var chk6: CheckBox? = null
    var chk7: CheckBox? = null
    var chk8: CheckBox? = null
    var chk9: CheckBox? = null
    var btn: Button? = null
    var txt: TextView? = null
    var list: ArrayList<String>? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_demo)
        manager = SessionManager()
        token = manager!!.getPreferences(this@LanguageActivityDemo, Constants.USER_TOKEN_LRN)
        list = ArrayList()
        chk1 = findViewById<CheckBox>(R.id.checkBox1)
        chk2 = findViewById<CheckBox>(R.id.checkBox2)
        chk3 = findViewById<CheckBox>(R.id.checkBox3)
        chk4 = findViewById<CheckBox>(R.id.checkBox4)
        chk5 = findViewById<CheckBox>(R.id.checkBox5)
        chk6 = findViewById<CheckBox>(R.id.checkBox6)
        chk7 = findViewById<CheckBox>(R.id.checkBox7)
        chk8 = findViewById<CheckBox>(R.id.checkBox8)
        chk9 = findViewById<CheckBox>(R.id.checkBox9)
        txt = findViewById<TextView>(R.id.textView1)
        btn = findViewById<Button>(R.id.continuee)
        //        addListenerOnButton();
        blur_reg_lang = findViewById<RelativeLayout>(R.id.blur_reg_lang)
        btn!!.setOnClickListener { v: View? -> postlsnguage() }
    }

    @SuppressLint("SetTextI18n")
    private fun postlsnguage() {
        blur_reg_lang?.visibility = View.VISIBLE
        for (str in list!!) {
            val strWithComma = ", $str"
            val str1 = strWithComma.replace(",", " ")
            txt?.text = txt?.text.toString() + str
            RetrofitClient.getClient().getlangaugee(
                str1, "",
                "application/json",
                "Bearer $token"
            )?.enqueue(object : GlobalCallback<String?>(rb_kannada) {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    TODO("Not yet implemented")
                    blur_reg_lang?.visibility = View.GONE
                    try {
                        val languagestr  = response.body()?.toString()
                        if (languagestr != null) {
                            if (languagestr.contains("1")) {
                                val intent =
                                    Intent(this@LanguageActivityDemo, LoginInterestDemo::class.java)
                                startActivity(intent)
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            })
        }
    }




    fun onCheckboxClicked(view: View) {
        val checked: Boolean = (view as CheckBox).isChecked()
        when (view.id) {
            R.id.checkBox1 -> list!!.add(chk1?.tag.toString())
            R.id.checkBox2 -> list!!.add(chk2?.tag.toString())
            R.id.checkBox3 -> list!!.add(chk3?.tag.toString())
            R.id.checkBox4 -> list!!.add(chk4?.tag.toString())
            R.id.checkBox5 -> list!!.add(chk5?.tag.toString())
            R.id.checkBox6 -> list!!.add(chk6?.tag.toString())
            R.id.checkBox7 -> list!!.add(chk7?.tag.toString())
            R.id.checkBox8 -> list!!.add(chk8?.tag.toString())
            R.id.checkBox9 -> list!!.add(chk9?.tag.toString())
        }


    }
}