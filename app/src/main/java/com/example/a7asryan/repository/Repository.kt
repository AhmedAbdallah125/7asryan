package com.example.a7asryan.repository

import com.example.a7asryan.local.Converter
import com.example.a7asryan.local.LocalInterface
import com.example.a7asryan.model.ApiClass
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.User
import com.example.a7asryan.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(private val local: LocalInterface, private val remote: RemoteSource) :
    IRepository {
    override suspend fun getNews(): Flow<List<Article>> {
        val response = getNewsFromRemote()
        if (response.isSuccessful) {
            response.body()?.articles?.let {

                for (article in it) {
                    insertNewsToLocal(Converter.converArticaleToEntity(article))
                }

            }
            return getAllDataFromDatabase()
        } else {
            throw Exception("${response.errorBody()}")
        }

    }

    override suspend fun updateFavoriteArticle(article: Article) {
        local.updateFavoriteArticle(article)
    }

    override suspend fun insertUser(user: User) {
        local.insertUser(user)
    }

    override suspend fun checkUser(email: String, password: String): Boolean {
        return local.checkUserExistence(email, password)
    }

    override fun getAllDataFromDatabase(): Flow<List<Article>> {
        return local.getAllDataFromDatabase()
    }

    override fun getFavouriteArticles(): Flow<List<Article>> {
        return local.getFavouriteArticles()
    }

    private suspend fun getNewsFromRemote(): Response<ApiClass> {
        return remote.getNews()
    }

    private suspend fun insertNewsToLocal(article: Article) {
        local.insertNews(article)
    }

    override fun getArticleByUrl(url: String): Article {
        return local.getArticleByUrl(url)
    }
}