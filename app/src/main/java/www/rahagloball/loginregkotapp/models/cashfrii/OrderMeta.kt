package www.rahagloball.loginregkotapp.models.cashfrii

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderMeta {
    @SerializedName("return_url")
    @Expose
    var returnUrl: String? = null

    @SerializedName("notify_url")
    @Expose
    var notifyUrl: String? = null

    @SerializedName("payment_methods")
    @Expose
    var paymentMethods: String? = null
}