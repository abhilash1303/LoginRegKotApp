package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CommentsItem : Serializable {
    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("pool")
    var pool: Pool? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("pool_id")
    var poolId: String? = null

    @SerializedName("body")
    var body: String? = null

    @SerializedName("video_id")
    var videoId: String? = null

    @SerializedName("subcomment")
    var subcomment: List<SubcommentVid>? = null
}