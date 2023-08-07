package www.rahagloball.loginregkotapp.models.cashfrii

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymnetSessionPojo {
    @SerializedName("payment_session_id")
    @Expose
    var paymentSessionId: PaymentSessionId? = null
}