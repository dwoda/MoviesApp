package com.example.moviesapp.services.favourites

import android.content.SharedPreferences
import javax.inject.Inject

class FavouritesService @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val favouritesKey = "FAVOURITES_SHARED_PREFERENCES_KEY"
    }

    fun toggleFavourite(movieId: Int) {
        val id = movieId.toString()
        val favourites = getFavourites()

        if (id in favourites) {
            favourites.removeAll { it == id }
        } else {
            favourites.add(id)
        }

        saveFavourites(favourites)
    }

    fun isFavourite(movieId: Int) = (movieId.toString() in getFavourites())

    private fun saveFavourites(favourites: List<String>) {
        sharedPreferences
            .edit()
            .putStringSet(favouritesKey, favourites.toSet())
            .apply()
    }

    private fun getFavourites() = sharedPreferences
        .getStringSet(favouritesKey, emptySet())!!
        .toMutableList()
}