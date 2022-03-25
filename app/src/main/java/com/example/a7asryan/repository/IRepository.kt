package com.example.a7asryan.repository

import com.example.a7asryan.model.News
import com.example.a7asryan.model.User

interface IRepository{
    suspend fun getNews()

    suspend fun addToFavorite(news: News)

    suspend fun removeFromFavorite(news: News)

    suspend fun insertUser(user: User)

    suspend fun checkUser(email:String,password:String):Boolean
}