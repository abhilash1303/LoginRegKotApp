package www.rahagloball.loginregkotapp.models.relvidsubcat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RelatedVidPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<RelVid>? = null
}