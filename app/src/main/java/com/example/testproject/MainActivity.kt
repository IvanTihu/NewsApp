package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val login = "Ivan"
    val password = "1111"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btIn.setOnClickListener{
            binding.apply {
                if (textLogin.text.isNullOrEmpty()) textLogin.error = "Input login"
                if (textPassword.text.isNullOrEmpty()) textPassword.error = "Input password"
                if (!textLogin.text.isNullOrEmpty()) {
                    if (textLogin.text.toString() == login){
                        if (!textPassword.text.isNullOrEmpty()){
                            if (textPassword.text.toString() == password) {
                                tvRezult.text = "Congratulation you sign in"
                                layoutLogin.visibility = View.GONE
                                layoutPassword.visibility = View.GONE
                            }
                            else tvRezult.text = "Invalid password"
                        } else tvRezult.text = "Enter your password"
                    } else tvRezult.text = "Invalid login"
                } else tvRezult.text = "Enter your login and password"

            }
        }
        binding.textPassword.addTextChangedListener(textWatcher)
    }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            binding.btIn.isEnabled = true
        }
    }


}