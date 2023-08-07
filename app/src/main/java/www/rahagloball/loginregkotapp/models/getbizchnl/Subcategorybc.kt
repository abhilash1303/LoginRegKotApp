package www.rahagloball.loginregkotapp.models.getbizchnl

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Subcategorybc {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null
}