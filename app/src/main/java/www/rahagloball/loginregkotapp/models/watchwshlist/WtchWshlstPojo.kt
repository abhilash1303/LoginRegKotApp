package www.rahagloball.loginregkotapp.models.watchwshlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WtchWshlstPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("videos")
    @Expose
    var videos: List<WtchWshLstVideo>? = null
}