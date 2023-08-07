package www.rahagloball.loginregkotapp.models.allques

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionAll {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("instructor_id")
    @Expose
    var instructorId: String? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("answer")
    @Expose
    var answer: String? = null

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