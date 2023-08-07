package www.rahagloball.loginregkotapp.models.mymngbid

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VideoCategory : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("cat_name")
    @Expose
    var catName: String? = null

    @SerializedName("pic")
    @Expose
    var pic: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("is_delete")
    @Expose
    var isDelete: Int? = null
}