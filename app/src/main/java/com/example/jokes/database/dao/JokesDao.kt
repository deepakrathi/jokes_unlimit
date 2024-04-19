package com.example.jokes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jokes.models.PersistedJoke

@Dao
interface JokesDao {

    @Query("SELECT * FROM persistedjoke order by id desc limit 10")
    fun getJokes(): List<PersistedJoke>

    @Insert
    fun insert(joke : PersistedJoke)

}