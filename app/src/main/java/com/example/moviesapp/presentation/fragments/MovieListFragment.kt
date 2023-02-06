package com.example.moviesapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.models.MoviesListModel.MoviesListDetail
import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.domain.util.Constants.ITEM_COUNT
import com.example.moviesapp.presentation.MainViewModel
import com.example.moviesapp.presentation.adapters.ListOfMoviesAdapter


class MovieListFragment : Fragment(), ListOfMoviesAdapter.Listener{

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



        model.getPage(1)

        val k = adapter.itemCount

        model.searchList.observe(viewLifecycleOwner){
            adapter.submitList(it!!.data!!.moviesListDetails.toList())

            if(it.data != null){
                Toast.makeText(requireActivity(), "loaded successfully", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
            }

            val totalPages = it.data!!.totalResults / ITEM_COUNT + 2
            isLastPage = model.page == totalPages
        }

        model.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            } else{
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    private fun initRcView() = with(binding){
        rcView.layoutManager = GridLayoutManager(activity, 2)
        adapter = ListOfMoviesAdapter(this@MovieListFragment)
        rcView.adapter = adapter
        rcView.addOnScrollListener(this@MovieListFragment.scrollListener)
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    
    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0

            val isTotalMoreThatVisible = totalItemCount >= ITEM_COUNT

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThatVisible && isScrolling

            if(shouldPaginate) {
                model.getMoviesListResults()
                isScrolling = false
            } else{
                recyclerView.setPadding(0,0,0,0)
            }
        }
    }

    override fun onClick(item: MoviesListDetail) {
        val bundle = Bundle()
        bundle.putInt("SelectedMovieId", item.id )
        item.id

        requireActivity().findNavController(R.id.nav_host_fragment_main).navigate(R.id.action_movieListFragment_to_detailsFragment, bundle)
    }
}