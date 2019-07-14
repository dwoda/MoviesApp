package com.example.moviesapp.di

import com.example.moviesapp.api.configuration.ConfigurationApi
import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.discover.DiscoverApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(gson: GsonConverterFactory, rx: RxJava2CallAdapterFactory): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(gson)
            .addCallAdapterFactory(rx)

    @Provides
    @Singleton
    fun providesRetrofit(builder: Retrofit.Builder): Retrofit =
        builder
            .baseUrl("https://api.themoviedb.org/3/")
            .build()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providesMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun providesConfigurationApi(retrofit: Retrofit): ConfigurationApi = retrofit.create(ConfigurationApi::class.java)

    @Provides
    @Singleton
    fun providesDiscoverApi(retrofit: Retrofit): DiscoverApi = retrofit.create(DiscoverApi::class.java)
}