package com.tellmeajoke.ui.adapter

import com.tellmeajoke.data.db.entities.FavouriteJoke

interface onClickListener {
    fun onDeleteClick(joke: FavouriteJoke)
    fun onShareClick(joke: FavouriteJoke)
}