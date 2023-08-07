package www.rahagloball.loginregkotapp.models.suprtrlistt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Supporter : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: String? = null

    @SerializedName("channel_id")
    @Expose
    var channelId: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("channel")
    @Expose
    var channel: Channel_Support? = null
}