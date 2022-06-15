package com.example.testproject

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testproject.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val loginIn = "Ivan"
    val passwordIn = "111a"
    var initialHeight: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.circlePercentView.setPercentage(approvedPercentage = 50f, requiredPercentage = 40f)

        binding.verticalBarChart.setPercentage(22, 44)



        binding.expandView.post {
            binding.expandView.initView(expandedView = binding.internalView)
        }
//    lifecycleScope.launch{
//        var i=0
//        repeat(100){
//           delay(100)
//            // binding.horizontalBarChart.setPercentage(i,i)
//            binding.verticalBarChart.setPercentage(i, i)
//            i++
//        }
//
//    }


    }

    fun animate(target: Float) {
        ValueAnimator.ofFloat(binding.internalView.height.toFloat(), target).apply {
            duration = 500
            addUpdateListener {
                val layoutParams = binding.internalView.layoutParams
                layoutParams.height = (it.animatedValue as Float).toInt()
                binding.internalView.layoutParams = layoutParams

            }
            start()
        }
    }


}

