package com.example.testproject

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testproject.databinding.ExpandedViewBinding

class ExpandableView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ExpandedViewBinding? = null
    private var expandedViewHeight: Int = 0
    var expandedView: View? = null
    var isExpanded = true

    init {
        binding = ExpandedViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun initView(expandedView: View) {
        this.expandedView = expandedView
        this.expandedViewHeight = expandedView.height
        binding?.let { view ->
            view.title.setOnClickListener {
                isExpanded = if (isExpanded) {
                    animateHeight(expandedView, expandedViewHeight, 1)

                    view.title.text="Show Legend"
                    view.title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.ic_chevron_down, 0)
                    false
                } else {
                    view.title.text="Hide Legend"
                    view.title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.ic_chevron_up, 0)
                    animateHeight(expandedView, 1, expandedViewHeight)
                    true
                }
            }
        }
    }

    private fun animateHeight(expandedView: View, from: Int, to: Int) {
        expandedView.let {
            val anim = ValueAnimator.ofInt(from, to)
            anim.addUpdateListener { valueAnimator ->
                val layoutParams: ViewGroup.LayoutParams = it.layoutParams
                layoutParams.height = valueAnimator.animatedValue as Int
                it.layoutParams = layoutParams
            }
            anim.duration = 300
            anim.start()
        }
    }

}