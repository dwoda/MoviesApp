package com.example.moviesapp.services.discover

import com.example.moviesapp.api.movies.discover.DiscoverApi
import com.example.moviesapp.configuration.DeviceConfiguration
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.services.favourites.FavouritesService
import io.reactivex.Single
import javax.inject.Inject

class DiscoverService @Inject constructor(
    private val discoverApi: DiscoverApi,
    private val deviceConfiguration: DeviceConfiguration,
    private val favouritesService: FavouritesService,
    private val discoverMoviesConverter: DiscoverMoviesConverter
) {

    val cachedMovies: MutableList<Movie> = mutableListOf()

    fun getMovies() = cachedMovies
        .takeIf { it.isNotEmpty() }
        ?.let { Single.just(it) }
        ?: fetchAndSaveMovies()

    fun updateFavourites() =
        cachedMovies.apply {
            forEach { updateFavouriteStatus(it) }
            sortBy { it.title }
            sortByDescending { it.isFavourite }
        }

    private fun updateFavouriteStatus(movie: Movie) =
        movie.apply {
            isFavourite = favouritesService.isFavourite(id)
        }

    private fun fetchAndSaveMovies() =
        discoverApi.discoverMovies(deviceConfiguration.locale)
            .flatMap { Single.just(discoverMoviesConverter.convertDiscoverMovies(it)) }
            .doOnSuccess { cachedMovies.addAll(it) }
}