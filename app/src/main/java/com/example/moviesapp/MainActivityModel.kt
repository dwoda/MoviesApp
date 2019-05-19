package com.example.moviesapp

import com.example.moviesapp.network.ImagesApi
import com.example.moviesapp.network.ImagesApiClient
import com.example.moviesapp.network.MoviesApi
import com.example.moviesapp.network.MoviesApiClient
import com.example.moviesapp.network.models.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityModel : MainActivityContract.Model {

    override fun getMovies(onFinishedListener: MainActivityContract.Model.OnFinishedListener) {

        MoviesApiClient()
            .getClient()
            .create(MoviesApi::class.java)
            .getMovies()
            .enqueue(object : Callback<DiscoverMoviesResponse> {

                override fun onResponse(call: Call<DiscoverMoviesResponse>?, moviesResponse: Response<DiscoverMoviesResponse>?) {
                    if (moviesResponse!!.isSuccessful) {
                        val discoverResponse = moviesResponse.body()
                        onFinishedListener.onFinished(discoverResponse)
                    } else {
                        println(moviesResponse.errorBody())
                    }
                }

                override fun onFailure(call: Call<DiscoverMoviesResponse>?, t: Throwable?) {
                    onFinishedListener.onFailure(t!!)
                }
            })
    }
}