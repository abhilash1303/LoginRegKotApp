package www.rahagloball.loginregkotapp.models.sectiontile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SectionTitlePojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<SectionTitle>? = null
}