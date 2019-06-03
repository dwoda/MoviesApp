package com.example.moviesapp.details

import com.example.moviesapp.api.movies.models.DiscoverMovies
import com.example.moviesapp.api.movies.models.Movie
import com.example.moviesapp.api.movies.models.MovieDetails

interface MovieDetailsContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(movieDetails: MovieDetails)
            fun onFailure(throwable: Throwable)
        }

        fun getMovieDetails(id: Int, onFinishedListener: OnFinishedListener)
    }

    interface View {
        val movieId: Int
        fun setDetails(movie: MovieDetails)
        fun setError(message: String)
    }

    interface Presenter {
        fun attachView(view: View)
    }
}