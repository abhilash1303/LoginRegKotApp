package www.rahagloball.loginregkotapp.models.conectedbiz

import com.google.gson.annotations.SerializedName

class ConnectlistItem {
    @SerializedName("business")
    var business: Business? = null

    @SerializedName("demand_duration")
    var demandDuration: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("budget_after_commission")
    var budgetAfterCommission: String? = null

    @SerializedName("requirement")
    var requirement: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("subcategory_id")
    var subcategoryId: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("lead_type")
    var leadType: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("business_id")
    var businessId: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("budget")
    var budget: String? = null

    @SerializedName("status")
    var status: String? = null
}