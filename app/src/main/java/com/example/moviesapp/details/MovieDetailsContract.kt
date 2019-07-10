package com.example.moviesapp.details

interface MovieDetailsContract {

    interface View {
        val movieId: Int
        fun setTitle(title: String)
        fun setError(message: String)
        fun setImage(imageUrl: String)
        fun setGenres(genres: List<String>)
        fun setReleaseDate(date: String)
        fun setInitialState()
        fun displayData()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}