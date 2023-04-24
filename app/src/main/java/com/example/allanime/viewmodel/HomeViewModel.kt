package com.example.allanime.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allanime.datamodels.Anime
import com.example.allanime.datamodels.AnimeTopList
import com.example.allanime.network.AnimeApi
import com.example.allanime.repository.AnimeRepository
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {
    private val animeRepository = AnimeRepository()

    private var _animeList = MutableLiveData<List<Anime>>()
    val animeList: LiveData<List<Anime>> = _animeList

    fun getAnimeTop() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = animeRepository.getAnimeTop()
                _animeList.postValue(result)
            } catch (e: Exception) {
                Log.d("HomeViewModel", "Error: ${e.message}")
            }
        }
    }

    fun getMangaTop() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = animeRepository.getMangaTop()
                _animeList.postValue(result)
            } catch (e: Exception) {
                Log.d("HomeViewModel", "Error: ${e.message}")
            }
        }
    }

}