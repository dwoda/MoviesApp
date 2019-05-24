package com.example.moviesapp

import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.MoviesApiClient
import com.example.moviesapp.api.movies.models.DiscoverMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityModel @Inject constructor(private val moviesApiClient: MoviesApiClient) : MainActivityContract.Model {

    override fun getMovies(onFinishedListener: MainActivityContract.Model.OnFinishedListener) {

        moviesApiClient
            .getClient()
            .create(MoviesApi::class.java)
            .getMovies()
            .enqueue(object : Callback<DiscoverMovies> {

                override fun onResponse(call: Call<DiscoverMovies>?, movies: Response<DiscoverMovies>?) {
                    if (movies!!.isSuccessful) {
                        val discoverResponse = movies.body()
                        onFinishedListener.onFinished(discoverResponse!!)
                    } else {
                        println(movies.errorBody())
                    }
                }

                override fun onFailure(call: Call<DiscoverMovies>?, t: Throwable?) {
                    onFinishedListener.onFailure(t!!)
                }
            })
    }
}