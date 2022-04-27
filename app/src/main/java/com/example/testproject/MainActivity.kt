package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doAfterTextChanged
import com.example.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val loginIn = "Ivan"
    val passwordIn = "111a"
    var loginValid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIn.setOnClickListener {
            binding.apply {
                if (loginValid(textLogin.text.toString(), textPassword.text.toString())) {
                    tvRezult.text = "Congratulation you sign in"
                    layoutLogin.visibility = View.GONE
                    layoutPassword.visibility = View.GONE
                } else tvRezult.text = "Invalid login or password"

            }
        }

        binding.textLogin.doAfterTextChanged {
            loginValid = isLoginValid(it.toString())
        }

        binding.textPassword.doAfterTextChanged {
            val rez = isPasswordValid(it.toString())
            binding.btIn.isEnabled = rez && loginValid
        }
    }

    private fun loginValid (login: String, password: String):Boolean = login == loginIn && password == passwordIn

    private fun isLoginValid(text: String): Boolean = text.length > 2

    private fun isPasswordValid(text: String): Boolean {
       var hesLetter = false
        var number: Int = 0

        text.forEach {
            if (it.isDigit()) number++
            else if (it.isLetter()) hesLetter = true
        }
        return number > 2 && hesLetter
    }


}