package www.rahagloball.loginregkotapp.models.sectiontile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SectionTitle {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("is_delete")
    @Expose
    var isDelete: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}