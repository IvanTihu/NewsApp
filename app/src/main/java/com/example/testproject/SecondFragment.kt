package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.testproject.databinding.FragmentSecondBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    private val args: SecondFragmentArgs by navArgs()
    lateinit var dataStore: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        binding = FragmentSecondBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = args.name
        binding.tvLogin.text = "Hello $message"



        val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
        val exampleCounterFlow: Flow<Int> = context?.dataStore?.data?.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }!!

    }




}