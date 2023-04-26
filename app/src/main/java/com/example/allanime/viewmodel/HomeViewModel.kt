package com.example.allanime.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.allanime.database.AnimeDatabase
import com.example.allanime.datamodels.Anime
import com.example.allanime.repository.AnimeRepository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : ViewModel() {
    private val animeRepository = AnimeRepository(AnimeDatabase.getInstance(application.applicationContext))

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

class HomeViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
