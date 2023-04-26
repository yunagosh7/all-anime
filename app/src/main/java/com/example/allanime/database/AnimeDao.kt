package com.example.allanime.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.allanime.datamodels.AnimeDatabaseData
import com.example.allanime.datamodels.AnimeDetail

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(anime: AnimeDatabaseData)

    @Query("SELECT * FROM anime_detail")
    fun getAll(): LiveData<List<AnimeDatabaseData>>
}