package com.example.moviesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesapp.network.MoviesApiController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val moviesApiController = MoviesApiController()
        moviesApiController.start()

    }
}
