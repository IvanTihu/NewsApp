package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import com.example.testproject.databinding.FragmentRegisterBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class RegisterFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private val LOGIN_COUNTER = stringPreferencesKey("login_key")
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        binding = FragmentRegisterBinding.bind(view)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this)[NewsViewModel::class.java]

        binding.textLoginRegister.doAfterTextChanged {
            binding.btRegister.isEnabled =
                isLoginValid(it.toString()) && isPasswordValid(binding.textPassword.text.toString())
        }

        binding.textPassword.doAfterTextChanged {
            binding.btRegister.isEnabled =
                isPasswordValid(it.toString()) && isLoginValid(binding.textLoginRegister.text.toString())
        }

        binding.btRegister.setOnClickListener {
            val login = binding.textLoginRegister.text.toString()
            val password = binding.textPassword.text.toString()

//            lifecycleScope.launch {
//               saveName(login)
//           }

            viewModel.saveUser(user = User(login = login, password = password))

            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(login)
            )
        //todo move Login Fragment, send name end fill
        }
    }


     private suspend fun saveName(name: String) {
        context?.dataStore?.edit { settings ->
            settings[LOGIN_COUNTER] = name
        }
    }


    private fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }



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