package www.rahagloball.loginregkotapp.models.mychanldtls

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyChnlDtlsPojo : Serializable {
    @SerializedName("course")
    @Expose
    var course: List<CourseMychDtls>? = null

    @SerializedName("cuties")
    @Expose
    var cuties: List<MyChDtlsCuty>? = null

    @SerializedName("subscribedData")
    @Expose
    var subscribedData: List<SubscribedDatum>? = null

    @SerializedName("supportcount")
    @Expose
    var supportcount: String? = null

    @SerializedName("channel_title")
    @Expose
    var channelTitle: String? = null

    @SerializedName("channel_description")
    @Expose
    var channelDescription: String? = null

    @SerializedName("videos")
    @Expose
    var videos: MyChDtlsVideos? = null
}