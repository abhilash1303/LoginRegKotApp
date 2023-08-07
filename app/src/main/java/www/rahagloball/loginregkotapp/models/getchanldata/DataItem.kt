package www.rahagloball.loginregkotapp.models.getchanldata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataItem {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("pool_id")
    @Expose
    var poolId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("City_name")
    @Expose
    var cityName: String? = null

    @SerializedName("channel_type")
    @Expose
    var channelType: String? = null

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
}