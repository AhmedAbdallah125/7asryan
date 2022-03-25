package com.example.a7asryan.model

class Converter {
    fun fromNewsApiToEntity(news: ApiClass): News {
        val articlesApi = news.articles
        var entitiesAriticales: ArrayList<Article> = arrayListOf()

        for (aricaleApi: ArticleApi in articlesApi!!) {
            converArticaleToEntity(aricaleApi)?.let { entitiesAriticales.add(it) }
        }
        return News(0, entitiesAriticales)
    }

    fun converArticaleToEntity(aricaleApi: ArticleApi): Article? {
        var article: Article? = null
        article?.sourceName = aricaleApi.source?.name
        article?.author = aricaleApi.author
        article?.description = aricaleApi.description
        article?.content = aricaleApi.content
        article?.publishedAt = aricaleApi.publishedAt
        article?.title = aricaleApi.title
        article?.url = aricaleApi.url
        article?.urlToImage = aricaleApi.urlToImage

        return article?.let { it }
    }

}

