package com.example.moviesapp.details

import com.example.moviesapp.api.configuration.ConfigurationApi
import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.MoviesApiClient
import com.example.moviesapp.api.movies.models.DiscoverMovies
import com.example.moviesapp.api.movies.models.MovieDetails
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsModel @Inject constructor(private val moviesApiClient: MoviesApiClient, private val configurationApi: ConfigurationApi) : MovieDetailsContract.Model {
    override fun getMovieDetails(id: Int, onFinishedListener: MovieDetailsContract.Model.OnFinishedListener) {
        moviesApiClient
            .getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { onFinishedListener.onFinished(it) }
            .doOnError { onFinishedListener.onFailure(it) }
            .subscribe()
    }

    override fun getMoviePosters(
        id: Int,
        onPosterFinishedListener: MovieDetailsContract.Model.OnPosterFinishedListener
    ) {
        moviesApiClient
            .getMovieImages(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { onPosterFinishedListener.onPosterFinished(it) }
            .doOnError { onPosterFinishedListener.onPosterFailure(it) }
            .subscribe()
    }
}