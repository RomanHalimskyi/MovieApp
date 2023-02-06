package com.example.moviesapp.data.repository

import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.data.network.MovieDbApi
import com.example.moviesapp.domain.repository.MovieDbRepository

class MovieDbRepositoryImpl(
    private val movieDbApi: MovieDbApi
):MovieDbRepository {
    override suspend fun getMovieList(page: Int): MoviesList? {
        return movieDbApi.getMoviesListData(page)
    }

    override suspend fun getMovieDetail(id: Int): MovieDetails? {
        return movieDbApi.getMovieDetailData(id)
    }
}