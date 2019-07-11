package com.example.moviesapp.details

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.api.movies.discover.models.Movie
import com.example.moviesapp.api.movies.models.CastMember

class CreditsAdapter(
    private val credits: List<CastMember>
) : RecyclerView.Adapter<CreditsAdapter.CreditsViewHolder>() {

    class CreditsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.credits_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditsViewHolder {
        val v =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.credits_list_item, parent, false)

        return CreditsViewHolder(v)
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        val credit = cre[position]

        holder.movieTitle.text = movie.title
        holder.movieRating.text = movie.rating.toString()

        val color = ContextCompat.getColor(holder.movieRating.context, getRatingColor(movie.rating))
        (holder.movieRating.background as GradientDrawable).setColor(color)

//        holder.view.setOnClickListener { presenter.onItemSelected(movie.id) }
    }

    override fun getItemCount() = movies.size

    private fun getRatingColor(rating: Double) = when (rating.toInt()) {
        in 0..4 -> R.color.badRating
        in 5..7 -> R.color.mediumRating
        else -> R.color.goodRating
    }
}
