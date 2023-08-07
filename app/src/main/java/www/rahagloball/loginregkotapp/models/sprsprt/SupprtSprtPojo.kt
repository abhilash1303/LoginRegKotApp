package www.rahagloball.loginregkotapp.models.sprsprt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SupprtSprtPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: DataSs? = null
}