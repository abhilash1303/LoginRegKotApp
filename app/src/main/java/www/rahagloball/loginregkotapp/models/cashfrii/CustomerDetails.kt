package www.rahagloball.loginregkotapp.models.cashfrii

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomerDetails {
    @SerializedName("customer_id")
    @Expose
    var customerId: String? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("customer_email")
    @Expose
    var customerEmail: String? = null

    @SerializedName("customer_phone")
    @Expose
    var customerPhone: String? = null
}