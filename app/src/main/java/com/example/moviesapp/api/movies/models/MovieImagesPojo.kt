package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class MovieImagesPojo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrops")
    val backdrops: List<ImagePojo>,
    @SerializedName("posters")
    val posters: List<ImagePojo>
)