package com.example.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.api.movies.models.Movie

class MoviesAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val movieTitle: TextView = v.findViewById(R.id.movie_title)
        val movieRating: TextView = v.findViewById(R.id.movie_rating)
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
        holder.movieTitle.text = movies[position].title
        holder.movieRating.text = movies[position].rating
    }

    override fun getItemCount() = movies.size
}
