package com.example.moviesapp.services.discover

import com.example.moviesapp.api.movies.discover.models.DiscoverMoviesPojo
import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.services.favourites.FavouritesService
import javax.inject.Inject

class DiscoverMoviesConverter @Inject constructor(private val favouritesService: FavouritesService) {

    fun convertDiscoverMovies(discoverMoviesPojo: DiscoverMoviesPojo) =
        discoverMoviesPojo
            .results
            .map {
                Movie(it.title, it.rating, it.id, favouritesService.isFavourite(it.id))
            }
            .sortedBy { it.title }
            .sortedByDescending { it.isFavourite }
}