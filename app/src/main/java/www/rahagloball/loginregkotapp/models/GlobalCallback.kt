package www.rahagloball.loginregkotapp.models

import android.util.Log
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class GlobalCallback<T>(private val view: View?) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (view == null) return

        var message = "Sorry! Unable to process request!"

        if (t is HttpException) {
            when (t.code()) {
                // 400 Bad Request
                400 -> message = "Server is unable to process request. Please try again later."
                // 401 Unauthorized, 403 Forbidden, 404 Not Found
                401, 403, 404 -> message =
                    "Server is not compatible with the client. Please send an email to contact@nlpartner.in."
                // 408 Request Time-out
                408 -> message =
                    "Request timed out. Please try again or check your internet connection."
            }
        } else if (t is SocketTimeoutException) {
            message =
                "Server is not reachable. Either the server or your internet connection is down."
        } else if (t is UnknownHostException) {
            message =
                "Server is not compatible with the client. Please send an email to contact@nlpartner.in."
        }

        Log.e("onFailure:", t.message, t)
    }
}
