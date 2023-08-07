package www.rahagloball.loginregkotapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager {

    fun setPreferences(context: Context, key: String, value: String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPreferences(context: Context, key: String): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences("Androidwarriors", Context.MODE_PRIVATE)
        return prefs.getString(key, "") ?: ""
    }
}