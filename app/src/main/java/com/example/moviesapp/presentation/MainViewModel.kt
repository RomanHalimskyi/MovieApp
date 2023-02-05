package com.example.moviesapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.models.MoviesListModel.MoviesList
import com.example.moviesapp.data.models.MoviesListModel.MoviesListDetail
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieDbRepository): ViewModel() {

    private val _searchList = MutableLiveData<List<MoviesListDetail>>()
    val searchList: LiveData<List<MoviesListDetail>>
        get() = _searchList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getMoviesListResults(page: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val res = movieRepository.getMovieList(page = page)
                _searchList.value = res!!.moviesListDetails!!

                Log.e("AAA", "succses")
            } catch(e:Exception){
//                _message.value = "Something went wrong"
                Log.i("popular exeception", e.message.toString())
            }
            finally {
                _loading.value = false
            }
        }
    }

}