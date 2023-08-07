package www.rahagloball.loginregkotapp.configuration

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.WindowManager

/**
 * Created by Ashu on 19.1.2018.
 */
object Utils {
    //Email Validation pattern
    const val regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b"

    //Web Validation pattern
    const val WebUrl =
        "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?$"

    //Fragments Tags
    const val Login_Fragment = "Login_Fragment"
    const val FirstSplashLogin = "FirstSplashLogin"
    const val SignUp_Fragment = "SignUp_Fragment"
    const val ForgotPassword_Fragment = "ForgotPassword_Fragment"
    const val HomeFragment = "HomeFragment"
    const val MyProfileFragmet = "MyProfileFragmet"
    const val SearchFragment = "SearchFragment"
    const val DrawerFragment = "DrawerFragment"
    private var screenWidth = 0
    private var screenHeight = 0
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getScreenHeight(c: Context): Int {
        if (screenHeight == 0) {
            val wm = c.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                display.getSize(size)
            }
            screenHeight = size.y
        }
        return screenHeight
    }

    fun getScreenWidth(c: Context): Int {
        if (screenWidth == 0) {
            val wm = c.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                display.getSize(size)
            }
            screenWidth = size.x
        }
        return screenWidth
    }

    val isAndroid5: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    @SuppressLint("DefaultLocale")
    fun getShortenedViewsString(views: Int): String {
        var viewsString = ""
        viewsString = if (views >= 1000000000) {
            val billions = views / 1000000000.0f
            //            viewsString = String.format("%.1f", billions);
            String.format("%.1fB", billions).replace(".0", "") + "B"
        } else if (views >= 1000000) {
            val millions = views / 1000000.0f
            //            viewsString = String.format("%.1fM", millions);
            String.format("%.1f", millions).replace(".0", "") + "M"
        } else if (views >= 1000) {
            val thousands = views / 1000.0f
            String.format("%.1f", thousands).replace(".0", "") + "K"
            //            viewsString = String.format("%.1fK", thousands);
        } else {
            views.toString()
        }
        return viewsString
    }
}