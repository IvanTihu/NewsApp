package com.example.testproject

import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.data.Article
import com.example.testproject.databinding.FragmentNewsBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val LOGIN_COUNTER = stringPreferencesKey("login_key")

class NewsFragment : Fragment(),
    DataAdapter.OnDataClick,
    ConnectivityReceiver.ConnectivityReceiverListener
{

    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var binding: FragmentNewsBinding
    private val args: NewsFragmentArgs by navArgs()
    private val viewModel: NewsViewModel by activityViewModels()
    private var sharedPref: SharedPreferences? = null
    var adapter: DataAdapter = DataAdapter(listOf(), this)
    private lateinit var arrayNews: List<Article>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        binding = FragmentNewsBinding.bind(view)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(
            ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(ConnectivityReceiver())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        sharedPref = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


        layoutManager = LinearLayoutManager(context)
        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter = adapter
//        viewModel.getNews()
//
//        binding.swipeRefresh.setOnRefreshListener {
//            viewModel.getNews()
//        }

        viewModel.newsLivedata.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            arrayNews = it
            binding.swipeRefresh.isRefreshing = false
        })

        val message = args.name
        if (message == "") {
            lifecycleScope.launch {
                read().collect {
                    binding.tvLogin.text = "Hello $it"
                }
            }
        } else {
            binding.tvLogin.text = "Hello $message"
        }

        sharedPref?.edit().apply {
            this?.putString("name", message)
            this?.putBoolean(VALID, true)
            this?.apply()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu_exit, menu)
    }

    // exit from account and navigate to loginFragment, Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit_action -> {
                with(sharedPref?.edit()) {
                    this?.putBoolean(VALID, false)
                    this?.apply()
                }
                val item = sharedPref?.getBoolean(VALID, false)
                Toast.makeText(requireActivity(), "VALID = $item", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToLoginFragment()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }

    //function onClick switching to news details activity
    override fun onItemClick(position: Int) {
        val url = arrayNews[position].url
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(
                url
            )
        )
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun showNetworkMessage(connected: Boolean) {

        if (connected) {
            viewModel.getNews()

            binding.swipeRefresh.setOnRefreshListener {
                viewModel.getNews()
            }
            binding.tvLogin.text = "INTERNET IS CONNECTED"
            Toast.makeText(requireActivity(), "Internet is connected", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvLogin.text = "INTERNET IS DISCONNECTED"
            Toast.makeText(requireActivity(), "Internet is disconnected", Toast.LENGTH_SHORT).show()
        }

    }
}