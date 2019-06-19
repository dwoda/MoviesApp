package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.ApiConstants.Urls.baseUrl
import com.example.moviesapp.api.RetrofitBuilder
import javax.inject.Inject

class ConfigurationApiClient @Inject constructor(retrofitBuilder: RetrofitBuilder) {

    private val configurationApi = retrofitBuilder.getImplementation(baseUrl, ConfigurationApi::class.java)

    private fun getConfiguration() = configurationApi.getConfiguration()
}