package com.example.moviesapp

import com.example.moviesapp.network.models.DiscoverResponse

class MainActivityPresenter : MainActivityContract.Presenter, MainActivityContract.Model.OnFinishedListener {

    lateinit var view: MainActivityContract.View

    override fun onFinished(discoverResponse: DiscoverResponse) {
        view.setTitle(discoverResponse.results[0].title)
    }

    override fun onFailure(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
        MainActivityModel().getMovies(this)
    }

}