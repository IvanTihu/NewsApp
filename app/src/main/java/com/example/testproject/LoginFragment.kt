package com.example.testproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.datastore.preferences.core.edit
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
import android.content.SharedPreferences as SharedPreferences1

private val LOGIN_COUNTER = stringPreferencesKey("login_key")

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
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

        val sharedPref = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val edit = sharedPref?.edit()
        val validation = sharedPref?.getBoolean(VALID, false)

        if (validation == true) {
            var userLogin = ""
            lifecycleScope.launch {
                read().collect {
                    userLogin = it
                }
            }
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNewsFragment(userLogin)
            )
        } else {
            if (args.loginN.isEmpty().not()) {
                val amount = args.loginN
                binding.textLogin.setText(amount)
            }

            binding.textPasswordLogin.doAfterTextChanged {
                binding.btIn.isEnabled = isPasswordValid(it.toString())
            }

            binding.btIn.setOnClickListener {
                binding.apply {
                    lifecycleScope.launch {
                        read().collect {
                            val userLogin = textLogin.text.toString()
                            if (userLogin == it) {
                                tvRezult.text = "Congratulation you sign in"

                                edit?.apply {
                                    putBoolean(VALID, true)
                                    apply()
                                }

                                val test = sharedPref?.getBoolean(VALID, false)
                                Toast.makeText(activity, test.toString(), Toast.LENGTH_SHORT).show()
                                findNavController().navigate(
                                    LoginFragmentDirections.actionLoginFragmentToNewsFragment(
                                        userLogin
                                    )
                                )
                            } else {
                                tvRezult.text = getString(R.string.ivnalid_login_or_password)
                                edit?.putBoolean(VALID, false)
                            }
                        }
                    }
                }
            }
            binding.btRegister.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections
                        .actionLoginFragmentToRegisterFragment()
                )
            }
        }

    }

    private fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }

    private fun isPasswordValid(password: String): Boolean {
        var hasLetter = false
        password.forEach {
            if (it.isDigit()) hasLetter = true
        }
        return hasLetter && password.length > 3
    }
}