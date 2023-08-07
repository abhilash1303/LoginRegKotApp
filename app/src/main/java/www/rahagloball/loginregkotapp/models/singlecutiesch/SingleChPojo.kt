package www.rahagloball.loginregkotapp.models.singlecutiesch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SingleChPojo : Serializable {
    @SerializedName("data")
    @Expose
    var data: List<SingleCutiesCh>? = null
}