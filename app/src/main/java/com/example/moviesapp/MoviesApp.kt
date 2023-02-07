package com.example.moviesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import com.example.moviesapp.di.networkModule
import com.example.moviesapp.di.repositoryModule
import com.example.moviesapp.di.viewModelModule
import org.koin.core.context.startKoin

class MoviesApp: Application() {
    override fun onCreate() {
    startKoin {
        androidContext(this@MoviesApp)
        modules(listOf(viewModelModule, networkModule, repositoryModule))
    }
        super.onCreate()
    }
}

