package com.example.moviesapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiClient {

    companion object {
        const val baseUrl = "https://api.themoviedb.org/3/"
    }

    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}