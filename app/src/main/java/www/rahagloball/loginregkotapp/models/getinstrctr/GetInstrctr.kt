package www.rahagloball.loginregkotapp.models.getinstrctr

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetInstrctr {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("fname")
    @Expose
    var fname: String? = null

    @SerializedName("lname")
    @Expose
    var lname: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("detail")
    @Expose
    var detail: String? = null

    @SerializedName("file")
    @Expose
    var file: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}