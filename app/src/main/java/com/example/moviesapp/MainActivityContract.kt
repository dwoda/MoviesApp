package com.example.moviesapp

import com.example.moviesapp.network.models.DiscoverResponse

interface MainActivityContract{

    interface Model {
        interface OnFinishedListener {
            fun onFinished(discoverResponse: DiscoverResponse)
            fun onFailure(throwable: Throwable)
        }

        fun getMovies(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun setTitle(title: String)
    }

    interface Presenter {
        fun attachView(view: View)
    }

}