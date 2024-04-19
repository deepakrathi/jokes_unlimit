package com.example.jokes

import android.app.Application
import com.example.jokes.database.AppDatabase
import com.example.jokes.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class JokesApplication : Application() {

    private val database by lazy { AppDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()

        val dbModule = module {
            single { database.userDao() }
        }

        startKoin{
            androidContext(this@JokesApplication)
            modules(KoinModules.repositoryModules)
            modules(KoinModules.viewModelModules)
            modules(dbModule)
        }
    }

}