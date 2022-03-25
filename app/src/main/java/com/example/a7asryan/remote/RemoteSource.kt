package com.example.a7asryan.remote

import com.example.a7asryan.model.News
import retrofit2.Response

// https://newsapi.org/v2/top-headlines?country=us&apiKey=9c247add9401437388bd67294067ddd4

interface RemoteSource {
    suspend fun getNews():Response<News>

}