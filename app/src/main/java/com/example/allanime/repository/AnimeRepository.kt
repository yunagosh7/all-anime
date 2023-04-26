package com.example.allanime.repository

import com.example.allanime.database.AnimeDatabase
import com.example.allanime.datamodels.Anime
import com.example.allanime.datamodels.AnimeDatabaseData
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.datamodels.AnimeDetailData
import com.example.allanime.network.AnimeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository(private val database: AnimeDatabase) {
    suspend fun getAnimeTop(): List<Anime> {
        return withContext(Dispatchers.IO) {
            val result = AnimeApi.apiService.getTopAnimes()

            result.data
        }
    }

    suspend fun getMangaTop(): List<Anime> {
        return withContext(Dispatchers.IO) {
            val result = AnimeApi.apiService.getTopMangas()
            result.data
        }
    }

    suspend fun getAnimeDetail(id: String): AnimeDetailData {
        return withContext(Dispatchers.IO) {
            val result = AnimeApi.apiService.getAnimeDetailById(id)
            result
        }
    }

    suspend fun insertItemToDatabase(anime: AnimeDatabaseData) {
        withContext(Dispatchers.IO) {
            database.animeDao().insertOne(anime)
        }
    }


}