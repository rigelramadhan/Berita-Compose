package com.rigelramadhan.beritacompose.core.data.source.remote.network

import com.rigelramadhan.beritacompose.core.data.source.remote.response.NewsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("articles")
    suspend fun getNews(): List<NewsItem>

    @GET("articles/{id}")
    suspend fun getNewsById(@Path("id") id: Int): NewsItem
}