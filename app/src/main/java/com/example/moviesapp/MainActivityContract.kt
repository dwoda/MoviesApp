package com.example.moviesapp

interface MainActivityContract{

    interface View {
        fun setTitle(title: String)
    }

    interface Presenter {
        fun attachView(view: View)
    }

}