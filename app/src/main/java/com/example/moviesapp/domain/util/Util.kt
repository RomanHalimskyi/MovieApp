package com.example.moviesapp.domain.util

import com.example.moviesapp.data.models.movieDetailsModel.Genre

object Util {

    fun getGenres(ids: List<Genre>): ArrayList<String> {
        var genres = ArrayList<String>()

        for(i in ids){
            genres.add(i.name)
        }

        return genres
    }

    fun getGenresFromIds(ids: List<Int>): ArrayList<String> {
        val map = HashMap<Int, String>()
        map[28] = "Action"
        map[12] = "Adventure"
        map[16] = "Animation"
        map[35] = "Comedy"
        map[80] = "Crime"
        map[99] = "Documentary"
        map[18] = "Drama"
        map[10751] = "Family"
        map[14] = "Fantasy"
        map[36] = "History"
        map[27] = "Horror"
        map[10402] = "Music"
        map[9648] = "Mystery"
        map[10749] = "Romance"
        map[878] = "Science Fiction"
        map[10770] = "TV Movie"
        map[53] = "Thriller"
        map[10752] = "War"
        map[37] = "Western"
        val genres = ArrayList<String>()
        for (i in ids) {
            val item = map[i]
            if (item != null) {
                genres.add(item)
            }
        }
        return genres
    }
}