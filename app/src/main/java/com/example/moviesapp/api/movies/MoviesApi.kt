package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {

    @GET("discover/movie?api_key=${ApiConstants.apiKey}}")
    fun getMovies(): Call<DiscoverMoviesResponse>
}


