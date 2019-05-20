package com.example.moviesapp

import com.example.moviesapp.api.movies.models.DiscoverMovies

interface MainActivityContract{

    interface Model {
        interface OnFinishedListener {
            fun onFinished(discoverMovies: DiscoverMovies)
            fun onFailure(throwable: Throwable)
        }

        fun getMovies(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun setTitle(title: String)
    }

    interface Presenter {
        fun attachView(view: View)
    }

}