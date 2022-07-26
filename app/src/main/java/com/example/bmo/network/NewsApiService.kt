package com.example.bmo.network

import com.example.bmo.pojo.NewsApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    fun get_all_news(
        @Query("apiKey") api_key: String,
        @Query("q") query: String,
        @Query("domains") domains: ArrayList<String>? = null,
        @Query("sortBy") sort_by: String? = null,
        @Query("language") language: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("pageSize") page_size: Int? = null
    ): Observable<NewsApiResponse>

    @GET("top-headlines")
    fun get_local_news(
        @Query("apiKey") api_key: String,
        @Query("country") country: String,
        @Query("q") q: String? = null,
        @Query("category") category: String? = null,
        @Query("pageSize") page_size: Int? = null
    ): Observable<NewsApiResponse>

}