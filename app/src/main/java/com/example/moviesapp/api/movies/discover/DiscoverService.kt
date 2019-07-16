package com.example.moviesapp.api.movies.discover

import com.example.moviesapp.configuration.DeviceConfiguration
import javax.inject.Inject

class DiscoverService @Inject constructor(
    private val discoverApi: DiscoverApi,
    private val deviceConfiguration: DeviceConfiguration
) {

    fun discoverMovies() = discoverApi.discoverMovies(deviceConfiguration.locale)
}