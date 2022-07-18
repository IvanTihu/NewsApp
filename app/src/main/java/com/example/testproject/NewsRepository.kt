package com.example.testproject

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    fun getNews(): List<Article> {
        val apiInterface = ApiInterface.create().getNews("ua", "f6b10daf8fcc43efba18176dd0a71bfe")

        var response = apiInterface.execute(object : Response<News> {
            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                Log.d("testLogs", "OnResponse Success ${response?.body()?.articles}")
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                Log.d("testLogs", "onFailure${t?.message}")
            }
        })

        if(response)


    }
}
