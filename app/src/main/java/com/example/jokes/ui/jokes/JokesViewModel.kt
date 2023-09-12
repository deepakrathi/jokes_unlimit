package com.example.jokes.ui.jokes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokes.extensions.launch
import com.example.jokes.models.Joke
import com.example.jokes.network.NetworkResponse
import kotlinx.coroutines.delay

class JokesViewModel(
    private val jokesRepository: JokesRepository,
) : ViewModel() {

    val joke = MutableLiveData<Joke>()
    val localDbJokes = MutableLiveData<List<Joke>>()

    init {
        launch {
            getDbJokes()
            getJoke()
        }
    }

    private fun getJoke() {
        launch {
            while (true) {
                jokesRepository.getJoke().let {
                    when (it) {
                        is NetworkResponse.Success -> {
                            joke.postValue(it.data as Joke)
                        }

                        is NetworkResponse.Failure -> {
                            //handle failure with common/specific logic
                            Log.e("network-response", it.toString())
                        }
                    }
                }
                delay(5000)
            }
        }
    }


    private fun getDbJokes() {
        launch {
            val jokes = jokesRepository.getDbJokes()
            localDbJokes.postValue(jokes.map { it.toJoke() })
        }
    }

}