package com.example.moviesapp.api

object ApiConstants {

    const val apiKey = "4b25d5b4a04998375abc2359d7cc8e13" //extract to BuildConfig

    object Urls {
        val baseUrl = "https://api.themoviedb.org/3/"
        lateinit var imagesBaseUrl: String
    }
}