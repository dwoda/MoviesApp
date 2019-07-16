package com.example.moviesapp.di

import com.example.moviesapp.api.configuration.ConfigurationApi
import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.discover.DiscoverApi
import com.example.moviesapp.configuration.ApiKeyHttpInterceptor
import com.example.moviesapp.configuration.DeviceConfiguration
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        gson: GsonConverterFactory,
        rx: RxJava2CallAdapterFactory,
        client: OkHttpClient
    ): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
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

    @Provides
    @Singleton
    fun providesDeviceConfiguration(): DeviceConfiguration = DeviceConfiguration(Locale.getDefault())

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyHttpInterceptor: ApiKeyHttpInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(apiKeyHttpInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
}