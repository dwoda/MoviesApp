package com.example.moviesapp

import com.example.moviesapp.api.movies.models.DiscoverMovies
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val model: MainActivityModel) :
    MainActivityContract.Presenter,
    MainActivityContract.Model.OnFinishedListener {

    private lateinit var view: MainActivityContract.View

    override fun onFinished(discoverMovies: DiscoverMovies) {
        view.setTitles(discoverMovies.results.subList(0,20))
    }

    override fun onFailure(throwable: Throwable) {
        view.displayError(throwable.message)
    }

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        model.getMovies(this)
    }

}