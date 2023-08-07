package www.rahagloball.loginregkotapp.models.mymngvidsingle

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MngVidSingleCat : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("top_cat_id")
    @Expose
    var topCatId: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("featured")
    @Expose
    var featured: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("position")
    @Expose
    var position: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("cat_image")
    @Expose
    var catImage: String? = null

    @SerializedName("upload_category_image")
    @Expose
    var uploadCategoryImage: String? = null

    @SerializedName("maincat_id")
    @Expose
    var maincatId: String? = null
}