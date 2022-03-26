package com.example.a7asryan.local

import com.example.a7asryan.model.Article
import com.example.a7asryan.model.User
import kotlinx.coroutines.flow.Flow

interface LocalInterface {
    suspend fun insertUser(user: User)
    suspend fun checkUserExistence(email: String, password: String): Boolean
    fun getAllDataFromDatabase():  Flow<List<Article>>
    suspend fun insertNews(news: Article)
}