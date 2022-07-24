package com.example.bmo.pojo

data class News(

    val source: Pair<String, String>,
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
