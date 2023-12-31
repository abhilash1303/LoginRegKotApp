package www.rahagloball.loginregkotapp.progrescustm

import android.animation.ValueAnimator

class LineScalePulseOutIndicator : LineScaleIndicator() {
    override fun onCreateAnimators(): ArrayList<ValueAnimator>? {
        val animators = ArrayList<ValueAnimator>()
        val delays = longArrayOf(500, 250, 0, 250, 500)
        for (i in 0..4) {
            val scaleAnim = ValueAnimator.ofFloat(1f, 0.3f, 1f)
            scaleAnim.duration = 900
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i]
            addUpdateListener(scaleAnim) { animation: ValueAnimator ->
                scaleYFloats[i] = animation.animatedValue as Float
                postInvalidate()
            }
            animators.add(scaleAnim)
        }
        return animators
    }
}