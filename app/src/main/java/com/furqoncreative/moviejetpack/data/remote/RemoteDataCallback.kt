package com.furqoncreative.moviejetpack.data.remote

interface RemoteDataCallback<T> {
    fun onDataReceived(response: T)
}