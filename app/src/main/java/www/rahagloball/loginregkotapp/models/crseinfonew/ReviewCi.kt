package www.rahagloball.loginregkotapp.models.crseinfonew

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ReviewCi : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("learn")
    @Expose
    var learn: Int? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("value")
    @Expose
    var value: Int? = null

    @SerializedName("review")
    @Expose
    var review: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("approved")
    @Expose
    var approved: Int? = null

    @SerializedName("featured")
    @Expose
    var featured: Any? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}