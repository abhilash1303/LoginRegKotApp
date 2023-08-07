package www.rahagloball.loginregkotapp.models.crseinfonew

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CrsInfoNewPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<DatumCrsInfo>? = null
}