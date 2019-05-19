package com.example.moviesapp.network

import com.example.moviesapp.network.models.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("discover/movie?api_key=4b25d5b4a04998375abc2359d7cc8e13")
    fun getMovies(): Call<DiscoverMoviesResponse>
}


