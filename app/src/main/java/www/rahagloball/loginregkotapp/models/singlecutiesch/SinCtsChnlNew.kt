package www.rahagloball.loginregkotapp.models.singlecutiesch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SinCtsChnlNew : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("supports_count")
    @Expose
    var supportsCount: String? = null
}