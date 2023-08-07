package www.rahagloball.loginregkotapp.models.getchanldata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetChanlPojo {
    @SerializedName("data")
    @Expose
    var data: List<DataItem>? = null
}