package com.example.a7asryan.model

import android.icu.text.CaseMap
import android.widget.HeaderViewListAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(

    @PrimaryKey(autoGenerate = true)
    private val id: Int?,
    private val articles: List<Article>?
)

data class Article(
    private var isFavourite: Boolean = false,
    private val sourceName: String?,
    private val author: String?,
    private val title: String?,
    private val description: String?,
    private val url: String?,
    private val urlToImage: String?,
    private val publishedAt: String?,
    private val content: String?
)

