package com.example.moviesapp.details

import android.annotation.SuppressLint
import android.net.Uri
import com.example.moviesapp.api.movies.MoviesService
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import com.example.moviesapp.apiconfiguration.ApiConfiguration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
    private val moviesService: MoviesService,
    private val apiConfiguration: ApiConfiguration
) :
    MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View

    companion object {
        lateinit var posterUrl: String
        lateinit var title: String
        lateinit var genres: List<String>
        lateinit var releaseDate: String
        lateinit var error: String
    }

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
        view.setInitialState()
        getMovieData()
    }

    @SuppressLint("CheckResult")
    private fun getMovieData() {
        moviesService
            .getMovieDetails(view.movieId)
            .doOnSuccess(::saveMovieDetails)
            .flatMap { moviesService.getMovieImages(view.movieId) }
            .doOnSuccess(::savePosterUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { setMovieDetails() }
            .doOnError { setMovieDetailsError(it) }
            .subscribe({}, {})
    }

    private fun savePosterUrl(movieImages: MovieImages) {
        val width = getProperPosterSize(apiConfiguration.images.posterSizes)
        val path = movieImages.posters[0].file_path

        posterUrl = Uri.parse(apiConfiguration.images.baseUrl)
            .buildUpon()
            .appendPath(width)
            .appendEncodedPath(path)
            .build()
            .toString()
    }

    private fun saveMovieDetails(movieDetails: MovieDetails) {
        title = movieDetails.title
        genres = movieDetails.genres.map { it.name }
        releaseDate = formatDate(movieDetails.release_date)
    }

    private fun formatDate(date: String): String {
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return newFormat.format(parsedDate)
    }

    private fun setMovieDetails() {
        view.setGenres(genres)
        view.setReleaseDate(releaseDate)
        view.setTitle(title)
        view.setImage(posterUrl)
        view.displayData()
    }

    private fun setMovieDetailsError(throwable: Throwable) {
        view.setError(throwable.localizedMessage)
    }

    private fun getProperPosterSize(sizes: List<String>) : String{

        val maxSizeIndex = 2

        return if (sizes.size >= maxSizeIndex - 1) {
            sizes[maxSizeIndex]
        } else {
            sizes.last()
        }
    }
}