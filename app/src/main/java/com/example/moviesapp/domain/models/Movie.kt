package com.example.moviesapp.domain.models

data class Movie(
    val title: String,
    val rating: Double,
    val id: Int,
    var isFavourite: Boolean = false
)