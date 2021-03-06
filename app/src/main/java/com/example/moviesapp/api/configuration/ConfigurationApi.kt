package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.configuration.models.ConfigurationPojo
import io.reactivex.Single
import retrofit2.http.GET

interface ConfigurationApi {
    @GET("configuration")
    fun getConfiguration(): Single<ConfigurationPojo>
}