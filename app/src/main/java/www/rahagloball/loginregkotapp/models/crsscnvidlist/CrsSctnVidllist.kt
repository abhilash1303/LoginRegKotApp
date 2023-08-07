package www.rahagloball.loginregkotapp.models.crsscnvidlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CrsSctnVidllist {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("c_section_id")
    @Expose
    private var cSectionId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("paid_or_overview")
    @Expose
    var paidOrOverview: String? = null

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
    fun getcSectionId(): String? {
        return cSectionId
    }

    fun setcSectionId(cSectionId: String?) {
        this.cSectionId = cSectionId
    }
}