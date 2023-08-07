package www.rahagloball.loginregkotapp.models.mysavedvidss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SavedVidPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("videos")
    @Expose
    var videos: List<SvdVideo>? = null
}