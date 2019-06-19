package com.example.moviesapp.details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.movie_details.*
import javax.inject.Inject


class MovieDetailsActivity : DaggerActivity(), MovieDetailsContract.View {
    @Inject
    lateinit var presenter: MovieDetailsPresenter

    override val movieId: Int
        get() = intent.getIntExtra("MOVIE_ID", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)
        AndroidInjection.inject(this)
        presenter.attachView(this)
        setInitialState()
    }

    private fun setInitialState() {
        movie_details.visibility = View.GONE
        details_progress_bar.visibility = View.VISIBLE
    }

    override fun setTitle(title: String) {
        details_progress_bar.visibility = View.GONE
        movie_details.visibility = View.VISIBLE
        movie_detail_title.text = title
    }

    override fun setGenres(genres: List<String>) {
        genre.text = genres.joinToString { it }
    }

    override fun setReleaseDate(date: String) {
        release_date.text = date
    }

    override fun setError(message: String) {
        details_progress_bar.visibility = View.GONE
        movie_details.visibility = View.VISIBLE
        movie_detail_title.text = message
    }

    override fun setImage(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(movie_poster)
    }
}
