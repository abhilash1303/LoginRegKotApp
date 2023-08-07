package www.rahagloball.loginregkotapp.models.mysprtsvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MySprtVidPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("videos")
    @Expose
    var videos: List<MySprtVidList>? = null
}