package com.example.moviesapp

import com.example.moviesapp.data.models.moviesListModel.MoviesList
import com.example.moviesapp.domain.repository.MovieDbRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest() {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}