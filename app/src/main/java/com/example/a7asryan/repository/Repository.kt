package com.example.a7asryan.repository

import android.content.Context
import com.example.a7asryan.local.LocalInterface
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.News
import com.example.a7asryan.model.User
import retrofit2.Response

class Repository(val local :LocalInterface) : IRepository {
    override suspend fun getNews() {
        val response = getNewsFromRemote()
        if (response.isSuccessful) {
            response.body()?.let {
                insertNewsToLocal(it)
            }
        } else {
            throw Exception("${response.errorBody()}")
        }
        getNewsFromLocal()
    }

    override suspend fun addToFavorite(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorite(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: User) {
        local.insertUser(user)
    }

    override suspend fun checkUser(email: String,password:String):Boolean{
       return local.checkUserExistence(email,password)
    }

    private suspend fun getNewsFromRemote(): Response<News> {
        TODO("Not yet implemented")
    }

    private suspend fun insertNewsToLocal(news: News) {
        TODO("Not yet implemented")
    }

    private suspend fun getNewsFromLocal() {
        TODO("Not yet implemented")
    }
}