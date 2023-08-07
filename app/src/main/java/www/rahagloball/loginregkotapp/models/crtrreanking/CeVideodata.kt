package www.rahagloball.loginregkotapp.models.crtrreanking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CeVideodata : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("channel_id")
    @Expose
    var channelId: String? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("preview_image")
    @Expose
    var previewImage: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("visible")
    @Expose
    var visible: String? = null

    @SerializedName("keywords")
    @Expose
    var keywords: String? = null

    @SerializedName("video_category")
    @Expose
    var videoCategory: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("sub_category")
    @Expose
    var subCategory: String? = null

    @SerializedName("preview_video")
    @Expose
    var previewVideo: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("duration")
    @Expose
    var duration: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("reasonToReject")
    @Expose
    var reasonToReject: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("comments_count")
    @Expose
    var commentsCount: String? = null

    @SerializedName("views_count")
    @Expose
    var viewsCount: String? = null

    @SerializedName("votes_count")
    @Expose
    var votesCount: String? = null

    @SerializedName("comments")
    @Expose
    var comments: List<CrComment>? = null

    @SerializedName("views")
    @Expose
    var views: List<CrViews>? = null

    @SerializedName("votes")
    @Expose
    var votes: List<CrVotes>? = null

    @SerializedName("channel")
    @Expose
    var channel: CrChannel? = null
}