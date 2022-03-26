package com.example.a7asryan.remote

import com.example.a7asryan.model.ApiClass
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://newsapi.org/v2/"
private val retrofit = Retrofit.Builder()
    .apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(BASE_URL)
    }.build()

object RetrofitHelper : RemoteSource {
    private val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java);
    }

    override suspend fun getNews(): Response<ApiClass> {
        return retrofitService.getNews()
    }

}