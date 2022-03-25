package com.example.a7asryan.local

import android.content.Context
import com.example.a7asryan.model.News
import com.example.a7asryan.model.User
import kotlinx.coroutines.flow.Flow

class ConcreteLocal (
    private val context: Context,
    private val newsDao: NewsDao = DataBase.getDataBase(context).newsDao()
) : LocalInterface{
    override suspend fun insertUser(user: User) {
        newsDao.insertUser(user);
    }

    override suspend fun checkUserExistence(email: String, password: String): Boolean {
       return newsDao.checkUserExistence(email, password)
    }

    override fun getAllDataFromDatabase(): Flow<News> {
        return newsDao.getAllDataFromDatabase()
    }

    override suspend fun insertNews(news: News) {
        newsDao.insertNews(news)
    }
}