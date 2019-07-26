package com.example.moviesapp.details

import android.annotation.SuppressLint
import android.net.Uri
import com.example.moviesapp.services.movies.MoviesService
import com.example.moviesapp.api.movies.models.CreditsPojo
import com.example.moviesapp.api.movies.models.MovieDetailsPojo
import com.example.moviesapp.configuration.ApiConfiguration
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
    private val moviesService: MoviesService,
    private val apiConfiguration: ApiConfiguration
) :
    MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View

    private val disposables = CompositeDisposable()

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
        view.displayInitialState()
        getMovieData()
    }

    override fun detachView() {
        disposables.clear()
    }

    private fun getMovieData() {
        val id = view.movieId

        moviesService
            .getMovieDetails(id)
            .flatMap { convertMovieDetailsToBuilder(it) }
            .flatMap { addPosterUrl(id, it) }
            .flatMap { addMovieCredits(id, it) }
            .flatMap { Single.just(it.build()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { setMovieDetails(it) },
                { setMovieDetailsError(it) }
            )
            .addTo(disposables)
    }

    private fun convertMovieDetailsToBuilder(movieDetails: MovieDetailsPojo) =
        MovieDetailsDisplay.Builder()
            .apply {
                title = movieDetails.title
                genres = movieDetails.genres.map { it.name }
                releaseDate = formatDate(movieDetails.release_date)
            }
            .let { Single.just(it) }

    private fun addPosterUrl(id: Int, builder: MovieDetailsDisplay.Builder) =
        moviesService.getMovieImages(id)
            .flatMap { movieImages ->
                val width = getProperPosterSize(apiConfiguration.images.posterSizes)
                val path = movieImages.posters[0].file_path

                val posterUrl =
                    Uri.parse(apiConfiguration.images.baseUrl)
                        .buildUpon()
                        .appendPath(width)
                        .appendEncodedPath(path)
                        .build()
                        .toString()

                builder
                    .apply { this.posterUrl = posterUrl }
                    .let { Single.just(it) }
            }

    private fun addMovieCredits(id: Int, builder: MovieDetailsDisplay.Builder) =
        moviesService.getMovieCredits(id)
            .flatMap {
                builder
                    .apply { this.credits = it }
                    .let { Single.just(it) }
            }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return newFormat.format(parsedDate)
    }

    private fun setMovieDetails(details: MovieDetailsDisplay) {
        view.displayDetails(details)
    }

    private fun setMovieDetailsError(throwable: Throwable) {
        view.displayError(throwable.localizedMessage)
    }

    private fun getProperPosterSize(sizes: List<String>) = sizes.getOrElse(2) { sizes.last() }

    data class MovieDetailsDisplay(
        val posterUrl: String,
        val title: String,
        val genres: List<String>,
        val releaseDate: String,
        val credits: CreditsPojo
    ) {

        class Builder {
            lateinit var posterUrl: String
            lateinit var title: String
            lateinit var genres: List<String>
            lateinit var releaseDate: String
            lateinit var credits: CreditsPojo

            fun build() = MovieDetailsDisplay(posterUrl, title, genres, releaseDate, credits)
        }
    }
}