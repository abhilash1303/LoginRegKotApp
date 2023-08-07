package www.rahagloball.loginregkotapp.models.updtprofl

import com.google.gson.annotations.SerializedName

class ProfileItem {
    @SerializedName("pincode")
    var pincode: String? = null

    @SerializedName("address")
    var address: String? = null

    @SerializedName("occupation")
    var occupation: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("married_status")
    var marriedStatus: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("user-image")
    var userImage: String? = null

    @SerializedName("subcategory_id")
    var subcategoryId: String? = null

    @SerializedName("category_id")
    var categoryId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("gender")
    var gnederr: String? = null

    @SerializedName("age")
    var age: String? = null
}