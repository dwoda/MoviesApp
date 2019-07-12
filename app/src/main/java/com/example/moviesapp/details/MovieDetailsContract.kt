package com.example.moviesapp.details

interface MovieDetailsContract {

    interface View {
        val movieId: Int
        fun displayDetails(movieDetailsDisplay: MovieDetailsPresenter.MovieDetailsDisplay)
        fun displayError(message: String)
        fun displayInitialState()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}