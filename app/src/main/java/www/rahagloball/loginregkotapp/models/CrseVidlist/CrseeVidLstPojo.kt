package www.rahagloball.loginregkotapp.models.CrseVidlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CrseeVidLstPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<CrseeVidLst>? = null
}