package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubcommentVid : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("comment_id")
    @Expose
    var commentId: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("video_id")
    @Expose
    var videoId: String? = null

    @SerializedName("creator_id")
    @Expose
    var creatorId: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
}