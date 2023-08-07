package www.rahagloball.loginregkotapp.constsnsesion


import android.annotation.SuppressLint

import android.annotation.TargetApi

import android.content.Context

import android.content.res.TypedArray

import android.graphics.Canvas

import android.graphics.Color

import android.graphics.Paint

import android.graphics.Path

import android.graphics.Rect

import android.graphics.Typeface

import android.os.Build

import android.util.AttributeSet

import android.view.View


import androidx.annotation.ColorInt

import androidx.annotation.ColorRes

import androidx.annotation.StringRes

import androidx.core.content.ContextCompat
import www.rahagloball.loginregkotapp.R


class TriangleLabelView : View {
    private class PaintHolder {
        var text = ""
        var paint: Paint? = null
        var color = 0
        var size = 0f
        var height = 0f
        var width = 0f
        var style = 0
        fun initPaint() {
            paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint!!.color = color
            paint!!.textAlign = Paint.Align.CENTER
            paint!!.textSize = size
            if (style == 1) {
                paint!!.typeface = Typeface.SANS_SERIF
            } else if (style == 2) {
                paint!!.typeface = Typeface.DEFAULT_BOLD
            }
        }

        fun resetStatus() {
            val rectText = Rect()
            paint!!.getTextBounds(text, 0, text.length, rectText)
            width = rectText.width().toFloat()
            height = rectText.height().toFloat()
        }
    }

    private val primary = PaintHolder()
    private val secondary = PaintHolder()
    private var topPadding = 0f
    private var bottomPadding = 0f
    private var centerPadding = 0f
    private var trianglePaint: Paint? = null
    var triangleBackGroundColor = 0
        private set
    private var width = 0
    private var height = 0
    private var corner: Corner? = null

    enum class Corner(private val type: Int) {
        TOP_LEFT(1), TOP_RIGHT(2), BOTTOM_LEFT(3), BOTTOM_RIGHT(4);

        fun top(): Boolean {
            return this == TOP_LEFT || this == TOP_RIGHT
        }

        fun left(): Boolean {
            return this == TOP_LEFT || this == BOTTOM_LEFT
        }

        companion object {
            fun from(type: Int): Corner {
                for (c in values()) {
                    if (c.type == type) return c
                }
                return TOP_LEFT
            }
        }
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TriangleLabelView)
        topPadding =
            ta.getDimension(R.styleable.TriangleLabelView_labelTopPadding, dp2px(7f).toFloat())
        centerPadding =
            ta.getDimension(R.styleable.TriangleLabelView_labelCenterPadding, dp2px(3f).toFloat())
        bottomPadding =
            ta.getDimension(R.styleable.TriangleLabelView_labelBottomPadding, dp2px(3f).toFloat())
        triangleBackGroundColor = ta.getColor(
            R.styleable.TriangleLabelView_backgroundColor,
            Color.parseColor("#66000000")
        )
        primary.color = ta.getColor(R.styleable.TriangleLabelView_primaryTextColor, Color.WHITE)
        secondary.color = ta.getColor(R.styleable.TriangleLabelView_secondaryTextColor, Color.WHITE)
        primary.size = ta.getDimension(R.styleable.TriangleLabelView_primaryTextSize, sp2px(11f))
        secondary.size = ta.getDimension(R.styleable.TriangleLabelView_secondaryTextSize, sp2px(8f))
        val primary: String? = ta.getString(R.styleable.TriangleLabelView_primaryText)
        if (primary != null) {
            this.primary.text = primary
        }
        val secondary: String? = ta.getString(R.styleable.TriangleLabelView_secondaryText)
        if (secondary != null) {
            this.secondary.text = secondary
        }
        this.primary.style = ta.getInt(R.styleable.TriangleLabelView_primaryTextStyle, 2)
        this.secondary.style = ta.getInt(R.styleable.TriangleLabelView_secondaryTextStyle, 0)
        corner = Corner.from(ta.getInt(R.styleable.TriangleLabelView_corner, 1))
        ta.recycle()
        this.primary.initPaint()
        this.secondary.initPaint()
        trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        trianglePaint!!.color = triangleBackGroundColor
        this.primary.resetStatus()
        this.secondary.resetStatus()
    }

    var labelTopPadding: Float
        get() = topPadding
        set(dp) {
            topPadding = dp2px(dp).toFloat()
        }
    var labelCenterPadding: Float
        get() = centerPadding
        set(dp) {
            centerPadding = dp2px(dp).toFloat()
            relayout()
        }
    var labelBottomPadding: Float
        get() = bottomPadding
        set(dp) {
            bottomPadding = dp2px(dp).toFloat()
            relayout()
        }

    fun setPrimaryText(@StringRes textRes: Int) {
        primary.text = context.getString(textRes)
        primary.resetStatus()
        relayout()
    }

    var primaryText: String
        get() = primary.text
        set(text) {
            primary.text = text
            primary.resetStatus()
            relayout()
        }

    fun setSecondaryText(@StringRes textRes: Int) {
        secondary.text = context.getString(textRes)
        secondary.resetStatus()
        relayout()
    }

    var secondaryText: String
        get() = secondary.text
        set(smallText) {
            secondary.text = smallText
            secondary.resetStatus()
            relayout()
        }

    fun setPrimaryTextColor(@ColorInt color: Int) {
        primary.color = color
        primary.initPaint()
        primary.resetStatus()
        relayout()
    }

    fun setPrimaryTextColorResource(@ColorRes colorResource: Int) {
        primary.color = ContextCompat.getColor(context, colorResource)
        primary.initPaint()
        primary.resetStatus()
        relayout()
    }

    fun setSecondaryTextColor(@ColorInt color: Int) {
        secondary.color = color
        secondary.initPaint()
        secondary.resetStatus()
        relayout()
    }

    fun setSecondaryTextColorResource(@ColorRes colorResource: Int) {
        secondary.color = ContextCompat.getColor(context, colorResource)
        secondary.initPaint()
        secondary.resetStatus()
        relayout()
    }

    var primaryTextSize: Float
        get() = primary.size
        set(sp) {
            primary.size = sp2px(sp)
            relayout()
        }
    var secondaryTextSize: Float
        get() = secondary.size
        set(sp) {
            secondary.size = sp2px(sp)
            relayout()
        }

    fun setTriangleBackgroundColor(@ColorInt color: Int) {
        triangleBackGroundColor = color
        trianglePaint!!.color = triangleBackGroundColor
        relayout()
    }

    fun setTriangleBackgroundColorResource(@ColorRes colorResource: Int) {
        triangleBackGroundColor = ContextCompat.getColor(context, colorResource)
        trianglePaint!!.color = triangleBackGroundColor
        relayout()
    }

    fun setCorner(corner: Corner?) {
        this.corner = corner
        relayout()
    }

    fun getCorner(): Corner? {
        return corner
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()

        // translate
        if (corner!!.top()) {
            canvas.translate(0f, (height * Math.sqrt(2.0) - height).toFloat())
        }

        // rotate
        if (corner!!.top()) {
            if (corner!!.left()) {
                canvas.rotate(DEGREES_LEFT.toFloat(), 0f, height.toFloat())
            } else {
                canvas.rotate(DEGREES_RIGHT.toFloat(), width.toFloat(), height.toFloat())
            }
        } else {
            if (corner!!.left()) {
                canvas.rotate(DEGREES_RIGHT.toFloat(), 0f, 0f)
            } else {
                canvas.rotate(DEGREES_LEFT.toFloat(), width.toFloat(), 0f)
            }
        }

        // draw triangle
        @SuppressLint("DrawAllocation") val path = Path()
        if (corner!!.top()) {
            path.moveTo(0f, height.toFloat())
            path.lineTo((width / 2).toFloat(), 0f)
            path.lineTo(width.toFloat(), height.toFloat())
        } else {
            path.moveTo(0f, 0f)
            path.lineTo((width / 2).toFloat(), height.toFloat())
            path.lineTo(width.toFloat(), 0f)
        }
        path.close()
        canvas.drawPath(path, trianglePaint!!)

        // draw secondaryText
        if (corner!!.top()) {
            canvas.drawText(
                secondary.text,
                (width / 2).toFloat(),
                topPadding + secondary.height,
                secondary.paint!!
            )
            canvas.drawText(
                primary.text,
                (width / 2).toFloat(),
                topPadding + secondary.height + centerPadding + primary.height,
                primary.paint!!
            )
        } else {
            canvas.drawText(
                secondary.text,
                (width / 2).toFloat(),
                bottomPadding + secondary.height + centerPadding + primary.height,
                secondary.paint!!
            )
            canvas.drawText(
                primary.text,
                (width / 2).toFloat(),
                bottomPadding + primary.height,
                primary.paint!!
            )
        }
        canvas.restore()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        height =
            (topPadding + centerPadding + bottomPadding + secondary.height + primary.height).toInt()
        width = 2 * height
        val realHeight = (height * Math.sqrt(2.0)).toInt()
        setMeasuredDimension(width, realHeight)
    }

    fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Float {
        val scale = context.resources.displayMetrics.scaledDensity
        return spValue * scale
    }

    /**
     * Should be called whenever what we're displaying could have changed.
     */
    private fun relayout() {
        invalidate()
        requestLayout()
    }

    companion object {
        private val TAG = TriangleLabelView::class.java.simpleName
        private const val DEGREES_LEFT = -45
        private const val DEGREES_RIGHT = 45
    }
}