package www.rahagloball.loginregkotapp.models.chctsfront

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CtsSingleChPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<FrntCtsChId>? = null
}