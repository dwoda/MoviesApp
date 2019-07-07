package com.example.moviesapp

import com.example.moviesapp.api.movies.models.Movie

interface MainActivityContract{

    interface View {
        fun setTitles(movieList: List<Movie>)
        fun displayError(message: String?)
        fun setInitialState()
        fun openMovieDetails(id: Int)
    }

    interface Presenter {
        fun attachView(view: View)
        fun onItemSelected(id: Int)
    }
}