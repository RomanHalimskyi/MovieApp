package com.example.moviesapp.presentation

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.MoviesApp
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.data.models.MoviesListModel.MoviesListDetail
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieDbRepository): ViewModel() {

    var page = 1
    var movieDbResponse: MoviesList? = null

    private val _searchList = MutableLiveData<MoviesList>()
    val searchList: LiveData<MoviesList>
        get() = _searchList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getPage(newPage: Int){
        page = newPage
        getMoviesListResults()
    }
    fun getMoviesListResults() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val res = movieRepository.getMovieList(page = page)

                page++

                if(movieDbResponse == null){
                    movieDbResponse = res

                    _searchList.value = movieDbResponse!!
                }else{
                    val oldMovies = movieDbResponse
                    val newMovies = res

                    oldMovies!!.moviesListDetails.addAll(newMovies!!.moviesListDetails)

                    _searchList.value = oldMovies!!
                }
                Log.e("AAA", "succses")
            } catch(e:Exception){
                Log.i("popular exeception", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }
}