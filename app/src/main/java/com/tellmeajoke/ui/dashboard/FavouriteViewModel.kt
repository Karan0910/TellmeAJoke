package com.tellmeajoke.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.main.DispatcherProvider
import com.tellmeajoke.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {


    private val _favJokes = MutableLiveData<List<FavouriteJoke>>()

    val favJokes: LiveData<List<FavouriteJoke>> = _favJokes


    fun getAllFavJokes(): LiveData<List<FavouriteJoke>> {
        return mainRepository.getFavoriteJokes()
    }

}