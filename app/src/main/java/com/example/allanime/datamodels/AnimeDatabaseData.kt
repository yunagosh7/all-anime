package com.example.allanime.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "anime_detail")
data class AnimeDatabaseData (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val synopsis: String,
    val image: String,
    val score: Float,
    val genres: String,
    var isSaved: Boolean
    )