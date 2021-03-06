package com.example.moviesapp

import com.example.moviesapp.di.AppModule
import com.example.moviesapp.di.DaggerAppDependencies
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector

class MoviesApp : DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppDependencies
            .builder()
            .appModule(AppModule(this))
            .application(this)
            .build()
}