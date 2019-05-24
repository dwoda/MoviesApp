package com.example.moviesapp.api.movies

import com.example.moviesapp.api.ApiConstants.Urls.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MoviesApiClient @Inject constructor() {
    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}