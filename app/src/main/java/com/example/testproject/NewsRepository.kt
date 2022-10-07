package com.example.testproject

import androidx.lifecycle.LiveData
import com.example.testproject.data.Article

class NewsRepository {

    private val db = ArticleDatabase.getInstance(App.instance).articleDao

    fun saveAll(list: List<ArticleDB>) =
        db.insertAll(list)

    fun getAll(): List<ArticleDB> {
        return db.getAll()
    }

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
