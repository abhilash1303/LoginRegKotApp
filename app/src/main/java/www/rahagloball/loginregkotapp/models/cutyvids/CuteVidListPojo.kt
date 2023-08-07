package www.rahagloball.loginregkotapp.models.cutyvids

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CuteVidListPojo : Serializable {
    @SerializedName("data")
    lateinit var data: ArrayList<CuteVidListPojoItem>
}