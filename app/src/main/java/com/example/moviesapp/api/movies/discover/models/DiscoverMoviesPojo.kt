package com.example.moviesapp.api.movies.discover.models

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesPojo(
    @SerializedName("results")
    val results: List<MoviePojo>
)