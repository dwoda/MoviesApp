package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.ApiConstants.Urls.baseUrl
import com.example.moviesapp.api.movies.MoviesApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigurationApiClient {

    private val configurationApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ConfigurationApi::class.java)

    private fun getConfiguration() = configurationApi.getConfiguration()
}