package com.example.moviesapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitUrl(val value: RetrofitUrlValue)

enum class RetrofitUrlValue {
    IMAGES,
}