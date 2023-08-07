package www.rahagloball.loginregkotapp.models.mysuprsprtsvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MySprSprtVidsPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("videos")
    @Expose
    var videos: List<MySuprSprtVidList>? = null
}