package com.tellmeajoke.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tellmeajoke.data.db.entities.FavouriteJoke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {

    @Query("SELECT * FROM favouriteJoke")
    fun getJokes(): LiveData<List<FavouriteJoke>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: FavouriteJoke)

    @Delete
    suspend fun deleteJoke(joke: FavouriteJoke)
}