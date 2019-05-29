package com.example.moviesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.moviesapp.api.movies.models.Movie
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityPresenter

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppDependencies.create().inject(this)
        presenter.attachView(this)
    }

    override fun setTitles(movieList: List<Movie>) {
        progressBar.visibility = View.GONE

        viewManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.movies_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = MoviesAdapter(movieList)
        }
    }
}
