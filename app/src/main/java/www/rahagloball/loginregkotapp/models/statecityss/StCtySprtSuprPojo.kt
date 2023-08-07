package www.rahagloball.loginregkotapp.models.statecityss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StCtySprtSuprPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<StCtySprtSupr>? = null
}