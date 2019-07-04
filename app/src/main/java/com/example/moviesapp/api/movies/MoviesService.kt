package com.example.moviesapp.api.movies

import javax.inject.Inject

class MoviesService @Inject constructor(
    private val moviesApi: MoviesApi
) {

    fun getMovieDetails(id: Int) = moviesApi.getMovieDetails(id)

    fun getMovies() = moviesApi.getMovies()

    fun getMovieImages(id: Int) = moviesApi.getMovieImages(id)
}