package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: String
)