package www.rahagloball.loginregkotapp.models.allconect

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CnctState {
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

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null

    @SerializedName("fips_code")
    @Expose
    var fipsCode: String? = null

    @SerializedName("iso2")
    @Expose
    var iso2: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("flag")
    @Expose
    var flag: String? = null

    @SerializedName("wikiDataId")
    @Expose
    var wikiDataId: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}