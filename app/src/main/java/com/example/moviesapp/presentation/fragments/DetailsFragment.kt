package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviesapp.R

import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.presentation.DetailsViewModel

import com.example.moviesapp.presentation.MainViewModel
import com.example.moviesapp.presentation.adapters.ListOfMoviesAdapter
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private  val model: DetailsViewModel by activityViewModels()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.searchList.observe(viewLifecycleOwner){

            binding.tvTitle.text = "rytufygihojpokyftudyiyg"
            binding.tvGenre.text = it.genres.toString()
            binding.tvDesctiptionFull.text = it.overview
            binding.tvTitle.text = it.voteAverage.toString()
            binding.tvRealeseYear.text = it.releaseDate.toString()
            binding.tvTitle.text = it.title

            Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath).into(binding.imageView2)

        }

        binding.back.setOnClickListener{
            requireActivity().findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_detailsFragment_to_movieListFragment)
        }

    }
}