package com.example.a7asryan.repository

import com.example.a7asryan.model.News

interface IRepository{
    suspend fun getNews()

    suspend fun addToFavorite(news: News)

    suspend fun removeFromFavorite(news: News)

    suspend fun insertUser()

    suspend fun getUser(email:String)
}