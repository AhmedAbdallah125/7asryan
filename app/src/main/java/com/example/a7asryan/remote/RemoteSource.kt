package com.example.a7asryan.remote

import com.example.a7asryan.model.News
import retrofit2.Response


interface RemoteSource {
    suspend fun getNews():Response<News>
}