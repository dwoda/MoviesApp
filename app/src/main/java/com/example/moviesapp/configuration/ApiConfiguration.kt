package com.example.moviesapp.configuration

import com.example.moviesapp.api.configuration.models.Configuration
import com.example.moviesapp.api.configuration.models.Images
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConfiguration @Inject constructor() {

    lateinit var images: Images

    fun setConfiguration(configuration: Configuration) =
        configuration.let {
            images = it.images
            Single.just(it)
        }
}