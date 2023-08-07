package www.rahagloball.loginregkotapp.models.statecitymodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class States(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("country_id")
    val country_id: Long?,
    @SerializedName("country_code")
    val country_code: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("slug")
    val slug: String?,
) : Serializable

//class States : Serializable {
//    @SerializedName("id")
//    var id: Long = 0
//
//    @SerializedName("name")
//    var name: String? = null
//
//    @SerializedName("country_id")
//    var country_id: Long = 0
//
//    @SerializedName("country_code")
//    var country_code: String? = null
//
//    @SerializedName("type")
//    var type: String? = null
//
//    @SerializedName("slug")
//    var slug: String? = null
//
//    constructor() {}
//    constructor(
//        id: Long,
//        name: String?,
//        country_id: Long,
//        country_code: String?,
//        type: String?,
//        slug: String?
//    ) {
//        this.id = id
//        this.name = name
//        this.country_id = country_id
//        this.country_code = country_code
//        this.type = type
//        this.slug = slug
//    }
//}