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
    /*val loginIn = "Ivan"
    val passwordIn = "111a"  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



       /* binding.btIn.setOnClickListener {
            binding.apply {
                if (loginValid(textLogin.text.toString(), textPassword.text.toString())) {
                    tvRezult.text = "Congratulation you sign in"
                    layoutLogin.visibility = View.GONE
                    layoutPassword.visibility = View.GONE
                } else tvRezult.text = "Invalid login or password"

            }
        }

        binding.textLogin.doAfterTextChanged {
            binding.btIn.isEnabled = isLoginValid(it.toString()) && isPasswordValid(binding.textPassword.text.toString())
        }

        binding.textPassword.doAfterTextChanged {
            binding.btIn.isEnabled = isPasswordValid(it.toString()) && isLoginValid(binding.textLogin.text.toString())
        }  */
    }

   /* private fun loginValid(login: String, password: String): Boolean =
        login == loginIn && password == passwordIn

    private fun isLoginValid(text: String): Boolean = text.length > 2

    private fun isPasswordValid(password: String): Boolean {
        var hasLetter = false
        password.forEach {
            if (it.isDigit())
                hasLetter = true
        }
        return hasLetter && password.length > 3
    }  */


}