package com.example.moviesapp.di

import com.example.moviesapp.discover.DiscoverMoviesActivity
import com.example.moviesapp.details.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): DiscoverMoviesActivity

    @ContributesAndroidInjector
    abstract fun contributesMovieDetailsActivity(): MovieDetailsActivity
}