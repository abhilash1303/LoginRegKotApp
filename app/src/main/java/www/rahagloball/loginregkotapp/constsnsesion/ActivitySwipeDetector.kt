package www.rahagloball.loginregkotapp.constsnsesion

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

class ActivitySwipeDetector(private val activity: SwipeInterface) : OnTouchListener {
    private var downX = 0f
    private var downY = 0f
    private var upX = 0f
    private var upY = 0f
    fun onRightToLeftSwipe(v: View?) {
        Log.i(logTag, "RightToLeftSwipe!")
        activity.right2left(v)
    }

    fun onLeftToRightSwipe(v: View?) {
        Log.i(logTag, "LeftToRightSwipe!")
        activity.left2right(v)
    }

    fun onTopToBottomSwipe(v: View?) {
        Log.i(logTag, "onTopToBottomSwipe!")
        activity.top2bottom(v)
    }

    fun onBottomToTopSwipe(v: View?) {
        Log.i(logTag, "onBottomToTopSwipe!")
        activity.bottom2top(v)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                return true
            }

            MotionEvent.ACTION_UP -> {
                upX = event.x
                upY = event.y
                val deltaX = downX - upX
                val deltaY = downY - upY

                // swipe horizontal?
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        onLeftToRightSwipe(v)
                        return true
                    }
                    if (deltaX > 0) {
                        onRightToLeftSwipe(v)
                        return true
                    }
                } else {
                    Log.i(
                        logTag,
                        "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE
                    )
                }

                // swipe vertical?
                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // top or down
                    if (deltaY < 0) {
                        onTopToBottomSwipe(v)
                        return true
                    }
                    if (deltaY > 0) {
                        onBottomToTopSwipe(v)
                        return true
                    }
                } else {
                    Log.i(
                        logTag,
                        "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE
                    )
                    v.performClick()
                }
            }
        }
        return false
    }

    companion object {
        const val logTag = "ActivitySwipeDetector"
        const val MIN_DISTANCE = 100
    }
}