package com.tellmeajoke.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tellmeajoke.data.db.dao.JokesDao
import com.tellmeajoke.data.db.entities.FavouriteJoke

@Database(
    entities = [FavouriteJoke::class],
    version = 1
)
abstract class FavoriteJokeDatabase : RoomDatabase() {

    abstract val jokesDao: JokesDao

    companion object{
        const val DATABASE_NAME = "fav_jokes_db"
    }
}