package com.example.moviesapp.details

import com.example.moviesapp.api.movies.models.MovieDetails
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(private val model: MovieDetailsModel) :
    MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {

    private lateinit var view: MovieDetailsContract.View

    override fun onFinished(movieDetails: MovieDetails) {
        view.setDetails(movieDetails)
    }

    override fun onFailure(throwable: Throwable) {
        view.setError(throwable.localizedMessage)
    }

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
        model.getMovieDetails(view.movieId, this)
    }
}