package com.example.moviesapp.api

import com.example.moviesapp.BuildConfig

object ApiConstants {

    const val apiKey = BuildConfig.API_KEY

    object Urls {
        const val baseUrl = "https://api.themoviedb.org/3/"
        const val imagesBaseUrl = "http://image.tmdb.org/t/p" // TODO get from configuration API
    }
}