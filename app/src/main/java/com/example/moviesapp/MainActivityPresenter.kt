package com.example.moviesapp

import com.example.moviesapp.api.movies.MoviesService
import com.example.moviesapp.api.movies.models.DiscoverMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val moviesService: MoviesService) : MainActivityContract.Presenter {
    private lateinit var view: MainActivityContract.View

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        view.setInitialState()
        getInitialData()
    }

    override fun onItemSelected(id: Int) {
        view.openMovieDetails(id)
    }

    private fun getInitialData() {
        moviesService
            .getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(::onDataFetchSuccess)
            .doOnError(::onDataFetchError)
            .subscribe()
    }

    private fun onDataFetchError(it: Throwable) {
        view.displayError(it.message)
    }

    private fun onDataFetchSuccess(it: DiscoverMovies) {
        view.setTitles(it.results.subList(0, 20))
    }
}