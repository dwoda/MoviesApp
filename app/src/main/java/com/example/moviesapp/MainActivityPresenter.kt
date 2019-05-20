package com.example.moviesapp

import com.example.moviesapp.api.movies.models.DiscoverMovies

class MainActivityPresenter : MainActivityContract.Presenter, MainActivityContract.Model.OnFinishedListener {

    private val model = MainActivityModel()
    private lateinit var view: MainActivityContract.View

    override fun onFinished(discoverMovies: DiscoverMovies) {
        view.setTitle(discoverMovies.results[0].title)
    }

    override fun onFailure(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        model.getMovies(this)
    }

}