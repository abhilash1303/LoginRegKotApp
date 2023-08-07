package www.rahagloball.loginregkotapp.models.lrndtls_new

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CourseDtlsPojo {
    @SerializedName("data")
    @Expose
    var data: List<DatumCd>? = null
}