package www.rahagloball.loginregkotapp.models.mysinglecrs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MySingleCoursePojo {
    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("data")
    @Expose
    var data: MySingleCourse? = null
}