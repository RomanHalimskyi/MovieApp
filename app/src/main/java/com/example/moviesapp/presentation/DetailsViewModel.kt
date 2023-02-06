package com.example.moviesapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.models.MovieDetailsModel.MovieDetails
import com.example.moviesapp.data.models.MoviesListModel.MoviesListDetail
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsViewModel(
    private val movieRepository: MovieDbRepository
) : ViewModel() {

    private val _searchList = MutableLiveData<MovieDetails>()
    val searchList: LiveData<MovieDetails>
        get() = _searchList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val page = 1

    init {
        getMoviesListResults(586)
    }
    fun getMoviesListResults(page: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val res = movieRepository.getMovieDetail(id = page)
                _searchList.value = res!!
            } catch(e:Exception){

                Log.i("popular exeception", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }

}