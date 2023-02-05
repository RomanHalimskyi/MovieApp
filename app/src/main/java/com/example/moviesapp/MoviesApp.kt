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
//    startKoin {
//        androidContext(this@MyApp)
//        modules(listOf(viewModelModule, networkModule, movieDBModule, repositoryModule))
//    }
    override fun onCreate() {
    startKoin {
        androidContext(this@MoviesApp)
        modules(listOf(viewModelModule, networkModule, repositoryModule))
        //movieDBModule,
    }

//    val networkConstraints =
//        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
//    val movieWorker = PeriodicWorkRequest
//        .Builder(MovieWorker::class.java, 15, TimeUnit.MINUTES)
//        .setConstraints(networkConstraints)
//        .addTag("movie_work")
//        .build()
//    WorkManager.getInstance(applicationContext).enqueue(movieWorker)
        super.onCreate()
    }
}

