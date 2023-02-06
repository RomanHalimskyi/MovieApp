package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviesapp.R

import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.domain.util.Constants.IMAGE_BASE_URL
import com.example.moviesapp.domain.util.Util
import com.example.moviesapp.presentation.DetailsViewModel

import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private  val model: DetailsViewModel by activityViewModels()
    lateinit var navController: NavController

    private var genres: ArrayList<String>? = null


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

        val id = arguments?.getInt("SelectedMovieId")
        binding.tvGenre.text = id.toString()

        model.getMoviesListResults(id = id!!)

        model.searchList.observe(viewLifecycleOwner){
            binding.tvTitle.text = it.title

            genres = it.genres?.let { Util.getGenres(it) }
            binding.tvGenre.text = genres.toString().substring(1,genres.toString().lastIndex)

            binding.tvRaiting.text = it.voteAverage.toString()
            binding.tvDesctiptionFull.text = it.overview
            binding.tvTitle.text = it.voteAverage.toString()
            binding.tvRealeseYear.text = it.releaseDate.toString()
            binding.tvTitle.text = it.title

            Picasso.get().load(IMAGE_BASE_URL + it.posterPath).into(binding.imageView2)
        }

        model.loading.observe(viewLifecycleOwner){
            if(it){
                binding.cl.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else{
                binding.progressBar.visibility = View.GONE
                binding.cl.visibility = View.VISIBLE
            }
        }
    }
}