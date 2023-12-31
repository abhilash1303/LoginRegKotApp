package www.rahagloball.loginregkotapp.constsnsesion

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

class MoveUpwardBehavior : CoordinatorLayout.Behavior<View?> {
    constructor() : super() {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is SnackbarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val translationY = Math.min(0f, ViewCompat.getTranslationY(dependency) - dependency.height)
        ViewCompat.setTranslationY(child, translationY)
        return true
    }

    //you need this when you swipe the snackbar(thanx to ubuntudroid's comment)
    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        ViewCompat.animate(child!!).translationY(0f).start()
    }
}