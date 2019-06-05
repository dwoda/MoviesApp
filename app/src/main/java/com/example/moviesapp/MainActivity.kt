package com.example.moviesapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.api.movies.models.Movie
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppDependencies.create().inject(this)
        setInitialState()
        presenter.attachView(this)
    }

    override fun setTitles(movieList: List<Movie>) {
        progressBar.visibility = View.GONE
        movies_recycler_view.visibility = View.VISIBLE

        val viewManager = LinearLayoutManager(this)
        val dividerItemDecorator = DividerItemDecoration(this, viewManager.orientation)

        movies_recycler_view.apply {
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecorator)
            layoutManager = viewManager
            adapter = MoviesAdapter(movieList)
        }
    }

    override fun displayError(message: String?) {
        progressBar.visibility = View.GONE
        movies_recycler_view.visibility = View.GONE
        movies_error.visibility = View.VISIBLE
        movies_error.text = message ?: resources.getString(R.string.error_loading_data)
    }

    private fun setInitialState() {
        progressBar.visibility = View.VISIBLE
        movies_error.visibility = View.GONE
        movies_recycler_view.visibility = View.GONE
    }
}