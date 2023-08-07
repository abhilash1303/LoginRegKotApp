package www.rahagloball.loginregkotapp.models.suprtrlistt

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User_Support : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("fname")
    @Expose
    var fname: String? = null

    @SerializedName("lname")
    @Expose
    var lname: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("doa")
    @Expose
    var doa: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("married_status")
    @Expose
    var marriedStatus: String? = null

    @SerializedName("city_id")
    @Expose
    var cityId: String? = null

    @SerializedName("state_id")
    @Expose
    var stateId: String? = null

    @SerializedName("country_id")
    @Expose
    var countryId: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("pin_code")
    @Expose
    var pinCode: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("verified")
    @Expose
    var verified: String? = null

    @SerializedName("user_img")
    @Expose
    var userImg: String? = null

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("email_verified_at")
    @Expose
    var emailVerifiedAt: String? = null

    @SerializedName("detail")
    @Expose
    var detail: String? = null

    @SerializedName("braintree_id")
    @Expose
    var braintreeId: String? = null

    @SerializedName("fb_url")
    @Expose
    var fbUrl: String? = null

    @SerializedName("twitter_url")
    @Expose
    var twitterUrl: String? = null

    @SerializedName("youtube_url")
    @Expose
    var youtubeUrl: String? = null

    @SerializedName("linkedin_url")
    @Expose
    var linkedinUrl: String? = null

    @SerializedName("prefer_pay_method")
    @Expose
    var preferPayMethod: String? = null

    @SerializedName("paypal_email")
    @Expose
    var paypalEmail: String? = null

    @SerializedName("paytm_mobile")
    @Expose
    var paytmMobile: String? = null

    @SerializedName("bank_acc_name")
    @Expose
    var bankAccName: String? = null

    @SerializedName("bank_acc_no")
    @Expose
    var bankAccNo: String? = null

    @SerializedName("ifsc_code")
    @Expose
    var ifscCode: String? = null

    @SerializedName("bank_name")
    @Expose
    var bankName: String? = null

    @SerializedName("facebook_id")
    @Expose
    var facebookId: String? = null

    @SerializedName("google_id")
    @Expose
    var googleId: String? = null

    @SerializedName("amazon_id")
    @Expose
    var amazonId: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("zoom_email")
    @Expose
    var zoomEmail: String? = null

    @SerializedName("jwt_token")
    @Expose
    var jwtToken: String? = null

    @SerializedName("gitlab_id")
    @Expose
    var gitlabId: String? = null

    @SerializedName("linkedin_id")
    @Expose
    var linkedinId: String? = null

    @SerializedName("twitter_id")
    @Expose
    var twitterId: String? = null

    @SerializedName("occupation")
    @Expose
    var occupation: String? = null

    @SerializedName("otp")
    @Expose
    var otp: String? = null

    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("video")
    @Expose
    var video: String? = null

    @SerializedName("c_name")
    @Expose
    private var cName: String? = null

    @SerializedName("aboutus")
    @Expose
    var aboutus: String? = null

    @SerializedName("got_lead")
    @Expose
    var gotLead: String? = null

    @SerializedName("lead_time")
    @Expose
    var leadTime: String? = null

    @SerializedName("package_id")
    @Expose
    var packageId: String? = null

    @SerializedName("via")
    @Expose
    var via: String? = null
    fun getcName(): String? {
        return cName
    }

    fun setcName(cName: String?) {
        this.cName = cName
    }
}