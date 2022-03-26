package com.example.a7asryan.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert
    suspend fun insertUser(user: User)
    @Insert(onConflict = REPLACE)
    suspend fun insertNews(news: Article)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email=:email And password=:password)")
    suspend fun checkUserExistence(email: String, password: String): Boolean

    @Query("SELECT * FROM news")
    fun getAllDataFromDatabase():  Flow<List<Article>>
}