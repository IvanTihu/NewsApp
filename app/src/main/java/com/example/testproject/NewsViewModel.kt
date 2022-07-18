package com.example.testproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel(var newsRepo: NewsRepository) : ViewModel() {

    lateinit var newsLivedata: MutableLiveData<List<Article>>

    fun getNews() = newsRepo.getNews()

}