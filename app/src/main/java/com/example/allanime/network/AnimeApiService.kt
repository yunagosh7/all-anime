package com.example.allanime.network

import androidx.lifecycle.LiveData
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.datamodels.AnimeDetailData
import com.example.allanime.datamodels.AnimeTopList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.jikan.moe/v4/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeTopList

    @GET("top/manga")
    suspend fun getTopMangas(): AnimeTopList

    @GET("anime/{id}")
    suspend fun getAnimeDetailById(@Path("id") id: String): AnimeDetailData
}

object AnimeApi {
    val apiService: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }
}