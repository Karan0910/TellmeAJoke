package com.tellmeajoke.data.models

import com.tellmeajoke.data.db.entities.FavouriteJoke

data class JokeResponse(val id: String, val joke: String, val status: Int, var isFav : Boolean = false)

fun mapToJoke(jokeResponse: JokeResponse): FavouriteJoke {
    return FavouriteJoke(jokeResponse.joke, jokeResponse.id)
}