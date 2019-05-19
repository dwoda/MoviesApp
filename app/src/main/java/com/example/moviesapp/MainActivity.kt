package com.example.moviesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.moviesapp.network.DataInitializer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        DataInitializer().initializeUrls()
    }

    override fun setTitle(title: String) {
        movieTitle.text = title
        movieTitleProgressBar.visibility = View.GONE
        movieTitle.visibility = View.VISIBLE
    }

    override fun setImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
