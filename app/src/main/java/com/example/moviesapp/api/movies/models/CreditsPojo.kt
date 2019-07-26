package com.example.moviesapp.api.movies.models

import com.google.gson.annotations.SerializedName

data class CreditsPojo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<CastMemberPojo>,
    @SerializedName("crew")
    val crew: List<CrewMemberPojo>
)