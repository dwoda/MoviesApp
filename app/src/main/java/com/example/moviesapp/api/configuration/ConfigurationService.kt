package com.example.moviesapp.api.configuration

import javax.inject.Inject

class ConfigurationService @Inject constructor(
    private val configurationApi: ConfigurationApi
) {
    fun getConfiguration() = configurationApi.getConfiguration()
}