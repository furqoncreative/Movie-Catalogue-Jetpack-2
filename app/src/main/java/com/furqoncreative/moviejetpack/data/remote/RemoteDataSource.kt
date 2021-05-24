package com.furqoncreative.moviejetpack.data.remote

import com.furqoncreative.moviejetpack.BuildConfig.API_TOKEN
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.utils.EspressoIdlingResource
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: AppService
) {

    suspend fun getPopularMovies(callback: RemoteDataCallback<List<MovieEntity>>) {
        EspressoIdlingResource.increment()
        appService.getPopularMovies(API_TOKEN).await().results.let {
            if (it != null) {
                callback.onDataReceived(it)
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getMovie(id: Int?, callback: RemoteDataCallback<MovieEntity>) {
        EspressoIdlingResource.increment()
        appService.getMovie(id, API_TOKEN).await().let {
            callback.onDataReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getPopularTvShow(callback: RemoteDataCallback<List<TvShowEntity>>) {
        EspressoIdlingResource.increment()
        appService.getPopularTvShow(API_TOKEN).await().results.let {
            if (it != null) {
                callback.onDataReceived(it)
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getTvShow(id: Int?, callback: RemoteDataCallback<TvShowEntity>) {
        EspressoIdlingResource.increment()
        appService.getTvShow(id, API_TOKEN).await().let {
            callback.onDataReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

}