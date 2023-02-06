package com.example.moviesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesListModel.MoviesListDetail

import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.FragmentMovieListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  val mainViewModel by viewModel<MainViewModel>()
    private  val detailViewModel by viewModel<DetailsViewModel>()

    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        mainViewModel.getMoviesListResults()
        mainViewModel.getPage(1)
        detailViewModel.getMoviesListResults(586)

    }

    override fun onStart() {
        super.onStart()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main)
    }
}