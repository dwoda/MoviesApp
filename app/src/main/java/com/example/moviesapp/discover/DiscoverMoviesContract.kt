package com.example.moviesapp.discover

import com.example.moviesapp.api.movies.discover.models.Movie

interface DiscoverMoviesContract {

    interface View {
        fun setTitles(movieList: List<Movie>)
        fun displayError(message: String?)
        fun setInitialState()
        fun openMovieDetails(id: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun onItemSelected(id: Int)
        fun detachView()
    }
}