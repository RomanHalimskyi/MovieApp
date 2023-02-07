package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.models.moviesListModel.MoviesListDetail
import com.example.moviesapp.databinding.ListMoviesBinding
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.moviesapp.domain.util.Util
import com.squareup.picasso.Picasso


class ListOfMoviesAdapter(val listener: Listener): ListAdapter<MoviesListDetail, ListOfMoviesAdapter.Holder>(Comparator()) {

    class Holder(view: View, val listener: Listener): RecyclerView.ViewHolder(view){
        val binding = ListMoviesBinding.bind(view)
        var itemTemp: MoviesListDetail? = null

        init {
            itemView.setOnClickListener{
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }

        fun bind(item: MoviesListDetail) = with(binding) {
            itemTemp = item
            tvTitle.text = item.title
            tvRealeseDate.text = item.releaseDate
            tvMovieRaiting.text = item.voteAverage.toString()
            tvMovieGenre.text = item.genreIds.toString()
            val genres = item.let { Util.getGenresFromIds(item.genreIds) }
            tvMovieGenre.text = genres.toString().substring(1,genres.toString().lastIndex)
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + item.posterPath).into(im)
        }
    }

    class Comparator : ItemCallback<MoviesListDetail>(){
        override fun areItemsTheSame(
            oldItem: MoviesListDetail,
            newItem: MoviesListDetail,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MoviesListDetail,
            newItem: MoviesListDetail,
        ): Boolean {
            return oldItem.id == newItem.id

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movies, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    interface Listener{
        fun onClick(item: MoviesListDetail)
    }
}
