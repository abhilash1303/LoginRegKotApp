package www.rahagloball.loginregkotapp.models.reviewpoj

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<GetAssocReview>? = null
}