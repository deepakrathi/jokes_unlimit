package com.example.jokes.network

import com.example.jokes.models.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesApiService {

    /**
     * Server request to get one joke at a time, in json format by default,
     * if there are more formats further in future then format value needs to be passed
     */
    @GET("api?format=json")
    suspend fun getJoke(@Query("format") dataFormat: String = "json") : Response<Joke>

}