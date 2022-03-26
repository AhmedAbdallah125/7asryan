package com.example.a7asryan.local

import com.example.a7asryan.model.ApiClass
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.ArticleApi
import com.example.a7asryan.model.News

class Converter {

    companion object {

    fun fromNewsApiToEntity(news: ApiClass): News {
        val articlesApi = news.articles
        var entitiesArticales: ArrayList<Article> = arrayListOf()

        articlesApi?.let { articlesApiList ->
            for (articaleApi in articlesApiList) {
                converArticaleToEntity(articaleApi).let { entitiesArticales.add(it) }
            }

        }
        return News(articles = entitiesArticales)
    }
      private fun converArticaleToEntity(aricaleApi: ArticleApi): Article {
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


}

