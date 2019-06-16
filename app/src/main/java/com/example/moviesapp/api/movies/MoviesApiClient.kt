package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants.Urls.baseUrl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MoviesApiClient @Inject constructor() {

    private fun getMoviesApi(): MoviesApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MoviesApi::class.java)

    fun getMovieDetails(id: Int) = getMoviesApi().getMovieDetails(id)

    fun getMovies() = getMoviesApi().getMovies()

    fun getMovieImages(id: Int) = getMoviesApi().getMovieImages(id)
}