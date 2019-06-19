package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants.Urls.baseUrl
import com.example.moviesapp.api.RetrofitBuilder
import javax.inject.Inject

class MoviesApiClient @Inject constructor(retrofitBuilder: RetrofitBuilder) {

    private val moviesApi = retrofitBuilder.getImplementation(baseUrl, MoviesApi::class.java)

    fun getMovieDetails(id: Int) = moviesApi.getMovieDetails(id)

    fun getMovies() = moviesApi.getMovies()

    fun getMovieImages(id: Int) = moviesApi.getMovieImages(id)
}