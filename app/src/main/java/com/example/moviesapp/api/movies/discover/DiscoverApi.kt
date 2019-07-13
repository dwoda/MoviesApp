package com.example.moviesapp.api.movies.discover

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.discover.models.DiscoverMovies
import io.reactivex.Single
import retrofit2.http.GET

interface DiscoverApi {
    @GET("discover/movie?api_key=${ApiConstants.apiKey}")
    fun discoverMovies(): Single<DiscoverMovies>
}