package www.rahagloball.loginregkotapp.models.allconect

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConnctData {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("owner_name")
    @Expose
    var ownerName: String? = null

    @SerializedName("business_name")
    @Expose
    var businessName: String? = null

    @SerializedName("business_description")
    @Expose
    var businessDescription: String? = null

    @SerializedName("alternate_number")
    @Expose
    var alternateNumber: String? = null

    @SerializedName("landline")
    @Expose
    var landline: String? = null

    @SerializedName("state_id")
    @Expose
    var stateId: String? = null

    @SerializedName("lattitude")
    @Expose
    var lattitude: String? = null

    @SerializedName("longnitude")
    @Expose
    var longnitude: String? = null

    @SerializedName("city_id")
    @Expose
    var cityId: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("company_type")
    @Expose
    var companyType: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("verify")
    @Expose
    var verify: String? = null

    @SerializedName("establish_date")
    @Expose
    var establishDate: String? = null

    @SerializedName("banner_image")
    @Expose
    var bannerImage: String? = null

    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null

    @SerializedName("area_of_service")
    @Expose
    var areaOfService: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("other_subcat")
    @Expose
    var otherSubcat: String? = null

    @SerializedName("my_package")
    @Expose
    var myPackage: MyPackageCnct? = null

    @SerializedName("category")
    @Expose
    var category: CnctCategory? = null

    @SerializedName("subcategories")
    @Expose
    var subcategories: CnctSubCategory? = null

    @SerializedName("city")
    @Expose
    var city: CnctCity? = null

    @SerializedName("state")
    @Expose
    var state: CnctState? = null
}