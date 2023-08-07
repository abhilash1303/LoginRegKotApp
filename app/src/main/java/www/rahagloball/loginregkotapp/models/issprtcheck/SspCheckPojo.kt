package www.rahagloball.loginregkotapp.models.issprtcheck

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SspCheckPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: SspCheck? = null
}