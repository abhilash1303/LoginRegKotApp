package www.rahagloball.loginregkotapp.models.newvidsinglelist

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Channel : Serializable {
    @SerializedName("supports_count")
    var supportsCount: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("pool_id")
    var poolId: String? = null

    @SerializedName("status")
    var status: String? = null
}