package com.example.moviesapp.discover

import com.example.moviesapp.api.configuration.ConfigurationService
import com.example.moviesapp.api.movies.discover.DiscoverService
import com.example.moviesapp.api.movies.discover.models.DiscoverMovies
import com.example.moviesapp.configuration.ApiConfiguration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val discoverService: DiscoverService,
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
            .flatMap { discoverService.discoverMovies() }
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