package com.example.moviesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppDependencies.create().inject(this)
        presenter.attachView(this)
    }

    override fun setTitle(title: String) {
        movieTitle.text = title
        movieTitleProgressBar.visibility = View.GONE
        movieTitle.visibility = View.VISIBLE
    }
}
