package com.example.allanime.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.allanime.database.AnimeDatabase
import com.example.allanime.datamodels.AnimeDatabaseData
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.datamodels.AnimeDetailData
import com.example.allanime.repository.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val application: Application
) : ViewModel() {
    private val animeRepository = AnimeRepository(AnimeDatabase.getInstance(application.applicationContext))

    private var _anime = MutableLiveData<AnimeDetailData>()
    val anime: LiveData<AnimeDetailData> = _anime


    fun getAnimeById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = animeRepository.getAnimeDetail(id)
                _anime.postValue(result)
            } catch (e: Exception) {
                Log.d("AnimeDetailViewModel", "Error: ${e.message}")
            }
        }
    }

    fun insertItemToDatabase(anime: AnimeDetail) {
        var genres = ""
        anime.genres.forEach {
            Log.d("AnimeDetailViewModel", it.name)
            genres += "${it.name},"
        }
        Log.d("AnimeDetailViewModel", "Result: $genres")

        val genresList = genres.split(",")

        genresList.forEach {
            Log.d("AnimeDetailViewModel", "List elements: $it")
        }

        val animeDatabaseData = AnimeDatabaseData(
            name = anime.name,
            genres =  genres,
            image = anime.image.jpg.imageUrl,
            isSaved = true,
            score = anime.score,
            synopsis = anime.synopsis
        )
        CoroutineScope(Dispatchers.IO).launch {
            animeRepository.insertItemToDatabase(animeDatabaseData)
        }
        Log.d("AnimeDetailViewModel", animeDatabaseData.genres)
    }

}



class AnimeDetailViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeDetailViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}