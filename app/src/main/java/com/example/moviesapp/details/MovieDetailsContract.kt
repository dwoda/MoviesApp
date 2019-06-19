package com.example.moviesapp.details

import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages

interface MovieDetailsContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(movieDetails: MovieDetails)
            fun onFailure(throwable: Throwable)
        }

        interface OnPosterFinishedListener {
            fun onPosterFinished(movieImages: MovieImages)
            fun onPosterFailure(throwable: Throwable)
        }

        fun getMovieDetails(id: Int, onFinishedListener: OnFinishedListener)
        fun getMoviePosters(id: Int, onPosterFinishedListener: OnPosterFinishedListener)
    }

    interface View {
        val movieId: Int
        fun setTitle(title: String)
        fun setError(message: String)
        fun setImage(imageUrl: String)
        fun setGenres(genres: List<String>)
        fun setReleaseDate(date: String)
    }

    interface Presenter {
        fun attachView(view: View)
    }
}