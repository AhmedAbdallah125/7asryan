package com.example.a7asryan.repository

import com.example.a7asryan.model.News
import com.example.a7asryan.model.User
import kotlinx.coroutines.flow.Flow

interface IRepository{
    suspend fun getNews():Flow<News>

    suspend fun addToFavorite(news: News)

    suspend fun removeFromFavorite(news: News)

    suspend fun insertUser(user: User)

    suspend fun checkUser(email:String,password:String):Boolean

    fun getAllDataFromDatabase(): Flow<News>
}