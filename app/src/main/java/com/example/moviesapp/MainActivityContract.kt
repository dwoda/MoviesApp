package com.example.moviesapp

import com.example.moviesapp.api.movies.models.DiscoverMovies
import com.example.moviesapp.api.movies.models.Movie

interface MainActivityContract{

    interface Model {
        interface OnFinishedListener {
            fun onFinished(discoverMovies: DiscoverMovies)
            fun onFailure(throwable: Throwable)
        }

        fun getMovies(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun setTitles(movieList: List<Movie>)
    }

    interface Presenter {
        fun attachView(view: View)
    }

}