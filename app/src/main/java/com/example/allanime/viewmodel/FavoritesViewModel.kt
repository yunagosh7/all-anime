package com.example.allanime.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.allanime.database.AnimeDatabase
import com.example.allanime.datamodels.AnimeDetail
import com.example.allanime.repository.AnimeRepository

class FavoritesViewModel(private val application: Application) : ViewModel() {
    private val animeRepository = AnimeRepository(AnimeDatabase.getInstance(application.applicationContext))

    private var _animeList = MutableLiveData<List<AnimeDetailViewModel>>()
    val animeList: LiveData<List<AnimeDetailViewModel>> = _animeList

    fun insertItemToDatabase(anime: AnimeDetail) {

    }


}



class FavoritesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}