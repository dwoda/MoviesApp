package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("title")
    val title: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("genres")
    val genres: List<Genre>
)