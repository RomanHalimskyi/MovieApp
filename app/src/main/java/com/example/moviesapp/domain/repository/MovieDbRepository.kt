package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.domain.Resource

interface MovieDbRepository {
    suspend fun getMovieList(page: Int): Resource<MoviesList?>

    suspend fun getMovieDetail(id: Int): Resource<MovieDetails?>
}