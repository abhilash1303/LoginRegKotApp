package www.rahagloball.loginregkotapp.models.crseinfonew

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DatumCrsInfo : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: String? = null

    @SerializedName("childcategory_id")
    @Expose
    var childcategoryId: String? = null

    @SerializedName("language_id")
    @Expose
    var languageId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("short_detail")
    @Expose
    var shortDetail: String? = null

    @SerializedName("detail")
    @Expose
    var detail: String? = null

    @SerializedName("requirement")
    @Expose
    var requirement: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("discount_price")
    @Expose
    var discountPrice: String? = null

    @SerializedName("day")
    @Expose
    var day: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("featured")
    @Expose
    var featured: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("preview_image")
    @Expose
    var previewImage: String? = null

    @SerializedName("video_url")
    @Expose
    var videoUrl: String? = null

    @SerializedName("preview_type")
    @Expose
    var previewType: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("duration")
    @Expose
    var duration: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("duration_type")
    @Expose
    var durationType: String? = null

    @SerializedName("instructor_revenue")
    @Expose
    var instructorRevenue: String? = null

    @SerializedName("involvement_request")
    @Expose
    var involvementRequest: String? = null

    @SerializedName("refund_policy_id")
    @Expose
    var refundPolicyId: String? = null

    @SerializedName("level_tags")
    @Expose
    var levelTags: String? = null

    @SerializedName("assignment_enable")
    @Expose
    var assignmentEnable: String? = null

    @SerializedName("appointment_enable")
    @Expose
    var appointmentEnable: String? = null

    @SerializedName("certificate_enable")
    @Expose
    var certificateEnable: String? = null

    //    private ArrayList<Details> details = new ArrayList<Details>();
    @SerializedName("course_tags")
    @Expose
    var courseTags: Any? = null
        get() = if (field is String) {
            val tags: MutableList<String> =
                ArrayList()
            tags.add(field as String)
            tags
        } else {
            field as List<String>?
        }

    @SerializedName("include")
    @Expose
    var include: List<IncludeItemCi>? = null

    @SerializedName("whatlearns")
    @Expose
    var whatlearns: List<WhatlearnsCi>? = null

    @SerializedName("review")
    @Expose
    var review: List<ReviewCi>? = null
}