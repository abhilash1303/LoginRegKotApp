package www.rahagloball.loginregkotapp.models.crsscnvidlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CrsSctnVidllistPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<CrsSctnVidllist>? = null
}