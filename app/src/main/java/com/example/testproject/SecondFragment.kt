package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testproject.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val LOGIN_COUNTER = stringPreferencesKey("login_key")

class SecondFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val args: SecondFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = args.name
        if (message == ""){
           lifecycleScope.launch {
                read().collect {
                    binding.tvLogin.text = "Hello $it"
                }
            }
        }
        else {
            binding.tvLogin.text = "Hello $message"
        }

        var sharedPref = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        var edit = sharedPref?.edit()

        edit?.apply {
            putString("name", message)
            putBoolean(VALID, true)
            apply()
        }

        binding.imExit.setOnClickListener{
            edit?.apply {
                putBoolean(VALID, false)
                apply()
            }
            findNavController().navigate(
                SecondFragmentDirections.actionHomeFragmentToLoginFragment()
            )
        }

        binding.btOtput.setOnClickListener {
            edit?.apply {
                putBoolean(VALID, false)
                apply()
            }
            findNavController().navigate(
                SecondFragmentDirections.actionHomeFragmentToLoginFragment()
            )
        }
    }

    private fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }


}