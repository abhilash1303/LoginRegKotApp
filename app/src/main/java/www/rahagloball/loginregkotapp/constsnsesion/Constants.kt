package www.rahagloball.loginregkotapp.constsnsesion

import android.content.Context
import android.net.ConnectivityManager

object Constants {
    const val USER_NAME = "uName"
    const val USER_EMAIL = "uEmail"
    const val USER_NUM = "uNum"
    const val USER_STATE = "uState"
    const val USER_CITY = "uCity"
    const val USER_GENDER = "Gender"
    const val USER_MARITAL = "Marital"
    const val USER_AINCOME = "AIncome"
    const val USER_TOKEN_LRN = "access_token"
    const val LOGIN_STATUS = "loginStatus"
    const val USER_PROFILE_PIC = "ProfilePic"
    const val USER_NUM1 = "mobile"
    const val CHANNEL_ID = "NLP_app"
    const val CHANNEL_DESC = "Its About Buyleads"
    val NOTICHANNEL_NAME: CharSequence = "BuyLeads"
    const val SUPPORT_HIT = "support"
    const val VIDEO_SAVEEE = "status"
    const val LIKE_HIT = "vote"
    const val USER_CATGRY = "category_id"

    fun isWorkingInternetPresent(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.let {
            val info = it.activeNetworkInfo
            return info != null && info.isConnected
        }
        return false
    }
}