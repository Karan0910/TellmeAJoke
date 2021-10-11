package com.tellmeajoke.di

import com.tellmeajoke.data.api.JokeApi
import com.tellmeajoke.main.DispatcherProvider
import com.tellmeajoke.main.MainRepository
import com.tellmeajoke.main.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val BASE_URL = "https://icanhazdadjoke.com"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesJokesApi(): JokeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokeApi::class.java)

    @Singleton
    @Provides
    fun providesMainRepository(api : JokeApi) : MainRepository = MainRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesDispatchers() : DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default

    }
}