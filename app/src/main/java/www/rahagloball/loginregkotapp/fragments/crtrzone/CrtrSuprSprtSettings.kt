package www.rahagloball.loginregkotapp.fragments.crtrzone

//import okhttp3.MediaType
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Response
import www.rahagloball.loginregkotapp.R
import www.rahagloball.loginregkotapp.SessionManager
import www.rahagloball.loginregkotapp.constsnsesion.Constants
import www.rahagloball.loginregkotapp.models.GlobalCallback
import www.rahagloball.loginregkotapp.models.RetrofitClient
class CrtrSuprSprtSettings : Fragment() {
    var monthly_price: EditText? = null
    var sixmonthly_price: EditText? = null
    var twlvmonthly_price: EditText? = null
    var monthly_bnfts: EditText? = null
    var sixmonthly_bnfts: EditText? = null
    var twlvmonthly_bnfts: EditText? = null
    var monthly_price_str: String? = null
    var sixmonthly_price_str: String? = null
    var twlvmonthly_price_str: String? = null
    var monthly_bnfts_str: String? = null
    var sixmonthly_bnfts_str: String? = null
    var twlvmonthly_bnfts_str: String? = null
    var submit_ssprtsprt: TextView? = null
    var token: String? = null
    var manager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewexp: View = inflater.inflate(R.layout.ch_sssetngs_layout, container, false)
        manager = SessionManager()
        token = manager?.getPreferences(requireActivity(), Constants.USER_TOKEN_LRN)
        monthly_price = viewexp.findViewById<EditText>(R.id.monthly_price)
        sixmonthly_price = viewexp.findViewById<EditText>(R.id.sixmonthly_price)
        twlvmonthly_price = viewexp.findViewById<EditText>(R.id.twlvmonthly_price)
        monthly_bnfts = viewexp.findViewById<EditText>(R.id.monthly_bnfts)
        sixmonthly_bnfts = viewexp.findViewById<EditText>(R.id.sixmonthly_bnfts)
        twlvmonthly_bnfts = viewexp.findViewById<EditText>(R.id.twlvmonthly_bnfts)
        submit_ssprtsprt = viewexp.findViewById<TextView>(R.id.submit_ssprtsprt)
        sixmonthly_price?.setEnabled(false)
        twlvmonthly_price?.setEnabled(false)
        monthly_bnfts?.setEnabled(false)
        sixmonthly_bnfts?.setEnabled(false)
        twlvmonthly_bnfts?.setEnabled(false)
        monthly_price?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val valueString = s.toString()
                if (!valueString.isEmpty()) {
                    val value = valueString.toInt()
                    val result1 = value * 6
                    sixmonthly_price?.setText(result1.toString())
                    val result2 = value * 12
                    twlvmonthly_price?.setText(result2.toString())
                    sixmonthly_price?.isEnabled = false
                    twlvmonthly_price?.isEnabled = false
                    monthly_bnfts?.isEnabled = true
                    sixmonthly_bnfts?.isEnabled = true
                    twlvmonthly_bnfts?.isEnabled = true
                } else {
                    sixmonthly_price?.setText("")
                    twlvmonthly_price?.setText("")
                    monthly_bnfts?.setText("")
                    sixmonthly_bnfts?.setText("")
                    twlvmonthly_bnfts?.setText("")
                    sixmonthly_price?.isEnabled = false
                    twlvmonthly_price?.isEnabled = false
                    monthly_bnfts?.isEnabled = false
                    sixmonthly_bnfts?.isEnabled = false
                    twlvmonthly_bnfts?.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        submit_ssprtsprt?.setOnClickListener(View.OnClickListener { v: View? -> checkvalidation() })
        return viewexp
    }

    private fun checkvalidation() {
        monthly_price_str = monthly_price?.text.toString()
        sixmonthly_price_str = sixmonthly_price?.text.toString()
        twlvmonthly_price_str = twlvmonthly_price?.text.toString()
        monthly_bnfts_str = monthly_bnfts?.text.toString()
        sixmonthly_bnfts_str = sixmonthly_bnfts?.text.toString()
        twlvmonthly_bnfts_str = twlvmonthly_bnfts?.text.toString()
        if (monthly_price_str == "" || monthly_price_str!!.isEmpty() || sixmonthly_price_str == "" || sixmonthly_price_str!!.isEmpty() || twlvmonthly_price_str == "" || twlvmonthly_price_str!!.isEmpty() || monthly_bnfts_str == "" || monthly_bnfts_str!!.isEmpty() || sixmonthly_bnfts_str == "" || sixmonthly_bnfts_str!!.isEmpty() || twlvmonthly_bnfts_str == "" || twlvmonthly_bnfts_str!!.isEmpty()) {
            Toast.makeText(activity, "Enter all the fields!", Toast.LENGTH_SHORT).show()
        } else {
            get_ssuprt_stngs()
        }
    }

    private fun get_ssuprt_stngs() {
        monthly_price_str = monthly_price?.text.toString()
        sixmonthly_price_str = sixmonthly_price?.text.toString()
        twlvmonthly_price_str = twlvmonthly_price?.text.toString()
        monthly_bnfts_str = monthly_bnfts?.text.toString()
        sixmonthly_bnfts_str = sixmonthly_bnfts?.text.toString()
        twlvmonthly_bnfts_str = twlvmonthly_bnfts?.text.toString()
        RetrofitClient.getClient().postssprtstngs(
            monthly_price_str, monthly_bnfts_str, sixmonthly_price_str, sixmonthly_bnfts_str,
            twlvmonthly_price_str, twlvmonthly_bnfts_str, "application/json", "Bearer $token"
        )?.enqueue(object : GlobalCallback<String?>(monthly_price) {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                try {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            activity,
                            "Super support settings has been Successfully set!",
                            Toast.LENGTH_SHORT
                        ).show()
                        requireActivity().finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
}