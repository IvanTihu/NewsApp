package com.example.testproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleDB::class, User::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao
    abstract val userDao: UserDao

    companion object {
    private var INSTANCE: ArticleDatabase? = null
        fun getInstance(context: Context): ArticleDatabase {
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_database"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}
