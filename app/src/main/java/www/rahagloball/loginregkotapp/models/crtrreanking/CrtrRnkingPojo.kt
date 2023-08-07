package www.rahagloball.loginregkotapp.models.crtrreanking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CrtrRnkingPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<CrDatum>? = null
}