package www.rahagloball.loginregkotapp.models.watchistry

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WtchHstryView : Serializable {
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

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("ip_address")
    @Expose
    var ip_address: String? = null
}