package www.rahagloball.loginregkotapp.models.alltranxx

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllTranxPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<AllTranxx>? = null
}