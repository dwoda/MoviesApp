package com.example.moviesapp.configuration

import com.example.moviesapp.api.configuration.models.ConfigurationPojo
import com.example.moviesapp.api.configuration.models.ImagesPojo
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConfiguration @Inject constructor() {

    lateinit var images: ImagesPojo

    fun setConfiguration(configuration: ConfigurationPojo) =
        configuration.let {
            images = it.images
            Single.just(it)
        }
}