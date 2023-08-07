package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pincode(
    @SerializedName("pincode_list")
    val pincodes: ArrayList<Pincodes>?
): Serializable



//class Pincode(@field:SerializedName("pincode_list") var pincodes: ArrayList<Pincodes>) :
//    Serializable {
//
//    @SerializedName("pincode_list\\r\\n")
//    var a: String? = null
//
//}