package com.example.seekhoapp.ui.animeDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhoapp.api.response.AnimeDetailResponse
import com.example.seekhoapp.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val repository: AnimeRepository) : ViewModel() {
    val animeDetails = MutableLiveData<AnimeDetailResponse>()
    private val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getAnimeDetails(animeId)
                animeDetails.postValue(response)
            } catch (e: Exception) {
                error.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}
