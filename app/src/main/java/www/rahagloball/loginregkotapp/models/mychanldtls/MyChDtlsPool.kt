package www.rahagloball.loginregkotapp.models.mychanldtls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyChDtlsPool : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("business_id")
    @Expose
    var businessId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("intrested_area")
    @Expose
    var intrestedArea: String? = null

    @SerializedName("login_status")
    @Expose
    var loginStatus: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("monthly_price")
    @Expose
    var monthlyPrice: String? = null

    @SerializedName("monthly_benefit")
    @Expose
    var monthlyBenefit: String? = null

    @SerializedName("halfy_price")
    @Expose
    var halfyPrice: String? = null

    @SerializedName("halfy_benefit")
    @Expose
    var halfyBenefit: String? = null

    @SerializedName("yearly_price")
    @Expose
    var yearlyPrice: String? = null

    @SerializedName("yearly_benefit")
    @Expose
    var yearlyBenefit: String? = null

    @SerializedName("is_ssenable")
    @Expose
    var isSsenable: String? = null

    @SerializedName("user")
    @Expose
    var user: MyChDtlsUser? = null
}