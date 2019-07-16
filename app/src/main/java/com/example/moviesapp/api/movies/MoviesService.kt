package com.example.moviesapp.api.movies

import com.example.moviesapp.configuration.DeviceConfiguration
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val moviesApi: MoviesApi,
    private val deviceConfiguration: DeviceConfiguration
) {

    fun getMovieDetails(id: Int) = moviesApi.getMovieDetails(id, deviceConfiguration.locale)

    fun getMovieImages(id: Int) = moviesApi.getMovieImages(id)

    fun getMovieCredits(id: Int) = moviesApi.getMovieCredits(id)
}