package com.example.a7asryan.locale

import com.example.a7asryan.model.ApiClass
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.ArticleApi
import com.example.a7asryan.model.News

class Converter {
    fun fromNewsApiToEntity(news: ApiClass): News {
        val articlesApi = news.articles
        var entitiesAriticales: ArrayList<Article> = arrayListOf()

        if (articlesApi != null) {
            for (aricaleApi: ArticleApi in articlesApi) {
                converArticaleToEntity(aricaleApi)?.let { entitiesAriticales.add(it) }
            }
        }
        return News(articles = entitiesAriticales)
    }

    fun converArticaleToEntity(aricaleApi: ArticleApi): Article {
        return Article(false,
            aricaleApi.source?.name,
            aricaleApi.author,
            aricaleApi.title,
            aricaleApi.description,
            aricaleApi.url,
            aricaleApi.urlToImage,
            aricaleApi.publishedAt,
            aricaleApi.content
        )
    }

}

