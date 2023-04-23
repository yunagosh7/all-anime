package com.example.allanime.network

import androidx.lifecycle.LiveData
import com.example.allanime.datamodels.AnimeTopList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.jikan.moe/v4/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeTopList
}

object AnimeApi {
    val apiService: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }
}