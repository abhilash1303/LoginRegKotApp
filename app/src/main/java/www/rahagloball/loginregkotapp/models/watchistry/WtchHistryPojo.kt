package www.rahagloball.loginregkotapp.models.watchistry

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WtchHistryPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("videos")
    @Expose
    var videos: List<WtchHstryVideo>? = null
}