package com.example.moviesapp

import com.example.moviesapp.network.models.DiscoverMoviesResponse

class MainActivityPresenter : MainActivityContract.Presenter, MainActivityContract.Model.OnFinishedListener {

    private val model = MainActivityModel()
    private lateinit var view: MainActivityContract.View

    override fun onFinished(discoverMoviesResponse: DiscoverMoviesResponse) {
        view.setTitle(discoverMoviesResponse.results[0].title)
    }

    override fun onFailure(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        model.getMovies(this)
    }

}