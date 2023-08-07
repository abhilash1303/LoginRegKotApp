package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName

data class CountriesKotlin(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nicename")
    val nicename: String?
)