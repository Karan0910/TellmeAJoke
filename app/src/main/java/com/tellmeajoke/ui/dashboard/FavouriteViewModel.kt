package com.tellmeajoke.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tellmeajoke.data.db.entities.FavouriteJoke
import com.tellmeajoke.main.DispatcherProvider
import com.tellmeajoke.main.MainRepository
import com.tellmeajoke.ui.adapter.onClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    fun getAllFavJokes(): LiveData<List<FavouriteJoke>> {
        return mainRepository.getFavoriteJokes()
    }

    fun deleteJoke(joke: FavouriteJoke) {
        viewModelScope.launch(dispatchers.io) {
            mainRepository.deleteFavoriteJoke(joke)
        }
    }

}