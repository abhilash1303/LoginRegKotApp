package www.rahagloball.loginregkotapp.models.allannce

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllAnnouncmnt {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("announsment")
    @Expose
    var announsment: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}