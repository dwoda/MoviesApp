package com.example.moviesapp

import dagger.Component

@Component
interface AppDependencies {
    fun inject(app: MainActivity)
}