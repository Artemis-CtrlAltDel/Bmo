package com.example.bmo.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,

    var is_favorite: Boolean = false,
    var favorite_count: Long = 0
)
