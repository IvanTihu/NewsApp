package com.example.testproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.testproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val loginIn = "Ivan"
    val passwordIn = "111a"

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


//
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





}

