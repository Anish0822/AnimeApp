package com.example.seekhoapp.ui.animeList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoapp.api.response.AnimeListResponse
import com.example.seekhoapp.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeListViewModel(private val repository: AnimeRepository) : ViewModel() {
    val animeList = MutableLiveData<AnimeListResponse>()
    private val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchAnimeList() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getAnime()
                animeList.postValue(response)
            } catch (e: Exception) {
                error.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}

