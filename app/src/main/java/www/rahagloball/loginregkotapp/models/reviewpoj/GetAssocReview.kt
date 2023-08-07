package www.rahagloball.loginregkotapp.models.reviewpoj

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetAssocReview {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("business_id")
    @Expose
    var businessId: String? = null

    @SerializedName("review")
    @Expose
    var review: String? = null

    @SerializedName("rating")
    @Expose
    var rating: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("user")
    @Expose
    var user: RvwUser? = null
}
