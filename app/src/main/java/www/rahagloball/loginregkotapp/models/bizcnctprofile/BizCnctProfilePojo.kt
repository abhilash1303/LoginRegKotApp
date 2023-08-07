package www.rahagloball.loginregkotapp.models.bizcnctprofile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BizCnctProfilePojo {
    @SerializedName("view_profile")
    @Expose
    var viewProfile: List<ViewProfileItem>? = null
}