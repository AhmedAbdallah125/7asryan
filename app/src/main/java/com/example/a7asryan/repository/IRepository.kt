package com.example.a7asryan.repository

import com.example.a7asryan.model.Article
import com.example.a7asryan.model.User
import kotlinx.coroutines.flow.Flow

interface IRepository{
    suspend fun getNews(): Flow<List<Article>>

    suspend fun insertUser(user: User)

    suspend fun checkUser(email:String,password:String):Boolean

    fun getAllDataFromDatabase():  Flow<List<Article>>

    suspend fun updateFavoriteArticle(article: Article)

    fun getArticleByUrl(url: String): Article
}