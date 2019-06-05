package com.example.moviesapp

import com.example.moviesapp.details.MovieDetailsActivity
import dagger.Component

@Component
interface AppDependencies {
    fun inject(app: MainActivity)
    fun inject(details: MovieDetailsActivity)
}