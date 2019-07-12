package com.example.moviesapp.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.api.movies.models.CastMember

class CreditsAdapter(
    private val credits: List<CastMember>
) : RecyclerView.Adapter<CreditsAdapter.CreditsViewHolder>() {

    class CreditsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.credits_actor_name)
        val characterName: TextView = view.findViewById(R.id.credits_character_name)
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
        val credit = credits[position]

        holder.actorName.text = credit.name
        holder.characterName.text = credit.character
    }

    override fun getItemCount() = credits.size
}