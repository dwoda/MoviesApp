package com.example.moviesapp.apiconfiguration

import com.example.moviesapp.api.configuration.models.Configuration
import com.example.moviesapp.api.configuration.models.Images
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConfiguration @Inject constructor() {

    lateinit var images: Images

    fun setConfiguration(configuration: Configuration) {
        images = configuration.images
    }
}