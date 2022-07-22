package com.example.bmo.pojo

data class News(

    val source: Pair<String, String>,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val url_to_image: String,
    val published_at: String,
    val content: String,

    val is_favorite: Boolean = false
)
