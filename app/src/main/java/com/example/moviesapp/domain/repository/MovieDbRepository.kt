package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesList

interface MovieDbRepository {
    suspend fun getMovieList(page: Int): MoviesList?

    suspend fun getMovieDetail(id: Int): MovieDetails?
}