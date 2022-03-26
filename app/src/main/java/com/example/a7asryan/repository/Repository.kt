package com.example.a7asryan.repository

import android.content.Context
import com.example.a7asryan.local.Converter
import com.example.a7asryan.local.LocalInterface
import com.example.a7asryan.model.ApiClass
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.News
import com.example.a7asryan.model.User
import com.example.a7asryan.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(val local :LocalInterface,val remote :RemoteSource) : IRepository {
    override suspend fun getNews() :Flow<News>{
        val response = getNewsFromRemote()
        if (response.isSuccessful) {
            response.body()?.let {

                insertNewsToLocal(Converter.fromNewsApiToEntity(it))
            }
            return getAllDataFromDatabase()
        } else {
            throw Exception("${response.errorBody()}")
        }

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

    override fun getAllDataFromDatabase(): Flow<News> {
        return  local.getAllDataFromDatabase()
    }

    private suspend fun getNewsFromRemote(): Response<ApiClass> {
        return remote.getNews()
    }

    private suspend fun insertNewsToLocal(news: News) {
        local.insertNews(news)
    }


}