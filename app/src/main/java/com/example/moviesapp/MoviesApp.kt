package com.example.moviesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext

import android.widget.Toast
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import com.example.moviesapp.di.networkModule
import com.example.moviesapp.di.repositoryModule
import com.example.moviesapp.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class MoviesApp: Application() {
    override fun onCreate() {
    startKoin {
        androidContext(this@MoviesApp)
        modules(listOf(viewModelModule, networkModule, repositoryModule))
    }
        super.onCreate()
    }
}

