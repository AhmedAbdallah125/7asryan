package com.example.a7asryan.model


data class ApiClass(
    private val status: String,
    private val totalResults: Int,
     val articles: List<ArticleApi>?
)

data class ArticleApi(
     var source: Source?,
     var author: String?,
     val title: String?,
     val description: String?,
     val url: String?,
     val urlToImage: String?,
     val publishedAt: String?,
     val content: String?
)

data class Source(
     val id: String,
     val name: String
)
