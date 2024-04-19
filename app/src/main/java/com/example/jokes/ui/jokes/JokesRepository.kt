package com.example.jokes.ui.jokes

import com.example.jokes.models.Joke
import com.example.jokes.models.PersistedJoke
import com.example.jokes.database.dao.JokesDao
import com.example.jokes.network.JokesApiService
import com.example.jokes.network.NetworkResponse

class JokesRepository(
    private val jokesApiService: JokesApiService,
    private val jokesDao: JokesDao
) {

    suspend fun getJoke(): NetworkResponse<out Any> {
        try {
            val joke = jokesApiService.getJoke().body()
            joke?.let {
                joke.timestamp = System.currentTimeMillis()
                insertJokeToDb(it)
                return NetworkResponse.Success(joke)
            }
            return NetworkResponse.Failure(null)
        } catch (e: Exception) {
            return NetworkResponse.Failure(e)
        }
    }

    fun getDbJokes(): List<PersistedJoke> {
        return jokesDao.getJokes()
    }

    private fun insertJokeToDb(joke: Joke) {
        jokesDao.insert(joke.toPeristedJoke())
    }

}