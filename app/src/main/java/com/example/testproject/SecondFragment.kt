package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val LOGIN_COUNTER = stringPreferencesKey("login_key")

class SecondFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var binding: FragmentHomeBinding
    private val args: SecondFragmentArgs by navArgs()
    var adapter:DataAdapter= DataAdapter(listOf())
    val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter = adapter

        viewModel.getNews()

        viewModel.newsLivedata.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })


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

        var sharedPref = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        var edit = sharedPref?.edit()

        edit?.apply {
            putString("name", message)
            putBoolean(VALID, true)
            apply()
        }

        binding.imExit.setOnClickListener {
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
    }


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