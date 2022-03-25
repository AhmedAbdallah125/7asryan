package com.example.a7asryan.remote

import com.example.a7asryan.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
private const val  APIKEY : String ="9c247add9401437388bd67294067ddd4"
interface RetrofitService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey :String =APIKEY
    )
    : Response<News>
}