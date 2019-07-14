package com.example.moviesapp.details

import android.annotation.SuppressLint
import android.net.Uri
import com.example.moviesapp.api.movies.MoviesService
import com.example.moviesapp.api.movies.models.Credits
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import com.example.moviesapp.configuration.ApiConfiguration
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
    private val movieDetailsDisplay = MovieDetailsDisplay()

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
            .doOnSuccess(::saveMovieDetails)
            .flatMap { moviesService.getMovieImages(id) }
            .doOnSuccess(::savePosterUrl)
            .flatMap { moviesService.getMovieCredits(id) }
            .doOnSuccess { movieDetailsDisplay.credits = it }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { setMovieDetails() },
                { setMovieDetailsError(it) })
            .addTo(disposables)
    }

    private fun savePosterUrl(movieImages: MovieImages) {
        val width = getProperPosterSize(apiConfiguration.images.posterSizes)
        val path = movieImages.posters[0].file_path

        movieDetailsDisplay.posterUrl = Uri.parse(apiConfiguration.images.baseUrl)
            .buildUpon()
            .appendPath(width)
            .appendEncodedPath(path)
            .build()
            .toString()
    }

    private fun saveMovieDetails(movieDetails: MovieDetails) {
        movieDetailsDisplay.apply {
            title = movieDetails.title
            genres = movieDetails.genres.map { it.name }
            releaseDate = formatDate(movieDetails.release_date)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return newFormat.format(parsedDate)
    }

    private fun setMovieDetails() {
        view.displayDetails(movieDetailsDisplay)
    }

    private fun setMovieDetailsError(throwable: Throwable) {
        view.displayError(throwable.localizedMessage)
    }

    private fun getProperPosterSize(sizes: List<String>) = sizes.getOrElse(2) { sizes.last() }

    class MovieDetailsDisplay {
        lateinit var posterUrl: String
        lateinit var title: String
        lateinit var genres: List<String>
        lateinit var releaseDate: String
        lateinit var error: String
        lateinit var credits: Credits
    }
}