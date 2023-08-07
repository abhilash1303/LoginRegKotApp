package www.rahagloball.loginregkotapp.models.issprtcheck

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SspCheck {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("channel_id")
    @Expose
    var channelId: String? = null

    @SerializedName("plan_type")
    @Expose
    var planType: String? = null

    @SerializedName("payment_method")
    @Expose
    var paymentMethod: String? = null

    @SerializedName("plan_amount")
    @Expose
    var planAmount: String? = null

    @SerializedName("processing_fee")
    @Expose
    var processingFee: String? = null

    @SerializedName("topay_amount")
    @Expose
    var topayAmount: String? = null

    @SerializedName("plan_date")
    @Expose
    var planDate: String? = null

    @SerializedName("plan_expires_at")
    @Expose
    var planExpiresAt: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}