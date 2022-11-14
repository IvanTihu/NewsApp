package com.example.testproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val password: String
)
