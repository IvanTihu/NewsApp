package com.example.testproject

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testproject.databinding.HorizontalBarChartBinding

class HorizontalBarChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val PADDING_END = 40.px
    var maxWidthBar = 0
    private var binding: HorizontalBarChartBinding? = null


    init {
        binding = HorizontalBarChartBinding.inflate(LayoutInflater.from(context), this, true)
        val widthScreen = Resources.getSystem().displayMetrics.widthPixels
        maxWidthBar = widthScreen / 2
        setPercentage(0, 0)
    }

    fun setPercentage(completePercentage: Int, incompletePercentage: Int) {
        binding?.completeLabel?.text = "$completePercentage"
        binding?.incompleteLabel?.text = "$incompletePercentage"

        var completeBarWidth =
            ((completePercentage / 100f * maxWidthBar) - (completePercentage / 100f * PADDING_END)).toInt()
        var inCompleteBarWidth =
            ((incompletePercentage / 100f * maxWidthBar ) - (incompletePercentage / 100f * PADDING_END)).toInt()

        if (completeBarWidth == 0) completeBarWidth = 2.px
        if (inCompleteBarWidth == 0) inCompleteBarWidth = 2.px

        binding?.completeBar?.layoutParams?.width = completeBarWidth
        binding?.incompleteBar?.layoutParams?.width = inCompleteBarWidth

    }
}
