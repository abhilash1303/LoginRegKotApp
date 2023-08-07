package www.rahagloball.loginregkotapp.models.getbizchnl

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Statebc {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("country_id")
    @Expose
    var countryId: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}