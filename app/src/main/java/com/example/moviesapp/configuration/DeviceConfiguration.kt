package com.example.moviesapp.configuration

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceConfiguration @Inject constructor(locale: Locale) {
    private val language = "${locale.language}-${locale.country}"
    private val region = locale.country

    val locale = mapOf(
        "language" to language,
        "region" to region
    )
}