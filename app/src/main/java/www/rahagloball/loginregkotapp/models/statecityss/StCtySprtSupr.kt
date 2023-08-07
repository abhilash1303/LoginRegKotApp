package www.rahagloball.loginregkotapp.models.statecityss

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StCtySprtSupr {
    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("support_percentage")
    @Expose
    var supportPercentage: String? = null

    @SerializedName("super_support_percentage")
    @Expose
    var superSupportPercentage: String? = null

    @SerializedName("support_count")
    @Expose
    var supportCount: String? = null

    @SerializedName("super_support_count")
    @Expose
    var superSupportCount: String? = null
}