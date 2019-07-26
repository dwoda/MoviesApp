package com.example.moviesapp.discover

import com.example.moviesapp.domain.models.Movie
import com.example.moviesapp.services.configuration.ConfigurationService
import com.example.moviesapp.services.discover.DiscoverService
import com.example.moviesapp.services.favourites.FavouritesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiscoverMoviesPresenter @Inject constructor(
    private val discoverService: DiscoverService,
    private val configurationService: ConfigurationService,
    private val favouritesService: FavouritesService
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
        favouritesService.toggleFavourite(id)
        discoverService.updateFavourites()
        view.reloadList()
    }

    private fun getInitialData() {
        configurationService.setApiConfiguration()
            .flatMap { discoverService.getMovies() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onDataFetchSuccess, ::onDataFetchError)
            .addTo(disposables)
    }

    private fun onDataFetchError(it: Throwable) {
        view.displayError(it.message)
    }

    private fun onDataFetchSuccess(it: List<Movie>) {
        view.setTitles(discoverService.cachedMovies)
//        view.setTitles(it)
    }
    }
}