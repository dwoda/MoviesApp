package com.example.moviesapp.details

import android.net.Uri
import com.example.moviesapp.api.ApiConstants.Urls.imagesBaseUrl
import com.example.moviesapp.api.movies.MoviesService
import com.example.moviesapp.api.movies.models.MovieDetails
import com.example.moviesapp.api.movies.models.MovieImages
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
    private val moviesService: MoviesService
) :
    MovieDetailsContract.Presenter {

    private lateinit var view: MovieDetailsContract.View

    override fun attachView(view: MovieDetailsContract.View) {
        this.view = view
        getMovieData()
    }

    private fun getMovieData() {
        moviesService
            .getMovieDetails(view.movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(::setMovieDetails)
            .doOnError(::setMovieDetailsError)
            .subscribe()

        moviesService
            .getMovieImages(view.movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(::setPosterUrl)
            .doOnError(::setPosterError)
            .subscribe()
    }

    private fun setPosterUrl(movieImages: MovieImages) {
        val width = "w154" // TODO get from configuration API
        val path = movieImages.posters[0].file_path.replace("/", "")

        val posterUrl = Uri.parse(imagesBaseUrl)
            .buildUpon()
            .appendPath(width)
            .appendPath(path)
            .build()
            .toString()

        view.setImage(posterUrl)
    }

    private fun setPosterError(throwable: Throwable) {
    }

    private fun setMovieDetails(movieDetails: MovieDetails) {
        view.setTitle(movieDetails.title)
        view.setGenres(movieDetails.genres.map { it.name })
        view.setReleaseDate(formatDate(movieDetails.release_date))
    }

    private fun setMovieDetailsError(throwable: Throwable) {
        view.setError(throwable.localizedMessage)
    }

    private fun formatDate(date: String): String {
        val newFormat = SimpleDateFormat("dd MMMM yyyy")
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return newFormat.format(parsedDate)
    }
}