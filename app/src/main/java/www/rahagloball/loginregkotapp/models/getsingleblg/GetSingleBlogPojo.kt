package www.rahagloball.loginregkotapp.models.getsingleblg

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetSingleBlogPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: GetSingleBlog? = null
}