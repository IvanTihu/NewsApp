package com.example.testproject.data

import com.example.testproject.ArticleDB


data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? ="",
    val publishedAt: String? = "",
    val source: Source? = Source(),
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)

fun Article.toArticleDB():ArticleDB{
    return ArticleDB(
        author = this.author.orEmpty(),
        title = this.title.orEmpty(),
        urlToImage = this.urlToImage.orEmpty()
    )
}

