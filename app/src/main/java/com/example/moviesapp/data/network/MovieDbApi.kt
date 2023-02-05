package com.example.moviesapp.data.network

import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/popular?api_key=652adc92a60c29fc5527d11f87144cb2&language=en-US")
    suspend fun getMoviesListData(
        @Query("page") page: Int
    ): MoviesList?

    @GET("movie/{movie_id}?api_key=652adc92a60c29fc5527d11f87144cb2")
    suspend fun getMovieDetailData(
        @Path("movie_id") id: Int
    ): MovieDetails?

}