package com.example.a7asryan.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a7asryan.model.User

@Dao
interface NewsDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE userName=:userName And password=:password)")
    suspend fun checkUserExistence(userName: String, password: String): Boolean
}