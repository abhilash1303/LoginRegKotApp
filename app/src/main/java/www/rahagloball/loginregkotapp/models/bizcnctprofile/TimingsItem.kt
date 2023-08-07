package www.rahagloball.loginregkotapp.models.bizcnctprofile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TimingsItem {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("business_id")
    @Expose
    var businessId: String? = null

    @SerializedName("day")
    @Expose
    var day: String? = null

    @SerializedName("start_time")
    @Expose
    var startTime: String? = null

    @SerializedName("end_time")
    @Expose
    var endTime: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}