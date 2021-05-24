package com.furqoncreative.moviejetpack.ui.tvshow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = repository.getPopularTvShow()

}