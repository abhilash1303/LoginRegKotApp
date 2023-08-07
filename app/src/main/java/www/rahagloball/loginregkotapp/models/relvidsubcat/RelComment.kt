package www.rahagloball.loginregkotapp.models.relvidsubcat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RelComment : Serializable {
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

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: Any? = null

    @SerializedName("pool")
    @Expose
    var pool: RelPool? = null
}