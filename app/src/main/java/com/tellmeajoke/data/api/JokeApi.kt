package com.tellmeajoke.data.api

import com.tellmeajoke.data.models.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeApi {

    @GET("/")
    @Headers("Accept: application/json")
    suspend fun  getJoke() : Response<JokeResponse>
}