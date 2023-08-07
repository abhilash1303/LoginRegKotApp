package www.rahagloball.loginregkotapp.models.cashfrii

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CashFripojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("cftoken")
    @Expose
    var cftoken: String? = null
}