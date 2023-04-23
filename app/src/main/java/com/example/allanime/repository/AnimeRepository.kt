package com.example.allanime.repository

import android.util.Log
import com.example.allanime.datamodels.Anime
import com.example.allanime.datamodels.AnimeTopList
import com.example.allanime.network.AnimeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeRepository {
    suspend fun getAnimeTop(): List<Anime> {
        return withContext(Dispatchers.IO) {
            val result = AnimeApi.apiService.getTopAnimes()

            result.data
        }
    }


}