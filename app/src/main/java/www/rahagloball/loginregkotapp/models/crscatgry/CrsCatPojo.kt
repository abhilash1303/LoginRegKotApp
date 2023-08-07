package www.rahagloball.loginregkotapp.models.crscatgry

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CrsCatPojo {
    @SerializedName("data")
    @Expose
    var data: List<CrsCat>? = null
}