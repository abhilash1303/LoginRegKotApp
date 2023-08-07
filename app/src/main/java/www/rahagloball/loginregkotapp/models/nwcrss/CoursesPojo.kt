package www.rahagloball.loginregkotapp.models.nwcrss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoursesPojo {
    @SerializedName("data")
    @Expose
    var data: List<Courseslist>? = null
}