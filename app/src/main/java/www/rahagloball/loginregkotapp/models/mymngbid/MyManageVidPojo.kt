package www.rahagloball.loginregkotapp.models.mymngbid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyManageVidPojo : Serializable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<MyManageVid>? = null
}