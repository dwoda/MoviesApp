package com.example.moviesapp.api.configuration.models

import com.google.gson.annotations.SerializedName

data class ImagesConfiguration(

    @SerializedName("base_url")
    val baseUrl: String,

    @SerializedName("poster_sizes")
    val posterSizes: List<String>
)