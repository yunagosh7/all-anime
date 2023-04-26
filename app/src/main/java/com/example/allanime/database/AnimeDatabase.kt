package com.example.allanime.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.allanime.datamodels.AnimeDatabaseData
import com.example.allanime.datamodels.AnimeDetail


@Database(entities = [AnimeDatabaseData::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        var INSTANCE: AnimeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AnimeDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AnimeDatabase::class.java,
                    "anime_database"
                ).fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE as AnimeDatabase
        }
    }
}