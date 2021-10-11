package com.tellmeajoke.main

import com.tellmeajoke.data.models.JokeResponse
import com.tellmeajoke.util.Resource

interface MainRepository {

    suspend fun getJoke() : Resource<JokeResponse>
}