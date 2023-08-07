package www.rahagloball.loginregkotapp.models.milestonebz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Milestone_Ss_Bz : Serializable {
    @SerializedName("total_supporters")
    @Expose
    var totalSupporters: Int? = null

    @SerializedName("total_super_supporters")
    @Expose
    var totalSuperSupporters: Int? = null

    @SerializedName("total_videos_count")
    @Expose
    var totalVideosCount: Int? = null

    @SerializedName("total_cuties_count")
    @Expose
    var totalCutiesCount: Int? = null
}