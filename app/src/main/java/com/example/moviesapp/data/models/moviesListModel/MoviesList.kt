package com.example.moviesapp.data.models.moviesListModel


import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val moviesListDetails: MutableList<MoviesListDetail>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)