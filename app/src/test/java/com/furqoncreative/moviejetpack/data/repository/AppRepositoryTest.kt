package com.furqoncreative.moviejetpack.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.data.local.DataDummy
import com.furqoncreative.moviejetpack.data.remote.RemoteDataCallback
import com.furqoncreative.moviejetpack.data.remote.RemoteDataSource
import com.furqoncreative.moviejetpack.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AppRepositoryTest : TestCase() {

    @get:Rule
    var instantTaskExecuteRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeAppRepository(remote)

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShows = DataDummy.generateDummyTvShows()
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Test
    fun testGetPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataCallback<List<MovieEntity>>)
                    .onDataReceived(dummyMovies)
                null
            }.`when`(remote).getPopularMovies(any())
            val movieEntities = LiveDataTestUtil.getValue(repository.getPopularMovies())
            verify(remote).getPopularMovies(any())
            Assert.assertNotNull(movieEntities)
            assertEquals(dummyMovies.size.toLong(), movieEntities.size.toLong())
        }

    }

    @Test
    fun testGetMovie() {
        CoroutineScope(Dispatchers.IO).launch {

            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataCallback<MovieEntity>)
                    .onDataReceived(dummyMovie)
                null
            }.`when`(remote).getMovie(eq(movieId), any())
            val movieEntities = LiveDataTestUtil.getValue(repository.getMovie(movieId))
            verify(remote).getMovie(eq(movieId), any())
            Assert.assertNotNull(movieEntities)
            assertEquals(dummyMovie.id, movieEntities.id)
        }
    }

    @Test
    fun testGetPopularTvShow() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataCallback<List<TvShowEntity>>)
                    .onDataReceived(dummyTvShows)
                null
            }.`when`(remote).getPopularTvShow(any())
            val tvShowEntities = LiveDataTestUtil.getValue(repository.getPopularTvShow())
            verify(remote).getPopularTvShow(any())
            Assert.assertNotNull(tvShowEntities)
            assertEquals(dummyTvShows.size.toLong(), dummyTvShows.size.toLong())
        }
    }

    @Test
    fun testGetTvShow() {
        CoroutineScope(Dispatchers.IO).launch {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataCallback<TvShowEntity>)
                    .onDataReceived(dummyTvShow)
                null
            }.`when`(remote).getTvShow(eq(tvShowId), any())
            val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShow(tvShowId))
            verify(remote).getTvShow(eq(tvShowId), any())
            Assert.assertNotNull(tvShowEntity)
            assertEquals(dummyTvShow.id, tvShowEntity.id)
        }
    }
}