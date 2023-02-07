package com.example.moviesapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.models.movieDetailsModel.MovieDetails
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieRepository: MovieDbRepository
) : ViewModel() {

    private val _details = MutableLiveData<MovieDetails>()
    val details: LiveData<MovieDetails>
        get() = _details

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading


    fun getMoviesListResults(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val res = movieRepository.getMovieDetail(id = id)
                _details.value = res!!.data!!
            } catch(e:Exception){

                Log.i("API DETAILS ERROR", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }

}