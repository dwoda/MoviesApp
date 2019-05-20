package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.configuration.models.Configuration
import com.example.moviesapp.api.ApiConstants
import retrofit2.Call
import retrofit2.http.GET

interface ConfigurationApi {

    @GET("configuration?api_key=${ApiConstants.apiKey}")
    fun getConfiguration(): Call<Configuration>
}