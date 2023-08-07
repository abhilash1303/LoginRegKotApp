package www.rahagloball.loginregkotapp.models.watchistry

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WtchHstryVote : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("video_id")
    @Expose
    var videoId: Int? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: Any? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: Any? = null
}