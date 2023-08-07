package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VotesItem : Serializable {
    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("pool_id")
    var poolId: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("video_id")
    var videoId: String? = null

    @SerializedName("status")
    var status: String? = null
}