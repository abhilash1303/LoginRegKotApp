package www.rahagloball.loginregkotapp.models.allannce

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllAnnouncmntPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<AllAnnouncmnt>? = null
}