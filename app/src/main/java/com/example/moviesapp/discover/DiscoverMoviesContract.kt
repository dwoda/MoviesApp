package com.example.moviesapp.discover

import com.example.moviesapp.domain.models.Movie

interface DiscoverMoviesContract {

    interface View {
        fun setTitles(movieList: List<Movie>)
        fun displayError(message: String?)
        fun setInitialState()
        fun openMovieDetails(id: Int)
        fun reloadList()
    }

    interface Presenter {
        fun attachView(view: View)
        fun onItemSelected(id: Int)
        fun onItemFavouriteIconSelected(id: Int)
        fun detachView()
    }
}