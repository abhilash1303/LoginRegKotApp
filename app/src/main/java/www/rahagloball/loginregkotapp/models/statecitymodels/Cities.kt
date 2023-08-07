package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName


data class Cities(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
)

//class Cities : Serializable {
//    @SerializedName("id")
//    var id: Long = 0
//
//    @SerializedName("name")
//    var name: String? = null
//
//    constructor() {}
//    constructor(id: Long, name: String?) {
//        this.id = id
//        this.name = name
//    }
//}