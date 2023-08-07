package www.rahagloball.loginregkotapp.models.mychvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyChVidpojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<MyChVideos>? = null
}