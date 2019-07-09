package com.example.moviesapp.api.configuration

import com.example.moviesapp.api.movies.MoviesApi
import javax.inject.Inject

class ConfigurationService @Inject constructor(
    private val configurationApi: ConfigurationApi
) {
    fun getConfiguration() = configurationApi.getConfiguration()
}