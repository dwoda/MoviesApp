package com.example.moviesapp.api.movies.discover

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.Credits
import com.example.moviesapp.api.movies.discover.models.DiscoverMovies
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DiscoverApi {
    @GET("discover/movie?api_key=${ApiConstants.apiKey}")
    fun discoverMovies(): Single<DiscoverMovies>
}


