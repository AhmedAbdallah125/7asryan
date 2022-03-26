package com.example.a7asryan.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a7asryan.model.Article
import com.example.a7asryan.model.User

@Database(entities = [Article::class, User::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        @Synchronized
        fun getDataBase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }
}

