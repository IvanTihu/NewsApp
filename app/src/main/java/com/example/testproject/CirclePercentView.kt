package com.example.testproject

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CirclePercentView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint: Paint? = null
    private var approvedPercentage = 0f
    private var requiredPercentage = 0f
    private val stroke = 20.px.toFloat()
    private var requiredColor: Int = 0
    private var approvedColor: Int = 0
    private var bgColor: Int = 0
    private var emptyColor: Int = 0


    init {
        attrs?.let { initAttributes(it) }
        paint = Paint()
        paint?.apply {
            color = getContext().resources.getColor(R.color.sheet_to_floor_add_btn_on_tap)
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeWidth = stroke
        }
    }

    private fun initAttributes(attrs: AttributeSet) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.circle_percent_view_attrs)
        this.approvedColor = attr.getColor(R.styleable.circle_percent_view_attrs_firstColor,
            resources.getColor(R.color.sheet_to_floor_add_btn_on_tap))
        this.requiredColor = attr.getColor(R.styleable.circle_percent_view_attrs_secondColor,
            resources.getColor(R.color.linked_add_btn_on_tap))
        this.bgColor = attr.getColor(R.styleable.circle_percent_view_attrs_bgColor,
            resources.getColor(R.color.bg))
        this.emptyColor = attr.getColor(R.styleable.circle_percent_view_attrs_emptyColor,
            resources.getColor(R.color.add_btn_disabled))

        attr.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val strokeHalf = stroke / 2

        val mainArc = RectF(stroke + strokeHalf,
            stroke + strokeHalf,
            width.toFloat() - stroke - strokeHalf,
            height.toFloat() - stroke - strokeHalf)

        val backgroundArc = RectF(strokeHalf,
            strokeHalf,
            width.toFloat() - strokeHalf,
            height.toFloat() - strokeHalf)

        canvas.drawArc(backgroundArc, -90f, 360f, false,
            paint.apply { this?.color = bgColor }!!)

        canvas.drawArc(mainArc,
            -90f,
            360f,
            false,
            paint.apply {
                this?.color = emptyColor
            }!!)


        if (requiredPercentage != 0f) {
            canvas.drawArc(mainArc, -90f, 360 * requiredPercentage, false, paint.apply {
                this?.color = requiredColor
            }!!)
        }
        if (approvedPercentage != 0f) {
            canvas.drawArc(mainArc, -90f, 360 * approvedPercentage, false, paint.apply {
                this?.color = approvedColor
            }!!)
        }
    }

    fun setPercentage(approvedPercentage: Float, requiredPercentage: Float) {
        this.approvedPercentage = approvedPercentage / 100
        this.requiredPercentage = (approvedPercentage + requiredPercentage) / 100
        invalidate()
    }
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
