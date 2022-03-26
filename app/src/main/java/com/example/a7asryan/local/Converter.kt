package com.example.a7asryan.local

import com.example.a7asryan.model.Article
import com.example.a7asryan.model.ArticleApi

class Converter {

    companion object {


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


}

