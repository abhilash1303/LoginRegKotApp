package www.rahagloball.loginregkotapp.models.bizch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BizChanPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null
}