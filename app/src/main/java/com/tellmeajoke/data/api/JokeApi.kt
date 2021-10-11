package com.tellmeajoke.data.api

import com.tellmeajoke.data.models.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokeApi {

    @GET("/")
    suspend fun  getJoke() : Response<JokeResponse>
}