package com.example.moviesapp.discover

import com.example.moviesapp.api.configuration.ConfigurationService
import com.example.moviesapp.api.movies.discover.DiscoverService
import com.example.moviesapp.api.movies.discover.models.DiscoverMovies
import com.example.moviesapp.configuration.ApiConfiguration
import com.example.moviesapp.services.storage.StorageService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiscoverMoviesPresenter @Inject constructor(
    private val discoverService: DiscoverService,
    private val configurationService: ConfigurationService,
    private val apiConfiguration: ApiConfiguration,
    private val storageService: StorageService
) :
    DiscoverMoviesContract.Presenter {
    private lateinit var view: DiscoverMoviesContract.View

    private val disposables = CompositeDisposable()

    override fun attachView(view: DiscoverMoviesContract.View) {
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

    override fun onItemFavouriteIconSelected(id: Int) {
        storageService.addFavourite(id)
    }

    private fun getInitialData() {
        configurationService.getConfiguration()
            .flatMap { apiConfiguration.setConfiguration(it) }
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