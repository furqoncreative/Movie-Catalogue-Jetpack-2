package com.furqoncreative.moviejetpack.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.data.local.DataDummy
import com.furqoncreative.moviejetpack.data.repository.AppRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {
    private lateinit var viewModel: TvShowDetailViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(repository)
    }

    @Test
    fun testGetTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(repository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = tvShowId?.let { viewModel.getTvShow(it).value } as TvShowEntity
        verify(repository).getTvShow(tvShowId)

        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(dummyTvShow.id, tvShowEntity.id)
        Assert.assertEquals(dummyTvShow.backdropPath, tvShowEntity.backdropPath)
        Assert.assertEquals(dummyTvShow.name, tvShowEntity.name)
        Assert.assertEquals(dummyTvShow.voteAverage, tvShowEntity.voteAverage, 0.0)
        Assert.assertEquals(dummyTvShow.firstAirDate, tvShowEntity.firstAirDate)
        Assert.assertEquals(dummyTvShow.overview, tvShowEntity.overview)

        viewModel.getTvShow(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}