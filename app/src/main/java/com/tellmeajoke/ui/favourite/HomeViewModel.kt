package com.tellmeajoke.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tellmeajoke.data.models.JokeResponse
import com.tellmeajoke.data.models.mapToJoke
import com.tellmeajoke.main.DispatcherProvider
import com.tellmeajoke.main.MainRepository
import com.tellmeajoke.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _joke = MutableLiveData<JokeResponse>()

    val joke: LiveData<JokeResponse> = _joke

    private val _isLoading = MutableLiveData<Boolean>(true)

    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchJoke() {
        _isLoading.value = true
        viewModelScope.launch(dispatchers.io) {
            when (val jokesResponse = mainRepository.getJoke()) {
                is Resource.Success -> {
                    _joke.postValue(jokesResponse.data!!)
                    _isLoading.postValue(false)
                }
                is Resource.Error -> {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    fun addJoke() {
        viewModelScope.launch(dispatchers.io) {
            val joke = _joke.value
            if (joke != null) {
                joke.isFav = true
            }
            _joke.postValue(joke!!)
            _joke.value?.let { mapToJoke(it) }?.let { mainRepository.insertFavoriteJoke(it) }
        }
    }
}