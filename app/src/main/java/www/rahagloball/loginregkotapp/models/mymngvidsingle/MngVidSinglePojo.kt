package www.rahagloball.loginregkotapp.models.mymngvidsingle

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MngVidSinglePojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: MngVidSingle? = null
}