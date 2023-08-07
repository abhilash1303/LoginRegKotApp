package www.rahagloball.loginregkotapp.models.LrnSrchVid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LearnSearchPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<SearchVideo>? = null
}