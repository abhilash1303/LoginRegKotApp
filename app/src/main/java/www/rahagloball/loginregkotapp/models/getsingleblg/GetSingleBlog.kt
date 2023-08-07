package www.rahagloball.loginregkotapp.models.getsingleblg

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetSingleBlog {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}