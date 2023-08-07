package www.rahagloball.loginregkotapp.models.cffwalt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CfDataWalt {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("balance")
    @Expose
    var balance: String? = null

    @SerializedName("min_balance")
    @Expose
    var minBalance: String? = null

    @SerializedName("last_recharge")
    @Expose
    var lastRecharge: String? = null

    @SerializedName("active")
    @Expose
    var active: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}