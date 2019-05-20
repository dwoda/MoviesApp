package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class MovieImages (
    @SerializedName("id")
    val id: Int,
    @SerializedName("posters")
    val posters: List<Image>
)