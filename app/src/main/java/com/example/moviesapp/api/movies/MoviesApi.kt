package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.movies.models.Credits
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movie/{id}?api_key=${ApiConstants.apiKey}")
    fun getMovieDetails(@Path("id") id: Int): Single<MovieDetails>

    @GET("movie/{id}/images?api_key=${ApiConstants.apiKey}")
    fun getMovieImages(@Path("id") id: Int): Single<MovieImages>

    @GET("movie/{id}/credits?api_key=${ApiConstants.apiKey}")
    fun getMovieCredits(@Path("id") id: Int): Single<Credits>
}