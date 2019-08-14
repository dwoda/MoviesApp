package com.example.moviesapp.api.configuration.models

import com.google.gson.annotations.SerializedName

data class ConfigurationPojo(
    @SerializedName("images")
    val images: ImagesPojo,
    @SerializedName("change_keys")
    val changeKeys: List<String>
)