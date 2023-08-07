package www.rahagloball.loginregkotapp.models.ovralanlyts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OvralAnlytcsPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<OvralAnlytcs>? = null
}