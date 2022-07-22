package com.example.bmo.repository

import com.example.bmo.network.NewsApiService
import retrofit2.http.Query
import javax.inject.Inject

class Repository @Inject constructor(private val news_api_service: NewsApiService) {

    fun get_all_news(
        api_key: String,
        q: String,
        domains: ArrayList<String>? = null,
        sort_by: String? = null,
        language: String? = null,
        from: String? = null,
        to: String? = null,
        page_size: Int? = null) =

        news_api_service.get_all_news(api_key, q, domains, sort_by, language, from, to, page_size)

    fun get_top_news(
        api_key: String,
        country: String,
        q: String? = null,
        category: String? = null,
        page_size: Int? = null) =

        news_api_service.get_top_news(api_key, country, q, category, page_size)
}