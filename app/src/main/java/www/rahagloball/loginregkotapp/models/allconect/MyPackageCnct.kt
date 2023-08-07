package www.rahagloball.loginregkotapp.models.allconect

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyPackageCnct {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("package_id")
    @Expose
    var packageId: String? = null

    @SerializedName("assoc_id")
    @Expose
    var assocId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("business_id")
    @Expose
    var businessId: String? = null

    @SerializedName("invoce_no")
    @Expose
    var invoceNo: String? = null

    @SerializedName("package_name")
    @Expose
    var packageName: String? = null

    @SerializedName("payment_mode")
    @Expose
    var paymentMode: String? = null

    @SerializedName("payment_id")
    @Expose
    var paymentId: String? = null

    @SerializedName("validity")
    @Expose
    var validity: String? = null

    @SerializedName("wallet_amount")
    @Expose
    var walletAmount: String? = null

    @SerializedName("recharge_with_gst")
    @Expose
    var rechargeWithGst: String? = null

    @SerializedName("lead_id")
    @Expose
    var leadId: String? = null

    @SerializedName("lead_price")
    @Expose
    var leadPrice: String? = null

    @SerializedName("listing")
    @Expose
    var listing: String? = null

    @SerializedName("transaction_status")
    @Expose
    var transactionStatus: String? = null

    @SerializedName("comments")
    @Expose
    var comments: String? = null

    @SerializedName("transaction_mode")
    @Expose
    var transactionMode: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("net_amt")
    @Expose
    var netAmt: String? = null

    @SerializedName("gross_amt")
    @Expose
    var grossAmt: String? = null

    @SerializedName("earn_comission")
    @Expose
    var earnComission: String? = null

    @SerializedName("earn_sellfee")
    @Expose
    var earnSellfee: String? = null

    @SerializedName("earn_gst")
    @Expose
    var earnGst: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("options")
    @Expose
    var options: String? = null

    @SerializedName("feedback")
    @Expose
    var feedback: String? = null

    @SerializedName("expiry_at")
    @Expose
    var expiryAt: String? = null

    @SerializedName("is_free")
    @Expose
    var isFree: String? = null
}