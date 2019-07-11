package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<CastMember>,
    @SerializedName("crew")
    val crew: List<CrewMember>
)