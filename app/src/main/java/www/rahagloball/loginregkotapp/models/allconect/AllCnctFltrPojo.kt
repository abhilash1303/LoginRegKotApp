package www.rahagloball.loginregkotapp.models.allconect

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllCnctFltrPojo {
    @SerializedName("data")
    @Expose
    var data: List<ConnctData>? = null
}