package com.example.localData

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a7asryan.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class DataBase: RoomDatabase() {
    //abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        @Synchronized
        fun getDataBase(application: Application): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    DataBase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }
}

