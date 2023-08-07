package www.rahagloball.loginregkotapp.models.ctgry

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryNl(
    @SerializedName("data")
    val categories: ArrayList<CategiesNl>?
) : Serializable
