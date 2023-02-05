package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MAIN
import com.example.moviesapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.databinding.FragmentMovieListBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener{
            requireActivity().findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_detailsFragment_to_movieListFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()

    }
}