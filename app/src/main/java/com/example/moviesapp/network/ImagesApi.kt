package com.example.moviesapp.network

import com.example.moviesapp.network.models.DiscoverMoviesResponse
import com.example.moviesapp.network.models.configuration.Configuration
import retrofit2.Call
import retrofit2.http.GET

interface ImagesApi {
    @GET("discover/movie?api_key=${ApiConstants.apiKey}}")
    fun getMovies(): Call<DiscoverMoviesResponse>

    @GET("configuration?api_key=${ApiConstants.apiKey}")
    fun getConfiguration(): Call<Configuration>



}


