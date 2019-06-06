package com.example.moviesapp.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.DaggerAppDependencies
import com.example.moviesapp.api.movies.models.MovieDetails
import kotlinx.android.synthetic.main.movie_details.*
import javax.inject.Inject
import android.widget.Toast
import android.util.Log
import com.bumptech.glide.Glide
import com.example.moviesapp.R


class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.View {
    @Inject
    lateinit var presenter: MovieDetailsPresenter

    override val movieId: Int
        get() = intent.getIntExtra("MOVIE_ID", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        DaggerAppDependencies.create().inject(this)
        presenter.attachView(this)
        setInitialState()
    }

    private fun setInitialState() {
        movie_details.visibility = View.GONE
        details_progress_bar.visibility = View.VISIBLE
    }

    override fun setDetails(movie: MovieDetails) {
        details_progress_bar.visibility = View.GONE
        movie_details.visibility = View.VISIBLE
        movie_detail_title.text = movie.title

        Glide
            .with(this)
            .load("https://www.gstatic.com/webp/gallery/1.webp")
            .into(movie_poster)
    }

    override fun setError(message: String) {
        details_progress_bar.visibility = View.GONE
        movie_details.visibility = View.VISIBLE
        movie_detail_title.text = message
    }
}
