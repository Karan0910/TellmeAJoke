package com.tellmeajoke.main

import androidx.lifecycle.LiveData
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.data.models.JokeResponse
import com.tellmeajoke.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getJoke() : Resource<JokeResponse>

    fun getFavoriteJokes(): LiveData<List<FavouriteJoke>>

    suspend fun insertFavoriteJoke(joke: FavouriteJoke)

    suspend fun deleteFavoriteJoke(joke: FavouriteJoke)
}