package com.example.moviesapp

import com.example.moviesapp.api.movies.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityModel @Inject constructor(private val moviesService: MoviesService) : MainActivityContract.Model {

    override fun getMovies(onFinishedListener: MainActivityContract.Model.OnFinishedListener) {

        moviesService
            .getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { onFinishedListener.onFinished(it) }
            .doOnError { onFinishedListener.onFailure(it) }
            .subscribe()
    }
}