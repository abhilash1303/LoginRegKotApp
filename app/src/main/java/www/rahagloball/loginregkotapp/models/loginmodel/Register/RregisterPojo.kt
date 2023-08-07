package www.rahagloball.loginregkotapp.models.loginmodel.Register

import com.google.gson.annotations.SerializedName

class RregisterPojo {

        @SerializedName("access_token")
        var accessToken: String? = null

        @SerializedName("expires_at")
        var expiresAt: String? = null

        @SerializedName("token_type")
        var tokenType: String? = null

        @SerializedName("user")
        var user: String? = null

        @SerializedName("result")
        var result: String? = null


}