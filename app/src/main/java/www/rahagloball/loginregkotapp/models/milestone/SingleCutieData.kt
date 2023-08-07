package www.rahagloball.loginregkotapp.models.milestone

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SingleCutieData : Serializable {
    @SerializedName("highest_views_count")
    @Expose
    var highestViewsCount: Int? = null

    @SerializedName("cutie_detail")
    @Expose
    var cutieDetail: CutieDetail? = null
}