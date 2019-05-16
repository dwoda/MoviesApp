package com.example.moviesapp.network.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val title: String
)