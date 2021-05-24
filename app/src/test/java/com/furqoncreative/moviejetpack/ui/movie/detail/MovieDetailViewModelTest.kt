package com.furqoncreative.moviejetpack.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.data.local.DataDummy
import com.furqoncreative.moviejetpack.data.repository.AppRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)
    }

    @Test
    fun testGetMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(repository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = movieId?.let { viewModel.getMovie(it).value } as MovieEntity
        verify(repository).getMovie(movieId)

        Assert.assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.voteAverage, movieEntity.voteAverage, 0.0)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        viewModel.getMovie(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}