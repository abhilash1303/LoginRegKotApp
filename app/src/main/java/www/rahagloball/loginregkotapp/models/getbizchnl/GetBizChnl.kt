package www.rahagloball.loginregkotapp.models.getbizchnl

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetBizChnl {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("channel_name")
    @Expose
    var channelName: String? = null

    @SerializedName("biz_name")
    @Expose
    var bizName: String? = null

    @SerializedName("about_biz")
    @Expose
    var aboutBiz: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: String? = null

    @SerializedName("state_id")
    @Expose
    var stateId: String? = null

    @SerializedName("city_id")
    @Expose
    var cityId: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("deleted_at")
    @Expose
    var deletedAt: String? = null

    @SerializedName("company_type")
    @Expose
    var companyType: String? = null

    @SerializedName("authorised_person")
    @Expose
    var authorisedPerson: String? = null

    @SerializedName("pan_number")
    @Expose
    var panNumber: String? = null

    @SerializedName("gstin_number")
    @Expose
    var gstinNumber: String? = null

    @SerializedName("official_email")
    @Expose
    var officialEmail: String? = null

    @SerializedName("country_id")
    @Expose
    var countryId: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("category")
    @Expose
    var category: Categorybc? = null

    @SerializedName("subcategory")
    @Expose
    var subcategory: Subcategorybc? = null

    @SerializedName("state")
    @Expose
    var state: Statebc? = null

    @SerializedName("city")
    @Expose
    var city: Citybc? = null
}