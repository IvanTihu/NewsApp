package com.example.testproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel() : ViewModel() {
    var newsRepo: NewsRepository = NewsRepository()

    var newsLivedata = MutableLiveData<List<Article>>()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = newsRepo.getNews()
            withContext(Dispatchers.Main) {
                newsLivedata.value = response
            }
        }
    }
}