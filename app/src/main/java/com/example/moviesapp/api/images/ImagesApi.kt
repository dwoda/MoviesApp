package com.example.moviesapp.api.images

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.DiscoverMoviesResponse
import com.example.moviesapp.api.configuration.models.Configuration
import retrofit2.Call
import retrofit2.http.GET

interface ImagesApi {
    @GET("discover/movie?api_key=${ApiConstants.apiKey}}")
    fun getImage(): Call<DiscoverMoviesResponse>

    @GET("configuration?api_key=${ApiConstants.apiKey}")
    fun getConfiguration(): Call<Configuration>
}


