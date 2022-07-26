package com.example.testproject

import android.content.Context
import android.content.SharedPreferences
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
import com.example.testproject.databinding.FragmentNewsBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val LOGIN_COUNTER = stringPreferencesKey("login_key")

class NewsFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var binding: FragmentNewsBinding
    private val args: NewsFragmentArgs by navArgs()
    var adapter: DataAdapter = DataAdapter(listOf())
    private val viewModel: NewsViewModel by activityViewModels()
    private var sharedPref: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        binding = FragmentNewsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        sharedPref = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        layoutManager = LinearLayoutManager(context)
        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter = adapter

        viewModel.getNews()

        viewModel.newsLivedata.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
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

//        binding.imExit.setOnClickListener {
//
//            with(sharedPref?.edit()) {
//                this?.putBoolean(VALID, false)
//                this?.apply()
//            }
//
//            val item = sharedPref?.getBoolean(VALID, false)
//            Toast.makeText(requireActivity(), "VALID = $item", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(
//                NewsFragmentDirections.actionNewsFragmentToLoginFragment()
//            )
//        }

//        binding.bt.setOnClickListener {
//            edit?.apply {
//                putBoolean(VALID, false)
//                apply()
//            }
//            findNavController().navigate(
//                NewsFragmentDirections.actionHomeFragmentToLoginFragment()
//            )
//        }
        //function to add a new data in RecyclerView
//        binding.btAdd.setOnClickListener {
//            val data = Data("new Task is $counter", "new Data is $counter")
//            (adapter as DataAdapter).addData(data)
//            counter++
//        }
        // onClickListener item position RecyclerView
//        (adapter as DataAdapter).setOnItemClickListener(object : DataAdapter.OnDataClick{
//            override fun onDataItemClick(position: Int) {
//                Toast.makeText(requireActivity(), "You Clicked Task №$position", Toast.LENGTH_SHORT).show()
//            }
//                override fun onItemDelete(position: Int) {
//                data.removeAt(position)
//                (adapter as DataAdapter).notifyItemRemoved(position)
//            }
//
//        })

        //        val apiInterface = ApiInterface.create().getNews("ua", "f6b10daf8fcc43efba18176dd0a71bfe")
//
//        apiInterface.enqueue(object : Callback<News> {
//            override fun onResponse(call: Call<News>?, response: Response<News>?) {
//                Log.d("testLogs", "OnResponse Success ${response?.body()?.articles}")
//                adapter.updateList(response?.body()?.articles)
//            }
//
//            override fun onFailure(call: Call<News>?, t: Throwable?) {
//                Log.d("testLogs", "onFailure${t?.message}")
//            }
//        })

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
    //    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.btn_exit_action -> {
//                findNavController().navigate(
//                    NewsFragmentDirections.actionNewsFragmentToLoginFragment()
//                )
//                Toast.makeText(requireActivity(), "Clicked exit", Toast.LENGTH_SHORT).show()
//                edit?.apply {
//                    putBoolean(VALID, false)
//                    apply()
//                }
//
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


    private fun read(): Flow<String> {
        return context?.dataStore?.data?.map { preferences ->
            preferences[LOGIN_COUNTER] ?: ""
        }!!
    }
//    private fun createDataList(){
//        for (i in 0..30){
//            data.add(Data("Task #$i", "data $i"))
//        }
//    }
}