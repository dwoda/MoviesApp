package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.DiscoverMovies
import com.example.moviesapp.api.movies.models.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesApi {
    @GET("discover/movie?api_key=${ApiConstants.apiKey}")
    fun getMovies(): Single<DiscoverMovies>

    @GET("movie/{id}?api_key=${ApiConstants.apiKey}")
    fun getMovieDetails(@Path("id") id: Int): Single<MovieDetails>
}


