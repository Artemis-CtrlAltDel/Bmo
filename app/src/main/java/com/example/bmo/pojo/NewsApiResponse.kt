package com.example.bmo.pojo

data class NewsApiResponse(

    val status: String,
    val totalResults: Long,
    val articles: ArrayList<News>
)
