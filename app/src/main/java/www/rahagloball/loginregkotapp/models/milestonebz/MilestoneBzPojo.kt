package www.rahagloball.loginregkotapp.models.milestonebz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MilestoneBzPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<MilestoneBz>? = null
}