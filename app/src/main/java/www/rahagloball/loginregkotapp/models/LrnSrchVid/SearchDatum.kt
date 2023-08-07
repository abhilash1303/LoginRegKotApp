package www.rahagloball.loginregkotapp.models.LrnSrchVid

import com.google.gson.annotations.SerializedName

class SearchDatum {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("category_id")
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    var subcategoryId: String? = null

    @SerializedName("childcategory_id")
    var childcategoryId: String? = null

    @SerializedName("language_id")
    var languageId: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("short_detail")
    var shortDetail: String? = null

    @SerializedName("detail")
    var detail: String? = null

    @SerializedName("requirement")
    var requirement: String? = null

    @SerializedName("price")
    var price: String? = null

    @SerializedName("discount_price")
    var discountPrice: String? = null

    @SerializedName("day")
    var day: String? = null

    @SerializedName("video")
    var video: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("featured")
    var featured: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("preview_image")
    var previewImage: String? = null

    @SerializedName("video_url")
    var videoUrl: String? = null

    @SerializedName("preview_type")
    var previewType: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("duration")
    var duration: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("duration_type")
    var durationType: String? = null

    @SerializedName("instructor_revenue")
    var instructorRevenue: String? = null

    @SerializedName("involvement_request")
    var involvementRequest: String? = null

    @SerializedName("refund_policy_id")
    var refundPolicyId: String? = null

    @SerializedName("level_tags")
    var levelTags: String? = null

    @SerializedName("assignment_enable")
    var assignmentEnable: String? = null

    @SerializedName("appointment_enable")
    var appointmentEnable: String? = null

    @SerializedName("certificate_enable")
    var certificateEnable: String? = null

    @SerializedName("course_tags")
    var courseTags: String? = null
}