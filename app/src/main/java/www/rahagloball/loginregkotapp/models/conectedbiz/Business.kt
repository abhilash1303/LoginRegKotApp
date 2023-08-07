package www.rahagloball.loginregkotapp.models.conectedbiz

import com.google.gson.annotations.SerializedName

class Business {
    @SerializedName("company_type")
    var companyType: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("longnitude")
    var longnitude: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("subcategory_id")
    var subcategoryId: String? = null

    @SerializedName("category_id")
    var categoryId: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("business_description")
    var businessDescription: String? = null

    @SerializedName("verify")
    var verify: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("banner_image")
    var bannerImage: String? = null

    @SerializedName("establish_date")
    var establishDate: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("business_name")
    var businessName: String? = null

    @SerializedName("pincode")
    var pincode: String? = null

    @SerializedName("website")
    var website: String? = null

    @SerializedName("owner_name")
    var ownerName: String? = null

    @SerializedName("address")
    var address: String? = null

    @SerializedName("lattitude")
    var lattitude: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("alternate_number")
    var alternateNumber: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("landline")
    var landline: String? = null

    @SerializedName("subcategories")
    var subcategories: Subcategories? = null

    @SerializedName("status")
    var status: String? = null
}