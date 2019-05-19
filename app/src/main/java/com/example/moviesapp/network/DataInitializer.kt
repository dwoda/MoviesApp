package com.example.moviesapp.network

import com.example.moviesapp.network.models.configuration.Configuration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataInitializer {

    fun initializeUrls() {
        MoviesApiClient()
            .getClient()
            .create(MoviesApi::class.java)
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