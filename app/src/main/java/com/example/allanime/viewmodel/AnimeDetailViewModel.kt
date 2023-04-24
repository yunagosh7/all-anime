package com.example.allanime.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.allanime.datamodels.Anime
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.datamodels.AnimeDetailData
import com.example.allanime.repository.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeDetailViewModel : ViewModel() {
    private val animeRepository = AnimeRepository()

    private var _anime = MutableLiveData<AnimeDetail>()
    val anime: LiveData<AnimeDetail> = _anime


    fun getAnimeById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = animeRepository.getAnimeDetail(id)
                _anime.postValue(result.data)
            } catch (e: Exception) {
                Log.d("AnimeDetailViewModel", "Error: ${e.message}")
            }
        }
    }

}