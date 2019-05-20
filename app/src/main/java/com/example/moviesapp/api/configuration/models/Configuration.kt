package com.example.moviesapp.api.configuration.models

import com.example.moviesapp.api.images.model.Images
import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("images")
    val images: Images
)