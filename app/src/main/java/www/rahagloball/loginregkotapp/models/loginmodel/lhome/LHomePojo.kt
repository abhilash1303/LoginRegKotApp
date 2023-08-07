package www.rahagloball.loginregkotapp.models.loginmodel.lhome

import com.google.gson.annotations.SerializedName

class LHomePojo {
    @SerializedName("access_token")
    var accessToken: String? = null

    @SerializedName("expires_at")
    var expiresAt: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("token_type")
    var tokenType: String? = null

    @SerializedName("result")
    var result: String? = null
}
