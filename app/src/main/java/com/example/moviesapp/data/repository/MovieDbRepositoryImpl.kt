package com.example.moviesapp.data.repository

import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.data.network.MovieDbApi
import com.example.moviesapp.domain.repository.MovieDbRepository

class MovieDbRepositoryImpl(
    private val movieDbApi: MovieDbApi
):MovieDbRepository {
    override suspend fun getMovieList(page: Int): MoviesList? {
        return movieDbApi.getMoviesListData(page)
    }

//    suspend fun getSearchResults(query: String) : SearchResult?{
//        return imdbService.getSearchResults(query,true)
//    }
}