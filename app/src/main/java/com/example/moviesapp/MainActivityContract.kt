package com.example.moviesapp

import com.example.moviesapp.api.movies.models.DiscoverMoviesResponse

interface MainActivityContract{

    interface Model {
        interface OnFinishedListener {
            fun onFinished(discoverMoviesResponse: DiscoverMoviesResponse)
            fun onFailure(throwable: Throwable)
        }

        interface OnImageFinishedListener {
            fun onFinished(discoverMoviesResponse: DiscoverMoviesResponse)
            fun onFailure(throwable: Throwable)
        }

        fun getMovies(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun setTitle(title: String)
        fun setImage()
    }

    interface Presenter {
        fun attachView(view: View)
    }

}