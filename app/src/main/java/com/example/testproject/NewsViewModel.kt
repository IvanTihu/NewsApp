package com.example.testproject

import androidx.lifecycle.*
import com.example.testproject.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel() : ViewModel() {

    var newsRepo: NewsRepository = NewsRepository()
    var newsLivedata = MutableLiveData<List<Article>>()

    fun saveAll(list: List<ArticleDB>) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepo.saveAll(list)
        }
    }

    fun getAllArticle() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = newsRepo.getAll()
            withContext(Dispatchers.Main) {
                newsLivedata.value = response.map {
                    it.toArticle()
                }
            }
        }
    }

        fun getNews() {
            viewModelScope.launch(Dispatchers.IO) {
                val response = newsRepo.getNews()
                withContext(Dispatchers.Main) {
                    newsLivedata.value = response
                }
            }
        }

    }