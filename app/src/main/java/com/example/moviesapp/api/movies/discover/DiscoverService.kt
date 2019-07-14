package com.example.moviesapp.api.movies.discover

import javax.inject.Inject

class DiscoverService @Inject constructor(
    private val discoverApi: DiscoverApi
) {
    fun discoverMovies() = discoverApi.discoverMovies()
}