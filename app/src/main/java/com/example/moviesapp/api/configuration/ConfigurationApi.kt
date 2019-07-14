package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.configuration.models.Configuration
import io.reactivex.Single
import retrofit2.http.GET

interface ConfigurationApi {
    @GET("configuration")
    fun getConfiguration(): Single<Configuration>
}