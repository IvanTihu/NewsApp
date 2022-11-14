package com.example.testproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert
    fun insert(item: ArticleDB)

    @Insert
    fun insertAll(list: List<ArticleDB>)

    @Query("SELECT*FROM article_table")
    fun getAll(): List<ArticleDB>
}