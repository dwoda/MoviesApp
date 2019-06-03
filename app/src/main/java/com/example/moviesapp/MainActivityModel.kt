package com.example.moviesapp

import com.example.moviesapp.api.movies.MoviesApi
import com.example.moviesapp.api.movies.MoviesApiClient
import com.example.moviesapp.api.movies.models.DiscoverMovies
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityModel @Inject constructor(private val moviesApiClient: MoviesApiClient) : MainActivityContract.Model {

    override fun getMovies(onFinishedListener: MainActivityContract.Model.OnFinishedListener) {

        moviesApiClient
            .getClient()
            .create(MoviesApi::class.java)
            .getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<DiscoverMovies> {
                    override fun onSuccess(movies: DiscoverMovies) {
                        onFinishedListener.onFinished(movies)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        onFinishedListener.onFailure(e)
                    }
                }
            )
    }
}