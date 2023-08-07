package www.rahagloball.loginregkotapp.models.ltstvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LtstVidPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<LtstVidDatum>? = null
}