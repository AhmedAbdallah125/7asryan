package com.example.a7asryan.locale

import androidx.room.TypeConverter
import com.example.a7asryan.model.Article
import com.google.gson.Gson

class RoomConverter {
    @TypeConverter
    fun listOfAriticalesToJsoon (value: List<Article>) = Gson().toJson(value)

    @TypeConverter
    fun gsonToArticleList(value: String) = Gson().fromJson(value, Array<Article>::class.java).toList()
}