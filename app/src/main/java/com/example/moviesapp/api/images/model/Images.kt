package com.example.moviesapp.api.images.model

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("base_url")
    val baseUrl: String,

    @SerializedName("poster_sizes")
    val posterSizes: List<String>
)