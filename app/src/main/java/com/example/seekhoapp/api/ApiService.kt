package com.example.seekhoapp.api

import com.example.seekhoapp.api.response.AnimeDetailResponse
import com.example.seekhoapp.api.response.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top/anime")
    suspend fun fetchTopAnime(): AnimeListResponse

    @GET("anime/{anime_id}")
    suspend fun fetchAnimeDetails(@Path("anime_id") animeId: Int): AnimeDetailResponse
}
