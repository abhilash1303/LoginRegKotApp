package www.rahagloball.loginregkotapp.models.allques

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InstrctrQuesPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("questions")
    @Expose
    var questions: List<QuestionAll>? = null
}