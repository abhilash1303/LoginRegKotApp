package www.rahagloball.loginregkotapp.constsnsesion

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import www.rahagloball.loginregkotapp.R

class CustomDialog(context: Context?) : Dialog(context!!) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.custom_dialog1)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}