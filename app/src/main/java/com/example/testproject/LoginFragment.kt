package com.example.testproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.example.testproject.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val loginIn = "Ivan"
    val passwordIn = "111a"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        binding.textLogin.doAfterTextChanged {
            binding.btIn.isEnabled = isLoginValid(it.toString()) && isPasswordValid(binding.textPassword.text.toString())
        }

        binding.textPassword.doAfterTextChanged {
            binding.btIn.isEnabled = isPasswordValid(it.toString()) && isLoginValid(binding.textLogin.text.toString())
        }

        binding.btIn.setOnClickListener {
            binding.apply {
                if (loginValid(textLogin.text.toString(), textPassword.text.toString())) {
                    tvRezult.text = "Congratulation you sign in"





                  //  layoutLogin.visibility = View.GONE
                    //layoutPassword.visibility = View.GONE
                } else tvRezult.text = "Invalid login or password"

            }
        }
    }



    private fun loginValid(login: String, password: String): Boolean =
        login == loginIn && password == passwordIn

    private fun isLoginValid(text: String): Boolean = text.length > 2

    private fun isPasswordValid(password: String): Boolean {
        var hasLetter = false
        password.forEach {
            if (it.isDigit())
                hasLetter = true
        }
        return hasLetter && password.length > 3
    }

}