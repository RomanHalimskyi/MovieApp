package com.example.moviesapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.models.moviesListModel.MoviesList
import com.example.moviesapp.domain.Resource
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieDbRepository): ViewModel() {

    var page = 1
    private var pagination = false
    private var movieDbResponse: Resource<MoviesList?>? = null

    private val _searchList = MutableLiveData<Resource<MoviesList?>?>()
    val searchList: LiveData<Resource<MoviesList?>?>
        get() = _searchList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun paginate(paginate: Boolean){
        pagination = paginate
    }
    fun getMoviesListResults() {
        viewModelScope.launch {
            _loading.value = true
            try {
                if(pagination){
                    page++
                }

                val res = movieRepository.getMovieList(page = page)

                if(movieDbResponse == null){
                    movieDbResponse = res
                    _searchList.value = movieDbResponse
                }else{
                    val oldMovies = movieDbResponse!!
                    if (oldMovies != res) {
                        oldMovies.data!!.moviesListDetails.addAll(res.data!!.moviesListDetails)
                    }
                    _searchList.value = oldMovies
                }
            } catch(e:Exception){
                Log.i("API LIST ERROR", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }
}