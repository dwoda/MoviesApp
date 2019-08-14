package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class GenrePojo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)