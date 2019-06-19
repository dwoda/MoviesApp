package com.example.moviesapp.details

import com.example.moviesapp.api.movies.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsModel @Inject constructor(private val moviesService: MoviesService) : MovieDetailsContract.Model {
    override fun getMovieDetails(id: Int, onFinishedListener: MovieDetailsContract.Model.OnFinishedListener) {
        moviesService
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
        moviesService
            .getMovieImages(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { onPosterFinishedListener.onPosterFinished(it) }
            .doOnError { onPosterFinishedListener.onPosterFailure(it) }
            .subscribe()
    }
}