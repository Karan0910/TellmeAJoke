package com.tellmeajoke.main

import com.tellmeajoke.data.api.JokeApi
import com.tellmeajoke.data.models.JokeResponse
import com.tellmeajoke.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: JokeApi) : MainRepository {

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
}