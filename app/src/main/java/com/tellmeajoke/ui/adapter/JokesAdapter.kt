package com.tellmeajoke.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.databinding.ItemFavJokeBinding

val listDiffer = object : DiffUtil.ItemCallback<FavouriteJoke>() {
    override fun areItemsTheSame(oldItem: FavouriteJoke, newItem: FavouriteJoke): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FavouriteJoke, newItem: FavouriteJoke): Boolean =
        oldItem == newItem
}

class JokesAdapter(private val deleteClickListener: onDeleteClickListener) :
    ListAdapter<FavouriteJoke, JokesAdapter.JokeViewHolder>(listDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding =
            ItemFavJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JokeViewHolder(private val binding: ItemFavJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        internal fun bind(joke: FavouriteJoke) {
            binding.textJoke.text = joke.joke

            itemView.setOnClickListener {
                deleteClickListener.onDeleteClick(joke)
            }
        }
    }
}


