package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City  (
    @SerializedName("citybl")
    val cities:ArrayList<Cities>

):Serializable

