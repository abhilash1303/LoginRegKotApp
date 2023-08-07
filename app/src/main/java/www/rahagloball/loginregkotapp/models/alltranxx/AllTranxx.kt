package www.rahagloball.loginregkotapp.models.alltranxx

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllTranxx {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("paid_for_id")
    @Expose
    var paidForId: String? = null

    @SerializedName("paid_for_type")
    @Expose
    var paidForType: String? = null

    @SerializedName("transaction_id")
    @Expose
    var transactionId: String? = null

    @SerializedName("gateway")
    @Expose
    var gateway: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

    @SerializedName("destination")
    @Expose
    var destination: String? = null

    @SerializedName("hash")
    @Expose
    var hash: String? = null

    @SerializedName("response")
    @Expose
    var response: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("payment_mode")
    @Expose
    var paymentMode: String? = null

    @SerializedName("amount")
    @Expose
    var amount: String? = null

    @SerializedName("verified_at")
    @Expose
    var verifiedAt: String? = null

    @SerializedName("deleted_at")
    @Expose
    var deletedAt: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}