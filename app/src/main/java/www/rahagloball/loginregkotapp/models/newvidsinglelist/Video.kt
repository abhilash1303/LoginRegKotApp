package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Video : Serializable {
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

    @SerializedName("preview_video")
    var preview_videoo: String? = null

    @SerializedName("category")
    var vid_catid: String? = null

    @SerializedName("sub_category")
    var vid_subcatid: String? = null

    @SerializedName("video_category")
    var vid_cat: String? = null

    @SerializedName("keywords")
    var keywordss: String? = null

    @SerializedName("upload_time")
    var upload_timee: String? = null

    @SerializedName("visible")
    var visiblestr: String? = null
}