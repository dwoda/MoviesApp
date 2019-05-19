package com.example.moviesapp.network.models.configuration

import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("images")
    val images: Images
)