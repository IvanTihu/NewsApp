package com.example.testproject

import com.example.testproject.data.Article

class NewsRepository {

    fun getNews(): List<Article> {
        val apiInterface = ApiInterface.create().getNews("ua", "f6b10daf8fcc43efba18176dd0a71bfe")
        val response = apiInterface.execute()

        return if (response.isSuccessful) {
            response.body()?.articles ?: emptyList()
        } else {
            emptyList()
        }
    }
}
