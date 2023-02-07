package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.models.movieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.moviesListModel.MoviesList
import com.example.moviesapp.domain.Resource

interface MovieDbRepository {
    suspend fun getMovieList(page: Int): Resource<MoviesList?>

    suspend fun getMovieDetail(id: Int): Resource<MovieDetails?>
}