package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class DiscoverMovies(
    @SerializedName("results")
    val results: List<Movie>
)

