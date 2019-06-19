package com.example.moviesapp.api

import com.example.moviesapp.BuildConfig

object ApiConstants {

    const val apiKey = BuildConfig.API_KEY

    object Urls {
        const val baseUrl = "https://api.themoviedb.org/3/"
        lateinit var imagesBaseUrl: String
    }
}