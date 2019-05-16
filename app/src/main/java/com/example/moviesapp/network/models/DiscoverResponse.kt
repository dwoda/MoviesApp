package com.example.moviesapp.network.models

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @SerializedName("results")
    val results: List<Movie>
)

