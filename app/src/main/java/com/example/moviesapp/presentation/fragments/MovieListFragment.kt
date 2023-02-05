package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R

import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.presentation.MainViewModel
import com.example.moviesapp.presentation.adapters.ListOfMoviesAdapter

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
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()

        val k = adapter.itemCount
        model.searchList.observe(viewLifecycleOwner){
            Log.e("AAA", it[5].title)
            adapter.submitList(it)
        }

        binding.next.setOnClickListener{
            requireActivity().findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_movieListFragment_to_detailsFragment)
        }


    }
    private fun initRcView() = with(binding){
        rcView.layoutManager = GridLayoutManager(activity, 2)
        adapter = ListOfMoviesAdapter(null)
        rcView.adapter = adapter

    }
}