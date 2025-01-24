package com.example.seekhoapp.ui.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seekhoapp.repository.AnimeRepository

class AnimeListViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeListViewModel(repository) as T
    }
}
