package www.rahagloball.loginregkotapp.models.mysprtsvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MySprtCmnts : Serializable {
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

    @SerializedName("body")
    @Expose
    var body: String? = null

    @SerializedName("reply_data")
    @Expose
    var replyData: Any? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: Any? = null
}