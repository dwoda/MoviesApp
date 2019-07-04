package com.example.moviesapp.di

import com.example.moviesapp.MainActivity
import com.example.moviesapp.details.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesMovieDetailsActivity(): MovieDetailsActivity
}