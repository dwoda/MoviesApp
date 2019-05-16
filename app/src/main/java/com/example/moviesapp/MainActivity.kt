package com.example.moviesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.moviesapp.network.MoviesApi
import com.example.moviesapp.network.models.DiscoverResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById(R.id.movieTitle)
        updateView()
    }

    private fun updateView() {

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
                    title.text = discoverResponse.results[0].title
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
