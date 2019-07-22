package com.example.moviesapp.services.storage

import android.content.SharedPreferences
import javax.inject.Inject

class StorageService @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val favouritesKey = "FAVOURITES_SHARED_PREFERENCES_KEY"
    }

    fun getFavourites() = sharedPreferences
        .getStringSet(favouritesKey, emptySet())!!
        .toMutableList()

    fun saveFavourites(favourites: List<String>) {
        sharedPreferences
            .edit()
            .putStringSet(favouritesKey, favourites.toSet())
            .commit()
    }
}