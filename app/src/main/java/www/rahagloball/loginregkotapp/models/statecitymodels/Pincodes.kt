package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Pincodes(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("pincode")
    val pincode: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("office_name")
    val office_name: String?
) : Serializable


//class Pincodes(
//    @field:SerializedName("id") var id: Long,
//    @field:SerializedName(
//        "pincode"
//    ) var pincode: String,
//    @field:SerializedName("status") var status: String,
//    @field:SerializedName(
//        "office_name"
//    ) var office_name: String
//) : Serializable