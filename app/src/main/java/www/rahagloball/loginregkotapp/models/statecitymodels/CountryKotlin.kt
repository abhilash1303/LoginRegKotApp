package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryKotlin(
    @SerializedName("countriesbl")
    val countries: ArrayList<CountriesKotlin>?
): Serializable
