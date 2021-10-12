package com.tellmeajoke.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteJoke(
    val joke: String,
    @PrimaryKey val id: String
)