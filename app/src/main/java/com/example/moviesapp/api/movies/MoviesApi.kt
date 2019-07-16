package com.example.moviesapp.api.movies

import com.example.moviesapp.api.movies.models.Credits
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MoviesApi {
    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") id: Int,
        @QueryMap locale: Map<String, String>
    ): Single<MovieDetails>

    @GET("movie/{id}/images")
    fun getMovieImages(@Path("id") id: Int): Single<MovieImages>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int): Single<Credits>
}