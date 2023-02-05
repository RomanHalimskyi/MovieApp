package com.example.moviesapp.di

import com.example.moviesapp.data.network.MovieDbApi
import com.example.moviesapp.data.repository.MovieDbRepositoryImpl
import com.example.moviesapp.domain.repository.MovieDbRepository

import com.example.moviesapp.presentation.DetailsViewModel
import com.example.moviesapp.presentation.MainViewModel
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun getInstance(): MovieDbApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()
                    val originalUrl = originalRequest.url
                    val newRequest=originalRequest.newBuilder().apply {
                        url(originalUrl.newBuilder().build())
                    }.build()
                    return chain.proceed(newRequest)
                }

            })
        }
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(MovieDbApi::class.java)
    }

    single { getInstance() }
}

val repositoryModule = module {
    factory<MovieDbRepository> { MovieDbRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel<MainViewModel> {
        MainViewModel(movieRepository = get())
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(movieRepository = get())
    }
}