package www.rahagloball.loginregkotapp.models.ctgry

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubCategoryNl(
    @SerializedName("data")
    val subcategories: ArrayList<SubCategiesNl>?
) : Serializable
