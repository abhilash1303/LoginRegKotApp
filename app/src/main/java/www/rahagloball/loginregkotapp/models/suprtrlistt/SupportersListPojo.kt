package www.rahagloball.loginregkotapp.models.suprtrlistt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SupportersListPojo : Serializable {
    @SerializedName("supporter-list")
    @Expose
    var supporterList: List<Supporter>? = null
}