package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable {
    @SerializedName("login_status")
    var loginStatus: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("channel")
    var channel: Channel? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("video")
    var video: Video? = null

    @SerializedName("status")
    var status: String? = null
}