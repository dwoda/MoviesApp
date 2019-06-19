package com.example.moviesapp.details

import android.net.Uri
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(private val model: MovieDetailsModel) :
    MovieDetailsContract.Presenter,
    MovieDetailsContract.Model.OnFinishedListener,
    MovieDetailsContract.Model.OnPosterFinishedListener {

    override fun onPosterFinished(movieImages: MovieImages) {

        val baseUrl = "http://image.tmdb.org/t/p" // TODO get from configuration API
        val width = "w154" // TODO get from configuration API
        val path = movieImages.posters[0].file_path.replace("/", "")

        val posterUrl = Uri.parse(baseUrl)
            .buildUpon()
            .appendPath(width)
            .appendPath(path)
            .build()
            .toString()

        view.setImage(posterUrl)
    }

    override fun onPosterFailure(throwable: Throwable) {

    }

    private lateinit var view: MovieDetailsContract.View

    override fun onFinished(movieDetails: MovieDetails) {
        view.setTitle(movieDetails.title)
        view.setGenres(movieDetails.genres.map { it.name })
        view.setReleaseDate(formatDate(movieDetails.release_date))
    }

    override fun onFailure(throwable: Throwable) {
        view.setError(throwable.localizedMessage)
    }

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
        model.getMovieDetails(view.movieId, this)
        model.getMoviePosters(view.movieId, this)
    }

    private fun formatDate(date: String): String {
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return newFormat.format(parsedDate)
    }
}