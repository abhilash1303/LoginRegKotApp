package www.rahagloball.loginregkotapp.models.ctgry

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategiesNl(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("title")
    val name: String?,
):Serializable
