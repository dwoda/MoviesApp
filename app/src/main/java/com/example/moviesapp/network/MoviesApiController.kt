package com.example.moviesapp.network

import com.example.moviesapp.network.models.DiscoverResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiController : Callback<DiscoverResponse> {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val moviesApi = retrofit.create(MoviesApi::class.java)
        val call = moviesApi.getMovies()
        call.enqueue(this)

    }

    override fun onResponse(call: Call<DiscoverResponse>?, response: Response<DiscoverResponse>?) {

        if (response!!.isSuccessful) {

            val discoverResponse = response.body()
            val title = discoverResponse.results[0].title
            println("DAWID test - $title")
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<DiscoverResponse>?, t: Throwable?) {
        t!!.printStackTrace()
    }
}