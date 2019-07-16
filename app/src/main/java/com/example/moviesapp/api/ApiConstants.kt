package com.example.moviesapp.api

import com.example.moviesapp.BuildConfig
import javax.inject.Inject

class ApiConstants @Inject constructor() {
    val apiKey = BuildConfig.API_KEY
}