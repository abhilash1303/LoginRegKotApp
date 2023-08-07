package www.rahagloball.loginregkotapp.models.getblogs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetBlogsPojo {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<GetBlogs>? = null
}