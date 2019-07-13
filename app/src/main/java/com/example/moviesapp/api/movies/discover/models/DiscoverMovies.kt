package com.example.moviesapp.api.movies.discover.models

import com.google.gson.annotations.SerializedName

data class DiscoverMovies(
    @SerializedName("results")
    val results: List<Movie>
)