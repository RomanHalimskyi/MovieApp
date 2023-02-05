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
    private  val viewModel by viewModel<MainViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.placeHolder, MainFragment.newInstance())
//            .commit()
        Log.e("AAA", "asdfgewqaevdxvsdvx")

        val k = viewModel.getMoviesListResults(5)
        viewModel.searchList.observe(this, Observer {
            it.get(1).overview
            Log.e("AAA", it.get(1).title + "hfjdkls;")
        })

    }

    override fun onStart() {
        super.onStart()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main)
    }
}