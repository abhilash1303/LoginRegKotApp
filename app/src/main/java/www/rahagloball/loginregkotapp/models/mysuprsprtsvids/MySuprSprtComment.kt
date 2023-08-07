package www.rahagloball.loginregkotapp.models.mysuprsprtsvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MySuprSprtComment : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("video_id")
    @Expose
    var videoId: String? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

    @SerializedName("reply_data")
    @Expose
    var replyData: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}