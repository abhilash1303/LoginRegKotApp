package www.rahagloball.loginregkotapp.models.bzchexits

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BizChExistsPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("is_exist")
    @Expose
    var isExist: Boolean? = null

    @SerializedName("data")
    @Expose
    var data: BizChExists? = null
}