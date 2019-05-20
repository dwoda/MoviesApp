package com.example.moviesapp.api.images

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.DiscoverMovies
import retrofit2.Call
import retrofit2.http.GET

interface ImagesApi {

    @GET("discover/movie?api_key=${ApiConstants.apiKey}}")
    fun getImage(): Call<DiscoverMovies>
}


