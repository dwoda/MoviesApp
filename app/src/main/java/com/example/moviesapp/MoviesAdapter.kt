package com.example.moviesapp

import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.api.movies.models.Movie
import com.example.moviesapp.details.MovieDetailsActivity
import java.security.AccessController.getContext

class MoviesAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.movie_title)
        val movieRating: TextView = view.findViewById(R.id.movie_rating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val v =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)

        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.movieTitle.text = movie.title
        holder.movieRating.text = movie.rating.toString()

        val color = ContextCompat.getColor(holder.movieRating.context, getRatingColor(movie.rating))
        (holder.movieRating.background as GradientDrawable).setColor(color)

        holder.view.setOnClickListener {
            val intent =
                Intent(it.context, MovieDetailsActivity::class.java)
                    .apply { putExtra("MOVIE_ID", movie.id) }

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = movies.size

    private fun getRatingColor(rating: Double): Int {

        val colorResourceId = when (rating.toInt()) {
            in 0..4 -> R.color.badRating
            in 5..7 -> R.color.mediumRating
            else -> R.color.goodRating
        }

        return colorResourceId

    }
}
