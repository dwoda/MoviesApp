package com.example.moviesapp

import com.example.moviesapp.api.configuration.ConfigurationService
import com.example.moviesapp.api.movies.MoviesService
import com.example.moviesapp.api.movies.models.DiscoverMovies
import com.example.moviesapp.apiconfiguration.ApiConfiguration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val moviesService: MoviesService,
    private val configurationService: ConfigurationService,
    private val apiConfiguration: ApiConfiguration
) :
    MainActivityContract.Presenter {

    private lateinit var view: MainActivityContract.View

    private val disposables = CompositeDisposable()

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        view.setInitialState()
        getInitialData()
    }

    override fun detachView() {
        disposables.clear()
    }

    override fun onItemSelected(id: Int) {
        view.openMovieDetails(id)
    }

    private fun getInitialData() {
        configurationService.getConfiguration()
            .doOnSuccess { apiConfiguration.setConfiguration(it) }
            .flatMap { moviesService.getMovies() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onDataFetchSuccess, ::onDataFetchError)
            .addTo(disposables)
    }

    private fun onDataFetchError(it: Throwable) {
        view.displayError(it.message)
    }

    private fun onDataFetchSuccess(it: DiscoverMovies) {
        view.setTitles(it.results.subList(0, 20))
    }
}