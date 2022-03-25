package com.example.a7asryan.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a7asryan.model.User

@Dao
interface NewsDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email=:email And password=:password)")
    suspend fun checkUserExistence(email: String, password: String): Boolean
}