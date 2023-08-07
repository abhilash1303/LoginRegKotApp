package www.rahagloball.loginregkotapp.models.osalvds

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AllVidsPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("allVideos")
    @Expose
    var allVideos: String? = null

    @SerializedName("video_count")
    @Expose
    var videoCount: String? = null

    @SerializedName("cuties_count")
    @Expose
    var cutiesCount: String? = null

    @SerializedName("totlaviews")
    @Expose
    var totlaviews: String? = null

    @SerializedName("latestVideos")
    @Expose
    var latestVideos: String? = null

    @SerializedName("highest_views")
    @Expose
    var highestViews: String? = null

    @SerializedName("trtitle")
    @Expose
    var trtitle: Trtitle? = null
}