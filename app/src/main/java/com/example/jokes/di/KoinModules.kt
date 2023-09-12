package com.example.jokes.di

import com.example.jokes.network.RetrofitClient
import com.example.jokes.ui.jokes.JokesRepository
import com.example.jokes.ui.jokes.JokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    val repositoryModules = module {
        single { RetrofitClient.create() }
        single { JokesRepository(get(), get()) }
    }

    val viewModelModules = module {
        viewModel { JokesViewModel(get()) }
    }
}