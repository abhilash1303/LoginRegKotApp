package www.rahagloball.loginregkotapp.models.bizcnctprofile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CatalogItem {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("business_id")
    @Expose
    var businessId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("preview_image")
    @Expose
    var previewImage: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("decription")
    @Expose
    var decription: String? = null

    @SerializedName("attach_file")
    @Expose
    var attachFile: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("pdct_one")
    @Expose
    var pdctOne: String? = null

    @SerializedName("pdct_one_range")
    @Expose
    var pdctOneRange: String? = null

    @SerializedName("pdct_two")
    @Expose
    var pdctTwo: String? = null

    @SerializedName("pdct_two_range")
    @Expose
    var pdctTwoRange: String? = null

    @SerializedName("pdct_three")
    @Expose
    var pdctThree: String? = null

    @SerializedName("pdct_three_range")
    @Expose
    var pdctThreeRange: String? = null

    @SerializedName("pdct_four")
    @Expose
    var pdctFour: String? = null

    @SerializedName("pdct_four_range")
    @Expose
    var pdctFourRange: String? = null

    @SerializedName("pdct_five")
    @Expose
    var pdctFive: String? = null

    @SerializedName("pdct_five_range")
    @Expose
    var pdctFiveRange: String? = null

    @SerializedName("pdct_six")
    @Expose
    var pdctSix: String? = null

    @SerializedName("pdct_six_range")
    @Expose
    var pdctSixRange: String? = null

    @SerializedName("restriction")
    @Expose
    var restriction: String? = null
}