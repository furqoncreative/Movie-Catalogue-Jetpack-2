package com.furqoncreative.moviejetpack.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.furqoncreative.moviejetpack.data.entity.movie.MovieEntity
import com.furqoncreative.moviejetpack.data.entity.tvshow.TvShowEntity
import com.furqoncreative.moviejetpack.data.remote.RemoteDataCallback
import com.furqoncreative.moviejetpack.data.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {

    fun getPopularMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataCallback<List<MovieEntity>> {
                override fun onDataReceived(response: List<MovieEntity>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (data in response) {
                        val movie = MovieEntity(
                            data.overview,
                            data.title,
                            data.posterPath,
                            data.backdropPath,
                            data.releaseDate,
                            data.voteAverage,
                            data.id
                        )
                        movieList.add(movie)
                    }
                    movieResult.postValue(movieList.take(10))
                }
            })
        }
        return movieResult
    }

    fun getMovie(id: Int?): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovie(id, object : RemoteDataCallback<MovieEntity> {
                override fun onDataReceived(response: MovieEntity) {
                    val movie = MovieEntity(
                        response.overview,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.id
                    )
                    movieResult.postValue(movie)
                }
            })
        }
        return movieResult
    }

    fun getPopularTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTvShow(object : RemoteDataCallback<List<TvShowEntity>> {
                override fun onDataReceived(response: List<TvShowEntity>) {
                    val tvShowist = ArrayList<TvShowEntity>()
                    for (data in response) {
                        val tvShow = TvShowEntity(
                            data.overview,
                            data.name,
                            data.posterPath,
                            data.backdropPath,
                            data.firstAirDate,
                            data.voteAverage,
                            data.id
                        )
                        tvShowist.add(tvShow)
                    }
                    tvShowResult.postValue(tvShowist.take(10))
                }
            })
        }
        return tvShowResult
    }

    fun getTvShow(id: Int?): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShow(id, object : RemoteDataCallback<TvShowEntity> {
                override fun onDataReceived(response: TvShowEntity) {
                    val tvShow = TvShowEntity(
                        response.overview,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.id
                    )
                    tvShowResult.postValue(tvShow)
                }
            })
        }
        return tvShowResult
    }
}