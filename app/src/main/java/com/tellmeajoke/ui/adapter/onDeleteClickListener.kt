package com.tellmeajoke.ui.adapter

import com.tellmeajoke.data.db.entities.FavouriteJoke

interface onDeleteClickListener {
    fun onDeleteClick(joke: FavouriteJoke)
}