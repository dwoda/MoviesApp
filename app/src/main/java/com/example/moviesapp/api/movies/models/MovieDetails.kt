package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("title")
    val title: String,
    @SerializedName("tagline")
    val tagline: String
)