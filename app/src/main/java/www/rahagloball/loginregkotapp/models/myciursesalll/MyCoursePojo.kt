package www.rahagloball.loginregkotapp.models.myciursesalll

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyCoursePojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<MyCourseAll>? = null
}