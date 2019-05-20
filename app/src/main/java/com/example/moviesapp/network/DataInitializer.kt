package com.example.moviesapp.network

import com.example.moviesapp.api.ApiConstants
import com.example.moviesapp.api.configuration.ConfigurationApi
import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.MoviesApiClient
import com.example.moviesapp.api.configuration.models.Configuration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataInitializer {

    fun initializeUrls() {
        MoviesApiClient()
            .getClient()
            .create(ConfigurationApi::class.java)
            .getConfiguration()
            .enqueue(object : Callback<Configuration> {

                override fun onResponse(call: Call<Configuration>?, response: Response<Configuration>?) {
                    ApiConstants.Urls.imagesBaseUrl = response!!.body().images.baseUrl
                }

                override fun onFailure(call: Call<Configuration>?, t: Throwable?) {
                    t!!.printStackTrace()
                }
            })
    }

}