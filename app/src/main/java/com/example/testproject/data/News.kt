package com.example.testproject.data


data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

