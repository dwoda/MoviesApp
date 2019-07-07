package com.example.moviesapp.di

import com.example.moviesapp.api.movies.MoviesApi
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
    fun providesRetrofitBuilder(gson: GsonConverterFactory, rx: RxJava2CallAdapterFactory) =
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
    @RetrofitUrl(RetrofitUrlValue.IMAGES)
    fun providesRetrofitForImages(builder: Retrofit.Builder): Retrofit =
        builder
            .baseUrl("https://dupa.com")
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
}