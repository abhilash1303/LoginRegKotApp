package www.rahagloball.loginregkotapp.models.milestone

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SingleVideoData : Serializable {
    @SerializedName("highest_views_count")
    @Expose
    var highestViewsCount: Int? = null

    @SerializedName("video_detail")
    @Expose
    var videoDetail: VideoDetail? = null
}