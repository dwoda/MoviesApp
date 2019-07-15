package com.example.moviesapp.discover

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.api.movies.discover.models.Movie
import com.example.moviesapp.details.MovieDetailsActivity
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainActivityContract.View {
    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
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
            adapter = MoviesAdapter(movieList, presenter)
        }
    }

    override fun displayError(message: String?) {
        progressBar.visibility = View.GONE
        movies_recycler_view.visibility = View.GONE
        movies_error.visibility = View.VISIBLE
        movies_error.text = message ?: resources.getString(R.string.error_loading_data)
    }

    override fun setInitialState() {
        progressBar.visibility = View.VISIBLE
        movies_error.visibility = View.GONE
        movies_recycler_view.visibility = View.GONE
    }

    override fun openMovieDetails(id: Int) {
        Intent(this, MovieDetailsActivity::class.java)
            .apply { putExtra("MOVIE_ID", id) }
            .let { startActivity(it) }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}