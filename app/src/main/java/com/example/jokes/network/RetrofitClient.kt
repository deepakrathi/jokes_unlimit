package com.example.jokes.network

import com.example.jokes.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton retrofit client object class
 */
object RetrofitClient {

    fun create() : JokesApiService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(JokesApiService::class.java)
    }

}