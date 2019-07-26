package com.example.moviesapp.api.movies.discover

import com.example.moviesapp.api.movies.discover.models.DiscoverMoviesPojo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DiscoverApi {
    @GET("discover/movie")
    fun discoverMovies(@QueryMap locale: Map<String, String>): Single<DiscoverMoviesPojo>
}