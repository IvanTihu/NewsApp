package com.example.testproject

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testproject.databinding.VerticalBarChartBinding


class VerticalBarChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val PADDING_END = 40.px
    private var binding: VerticalBarChartBinding? = null

    init {
        binding = VerticalBarChartBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setPercentage(completePercentage: Int, incompletePercentage: Int) {
        this.post {
            binding?.completeLabel?.text = "$completePercentage"
            binding?.incompleteLabel?.text = "$incompletePercentage"

            var completeBarWidth =
                ((completePercentage / 100f * height) - (completePercentage / 100f * PADDING_END)).toInt()
            var inCompleteBarWidth =
                ((incompletePercentage / 100f * height) - (incompletePercentage / 100f * PADDING_END)).toInt()

            if (completeBarWidth == 0) completeBarWidth = 2.px
            if (inCompleteBarWidth == 0) inCompleteBarWidth = 2.px

            binding?.completeBar?.layoutParams?.height = completeBarWidth
            binding?.incompleteBar?.layoutParams?.height = inCompleteBarWidth
        }
    }
}
