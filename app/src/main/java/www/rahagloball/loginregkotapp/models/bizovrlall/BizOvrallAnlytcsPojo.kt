package www.rahagloball.loginregkotapp.models.bizovrlall

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BizOvrallAnlytcsPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<BizOvrallAnlytcs>? = null
}