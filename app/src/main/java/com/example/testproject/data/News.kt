package com.example.testproject.data

import com.example.testproject.data.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)