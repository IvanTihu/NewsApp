package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.testproject.databinding.ActivityMainBinding

const val APP_PREFERENCES = "APP_PREFERENCES"
const val VALID = "VALID"
private val LOGIN_COUNTER = stringPreferencesKey("login_key")


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    // private val viewModel: NewsViewModel by viewModels()
    // private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  setSupportActionBar(binding.toolbar)
       // title = "News"
        val sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        val navHost = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment

        navController = navHost.navController
       // NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }


}