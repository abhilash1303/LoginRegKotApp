package www.rahagloball.loginregkotapp.models.catgryy

import com.google.gson.annotations.SerializedName

class DataItem {
    @SerializedName("cat_image")
    var catImage: String? = null

    @SerializedName("featured")
    var featured: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("maincat_id")
    var maincatId: String? = null

    @SerializedName("icon")
    var icon: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("position")
    var position: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("status")
    var status: String? = null
}