package com.example.moviesapp.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapp.presentation.MainViewModel

class MoviesListAdapter(
    fa: FragmentActivity,
    private val list: List<Fragment>) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

//    @BindingAdapter("listData")
//    fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
//        val adapter = recyclerView.adapter as MovieListAdapter
//        adapter.submitList(data) {
//            // scroll the list to the top after the diffs are calculated and posted
//            recyclerView.scrollToPosition(0)
//        }
//    }

}


