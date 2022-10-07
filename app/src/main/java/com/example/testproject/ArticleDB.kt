package com.example.testproject

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testproject.data.Article

@Entity(tableName = "article_table")
data class ArticleDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String = "",
    val title: String = "",
    val urlToImage: String = ""
)

fun ArticleDB.toArticle(): Article {
    return Article(
        author = this.author,
        title = this.title,
        urlToImage = this.urlToImage
    )
}

