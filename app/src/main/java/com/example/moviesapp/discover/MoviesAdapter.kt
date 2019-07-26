package com.example.moviesapp.discover

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.domain.models.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
    private val presenter: DiscoverMoviesContract.Presenter
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.movie_title)
        val movieRating: TextView = view.findViewById(R.id.movie_rating)
        val favouriteStar: ImageView = view.findViewById(R.id.favourite_star)
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
        holder.favouriteStar.setFavouriteIcon(movie)

        val color = ContextCompat.getColor(holder.movieRating.context, getRatingColor(movie.rating))
        (holder.movieRating.background as GradientDrawable).setColor(color)

        holder.favouriteStar.setOnClickListener {
            presenter.onItemFavouriteIconSelected(movie.id)
            this.notifyDataSetChanged()
            println("DW_RECYCLER_CHANGED")
        }
        holder.view.setOnClickListener { presenter.onItemSelected(movie.id) }
    }

    override fun getItemCount() = movies.size

    private fun getRatingColor(rating: Double) = when (rating.toInt()) {
        in 0..4 -> R.color.badRating
        in 5..7 -> R.color.mediumRating
        else -> R.color.goodRating
    }

    private fun ImageView.setFavouriteIcon(movie: Movie) {
        if (movie.isFavourite) {
            this.setImageResource(R.drawable.ic_star_black_24dp)
        } else {
            this.setImageResource(R.drawable.ic_star_border_black_24dp)
        }
    }
}
