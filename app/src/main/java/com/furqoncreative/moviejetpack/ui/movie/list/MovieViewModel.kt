package com.furqoncreative.moviejetpack.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = repository.getPopularMovies()

}