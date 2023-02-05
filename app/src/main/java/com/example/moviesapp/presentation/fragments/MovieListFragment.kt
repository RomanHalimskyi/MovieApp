package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R

import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.presentation.MainViewModel
import com.example.moviesapp.presentation.adapters.ListOfMoviesAdapter
import com.example.moviesapp.presentation.adapters.MoviesListAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(){

    private lateinit var adapter: ListOfMoviesAdapter
    private lateinit var binding: FragmentMovieListBinding
    private val model: MainViewModel by activityViewModels()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.searchList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.next.setOnClickListener{
            requireActivity().findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_movieListFragment_to_detailsFragment)
        }

    }
    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = ListOfMoviesAdapter(null)
        rcView.adapter = adapter

    }
}