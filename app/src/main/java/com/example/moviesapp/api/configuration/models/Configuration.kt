package com.example.moviesapp.api.configuration.models

import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("images")
    val images: Images,
    @SerializedName("change_keys")
    val changeKeys: List<String>
)