package com.tellmeajoke.main

import androidx.lifecycle.LiveData
import com.tellmeajoke.data.api.JokeApi
import com.tellmeajoke.data.db.dao.JokesDao
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.data.models.JokeResponse
import com.tellmeajoke.util.Resource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: JokeApi, private val dao: JokesDao) : MainRepository {

    override suspend fun getJoke(): Resource<JokeResponse> {

        return try {
            val response = api.getJoke()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

    override fun getFavoriteJokes(): LiveData<List<FavouriteJoke>> {
        return dao.getJokes()
    }

    override suspend fun insertFavoriteJoke(joke: FavouriteJoke) {
        dao.insertJoke(joke)
    }

    override suspend fun deleteFavoriteJoke(joke: FavouriteJoke) {
        dao.deleteJoke(joke)
    }
}