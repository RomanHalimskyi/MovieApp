package com.example.moviesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviesapp.R

import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.FragmentMovieListBinding
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

        val k = mainViewModel.getMoviesListResults(5)
        mainViewModel.searchList.observe(this, Observer {
            it.get(1).overview
            Log.e("AAA", it.get(1).title)
        })

        detailViewModel.getMoviesListResults(586)
        val t = detailViewModel.getMoviesListResults(586)
        detailViewModel.searchList.observe(this, Observer {
            it.title
            Log.e("AAA", it.title)
        })

    }

    override fun onStart() {
        super.onStart()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main)
    }
}