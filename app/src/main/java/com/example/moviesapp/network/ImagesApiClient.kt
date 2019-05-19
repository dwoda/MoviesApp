package com.example.moviesapp.network

import com.example.moviesapp.network.ApiConstants.Urls.imagesBaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesApiClient {

    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(imagesBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}