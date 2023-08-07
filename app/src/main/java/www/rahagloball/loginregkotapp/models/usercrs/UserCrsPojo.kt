package www.rahagloball.loginregkotapp.models.usercrs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserCrsPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<UserCrs>? = null
}