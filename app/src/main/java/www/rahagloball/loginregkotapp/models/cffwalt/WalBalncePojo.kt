package www.rahagloball.loginregkotapp.models.cffwalt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WalBalncePojo {
    @SerializedName("staus")
    @Expose
    var staus: String? = null

    @SerializedName("data")
    @Expose
    var data: CfDataWalt? = null
}