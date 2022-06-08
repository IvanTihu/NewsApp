package com.example.testproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val LOGIN_COUNTER = stringPreferencesKey("login_key")

    val loginIn = "Ivan"
    val passwordIn = "111a"
    private val args: LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            val loginData = read()
//            lifecycleScope.launch{
//                loginData.collect {
//                    binding.textLogin.setText(it)
//                }
//            }

        if (args.loginN.isNullOrEmpty().not()) {
            val amount = args.loginN
            binding.textLogin.setText(amount)
        }

        binding.btIn.setOnClickListener {
            binding.apply {
                if (isLoginValid(textLogin.text.toString(), textPassword.text.toString())) {
                    tvRezult.text = "Congratulation you sign in"
                    val userLogin = textLogin.text.toString()
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(userLogin)
                    )
                } else tvRezult.text = "Invalid login or password"

            }
        }

        binding.btRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun isLoginValid(text1: String, text2: String): Boolean = text1.length > 2 && text2.length > 3

    fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }

}