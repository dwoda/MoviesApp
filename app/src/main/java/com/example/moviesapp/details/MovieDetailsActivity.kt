package com.example.moviesapp.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
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
    }

    override fun displayInitialState() {
        movie_details.visibility = View.GONE
        movie_details_error.visibility = View.GONE
        details_progress_bar.visibility = View.VISIBLE
    }

    override fun displayDetails(movieDetailsDisplay: MovieDetailsPresenter.MovieDetailsDisplay) {
        movie_detail_title.text = movieDetailsDisplay.title
        genre.text = movieDetailsDisplay.genres.joinToString { it }
        release_date.text = movieDetailsDisplay.releaseDate

        credits_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieDetailsActivity)
            adapter = CreditsAdapter(movieDetailsDisplay.credits.cast)
        }
        setImage(movieDetailsDisplay.posterUrl)

        details_progress_bar.visibility = View.GONE
        movie_details.visibility = View.VISIBLE
    }

    override fun displayError(message: String) {
        movie_details_error.text = message

        details_progress_bar.visibility = View.GONE
        movie_details_error.visibility = View.VISIBLE
    }

    private fun setImage(imageUrl: String) {

        val placeholder =
            CircularProgressDrawable(this)
                .apply {
                    setColorSchemeColors(R.color.colorAccent)
                    strokeWidth = 10f
                    centerRadius = 30f
                    start()
                }
        Glide
            .with(this)
            .load(imageUrl)
            .placeholder(placeholder)
            .error(R.drawable.ic_error_black_24dp)
            .into(movie_poster)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
