package com.example.jokes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokes.models.PersistedJoke
import com.example.jokes.database.dao.JokesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@Database(entities = [PersistedJoke::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): JokesDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java, "app_database"
            ).setQueryExecutor(Dispatchers.IO.asExecutor())
                .build().also {
                    instance = it
                }
        }
    }

}