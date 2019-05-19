package com.example.moviesapp

import com.example.moviesapp.network.MoviesApi
import com.example.moviesapp.network.models.DiscoverResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPresenter : MainActivityContract.Presenter {

    override fun attachView(view: MainActivityContract.View) {

        val baseUrl = "https://api.themoviedb.org/3/"

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val moviesApi = retrofit.create(MoviesApi::class.java)
        val call = moviesApi.getMovies()

        call.enqueue(object : Callback<DiscoverResponse> {

            override fun onResponse(call: Call<DiscoverResponse>?, response: Response<DiscoverResponse>?) {
                if (response!!.isSuccessful) {
                    val discoverResponse = response.body()
                    view.setTitle(discoverResponse.results[0].title)
                } else {
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<DiscoverResponse>?, t: Throwable?) {
                t!!.printStackTrace()
            }

        })
    }

}