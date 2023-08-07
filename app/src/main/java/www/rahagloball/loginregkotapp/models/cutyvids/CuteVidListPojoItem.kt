package www.rahagloball.loginregkotapp.models.cutyvids

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CuteVidListPojoItem : Serializable {
    @SerializedName("thumbnail")
    var thumbnail: String? = null

    @SerializedName("preview_image")
    var previewImage: String? = null

    @SerializedName("comments")
    var comments: List<CommentsItem>? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("video")
    lateinit var video: String

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

    @SerializedName("votes_count")
    var votesCount: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("comments_count")
    var commentsCount: String? = null

    @SerializedName("votes")
    var votes: List<VotesItem>? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("views_count")
    var viewsCount: String? = null

    @SerializedName("channel_id")
    var channelId: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("channel")
    @Expose
    var channel: CutChannel? = null
}