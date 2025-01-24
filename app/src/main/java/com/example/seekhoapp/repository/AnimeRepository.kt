package com.example.seekhoapp.repository

import com.example.seekhoapp.api.ApiService
import com.example.seekhoapp.api.network.RetrofitClient

class AnimeRepository {
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun getAnime() = apiService.fetchTopAnime()

    suspend fun getAnimeDetails(animeId: Int) = apiService.fetchAnimeDetails(animeId)
}
