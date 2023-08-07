package www.rahagloball.loginregkotapp.models.cutiesss

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItemCute : Serializable {
    @SerializedName("thumbnail")
    var thumbnail: String? = null

    @SerializedName("preview_image")
    var previewImage: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("video")
    var video: String? = null

    @SerializedName("pool_id")
    var poolId: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("duration")
    var duration: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("views_count")
    var viewsCount: String? = null

    @SerializedName("channel_id")
    var channelId: String? = null

    @SerializedName("status")
    var status: String? = null
}