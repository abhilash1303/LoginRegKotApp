package www.rahagloball.loginregkotapp.models.getbizchnl

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetBizChnlPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: GetBizChnl? = null
}