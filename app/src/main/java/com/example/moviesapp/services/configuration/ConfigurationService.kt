package com.example.moviesapp.services.configuration

import com.example.moviesapp.api.configuration.ConfigurationApi
import com.example.moviesapp.configuration.ApiConfiguration
import javax.inject.Inject

class ConfigurationService @Inject constructor(
    private val configurationApi: ConfigurationApi,
    private val apiConfiguration: ApiConfiguration
) {
    fun setApiConfiguration() =
        configurationApi
            .getConfiguration()
            .doOnSuccess { apiConfiguration.setConfiguration(it) }
}