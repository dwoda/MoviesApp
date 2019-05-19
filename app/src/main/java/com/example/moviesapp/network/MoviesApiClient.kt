package com.example.moviesapp.network

import com.example.moviesapp.network.ApiConstants.Urls.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiClient {
    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}