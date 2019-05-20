package com.example.moviesapp.api.images

import com.example.moviesapp.api.ApiConstants.Urls.imagesBaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesApiClient {

    fun getClient(): Retrofit = Retrofit.Builder()
        .baseUrl(imagesBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}