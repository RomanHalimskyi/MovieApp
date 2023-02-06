package com.example.moviesapp.data.repository

import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.data.network.MovieDbApi
import com.example.moviesapp.domain.Resource
import com.example.moviesapp.domain.repository.MovieDbRepository

class MovieDbRepositoryImpl(
    private val movieDbApi: MovieDbApi
):MovieDbRepository {
    override suspend fun getMovieList(page: Int): Resource<MoviesList?> {
        return try{
            Resource.Success(
                data = movieDbApi.getMoviesListData(page = page)
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetails?> {
        return try{
            Resource.Success(data = movieDbApi.getMovieDetailData(id =id)
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}