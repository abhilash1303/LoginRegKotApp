package www.rahagloball.loginregkotapp.models.milestone

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Milestone : Serializable {
    @SerializedName("single_cutie_data")
    @Expose
    var singleCutieData: SingleCutieData? = null

    @SerializedName("single_video_data")
    @Expose
    var singleVideoData: SingleVideoData? = null

    @SerializedName("views_count_on_total_videos")
    @Expose
    var viewsCountOnTotalVideos: Int? = null

    @SerializedName("views_count_on_total_cuties")
    @Expose
    var viewsCountOnTotalCuties: Int? = null

    @SerializedName("milestone")
    @Expose
    var milestone: Milestone_Ss? = null
}