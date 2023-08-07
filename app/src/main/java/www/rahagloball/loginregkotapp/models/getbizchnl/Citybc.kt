package www.rahagloball.loginregkotapp.models.getbizchnl

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Citybc {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("state_id")
    @Expose
    var stateId: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}