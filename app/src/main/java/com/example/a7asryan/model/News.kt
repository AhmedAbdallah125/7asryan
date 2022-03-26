package com.example.a7asryan.model

import android.icu.text.CaseMap
import android.widget.HeaderViewListAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class Article(
    var isFavourite: Boolean = false,
    var sourceName: String?,
    var  author: String?,
    var title: String?,
    var description: String?,
    @PrimaryKey
    var url: String ="",
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)

